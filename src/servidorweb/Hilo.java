/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servidorweb;

import java.io.*;
import java.util.*;

/**
 *
 * @author Alberto
 */
public class Hilo extends Thread{
    private String res;
    private String[] parts;
    private String rutaPeti;
    
    Hilo(String res, String rutaPrinc) {
       this.res = res;
       this.rutaPeti = rutaPrinc;
    }
    
    @Override
    public void run(){
    // Devuelve la cabecera de la solicitud completa del cliente
        parts = this.res.split("[\\r\\n]+");
        System.out.println(parts[0]);
    }
    public String nuevaRuta(String rutaPeti){
            if(parts[0].equals("GET / HTTP/1.1") == false){
            this.rutaPeti = this.rutaPeti+parts[0];
        }
            return this.rutaPeti;
    }
    
}
