FROM amazoncorretto:17.0.5-al2022-RC-headless
COPY target/apigateway-0.0.1-SNAPSHOT.jar /tmp/apigateway-0.0.1-SNAPSHOT.jar
EXPOSE 8080
ENV SPRING_PROFILES_ACTIVE=DEV
ENTRYPOINT ["java", "-jar", "/tmp/apigateway-0.0.1-SNAPSHOT.jar"]