
package servidorweb;

import java.io.*;
import java.net.*;
import java.util.*;

public class ServidorWeb {
    static private Integer PUERTO = 9090;
    static private String RUTAPRINC = "C:\\Users\\Alberto\\Documents\\UNIVERSIDAD\\Ampliacion POO\\ServidorWeb\\";
    static private String DOCPRINC = "index.html";
    public static void main(String[] args) throws IOException {      
        ServerSocket socketServidor = new ServerSocket(PUERTO);

        while (true){
          try{
            //String rutaPrinc = muestraContenido(RUTAPRINC);
            Socket socketClien = socketServidor.accept();
            System.out.println("Un Cliente");
            OutputStream os = socketClien.getOutputStream();
            InputStream is = socketClien.getInputStream();
            byte[] buffer = new byte[4096];
            int nb = is.read(buffer);
            String res = new String(buffer);
            Hilo hilo = new Hilo(res, rutaPrinc);
            hilo.start();
            //System.out.println(Arrays.toString(datos));
            //System.out.println(datos[0]);
            
            StringBuffer sb = new StringBuffer();
            
            sb.append("HTTP/1.1 200 OK\n").append("Date: \n").append("Content-Type: text/html\n").append("Content-Length:"+rutaPrinc.length());
            sb.append("\n\n");
            
            sb.append(rutaPrinc);
            os.write(sb.toString().getBytes());
            
            socketClien.close();
            
          }
          catch(Exception ex)
          {
            System.out.println(ex.toString());
          }
        }
    }
    public static String muestraContenido(String archivo) throws FileNotFoundException, IOException {
      String linea, cadena;
      FileReader f = new FileReader(archivo);
      BufferedReader b = new BufferedReader(f);
      StringBuffer sb = new StringBuffer();
      while((linea = b.readLine())!=null) {
          sb.append(linea).append("\n");
      }
      cadena=sb.toString();
      return cadena;
      
      
    }

}
