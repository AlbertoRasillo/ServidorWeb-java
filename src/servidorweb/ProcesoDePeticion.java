
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
public class ProcesoDePeticion implements Runnable { // extends Thread{
    private Socket socket;
    private int timeout;
    //ruta relativa desde src/servidorweb/web/
    static  String RUTAPRINC = "web";
    static final private String DOCPRINC = "/index.html";
    static private String DOCERROR = "error.html";

    BufferedReader entrada;
    DataOutputStream salida;
    ArrayList<String> cabeceraPeticion = new ArrayList<String>();
    
    public ProcesoDePeticion(Socket sock) throws IOException{
        socket = sock;
        entrada = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        // this.start();
    }
    
    @Override
    public void run(){
        try {
                cabeceraPeticion= cabeceraToArray(entrada);

            } catch (IOException ex) {
                Logger.getLogger(ProcesoDePeticion.class.getName()).log(Level.SEVERE, null, ex);
            }
        if(cabeceraPeticion.get(0)!=null){
            try {
                respuesta(cabeceraPeticion.get(0));
            } catch (IOException ex) {
                Logger.getLogger(ProcesoDePeticion.class.getName()).log(Level.SEVERE, null, ex); 
            }            
        }
        try {
                entrada.close();
            } catch (IOException ex) {
                Logger.getLogger(ProcesoDePeticion.class.getName()).log(Level.SEVERE, null, ex);
            }
        if(cabeceraPeticion.get(0)!=null){
            try {
                salida.close();
            } catch (IOException ex) {
                Logger.getLogger(ProcesoDePeticion.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
            try {
                socket.close();
            } catch (IOException ex) {
                Logger.getLogger(ProcesoDePeticion.class.getName()).log(Level.SEVERE, null, ex);
            }
         
    }
    public void respuesta(String cabeceraNueva) throws IOException{
        salida = new DataOutputStream(socket.getOutputStream());
        String ruta = null;
        FileInputStream fichero = null;
        //String cabeceraNueva = null;
        String opPeticion = null;
        String recursoSol = null;
        String tipoArchivo = null;
        String paginaPrincipal = null;
        String docError = null;
        String cabecera="HTTP/1.x ";
        String cabeceraIndex="200 OK" + "\r\n" + "Transfer-Encoding: " + "\r\n" + "Date: " 
                            + "\r\n" + "Content-Type: text/html\r\n" + "\r\n";
        
        opPeticion = tipoPeticion(cabeceraNueva);
        //obtenemos el nombre recurso que solicita el cliente
        recursoSol = recursoSolicitado(cabeceraNueva);
        String [] rutaVirt = recursoToArray(recursoSol);
        for(DirectorioVirtual dv: DirectorioVirtual.Directorios ){
            if(dv.getNombre() == rutaVirt[0]){
                paginaPrincipal = dv.getDocumentoPrincipal();
                docError = dv.getPáginaError();
            }
        }
        if(esIndex(recursoSol)== true){
            try {
                fichero = new FileInputStream(RUTAPRINC+paginaPrincipal);
                salida.writeBytes(cabeceraIndex);
                enviarFichero(opPeticion, fichero);
            } catch (Exception e) {
                System.err.println("No se pudo enviar pagina index");
                System.err.println();
            }
        }else{
        
            ruta = RUTAPRINC+recursoSol;
            
            try {
                fichero = new FileInputStream(ruta);
            } catch (Exception ex) {
                System.err.println("Error: "+ex.getMessage());
                System.err.println("Se ha detectado un error 404");
                System.err.println();
                try {
                    //mostramos el fichero de error 404 - pagina no encontrada
                    fichero = new FileInputStream(RUTAPRINC+docError);
                    cabecera=cabecera+"404 No encontrado" + "\r\n" + "Transfer-Encoding: " + "\r\n" + "Date: " 
                            + "\r\n" + "Content-Type: text/html\r\n" + "\r\n";
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
                    }catch(IOException ex){
                        // Quizás no ha sido posible enviar la cabecera
                        System.err.println("Error: "+ex.getMessage());
                        System.err.println("No se pudo enviar la cabecera de la respuesta correctamente");
                        System.err.println();
                        return; // No se debe seguir adelante
                    }
            }
        }    
        
    }
    public void enviarFichero(String opPeticion, FileInputStream fichero){
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
    public String tipoPeticion(String peticion){
        
        String tipoPetic = peticion;
        String opPeticion = null;
        System.out.println(tipoPetic);
        if (tipoPetic == null){
        //falla mirar para coger head de otra manera
            try{
                opPeticion = "";
                System.err.println("Se ha detectado un error 501");
                System.err.println();
            }catch(Exception ex){
            }
        }
        else{
            if (tipoPetic.startsWith("HEAD")){
                opPeticion = "HEAD";
            }
            if (tipoPetic.startsWith("GET")){
                opPeticion = "GET";
            }
        //comprobar que funciona este error
        }
        return opPeticion;
    }   
    
    public String tipoArchivo(String extension){
            extension = extension.toLowerCase();
            String tipoArchivo = null;
            
            for (TipoMIME MIME : TipoMIME.getTiposMime()){
                if (extension.endsWith(MIME.getExtension())){
                tipoArchivo="Content-Type: " + MIME.getExtension() + "\r\n";
            }
                
//            if (extension.endsWith(".html")){
//                tipoArchivo="Content-Type: text/html\r\n";
//            }
//            if (extension.endsWith(".htm")){
//                tipoArchivo="Content-Type: text/html\r\n";
//            }
//            if (extension.endsWith(".txt")){
//                tipoArchivo="Content-Type: text/plain\r\n";
//            }
//            if (extension.endsWith(".gif")){
//                tipoArchivo="Content-Type: image/gif\r\n";
//            }
//            if (extension.endsWith(".jpg")){
//                tipoArchivo="Content-Type: image/jpeg\r\n";
//            }
//            if (extension.endsWith(".zip")){
//                tipoArchivo="Content-Type: image/jpeg\r\n";
//            }
//            if (extension.endsWith(".zip")){
//                tipoArchivo="Content-Type: application/zip\r\n";
//            }
        }
             return tipoArchivo;
    }
    
    String[] recursoToArray(String ruta){
        String[] campos = ruta.split("/");
        return campos;
    }
    
    public String recursoSolicitado(String cabeceraHttp){
        ArrayList<String> head = new ArrayList<String>();
        
            int inicio = 0;
            int fin = 0;
            String webPeti = null;
            for (int pos=0; pos<cabeceraHttp.length(); pos++){
                // Buscamos el ultimo espacio en blanco en la linea
                if (cabeceraHttp.charAt(pos)==' ' && inicio!=0){
                    fin=pos;
                    break;
                }
                // Buscamos el primer espacio en blanco en la linea
                if (cabeceraHttp.charAt(pos)==' ' && inicio==0){
                    inicio=pos;
                }
            }
            webPeti = cabeceraHttp.substring(inicio+2, fin);
            
            System.out.println(webPeti);
            return webPeti;
    }
    public boolean esIndex (String ruta){ 
        ruta+="a/";
        String[] campos = ruta.split("/");
        
            if (campos[campos.length-1]=="a"){
                return true;
            }
        return false; 
    }

    public ArrayList<String> cabeceraToArray(BufferedReader entrada) throws IOException{
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
    
    
    public String crearCabecera(int codigoRepuesta, int tipoArchivo){
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

    

