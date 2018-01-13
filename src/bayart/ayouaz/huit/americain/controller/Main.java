/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bayart.ayouaz.huit.americain.controller;

import bayart.ayouaz.huit.americain.model.Input;
import bayart.ayouaz.huit.americain.view.IHM;
import bayart.ayouaz.huit.americain.view.ViewGraphic;
import bayart.ayouaz.huit.americain.view.ViewTerminal;

/**
 *
 * @author ed
 */
public class Main {

    public static void main(String[] args) {
        PartieGraphique p = new PartieGraphique();
        System.out.println("Quelle vue voulez vous? \n 0 - terminal\n 1 - graphique");
        int choix = Input.demanderEntier(0,1);

        IHM ihm;
        if(choix == 0){
            ihm = new ViewTerminal(p);
        } else if(choix == 1){
            ihm = new ViewGraphic(p);
        }
    }

}
