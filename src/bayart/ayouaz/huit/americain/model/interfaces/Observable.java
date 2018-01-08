/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bayart.ayouaz.huit.americain.model.interfaces;

import java.util.ArrayList;

/**
 *
 * @author ed
 */
public class Observable {
    private ArrayList<Observer> liste;
    
    public Observable(){
        liste = new ArrayList<Observer>();
    }
    
    public void addObserver(Observer o){
        liste.add(o);
    }
    
    public void notifyObserver(){
        for(int i=0;i<liste.size();++i){
            liste.get(i).update(this, null);
        }
    }
    
    public void notifyObserver(Object o){
        for(int i=0;i<liste.size();++i){
            liste.get(i).update(this, o);
        }
    }
    
    public void removeObserver(Observer o){
        liste.remove(o);
    }
}
