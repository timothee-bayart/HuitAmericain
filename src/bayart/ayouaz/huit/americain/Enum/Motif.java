package bayart.ayouaz.huit.americain.Enum;

public enum Motif {
	AS("As"),
	DEUX("Deux"),
	TROIS("Trois"),
	QUATRE("Quatre"),
	CINQ("Cinq"),
	SIX("Six"),
	SEPT("Sept"),
	HUIT("Huit"),
	NEUF("Neuf"),
	DIX("Dix"),
	VALET("Valet"),
	DAME("Dame"),
	ROI("Roi"),
	JOKER("Joker");

	private final String label;

	Motif(String s) {
        label = s;
    }
	

	public String toString() {
		return this.label;
    } 
}
