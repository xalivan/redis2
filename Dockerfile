FROM openjdk:11
COPY target/redis-0.0.1-SNAPSHOT.jar redis.jar
ENTRYPOINT ["java","-jar","redis.jar"]
