FROM amazoncorretto:17.0.5-al2022-RC-headless
COPY target/srv-abertura-conta-0.0.1-SNAPSHOT.jar /tmp/srv-abertura-conta-0.0.1-SNAPSHOT.jar
EXPOSE 8087
ENV SPRING_PROFILES_ACTIVE=DEV
ENTRYPOINT ["java", "-jar", "/tmp/srv-abertura-conta-0.0.1-SNAPSHOT.jar"]