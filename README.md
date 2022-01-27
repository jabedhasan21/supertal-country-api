# Application Installation guides

### Database Seeder
* The initial list of users define in this  `src/main/resources/seed-data.json` file.

# Run this Application in Docker Container
###  Building  image locally
* We have to go to the codebase root directory and need to run those below command.
* #### Docker CMD
  * Network `country-api-network` declared as external in the docker-compose file.
  * So we have to create the network manually using this `docker network create country-api-network`  command.
* #### Docker Compose CMD
  * To build the image
    * `docker-compose --env-file .env build`
  * To run the image inside a container
    * `docker-compose --env-file .env up -d`
  * To stop the running containers
    * `docker-compose --env-file .env stop`
  * To remove stops containers
    * `docker-compose --env-file .env down`

+ ### To access the docker container Database from the docker host computer we have to use this configuration
  ```
  mongodb.host=localhost
  mongodb.port=27018
  mongodb.database=country_db
  mongodb.authentication-database=admin
  mongodb.username=root
  mongodb.password=root
  ```

# Run This Application Locally
### Prerequisites
* To run this application locally, we need those following dependency:
  * Apache Maven
    * Version >= 3.6.3
  * Java 11
    * This application is developed in the Spring Boot framework(version: 2.5.6).
  * MongoDB
    * We need to update this `src/main/resources/application.properties` file according to the local configuration.
### Run
* To run this Spring Boot app we need to build it first.
* To build and package a Spring Boot app into a single executable Jar file with a Maven, we have to use the below command.
* We need to run it from the project folder which contains the pom.xml file.
* This command will generate an application packaged as an executable jar file.
```
mvn clean install package
```

* To run the Spring Boot app from a command line in a Terminal window, We have to run the java -jar command.
```
java -jar target/country-api-1.0.0.jar
```

* We can also use the Maven plugin to run the app. We have to use the below command to run Spring Boot app with the Maven plugin :
```
mvn spring-boot:run
```

# API Documentation
* For the API documentation, there is a postman collection in project root directory named: `Supertal-Country-API.postman_collection.json`
* To see the API list we have to import the postman collection.