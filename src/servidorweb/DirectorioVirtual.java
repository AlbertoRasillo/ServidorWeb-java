/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servidorweb;

import java.util.ArrayList;

/**
 *
 * @author Axtro
 */
public class DirectorioVirtual {
    private String nombre;
    private String documentoPrincipal;
    private String páginaError;
    private ArrayList<DirectorioVirtual> Directorios = new ArrayList<DirectorioVirtual>();

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

    public String getPáginaError() {
        return páginaError;
    }

    public void setPáginaError(String páginaError) {
        this.páginaError = páginaError;
    }

    public ArrayList<DirectorioVirtual> getDirectorios() {
        return Directorios;
    }

    public void setDirectorios(ArrayList<DirectorioVirtual> Directorios) {
        this.Directorios = Directorios;
    }
    
    
    
    
}
