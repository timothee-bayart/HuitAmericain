package bayart.ayouaz.huit.americain.model;

import bayart.ayouaz.huit.americain.model.enums.Couleur;
import bayart.ayouaz.huit.americain.model.enums.Motif;

import java.io.Serializable;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Observable;

/**
 * 
 * Cette classe est une collection de Cartes personnalisée.
 */
public class GroupeDeCarte extends Observable implements Serializable {
	
    public static final int PAQUET_32_CARTES = 32;
    public static final int PAQUET_52_CARTES = 52;

    //list car on veut pouvoir prendre une carte a une certaine posistion, et queue car on veut aussi piocher dans une pile
    private LinkedList<Carte> cartes = new LinkedList<Carte>();

    /**
     * Constructeur
     * @param nbPaquet le nombre de paquets de carte utilisés pour generer ce groupe de carte
     * @param nbCarteParPaquet le nombre de carte par paquet (32, 54...)
     * @param joker si l'on souhaite ou non jouer avec des jokers
     */
    public GroupeDeCarte(int nbPaquet, int nbCarteParPaquet, boolean joker){

        if(nbPaquet>0 && (nbCarteParPaquet==PAQUET_32_CARTES || nbCarteParPaquet==PAQUET_52_CARTES)){

            for(int i=0; i<nbPaquet; ++i){

                for(Couleur couleur : Couleur.values()) {
                    for(Motif motif : Motif.values()) {
                        if(motif != Motif.JOKER &&
                            ((nbCarteParPaquet == PAQUET_32_CARTES && motif.getNumero() >= 7) ||
                            nbCarteParPaquet == PAQUET_52_CARTES)){

                            cartes.add(new Carte(couleur, motif));
                        }
                    }
                }

                if(joker){
                    cartes.add(new Carte(null, Motif.JOKER));
                    cartes.add(new Carte(null, Motif.JOKER));
                }
            }
        }
    }

    /**
     * Constructeur par défault
     */
    public GroupeDeCarte() {
    }

    /**
     * Mélange le groupe de carte
     */
    public void melanger(){
    	Collections.shuffle(this.cartes);
    }

    /**
     * Permet d'ajouter une carte dans le groupe de carte
     * @param carteAAjouter la carte que l'on veut ajouter
     */
    public void ajouterCarte(Carte carteAAjouter) {
    	this.cartes.add(carteAAjouter);
    }

    /**
     * Permet de retirer une carte dans le groupe de carte
     * @param aRetirer la carte que l'on veut retirer
     * @return retourne la carte qui a été retirée
     */
    public Carte retirerCarte(Carte aRetirer) {
		this.cartes.remove(aRetirer);
    	return aRetirer;
    }

    /**
     * Permet de piocher une carte en utilisant le groupeDeCarte comme une pile
     * @return retourne la carte qui a été retirée
     */
    public Carte retirerCarte() {
         return this.cartes.poll();
    }

    /**
     * Permet de récupérer une carte à un certain index
     * @param index l'index de la carte a retourner
     * @return retourne la carte à l'index spécifié
     */
    public Carte getCarte(int index) {
    	return this.cartes.get(index);
    }
	
    @Override
    /**
     * Redéfinition de la méthode toString, en listant les cartes avec leur index
     */
	public String toString() {
		StringBuffer sb = new StringBuffer();
		
		int index = 1;

		Iterator<Carte> cartesIt = this.cartes.iterator();

		while(cartesIt.hasNext()) {
			Carte carte = cartesIt.next();
			sb.append("["+index+"] : ");
			sb.append(carte);
			sb.append("\n");
			index++;
		}
		
		return sb.toString();
	}

    /**
     * Getter qui retourne le nombre de cartes dans le groupe de carte
     * @return le nombre de carte dans le groupe de carte
     */
	public int getNombreDeCartes() {
		return this.cartes.size();
	}

    /**
     * Getter qui retourne si le groupe de carte est vide ou non
     * @return true si le groupe de contient aucune carte, false si il en contient
     */
	public boolean estVide(){
        return this.cartes.isEmpty();
    }
}





