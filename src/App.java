import javax.swing.SwingUtilities;

/**
 * Classe principale de l'application de gestion des contacts
 * Point d'entrée du programme
 */
public class App {
    /**
     * Méthode principale qui démarre l'application
     * @param args Arguments de ligne de commande (non utilisés)
     */
    public static void main(String[] args) {
        try {
            // Initialisation de la connexion à la base de données
            DatabaseConfig.initDatabase();
            
            // Lancement de l'interface graphique dans le thread EDT (Event Dispatch Thread)
            SwingUtilities.invokeLater(() -> {
                // Création et affichage de la fenêtre principale
                MainFrame frame = new MainFrame();
                frame.setDefaultCloseOperation(MainFrame.EXIT_ON_CLOSE);
                frame.setVisible(true);
            });
        } catch (Exception e) {
            // Gestion des erreurs au démarrage
            System.err.println("Erreur lors du démarrage de l'application");
            e.printStackTrace();
            System.exit(1);
        }
    }
}
