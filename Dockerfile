FROM openjdk:oracle
COPY target/carwash-0.0.1.jar carwash/carwash-0.0.1.jar
EXPOSE 8080
CMD ["java", "-jar", "carwash/carwash-0.0.1.jar"]