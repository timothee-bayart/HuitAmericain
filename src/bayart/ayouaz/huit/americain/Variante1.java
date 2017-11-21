package bayart.ayouaz.huit.americain;

public class Variante1 extends Regle {
    public String nomVariante;

	@Override
	public void initPartie() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean isCoupJouable(Carte coupJoue, Carte carteDefausse) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int conditionVictoire(GroupeDeCarte paquetDuJoueurEnCours) {
		// TODO Auto-generated method stub
		return 0;
	}

}
