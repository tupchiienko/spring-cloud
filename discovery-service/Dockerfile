FROM openjdk:8-jdk-alpine
VOLUME /tmp
COPY ./target/*.jar app.jar
EXPOSE 8761
ENTRYPOINT ["sh", "-c", "java -jar /app.jar"]