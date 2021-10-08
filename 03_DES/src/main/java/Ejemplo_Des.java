/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Administrador
 * 
 * Cifrado simetrico
 * Modo de bloque de 64 bits
 * Maneja una llave privada de 64 bits => 8 
 */

import javax.crypto.*;
import javax.crypto.spec.*;
//genera llaves 


import java.security.*;
//todos los tipos de algortmo cifrado


import java.io.*;

public class Ejemplo_Des {
     public static void main(String[] args) throws Exception{
         // comprobar argumento del archivo
         
        
         
         /* Lo primero que tenems que hacer es cargar una instancia del proveedor 
         del cifrado que vamos a ocupar en esa ocacion vamos a ocupar un cifrado DES 
        
         */
         
         System.out.println("1.- Generar clave DES: ");
         
         
         //La clase se encargara de generar llaves
         KeyGenerator generadorDES = KeyGenerator.getInstance("DES");
         
         //inicializar el tamaño de la llave del generador
         generadorDES.init(56); //56 bits
         
         //Generar la clave
         
         SecretKey clave = generadorDES.generateKey(); // es generar las 16 subllaves
         
         System.out.println("Clave es: " + clave);
         
         
         // la llave no tiene formato, como tal son los bits en bruto
         mostrarBytes(clave.getEncoded());
         
         
         //algoritmo
         /* El tipo de algoritmo que vamos a ocupar
         El modo del cifrado de dicho algoritmo
         Si exixste o no alguna norma para dicho algoritmo, PKCS (estandares)
         */
         
         Cipher cifrado = Cipher.getInstance("DES/ECB/PKCS5Padding");
         
         /*
         Vamos a ocpuar una instancia del algoritmo DES en el modo de cifrado por bloques electronico
         vamos a aplicar PKCS5 utilizando el relleno para los bloques
         
         Algoritmo: DES
         MODO: ECB
         RELLENO : PKCS5 
         */
         
         System.out.println("2.- Cifrar con DES el archivo: " + args[0] + ", dejar el resultado en : " + args[0]+".cifrado");
         
         cifrado.init(Cipher.ENCRYPT_MODE, clave);
         
         /*
         El problema es como vamos a leer los bloques
         buffer para entrada y salida de los bits
         */
         
         
         byte[] buffer = new byte[1000]; // leer cada mil
         byte[] bufferCifrado;
         
         
         // generar mis archivos
         
         FileInputStream in = new FileInputStream(args[0]);
         FileOutputStream out = new FileOutputStream(args[0]+".cifrado");
         
         //lectura
         
         int bytesleidos = in.read(buffer, 0, 1000);
         // mientras no este al final del archivo
         
         while(bytesleidos != -1){
             //pasar el texto claro leido al cifrador
             
             bufferCifrado = cifrado.update(buffer, 0, bytesleidos);
             //generar la salida
             out.write(bufferCifrado);
             bytesleidos = in.read(buffer, 0, 1000);
         }
         // vamos a reunir todos los bloques del cifrado
         bufferCifrado = cifrado.doFinal();
         // ya puedo escribir el archivo cifrado
         
         out.write(bufferCifrado);
         
         in.close();
         out.close();
         // vamos a descifrar
         System.out.println("3.- Descifrar con DES el archivo: " + args[0] + 
                 ".cifrado " +",vamos a ver el resultado en"
                 + "el archivo: " +args[0]+".descifrado");
         
         cifrado.init(Cipher.DECRYPT_MODE, clave);
         
        
         byte[] bufferPlano;
         
         
         // generar mis archivos
         
         in = new FileInputStream(args[0]+".cifrado");
         out = new FileOutputStream(args[0]+".descifrado");
         
         //lectura
         
         bytesleidos = in.read(buffer, 0, 1000);
         // mientras no este al final del archivo
         
         while(bytesleidos != -1){
             //pasar el texto claro leido al cifrador
             
             bufferPlano = cifrado.update(buffer, 0, bytesleidos);
             //generar la salida
             out.write(bufferPlano);
             bytesleidos = in.read(buffer, 0, 1000);
         }
         // vamos a reunir todos los bloques del cifrado
         bufferPlano = cifrado.doFinal();
         // ya puedo escribir el archivo cifrado
         
         out.write(bufferPlano);
         
         in.close();
         out.close();
         
         
         
         
     }

    public static void mostrarBytes(byte[] buffer) {
        System.out.write(buffer, 0, buffer.length);
    }

    public static void mensajeAyuda() {
        System.out.println("Ejemplo de cifrado DES para archivos .txt");
        System.out.println("Cuidado con la llave solo debe de ser de 8 caracteres");
        System.out.println("Compruebe que cargo el archivo para cifrar, sino no servira");
        System.out.println("A mimir");
    }
}
