/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bayart.ayouaz.huit.americain.Model;

import static bayart.ayouaz.huit.americain.Model.Partie.CLAVIER;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 *
 * @author ed
 */
public class Input {
    public static Scanner CLAVIER = new Scanner(System.in);
    
    public static int demanderEntier(int min, int max){
        int index;
        while(true){
            try {
                index = CLAVIER.nextInt();
            if(index>=0 && index<3){
                return index;
            }

            } catch(InputMismatchException e) {
                Partie.CLAVIER.next();
            }
        }
    }
}
