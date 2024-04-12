FROM amazoncorretto:17.0.5-al2022-RC-headless
COPY target/eureka-server-0.0.1-SNAPSHOT.jar /tmp/eureka-server-0.0.1-SNAPSHOT.jar
EXPOSE 8761
ENTRYPOINT ["java", "-jar", "/tmp/eureka-server-0.0.1-SNAPSHOT.jar"]