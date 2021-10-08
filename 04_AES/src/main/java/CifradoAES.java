/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Administrador
 */

import java.io.*;
import java.util.*;
public class CifradoAES {
    
    public static void main(String[] args) throws Exception{
        
        String mensaje = "Camara mis perros, guiño guiño, ya se la saben, cartera y celular en mano";
        
        String mensajecifrado = AES.encrypt(mensaje);
        String mensajedescifrado = AES.decrypt(mensajecifrado);
        
        
        System.out.println("El mensaje original es: " + mensaje);
        System.out.println("El mensaje cifrado es: " + mensajecifrado);
        System.out.println("El mensaje descifrado es: " + mensajedescifrado);
        
    }
}
