package ma.projet.service;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;


import ma.projet.beans.Client;
import ma.projet.connexion.Connexion;
import ma.projet.dao.IDao;

public class ClientService implements IDao<Client> {

    @Override
    public boolean create(Client obj) {
        String query = "INSERT INTO client (nom, prenom) VALUES (?, ?)";
        try (Connection connection = Connexion.getConnection();
            PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, obj.getName());
            statement.setString(2, obj.getPrenom());
            return statement.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean delete(Client obj) {
        String query = "DELETE FROM client WHERE id = ?";
        try (Connection connection = Connexion.getConnection();
            PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, obj.getId());
            return statement.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean update(Client obj) {
        String query = "UPDATE client SET nom = ?, prenom = ? WHERE id = ?";
        try (Connection connection = Connexion.getConnection();
            PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, obj.getName());
            statement.setString(2, obj.getPrenom());
            statement.setInt(3, obj.getId());
            return statement.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }


    @Override
    public Client findById(int id) {
        String query = "SELECT * FROM client WHERE id = ?";
        try (Connection connection = Connexion.getConnection();
            PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return new Client(resultSet.getInt("id"),
                            resultSet.getString("nom"),
                            resultSet.getString("prenom"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Client> findAll() {
        List<Client> clients = new ArrayList<>();
        String query = "SELECT * FROM client";
        try (Connection connection = Connexion.getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query)) {
            while (resultSet.next()) {
                clients.add(new Client(resultSet.getInt("id"),
                        resultSet.getString("nom"),
                        resultSet.getString("prenom")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return clients;
    }
}
