FROM openjdk:11
EXPOSE 8888
ADD target/gateway-control.jar gateway-control.jar
ENTRYPOINT ["java","-jar","/gateway-control.jar"]