# Project Name
Distributed Meriah Candy Shop system
-----
## 1. Applications Involved
## Description
There are 4 applications involved in this project.
### 1. Customer Application
- Role: Enables customers to place candy orders.
- Functionality: Provides a user interface for entering customer name and selecting quantities of various candies. It calculates the total order cost and sends the order details to the shop's owner application via a socket connection.

### 2. Owner Application
- Role: Receives and displays new customer orders for the shop owner.
- Functionality: Listens for incoming orders from the Customer Application and displays them in a user-friendly format. It then sends these orders to the Backend Server Application for storage.

### 3. Order History View Application
- Role: Allows the shop owner to view a history of all placed orders.
- Functionality: Connects to the Backend Server Application to fetch historical order data and displays it in a tabular format, making it easy to review past orders.

### 3. Backend Server Application
- Role: Stores and manages order data.
- Functionality: Consists of PHP scripts and a MySQL database. It stores new order details received from the Owner Application and provides an interface for the Order History View Application to retrieve stored order data.

  ---

## 2. Installation
Prerequisites
- Ensure you have Eclipse IDE installed.

Customer or owner Application
Import the project into Eclipse:
- Open Eclipse.
- Go to File > Import > General > Existing Projects into Workspace.
- Select the Customer or owner Application project directory.
- Click Finish.

Run the application:
- Right-click the project in the Project Explorer.
- Select Run As > Java Application.

Backend Server Application
- Set up the MySQL database:
- Create a new database and user.
- Import the provided SQL schema.
- Configure PHP scripts with database credentials.

---

## 3. Architecture/Layer Diagram
Architecture/Layer diagram for each of the apps including the middleware
1. Customer Application:
- Presentation Layer: User interface for entering customer information and candy quantities.
- Business Logic Layer: Validates user input, calculates total cost, and sends order data to the middleware layer via sockets.

2. Owner Application:
- Presentation Layer: User interface for displaying new orders received from customers.
- Business Logic Layer: Receives order data from the middleware layer, processes it and sends it to the backend server for storage using HTTP requests.

3. Order History View Application:
- Presentation Layer: User interface for displaying historical order data in a tabular format.
- Business Logic Layer: Connects to the backend server using HTTP requests to retrieve order history data and populates the table in the UI.

4. Backend Server Application:
This application resides within the middleware layer.
- Business Logic Layer (PHP scripts): Handles incoming HTTP requests for saving new orders and retrieves order history data. It interacts with the MySQL database to perform these operations.
- Data Layer: The MySQL database server stores all order data.

Middleware Layer:
- Socket Communication: Enables communication between the Customer and Owner applications for transmitting order details.
- HTTP Requests: Facilitates communication with the backend server for saving new orders and retrieving order history.
- 
---

## 4. Contributing URL Endpoints Middleware RESTful/Socket 
- **RESTful Endpoints**:
 ### Save Order to Database

- **URL:** `http://localhost/orders.php`
- **Method:** `GET`
- **Parameters:**
  - `NameCust` - Customer name
  - `Bento_Quantity` - Quantity of Bento Milk Candy
  - `Gum_Quantity` - Quantity of Super 88 Bubble Gum
  - `Mom_Quantity` - Quantity of Mommom 40’s Eyeglass Candy
  - `Hitto_Quantity` - Quantity of Hitto Chewy Candy
  - `Total` - Total amount

### View Order History

- **URL:** `http://localhost/view_orders.php`
- **Method:** `GET`

- **Socket Endpoints**:

### Customer to Owner

- **Socket Connection:** `Socket socket = new Socket("25.6.15.15", 88);`
  - This socket connection is used by the Customer application to send order details to the Owner application.
  - **Socket Endpoint:** `25.6.15.15:88`

### Owner Socket Server

- **ServerSocket:** `ServerSocket serverSocket = new ServerSocket(88);`
  - This socket server listens for incoming connections from the Customer application to receive order details.
  - **Socket Endpoint:** `localhost:88`

---

## 5. Middleware 
- **Description**: Explanation of the middleware and its role in connecting the applications.
Functions/Features in the middleware
### Middleware Components
### 1. Socket Communication

#### Customer to Owner Communication
The Customer application sends order details to the Owner application via socket communication.

**Implementation:**
- **Customer Application:**
  - A socket connection is established to the server (Owner) at IP address `25.6.15.15` on port `88`.
  - The order data, including customer name and candy quantities, is sent through this socket connection.
- **Owner Server:**
  - The Owner application runs a server socket to receive orders from customers.
  - A server socket listens on port `88` for incoming connections.
  - Upon receiving an order, the server processes the data and updates the order table in the Owner application's GUI.

### 2. HTTP Requests

#### Saving Orders to Database
The middleware facilitates saving order details to a MySQL database through HTTP GET requests.

**Implementation:**
- **Owner Application:**
  - Upon receiving an order, an HTTP GET request is sent to a PHP script (`orders.php`) hosted on the server.
  - The PHP script processes the request and inserts the order data into the database.

- **PHP Script (orders.php):**
  - Connects to the MySQL database.
  - Inserts the order details into the `pesanan` table.
  - Returns a JSON response indicating success or failure.

#### Order History View
The Owner application includes a feature to view the order history.

**Implementation:**
- A separate GUI window (`OrderHistoryView`) is provided to display the order history.
- Fetches order data from the database via an HTTP GET request to another PHP script (`view_orders.php`).

- **PHP Script (view_orders.php):**
  - Retrieves all orders from the `pesanan` table.
  - Returns the order data in JSON format.
  - The `OrderHistoryView` parses this JSON data and updates the table to display all past orders.

### 3. Data Validation

#### Input Validation in Customer Application
Ensures that all user inputs are valid before processing the order.

**Features:**
- Checks if at least one candy quantity is greater than zero.
- Ensures the quantity for each candy type is less than 10.
- Displays appropriate error messages for invalid inputs.

### 4. Error Handling

#### Error Handling
Both applications include error handling mechanisms to manage issues like invalid input, socket communication errors, and database connection failures.

**Customer Application:**
- Displays error messages for invalid inputs and socket errors.

**Owner Application:**
- Handles exceptions during socket communication and HTTP requests, displaying appropriate error messages.

### Middleware Flow
1. Customer places an order using the Customer application.
2. Order data is sent via socket to the Owner application.
3. Owner application receives the order data and sends it via HTTP request to `orders.php`.
4. `orders.php` validates and stores the order in the database.
5. Owner can view new orders in real-time, and past orders can be viewed via the OrderHistoryView.
6. OrderHistoryView fetches order history data from `view_orders.php` and displays it.

---

## 6. Database
### Database Name : meriahcandy
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
## VIDEO PRESENTATION : 

## Contact

For more information, please contact:

- **Anis**: [B032210177@student.utem.edu.my]
- **Aziah**: [email@example.com]
- **Aina**: [B032210185@student.utem.edu.my]
- **Fatimah**: [B032210140@student.edu.utem.my]
