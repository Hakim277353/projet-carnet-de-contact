import javax.swing.*; 
import javax.swing.event.*; 
import javax.swing.table.*; 
import java.awt.*; 
import java.awt.event.*; 
import java.util.List; // Ajout de l'import pour List

/**
 * MainFrame - Fenêtre principale de l'application
 * Gère l'interface utilisateur principale et les interactions
 */
public class MainFrame extends JFrame {
    /** DAO pour l'accès aux données des contacts */
    private ContactDao dao = new ContactDao();
    /** Table pour afficher la liste des contacts */
    private JTable table;
    /** Champ de recherche pour filtrer les contacts */
    private JTextField searchField;

    /**
     * Constructeur - Initialise l'interface graphique principale
     * Configure la disposition et les composants de la fenêtre
     */
    public MainFrame() {
        setTitle("Gestionnaire de Contacts");
        setLayout(new BorderLayout());
        setPreferredSize(new Dimension(600, 400));

        // Tableau des contacts
        table = new JTable(new ContactTableModel(dao.getAllContacts()));
        add(new JScrollPane(table), BorderLayout.CENTER);

        // Barre de recherche (en haut)
        JPanel searchPanel = new JPanel();
        searchPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        searchField = new JTextField(20);
        searchField.getDocument().addDocumentListener(new SearchListener()); // Écouteur dynamique
        searchPanel.add(new JLabel("Rechercher:"));
        searchPanel.add(searchField);
        add(searchPanel, BorderLayout.NORTH);

        // Boutons (en bas)
        JPanel buttonsPanel = new JPanel();
        buttonsPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        buttonsPanel.add(new JButton(new AddContactAction()));
        buttonsPanel.add(new JButton(new ModifyContactAction())); // Ajout du bouton Modifier
        buttonsPanel.add(new JButton(new DeleteContactAction()));
        add(buttonsPanel, BorderLayout.SOUTH);

        pack();
        setLocationRelativeTo(null);
    }

    /**
     * Classe interne pour gérer l'action d'ajout d'un contact
     */
    private class AddContactAction extends AbstractAction {
        public AddContactAction() {
            super("Ajouter");
        }

        public void actionPerformed(ActionEvent e) {
            ContactForm form = new ContactForm(MainFrame.this);
            form.setVisible(true);

            // Rafraîchir le tableau après ajout
            refreshTable();
        }
    }

    /**
     * Classe interne pour gérer l'action de suppression d'un contact
     */
    private class DeleteContactAction extends AbstractAction {
        public DeleteContactAction() {
            super("Supprimer");
        }

        public void actionPerformed(ActionEvent e) {
            int selectedRow = table.getSelectedRow();
            if (selectedRow >= 0) {
                int confirm = JOptionPane.showConfirmDialog(
                        MainFrame.this,
                        "Supprimer ce contact ?",
                        "Confirmation",
                        JOptionPane.YES_NO_OPTION
                );

                if (confirm == JOptionPane.YES_OPTION) {
                    Contact contact = dao.getAllContacts().get(selectedRow);
                    dao.deleteContact(contact.getId());
                    refreshTable();
                }
            } else {
                JOptionPane.showMessageDialog(
                        MainFrame.this,
                        "Sélectionnez un contact à supprimer.",
                        "Erreur",
                        JOptionPane.WARNING_MESSAGE
                );
            }
        }
    }

    /**
     * Classe interne pour gérer l'action de modification d'un contact
     */
    private class ModifyContactAction extends AbstractAction {
        public ModifyContactAction() {
            super("Modifier");
        }

        public void actionPerformed(ActionEvent e) {
            int selectedRow = table.getSelectedRow();
            if (selectedRow >= 0) {
                selectedRow = table.convertRowIndexToModel(selectedRow); // Important pour le tri
                Contact contactToModify = dao.getAllContacts().get(selectedRow);
                ContactForm form = new ContactForm(MainFrame.this, contactToModify);
                form.setVisible(true);
                refreshTable();
            } else {
                JOptionPane.showMessageDialog(
                    MainFrame.this,
                    "Sélectionnez un contact à modifier.",
                    "Erreur",
                    JOptionPane.WARNING_MESSAGE
                );
            }
        }
    }

    /**
     * Classe interne pour gérer la recherche en temps réel
     */
    private class SearchListener implements DocumentListener {
        public void insertUpdate(DocumentEvent e) { triggerSearch(); }
        public void removeUpdate(DocumentEvent e) { triggerSearch(); }
        public void changedUpdate(DocumentEvent e) { triggerSearch(); }

        private void triggerSearch() {
            String query = searchField.getText().toLowerCase();
            TableRowSorter<ContactTableModel> sorter = new TableRowSorter<>((ContactTableModel) table.getModel());
            table.setRowSorter(sorter);
            sorter.setRowFilter(RowFilter.regexFilter("(?i)" + query)); // Filtre insensible à la casse
        }
    }

    /**
     * Rafraîchit l'affichage de la table avec les données à jour
     */
    private void refreshTable() {
        table.setModel(new ContactTableModel(dao.getAllContacts()));
    }
}
