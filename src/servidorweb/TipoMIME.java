/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servidorweb;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author Axtro
 */
public class TipoMIME {
    
    private static final String SEPARADOR  = ";"; 
    
    private String extension;
    private String contentType;
    
    private static ArrayList<TipoMIME> tiposMime = new ArrayList<TipoMIME>();
    private static final String NOMFICHERO = "tiposmime.txt";

    public TipoMIME(String extension, String contentType) {
        this.extension = extension;
        this.contentType = contentType;
    }
      
     
    
    public static ArrayList<TipoMIME> getTiposMime() {
        return tiposMime;
    }

    public String getExtension() {
        return extension;
    }

    public String getContentType() {
        return contentType;
    }
      
    
    public String tipoMIMECSV(){
        StringBuffer sb = new StringBuffer();
        sb.append(this.extension);
        sb.append(";");
        sb.append(this.contentType);
        sb.append(";");
        return sb.toString();   
    }
    
    public static void ArchivartiposMime(ArrayList<TipoMIME> mimes){
        try{
            FileWriter fw = new FileWriter(NOMFICHERO, false); 
            
            for (TipoMIME mime : mimes) {
                
                fw.write(mime.tipoMIMECSV());
                fw.write(13);
                fw.write(10);
                //--->
            }
            fw.close();    
        }catch(Exception ex){
            System.out.println(ex.toString());
        }
    }
     public static ArrayList<TipoMIME> cargarClientes() throws FileNotFoundException, IOException{
        try{
            File f = new File(NOMFICHERO);
            if (f.exists()){
                FileReader fr = new FileReader(NOMFICHERO);
                BufferedReader br = new BufferedReader(fr);
                String linea ;
                
                while ((linea = br.readLine())!= null){
                    String tokens[] = linea.split(SEPARADOR);
                    TipoMIME obj = new TipoMIME(tokens[0], tokens[1]);
                    tiposMime.add(obj);
                }
                fr.close();
            }    
        } catch(Exception ex){
            System.out.println(ex.toString());
        }
        return tiposMime;
    }
}
