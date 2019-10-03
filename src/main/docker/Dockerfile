FROM openjdk:11

VOLUME /tmp
ADD async-0.0.1-SNAPSHOT.jar app.jar
ENTRYPOINT ["sh", "-c", "java -Dspring.profiles.active=local -jar /app.jar"]