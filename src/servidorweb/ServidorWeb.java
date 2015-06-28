/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servidorweb;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * Alberto Rasillo
 */
public class ServidorWeb extends Thread {
    private final int PUERTO=9999;
    private String estado;
    private static int NUMCLIENTESDEF = 50;
    ServerSocket socketServidor;
    private static ExecutorService exec;
   
   

    
    public ServidorWeb() throws IOException {
       socketServidor= new ServerSocket(PUERTO);
       this.start();
    };

    public static ExecutorService getExec() {
        return exec;
    }

    public static void setExec(ExecutorService exec) {
        ServidorWeb.exec = exec;
    }
    
    
    public void run() {

        try {
            while (true) {
                try {
                    Socket socketClien = socketServidor.accept();
                    System.out.println(socketClien.getInetAddress().toString());
                    exec.execute (new ProcesoDePeticion(socketClien));
                     ProcesoDePeticion procesoDePeticion = new ProcesoDePeticion(socketClien);
                    System.out.println("hilo servidor web");
                }catch (IOException ex) {   
                    Logger.getLogger(ServidorWeb.class.getName()).log(Level.SEVERE, null, ex);
                }
            } 
        }
        finally {
            exec.shutdown();
        }
    }

    public int getPUERTO() {
        return PUERTO;
    }

    public String getEstado() {
        return estado;
    }

    public static int getNUMCLIENTES() {
        return NUMCLIENTESDEF;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public void setNUMCLIENTES(int NUMCLIENTES) {
        this.NUMCLIENTESDEF = NUMCLIENTES;
    }
    
    public void pararServidor(ServidorWeb sw) {
        sw.interrupt();
    }
}
    

