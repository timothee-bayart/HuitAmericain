package bayart.ayouaz.huit.americain.model.enums;

/**
 *
 * Cette enum permet de manipuler plus simplement les motifs que peuvent avoir
 * les cartes. C'est à dire As, Roi, deux...
 */
public enum Motif {
    AS("As", 1),
    DEUX("Deux", 2),
    TROIS("Trois", 3),
    QUATRE("Quatre", 4),
    CINQ("Cinq", 5),
    SIX("Six", 6),
    SEPT("Sept", 7),
    HUIT("Huit", 8),
    NEUF("Neuf", 9),
    DIX("Dix", 10),
    VALET("Valet", 11),
    DAME("Dame", 12),
    ROI("Roi", 13),
    JOKER("Joker", 666);

    private final String label;
    private final int numero;

    Motif(String label, int numero) {
        this.label = label;
        this.numero = numero;
    }
    /**
     * permet d'afficher le nom du motif
     * @return le string du nom du motif
     */
    public String toString() {
        return this.label;
    }
    /**
     * permet d'obtenir l'id d'une couleur
     * @return l'id de la couleur courante
     */
    public int getNumero() {
        return this.numero;
    }
    /**
     * Permet d'obtenir un motif à partir de son nom.
     * @param text le nom d'un motif
     * @return un motif au type Motif
     */
    public static Motif fromString(String text) {
        for (Motif motif : Motif.values()) {
            if (motif.toString().equals(text)) {
                return motif;
            }
        }
        throw new IllegalArgumentException("No Motif with text " + text + " found");
    }
}
