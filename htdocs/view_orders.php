<?php
header("Content-Type: application/json");

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

    // Fetch all orders from the pesanan table
    $stmt = $dbPDO->prepare("SELECT * FROM pesanan");
    $stmt->execute();

    // Fetch data as an associative array
    $orders = $stmt->fetchAll(PDO::FETCH_ASSOC);

    // Output data as JSON
    echo json_encode($orders);

} catch (PDOException $e) {
    echo json_encode(array("error" => "Database error: " . $e->getMessage()));
}

// Close the connection (optional as it will be closed automatically when script ends)
$dbPDO = null;
?>
