# springboot-redis-demo
A simple demo to show how Java Spring Boot connect to a simple Redis server. Redis Server Setup can refer to [My Redis Setup Project](https://github.com/bennzhang/redis-basic-sentinel-cluster)

## redis.conf file

Simple redis configuation files are included in the `redis-server` folder `redis.conf` is default configuation file when redis installed. While `redis.test.conf` is the configuration file Redis Server runs on. Redis Server is on a remote server.
```
$ cat redis.test.conf
include redis.conf
port 6379
bind 0.0.0.0
requirepass "yourpassword"

dir .
```

Start redis-server on the remote server:
```
redis-server redis.test.conf &
```

## Spring boot application.properties file
Change `<remote-server-ip>` to the real IP.

```
server.port=5001

spring.redis.hostname=<remote-server-ip>
spring.redis.password=yourpassword
spring.redis.port=6379
spring.redis.timeout=2000
```

## Compile/Run
```
# compile
mvnw clean install -DskipTests

# run
java -jar target\springboot-redis-demo-0.0.1-SNAPSHOT.jar
```


## Other notes
This example only shows how to connect Redis. There is no other database datasources.  But by default, spring boot needs database driver. Add the following `@EnableAutoConfiguration` to avoid  `Cannot determine embedded database driver class for database type NONE` error

```
@SpringBootApplication
@EnableAutoConfiguration(exclude = {DataSourceAutoConfiguration.class, DataSourceTransactionManagerAutoConfiguration.class, HibernateJpaAutoConfiguration.class})
public class SpringbootRedisDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootRedisDemoApplication.class, args);
    }
}
```

To add following in you domain object to avoid this error `java.lang.IllegalArgumentException: DefaultSerializer requires a Serializable payload but received an object of type`

```
@Data
public class UserSession implements Serializable {
    /**
     * Solve this error
     * java.lang.IllegalArgumentException: DefaultSerializer requires a Serializable payload but received an object of type
     */
    private static final long serialVersionUID = -1L;
```

## Test
The test json files are in `json` folder. 

```
# add one user
curl -H "Content-Type: application/json" -X POST -d @user5.json http://localhost:5001/user/add/5

# add another user
curl -H "Content-Type: application/json" -X POST -d @user6.json http://localhost:5001/user/add/6

# get all user
curl -H "Content-Type: application/json" -X GET http://localhost:5001/user/getAll

# delete one user
curl -H "Content-Type: application/json" -X GET http://localhost:5001/user/delete/6
```

