package banking;

import java.sql.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class DatabaseManager {

    static final Scanner s = new Scanner(System.in);

    static Connection conn = null;
    static Map<String, Card> cards = new HashMap<>();

    public static void makeDBConnection(String fileName) {
        String sql = "CREATE TABLE IF NOT EXISTS card (\n"
                + "    id integer PRIMARY KEY,\n"
                + "    number text NOT NULL UNIQUE,\n"
                + "    pin text NOT NULL,\n"
                + "    balance integer\n"
                + ");";

        try {
            conn = DriverManager.getConnection("jdbc:sqlite:" + fileName);
            Statement stmt = conn.createStatement();

            // napravi novu tablicu ako je nema
            stmt.executeUpdate(sql);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void insertToDB(Card c) {

        String sql = "INSERT INTO card (number, pin, balance) VALUES(?, ?, ?)";
        //RuntimeException exception = new RuntimeException();

        try (PreparedStatement s = conn.prepareStatement(sql)) {
            s.setString(1, c.getCardNumber());
            s.setString(2, c.getPin());
            s.setInt(3, c.getBalance());
            s.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        cards.put(c.getCardNumber(), c);
        System.out.println(c);
    }

    /*public void selectBalance() {
        Card c = new Card();
        String sql1 = "SELECT balance FROM fileName";
        try (PreparedStatement s = conn.prepareStatement(sql1)) {
           c.getBalance();
           s.execute();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        System.out.println(sql1);
        cards.put(c.getCardNumber(), c);
        System.out.println(c);
    }

    public void addIncome(Card c) {

        int b =s.nextInt();
        int b0 = c.getBalance();
        int newbalance = b + b0;

        String sql = "INSERT INTO card (number, pin, balance) VALUES(?, ?, ?)";
        try (PreparedStatement s = conn.prepareStatement(sql)) {
            s.setInt(3, newbalance);
            s.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        cards.put(c.getCardNumber(), c);
        System.out.println(c);
    }
    public boolean isCardInDB(String number, String pin) {
        String sql = "SELECT number, pin FROM card";
        try {
            Connection conn = this.conn;
            Statement statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                if (number.equals(resultSet.getString("number")) &&
                        pin.equals(resultSet.getString("pin"))) {
                    return true;
                }
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return false;
    }*/
}
