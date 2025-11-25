package Onlinehealthcare.management.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class Register extends JFrame implements ActionListener {

    JTextField textUsername;
    JPasswordField textPassword, textConfirmPassword;
    JButton bRegister, bCancel;

    Register() {

        // Frame Title
        setTitle("Patient Registration");

        // Username Label and TextField
        JLabel labelUser = new JLabel("Username:");
        labelUser.setBounds(40, 20, 100, 30);
        labelUser.setFont(new Font("Tahoma", Font.BOLD, 14));
        add(labelUser);

        textUsername = new JTextField();
        textUsername.setBounds(150, 20, 150, 30);
        add(textUsername);

        // Password Label and Field
        JLabel labelPass = new JLabel("Password:");
        labelPass.setBounds(40, 70, 100, 30);
        labelPass.setFont(new Font("Tahoma", Font.BOLD, 14));
        add(labelPass);

        textPassword = new JPasswordField();
        textPassword.setBounds(150, 70, 150, 30);
        add(textPassword);

        // Confirm Password Label and Field
        JLabel labelConfirm = new JLabel("Confirm Password:");
        labelConfirm.setBounds(40, 120, 150, 30);
        labelConfirm.setFont(new Font("Tahoma", Font.BOLD, 14));
        add(labelConfirm);

        textConfirmPassword = new JPasswordField();
        textConfirmPassword.setBounds(150, 150, 150, 30);
        add(textConfirmPassword);

        // Register Button
        bRegister = new JButton("Register");
        bRegister.setBounds(40, 200, 120, 30);
        bRegister.setBackground(Color.BLACK);
        bRegister.setForeground(Color.WHITE);
        bRegister.addActionListener(this);
        add(bRegister);

        // Cancel Button
        bCancel = new JButton("Cancel");
        bCancel.setBounds(180, 200, 120, 30);
        bCancel.setBackground(Color.BLACK);
        bCancel.setForeground(Color.WHITE);
        bCancel.addActionListener(this);
        add(bCancel);

        // Frame properties
        getContentPane().setBackground(new Color(109, 164, 170));
        setLayout(null);
        setSize(400, 300);
        setLocation(500, 300);
        setVisible(true);
    }

    // Custom method to show colored popup
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

        if (e.getSource() == bRegister) {
            String username = textUsername.getText().trim();
            String password = new String(textPassword.getPassword()).trim();
            String confirm = new String(textConfirmPassword.getPassword()).trim();

            // Validation
            if (username.isEmpty() || password.isEmpty() || confirm.isEmpty()) {
                showMessage("Please fill all fields", Color.RED, Color.WHITE, "Error");
                return;
            }

            if (!password.equals(confirm)) {
                showMessage("Passwords do not match", Color.RED, Color.WHITE, "Error");
                return;
            }

            try {
                Conn c = new Conn(); // Your database connection class
                String query = "INSERT INTO login(ID, PW) VALUES('" + username + "','" + password + "')";
                c.statement.executeUpdate(query);

                showMessage("Registered successfully!", new Color(0, 255, 255), Color.RED, "Success");
                setVisible(false);
                new Login(); // redirect to login page

            } catch (SQLIntegrityConstraintViolationException ex) {
                showMessage("Username already exists!", Color.RED, Color.WHITE, "Error");
            } catch (Exception ex) {
                ex.printStackTrace();
            }

        } else if (e.getSource() == bCancel) {
            setVisible(false);
            new Login(); // Go back to login page
        }
    }

    public static void main(String[] args) {
        new Register();
    }
}
