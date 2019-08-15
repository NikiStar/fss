FROM java:8-jdk-alpine
COPY ./target/flight-status-service-0.0.1.jar /usr/app/
WORKDIR /usr/app
RUN sh -c 'touch flight-status-service-0.0.1.jar'
ENTRYPOINT ["java","-jar","flight-status-service-0.0.1.jar"]