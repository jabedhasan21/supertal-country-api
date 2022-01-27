########## Build Stage ##########
FROM maven:3.8.4-openjdk-11-slim AS MAVEN_BUILD

ARG SPRING_ACTIVE_PROFILE

# Image Layer
WORKDIR /build
COPY pom.xml .
RUN mvn verify clean --fail-never

# Image Layer: With The Application
COPY src /build/src
RUN mvn -v
RUN mvn clean install -Dspring.profiles.active=$SPRING_ACTIVE_PROFILE && mvn package -B -e -Dspring.profiles.active=$SPRING_ACTIVE_PROFILE

# Final Application Image
FROM openjdk:18-ea-11-alpine3.14
WORKDIR /app

#### Config Stage ####
# Create a group and user
RUN addgroup -S appgroup && adduser -S devops -G appgroup

# Set ownership and permission
RUN chown -R devops:appgroup /app

##### Switch To User ####
# Tell docker that all future commands should run as the devops user
USER devops

######## Run/Package Stage ########
COPY --from=MAVEN_BUILD /build/target/country-api-1.0.0.jar /app/country-api.jar

ENTRYPOINT ["java", "-jar", "country-api.jar"]