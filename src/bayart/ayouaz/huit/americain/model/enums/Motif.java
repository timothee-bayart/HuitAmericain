package bayart.ayouaz.huit.americain.model.enums;

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

	public String toString() {
		return this.label;
    }

	public int getNumero() {
		return this.numero;
	}

	public static Motif fromString(String text) {
		for (Motif motif : Motif.values()) {
			if (motif.toString().equals(text)) {
				return motif;
			}
		}
		throw new IllegalArgumentException("No Motif with text " + text + " found");
	}
}
