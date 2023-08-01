FROM openjdk:17-jdk-alpine

WORKDIR /app

COPY build/libs/ErtelApiAction-0.0.1-SNAPSHOT.jar ertelapiaction.jar

#RUN ["mkdir", "/app"]

CMD ["java", "-jar", "ertelapiaction.jar"]