/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servidorweb;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * Alberto Rasillo
 */
public class ServidorWeb extends Thread {
    private final int PUERTO=9999;
    private String estado;
    private int NºClientes = 10;
    ServerSocket socketServidor;
    int contadorPeticiones;
    
    public ServidorWeb() throws IOException{
       socketServidor= new ServerSocket(PUERTO);
       this.contadorPeticiones = 0;
       this.start();
    };
    
    
    public void run(){
        while (!this.isInterrupted()){
            if(this.contadorPeticiones<NºClientes){
                try {
                    Socket socketClien = socketServidor.accept();
                    this.contadorPeticiones++;
                    System.out.println(this.contadorPeticiones);
                    ProcesoDePeticion hilo = new ProcesoDePeticion(socketClien,this);
                    System.out.println("hilo servidor web");
                    hilo.start();
                } catch (IOException ex) {
                    Logger.getLogger(ServidorWeb.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (Thread.currentThread().isInterrupted()) {
                    // cleanup and stop execution
                    // for example a break in a loop
            }
        }    
    }
    
    public int getPUERTO() {
        return PUERTO;
    }

    public String getEstado() {
        return estado;
    }

    public int getNºClientes() {
        return NºClientes;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public void setNºClientes(int NºClientes) {
        this.NºClientes = NºClientes;
    }
    public void pararServidor(ServidorWeb sw){
        //interrupt o stop???
        sw.interrupt();
    }
  
}
    

