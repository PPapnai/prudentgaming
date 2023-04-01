# 1x2network betting assignment

Create a Spring Boot Web Application that can insert data into a MySQL database from a JSON
file containing betting summary data. The application must also be able to search and filter the
data from the database and display it appropriately.

## Requirements

For building and running this application uses:

- Build Tool : [Maven 3.8.7](https://maven.apache.org/docs/3.8.7/release-notes.html)
- Programming Language : [Java 1.8](https://www.oracle.com/in/java/technologies/javase/javase8-archive-downloads.html)
- Spring Boot version : [Spring Boot 2.7.10](https://docs.spring.io/spring-boot/docs/2.7.10/)
- Packaging : [Jar](https://docs.oracle.com/javase/8/docs/technotes/guides/jar/jarGuide.html)


## Dependencies

- [Spring Web] (https://mvnrepository.com/artifact/org.springframework.boot/spring-boot-starter-web)
- [Spring Data JPA] (https://mvnrepository.com/artifact/org.springframework.boot/spring-boot-starter-data-jpa)
- [Spring for Apache Kafka] (https://mvnrepository.com/artifact/org.springframework.kafka/spring-kafka)
- [Thymeleaf] (https://mvnrepository.com/artifact/org.springframework.boot/spring-boot-starter-thymeleaf)
- [MySQL Driver] (https://mvnrepository.com/artifact/com.mysql/mysql-connector-j)
- [Lombok] (https://mvnrepository.com/artifact/org.projectlombok/lombok)
 
## Building the application locally

Go to your command prompt and execute the below command:

```shell
cd <project_root_directory>
mvnw clean install
```

This will build the jar file in the target folder.