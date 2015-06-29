
package servidorweb;

import java.io.*;
import java.net.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Alberto
 */
public class ProcesoDePeticion implements Runnable { 
    private Socket socket;
    //ruta relativa desde src/servidorweb/web/
    static  String RUTAPRINC = "";
    static final private String recursoBaneado = "baneado.html";
    static private String cabecera = "HTTP/1.x ";
    static final private String cabeceraIndex = "HTTP/1.x " +"200 OK" + "\r\n" + "Transfer-Encoding: " + "\r\n" + "Date: " 
                                                    + "\r\n" + "Content-Type: text/html\r\n" + "\r\n";
    
    static final private String cabeceraBaneado = "HTTP/1.x " +"200 OK" + "\r\n" + "Transfer-Encoding: " + "\r\n" + "Date: " 
                                                    + "\r\n" + "Content-Type: text/html\r\n" + "\r\n";
    
    static final private String cabecera404 = "HTTP/1.x "+"404 No encontrado" + "\r\n" + "Transfer-Encoding: " + "\r\n" + "Date: " 
                                                    + "\r\n" + "Content-Type: text/html\r\n" + "\r\n";
    
    static final private String cabeceraError501 = "HTTP/1.x " +"501 No implementado" + "\r\n" + "Transfer-Encoding: " + "\r\n" + "Date: " 
                                                    + "\r\n" + "Content-Type: text/html\r\n" + "\r\n";
    
    static final private String mensaje404 = "No se pudo enviar el mensaje de error 404";
    static final private String mensajeError404 = "No se pudo enviar el mensaje de error 404";
    
    static final private String cabecera200ConCookie = "HTTP/1.x " + "200 OK" + "\r\n" + "Transfer-Encoding: chunked" + "\r\n" + "Content-Encoding: gzip,deflate" + "\r\n" + "Date: ";
    
    static final private String cabecera200SinCookie = "HTTP/1.x " + "200 OK" + "\r\n" + "Transfer-Encoding: chunked" + "\r\n" + "Content-Encoding: gzip,deflate" + "\r\n" 
                                                       + "Date:" + "\r\n" + "Set-Cookie: ";
    
    static final private String mensajeErrorIndex = "No se pudo enviar pagina index";
    static final private String mensajeErrorBaneado = "No se pudo enviar pagina baneado";
    static final private String mensajeError501 = "Se ha detectado un error 501"; 
    static final private String mensajeError200Cabecera  = "No se pudo enviar la cabecera de la respuesta correctamente";
    static final private String mensajeErrorEnviarFichero = "No se pudo enviar el contenido de la respuesta correctamente";
    static final private String etiquetaError = "Error: ";
    static final private String etiquetaCookie = "Cookie:";
    static final private String etiquetaTipMime = "Content-Type: ";
    static final private String saltoLinea = "\r\n";
    static final private String barra = "/";
    private static final String FORMATOFECHA ="Wdy, DD-Mon-YYYY HH:MM:SS GMT";

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
        String nombreDV = null;
        opPeticion = tipoPeticion(cabeceraNueva);
        //obtenemos el nombre recurso que solicita el cliente
        recursoSol = recursoSolicitado(cabeceraNueva);
        
        nombreDV = buscarNombeDirVir(recursoSol);
        paginaPrincipal = buscaPaginaPrincipal(recursoSol);
        docError = buscaPaginaError(recursoSol);
        

        if (Cliente.ExisteCliente(buscarCookie(cabeceraPeticion), Cliente.getClientesBaneados())) {
            try {
                ruta = RUTAPRINC + recursoBaneado;
                fichero = new FileInputStream(ruta);
                salida.writeBytes(cabeceraBaneado);
                enviarFichero(opPeticion, fichero);          
            } catch (Exception e) {
                System.err.println(mensajeErrorBaneado);
                System.err.println();
            }
        }
        
        
        else {
            //No tiene cookie
            if (buscarCookie(cabeceraPeticion)== null || Cliente.ExisteCliente(buscarCookie(cabeceraPeticion), Cliente.getClientes())==false){
                cabecera = crearCabecera(200, false) + saltoLinea;  
            }
            //Tiene cookie
            else{
                
                cabecera = crearCabecera(200, true) + saltoLinea;
            }
            if(esIndex(recursoSol) == true) {
                try 
                {
                    ruta = RUTAPRINC + recursoSol;
                    fichero = new FileInputStream(ruta + paginaPrincipal);
                    salida.writeBytes(cabecera);
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
                    fichero = new FileInputStream(nombreDV+barra+docError);
                    cabecera = cabecera404;
                    salida.writeBytes(cabecera);
                    enviarFichero(opPeticion, fichero);
                    //return;
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
                    System.err.println(etiquetaError + ex.getMessage());
                    System.err.println(mensajeErrorEnviarFichero);
                    System.err.println();
                }
            }
    }
    
    public String tipoPeticion(String peticion){
        
        String tipoPetic = peticion;
        String opPeticion = null;
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
    
    
    public String buscarNombeDirVir(String recursoSol){
        String nombre = null;
        String [] rutaVirt = recursoToArray(recursoSol);
        for( DirectorioVirtual dv: DirectorioVirtual.directorios )
        {
            if( dv.getNombre().equals(rutaVirt[0] ))
            {
                nombre = dv.getNombre();
            }
        }
        return nombre;
    }
    
    public String buscaPaginaPrincipal(String recursoSol){
        String paginaPrincipal = null;
        String [] rutaVirt = recursoToArray(recursoSol);
        for( DirectorioVirtual dv: DirectorioVirtual.directorios )
        {
            if( dv.getNombre().equals(rutaVirt[0] ))
            {
                paginaPrincipal = dv.getDocumentoPrincipal();
            }
        }
        return paginaPrincipal;
    }
    
    public String buscaPaginaError(String recursoSol){
        String docError = null;
        String [] rutaVirt = recursoToArray(recursoSol);
        for( DirectorioVirtual dv: DirectorioVirtual.directorios )
        {
            if( dv.getNombre().equals(rutaVirt[0] ))
            {
                docError = dv.getPaginaError();
            }
        }
        return docError;
    }
    
    
    public String tipoArchivo(String extension) throws IOException{
            extension = extension.toLowerCase();
            String tipoArchivo = null;
            for (TipoMIME MIME : TipoMIME.getTiposMime()){
                if (extension.endsWith(MIME.getExtension())){
                tipoArchivo = etiquetaTipMime + MIME.getContentType()+ saltoLinea;
            }
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
                cabeceraPeticion.add(linea);
            }
        return cabeceraPeticion;
        
    }
    
    
    public String buscarCookie(ArrayList<String> cabeceraPeticion){
        
        for(String linea: cabeceraPeticion){
            if(linea.startsWith(etiquetaCookie)){               
           
                String[] cookie = linea.split(" ");
                return cookie[1];
            }
        }
        return null;
    }
    
    public String calcularfechaExpiracion(){
            DateFormat df =  new  SimpleDateFormat ( "dd MMM yyyy kk:mm:ss z" ); 
            Calendar cal   =  Calendar.getInstance(); 
            cal.add(Calendar.DATE, 7);
            String fecha =(cal.getTime().toGMTString());
            return fecha;
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
            cabecera1 = cabecera1 + "; Expires=" + calcularfechaExpiracion();
        }
//        else{
//            cabecera1 =  cabeceraError501;
//        }
        return cabecera1;
    }
}

    

