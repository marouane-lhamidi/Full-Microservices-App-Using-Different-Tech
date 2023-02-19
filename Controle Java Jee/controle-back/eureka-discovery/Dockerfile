FROM openjdk:11
EXPOSE 8761
ADD target/eureka-control.jar eureka-control.jar
ENTRYPOINT ["java","-jar","/eureka-control.jar"]