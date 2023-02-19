FROM openjdk:11
EXPOSE 8083
ADD target/billings-control.jar billings-control.jar
ENTRYPOINT ["java","-jar","/billings-control.jar"]