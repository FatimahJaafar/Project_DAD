package testproject;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import org.json.JSONArray;
import org.json.JSONObject;

public class OrderHistoryView extends JFrame {

    private JTable historyTable;
    private DefaultTableModel historyTableModel;
    private JLabel backgroundLabel;

    public OrderHistoryView() {
        setTitle("Order History");
        setBounds(100, 100, 734, 538);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        getContentPane().setLayout(null); // Using null layout for absolute positioning

        // Create a layered pane to control z-order of components
        JLayeredPane layeredPane = new JLayeredPane();
        layeredPane.setBounds(0, 0, 734, 538);
        getContentPane().add(layeredPane);

        // Add background image
        backgroundLabel = new JLabel();
        backgroundLabel.setIcon(new ImageIcon("C:\\xampp\\htdocs\\bgimage.jpg"));
        backgroundLabel.setBounds(0, 0, 734, 538); // Adjust the size to cover the entire window
        layeredPane.add(backgroundLabel, JLayeredPane.FRAME_CONTENT_LAYER);

        // Initialize table model with column headers
        historyTableModel = new DefaultTableModel(
            new Object[][]{},
            new String[]{"Customer Name", "Bento Quantity", "Gum Quantity", "Mom Quantity", "Hitto Quantity", "Total"}
        );

        // Create the JTable using the initialized tableModel
        historyTable = new JTable(historyTableModel);

        // Set table to not auto-resize columns
        historyTable.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);

        // Set preferred, minimum, and maximum column widths for each column
        historyTable.getColumnModel().getColumn(0).setPreferredWidth(150); // Customer Name
        historyTable.getColumnModel().getColumn(0).setMinWidth(100);
        historyTable.getColumnModel().getColumn(0).setMaxWidth(300);

        historyTable.getColumnModel().getColumn(1).setPreferredWidth(100); // Bento Quantity
        historyTable.getColumnModel().getColumn(1).setMinWidth(50);
        historyTable.getColumnModel().getColumn(1).setMaxWidth(150);

        historyTable.getColumnModel().getColumn(2).setPreferredWidth(100); // Gum Quantity
        historyTable.getColumnModel().getColumn(2).setMinWidth(50);
        historyTable.getColumnModel().getColumn(2).setMaxWidth(150);

        historyTable.getColumnModel().getColumn(3).setPreferredWidth(100); // Mom Quantity
        historyTable.getColumnModel().getColumn(3).setMinWidth(50);
        historyTable.getColumnModel().getColumn(3).setMaxWidth(150);

        historyTable.getColumnModel().getColumn(4).setPreferredWidth(100); // Hitto Quantity
        historyTable.getColumnModel().getColumn(4).setMinWidth(50);
        historyTable.getColumnModel().getColumn(4).setMaxWidth(150);

        historyTable.getColumnModel().getColumn(5).setPreferredWidth(100); // Total
        historyTable.getColumnModel().getColumn(5).setMinWidth(90);
        historyTable.getColumnModel().getColumn(5).setMaxWidth(200);

        // Add scroll pane
        JScrollPane scrollPane = new JScrollPane(historyTable);
        scrollPane.setBounds(33, 46, 638, 388); // Centering the table with some margin
        layeredPane.add(scrollPane, JLayeredPane.DEFAULT_LAYER);

        // Ensure background is behind the scroll pane
        layeredPane.moveToBack(backgroundLabel);

        // Fetch and display the order history
        fetchAndDisplayOrderHistory();
    }

    private void fetchAndDisplayOrderHistory() {
        try {
            String urlString = "http://localhost/view_orders.php";
            URL url = new URL(urlString);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");

            BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String inputLine;
            StringBuilder response = new StringBuilder();
            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();

            JSONArray jsonArray = new JSONArray(response.toString());
            historyTableModel.setRowCount(0);

            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObj = jsonArray.getJSONObject(i);
                historyTableModel.addRow(new Object[]{
                    jsonObj.getString("NameCust"),
                    jsonObj.getInt("Bento_Quantity"),
                    jsonObj.getInt("Gum_Quantity"),
                    jsonObj.getInt("Mom_Quantity"),
                    jsonObj.getInt("Hitto_Quantity"),
                    jsonObj.getDouble("Total")
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
