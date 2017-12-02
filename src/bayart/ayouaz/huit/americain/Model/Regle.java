package bayart.ayouaz.huit.americain.Model;

import bayart.ayouaz.huit.americain.Enum.Couleur;

public abstract class Regle {

    protected int nbPaquet;
    protected int nbCarteParPaquet;
    protected boolean avecJoker;

    protected String nomVariante;
    protected Couleur nouvelleCouleur;
    protected Carte carteCreeeParJoker;

    public GroupeDeCarte genererPioche(int nbJoueurs) {
        int maxJoueursParPioche;
        if(this.nbCarteParPaquet == GroupeDeCarte.PAQUET_52_CARTES){
            maxJoueursParPioche = 5;
        } else {
            maxJoueursParPioche = 2;
        }
        return new GroupeDeCarte((nbJoueurs / maxJoueursParPioche + 1) * this.nbPaquet, this.nbCarteParPaquet, this.avecJoker);
    }

    public abstract boolean isCoupJouable(Carte coupJoue, Carte carteDefausse);

    public abstract boolean isJoueurAGagne(GroupeDeCarte paquetDuJoueurEnCours);
    
    public abstract void appliquerEffetCarteSpeciale(Partie p);

    public Couleur getNouvelleCouleur(){
        return this.nouvelleCouleur;
    }

    public Carte getCarteCreeeParJoker() {
        return carteCreeeParJoker;
    }

    @Override
    public String toString(){
        return this.nomVariante;
    }
}
