FROM maven:3.8.6 as build
COPY src /usr/src/app/src
COPY pom.xml /usr/src/app
RUN mvn -f /usr/src/app/pom.xml clean package -DskipTests

FROM openjdk:17
COPY --from=build /usr/src/app/target/graduate-system-0.0.1-SNAPSHOT.jar /usr/app/graduate-system-0.0.1-SNAPSHOT.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","/usr/app/graduate-system-0.0.1-SNAPSHOT.jar"]
