/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servidorweb;

import java.text.ParseException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Axtro
 */
public class TareasSegundoPlano extends Thread{
    public TareasSegundoPlano(){
        this.start();
        
    }
    
    
    @Override
    public void run(){
        while (true){
            try {
                Thread.sleep(60*60*1000); //1 hora
            } catch (InterruptedException ex) {
                Logger.getLogger(TareasSegundoPlano.class.getName()).log(Level.SEVERE, null, ex);
            }
            try {
                Cliente.elimianrExpirados(Cliente.getClientes());
            } catch (ParseException ex) {
                Logger.getLogger(TareasSegundoPlano.class.getName()).log(Level.SEVERE, null, ex);
            }
            Cliente.ArchivarClientes(Cliente.getClientes());
            
        } 
    }
}
