/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Administrador
 */
import java.security.*;

// vamos a usar spec para la generacion de la subllave
import javax.crypto.*;
import javax.crypto.spec.SecretKeySpec;
import sun.misc.*;
public class AES {
    //lave
    
    public static final byte[] keyvalue = new byte[]{
        
        /*
        la llave puede ser de 
        
        128  16 caracteres  9
        192  24 caracteres  11
        
        256 32 caracteres   13
        */
      
       'q','w','e','r','t','y','u','i',
       'q','w','e','r','t','y','u','i'
      

        
};
    
    

    
    public static final String instancia = "AES";
    
    public static String encrypt(String Data) throws Exception {
        
        Key key = generateKey();
        
        // inicalizamos el cifrado
        
        Cipher cifrado = Cipher.getInstance(instancia);
        System.out.println(key);
        cifrado.init(Cipher.ENCRYPT_MODE, key);
        
        // obtenemos el mensaje y transformarlo en bytes
        
        byte[] encValores = cifrado.doFinal(Data.getBytes());
        
        System.out.println("Valor sin formato" + encValores);
        // vamos a aplicar un formato para que pueda ser leido el mensaje cifrado
        // vamos a aplicar el formato de codificacion de base 64
        String valoresencriptados = new BASE64Encoder().encode(encValores);
        
        return valoresencriptados;
    }

    
    
    public static String decrypt(String valoresencriptados) throws Exception {
        
        Key key = generateKey();
        
        // inicalizamos el cifrado
        
        Cipher cifrado = Cipher.getInstance(instancia);
        
        cifrado.init(Cipher.DECRYPT_MODE, key);
        
        // obtenemos el mensaje y transformarlo en bytes
        
        
        byte[] decodificadordeValores = new BASE64Decoder().decodeBuffer(valoresencriptados);
        
        byte[] decValores = cifrado.doFinal(decodificadordeValores);
        
        System.out.println("Valor sin formato" + decValores);
        // vamos a aplicar un formato para que pueda ser leido el mensaje cifrado
        // vamos a aplicar el formato de codificacion de base 64
        String valoresdescifrados = new String(decValores);
        
        return valoresdescifrados;
    }
    private static Key generateKey() throws Exception {

        Key key = new SecretKeySpec(keyvalue, instancia);
        
        return key;
    }
}

