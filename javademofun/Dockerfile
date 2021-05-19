FROM gradle:6.8.1-jdk11 as cache
RUN mkdir -p /home/gradle/cache_home
ENV GRADLE_USER_HOME /home/gradle/cache_home
WORKDIR /home/gradle/cache_home
COPY gradle.properties .
COPY build.gradle.kts .
RUN gradle clean build

FROM gradle:6.8.1-jdk11 as builder
COPY --from=cache /home/gradle/cache_home /home/gradle/.gradle
COPY . /usr/src/app/
WORKDIR /usr/src/app/
RUN gradle build -i
WORKDIR /usr/src/app/app/build/classes/kotlin/main/
RUN jar cvfe app.jar HelloWorldConsoleKt *.class
RUN cp app.jar /usr/src/app

FROM openjdk:11-jre-slim
USER root
WORKDIR /usr/src/app

COPY --from=builder /usr/src/app/*.jar ./app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]