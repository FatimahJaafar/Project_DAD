# Project Name
Meriah Candy Shop

## Applications Involved
## Description
How many apps involved (anis)
There are 4 applications involved in this project.
### 1. Customer Application
- **Description**: 
Role: Enables customers to place candy orders.
Functionality: Provides a user interface for entering customer name and selecting quantities of various candies. It calculates the total order cost and sends the order details to the shop's owner application via a socket connection.

### 2. Owner Application
- **Description**:
•	Role: Receives and displays new customer orders for the shop owner.
•	Functionality: Listens for incoming orders from the Customer Application and displays them in a user-friendly format. It then sends these orders to the Backend Server Application for storage.

### 3. Order History View Application
- **Description**:
•	Role: Allows the shop owner to view a history of all placed orders.
•	Functionality: Connects to the Backend Server Application to fetch historical order data and displays it in a tabular format, making it easy to review past orders.

### 3. Backend Server Application
- **Description**:
•	Role: Stores and manages order data.
•	Functionality: Consists of PHP scripts and a MySQL database. It stores new order details received from the Owner Application and provides an interface for the Order History View Application to retrieve stored order data.

  ---

## Installation
Brief explanation each apps (aziah)

---

## Architecture/Layer Diagram
Architecture/Layer diagram for each of the apps including the middleware (sama2)

---

## Contributing URL Endpoints Middleware RESTful/SOAP/Socket Endpoints
List of URL end points middleware RESTful/SOAP/Socket (sama2)

- **RESTful Endpoints**:
  - `GET /api/resource`: Description of the endpoint.
  - `POST /api/resource`: Description of the endpoint. (kita guna ni)
  - `PUT /api/resource`: Description of the endpoint.
  - `DELETE /api/resource`: Description of the endpoint.
  
- **Socket Endpoints**:
  - `ws://example.com/socket`: Description of the endpoint.

---

### 3. Middleware (Aina)
- **Description**: Explanation of the middleware and its role in connecting the applications.
Functions/Features in the middleware
## Middleware Components

### Socket Communication
- **Customer Application**: 
  - Sends order data to the Owner application via a socket connection.
  - Data format: `NameCust;Bento_Quantity;Gum_Quantity;Mom_Quantity;Hitto_Quantity;Total`.
- **Owner Application**: 
  - Listens for incoming connections from the Customer application on port 88.
  - Receives, parses, and processes order data.

### HTTP Requests
- **Owner Application**:
  - Sends order data to the `orders.php` script to save the order in the database.
  - Constructs an HTTP GET request with order data as query parameters.
- **OrderHistoryView**:
  - Fetches order history data from the `view_orders.php` script.
  - Constructs an HTTP GET request and parses the JSON response.

### PHP Scripts
- **orders.php**:
  - Receives order data via HTTP GET requests.
  - Validates and inserts the order data into the `pesanan` table in the MySQL database.
  - Returns a success or error message in JSON format.
- **view_orders.php**:
  - Retrieves order history data from the `pesanan` table.
  - Returns the order data in JSON format.

### Middleware Flow
1. Customer places an order using the Customer application.
2. Order data is sent via socket to the Owner application.
3. Owner application receives the order data and sends it via HTTP request to `orders.php`.
4. `orders.php` validates and stores the order in the database.
5. Owner can view new orders in real-time, and past orders can be viewed via the OrderHistoryView.
6. OrderHistoryView fetches order history data from `view_orders.php` and displays it.






## Database
#### Database Name : meriahcandy
- **Description**: The table is designed to track customer orders, detailing what they ordered and the associated quantities and costs.
- **Table Name** : `pesanan`
- **SQL** :
  CREATE TABLE pesanan
(
    OrderID INT PRIMARY KEY,
    NameCust VARCHAR(55) NOT NULL,
    Bento_Quantity INT NOT NULL,
    Gum_Quantity INT NOT NULL,
    Mom_Quantity INT NOT NULL,
    Hitto_Quantity INT NOT NULL,
    Total DOUBLE NOT NULL
);
- **Picture of the table**
![Screenshot 2024-06-24 180315](https://github.com/FatimahJaafar/Project_DAD/assets/163825344/41b7c4fc-0ce3-4175-a6d3-35aa5eb97437)
- **Description of SQL**: 
  - OrderID: An integer column serving as the primary key, uniquely identifying each order.
  - NameCust: A VARCHAR column (string) of maximum length 55, storing the customer's name.
  - Bento_Quantity, Gum_Quantity, Mom_Quantity, Hitto_Quantity: These columns represent the quantities ordered for different items.
  - Total: A DOUBLE column, presumably storing the total cost or price of the order.

## Contact

For more information, please contact:

- **Anis**: [B032210177@student.utem.edu.my]
- **Aziah**: [email@example.com]
- **Aina**: [B032210185@student.utem.edu.my]
- **Fatimah**: [B032210140@student.edu.utem.my]
