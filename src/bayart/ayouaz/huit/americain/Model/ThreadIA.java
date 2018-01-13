/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bayart.ayouaz.huit.americain.model;

import bayart.ayouaz.huit.americain.controller.PartieGraphique;

import java.io.Serializable;

/**
 *
 * @author ed
 */
public class ThreadIA extends Thread implements Serializable {

    private PartieGraphique partie;

    public ThreadIA(PartieGraphique p){
        super();
        partie=p;
    }

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
