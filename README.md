# Pre-reqs

* sbt
* Java 8
* Docker Toolbox

# Build

`sbt assembly`

# Run

`docker-compose up`

# Issue

Attempts to communicate with the linked server result in `Got an error reading communication packets`:

```
$ docker-compose up
Creating network "dockermysql124_default" with the default driver
Creating dockermysql124_db_1
Building service
Step 1 : FROM java:8
 ---> 081ce13c85db
Step 2 : COPY entrypoint.sh /app/bin/entrypoint.sh
 ---> 159b93a3992f
Removing intermediate container 85f938c382e0
Step 3 : COPY target/scala-2.11/finch-sample-assembly-0.0.0.jar /app/bin/service.jar
 ---> 816c7f1b175b
Removing intermediate container 8fd8c06b9ea5
Step 4 : RUN apt-get update && apt-get --yes install netcat
 ---> Running in a49adf46908a
Get:1 http://security.debian.org jessie/updates InRelease [63.1 kB]
Ign http://httpredir.debian.org jessie InRelease
Get:2 http://httpredir.debian.org jessie-updates InRelease [142 kB]
Get:3 http://httpredir.debian.org jessie-backports InRelease [166 kB]
Get:4 http://httpredir.debian.org jessie Release.gpg [2373 B]
Get:5 http://httpredir.debian.org jessie Release [148 kB]
Get:6 http://security.debian.org jessie/updates/main amd64 Packages [290 kB]
Get:7 http://httpredir.debian.org jessie-updates/main amd64 Packages [4982 B]
Get:8 http://httpredir.debian.org jessie-backports/main amd64 Packages [614 kB]
Get:9 http://httpredir.debian.org jessie/main amd64 Packages [9034 kB]
Fetched 10.5 MB in 6s (1586 kB/s)
Reading package lists...
Reading package lists...
Building dependency tree...
Reading state information...
The following extra packages will be installed:
  netcat-traditional
The following NEW packages will be installed:
  netcat netcat-traditional
0 upgraded, 2 newly installed, 0 to remove and 2 not upgraded.
Need to get 75.3 kB of archives.
After this operation, 194 kB of additional disk space will be used.
Get:1 http://httpredir.debian.org/debian/ jessie/main netcat-traditional amd64 1.10-41 [66.3 kB]
Get:2 http://httpredir.debian.org/debian/ jessie/main netcat all 1.10-41 [8962 B]
debconf: delaying package configuration, since apt-utils is not installed
Fetched 75.3 kB in 0s (87.6 kB/s)
Selecting previously unselected package netcat-traditional.
(Reading database ... 17545 files and directories currently installed.)
Preparing to unpack .../netcat-traditional_1.10-41_amd64.deb ...
Unpacking netcat-traditional (1.10-41) ...
Selecting previously unselected package netcat.
Preparing to unpack .../netcat_1.10-41_all.deb ...
Unpacking netcat (1.10-41) ...
Setting up netcat-traditional (1.10-41) ...
update-alternatives: using /bin/nc.traditional to provide /bin/nc (nc) in auto mode
Setting up netcat (1.10-41) ...
 ---> a4fa36318e12
Removing intermediate container a49adf46908a
Step 5 : EXPOSE 8080/tcp
 ---> Running in f40ae1f032eb
 ---> d13b7e31f154
Removing intermediate container f40ae1f032eb
Step 6 : WORKDIR /app/bin
 ---> Running in f597f9c310fe
 ---> 128bb01ec39a
Removing intermediate container f597f9c310fe
Step 7 : ENTRYPOINT ./entrypoint.sh
 ---> Running in fe1b6d668667
 ---> 02c21500642e
Removing intermediate container fe1b6d668667
Step 8 : CMD service
 ---> Running in 43aa75013d1f
 ---> 27ff24694a91
Removing intermediate container 43aa75013d1f
Successfully built 27ff24694a91
Creating dockermysql124_service_1
Attaching to dockermysql124_db_1, dockermysql124_service_1
db_1      | Initializing database
db_1      | 2016-04-07T19:44:02.083096Z 0 [Warning] InnoDB: New log files created, LSN=45790
db_1      | 2016-04-07T19:44:02.125361Z 0 [Warning] InnoDB: Creating foreign key constraint system tables.
db_1      | 2016-04-07T19:44:02.181613Z 0 [Warning] No existing UUID has been found, so we assume that this is the first time that this server has been started. Generating a new UUID: 144562ef-fcf9-11e5-83dd-0242ac120002.
db_1      | 2016-04-07T19:44:02.182961Z 0 [Warning] Gtid table is not ready to be used. Table 'mysql.gtid_executed' cannot be opened.
db_1      | 2016-04-07T19:44:02.183297Z 1 [Warning] root@localhost is created with an empty password ! Please consider switching off the --initialize-insecure option.
db_1      | 2016-04-07T19:44:02.643732Z 1 [Warning] 'user' entry 'root@localhost' ignored in --skip-name-resolve mode.
db_1      | 2016-04-07T19:44:02.643808Z 1 [Warning] 'user' entry 'mysql.sys@localhost' ignored in --skip-name-resolve mode.
db_1      | 2016-04-07T19:44:02.644507Z 1 [Warning] 'db' entry 'sys mysql.sys@localhost' ignored in --skip-name-resolve mode.
db_1      | 2016-04-07T19:44:02.644595Z 1 [Warning] 'proxies_priv' entry '@ root@localhost' ignored in --skip-name-resolve mode.
db_1      | 2016-04-07T19:44:02.644656Z 1 [Warning] 'tables_priv' entry 'sys_config mysql.sys@localhost' ignored in --skip-name-resolve mode.
db_1      | Database initialized
db_1      | MySQL init process in progress...
db_1      | 2016-04-07T19:44:04.821079Z 0 [Note] mysqld (mysqld 5.7.11) starting as process 39 ...
db_1      | 2016-04-07T19:44:04.825284Z 0 [Note] InnoDB: PUNCH HOLE support available
db_1      | 2016-04-07T19:44:04.825335Z 0 [Note] InnoDB: Mutexes and rw_locks use GCC atomic builtins
db_1      | 2016-04-07T19:44:04.825346Z 0 [Note] InnoDB: Uses event mutexes
db_1      | 2016-04-07T19:44:04.825354Z 0 [Note] InnoDB: GCC builtin __atomic_thread_fence() is used for memory barrier
db_1      | 2016-04-07T19:44:04.825361Z 0 [Note] InnoDB: Compressed tables use zlib 1.2.8
db_1      | 2016-04-07T19:44:04.825367Z 0 [Note] InnoDB: Using Linux native AIO
db_1      | 2016-04-07T19:44:04.826570Z 0 [Note] InnoDB: Number of pools: 1
db_1      | 2016-04-07T19:44:04.826789Z 0 [Note] InnoDB: Using CPU crc32 instructions
db_1      | 2016-04-07T19:44:04.833898Z 0 [Note] InnoDB: Initializing buffer pool, total size = 128M, instances = 1, chunk size = 128M
db_1      | 2016-04-07T19:44:04.840654Z 0 [Note] InnoDB: Completed initialization of buffer pool
db_1      | 2016-04-07T19:44:04.841825Z 0 [Note] InnoDB: If the mysqld execution user is authorized, page cleaner thread priority can be changed. See the man page of setpriority().
db_1      | 2016-04-07T19:44:04.853508Z 0 [Note] InnoDB: Highest supported file format is Barracuda.
db_1      | 2016-04-07T19:44:04.860128Z 0 [Note] InnoDB: Creating shared tablespace for temporary tables
db_1      | 2016-04-07T19:44:04.860279Z 0 [Note] InnoDB: Setting file './ibtmp1' size to 12 MB. Physically writing the file full; Please wait ...
db_1      | 2016-04-07T19:44:04.881345Z 0 [Note] InnoDB: File './ibtmp1' size is now 12 MB.
db_1      | 2016-04-07T19:44:04.882911Z 0 [Note] InnoDB: 96 redo rollback segment(s) found. 96 redo rollback segment(s) are active.
db_1      | 2016-04-07T19:44:04.883012Z 0 [Note] InnoDB: 32 non-redo rollback segment(s) are active.
db_1      | 2016-04-07T19:44:04.884308Z 0 [Note] InnoDB: Waiting for purge to start
db_1      | 2016-04-07T19:44:04.935250Z 0 [Note] InnoDB: 5.7.11 started; log sequence number 2492554
db_1      | 2016-04-07T19:44:04.936488Z 0 [Note] Plugin 'FEDERATED' is disabled.
db_1      | 2016-04-07T19:44:04.937589Z 0 [Warning] Failed to set up SSL because of the following SSL library error: SSL context is not usable without certificate and private key
db_1      | 2016-04-07T19:44:04.937940Z 0 [Note] InnoDB: Loading buffer pool(s) from /var/lib/mysql/ib_buffer_pool
db_1      | 2016-04-07T19:44:04.939812Z 0 [Warning] 'user' entry 'root@localhost' ignored in --skip-name-resolve mode.
db_1      | 2016-04-07T19:44:04.939870Z 0 [Warning] 'user' entry 'mysql.sys@localhost' ignored in --skip-name-resolve mode.
db_1      | 2016-04-07T19:44:04.939893Z 0 [Warning] 'db' entry 'sys mysql.sys@localhost' ignored in --skip-name-resolve mode.
db_1      | 2016-04-07T19:44:04.939907Z 0 [Warning] 'proxies_priv' entry '@ root@localhost' ignored in --skip-name-resolve mode.
db_1      | 2016-04-07T19:44:04.940708Z 0 [Warning] 'tables_priv' entry 'sys_config mysql.sys@localhost' ignored in --skip-name-resolve mode.
db_1      | 2016-04-07T19:44:04.944797Z 0 [Note] InnoDB: Buffer pool(s) load completed at 160407 19:44:04
db_1      | 2016-04-07T19:44:04.946880Z 0 [Note] Event Scheduler: Loaded 0 events
db_1      | 2016-04-07T19:44:04.947311Z 0 [Note] mysqld: ready for connections.
db_1      | Version: '5.7.11'  socket: '/var/run/mysqld/mysqld.sock'  port: 0  MySQL Community Server (GPL)
db_1      | Warning: Unable to load '/usr/share/zoneinfo/Factory' as time zone. Skipping it.
db_1      | Warning: Unable to load '/usr/share/zoneinfo/iso3166.tab' as time zone. Skipping it.
db_1      | Warning: Unable to load '/usr/share/zoneinfo/leap-seconds.list' as time zone. Skipping it.
db_1      | Warning: Unable to load '/usr/share/zoneinfo/posix/Factory' as time zone. Skipping it.
db_1      | Warning: Unable to load '/usr/share/zoneinfo/right/Factory' as time zone. Skipping it.
db_1      | Warning: Unable to load '/usr/share/zoneinfo/zone.tab' as time zone. Skipping it.
db_1      | 2016-04-07T19:44:07.391914Z 4 [Warning] 'db' entry 'sys mysql.sys@localhost' ignored in --skip-name-resolve mode.
db_1      | 2016-04-07T19:44:07.391979Z 4 [Warning] 'proxies_priv' entry '@ root@localhost' ignored in --skip-name-resolve mode.
db_1      | 2016-04-07T19:44:07.392014Z 4 [Warning] 'tables_priv' entry 'sys_config mysql.sys@localhost' ignored in --skip-name-resolve mode.
db_1      | mysql: [Warning] Using a password on the command line interface can be insecure.
db_1      | mysql: [Warning] Using a password on the command line interface can be insecure.
db_1      | mysql: [Warning] Using a password on the command line interface can be insecure.
db_1      | mysql: [Warning] Using a password on the command line interface can be insecure.
db_1      | 2016-04-07T19:44:07.411537Z 8 [Warning] 'db' entry 'sys mysql.sys@localhost' ignored in --skip-name-resolve mode.
db_1      | 2016-04-07T19:44:07.411579Z 8 [Warning] 'proxies_priv' entry '@ root@localhost' ignored in --skip-name-resolve mode.
db_1      | 2016-04-07T19:44:07.411693Z 8 [Warning] 'tables_priv' entry 'sys_config mysql.sys@localhost' ignored in --skip-name-resolve mode.
db_1      | 2016-04-07T19:44:07.412403Z 0 [Note] Giving 0 client threads a chance to die gracefully
db_1      | 2016-04-07T19:44:07.412423Z 0 [Note] Shutting down slave threads
db_1      | 2016-04-07T19:44:07.412430Z 0 [Note] Forcefully disconnecting 0 remaining clients
db_1      | 2016-04-07T19:44:07.412438Z 0 [Note] Event Scheduler: Purging the queue. 0 events
db_1      | 2016-04-07T19:44:07.412467Z 0 [Note] Binlog end
db_1      |
db_1      | /usr/local/bin/docker-entrypoint.sh: ignoring /docker-entrypoint-initdb.d/*
db_1      |
db_1      | 2016-04-07T19:44:07.413693Z 0 [Note] Shutting down plugin 'ngram'
db_1      | 2016-04-07T19:44:07.413714Z 0 [Note] Shutting down plugin 'BLACKHOLE'
db_1      | 2016-04-07T19:44:07.413722Z 0 [Note] Shutting down plugin 'partition'
db_1      | 2016-04-07T19:44:07.413728Z 0 [Note] Shutting down plugin 'ARCHIVE'
db_1      | 2016-04-07T19:44:07.413734Z 0 [Note] Shutting down plugin 'MEMORY'
db_1      | 2016-04-07T19:44:07.413740Z 0 [Note] Shutting down plugin 'INNODB_SYS_VIRTUAL'
db_1      | 2016-04-07T19:44:07.413746Z 0 [Note] Shutting down plugin 'INNODB_SYS_DATAFILES'
db_1      | 2016-04-07T19:44:07.413752Z 0 [Note] Shutting down plugin 'INNODB_SYS_TABLESPACES'
db_1      | 2016-04-07T19:44:07.413757Z 0 [Note] Shutting down plugin 'INNODB_SYS_FOREIGN_COLS'
db_1      | 2016-04-07T19:44:07.413763Z 0 [Note] Shutting down plugin 'INNODB_SYS_FOREIGN'
db_1      | 2016-04-07T19:44:07.413768Z 0 [Note] Shutting down plugin 'INNODB_SYS_FIELDS'
db_1      | 2016-04-07T19:44:07.413774Z 0 [Note] Shutting down plugin 'INNODB_SYS_COLUMNS'
db_1      | 2016-04-07T19:44:07.413779Z 0 [Note] Shutting down plugin 'INNODB_SYS_INDEXES'
db_1      | 2016-04-07T19:44:07.413784Z 0 [Note] Shutting down plugin 'INNODB_SYS_TABLESTATS'
db_1      | 2016-04-07T19:44:07.413798Z 0 [Note] Shutting down plugin 'INNODB_SYS_TABLES'
db_1      | 2016-04-07T19:44:07.413804Z 0 [Note] Shutting down plugin 'INNODB_FT_INDEX_TABLE'
db_1      | 2016-04-07T19:44:07.413810Z 0 [Note] Shutting down plugin 'INNODB_FT_INDEX_CACHE'
db_1      | 2016-04-07T19:44:07.413815Z 0 [Note] Shutting down plugin 'INNODB_FT_CONFIG'
db_1      | 2016-04-07T19:44:07.413821Z 0 [Note] Shutting down plugin 'INNODB_FT_BEING_DELETED'
db_1      | 2016-04-07T19:44:07.413826Z 0 [Note] Shutting down plugin 'INNODB_FT_DELETED'
db_1      | 2016-04-07T19:44:07.413832Z 0 [Note] Shutting down plugin 'INNODB_FT_DEFAULT_STOPWORD'
db_1      | 2016-04-07T19:44:07.413837Z 0 [Note] Shutting down plugin 'INNODB_METRICS'
db_1      | 2016-04-07T19:44:07.413843Z 0 [Note] Shutting down plugin 'INNODB_TEMP_TABLE_INFO'
db_1      | 2016-04-07T19:44:07.413848Z 0 [Note] Shutting down plugin 'INNODB_BUFFER_POOL_STATS'
db_1      | 2016-04-07T19:44:07.413854Z 0 [Note] Shutting down plugin 'INNODB_BUFFER_PAGE_LRU'
db_1      | 2016-04-07T19:44:07.413859Z 0 [Note] Shutting down plugin 'INNODB_BUFFER_PAGE'
db_1      | 2016-04-07T19:44:07.413864Z 0 [Note] Shutting down plugin 'INNODB_CMP_PER_INDEX_RESET'
db_1      | 2016-04-07T19:44:07.413870Z 0 [Note] Shutting down plugin 'INNODB_CMP_PER_INDEX'
db_1      | 2016-04-07T19:44:07.413875Z 0 [Note] Shutting down plugin 'INNODB_CMPMEM_RESET'
db_1      | 2016-04-07T19:44:07.413881Z 0 [Note] Shutting down plugin 'INNODB_CMPMEM'
db_1      | 2016-04-07T19:44:07.413886Z 0 [Note] Shutting down plugin 'INNODB_CMP_RESET'
db_1      | 2016-04-07T19:44:07.413892Z 0 [Note] Shutting down plugin 'INNODB_CMP'
db_1      | 2016-04-07T19:44:07.413897Z 0 [Note] Shutting down plugin 'INNODB_LOCK_WAITS'
db_1      | 2016-04-07T19:44:07.413902Z 0 [Note] Shutting down plugin 'INNODB_LOCKS'
db_1      | 2016-04-07T19:44:07.413908Z 0 [Note] Shutting down plugin 'INNODB_TRX'
db_1      | 2016-04-07T19:44:07.413913Z 0 [Note] Shutting down plugin 'InnoDB'
db_1      | 2016-04-07T19:44:07.413970Z 0 [Note] InnoDB: FTS optimize thread exiting.
db_1      | 2016-04-07T19:44:07.414115Z 0 [Note] InnoDB: Starting shutdown...
db_1      | 2016-04-07T19:44:07.515068Z 0 [Note] InnoDB: Dumping buffer pool(s) to /var/lib/mysql/ib_buffer_pool
db_1      | 2016-04-07T19:44:07.515645Z 0 [Note] InnoDB: Buffer pool(s) dump completed at 160407 19:44:07
db_1      | 2016-04-07T19:44:08.742375Z 0 [Note] InnoDB: Shutdown completed; log sequence number 12068028
db_1      | 2016-04-07T19:44:08.743884Z 0 [Note] InnoDB: Removed temporary tablespace data file: "ibtmp1"
db_1      | 2016-04-07T19:44:08.743928Z 0 [Note] Shutting down plugin 'MyISAM'
db_1      | 2016-04-07T19:44:08.743944Z 0 [Note] Shutting down plugin 'MRG_MYISAM'
db_1      | 2016-04-07T19:44:08.743951Z 0 [Note] Shutting down plugin 'CSV'
db_1      | 2016-04-07T19:44:08.743958Z 0 [Note] Shutting down plugin 'PERFORMANCE_SCHEMA'
db_1      | 2016-04-07T19:44:08.743979Z 0 [Note] Shutting down plugin 'sha256_password'
db_1      | 2016-04-07T19:44:08.743985Z 0 [Note] Shutting down plugin 'mysql_native_password'
db_1      | 2016-04-07T19:44:08.743991Z 0 [Note] Shutting down plugin 'keyring_file'
db_1      | 2016-04-07T19:44:08.744143Z 0 [Note] Shutting down plugin 'binlog'
db_1      | 2016-04-07T19:44:08.744854Z 0 [Note] mysqld: Shutdown complete
db_1      |
db_1      |
db_1      | MySQL init process done. Ready for start up.
db_1      |
db_1      | 2016-04-07T19:44:08.991028Z 0 [Note] mysqld (mysqld 5.7.11) starting as process 1 ...
db_1      | 2016-04-07T19:44:08.997307Z 0 [Note] InnoDB: PUNCH HOLE support available
db_1      | 2016-04-07T19:44:08.997362Z 0 [Note] InnoDB: Mutexes and rw_locks use GCC atomic builtins
db_1      | 2016-04-07T19:44:08.997373Z 0 [Note] InnoDB: Uses event mutexes
db_1      | 2016-04-07T19:44:08.997381Z 0 [Note] InnoDB: GCC builtin __atomic_thread_fence() is used for memory barrier
db_1      | 2016-04-07T19:44:08.997388Z 0 [Note] InnoDB: Compressed tables use zlib 1.2.8
db_1      | 2016-04-07T19:44:08.997397Z 0 [Note] InnoDB: Using Linux native AIO
db_1      | 2016-04-07T19:44:08.997554Z 0 [Note] InnoDB: Number of pools: 1
db_1      | 2016-04-07T19:44:08.997632Z 0 [Note] InnoDB: Using CPU crc32 instructions
db_1      | 2016-04-07T19:44:09.006471Z 0 [Note] InnoDB: Initializing buffer pool, total size = 128M, instances = 1, chunk size = 128M
db_1      | 2016-04-07T19:44:09.016277Z 0 [Note] InnoDB: Completed initialization of buffer pool
db_1      | 2016-04-07T19:44:09.020279Z 0 [Note] InnoDB: If the mysqld execution user is authorized, page cleaner thread priority can be changed. See the man page of setpriority().
db_1      | 2016-04-07T19:44:09.032573Z 0 [Note] InnoDB: Highest supported file format is Barracuda.
db_1      | 2016-04-07T19:44:09.042111Z 0 [Note] InnoDB: Creating shared tablespace for temporary tables
db_1      | 2016-04-07T19:44:09.042312Z 0 [Note] InnoDB: Setting file './ibtmp1' size to 12 MB. Physically writing the file full; Please wait ...
db_1      | 2016-04-07T19:44:09.070257Z 0 [Note] InnoDB: File './ibtmp1' size is now 12 MB.
db_1      | 2016-04-07T19:44:09.070980Z 0 [Note] InnoDB: 96 redo rollback segment(s) found. 96 redo rollback segment(s) are active.
db_1      | 2016-04-07T19:44:09.071011Z 0 [Note] InnoDB: 32 non-redo rollback segment(s) are active.
db_1      | 2016-04-07T19:44:09.071334Z 0 [Note] InnoDB: Waiting for purge to start
db_1      | 2016-04-07T19:44:09.121447Z 0 [Note] InnoDB: 5.7.11 started; log sequence number 12068028
db_1      | 2016-04-07T19:44:09.122447Z 0 [Note] Plugin 'FEDERATED' is disabled.
db_1      | 2016-04-07T19:44:09.124731Z 0 [Warning] Failed to set up SSL because of the following SSL library error: SSL context is not usable without certificate and private key
db_1      | 2016-04-07T19:44:09.125089Z 0 [Note] Server hostname (bind-address): '*'; port: 3306
db_1      | 2016-04-07T19:44:09.125129Z 0 [Note] IPv6 is available.
db_1      | 2016-04-07T19:44:09.125142Z 0 [Note]   - '::' resolves to '::';
db_1      | 2016-04-07T19:44:09.125150Z 0 [Note] Server socket created on IP: '::'.
db_1      | 2016-04-07T19:44:09.125505Z 0 [Note] InnoDB: Loading buffer pool(s) from /var/lib/mysql/ib_buffer_pool
db_1      | 2016-04-07T19:44:09.129123Z 0 [Warning] 'db' entry 'sys mysql.sys@localhost' ignored in --skip-name-resolve mode.
db_1      | 2016-04-07T19:44:09.129180Z 0 [Warning] 'proxies_priv' entry '@ root@localhost' ignored in --skip-name-resolve mode.
db_1      | 2016-04-07T19:44:09.132008Z 0 [Warning] 'tables_priv' entry 'sys_config mysql.sys@localhost' ignored in --skip-name-resolve mode.
db_1      | 2016-04-07T19:44:09.134882Z 0 [Note] InnoDB: Buffer pool(s) load completed at 160407 19:44:09
db_1      | 2016-04-07T19:44:09.143471Z 0 [Note] Event Scheduler: Loaded 0 events
db_1      | 2016-04-07T19:44:09.144138Z 0 [Note] mysqld: ready for connections.
db_1      | Version: '5.7.11'  socket: '/var/run/mysqld/mysqld.sock'  port: 3306  MySQL Community Server (GPL)
service_1 | OpenJDK 64-Bit Server VM warning: ignoring option MaxPermSize=1024m; support was removed in 8.0
service_1 | I 0407 19:44:52.224 THREAD1:  => com.twitter.server.handler.AdminRedirectHandler
service_1 | I 0407 19:44:52.238 THREAD1: /admin => com.twitter.server.handler.SummaryHandler
service_1 | I 0407 19:44:52.239 THREAD1: /admin/server_info => com.twitter.finagle.Filter$$anon$1
service_1 | I 0407 19:44:52.258 THREAD1: /admin/threads => com.twitter.server.handler.ThreadsHandler
service_1 | I 0407 19:44:52.262 THREAD1: /admin/threads.json => com.twitter.server.handler.ThreadsHandler
service_1 | I 0407 19:44:52.248 THREAD1: /admin/contention => com.twitter.finagle.Filter$$anon$1
service_1 | I 0407 19:44:52.258 THREAD1: /admin/lint.json => com.twitter.server.handler.LintHandler
service_1 | I 0407 19:44:52.269 THREAD1: /admin/announcer => com.twitter.finagle.Filter$$anon$1
service_1 | I 0407 19:44:52.270 THREAD1: /admin/dtab => com.twitter.finagle.Filter$$anon$1
service_1 | I 0407 19:44:52.257 THREAD1: /admin/lint => com.twitter.server.handler.LintHandler
service_1 | I 0407 19:44:52.272 THREAD1: /admin/pprof/profile => com.twitter.server.handler.ProfileResourceHandler
service_1 | I 0407 19:44:52.273 THREAD1: /admin/pprof/contention => com.twitter.server.handler.ProfileResourceHandler
service_1 | I 0407 19:44:52.273 THREAD1: /admin/ping => com.twitter.server.handler.ReplyHandler
service_1 | I 0407 19:44:52.273 THREAD1: /admin/shutdown => com.twitter.server.handler.ShutdownHandler
service_1 | I 0407 19:44:52.272 THREAD1: /admin/pprof/heap => com.twitter.server.handler.HeapResourceHandler
service_1 | I 0407 19:44:52.274 THREAD1: /admin/tracing => com.twitter.server.handler.TracingHandler
service_1 | I 0407 19:44:52.275 THREAD1: /admin/events => com.twitter.server.handler.EventsHandler
service_1 | I 0407 19:44:52.275 THREAD1: /admin/events/record/ => com.twitter.server.handler.EventRecordingHandler
service_1 | I 0407 19:44:52.276 THREAD1: /admin/logging => com.twitter.server.handler.LoggingHandler
service_1 | I 0407 19:44:52.276 THREAD1: /admin/metrics => com.twitter.server.handler.MetricQueryHandler
service_1 | I 0407 19:44:52.276 THREAD1: /admin/clients/ => com.twitter.server.handler.ClientRegistryHandler
service_1 | I 0407 19:44:52.278 THREAD1: /admin/servers/ => com.twitter.server.handler.ServerRegistryHandler
service_1 | I 0407 19:44:52.279 THREAD1: /admin/files/ => com.twitter.server.handler.ResourceHandler
service_1 | I 0407 19:44:52.280 THREAD1: /admin/registry.json => com.twitter.server.handler.RegistryHandler
service_1 | I 0407 19:44:52.288 THREAD1: /favicon.ico => com.twitter.server.handler.ResourceHandler
service_1 | I 0407 19:44:52.311 THREAD1: Serving admin http on 0.0.0.0/0.0.0.0:9990
service_1 | I 0407 19:44:52.404 THREAD1: Finagle version 6.34.0 (rev=44f444f606b10582c2da8d5770b7879ddd961211) built at 20160310-155158
service_1 | I 0407 19:44:53.256 THREAD1: Migrating database using Flyway
service_1 | SLF4J: Failed to load class "org.slf4j.impl.StaticLoggerBinder".
service_1 | SLF4J: Defaulting to no-operation (NOP) logger implementation
service_1 | SLF4J: See http://www.slf4j.org/codes.html#StaticLoggerBinder for further details.
service_1 | Thu Apr 07 19:44:53 UTC 2016 WARN: Establishing SSL connection without server's identity verification is not recommended. According to MySQL 5.5.45+, 5.6.26+ and 5.7.6+ requirements SSL connection must be established by default if explicit option isn't set. For compliance with existing applications not using SSL the verifyServerCertificate property is set to 'false'. You need either to explicitly disable SSL by setting useSSL=false, or set useSSL=true and provide truststore for server certificate verification.
service_1 | org.flywaydb.core.api.FlywayException: Unable to obtain Jdbc connection from DataSource
service_1 | 	at org.flywaydb.core.internal.util.jdbc.JdbcUtils.openConnection(JdbcUtils.java:56)
service_1 | 	at org.flywaydb.core.Flyway.execute(Flyway.java:1314)
service_1 | 	at org.flywaydb.core.Flyway.migrate(Flyway.java:917)
service_1 | 	at io.finch.todo.Main$.main(Main.scala:80)
service_1 | 	at sun.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
service_1 | 	at sun.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:62)
service_1 | 	at sun.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)
service_1 | 	at java.lang.reflect.Method.invoke(Method.java:498)
service_1 | 	at com.twitter.app.App$$anonfun$nonExitingMain$3.apply(App.scala:176)
service_1 | 	at com.twitter.app.App$$anonfun$nonExitingMain$3.apply(App.scala:175)
service_1 | 	at scala.Option.foreach(Option.scala:257)
service_1 | 	at com.twitter.app.App$class.nonExitingMain(App.scala:175)
service_1 | 	at io.finch.todo.Main$.nonExitingMain(Main.scala:17)
service_1 | 	at com.twitter.app.App$class.main(App.scala:141)
service_1 | 	at io.finch.todo.Main$.main(Main.scala:17)
service_1 | 	at io.finch.todo.Main.main(Main.scala)
service_1 | Caused by: java.sql.SQLException: Can not issue NULL query.
service_1 | 	at com.mysql.jdbc.SQLError.createSQLException(SQLError.java:957)
service_1 | 	at com.mysql.jdbc.SQLError.createSQLException(SQLError.java:896)
service_1 | 	at com.mysql.jdbc.SQLError.createSQLException(SQLError.java:885)
service_1 | 	at com.mysql.jdbc.SQLError.createSQLException(SQLError.java:860)
service_1 | 	at com.mysql.jdbc.StatementImpl.checkNullOrEmptyQuery(StatementImpl.java:481)
service_1 | 	at com.mysql.jdbc.StatementImpl.executeInternal(StatementImpl.java:749)
service_1 | 	at com.mysql.jdbc.StatementImpl.execute(StatementImpl.java:740)
service_1 | 	at org.flywaydb.core.internal.util.jdbc.DriverDataSource.getConnectionFromDriver(DriverDataSource.java:414)
service_1 | 	at org.flywaydb.core.internal.util.jdbc.DriverDataSource.getConnection(DriverDataSource.java:365)
service_1 | 	at org.flywaydb.core.internal.util.jdbc.JdbcUtils.openConnection(JdbcUtils.java:50)
service_1 | 	... 15 more
service_1 | Exception thrown in main on startup
db_1      | 2016-04-07T19:44:54.584825Z 3 [Note] Aborted connection 3 to db: 'sample' user: 'root' host: '172.18.0.3' (Got an error reading communication packets)
dockermysql124_service_1 exited with code 1
Killing dockermysql124_db_1 ... done
```