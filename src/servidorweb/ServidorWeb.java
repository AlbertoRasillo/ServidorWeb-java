
package servidorweb;

import java.io.*;
import java.net.*;
import java.util.*;

public class ServidorWeb {

    public static void main(String[] args) throws IOException {
       
        ServerSocket ss = null;
        ss = new ServerSocket(9090);

        while (true){
          try{
            String documento = muestraContenido("C:\\Users\\Alberto\\Documents\\UNIVERSIDAD\\Ampliacion POO\\ServidorWeb\\index.html");
            Socket s = ss.accept();
            System.out.println("Un Cliente");
            OutputStream os = s.getOutputStream();
            InputStream is = s.getInputStream();
            byte[] buffer = new byte[4096];
            int nb = is.read(buffer);
            //System.out.println("Recibo esto cliente: "+nb+"/n/n");
            String res = new String(buffer);
            //System.out.println(res);
            String[] datos = GetPeticion.getPeticion(res);
              /*for (int i = 0; i < datos.length; i++) {
                 System.out.println(datos[i]);
              }*/
              
            System.out.println(Arrays.toString(datos));
            //System.out.println(datos[0]);
            StringBuffer sb = new StringBuffer();
            
            sb.append("HTTP/1.1 200 OK\n").append("Date: \n").append("Content-Type: text/html\n").append("Content-Length:"+documento.length());
            sb.append("\n\n");
            sb.append(documento);
            //sb.append("<html><body><B>Hola<BR>Mundo</B></body></html>");
            os.write(sb.toString().getBytes());
            
            s.close();
            
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
