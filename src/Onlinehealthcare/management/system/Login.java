package Onlinehealthcare.management.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class Login extends JFrame implements ActionListener {

    JTextField textUsername;
    JPasswordField textPassword;
    JButton bLogin, bCancel;

    Login() {

        // Frame Title
        setTitle("Patient Login");

        // Username Label and TextField
        JLabel labelUser = new JLabel("Username:");
        labelUser.setBounds(40, 30, 100, 30);
        labelUser.setFont(new Font("Tahoma", Font.BOLD, 14));
        add(labelUser);

        textUsername = new JTextField();
        textUsername.setBounds(150, 30, 150, 30);
        add(textUsername);

        // Password Label and Field
        JLabel labelPass = new JLabel("Password:");
        labelPass.setBounds(40, 80, 100, 30);
        labelPass.setFont(new Font("Tahoma", Font.BOLD, 14));
        add(labelPass);

        textPassword = new JPasswordField();
        textPassword.setBounds(150, 80, 150, 30);
        add(textPassword);

        // Login Button
        bLogin = new JButton("Login");
        bLogin.setBounds(40, 140, 120, 30);
        bLogin.setBackground(Color.BLACK);
        bLogin.setForeground(Color.WHITE);
        bLogin.addActionListener(this);
        add(bLogin);

        // Cancel Button
        bCancel = new JButton("Cancel");
        bCancel.setBounds(180, 140, 120, 30);
        bCancel.setBackground(Color.BLACK);
        bCancel.setForeground(Color.WHITE);
        bCancel.addActionListener(this);
        add(bCancel);

        // Frame properties
        getContentPane().setBackground(new Color(109, 164, 170));
        setLayout(null);
        setSize(400, 250);
        setLocation(500, 300);
        setVisible(true);
    }

    // Helper method for colored popups
    private void showMessage(String message, Color bgColor, Color textColor, String title) {
        JPanel panel = new JPanel();
        panel.setBackground(bgColor);
        JLabel label = new JLabel(message);
        label.setForeground(textColor);
        label.setFont(new Font("Tahoma", Font.BOLD, 16));
        panel.add(label);
        JOptionPane.showMessageDialog(null, panel, title, JOptionPane.INFORMATION_MESSAGE);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == bLogin) {
            String username = textUsername.getText().trim();
            String password = new String(textPassword.getPassword()).trim();

            if(username.isEmpty() || password.isEmpty()) {
                showMessage("Please enter both username and password", Color.RED, Color.WHITE, "Error");
                return;
            }

            try {
                Conn c = new Conn(); // Your database connection class
                String query = "SELECT * FROM login WHERE ID = '"+username+"' AND PW = '"+password+"'";
                ResultSet rs = c.statement.executeQuery(query);

                if(rs.next()) {
                    showMessage("Login Successful!", new Color(0, 255, 255), Color.RED, "Success"); // Aqua bg, red text
                    new Reception(); // Replace with your next page
                    setVisible(false);
                } else {
                    showMessage("Invalid Username or Password", Color.RED, Color.WHITE, "Error");
                }

            } catch(Exception ex) {
                ex.printStackTrace();
            }

        } else if (e.getSource() == bCancel) {
            System.exit(0);
        }
    }

    public static void main(String[] args) {
        new Login();
    }
}
