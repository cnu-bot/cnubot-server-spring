FROM openjdk:11 as builder

COPY . .

RUN chmod 755 ./gradlew && \
    ./gradlew clean build -x test

FROM openjdk:11 as runner

WORKDIR /app

ARG CNUBOT_KEY
ENV CNUBOT_KEY ${CNUBOT_KEY}

COPY --from=builder ./build/libs/*.jar app.jar

EXPOSE 8087

ENTRYPOINT java -jar -Djasypt.encryptor.password=${CNUBOT_KEY} app.jar 
