FROM openjdk:21
ENV PORT 8084
EXPOSE 8084
ADD /target/PaymentService-0.0.1-SNAPSHOT.jar payment-service.jar
ENTRYPOINT ["java", "-jar", "payment-service.jar"]