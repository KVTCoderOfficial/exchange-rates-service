FROM openjdk:15
COPY build/libs/exchange-rates-service-0.0.1-SNAPSHOT.jar exchange-rates-service.jar
ENTRYPOINT java -jar exchange-rates-service.jar
EXPOSE 8189