/**
 * Classe représentant un contact dans le carnet d'adresses
 * Contient toutes les informations relatives à un contact
 */
public class Contact {
    /** Identifiant unique du contact */
    private String id;
    /** Nom du contact */
    private String nom;
    /** Numéro de téléphone du contact */
    private String telephone;
    /** Adresse email du contact */
    private String email;

    /**
     * Constructeur pour un nouveau contact
     * @param nom Nom du contact
     * @param telephone Numéro de téléphone
     * @param email Adresse email
     */
    public Contact(String nom, String telephone, String email) {
        this.id = java.util.UUID.randomUUID().toString();
        this.nom = nom;
        this.telephone = telephone;
        this.email = email;
    }

    /**
     * Constructeur avec ID existant
     * @param id Identifiant unique
     * @param nom Nom du contact
     * @param telephone Numéro de téléphone
     * @param email Adresse email
     */
    public Contact(String id, String nom, String telephone, String email) {
        this.id = id;
        this.nom = nom;
        this.telephone = telephone;
        this.email = email;
    }

    // Getters et setters avec documentation JavaDoc
    /** @return L'identifiant unique du contact */
    public String getId() { return id; }
    /** @return Le nom du contact */
    public String getNom() { return nom; }
    /** @param nom Le nouveau nom du contact */
    public void setNom(String nom) { this.nom = nom; }
    /** @return Le numéro de téléphone du contact */
    public String getTelephone() { return telephone; }
    /** @param telephone Le nouveau numéro de téléphone du contact */
    public void setTelephone(String telephone) { this.telephone = telephone; }
    /** @return L'adresse email du contact */
    public String getEmail() { return email; }
    /** @param email La nouvelle adresse email du contact */
    public void setEmail(String email) { this.email = email; }
}
