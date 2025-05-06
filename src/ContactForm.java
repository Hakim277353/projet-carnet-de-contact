import javax.swing.*;
import java.awt.*;
import java.util.List;

/**
 * Formulaire de dialogue pour ajouter ou modifier un contact
 * Gère l'interface utilisateur pour la saisie des données
 */
public class ContactForm extends JDialog {
    /** Champ de saisie pour le nom */
    private JTextField nomField;
    /** Champ de saisie pour le téléphone */
    private JTextField telField;
    /** Champ de saisie pour l'email */
    private JTextField emailField;
    /** Contact à modifier (null si nouveau contact) */
    private Contact contactToModify;

    /**
     * Constructeur pour un nouveau contact
     * @param parent Fenêtre parente
     */
    public ContactForm(JFrame parent) {
        this(parent, null);
    }

    /**
     * Constructeur pour modifier un contact existant
     * @param parent Fenêtre parente
     * @param contact Contact à modifier
     */
    public ContactForm(JFrame parent, Contact contact) {
        super(parent, contact == null ? "Nouveau Contact" : "Modifier Contact", true);
        this.contactToModify = contact;
        
        // Configuration de la fenêtre
        setLayout(new BorderLayout(10, 10));
        setSize(400, 200);
        setLocationRelativeTo(parent);
        
        // Panel principal avec GridBagLayout
        JPanel mainPanel = new JPanel(new GridBagLayout());
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        GridBagConstraints gbc = new GridBagConstraints();
        
        // Configuration des contraintes communes
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 5, 5, 5);
        
        // Ajout des champs
        gbc.gridx = 0; gbc.gridy = 0;
        mainPanel.add(new JLabel("Nom:"), gbc);
        
        gbc.gridx = 1; gbc.weightx = 1.0;
        nomField = new JTextField(20);
        mainPanel.add(nomField, gbc);
        
        gbc.gridx = 0; gbc.gridy = 1; gbc.weightx = 0.0;
        mainPanel.add(new JLabel("Téléphone:"), gbc);
        
        gbc.gridx = 1; gbc.weightx = 1.0;
        telField = new JTextField(20);
        mainPanel.add(telField, gbc);
        
        gbc.gridx = 0; gbc.gridy = 2; gbc.weightx = 0.0;
        mainPanel.add(new JLabel("Email:"), gbc);
        
        gbc.gridx = 1; gbc.weightx = 1.0;
        emailField = new JTextField(20);
        mainPanel.add(emailField, gbc);
        
        // Remplir les champs si c'est une modification
        if (contact != null) {
            nomField.setText(contact.getNom());
            telField.setText(contact.getTelephone());
            emailField.setText(contact.getEmail());
        }
        
        // Panel pour les boutons
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JButton saveBtn = new JButton("Enregistrer");
        JButton cancelBtn = new JButton("Annuler");
        
        saveBtn.addActionListener(e -> saveContact());
        cancelBtn.addActionListener(e -> dispose());
        
        buttonPanel.add(saveBtn);
        buttonPanel.add(cancelBtn);
        
        // Ajout des panels au dialog
        add(mainPanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);
    }

    /**
     * Sauvegarde le contact dans la base de données
     * Effectue les validations nécessaires
     */
    private void saveContact() {
        String nom = nomField.getText().trim();
        String tel = telField.getText().trim();
        String email = emailField.getText().trim();

        // Validation
        if (nom.isEmpty() || tel.isEmpty()) {
            JOptionPane.showMessageDialog(this,
                "Le nom et le téléphone sont obligatoires.",
                "Erreur", JOptionPane.ERROR_MESSAGE);
            return;
        }

        ContactDao dao = new ContactDao();
        
        if (contactToModify != null) {
            // Modification du contact existant
            contactToModify.setNom(nom);
            contactToModify.setTelephone(tel);
            contactToModify.setEmail(email);
            dao.updateContact(contactToModify);
        } else {
            // Nouveau contact
            Contact newContact = new Contact(nom, tel, email);
            dao.addContact(newContact);
        }
        
        dispose();
    }
}
