package testproject;

import java.awt.EventQueue;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.Font;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.URL;
import java.net.URLEncoder;

public class Owner {

    private JFrame frame;
    private JTable table;
    private DefaultTableModel tableModel;

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    Owner window = new Owner();
                    window.frame.setVisible(true);
                    new Thread(() -> {
                        try {
                            window.startServer();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }).start();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public Owner() {
        initialize();
    }

    private void initialize() {
        frame = new JFrame();
        frame.setBounds(100, 100, 734, 538); // Set initial size
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel contentPane = new JPanel();
        frame.setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel lblNewLabel = new JLabel("New Orders");
        lblNewLabel.setBounds(102, 11, 472, 25);
        lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 20));
        lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
        contentPane.add(lblNewLabel);

        // Initialize table model with column headers
        tableModel = new DefaultTableModel(
                new Object[][]{},
                new String[]{"Customer Name", "Bento Quantity", "Gum Quantity", "Mom Quantity", "Hitto Quantity", "Total"}
        );

        // Create the JTable using the initialized tableModel
        table = new JTable(tableModel);
         
        // Set preferred, minimum, and maximum column widths for each column
        table.getColumnModel().getColumn(0).setPreferredWidth(150); // Customer Name
        table.getColumnModel().getColumn(0).setMinWidth(100);
        table.getColumnModel().getColumn(0).setMaxWidth(300);

        table.getColumnModel().getColumn(1).setPreferredWidth(100); // Bento Quantity
        table.getColumnModel().getColumn(1).setMinWidth(50);
        table.getColumnModel().getColumn(1).setMaxWidth(150);

        table.getColumnModel().getColumn(2).setPreferredWidth(100); // Gum Quantity
        table.getColumnModel().getColumn(2).setMinWidth(50);
        table.getColumnModel().getColumn(2).setMaxWidth(150);

        table.getColumnModel().getColumn(3).setPreferredWidth(100); // Mom Quantity
        table.getColumnModel().getColumn(3).setMinWidth(50);
        table.getColumnModel().getColumn(3).setMaxWidth(150);

        table.getColumnModel().getColumn(4).setPreferredWidth(100); // Hitto Quantity
        table.getColumnModel().getColumn(4).setMinWidth(50);
        table.getColumnModel().getColumn(4).setMaxWidth(150);

        table.getColumnModel().getColumn(5).setPreferredWidth(100); // Total
        table.getColumnModel().getColumn(5).setMinWidth(90);
        table.getColumnModel().getColumn(5).setMaxWidth(200);

        // Add the table to a scroll pane
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(33, 46, 638, 388);
        contentPane.add(scrollPane);
        
        // Add "View History" button
        JButton btnViewHistory = new JButton("View History");
        btnViewHistory.setBounds(265, 444, 150, 30);
        btnViewHistory.addActionListener(e -> openOrderHistoryView());
        contentPane.add(btnViewHistory);
        
        JLabel lblNewLabel_1 = new JLabel("New label");
        lblNewLabel_1.setIcon(new ImageIcon("C:\\xampp\\htdocs\\bgimage.jpg"));
        lblNewLabel_1.setBounds(0, 0, 795, 515);
        contentPane.add(lblNewLabel_1);

        frame.setVisible(true);
    }

    private void startServer() throws Exception {
        ServerSocket serverSocket = new ServerSocket(88);
        while (true) {
            Socket socket = serverSocket.accept();
            BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            String orderData = reader.readLine();
            if (orderData != null) {
                String[] data = orderData.split(";");
                String name = data[0];
                int bentoQty = Integer.parseInt(data[1]);
                int gumQty = Integer.parseInt(data[2]);
                int momQty = Integer.parseInt(data[3]);
                int hittoQty = Integer.parseInt(data[4]);
                double total = Double.parseDouble(data[5]);
                saveOrderToDatabase(name, bentoQty, gumQty, momQty, hittoQty, total);
                updateTable(name, bentoQty, gumQty, momQty, hittoQty, total);
            }
            reader.close();
            socket.close();
        }
    }

    private void saveOrderToDatabase(String name, int bentoQty, int gumQty, int momQty, int hittoQty, double total) {
        try {
            String encodedNameCust = URLEncoder.encode(name, "UTF-8");
            String urlString = "http://localhost/orders.php"
                    + "?Bento_Quantity=" + bentoQty
                    + "&Gum_Quantity=" + gumQty
                    + "&Mom_Quantity=" + momQty
                    + "&Hitto_Quantity=" + hittoQty
                    + "&NameCust=" + encodedNameCust
                    + "&Total=" + total;
            URL url = new URL(urlString);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");

            // Read response if needed
            BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String inputLine;
            StringBuilder response = new StringBuilder();
            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void updateTable(String name, int bentoQty, int gumQty, int momQty, int hittoQty, double total) {
        tableModel.addRow(new Object[]{name, bentoQty, gumQty, momQty, hittoQty, total});
    }

    private void openOrderHistoryView() {
        OrderHistoryView orderHistoryView = new OrderHistoryView();
        orderHistoryView.setVisible(true);
    }
}
