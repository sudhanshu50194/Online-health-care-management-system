package Onlinehealthcare.management.system;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class Conn {

    Connection connection;
    Statement statement;

    public Conn() {
        try {
            connection = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/Onlinehealthcare_management_system",
                    "root",
                    "Sudhanshu@99"
            );
            statement = connection.createStatement();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

