FROM eclipse-temurin:21-jdk-jammy
WORKDIR /app

ENV TZ=America/Sao_Paulo \
  LANG=pt_BR.UTF-8 \
  LANGUAGE=pt_BR.UTF-8 \
  LC_ALL=pt_BR.UTF-8

COPY target/*.jar app.jar

ENV JAVA_OPTIONS="-Xms256m -Xmx512m"
ENTRYPOINT exec java $JAVA_OPTIONS -XshowSettings:vm -jar /app/app.jar
