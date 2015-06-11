/*
 * Alberto Rasillo
 */
package servidorweb;

import java.io.*;
import java.net.*;
import java.util.*;

public class ServidorWeb {
    static private Integer PUERTO = 9090;
    public static void main(String[] args) throws IOException {      
        ServerSocket socketServidor = new ServerSocket(PUERTO);

        while (true){
          try{
            //String rutaPrinc = muestraContenido(RUTAPRINC);
            Socket socketClien = socketServidor.accept();
            Hilo hilo = new Hilo(socketClien);
            hilo.start();
            
          }
          catch(Exception ex)
          {
            System.out.println(ex.toString());
          }
        }
    }

}
