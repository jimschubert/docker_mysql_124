FROM java:8

COPY entrypoint.sh /app/bin/entrypoint.sh
COPY target/scala-2.11/finch-sample-assembly-0.0.0.jar /app/bin/service.jar

RUN apt-get update && apt-get --yes install netcat

EXPOSE 8080/tcp
WORKDIR /app/bin
ENTRYPOINT ["./entrypoint.sh"]
CMD ["service"]
