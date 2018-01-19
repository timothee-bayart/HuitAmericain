package bayart.ayouaz.huit.americain.model.enums;

/**
 *
 * Cette enum permet de manipuler plus simplement les couleurs que peuvent avoir
 * les cartes. C'est à dire Coeur, carreau, pic et trefle.
 */
public enum Couleur {
    COEUR("Coeur", 0),
    CARREAU("Carreau", 1),
    PIC("Pic", 2),
    TREFLE("Trèfle", 3);

    private final String label;
    public final int numero;

    /**
     * constructeur
     *
     * @param label nom de la couleur
     * @param numero son id
     */
    Couleur(String label, int numero) {
        this.label = label;
        this.numero = numero;
    }

    /**
     * permet d'afficher le nom de la couleur
     *
     * @return le nom de la couleur
     */
    public String toString() {
        return this.label;
    }
    /**
     * permet d'obtenir l'id de la couleur
     * @return l'id de la couleur
     */
    public int getNumero() {
        return this.numero;
    }
}
