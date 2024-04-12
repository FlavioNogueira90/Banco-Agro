FROM amazoncorretto:17.0.5-al2022-RC-headless
COPY target/srv-cadastro-cliente-0.0.1-SNAPSHOT.jar /tmp/srv-cadastro-cliente-0.0.1-SNAPSHOT.jar
EXPOSE 8082
ENV SPRING_PROFILES_ACTIVE=DEV
ENTRYPOINT ["java", "-jar", "/tmp/srv-cadastro-cliente-0.0.1-SNAPSHOT.jar"]