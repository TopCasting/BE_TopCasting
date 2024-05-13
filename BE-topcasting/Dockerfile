FROM gradle:7.6.4-jdk17 AS build

WORKDIR /app

COPY . /app

RUN gradle clean -x test build --no-daemon

FROM openjdk:17-slim

WORKDIR /app

COPY --from=build /app/build/libs/*.jar /app/tc.jar

EXPOSE 8080
ENTRYPOINT ["java"]
CMD ["-jar","tc.jar"]
