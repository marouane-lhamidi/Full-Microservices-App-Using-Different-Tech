# **Controle-Jee**

## **Description**  

>### Objectif :  
>Creation of an application based where we will apply different technologies and outils like Kafka Keycloak Docker ....,  based on a micro-service architecture that allows to manage invoices containing products and belonging to a customer and in the front side we will use Angular.

### **Steps :**

1. Architecture Of The Project.

2. Creation of customer-service micro-service which allows to manage customers.

3. Creation of customer-service micro-service which allows to manage products.

4. Creating the Spring cloud Gateway with a Static Routing System Configuration.

5. Creation of the Eureka Discovery Service directory.

6. The dynamic configuration of gateway routes.

7. Setting up the Kafka environment (Docker):
    -Running Zookeper and Kafka.
    -Testing with Kafka-console-producer and kafka-console-consumer.

8. Creation of Billing-Service billing service using Open Feign and enclouding kafka consumer .

9. Creation of billing-supplier-server to produce billings .

10. creation of analyse-server to analyse the produced billings .

11. Deploy keycloak server :
     - Create a Realm.
     - Create a client to secure.
     - Creation des utilisateurs.
     - Creation of users.
     - Assign roles to users.

12. Testing With Postman.

13. Securing Microservices and the Angular Frontend by Deploying Keycloak Adapters.

14. Dockerizing Our apps.
     - Dockerizing apps.
     - Docker Hub (**The Images Are Available In Docker Hub**) .
     - Docker-compose.
     

15. Creation of an Angular web client (Clients, Products, Inventory, Dashbord).


### **Realization :**
1. Architecture Of The Project.
     - Arch Demo.
![Arch Demonstration](/assets/Arch.png)


2. Creation of customer-service micro-service which allows to manage customers.
     - Code Demo.
![Code Demonstration](/assets/customer4.png)
     - Swagger Demo.
![Swagger Demonstration](/assets/customer5.png)

3. Creation of customer-service micro-service which allows to manage products.
     - Code Demo.
![Code Demonstration](/assets/Product4.png)
     - Swagger Demo.
![Swagger Demonstration](/assets/Product5.png)

4. Creating the Spring cloud Gateway with a Static Routing System Configuration.
     - Code Demo.
![Code Demonstration](/assets/GetwayStatic1.png)

5. Creation of the Eureka Discovery Service directory.
     - Code Demo.
![Code Demonstration](/assets/Eureka2.png)

6. The dynamic configuration of gateway routes.
     - Code Demo.
![Code Demonstration](/assets/GetwayDynamic1.png)

7. Setting up the Kafka environment (Docker):

    -Running Zookeper and Kafka.
![Code Demonstration](/assets/dockerDowload.png)
    -Testing with Kafka-console-producer and kafka-console-consumer.
![Code Demonstration](/assets/ConsumerProducer.png)

8. Creation of Billing-Service billing service using Open Feign and enclouding kafka consumer .

     - Code Demo.
![Code Demonstration](/assets/Bill4.png)

     - Code Fiegn Demo.
![Code Demonstration](/assets/Bill5.png)

     - Code Consumer Demo.
![Code Demonstration](/assets/Bill6.png)

     - Swagger Demo.
![Swagger Demonstration](/assets/Bill10.png)

9. Creation of billing-supplier-server to produce billings .
     - Code Supplier Demo.
![Code Demonstration](/assets/BillingSupplier.png)

10. creation of analyse-server to analyse the produced billings .
     - Code Supplier Demo.
![Code Demonstration](/assets/AnalyseService.png)

11. Deploy keycloak server :
     - Create a Realm.
![Code Demonstration](/assets/keycloakRealm.png)

     - Create a client to secure.
![Code Demonstration](/assets/KeycloakClient.png)

     - Creation des utilisateurs.
![Code Demonstration](/assets/keycloakUsers.png)

     - Creation of users.
![Code Demonstration](/assets/keycloakRealm.png)

     - Assign roles to users and setting password.
![Code Demonstration](/assets/RolesPassword.png)

12. Testing With Postman.

    - Authentication with password.
![Code Demonstration](/assets/KeycloakPassword.png)
    
    - Analysis of the contents of the two JWTs AccessToken and Refresh Token.
![Code Demonstration](/assets/token.png)

    - Authentication with the Refresh Token.
![Code Demonstration](/assets/KeycloakRefreshToken.png)

    - Authentication with Client ID and Client Secret.
![Code Demonstration](/assets/KeycloakSecretKey.png)

    - Changing Access Token and Refresh Token Token Settings.
![Code Demonstration](/assets/tokenExpiration.png)

13. Securing Microservices and the Angular Frontend by Deploying Keycloak Adapters.
     - Billing Service.
![Code Demonstration](/assets/billingService.png)
     - Customer Service.
![Code Demonstration](/assets/CustomerService.png)
     - Product Service.
![Code Demonstration](/assets/ProductService.png)
     - Front-End.
![Code Demonstration](/assets/fronKeycloak.png)

14. Dockerizing Our apps.
     - Dockerizing apps.
![Code Demonstration](/assets/DockerImages.png)
     - Docker-compose.
![Code Demonstration](/assets/DockerCompose.png)


15. Creation of an Angular web client (Clients, Products, Inventorym, Dashbord)
     - Dashboad
          - Dashboad
![Code Demonstration](/assets/Dashboad.png)
     - Clients
          - List of Clients
![Code Demonstration](/assets/CustomerList.png)
          - Edit Client
![Code Demonstration](/assets/CustomerEdit.png)
          - New Client
![Code Demonstration](/assets/CustomerNew.png)
     - Products
          - List of Products
![Code Demonstration](/assets/ProductList.png)
          - Edit Product
![Code Demonstration](/assets/ProductEdit.png)
          - New Product
![Code Demonstration](/assets/ProductNew.png)
     - Inventories
          - List of Inventories
![Code Demonstration](/assets/BillListt.png)
          - Inventory Information
![Code Demonstration](/assets/BillInformations.png)

