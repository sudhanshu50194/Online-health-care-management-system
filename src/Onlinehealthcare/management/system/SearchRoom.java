package  Onlinehealthcare.management.system;

import net.proteanit.sql.DbUtils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class SearchRoom extends JFrame {

    Choice choice;
    JTable table;

    private static final boolean DEBUG_DB = true;  // Print hidden characters

    SearchRoom() {

        JPanel panel = new JPanel();
        panel.setBounds(5, 5, 690, 490);
        panel.setBackground(new Color(90, 156, 163));
        panel.setLayout(null);
        add(panel);

        JLabel For = new JLabel("Search For Room");
        For.setBounds(250, 11, 250, 31);
        For.setForeground(Color.white);
        For.setFont(new Font("Tahoma", Font.BOLD, 22));
        panel.add(For);

        JLabel status = new JLabel("Status :");
        status.setBounds(70, 70, 80, 20);
        status.setForeground(Color.white);
        status.setFont(new Font("Tahoma", Font.BOLD, 14));
        panel.add(status);

        choice = new Choice();
        choice.setBounds(170, 70, 150, 20);
        choice.add("Available");
        choice.add("Occupied");
        panel.add(choice);

        table = new JTable();
        table.setBounds(0, 187, 700, 210);
        table.setBackground(new Color(90, 156, 163));
        table.setForeground(Color.white);
        table.setFont(new Font("Tahoma", Font.BOLD, 13));
        panel.add(table);

        loadAllRoomsWithDebug();

        JLabel Roomno = new JLabel("Room Number");
        Roomno.setBounds(23, 162, 150, 20);
        Roomno.setForeground(Color.white);
        Roomno.setFont(new Font("Tahoma", Font.BOLD, 14));
        panel.add(Roomno);

        JLabel available = new JLabel("Availability");
        available.setBounds(175, 162, 150, 20);
        available.setForeground(Color.white);
        available.setFont(new Font("Tahoma", Font.BOLD, 14));
        panel.add(available);

        JLabel price = new JLabel("Price");
        price.setBounds(350, 162, 150, 20);
        price.setForeground(Color.white);
        price.setFont(new Font("Tahoma", Font.BOLD, 14));
        panel.add(price);

        JLabel Bed = new JLabel("Bed Type");
        Bed.setBounds(500, 162, 150, 20);
        Bed.setForeground(Color.white);
        Bed.setFont(new Font("Tahoma", Font.BOLD, 14));
        panel.add(Bed);

        JButton Search = new JButton("Search");
        Search.setBounds(200, 420, 120, 30);
        Search.setBackground(Color.black);
        Search.setForeground(Color.white);
        Search.setFont(new Font("Tahoma", Font.BOLD, 14));
        panel.add(Search);

        Search.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String selected = choice.getSelectedItem();
                String pattern = "%" + selected.trim().toLowerCase() + "%";

                try {
                    Conn c = new Conn();

                    String sql = "SELECT * FROM room WHERE LOWER(Availability) LIKE ?";
                    PreparedStatement ps = c.connection.prepareStatement(sql);
                    ps.setString(1, pattern);

                    ResultSet rs = ps.executeQuery();
                    table.setModel(DbUtils.resultSetToTableModel(rs));

                    if (DEBUG_DB) printAvailabilityDebug(c.connection);

                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });

        JButton Back = new JButton("Back");
        Back.setBounds(380, 420, 120, 30);
        Back.setBackground(Color.black);
        Back.setForeground(Color.white);
        Back.setFont(new Font("Tahoma", Font.BOLD, 14));
        panel.add(Back);

        Back.addActionListener(e -> setVisible(false));

        setUndecorated(true);
        setSize(700, 500);
        setLayout(null);
        setLocation(450, 250);
        setVisible(true);
    }

    private void loadAllRoomsWithDebug() {
        try {
            Conn c = new Conn();
            String sql = "SELECT * FROM room";
            ResultSet rs = c.statement.executeQuery(sql);
            table.setModel(DbUtils.resultSetToTableModel(rs));

            if (DEBUG_DB) printAvailabilityDebug(c.connection);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void printAvailabilityDebug(Connection connection) {
        try {
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery("SELECT room_no, Availability FROM room");

            System.out.println("====== DEBUG ROOM DATA ======");
            while (rs.next()) {
                String room = rs.getString("room_no");
                String avail = rs.getString("Availability");

                if (avail == null) avail = "<null>";

                // Print raw value, trimmed, and each char code
                System.out.print("Room " + room + " | '" + avail + "' | len=" + avail.length() + " | chars=[ ");
                for (char c : avail.toCharArray()) {
                    System.out.print((int) c + " ");
                }
                System.out.println("]");
            }
            System.out.println("=============================");
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new SearchRoom();
    }
}
