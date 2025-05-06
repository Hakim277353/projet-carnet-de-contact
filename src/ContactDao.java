import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Classe d'accès aux données (DAO) pour les contacts
 * Gère toutes les opérations CRUD avec la base de données
 */
public class ContactDao {
    
    /**
     * Récupère tous les contacts de la base de données
     * @return Liste de tous les contacts
     */
    public List<Contact> getAllContacts() {
        List<Contact> contacts = new ArrayList<>();
        String sql = "SELECT * FROM contacts";
        
        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            
            while (rs.next()) {
                contacts.add(new Contact(
                    rs.getString("id"),
                    rs.getString("nom"),
                    rs.getString("telephone"),
                    rs.getString("email")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return contacts;
    }

    /**
     * Sauvegarde une liste complète de contacts
     * Remplace tous les contacts existants
     * @param contacts Liste des contacts à sauvegarder
     */
    public void saveContacts(List<Contact> contacts) {
        try (Connection conn = DatabaseConfig.getConnection()) {
            // Vider la table
            try (PreparedStatement delete = conn.prepareStatement("DELETE FROM contacts")) {
                delete.executeUpdate();
            }

            // Insérer les nouveaux contacts
            String sql = "INSERT INTO contacts (id, nom, telephone, email) VALUES (?, ?, ?, ?)";
            try (PreparedStatement insert = conn.prepareStatement(sql)) {
                for (Contact contact : contacts) {
                    insert.setString(1, contact.getId());
                    insert.setString(2, contact.getNom());
                    insert.setString(3, contact.getTelephone());
                    insert.setString(4, contact.getEmail());
                    insert.executeUpdate();
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Ajoute un nouveau contact dans la base de données
     * @param contact Le contact à ajouter
     */
    public void addContact(Contact contact) {
        String sql = "INSERT INTO contacts (id, nom, telephone, email) VALUES (?, ?, ?, ?)";
        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setString(1, contact.getId());
            stmt.setString(2, contact.getNom());
            stmt.setString(3, contact.getTelephone());
            stmt.setString(4, contact.getEmail());
            stmt.executeUpdate();
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Met à jour les informations d'un contact existant
     * @param contact Le contact à mettre à jour
     */
    public void updateContact(Contact contact) {
        String sql = "UPDATE contacts SET nom = ?, telephone = ?, email = ? WHERE id = ?";
        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setString(1, contact.getNom());
            stmt.setString(2, contact.getTelephone());
            stmt.setString(3, contact.getEmail());
            stmt.setString(4, contact.getId());
            stmt.executeUpdate();
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Supprime un contact de la base de données
     * @param id L'identifiant du contact à supprimer
     */
    public void deleteContact(String id) {
        String sql = "DELETE FROM contacts WHERE id = ?";
        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setString(1, id);
            stmt.executeUpdate();
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
