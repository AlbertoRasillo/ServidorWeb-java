/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servidorweb;

import java.io.FileWriter;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * Alberto Rasillo
 */
public class ServidorWeb extends Thread {
    private final int PUERTO=9999;
    private String estado;
    private int NUMCLIENTES = 50;
    private int Comparador;
    ServerSocket socketServidor;
    private ExecutorService exec=Executors.newFixedThreadPool(NUMCLIENTES);
    private static ArrayList<String> tiposMime = new ArrayList<String>();
    private static final String NOMFICHERO = "tiposmime";

    
    public ServidorWeb() throws IOException{
       socketServidor= new ServerSocket(PUERTO);
       this.start();
    };

    public ExecutorService getExec() {
        return exec;
    }
    
    
    public void run(){
        //exec = Executors.newFixedThreadPool(NUMCLIENTES);
        try {
            while (true){
    //            if(this.contadorPeticiones<NºClientes){
                    if (NUMCLIENTES!=Comparador){
                        exec=Executors.newFixedThreadPool(Comparador);
                        NUMCLIENTES=Comparador;
                    }
                    try {
                        Socket socketClien = socketServidor.accept();
                        System.out.println(socketClien.getInetAddress().toString());
                        exec.execute (new ProcesoDePeticion(socketClien));
                        // ProcesoDePeticion procesoDePeticion = new ProcesoDePeticion(socketClien);
                        System.out.println("hilo servidor web");

                    } catch (IOException ex) {
                        Logger.getLogger(ServidorWeb.class.getName()).log(Level.SEVERE, null, ex);
                    }
                //}
            } 
        }
        finally {
            exec.shutdown();
        }
    }

    public void setComparador(int Comparador) {
        this.Comparador = Comparador;
    }
    
    public int getPUERTO() {
        return PUERTO;
    }

    public String getEstado() {
        return estado;
    }

    public int getNUMCLIENTES() {
        return NUMCLIENTES;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public void setNUMCLIENTES(int NUMCLIENTES) {
        this.NUMCLIENTES = NUMCLIENTES;
    }
    public void pararServidor(ServidorWeb sw){
        //interrupt o stop???
        sw.interrupt();
    }

    public static void setTiposMime(ArrayList<String> tiposMime) {
        ServidorWeb.tiposMime = tiposMime;
    }

    public static void tiposMime(ArrayList<String> mimes){
        try{
            FileWriter fw = new FileWriter(NOMFICHERO, false); 
            
            for (String mime : mimes) {
                fw.write(mime);
                fw.write(13);
                fw.write(10);
                //--->
            }
            fw.close();    
        }catch(Exception ex){
            System.out.println(ex.toString());
        }
    }
  
}
    

