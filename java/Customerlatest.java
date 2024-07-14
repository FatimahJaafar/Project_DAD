import java.awt.EventQueue;
import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;

public class Customer {

    private JFrame frame;
    private JTextField txtName;
    private JTextField txtBento;
    private JTextField txtGum;
    private JTextField txtMom;
    private JTextField txtHitto;
    double bentoPrice = 4.50;
    double gumPrice = 0.50;
    double momPrice = 8.50;
    double hittoPrice = 12.50;
    double Total;
    int Bento_Quantity;
    int Gum_Quantity;
    int Mom_Quantity;
    int Hitto_Quantity;
    String NameCust;

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    Customer window = new Customer();
                    window.frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public Customer() {
        initialize();
    }

    private void initialize() {
        frame = new JFrame();
        frame.setBounds(100, 100, 734, 538);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(null);

        JLabel lblTitle = new JLabel("MERIAH CANDY SHOP");
        lblTitle.setBounds(203, 10, 352, 54);
        lblTitle.setFont(new Font("Tahoma", Font.PLAIN, 35));
        frame.getContentPane().add(lblTitle);

        JLabel lblPickupOnly = new JLabel("Pickup only !");
        lblPickupOnly.setBounds(287, 60, 173, 36);
        lblPickupOnly.setFont(new Font("Tahoma", Font.PLAIN, 30));
        frame.getContentPane().add(lblPickupOnly);

        JLabel lblEnterYourName = new JLabel("Enter Your Name :");
        lblEnterYourName.setBounds(62, 106, 173, 31);
        lblEnterYourName.setFont(new Font("Tahoma", Font.PLAIN, 20));
        frame.getContentPane().add(lblEnterYourName);

        txtName = new JTextField();
        txtName.setBounds(245, 106, 338, 31);
        txtName.setFont(new Font("Tahoma", Font.PLAIN, 20));
        frame.getContentPane().add(txtName);
        txtName.setColumns(10);

        JLabel lblCandy = new JLabel("Candy");
        lblCandy.setBounds(116, 151, 54, 31);
        lblCandy.setFont(new Font("Tahoma", Font.PLAIN, 20));
        frame.getContentPane().add(lblCandy);

        JLabel lblPrice = new JLabel("Price");
        lblPrice.setBounds(457, 151, 54, 31);
        lblPrice.setFont(new Font("Tahoma", Font.PLAIN, 20));
        frame.getContentPane().add(lblPrice);

        JLabel lblQuantity = new JLabel("Quantity");
        lblQuantity.setBounds(589, 151, 86, 31);
        lblQuantity.setFont(new Font("Tahoma", Font.PLAIN, 20));
        frame.getContentPane().add(lblQuantity);

        JLabel lblBentoMilkCandy = new JLabel("Bento Milk Candy (25 pieces)");
        lblBentoMilkCandy.setBounds(23, 192, 268, 31);
        lblBentoMilkCandy.setFont(new Font("Tahoma", Font.PLAIN, 20));
        frame.getContentPane().add(lblBentoMilkCandy);

        JLabel lblSuperBubble = new JLabel("Super 88 Bubble Gum (3 pieces)");
        lblSuperBubble.setBounds(23, 233, 290, 31);
        lblSuperBubble.setFont(new Font("Tahoma", Font.PLAIN, 20));
        frame.getContentPane().add(lblSuperBubble);

        JLabel lblMommomsEyeglass = new JLabel("Mommom 40’S Eyeglass Candy (40 pieces)");
        lblMommomsEyeglass.setBounds(23, 274, 388, 31);
        lblMommomsEyeglass.setFont(new Font("Tahoma", Font.PLAIN, 20));
        frame.getContentPane().add(lblMommomsEyeglass);

        JLabel lblHittoChewyCandy = new JLabel("Hitto Chewy Candy (100 pieces)");
        lblHittoChewyCandy.setBounds(23, 320, 290, 31);
        lblHittoChewyCandy.setFont(new Font("Tahoma", Font.PLAIN, 20));
        frame.getContentPane().add(lblHittoChewyCandy);

        JLabel lblPrice_1 = new JLabel("4.50");
        lblPrice_1.setBounds(457, 192, 54, 31);
        lblPrice_1.setFont(new Font("Tahoma", Font.PLAIN, 20));
        frame.getContentPane().add(lblPrice_1);

        JLabel lblPrice_2 = new JLabel("0.50");
        lblPrice_2.setBounds(457, 233, 54, 31);
        lblPrice_2.setFont(new Font("Tahoma", Font.PLAIN, 20));
        frame.getContentPane().add(lblPrice_2);

        JLabel lblPrice_3 = new JLabel("8.50");
        lblPrice_3.setBounds(457, 274, 54, 31);
        lblPrice_3.setFont(new Font("Tahoma", Font.PLAIN, 20));
        frame.getContentPane().add(lblPrice_3);

        JLabel lblPrice_4 = new JLabel("12.50");
        lblPrice_4.setBounds(457, 320, 54, 31);
        lblPrice_4.setFont(new Font("Tahoma", Font.PLAIN, 20));
        frame.getContentPane().add(lblPrice_4);

        txtBento = new JTextField();
        txtBento.setBounds(575, 192, 100, 31);
        txtBento.setFont(new Font("Tahoma", Font.PLAIN, 20));
        txtBento.setColumns(10);
        frame.getContentPane().add(txtBento);

        txtGum = new JTextField();
        txtGum.setBounds(575, 233, 100, 31);
        txtGum.setFont(new Font("Tahoma", Font.PLAIN, 20));
        txtGum.setColumns(10);
        frame.getContentPane().add(txtGum);

        txtMom = new JTextField();
        txtMom.setBounds(575, 274, 100, 31);
        txtMom.setFont(new Font("Tahoma", Font.PLAIN, 20));
        txtMom.setColumns(10);
        frame.getContentPane().add(txtMom);

        txtHitto = new JTextField();
        txtHitto.setBounds(575, 320, 100, 31);
        txtHitto.setFont(new Font("Tahoma", Font.PLAIN, 20));
        txtHitto.setColumns(10);
        frame.getContentPane().add(txtHitto);

        JLabel lblTotal = new JLabel("Total : RM");
        lblTotal.setBounds(474, 394, 98, 31);
        lblTotal.setHorizontalAlignment(SwingConstants.LEFT);
        lblTotal.setFont(new Font("Tahoma", Font.PLAIN, 20));
        frame.getContentPane().add(lblTotal);

        JLabel lblLimits = new JLabel("Limits = 10");
        lblLimits.setBounds(575, 358, 108, 31);
        lblLimits.setHorizontalAlignment(SwingConstants.LEFT);
        lblLimits.setFont(new Font("Tahoma", Font.PLAIN, 20));
        frame.getContentPane().add(lblLimits);

        JTextArea textAreaTotal = new JTextArea();
        textAreaTotal.setBounds(575, 395, 100, 36);
        textAreaTotal.setFont(new Font("Tahoma", Font.PLAIN, 20));
        frame.getContentPane().add(textAreaTotal);

        JButton btnProceed = new JButton("Proceed");
        btnProceed.setEnabled(false);
        btnProceed.setBounds(575, 455, 108, 36);

        txtName.getDocument().addDocumentListener(new DocumentListener() {
            public void changedUpdate(DocumentEvent e) {
                updateButtonState();
            }

            public void removeUpdate(DocumentEvent e) {
                updateButtonState();
            }

            public void insertUpdate(DocumentEvent e) {
                updateButtonState();
            }

            public void updateButtonState() {
                btnProceed.setEnabled(!txtName.getText().trim().isEmpty());
            }
        });

        btnProceed.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    // Get and validate the input data
                    NameCust = txtName.getText();
                    Bento_Quantity = txtBento.getText().isEmpty() ? 0 : Integer.parseInt(txtBento.getText());
                    Gum_Quantity = txtGum.getText().isEmpty() ? 0 : Integer.parseInt(txtGum.getText());
                    Mom_Quantity = txtMom.getText().isEmpty() ? 0 : Integer.parseInt(txtMom.getText());
                    Hitto_Quantity = txtHitto.getText().isEmpty() ? 0 : Integer.parseInt(txtHitto.getText());

                    // Validate that at least one quantity is greater than 0
                    if (Bento_Quantity <= 0 && Gum_Quantity <= 0 && Mom_Quantity <= 0 && Hitto_Quantity <= 0) {
                        JOptionPane.showMessageDialog(null, "Please enter at least one quantity at any candy.", "Input Error", JOptionPane.ERROR_MESSAGE);
                        return; // Stop further execution
                    }

                    // Validate quantity range (ensuring each is less than 10)
                    if (Bento_Quantity >= 10 || Gum_Quantity >= 10 || Mom_Quantity >= 10 || Hitto_Quantity >= 10) {
                        JOptionPane.showMessageDialog(null, "Quantity cannot be 10 or more.", "Input Error", JOptionPane.ERROR_MESSAGE);
                        return; // Stop further execution
                    }

                    // Calculate the total correctly
                    Total = (Bento_Quantity * bentoPrice) + (Gum_Quantity * gumPrice) + (Mom_Quantity * momPrice) + (Hitto_Quantity * hittoPrice);
                    textAreaTotal.setText(Double.toString(Total));

                    // Prepare the socket connection and send data
                    try (Socket socket = new Socket("25.6.15.15", 88);
                         PrintWriter out = new PrintWriter(socket.getOutputStream(), true)) {

                        String data = NameCust + ";" + Bento_Quantity + ";" + Gum_Quantity + ";" + Mom_Quantity + ";" + Hitto_Quantity + ";" + Total;
                        out.println(data);
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }

                        // Display success message
                        JOptionPane.showMessageDialog(null, "Your order successfully accepted, please go to our shop now!", "Order Accepted", JOptionPane.INFORMATION_MESSAGE);

                        // Clear input fields after processing
                        txtName.setText("");
                        txtBento.setText("");
                        txtGum.setText("");
                        txtMom.setText("");
                        txtHitto.setText("");
                        textAreaTotal.setText("");

                        // Optionally, you can reset the button state if needed
                        btnProceed.setEnabled(false);

                    } catch (NumberFormatException nfe) {
                        // Display error message if input is not a valid number
                        JOptionPane.showMessageDialog(null, "Invalid input, please enter numbers only.", "Input Error", JOptionPane.ERROR_MESSAGE);
                    }
                } 
        });

        
        btnProceed.setFont(new Font("Tahoma", Font.PLAIN, 20));
		frame.getContentPane().add(btnProceed);
		
		JLabel lblNewLabel = new JLabel("New label");
		lblNewLabel.setIcon(new ImageIcon("C:\\xampp\\htdocs\\WhatsApp Image 2024-06-23 at 21.37.43_63427034.jpg"));
		lblNewLabel.setBounds(0, 0, 736, 516);
		frame.getContentPane().add(lblNewLabel);
	}
}