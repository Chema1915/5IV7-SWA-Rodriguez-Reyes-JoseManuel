
import java.awt.Desktop;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.util.Base64;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Administrador
 */
public class Descifrars {
    boolean jalo = true;
    boolean Descifrararch(SecretKey clavef, String path) throws Exception {
      try{
          
       
      Cipher cifrado = Cipher.getInstance("DES/ECB/PKCS5Padding");
          
      cifrado.init(Cipher.DECRYPT_MODE, clavef);
      
      byte[] buffer = new byte[1000]; // leer cada mil
         byte[] bufferPlano;
         
         
         // generar mis archivos
         
         FileInputStream in = new FileInputStream(path);
         FileOutputStream out = new FileOutputStream(path+".descifrado");
         
         //lectura
         
         int bytesleidos = in.read(buffer, 0, 1000);
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
         
      }catch(InvalidKeyException e){
          System.out.println("Error");
          JOptionPane.showMessageDialog(null,"Error\n Recuerda que la clave tiene que ser de 8 bytes");
           System.out.println(e);
           jalo = false;
           
    }
      return jalo;
    
      
}
    
   SecretKey originalKey;
    public  SecretKey convertStringToSecretKeyto(String claves) {
        try{
    byte[] decodedKey = Base64.getDecoder().decode(claves);
     originalKey = new SecretKeySpec(decodedKey, 0, decodedKey.length, "DES");
    
        }catch(Exception e){
         originalKey = null;
        }
        return originalKey;
}
    String ubi;
     public String SubiraArchivo(){
      
      JFileChooser file = new JFileChooser();
      
      file.showOpenDialog(file);
      File abre = file.getSelectedFile();
      
      if(abre!= null){
          ubi = abre.getPath();
      }
      System.out.println(ubi);
     return ubi;
  }
public void abrirarchivo(String archivo){

     try {

            File objetofile = new File (archivo);
            Desktop.getDesktop().open(objetofile);

     }catch (IOException ex) {

            System.out.println(ex);

     }

}                         
}
