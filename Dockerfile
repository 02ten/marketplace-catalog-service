FROM gradle:latest AS TEMP_BUILD
ENV DIR = /app
WORKDIR = $DIR
COPY . $DIR
USER root
RUN gradle bootJar

FROM amazoncorretto:21
ENV DIR =/app
ENV JAR_NAME = Catalog-service-0.0.1-SNAPSHOT.jar
WORKDIR = $DIR
COPY --from=TEMP_BUILD $DIR/build/libs/$JAR_NAME .
EXPOSE 8901
ENTRYPOINT java -jar $JAR_NAME