FROM openjdk:11-jre
EXPOSE 8081
ADD target/patient-service-0.0.1-SNAPSHOT.jar patient-service.jar 
ENTRYPOINT ["java","-jar","/patient-service.jar"]