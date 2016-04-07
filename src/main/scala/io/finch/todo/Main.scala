/* https://raw.githubusercontent.com/finagle/finch/master/LICENSE */
package io.finch.todo

import java.util.UUID

import com.twitter.app.Flag
import com.twitter.finagle.{Http, Service}
import com.twitter.finagle.http.{Request, Response}
import com.twitter.finagle.stats.Counter
import com.twitter.server.TwitterServer
import com.twitter.util.Await
import io.circe.generic.auto._
import io.finch._
import io.finch.circe._
import org.flywaydb.core.Flyway

object Main extends TwitterServer {

  val port: Flag[Int] = flag("port", 8080, "TCP port for HTTP server")

  val todos: Counter = statsReceiver.counter("todos")

  val postedTodo: Endpoint[Todo] =
    body.as[UUID => Todo].map(_(UUID.randomUUID()))

  val getTodos: Endpoint[List[Todo]] = get("todos") {
    Ok(Todo.list())
  }

  val postTodo: Endpoint[Todo] = post("todos" :: postedTodo) { t: Todo =>
    todos.incr()
    Todo.save(t)

    Created(t)
  }

  val deleteTodo: Endpoint[Todo] = delete("todos" :: uuid) { id: UUID =>
    Todo.get(id) match {
      case Some(t) => Todo.delete(id); Ok(t)
      case None => throw new TodoNotFound(id)
    }
  }

  val deleteTodos: Endpoint[List[Todo]] = delete("todos") {
    val all: List[Todo] = Todo.list()
    all.foreach(t => Todo.delete(t.id))

    Ok(all)
  }

  val patchedTodo: Endpoint[Todo => Todo] = body.as[Todo => Todo]

  val patchTodo: Endpoint[Todo] =
    patch("todos" :: uuid :: patchedTodo) { (id: UUID, pt: Todo => Todo) =>
      Todo.get(id) match {
        case Some(currentTodo) =>
          val newTodo: Todo = pt(currentTodo)
          Todo.delete(id)
          Todo.save(newTodo)

          Ok(newTodo)
        case None => throw TodoNotFound(id)
      }
    }

  val api: Service[Request, Response] = (
    getTodos :+: postTodo :+: deleteTodo :+: deleteTodos :+: patchTodo
    ).handle({
    case e: TodoNotFound => NotFound(e)
  }).toService

  def main(): Unit = {

    log.info("Migrating database using Flyway")

    val flyway = new Flyway()
    flyway.setDataSource("jdbc:mysql://db:3306/sample", "root", "f1nch!", null)
    flyway.setLocations("/db")
    flyway.setBaselineOnMigrate(true)
    flyway.migrate()

    log.info("Serving the Todo application")

    val server = Http.server
      .withStatsReceiver(statsReceiver)
      .serve(s":${port()}", api)

    onExit { server.close() }

    Await.ready(adminHttpServer)
  }
}