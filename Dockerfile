FROM openjdk:8-jdk-alpine
COPY target/autoriza-control-0.0.1-*.jar app.jar
ENTRYPOINT ["java", "-Djava.security.egd=file:/dev/./urandom", "-jar", "/app.jar"]
