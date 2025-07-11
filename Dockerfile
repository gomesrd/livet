FROM ubuntu:latest AS BUILD

RUN apt-get update
RUN apt-get install openjdk-21-jdk -y
COPY . .

RUN apt-get install maven -y
RUN mvn clean install -DskipTests

FROM openjdk:21-jdk-slim

EXPOSE 8080

ENV TZ=America/Sao_Paulo \
  LANG=pt_BR.UTF-8 \
  LANGUAGE=pt_BR.UTF-8 \
  LC_ALL=pt_BR.UTF-8

COPY --from=BUILD /target/*.jar app.jar

ENTRYPOINT ["java", "-jar", "app.jar"]
