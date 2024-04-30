package ma.projet.connexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Connexion {
    private static final String URL = "jdbc:mysql://localhost:3306/demoJDBC";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "";
    public static Connection getConnection() throws SQLException {
        try {
            // Chargement du pilote JDBC
            Class.forName("com.mysql.cj.jdbc.Driver");
            // Établissement de la connexion
            return DriverManager.getConnection(URL, USERNAME, PASSWORD);
        } catch (ClassNotFoundException e) {
            System.err.println("Erreur lors du chargement du pilote JDBC : " + e.getMessage());
            throw new SQLException("Driver JDBC non trouvé", e);
        }
    }

}
