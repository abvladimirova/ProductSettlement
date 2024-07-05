FROM openjdk:17.0.2 as final
LABEL maintainer="avladimirova@inno.tech"

ARG DPVERSION=1.0
ARG JAR_FILE=./target/ProductSettlement-${DPVERSION}.jar

WORKDIR /app

#ENV LANG='ru_RU.UTF-8' LANGUAGE='ru_RU.UTF-8' LC_ALL='ru_RU.UTF-8' JAVA_OPTIONS='-Dfile.encoding=UTF-8'
COPY ${JAR_FILE} /app/app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "/app/app.jar"]