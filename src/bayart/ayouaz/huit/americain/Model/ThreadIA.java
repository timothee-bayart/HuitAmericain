
package bayart.ayouaz.huit.americain.model;

import bayart.ayouaz.huit.americain.controller.PartieGraphique;

import java.io.Serializable;

/**
 *Cette classe heritant de thread permet aux IA de jouer.
 * Elle est lancée en arriere plan pour ne pas gener l'execution du jeu.
 */
public class ThreadIA extends Thread implements Serializable {

    private PartieGraphique partie;
    /**
     * constructeur
     * @param p le controleur et toutes les informations necessaire afin de pouvoir jouer.
     */
    public ThreadIA(PartieGraphique p){
        super();
        partie=p;
    }
    /**
     * methode du thread qui sera executée au lancement de start().
     * Cette methode essaye de jouer toutes les 1,5 secondes. Si c'est au tour d'une
     * IA elle joue. Sinon elle ne fait rien.
     */
    @Override
    public void run() {
        while(partie.isPartieEnCours()){
            if(partie.getJoueurQuiJoue() instanceof Ia && partie.getJoueurQuiJoue().getPeutJouer()){

                try {
                    Thread.sleep(1500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

//                System.out.println(partie.getJoueurQuiJoue());
                partie.jouerCarte(((Ia)partie.getJoueurQuiJoue()).jouer(partie.getCarteDefausse()));
            }

            try {
                Thread.sleep(1500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}
