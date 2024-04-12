FROM amazoncorretto:17.0.5-al2022-RC-headless
COPY target/srv-conta-corrente-0.0.1-SNAPSHOT.jar /tmp/srv-conta-corrente-0.0.1-SNAPSHOT.jar
EXPOSE 8081
ENV SPRING_PROFILES_ACTIVE=DEV
ENTRYPOINT ["java", "-jar", "/tmp/srv-conta-corrente-0.0.1-SNAPSHOT.jar"]