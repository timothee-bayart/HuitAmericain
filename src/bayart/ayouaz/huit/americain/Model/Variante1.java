package bayart.ayouaz.huit.americain.Model;

public class Variante1 extends Regle {
    public String nomVariante;

	@Override
	public void initPartie() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean isCoupJouable(Carte coupJoue, Carte carteDefausse) {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean aGagne(GroupeDeCarte paquetDuJoueurEnCours) {
		// TODO Auto-generated method stub
		return paquetDuJoueurEnCours.getNombreDeCartes()==0;
	}

}
