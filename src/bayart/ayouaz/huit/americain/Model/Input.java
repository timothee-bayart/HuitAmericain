/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bayart.ayouaz.huit.americain.Model;

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 *
 * Cette classe permet une utilisation plus simple et plus haut niveau des scanners.
 */
public class Input {
    public static Scanner CLAVIER = new Scanner(System.in);
    
    public static int demanderEntier(int min, int max){
        int nombre = 0;
        boolean choixEffectue = false;

        do{
            try {
                nombre = CLAVIER.nextInt();

                if(nombre>=min && nombre<=max){
                    choixEffectue = true;
                }

            } catch(InputMismatchException e) {
                CLAVIER.next();
            }
        } while(!choixEffectue);

        return nombre;
    }
    
    public static String demanderString(){
        return CLAVIER.nextLine();
    }
}
