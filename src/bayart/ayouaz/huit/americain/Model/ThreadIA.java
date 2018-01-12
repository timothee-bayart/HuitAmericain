/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bayart.ayouaz.huit.americain.model;

import bayart.ayouaz.huit.americain.controller.PartieGraphique;

/**
 *
 * @author ed
 */
public class ThreadIA extends Thread{

    private PartieGraphique partie;

    public ThreadIA(PartieGraphique p){
        super();
        partie=p;
    }

    @Override
    public void run() {
        while(true){ //attention pas de while true, rajouter condition de sortie
            if(partie.getJoueurQuiJoue() instanceof Ia && partie.getJoueurQuiJoue().getPeutJouer()){
                System.out.println(partie.getJoueurQuiJoue());
                partie.jouerCarte(((Ia)partie.getJoueurQuiJoue()).jouer(partie.getCarteDefausse()));
            }

            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}
