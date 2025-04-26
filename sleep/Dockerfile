FROM eclipse-temurin:21-jdk-jammy

RUN apt-get update && apt-get install -y \
    findutils \
    curl \
    unzip \
    && rm -rf /var/lib/apt/lists/*

WORKDIR /app

COPY build.gradle gradlew settings.gradle ./
COPY gradle/ gradle/

RUN chmod +x gradlew
RUN ./gradlew wrapper

COPY src/ src

RUN ./gradlew build --no-daemon -x test

ENTRYPOINT ["java","-jar","build/libs/sleep-0.0.1-SNAPSHOT.jar"]
