# Spring-boot blog backend project
<p>This project is a service for a blog application.</p>
<p>It is a spring-boot project with a REST API.</p>
<p>It uses a MySQL database.</p>
<p>It uses JWT for authentication.</p>
<p>It uses Spring Security for authorization.</p>
<p>It uses Spring Data JPA for data access.</p>

[//]: # (Table of Contents)
<details>
    <summary>Table of Contents</summary>
    <ol>
        <li>
            <a href="#about-the-project">About the project</a>
            <ul>
                <li><a href="#prerequisites">Prerequisites</a></li>
                <li><a href="#installation">Installation</a></li>
            </ul>
        </li>
    </ol>
</details>


## About the project
This project was developed as a part of a blog application. It is a REST API that provides the following features:
- User registration
- User login
- User logout
- User profile update

## Pre-requisites
- Java 11
- Maven
- MySQL


## Technologies
- Java 8
- Spring Boot
- Spring Security
- Spring Data JPA
- MySQL
- JWT
- Maven
- Lombok
- Swagger

## Database configuration
The database configuration is located in the application.properties file. The default configuration is:
```
spring.datasource.url=jdbc:mysql://localhost:3306/blog?useSSL=false&serverTimezone=UTC
spring.datasource.username=root
spring.datasource.password=root
```
To change the database configuration, you can either change the values in the application.properties file or you can set the following environment variables:
```
SPRING_DATASOURCE_URL
SPRING_DATASOURCE_USERNAME
SPRING_DATASOURCE_PASSWORD
```



### How to run
This application is packaged as a war which has Tomcat 8 embedded. No Tomcat or JBoss installation is necessary.
You run it using the `java -jar` command.

First, you need to build the project using maven:
```
mvn clean install
```
Then, you can run the application:
```
java -jar target/blog-0.0.1-SNAPSHOT.jar
```
The application will start running at http://localhost:8081.

### How to test
You can test the application using Swagger UI. The Swagger UI is available at http://localhost:8081/swagger-ui.html.

### How to use
You can use the application by sending HTTP requests to the endpoints. The endpoints are described in the Swagger UI.

### How to contribute
If you want to contribute to this project, you can fork it and create a pull request.

### License
Distributed under the MIT License. See LICENSE for more information.

### Contact
You can contact me at: [email](mailto:orcohen332@gmail.com)

### Acknowledgements
- [Spring Boot](https://spring.io/projects/spring-boot)
- [Spring Security](https://spring.io/projects/spring-security)
- [Spring Data JPA](https://spring.io/projects/spring-data-jpa)
- [JWT](https://jwt.io/)
- [Swagger](https://swagger.io/)

