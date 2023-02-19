FROM openjdk:11.0.15
EXPOSE 8084
ADD target/products-control.jar products-control.jar
ENTRYPOINT ["java","-jar","/products-control.jar"]