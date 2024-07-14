<?php
// Database connection parameters
$hostAddr = "localhost";
$dbName = "meriahcandy";
$dbUser = "root";
$dbPwd = "";
$dbPort = 3306;

try {
    // Create a PDO instance
    $dbPDO = new PDO("mysql:host=$hostAddr;dbname=$dbName", $dbUser, $dbPwd);
    $dbPDO->setAttribute(PDO::ATTR_ERRMODE, PDO::ERRMODE_EXCEPTION);

    // Check if all required parameters are set
    if (isset($_GET['NameCust'], $_GET['Bento_Quantity'], $_GET['Gum_Quantity'], $_GET['Mom_Quantity'], $_GET['Hitto_Quantity'], $_GET['Total'])) {
        // Extract parameters from GET request (adjust as per your Java code)
        $NameCust = urldecode($_GET['NameCust']);
        $Bento_Quantity = $_GET['Bento_Quantity'];
        $Gum_Quantity = $_GET['Gum_Quantity'];
        $Mom_Quantity = $_GET['Mom_Quantity'];
        $Hitto_Quantity = $_GET['Hitto_Quantity'];
        $Total = $_GET['Total'];

        // Prepare SQL statement to insert into your_table_name table
        $stmt = $dbPDO->prepare("INSERT INTO pesanan (NameCust, Bento_Quantity, Gum_Quantity, Mom_Quantity, Hitto_Quantity, Total) VALUES (:NameCust, :Bento_Quantity, :Gum_Quantity, :Mom_Quantity, :Hitto_Quantity, :Total)");
        
        // Bind parameters
        $stmt->bindParam(':NameCust', $NameCust);
        $stmt->bindParam(':Bento_Quantity', $Bento_Quantity);
        $stmt->bindParam(':Gum_Quantity', $Gum_Quantity);
        $stmt->bindParam(':Mom_Quantity', $Mom_Quantity);
        $stmt->bindParam(':Hitto_Quantity', $Hitto_Quantity);
        $stmt->bindParam(':Total', $Total);

        // Execute the query
        if ($stmt->execute()) {
            echo json_encode(array("message" => "Data inserted successfully"));
        } else {
            echo json_encode(array("error" => "Failed to insert data"));
        }
    } else {
        echo json_encode(array("error" => "Missing parameters"));
    }

} catch (PDOException $e) {
    echo json_encode(array("error" => "Database error: " . $e->getMessage()));
}

// Close the connection (optional as it will be closed automatically when script ends)
$dbPDO = null;
?>