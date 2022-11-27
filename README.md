# Spring-boot blog backend application

[//]: # (Table of Contents)
<details>
    <summary>Table of Contents</summary>
    <ol>
        <li>
            <a href="https://github.com/Orcohen33/spring-boot-blog-backend/tree/main#about-the-project">About the project</a>
        </li>
        <li>
            <a href="https://github.com/Orcohen33/spring-boot-blog-backend/tree/main#about-the-service">About the Service</a>
        </li>
        <li>
            <a href="https://github.com/Orcohen33/spring-boot-blog-backend/tree/main#technologies">Technologies</a>
        </li>
        <li>
            <a href="https://github.com/Orcohen33/spring-boot-blog-backend/tree/main#database-configuration">Database configuration</a>
        </li>
        <li>
            <a href="https://github.com/Orcohen33/spring-boot-blog-backend/tree/main#pre-requisites">Pre-requisites</a>
        </li>
        <li>
            <a href="https://github.com/Orcohen33/spring-boot-blog-backend/tree/main#how-to-run">How to run</a>
        </li>
        <li>
            <a href="https://github.com/Orcohen33/spring-boot-blog-backend/tree/main#how-to-test">How to test</a>
        </li>
        <li>
            <a href="https://github.com/Orcohen33/spring-boot-blog-backend/tree/main#how-to-use">How to use</a>
        </li>
        <li>
            <a href="https://github.com/Orcohen33/spring-boot-blog-backend/tree/main#how-to-contribute">How to contribute</a>
        </li>
    </ol>
</details>


## About the project
Simple blog backend project with a REST API.
This project was developed as a part of a blog application. It is a REST API that provides the following features:
- CRUD operations for posts
- CRUD operations for comments
- CRUD operations for users
- Authentication and authorization using JWT


## Technologies
- Spring Boot
- Spring Security
- Spring Data JPA
- MySQL
- JWT
- Lombok
- Swagger

## Database configuration
  The database configuration is located in the application.properties file. The default configuration is:
```
spring.datasource.url=jdbc:mysql://localhost:3306/<your_mysql_host_or_ip>?useSSL=false&serverTimezone=UTC&createDatabaseIfNotExist=true
spring.datasource.username=<your_mysql_username>
spring.datasource.password=<your_mysql_password>
```
  To change the database configuration, you can either change the values in the application.properties file or you can set the following environment variables:
```
SPRING_DATASOURCE_URL
SPRING_DATASOURCE_USERNAME
SPRING_DATASOURCE_PASSWORD
```


## Pre-requisites
- Java 17
- Maven
- MySQL

### How to run
  This application is packaged as a war which has Tomcat 8 embedded. No Tomcat or JBoss installation is necessary.
  You run it using the `java -jar` command.
- Clone this repository
- Make sure you are using JDK 11 and Maven 3.x
- You can build the project and run the tests by running `mvn clean package`
- Once successfully built, you can run the service by one of these two methods:
```
java -jar target/blog-rest-api-0.0.1-SNAPSHOT.jar
```
  The application will start running at http://localhost:8081.

- Check the stdout or boot_example.log file to make sure no exceptions are thrown
  Once the application runs you should see something like this
```
2022-11-26 15:59:00.000  INFO 1 --- [    main] c.e.s.SpringBootBlogBackendApplication    : Started SpringBootBlogBackendApplication in 2.5 seconds (JVM running for 3.05)
```


### How to test
  You can test the application using Swagger UI. The Swagger UI is available at http://localhost:8081/swagger-ui.html.
  You can also test the application using Postman. The Postman collection is available at https://www.getpostman.com/collections/1b2b2b2b2b2b2b2b2b2b.
  I've also implemented a controller and integration tests using JUnit and Mockito.


### How to use
  To use the endpoints you need to authenticate first.
  To authenticate you need to send a POST request to http://localhost:8081/api/v1/auth/signin with the following body:
```
{
    "usernameOrEmail" : "your_username",
    "password" : "your_password"
}
```
  The response will be a JWT token that you need to use in the Authorization header of the requests you want to send.
    For example, to get all the posts you need to send a GET request to http://localhost:8081/api/v1/posts with the following header:
```
Authorization: Bearer <your token>
```
  You can use the application by sending HTTP requests to the endpoints.
  The endpoints are described in the Swagger UI.

### How to contribute
  If you want to contribute to this project,
  you can fork it and create a pull request.

### License
  Distributed under the MIT License. See LICENSE for more information.

### Contact
  You can contact me at: [email](mailto:orcohen3322@gmail.com)

### Acknowledgements
- [Spring Boot](https://spring.io/projects/spring-boot)
- [Spring Security](https://spring.io/projects/spring-security)
- [Spring Data JPA](https://spring.io/projects/spring-data-jpa)
- [JWT](https://jwt.io/)
- [Swagger](https://swagger.io/)

