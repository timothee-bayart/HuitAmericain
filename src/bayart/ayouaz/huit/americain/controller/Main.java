/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bayart.ayouaz.huit.americain.controller;

import bayart.ayouaz.huit.americain.view.ViewGraphic;

/**
 *
 * @author ed
 */
public class Main {
    
    public static void main(String[] args) {
        
                ViewGraphic vg = new ViewGraphic();
		Partie partie = new Partie();
		partie.initParamsDuJeu();
		partie.demarrerJeu();
	}
    
}
