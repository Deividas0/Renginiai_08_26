package org.example.Repository;

import org.example.Models.Klientas;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static org.example.Constants.*;

@Repository
public class Krepository {

    private static Connection _connection;

    public static void sqlConnection() throws SQLException {
        _connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
    }

    public void klientoRegistracija(String vardas, String pavarde, String elPastas, long telNumeris, String slaptazodis) throws SQLException {
        final String sql = "INSERT into klientai (vardas, pavarde, el_pastas, tel_numeris, slaptazodis) values (?, ?, ?, ?, ?)";
        try (Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD)) {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setString(1, vardas);
            preparedStatement.setString(2, pavarde);
            preparedStatement.setString(3, elPastas);
            preparedStatement.setLong(4, telNumeris);
            preparedStatement.setString(5, slaptazodis);

            preparedStatement.executeUpdate();
        }
    }

    public int login(String elPastas, String slaptazodis) throws SQLException {
        final String sql = "SELECT * FROM klientai WHERE el_pastas = ? AND slaptazodis = ?";
        try (Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD)) {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setString(1, elPastas);
            preparedStatement.setString(2, slaptazodis);

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                int id = resultSet.getInt("id");
                if (resultSet.wasNull()) {
                    return 0;
                } else {
                    return id;
                }
            }
        }
        return 0;
    }

    public int prenumeratosPatikra(int id) throws SQLException {
        final String sql = "SELECT * FROM klientai WHERE id = ?";
        try (Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD)) {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setInt(1, id);

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                String prenumerata = resultSet.getString("subscribed");
                if (prenumerata == null) {
                    return 2;
                }
                if (prenumerata.equals("1")) {
                    return 1;
                } else {
                    return 0;
                }
            }
        }
        return 0;
    }

    public String emailPatikra(String elPastas) throws SQLException {
        final String sql = "SELECT el_pastas FROM klientai WHERE el_pastas = ?";

        try (Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD)) {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, elPastas);

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                // Jei pastas yra grazinam pasta.
                return "yra";
            } else {
                // Jei tuscia grazinam "nera"
                return "nera";
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Print the stack trace if there’s an SQL exception
            throw e; // Rethrow the exception after logging it
        }
    }
    public String emailParsinesimas(int id) throws SQLException {
        final String sql = "SELECT * FROM klientai WHERE id = ?";
        try(Connection connection = DriverManager.getConnection(URL,USERNAME,PASSWORD)){
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
             preparedStatement.setInt(1,id);

             ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                String elPastas = resultSet.getString("el_pastas");
                return elPastas;
            } else {
                return "Klaida";
            }
        }
    }

    public void prenumeratosAtsisakymas(String elPastas) throws SQLException {
        final String sql = "UPDATE klientai SET subscribed = 0 WHERE el_pastas = ?";
        try(Connection connection = DriverManager.getConnection(URL,USERNAME,PASSWORD)){
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setString(1,elPastas);

            preparedStatement.executeUpdate();
        }
    }
    public void prenumeratosUzsisakymas(String elPastas) throws SQLException {
        final String sql = "UPDATE klientai SET subscribed = 1 WHERE el_pastas = ?";
        try(Connection connection = DriverManager.getConnection(URL,USERNAME,PASSWORD)){
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setString(1,elPastas);

            preparedStatement.executeUpdate();
        }
    }

    // CHAT GPT -----------------------------------------
    public List<Klientas> getSubscribedUsers() throws SQLException {
        String sql = "SELECT vardas, el_pastas FROM klientai WHERE subscribed = 1";
        List<Klientas> subscribedUsers = new ArrayList<>();

        try (Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD)) {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Klientas user = new Klientas();
                user.setVardas(resultSet.getString("vardas"));
                user.setElPastas(resultSet.getString("el_pastas"));
                subscribedUsers.add(user);
            }
        }

        return subscribedUsers;
    }

}

