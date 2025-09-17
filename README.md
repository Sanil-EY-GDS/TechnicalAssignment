# TechnicalAssignment
#  Order Management System with External Pricing API
 
##  Overview
This project is a **Spring Boot REST API** for managing customer orders.  
It integrates with an **external pricing service** to fetch product prices, multiplies them by the ordered quantity, and stores the order details in the database.  
 
---
 
##  Project Structure
```
src/main/java/com/example/technicalassignment/
│
├── controller/
│   └── demoController
    └── externalController
│
├── service/
│   ├── OrderService
│   └── external
│
├── model/dto
│   └── Order.java
    └── OrderREsponse
    └──inventoryResponse
    └── pricingResponse
│
├── repository/
│   └── OrderDB
```
 
---
 
##  Database Schema
 
### Table: `orders`
```sql
CREATE TABLE orders (
    id  PRIMARY KEY,
    cust_id VARCHAR(50) NOT NULL,
    qty INT NOT NULL,
    sku VARCHAR(50) NOT NULL,
    status VARCHAR(20)
    total_price DOUBLE ,
    
);
```
 
---
 
## 📡 API Endpoints
 
### 1. Create Order  
**POST** `/orders`
 
#### Request Body
```json
{
  "custId": "C123",
  "qty": 5
  "sku": "SSS"
 
}
```
 
#### Flow
1. Calls external API: `GET https://external/pricing/{sku}`  
2. Gets unit price from external API.  
3. Calculates `totalPrice = unitPrice × qty`.  
4. Saves order in DB with `status = CONFIRMED`.  
 
 
---
 
### 2. Get Order by ID  
**GET** `/orders/{id}`
 
#### Example Response
```json
[{
    "id": 18,
    "custID": "c129",
    "qty": 33,
    "sku": "SSS-BLACK",
    "status": "CONFIRMED",
    "totalPrice": 1001.0
}]
```
### 3. Create Order by CSV upload  
**POST** `/orders/batch-upload`
#### Request Body
```json
key:file
Type:file
value: select sample csv file
```
 
---
 
##  Configuration
 
- **Database:** PostgreSQL (update `application.properties` with your DB details).  
- **External Pricing API:**  
  - Expects a `GET` request at:  
    ```
    https://external/pricing/{sku}
    ```
  - Returns a `Double` (unit price).
    
- **External Inventory API:**
```
http://localhost:8080/external/inventory?sku={sku}&qty={qty}

``` 
 
---
 
## Tech Stack
- Java  
- Spring Boot   
- Spring Data JPA  
- Hibernate  
- PostgreSQL  
- RestTemplate (for external API calls)  
 
---
 
##  Run Instructions
1. Clone repo/install zip file in local
  
2. Update `application.properties` with DB credentials.  
3. Run the app:
   ```bash
   mvn spring-boot:run
   ```
4. Test with **Postman** or **curl**.
 
---
 

 
---
 
##  Note
- If the external pricing API is down or returns null, the order will fail with an error.  
- Validation ensures `custid`, `sku`, and `qty` are required.

