/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bayart.ayouaz.huit.americain.Model;

import bayart.ayouaz.huit.americain.controller.PartieGraphique;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

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
        while(true){
            System.out.println(partie.getJoueurQuiJoue());
            try {
                Thread.sleep(3000);
            } catch (InterruptedException ex) {
                Logger.getLogger(ThreadIA.class.getName()).log(Level.SEVERE, null, ex);
            }
            if(partie.getJoueurQuiJoue() instanceof Ia){
                System.out.println(((Ia)partie.getJoueurQuiJoue()).jouer(partie.getDernierCoupJoue()));
            }
        }
    }
    
}
