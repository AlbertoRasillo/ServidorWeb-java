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
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.UUID;

/**
 *
 * @author Alberto
 */
public class Cliente {
    
    private static final String NOMFICHERO = "usuarios";
    private static final String SEPARADOR  = ";"; 
    private static final String BANEADO ="baneado";
    private static final String ACTIVO ="activo";
    private static final String FORMATOFECHA = "dd-MMM-yyyy HH:mm:ss";
    
    private String fechaExpiracion;
    private String coockie;
    private String estado;
    
    private static ArrayList<Cliente> clientes = new ArrayList<Cliente>();
    private static ArrayList<Cliente> clientesBaneados = new ArrayList<Cliente>();;

    
   public Cliente(){
       coockie = generarId();
       estado = ACTIVO;
       fechaExpiracion= calcularFechaExpiracion();
       this.clientes.add(this);
   }
   public Cliente(String cookie, String estado, String fechaExpiracion){
       this.coockie=cookie;
       this.estado=estado;
       this.fechaExpiracion=fechaExpiracion;
   }
   
   public String generarId(){
        String uuid = UUID.randomUUID().toString();
        return uuid;
   } 

    public String getCoockie() {
        return coockie;
    }
    public String getfechaExpiracion() {
        return fechaExpiracion;
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

    public String getFechaExpiracion() {
        return fechaExpiracion;
    }

    public static ArrayList<Cliente> getClientes() {
        return clientes;
    }

    public static ArrayList<Cliente> getClientesBaneados() {
        return clientesBaneados;
    }
    
    public static ArrayList<Cliente> cargarClientes() throws FileNotFoundException, IOException{
        try{
            File f = new File(NOMFICHERO);
            if (f.exists()){
                FileReader fr = new FileReader(NOMFICHERO);
                BufferedReader br = new BufferedReader(fr);
                String linea ;
                
                while ((linea = br.readLine())!= null){
                    String tokens[] = linea.split(SEPARADOR);
                    Cliente obj = new Cliente(tokens[0], tokens[1],tokens[2]);
                    System.out.println(tokens[1]);
                    if (tokens[1].equals(BANEADO)){
                        clientesBaneados.add(obj);
                    }
                    clientes.add(obj);
                }
                fr.close();
            }    
        } catch(Exception ex){
            System.out.println(ex.toString());
        }
        return clientes;
    }
    
    
    public String cliente2CSV(){
        StringBuffer sb = new StringBuffer();
        sb.append(this.coockie);
        sb.append(";");
        sb.append(this.estado);
        sb.append(";");
        sb.append(this.fechaExpiracion);
        sb.append(";");
        return sb.toString();   
    }
    
    
    public static void ArchivarClientes(ArrayList<Cliente> clientes){
        try{
            FileWriter fw = new FileWriter(NOMFICHERO, false); 
            
            for (Cliente cliente : clientes) {
                fw.write(cliente.cliente2CSV());
                fw.write(13);
                fw.write(10);
                //--->
            }
            fw.close();    
        }catch(Exception ex){
            System.out.println(ex.toString());
        }
    }
    
    public static boolean ExisteCliente(String coockie, ArrayList<Cliente> clientes){
        boolean comprobar = false;
        for(Cliente c1 : clientes){
            if(c1.coockie.equals(coockie)){
                comprobar = true;
            }
        }
        return comprobar;
    }
    public static Cliente StringToCliente(String coockie){
        for(Cliente c1 : clientes){
             if(c1.coockie.equals(coockie)){
                 return c1;   
            }
        }
        return null;
    }           
    public String PaginaCliente2CSV(String cliente, String cabecera){
        StringBuffer sb = new StringBuffer();
        sb.append(cliente);
        sb.append(";");
        sb.append(cabecera);
        sb.append(";");
        return sb.toString();   
    }
    
     public static void ArchivarPeticionCliente(ArrayList<Cliente> clientes){
        try{
            FileWriter fw = new FileWriter(NOMFICHERO, false); 
            
            for (Cliente cliente : clientes) {
                fw.write(cliente.cliente2CSV());
                fw.write(13);
                fw.write(10);
                //--->
            }
            fw.close();    
        }catch(Exception ex){
            System.out.println(ex.toString());
        }
    }
      
    public static String calcularFechaExpiracion() {
        Calendar c1 = GregorianCalendar.getInstance();
        c1.add(Calendar.DATE, 7);
        return c1.getTime().toLocaleString();
    }
 
    public static Calendar stringToCalendar(String fecha) throws ParseException {
        DateFormat df =  new  SimpleDateFormat ( FORMATOFECHA ); 
        Calendar cal   =  Calendar.getInstance(); 
        cal . setTime ( df . parse ( fecha ));
        return cal;
    }
      
    public static void elimianrExpirados(ArrayList<Cliente> clientes) throws ParseException {
        Calendar c1 = GregorianCalendar.getInstance();
        for(Cliente cli: clientes){
            Calendar c2 = stringToCalendar(cli.fechaExpiracion);
            if (c1.compareTo(c2) < 0){
                clientes.remove(cli);
                clientesBaneados.remove(cli);
            }
        }
    }
 
    public static boolean banear(Cliente cli) {
        if(ExisteCliente(cli.coockie, clientes)) {
            cli.setEstado(BANEADO);
            clientesBaneados.add(cli);
            return true;
        }
        return false;
    } 
}
