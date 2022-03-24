#
# Build Stage
#
FROM maven:3.8.4-openjdk-11-slim AS build
COPY src /home/app/src
COPY pom.xml /home/app
RUN mvn -f /home/app/pom.xml clean package

#
# Package stage
#
FROM openjdk:11-jdk
COPY --from=build /home/app/target/blogs-0.0.1-SNAPSHOT.jar /usr/local/lib/demo.jar
EXPOSE 1603
ENTRYPOINT ["java","-jar","/usr/local/lib/demo.jar"]