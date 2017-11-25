package bayart.ayouaz.huit.americain.Model;

import bayart.ayouaz.huit.americain.Enum.Motif;

public class Variante1 extends Regle {
    public String nomVariante;
    
        Variante1(){
            derniereCarteJoue=null;
        }
        
	@Override
	public void initPartie() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean isCoupJouable(Carte coupJoue, Carte carteDefausse) {
		if (carteDefausse==null ||
                        coupJoue.getMotif()==Motif.JOKER ||
                        coupJoue.getMotif()==Motif.HUIT ||
                        coupJoue.getMotif()==carteDefausse.getMotif() ||
                        coupJoue.getCouleur() == carteDefausse.getCouleur()){
                        derniereCarteJoue=coupJoue;
                        return true;
                }
                else{
                    return false;
                }
	}

	@Override
	public boolean aGagne(GroupeDeCarte paquetDuJoueurEnCours) {
		return paquetDuJoueurEnCours.getNombreDeCartes()==0;
	}

    @Override
    public int leProchainJoueurDevra() {
        switch(derniereCarteJoue.getMotif()){
            case DEUX:
                break;
            case SEPT:
                break;
            case HUIT:
                break;
            case DIX:
                break;
            case VALET:
                break;
            case AS:
                break;
            case JOKER:
                break;
        }
        return 0;
    }

}
