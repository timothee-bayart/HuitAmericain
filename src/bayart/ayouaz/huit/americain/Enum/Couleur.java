package bayart.ayouaz.huit.americain.Enum;

public enum Couleur {
	COEUR("Coeur"),
	CARREAU("Carreau"),
	PIC("Pic"),
	TREFLE("Trèfle");
	
	private final String label;

	Couleur(String s) {
        label = s;
    }
	
	public String toString() {
		return this.label;
    } 
}
