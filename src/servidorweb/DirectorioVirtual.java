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
public class DirectorioVirtual {
    
    private static final String SEPARADOR  = ";"; 
     private static final String NOMFICHERO = "Directorios";
     
    private String nombre;
    private String documentoPrincipal;
    private String paginaError;
    public static ArrayList<DirectorioVirtual> directorios = new ArrayList<DirectorioVirtual>();

    public DirectorioVirtual(String nombre, String documentoPrincipal, String paginaError) {
        this.nombre = nombre;
        this.documentoPrincipal = documentoPrincipal;
        this.paginaError = paginaError;
    }
 
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDocumentoPrincipal() {
        return documentoPrincipal;
    }

    public void setDocumentoPrincipal(String documentoPrincipal) {
        this.documentoPrincipal = documentoPrincipal;
    }

    public String getPaginaError() {
        return paginaError;
    }

    public void setPaginaError(String paginaError) {
        this.paginaError = paginaError;
    }

    public ArrayList<DirectorioVirtual> getDirectorios() {
        return directorios;
    }

    public void setDirectorios(ArrayList<DirectorioVirtual> Directorios) {
        this.directorios = Directorios;
    }
    public String DirectorioVirtualToCSV(){
        StringBuffer sb = new StringBuffer();
        sb.append(this.nombre);
        sb.append(";");
        sb.append(this.documentoPrincipal);
        sb.append(";");
        sb.append(this.paginaError);
        sb.append(";");
        return sb.toString();   
    }
    
    public static void ArchivartiposMime(ArrayList<DirectorioVirtual> directorios){
        try{
            FileWriter fw = new FileWriter(NOMFICHERO, false); 
            
            for (DirectorioVirtual dv : directorios) {
                
                fw.write(dv.DirectorioVirtualToCSV());
                fw.write(13);
                fw.write(10);
                //--->
            }
            fw.close();    
        }catch(Exception ex){
            System.out.println(ex.toString());
        }
    }
     public static ArrayList<DirectorioVirtual> cargarDirectorios() throws FileNotFoundException, IOException{
        try{
            File f = new File(NOMFICHERO);
            if (f.exists()){
                FileReader fr = new FileReader(NOMFICHERO);
                BufferedReader br = new BufferedReader(fr);
                String linea ;
                
                while ((linea = br.readLine())!= null){
                    String tokens[] = linea.split(SEPARADOR);
                    DirectorioVirtual obj = new DirectorioVirtual(tokens[0], tokens[1],tokens[2]);
                    directorios.add(obj);
                }
                fr.close();
            }    
        } catch(Exception ex){
            System.out.println(ex.toString());
        }
        return directorios;
    }
    
    
    
}
