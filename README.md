## Description
How many apps involved (anis)

## Installation
Brief explanation each apps (aziah)

## Usage
Architecture/Layer diagram for each of the apps including the middleware (sama2)

## Contributing
List of URL end points middleware RESTful/SOAP/Socket (sama2)

## License
Functions/Features in the middleware (aina)

## Contact
The database and tables involve in the projects (timh)




# Project Name
Meriah Candy Shop

## Applications Involved

### 1. Application A (Anis)
- **Description**: A brief explanation of what Application A does.

### 2. Application B (Aziah)
- **Description**: A brief explanation of what Application B does.

### 3. Middleware (Aina)
- **Description**: Explanation of the middleware and its role in connecting the applications.

---

## Architecture/Layer Diagram

### Application A

### Application B
### Midldleware



---

## URL Endpoints

### Middleware RESTful/SOAP/Socket Endpoints
Here is a list of URL endpoints for the middleware, supporting different communication protocols:

- **RESTful Endpoints**:
  - `GET /api/resource`: Description of the endpoint.
  - `POST /api/resource`: Description of the endpoint.
  - `PUT /api/resource`: Description of the endpoint.
  - `DELETE /api/resource`: Description of the endpoint.

- **SOAP Endpoints**:
  - `POST /soap/resource`: Description of the endpoint.
  
- **Socket Endpoints**:
  - `ws://example.com/socket`: Description of the endpoint.

---

## Middleware Functions/Features

The middleware performs several key functions to facilitate communication and data processing between the applications:

- **Authentication**: Handles user authentication and authorization.
- **Data Transformation**: Transforms data between different formats required by the applications.
- **Logging**: Logs important events and errors for monitoring and debugging.
- **Caching**: Improves performance by caching frequent requests.
- **Error Handling**: Manages errors and provides appropriate responses.

---

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

- **Anis**: [email@example.com]
- **Aziah**: [email@example.com]
- **Aina**: [email@example.com]
- **Fatimah**: [B032210140@student.edu.utem.my]
