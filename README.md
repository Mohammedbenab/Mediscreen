# Mediscreen Application

# Introduction
This project is an application for a medical center. The client needs management tool for check patients health status.

# Setting up and launching project on your IDE (Eclipse or IntelliJ)
Who are requierments for this projet : <br/>
  ![Java](https://img.shields.io/badge/Java-1.8.x-red)
  ![Maven](https://img.shields.io/badge/Maven-6.0.x-green)
  ![NodeJS](https://img.shields.io/badge/NodeJS-18.3.0-green)
  ![Angular](https://img.shields.io/badge/Angular-15.1.2-red)
  ![MongoDB](https://img.shields.io/badge/MongoDB-6.0.x-green)
  ![MySQL](https://img.shields.io/badge/MySQL-6.0.x-blue)
  ![J-Unit](https://img.shields.io/badge/JUnit-5.0-orange)
  ![Docker](https://img.shields.io/badge/Docker-20.10.24-cyan)
  </br>

1 - Clone docker images on your desktop
> :warning: **Docker hub**: You need to login by your account!
 ```bash
  docker pull https://hub.docker.com/repositories/mohammedbenab
 ```
2 - Launch docker images
 ```bash
  docker run <name-file>
 ```
 
**Option** : </br>
1 - Clone project on your desktop
 ```bash
  git clone https://github.com/Mohammedbenab/Mediscreen.git
 ```
2 - Creat .jar files
 ```bash
  mvn clean install <name-service>
 ```
3 - Launch .jar files
 ```bash
  java -jar <name.jar> 
 ```
 # Endpoints Microservices and Swagger UI documentations
  Note-servie : http://localhost:8083/swagger-ui/index.html#
  Patient-servie : http://localhost:8081/swagger-ui/index.html#
  Risk-servie : http://localhost:8082/swagger-ui/index.html#
  Front-end : http://localhost:4200/swagger-ui/index.html#
