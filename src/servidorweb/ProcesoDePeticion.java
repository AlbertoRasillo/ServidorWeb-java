
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
    static  String RUTAPRINC = "";
    static final private String DOCPRINC = "index.html";
    static final private String DOCERROR = "error.html";
    static private String cabecera = "HTTP/1.x ";
    static final private String cabeceraIndex = "HTTP/1.x " +"200 OK" + "\r\n" + "Transfer-Encoding: " + "\r\n" + "Date: " 
                            + "\r\n" + "Content-Type: text/html\r\n" + "\r\n";
    static final private String cabecera404 = "HTTP/1.x "+"404 No encontrado" + "\r\n" + "Transfer-Encoding: " + "\r\n" + "Date: " 
                            + "\r\n" + "Content-Type: text/html\r\n" + "\r\n";
    static final private String mensaje404 = "No se pudo enviar el mensaje de error 404";
    static final private String mensajeError404 = "No se pudo enviar el mensaje de error 404";
    static final private String cabecera200ConCookie = "HTTP/1.x " + "200 OK" + "\r\n" + "Transfer-Encoding: " + "\r\n" + "Date: ";
    static final private String cabecera200SinCookie = "HTTP/1.x " + "200 OK" + "\r\n" + "Transfer-Encoding: " + "\r\n" + 
                             "\r\n" + "Date:" + "\r\n" + "Set-Cookie: ";
    static final private String mensajeErrorIndex = "No se pudo enviar pagina index";
    static final private String mensajeError501 = "Se ha detectado un error 501"; 
    static final private String mensajeError200Cabecera  = "No se pudo enviar la cabecera de la respuesta correctamente";
    static final private String mensajeErrorEnviarFichero = "No se pudo enviar el contenido de la respuesta correctamente";
    static final private String etiquetaError = "Error: ";
    static final private String etiquetaCookie = "Cookie:";
    static final private String etiquetaTipMime = "Content-Type: ";
    static final private String saltoLinea = "\r\n";

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

        
        opPeticion = tipoPeticion(cabeceraNueva);
        //obtenemos el nombre recurso que solicita el cliente
        recursoSol = recursoSolicitado(cabeceraNueva);
        String [] rutaVirt = recursoToArray(recursoSol);
        DirectorioVirtual.cargarDirectorios();
        for( DirectorioVirtual dv: DirectorioVirtual.directorios )
        {
            if( dv.getNombre().equals(rutaVirt[0] ))
            {
                paginaPrincipal = dv.getDocumentoPrincipal();
                docError = dv.getPaginaError();
            }
        }
        if (Cliente.ExisteCliente(buscarCookie(cabeceraPeticion), Cliente.getClientesBaneados())) {
            //no mostramos pagina a baneados
        }
        else {
            if (buscarCookie(cabeceraPeticion)== null || Cliente.ExisteCliente(buscarCookie(cabeceraPeticion), Cliente.getClientes())){
                cabecera = crearCabecera(200, true) + saltoLinea;
            }
            else{
                
                cabecera = crearCabecera(200, false) + saltoLinea;
            }
            if(esIndex(recursoSol) == true) {
                try 
                {
                    ruta = RUTAPRINC + recursoSol;
                    fichero = new FileInputStream(ruta + paginaPrincipal);
                    salida.writeBytes(cabeceraIndex);
                    enviarFichero(opPeticion, fichero);               
                } 
                catch (Exception e) 
                {
                    System.err.println(mensajeErrorIndex);
                    System.err.println();
                }
            }
            else {
                try {
                    ruta = RUTAPRINC+recursoSol;
                    tipoArchivo = tipoArchivo(ruta);
                    cabecera = cabecera + tipoArchivo + saltoLinea;
                    fichero = new FileInputStream(ruta);
                    salida.writeBytes(cabecera);
                    enviarFichero(opPeticion, fichero);
                } catch (Exception e) {
                    fichero = new FileInputStream(RUTAPRINC+docError);
                    cabecera = cabecera404;
                    salida.writeBytes(cabecera);
                    enviarFichero(opPeticion, fichero);
                    //return;
                }
                
            }
        }
//        if(esIndex(recursoSol) == true)
//        {
//            try 
//            {
//                fichero = new FileInputStream(RUTAPRINC + paginaPrincipal);
//                salida.writeBytes(cabeceraIndex);
//                enviarFichero(opPeticion, fichero);               
//            } 
//            catch (Exception e) 
//            {
//                System.err.println(mensajeErrorIndex);
//                System.err.println();
//            }
//        }
//        else
//        {
//            ruta = RUTAPRINC+recursoSol;
//            try 
//            {
//                fichero = new FileInputStream(ruta);
//            } 
//            catch (Exception ex) 
//            {
//                System.err.println(etiquetaError + ex.getMessage());
//                System.err.println(mensaje404);
//                System.err.println();
//                try 
//                {   //mostramos el fichero de error 404 - pagina no encontrada
//                    fichero = new FileInputStream(RUTAPRINC+docError);
//                    cabecera1 = cabecera404;
//                    salida.writeBytes(cabecera1);
//                    enviarFichero(opPeticion, fichero);
//                    return;
//                } 
//                catch (Exception ex2) 
//                {
//                    System.err.println(etiquetaError + ex2.getMessage());
//                    System.err.println(mensajeError404);
//                    System.err.println();
//                }               
//            }
//            //este if creo que no hace falta
//            if (cabeceraPeticion != null)
//                {
//                if(buscarCookie(cabeceraPeticion)== null||
//                    Cliente.ExisteCliente(buscarCookie(cabeceraPeticion), Cliente.getClientes()))
//                {
//                    Cliente client = new Cliente();
//                    client.getCoockie();
//                    cabecera1 = cabecera200SinCookie + client.getCoockie();
//                    tipoArchivo = tipoArchivo(ruta);
//                    cabecera1 = cabecera1 + tipoArchivo + "\r\n";
//                }
//                else if(Cliente.ExisteCliente(buscarCookie(cabeceraPeticion), Cliente.getClientesBaneados()))
//                {
//                    //
//                }
//                else
//                {
//                    cabecera1 = cabecera200ConCookie;
//                    tipoArchivo = tipoArchivo(ruta);
//                    cabecera1 = cabecera1 + tipoArchivo + "\r\n";
//                }
//                 // Enviamos la cabecera1 y recurso 
//                try
//                {
//                    salida.writeBytes(cabecera1);
//                    enviarFichero(opPeticion, fichero);
//                }
//                catch(IOException ex)
//                {
//                    // Quizás no ha sido posible enviar la cabecera1
//                    System.err.println(etiquetaError + ex.getMessage());
//                    System.err.println(mensajeError200Cabecera);
//                    System.err.println();
//                    return; // No se debe seguir adelante
//                }
//            }
//        }    
        
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
                    System.err.println(etiquetaError + ex.getMessage());
                    System.err.println(mensajeErrorEnviarFichero);
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
                System.err.println(mensajeError501);
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
    
    public String tipoArchivo(String extension) throws IOException{
            extension = extension.toLowerCase();
            String tipoArchivo = null;
            for (TipoMIME MIME : TipoMIME.getTiposMime()){
                if (extension.endsWith(MIME.getExtension())){
                tipoArchivo = etiquetaTipMime + MIME.getContentType()+ saltoLinea;
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
        System.out.println();
        return campos[campos.length-1].equals("a"); 
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
            if(linea.startsWith(etiquetaCookie)){
                return linea;
            }
        }
        return null;
    }
    
    
    public String crearCabecera(int codigoCabecera, boolean cookie){
        String cabecera1 = null;
        
        /**
         * 
         * 200:OK
         * 404:No Encontrado
         * 501:No implementado
         * 
         */
        if(codigoCabecera == 404)
        {
            cabecera1 = cabecera404;
        }
        if(codigoCabecera == 200 && cookie == true)
        {
            cabecera1 = cabecera200ConCookie;
        }
        if(codigoCabecera == 200 && cookie == false)
        {
            Cliente client = new Cliente();
            client.getCoockie();
            cabecera1 = cabecera200SinCookie + client.getCoockie();
        }
        
//        switch (codigoRepuesta){
//            case 200:
//                cabecera1=cabecera1+"200 OK";
//                break;
//            case 404:
//                cabecera1=cabecera1+"404 No encontrado";
//                break;
//            case 501:
//                cabecera1=cabecera1+"501 No implementado";
//                break;
//        }
//        cabecera1 = cabecera1 + "\r\n";
//        cabecera1 = cabecera1 + "Transfer-Encoding: " + "\r\n";
//        cabecera1 = cabecera1 + "Date: " + "\r\n";
//        switch(tipoArchivo){
//            // El caso -1 no devuelve nada porque lo reservamos para errores
//            case -1:
//                break;
//            // MIME conocidos
//            case 1:
//                cabecera1=cabecera1+"Content-Type: text/html\r\n";
//                break;
//            case 2:
//                cabecera1=cabecera1+"Content-Type: text/plain\r\n";
//                break;
//            case 3:
//                cabecera1=cabecera1+"Content-Type: image/gif\r\n";
//                break;
//            case 4:
//                cabecera1=cabecera1+"Content-Type: image/jpeg\r\n";
//                break;
//            case 5:
//                cabecera1=cabecera1+"Content-Type: application/zip\r\n";
//                break;
//            // En casos de formatos desconocidos... (es decir, el caso 0)
//            case 0: default:
//                cabecera1=cabecera1+"Content-Type: application/octet-stream\r\n";
//                break;
//        }
        //cabecera=cabecera1+"\r\n";
        
        return cabecera1;
    }
    }

    

