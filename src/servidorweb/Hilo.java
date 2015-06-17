
package servidorweb;

import java.io.*;
import java.net.*;
import java.util.*;

/**
 *
 * @author Alberto
 */
public class Hilo extends Thread{
    private Socket socket;
    private int timeout;
    static private String RUTAPRINC = "C:\\Users\\Alberto\\Documents\\UNIVERSIDAD\\Ampliacion POO\\ServidorWeb\\";
    static private String DOCPRINC = "index.html";
    static private String DOCERROR = "error.html";
    BufferedReader entrada;
    DataOutputStream salida;
    
    public Hilo(Socket sock){
        socket = sock;
    }
    
    @Override
    public void run(){
        try {
            entrada = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            salida = new DataOutputStream(socket.getOutputStream());
            respuesta(entrada,salida);
            entrada.close();
            salida.close();
        } catch (Exception e) {
        }

    }
    private void respuesta(BufferedReader entrada, DataOutputStream salida){
        int opPeticion = 0;
        String ruta;
        int tipoArchivo = 0;
        FileInputStream fichero = null;
        String peticion = null;
        
        try {
            peticion = entrada.readLine();
            entrada.readLine();
        } catch (Exception e) {
        }
        String tipoPetic = peticion.toUpperCase();
        System.out.println(tipoPetic);
        if (tipoPetic.startsWith("HEAD")){
            opPeticion = 1;
        }
        if (tipoPetic.startsWith("GET")){
            opPeticion = 2;
        }
        if (opPeticion == 0){
            try{
                System.err.println("Se ha detectado un error 501");
                System.err.println();
            }catch(Exception ex){
        }
        }else{
            //obtenemos el nombre recurso que solicita el cliente
            int inicio = 0;
            int fin = 0;
            for (int pos=0; pos<peticion.length(); pos++){
                // Buscamos el ultimo espacio en blanco en la linea
                if (peticion.charAt(pos)==' ' && inicio!=0){
                    fin=pos;
                    break;
                }
                // Buscamos el primer espacio en blanco en la linea
                if (peticion.charAt(pos)==' ' && inicio==0){
                    inicio=pos;
                }
            }
            String webPeti = peticion.substring(inicio+2, fin);
            ruta = RUTAPRINC+webPeti;
            try {
                fichero = new FileInputStream(ruta);
            } catch (Exception ex) {
                System.err.println("Error: "+ex.getMessage());
                System.err.println("Se ha detectado un error 404");
                System.err.println();
                try {
                    //mostramos el fichero de error 404 - pagina no encontrada
                    fichero = new FileInputStream(RUTAPRINC+DOCERROR);
                    salida.writeBytes(crearCabecera(404,1));
                    leerFichero(opPeticion, fichero);
                    return;
                } catch (Exception ex2) {
                        System.err.println("Error: "+ex2.getMessage());
                        System.err.println("No se pudo enviar el mensaje de error 404");
                        System.err.println();
                }
                
            }
            ruta = ruta.toLowerCase();
            if (ruta.endsWith(".htm")||ruta.endsWith(".html")){
                tipoArchivo=1;
            }
            if (ruta.endsWith(".txt")){
                tipoArchivo=2;
            }
            if (ruta.endsWith(".gif")){
                tipoArchivo=3;
            }
            if (ruta.endsWith(".jpg")||ruta.endsWith(".jpeg")){
                tipoArchivo=4;
            }
            if (ruta.endsWith(".zip")){
                tipoArchivo=5;
            }
            
            // Enviamos la cabecera y recurso 
            try{
                salida.writeBytes(crearCabecera(200,tipoArchivo));
                leerFichero(opPeticion, fichero);
            }catch(Exception ex){
                // Quizás no ha sido posible enviar la cabecera
                System.err.println("Error: "+ex.getMessage());
                System.err.println("No se pudo enviar la cabecera de la respuesta correctamente");
                System.err.println();
                return; // No se debe seguir adelante
            }
            
        }
    }
    private void leerFichero(int opPeticion, FileInputStream fichero){
            // la peticion es GET, el contenido tambien
            if (opPeticion==2){
                try{
                    while(true){
                        int b = fichero.read();
                        // Si llegamos al final del fichero, paramos el bucle
                        if (b==-1){
                            break;
                        }
                        salida.write(b);
                    }
                    fichero.close();
                }catch (Exception ex){
                    // Quizás no ha sido posible enviar el contenido
                    System.err.println("Error: "+ex.getMessage());
                    System.err.println("No se pudo enviar el contenido de la respuesta correctamente");
                    System.err.println();
                }
            }
    }
    
    private String crearCabecera(int codigoRepuesta, int tipoArchivo){
        String cabecera="HTTP/1.x ";
        
        /**
         * 
         * 200:OK
         * 404:No Encontrado
         * 501:No implementado
         * 
         */
        switch (codigoRepuesta){
            case 200:
                cabecera=cabecera+"200 OK";
                break;
            case 404:
                cabecera=cabecera+"404 No encontrado";
                break;
            case 501:
                cabecera=cabecera+"501 No implementado";
                break;
        }
        cabecera = cabecera + "\r\n";
        cabecera = cabecera + "Transfer-Encoding: " + "\r\n";
        cabecera = cabecera + "Date: " + "\r\n";
        switch(tipoArchivo){
            // El caso -1 no devuelve nada porque lo reservamos para errores
            case -1:
                break;
            // MIME conocidos
            case 1:
                cabecera=cabecera+"Content-Type: text/html\r\n";
                break;
            case 2:
                cabecera=cabecera+"Content-Type: text/plain\r\n";
                break;
            case 3:
                cabecera=cabecera+"Content-Type: image/gif\r\n";
                break;
            case 4:
                cabecera=cabecera+"Content-Type: image/jpeg\r\n";
                break;
            case 5:
                cabecera=cabecera+"Content-Type: application/zip\r\n";
                break;
            // En casos de formatos desconocidos... (es decir, el caso 0)
            case 0: default:
                cabecera=cabecera+"Content-Type: application/octet-stream\r\n";
                break;
        }
        cabecera=cabecera+"\r\n";
        
        return cabecera;
    }
    }

    

