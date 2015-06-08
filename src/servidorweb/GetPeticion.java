/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servidorweb;

import java.io.*;

/**
 *
 * @author Alberto
 */
public class GetPeticion {
        // Devuelve la cabecera de la solicitud completa del cliente al
    // método main de nuestro servidor
    public static String[] getPeticion( String sin ) throws IOException {
        
        String[] parts = sin.split("[\\r\\n]+");
            
        return parts;

            
        }
    
}
