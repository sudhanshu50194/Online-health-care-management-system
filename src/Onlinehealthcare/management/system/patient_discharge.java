package Onlinehealthcare.management.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.util.Date;

public class patient_discharge extends JFrame {

    patient_discharge(){

        JPanel panel = new JPanel();
        panel.setBounds(5,5,790,390);
        panel.setBackground(new Color(90, 156, 163));
        panel.setLayout(null);
        add(panel);

        JLabel label = new JLabel("CHECK-OUT");
        label.setBounds(100,20,150,20);
        label.setFont(new Font("Tahoma",Font.BOLD,20));
        label.setForeground(Color.white);
        panel.add(label);

        JLabel label2 = new JLabel("Customer Id");
        label2.setBounds(30,80,150,20);
        label2.setFont(new Font("Tahoma",Font.BOLD,14));
        label2.setForeground(Color.white);
        panel.add(label2);

        Choice choice = new Choice();
        choice.setBounds(200,80,150,25);
        panel.add(choice);

        // LOAD PATIENT NUMBERS
        try{
            Conn c = new Conn();

            ResultSet resultSet = c.statement.executeQuery("select * from Patient_Info");
            while (resultSet.next()){
                choice.add(resultSet.getString("number"));
            }
        }catch (Exception e){
            e.printStackTrace();
        }

        JLabel label3 = new JLabel("Room Number");
        label3.setBounds(30,130,150,20);
        label3.setFont(new Font("Tahoma",Font.BOLD,14));
        label3.setForeground(Color.white);
        panel.add(label3);

        JLabel RNo = new JLabel();
        RNo.setBounds(200,130,150,20);
        RNo.setFont(new Font("Tahoma",Font.BOLD,14));
        RNo.setForeground(Color.white);
        panel.add(RNo);

        JLabel label4 = new JLabel("In Time");
        label4.setBounds(30,180,150,20);
        label4.setFont(new Font("Tahoma",Font.BOLD,14));
        label4.setForeground(Color.white);
        panel.add(label4);

        JLabel INTime = new JLabel();
        INTime.setBounds(200,180,250,20);
        INTime.setFont(new Font("Tahoma",Font.BOLD,14));
        INTime.setForeground(Color.white);
        panel.add(INTime);

        JLabel label5 = new JLabel("Out Time");
        label5.setBounds(30,230,150,20);
        label5.setFont(new Font("Tahoma",Font.BOLD,14));
        label5.setForeground(Color.white);
        panel.add(label5);

        Date date = new Date();

        JLabel OUTTime = new JLabel(""+date);
        OUTTime.setBounds(200,230,250,20);
        OUTTime.setFont(new Font("Tahoma",Font.BOLD,14));
        OUTTime.setForeground(Color.white);
        panel.add(OUTTime);

        // DISCHARGE BUTTON
        JButton discharge = new JButton("Discharge");
        discharge.setBounds(30,300,120,30);
        discharge.setBackground(Color.black);
        discharge.setForeground(Color.white);
        panel.add(discharge);

        discharge.addActionListener(e -> {
            Conn c = new Conn();

            try {
                c.statement.executeUpdate("delete from Patient_Info where number = '"+choice.getSelectedItem()+"'");
                c.statement.executeUpdate("update room set Availability = 'Available' where room_no = '"+RNo.getText()+"'");
                JOptionPane.showMessageDialog(null,"Done");
                setVisible(false);
            } catch (Exception E){
                E.printStackTrace();
            }
        });

        // CHECK BUTTON (FIXED)
        JButton Check = new JButton("Check");
        Check.setBounds(170,300,120,30);
        Check.setBackground(Color.black);
        Check.setForeground(Color.white);
        panel.add(Check);

        Check.addActionListener(e -> {
            Conn c = new Conn();

            try{
                ResultSet resultSet = c.statement.executeQuery(
                        "select * from Patient_Info where number = '"+choice.getSelectedItem()+"'"
                );
                if (resultSet.next()){
                    // FIXED COLUMN NAMES
                    RNo.setText(resultSet.getString("room_number"));
                    INTime.setText(resultSet.getString("time"));
                }
            } catch (Exception E){
                E.printStackTrace();
            }
        });

        // BACK BUTTON
        JButton Back = new JButton("Back");
        Back.setBounds(300,300,120,30);
        Back.setBackground(Color.black);
        Back.setForeground(Color.white);
        panel.add(Back);

        Back.addActionListener(e -> setVisible(false));

        setUndecorated(true);
        setSize(800,400);
        setLayout(null);
        setLocation(400,250);
        setVisible(true);
    }

    public static void main(String[] args) {
        new patient_discharge();
    }
}
