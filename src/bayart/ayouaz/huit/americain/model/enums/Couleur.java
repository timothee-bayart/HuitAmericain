package bayart.ayouaz.huit.americain.model.enums;

public enum Couleur {
	COEUR("Coeur", 0),
	CARREAU("Carreau", 1),
	PIC("Pic", 2),
	TREFLE("Trèfle", 3);
	
	private final String label;
	public final int numero;

	Couleur(String label, int numero) {
		this.label = label;
		this.numero = numero;
	}
	
	public String toString() {
		return this.label;
    }

	public int getNumero() {
		return this.numero;
	}
}
