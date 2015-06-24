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
import java.io.IOException;
import java.util.ArrayList;
import java.util.UUID;

/**
 *
 * @author Alberto
 */
public class Cliente {
    private static final String NOMFICHERO = "usuarios";
    private static final String SEPARADOR  = ";"; 
    private static ArrayList<Cliente> clientes = new ArrayList<Cliente>();
    private static ArrayList<Cliente> clientesBaneados = new ArrayList<Cliente>();;
    private String coockie;
    private String estado;
    
   public Cliente(){
       coockie = generarId();
       estado = "activo";
   }
   public Cliente(String cookie, String estado){
       this.coockie=cookie;
       this.estado=estado;
   }
   
   public String generarId(){
        String uuid = UUID.randomUUID().toString();
        return uuid;
   } 

    public String getCoockie() {
        return coockie;
    }

    public String getEstado() {
        return estado;
    }

    public void setCoockie(String coockie) {
        this.coockie = coockie;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
   public static ArrayList<Cliente> cargarUsuarios() throws FileNotFoundException, IOException{
        ArrayList<Cliente> lista = new ArrayList<Cliente>();
        try{
            File f = new File(NOMFICHERO);
            if (f.exists()){
                FileReader fr = new FileReader(NOMFICHERO);
                BufferedReader br = new BufferedReader(fr);
                String linea ;
                lista = new ArrayList<Cliente>();
                while ((linea = br.readLine())!= null){
                    String tokens[] = linea.split(SEPARADOR);
                    Cliente obj = new Cliente(tokens[0], tokens[1]);
                    if (tokens[1]== "activo"){
                        clientes.add(obj);
                    }
                    if (tokens[1]== "baneado"){
                        clientesBaneados.add(obj);
                    }
                    lista.add(obj);
                }
                fr.close();
            }    
        } catch(Exception ex){
            System.out.println(ex.toString());
        }
        return lista;
    }
  
}
