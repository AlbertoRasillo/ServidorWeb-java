
package servidorweb;

import java.io.*;
import java.net.*;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Alberto
 */
public class ProcesoDePeticion extends Thread{
    private Socket socket;
    private int timeout;
    //ruta relativa desde src/servidorweb/web/
    static  String RUTAPRINC = "web\\";
    static final private String DOCPRINC = "index.html";
    static private String DOCERROR = "error.html";
    ServidorWeb sw;
    BufferedReader entrada;
    DataOutputStream salida;
     ArrayList<String> cabeceraPeticion = new ArrayList<String>();
    
    public ProcesoDePeticion(Socket sock, ServidorWeb sw) throws IOException{
        socket = sock;
        this.sw = sw;
        entrada = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        this.start();
    }
    
    @Override
    public void run(){
        try {
            cabeceraPeticion= leerCabeceraPeticion(entrada);
             
        } catch (IOException ex) {
            Logger.getLogger(ProcesoDePeticion.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            respuesta(cabeceraPeticion.get(0));
        } catch (IOException ex) {
            Logger.getLogger(ProcesoDePeticion.class.getName()).log(Level.SEVERE, null, ex); 
        }
        try {
            entrada.close();
        } catch (IOException ex) {
            Logger.getLogger(ProcesoDePeticion.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            salida.close();
        } catch (IOException ex) {
            Logger.getLogger(ProcesoDePeticion.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            socket.close();
        } catch (IOException ex) {
            Logger.getLogger(ProcesoDePeticion.class.getName()).log(Level.SEVERE, null, ex);
        }
         
    }
    private void respuesta(String cabeceraNueva) throws IOException{
        salida = new DataOutputStream(socket.getOutputStream());
        String ruta = null;
        FileInputStream fichero = null;
        //String cabeceraNueva = null;
        String opPeticion = null;
        String recursoSol = null;
        String tipoArchivo = null;
        String cabecera="HTTP/1.x ";
        
        try {
            //cabeceraNueva = entrada.readLine();
            //System.out.println(peticion);
            entrada.readLine();
        } catch (Exception e) {
        }
        opPeticion = tipoPeticion(cabeceraNueva);
        //obtenemos el nombre recurso que solicita el cliente
        recursoSol = recursoSolicitado(cabeceraNueva);
            ruta = RUTAPRINC+recursoSol;
            try {
                fichero = new FileInputStream(ruta);
            } catch (Exception ex) {
                System.err.println("Error: "+ex.getMessage());
                System.err.println("Se ha detectado un error 404");
                System.err.println();
                try {
                    //mostramos el fichero de error 404 - pagina no encontrada
                    fichero = new FileInputStream(RUTAPRINC+DOCERROR);
                    cabecera=cabecera+"404 No encontrado" + "\r\n" + "Transfer-Encoding: " + "\r\n" + "Date: " + "\r\n" + "Content-Type: text/html\r\n" + "\r\n";
                    System.out.println(cabecera);
                    salida.writeBytes(cabecera);
                    enviarFichero(opPeticion, fichero);
                    return;
                } catch (Exception ex2) {
                        System.err.println("Error: "+ex2.getMessage());
                        System.err.println("No se pudo enviar el mensaje de error 404");
                        System.err.println();
                }
                
            }
            
            if (cabeceraPeticion != null){
                if(buscarCookie(cabeceraPeticion)== null||
                        Cliente.ExisteCliente(buscarCookie(cabeceraPeticion), Cliente.getClientes())){
                        Cliente client = new Cliente();
                        client.getCoockie();
                        cabecera=cabecera+"200 OK" + "\r\n" + "Transfer-Encoding: " + "\r\n" + 
                                "Set-Cookie: " + client.getCoockie() + "\r\n" + "Date:" + "\r\n";
                        tipoArchivo = tipoArchivo(ruta);
                        cabecera = cabecera + tipoArchivo +"\r\n";
                }
                else if(Cliente.ExisteCliente(buscarCookie(cabeceraPeticion), Cliente.getClientesBaneados())){
                    //
                }
                else{
                        cabecera=cabecera+"200 OK" + "\r\n" + "Transfer-Encoding: " + "\r\n" + "Date: " + "\r\n";
                        tipoArchivo = tipoArchivo(ruta);
                        cabecera = cabecera + tipoArchivo +"\r\n";
                }
                 // Enviamos la cabecera y recurso 
                    try{
                        salida.writeBytes(cabecera);
                        enviarFichero(opPeticion, fichero);
                    }catch(Exception ex){
                        // Quizás no ha sido posible enviar la cabecera
                        System.err.println("Error: "+ex.getMessage());
                        System.err.println("No se pudo enviar la cabecera de la respuesta correctamente");
                        System.err.println();
                        return; // No se debe seguir adelante
                    }
            }
            
        
    }
    private void enviarFichero(String opPeticion, FileInputStream fichero){
            // la peticion es GET, el contenido tambien
            if (opPeticion=="GET"){
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
    private String tipoPeticion(String peticion){
        
        String tipoPetic = peticion;
        String opPeticion = null;
        System.out.println(tipoPetic);
        if (tipoPetic.startsWith("HEAD")){
            opPeticion = "HEAD";
        }
        if (tipoPetic.startsWith("GET")){
            opPeticion = "GET";
        }
        //comprobar que funciona este error
        if (opPeticion == ""){
            try{
                System.err.println("Se ha detectado un error 501");
                System.err.println();
            }catch(Exception ex){
            }
        }
        return opPeticion;
    }
    
    private String tipoArchivo(String ruta){
            ruta = ruta.toLowerCase();
            String tipoArchivo = null;
            if (ruta.endsWith(".html")){
                tipoArchivo="Content-Type: text/html\r\n";
            }
            if (ruta.endsWith(".htm")){
                tipoArchivo="Content-Type: text/html\r\n";
            }
            if (ruta.endsWith(".txt")){
                tipoArchivo="Content-Type: text/plain\r\n";
            }
            if (ruta.endsWith(".gif")){
                tipoArchivo="Content-Type: image/gif\r\n";
            }
            if (ruta.endsWith(".jpg")){
                tipoArchivo="Content-Type: image/jpeg\r\n";
            }
            if (ruta.endsWith(".zip")){
                tipoArchivo="Content-Type: image/jpeg\r\n";
            }
            if (ruta.endsWith(".zip")){
                tipoArchivo="Content-Type: application/zip\r\n";
            }
            return tipoArchivo;
    }
    
    private String recursoSolicitado(String peticion){
            int inicio = 0;
            int fin = 0;
            String webPeti = null;
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
            webPeti = peticion.substring(inicio+2, fin);
            System.out.println(webPeti);
            return webPeti;
    }

    public ArrayList<String> leerCabeceraPeticion(BufferedReader entrada) throws IOException{
        ArrayList<String> cabeceraPeticion = new ArrayList<String>();
        String linea;
        for (int i=0; i<=7; i++){
            linea = entrada.readLine();
            System.out.println(linea);
            cabeceraPeticion.add(linea);
        }
        return cabeceraPeticion;
        
    }
    public String buscarCookie(ArrayList<String> cabeceraPeticion){
        for(String linea: cabeceraPeticion){
            if(linea.startsWith("Cookie:")){
                return linea;
            }
        }
        return null;
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
//        switch(tipoArchivo){
//            // El caso -1 no devuelve nada porque lo reservamos para errores
//            case -1:
//                break;
//            // MIME conocidos
//            case 1:
//                cabecera=cabecera+"Content-Type: text/html\r\n";
//                break;
//            case 2:
//                cabecera=cabecera+"Content-Type: text/plain\r\n";
//                break;
//            case 3:
//                cabecera=cabecera+"Content-Type: image/gif\r\n";
//                break;
//            case 4:
//                cabecera=cabecera+"Content-Type: image/jpeg\r\n";
//                break;
//            case 5:
//                cabecera=cabecera+"Content-Type: application/zip\r\n";
//                break;
//            // En casos de formatos desconocidos... (es decir, el caso 0)
//            case 0: default:
//                cabecera=cabecera+"Content-Type: application/octet-stream\r\n";
//                break;
//        }
        //cabecera=cabecera+"\r\n";
        
        return cabecera;
    }
    }

    

