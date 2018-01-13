/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bayart.ayouaz.huit.americain.model;

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 *
 * Cette classe permet une utilisation plus simple et plus haut niveau des scanners.
 */
public class Input {
//    public static Scanner CLAVIER = new Scanner(System.in);
    
    public static int demanderEntier(int min, int max){
        int nombre = 0;
        boolean choixEffectue = false;

        Scanner scanner = new Scanner(System.in);

        do{
            try {
                nombre = scanner.nextInt();

                if(nombre>=min && nombre<=max){
                    choixEffectue = true;
                }

            } catch(InputMismatchException e) {
                scanner.next();
            }
        } while(!choixEffectue);

        return nombre;
    }
    
    public static String demanderString(){
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();

    }
}
