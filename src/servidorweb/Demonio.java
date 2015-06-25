/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servidorweb;

/**
 *
 * @author Axtro
 */
public class Demonio implements Runnable{
    Thread t;
    public Demonio(){
        Thread t = new Thread(this);
        t.start();
    }
    @Override
    public void run(){
        while (true){
            
        } 
    }
}
