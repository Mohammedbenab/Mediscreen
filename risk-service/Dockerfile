FROM openjdk:11-jre
EXPOSE 8082
ADD target/risk-service-0.0.1-SNAPSHOT.jar risk-service.jar 
ENTRYPOINT ["java","-jar","/risk-service.jar"]