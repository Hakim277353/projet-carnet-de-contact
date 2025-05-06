import javax.swing.table.AbstractTableModel;
import java.util.List;

/**
 * ContactTableModel - Modèle de données pour la table des contacts
 * Fait le lien entre les données des contacts et leur affichage dans le JTable
 */
public class ContactTableModel extends AbstractTableModel {
    /** Liste des contacts à afficher */
    private List<Contact> contacts;
    /** Noms des colonnes du tableau */
    private final String[] columnNames = {"Nom", "Téléphone", "Email"};

    /**
     * Constructeur - Initialise le modèle avec une liste de contacts
     * @param contacts Liste des contacts à afficher
     */
    public ContactTableModel(List<Contact> contacts) {
        this.contacts = contacts;
    }

    /**
     * @return Le nombre de lignes dans la table
     */
    public int getRowCount() { return contacts.size(); }

    /**
     * @return Le nombre de colonnes dans la table
     */
    public int getColumnCount() { return columnNames.length; }

    /**
     * @param column L'index de la colonne
     * @return Le nom de la colonne
     */
    public String getColumnName(int column) { return columnNames[column]; }

    /**
     * Retourne la valeur à afficher pour une cellule donnée
     * @param row La ligne
     * @param column La colonne
     * @return La valeur à afficher
     */
    public Object getValueAt(int row, int column) {
        Contact contact = contacts.get(row);
        return switch (column) {
            case 0 -> contact.getNom();
            case 1 -> contact.getTelephone();
            case 2 -> contact.getEmail();
            default -> null;
        };
    }
}
