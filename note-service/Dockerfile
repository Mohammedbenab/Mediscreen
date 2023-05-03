FROM openjdk:8-jre
EXPOSE 8083
ADD target/note-service-0.0.1-SNAPSHOT.jar note-service.jar 
ENTRYPOINT ["java","-jar","/note-service.jar"]