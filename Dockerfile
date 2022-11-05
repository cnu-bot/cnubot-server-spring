FROM openjdk:11 as builder

COPY . .

RUN chmod 755 ./gradlew && \
    ./gradlew bootjar

FROM openjdk:11 as runner

WORKDIR /app

COPY --from=builder ./build/libs/*.jar app.jar

EXPOSE 8087

ENTRYPOINT java -jar app.jar 
