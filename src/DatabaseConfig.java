import java.sql.*;

/**
 * Gère la configuration et la connexion à la base de données MySQL
 * Fournit les méthodes utilitaires pour initialiser et accéder à la BDD
 */
public class DatabaseConfig {
    /** URL de connexion au serveur MySQL */
    private static final String URL = "jdbc:mysql://localhost:3306/";
    /** Nom de la base de données */
    private static final String DB_NAME = "carnet_contacts";
    /** Nom d'utilisateur MySQL */
    private static final String USER = "root";
    /** Mot de passe MySQL */
    private static final String PASSWORD = "almia123";

    /** 
     * Bloc statique pour charger le driver MySQL
     * Exécuté une seule fois au chargement de la classe
     */
    static {
        try {
            // Enregistrement explicite du driver
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.err.println("MySQL JDBC Driver introuvable");
            e.printStackTrace();
            System.exit(1);
        }
    }

    /**
     * Établit une connexion à la base de données
     * Crée la base si elle n'existe pas
     * @return Connection - Une connexion active à la base
     * @throws SQLException En cas d'erreur de connexion
     */
    public static Connection getConnection() throws SQLException {
        // D'abord, essayons de nous connecter au serveur MySQL
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD)) {
            // Créer la base de données si elle n'existe pas
            try (Statement stmt = conn.createStatement()) {
                stmt.executeUpdate("CREATE DATABASE IF NOT EXISTS " + DB_NAME);
            }
        } catch (SQLException e) {
            System.err.println("Erreur lors de la création de la base de données");
            throw e;
        }

        // Maintenant, connectons-nous à la base de données spécifique
        try {
            return DriverManager.getConnection(URL + DB_NAME + "?serverTimezone=UTC", USER, PASSWORD);
        } catch (SQLException e) {
            System.err.println("Erreur de connexion à la base de données " + DB_NAME);
            throw e;
        }
    }

    /**
     * Initialise la structure de la base de données
     * Crée les tables nécessaires si elles n'existent pas
     */
    public static void initDatabase() {
        try (Connection conn = getConnection();
             Statement stmt = conn.createStatement()) {
            
            // Création de la table contacts
            String sql = """
                CREATE TABLE IF NOT EXISTS contacts (
                    id VARCHAR(36) PRIMARY KEY,
                    nom VARCHAR(100) NOT NULL,
                    telephone VARCHAR(20) NOT NULL,
                    email VARCHAR(100)
                )
            """;
            stmt.executeUpdate(sql);
            System.out.println("Base de données initialisée avec succès");
            
        } catch (SQLException e) {
            System.err.println("Erreur d'initialisation de la base de données");
            e.printStackTrace();
            System.exit(1);
        }
    }
}
