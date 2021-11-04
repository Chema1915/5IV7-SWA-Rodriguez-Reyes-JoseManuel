/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Administrador
 */

import java.math.BigInteger;
import java.io.*;

import java.util.*;
public class RSA {
    /*
    Definir los numeros
    */
    int tamprimo;
    BigInteger p,q,n;
    BigInteger fi;
    BigInteger e,d;
    
    
    public RSA(int tamprimo){
        this.tamprimo = tamprimo;
    }
    
    // metodo para generar los numeros primos
    
    public void generarPrimos(){
        p = new BigInteger(tamprimo,10,new Random());
        
        do q = new BigInteger(tamprimo,10,new Random());
        while(q.compareTo(p)==0);
        
    }
    
    //generar las claves
    
    public void generarClaves(){
        /*
        recordar que n = p*q
        fi= (p-1)(q-1)
        */
        n = p.multiply(q);
        fi = p.subtract(BigInteger.valueOf(1));
        
        fi = fi.multiply(q.subtract(BigInteger.valueOf(1)));
        
        /*
        calcular a e
        
        1<e<fi(n)
        */
        
       do e = new BigInteger(2*tamprimo, new Random());
       while((e.compareTo(fi) != -1) || (e.gcd(fi).compareTo(BigInteger.valueOf(1)) != 0));
       
       //calcular a d= e^1 mod fi     inverso multiplicativo de e
       
       d= e.modInverse(fi);
       
       
    }
    
    //ciframos con la clave publica
    
    public BigInteger[] cifrar(String mensaje){
        int i;
        byte[] temp= new byte[1];
        byte[] digitos = mensaje.getBytes();
        
        
        BigInteger[] bigdigitos = new BigInteger[digitos.length];
        
        for(i=0; i < bigdigitos.length; i++){
            temp[0] = digitos[i];
            bigdigitos[i] =new BigInteger(temp);
            
            
        }
        BigInteger[] cifrado = new BigInteger[bigdigitos.length];
        
        for(i = 0; i < bigdigitos.length; i++){
            //formula
            // c= M^e mod n
            cifrado[i] = bigdigitos[i].modPow(e, n);
        }
     return cifrado;   
    }
    //desciframos con clave privada d
    public String descifrar(BigInteger[] cifrado){
        BigInteger[] descifrado = new BigInteger[cifrado.length];
        
        //vamos a decifrar
        // Md = C^d mod n
         for(int j = 0; j < descifrado.length; j++){
            //formula
            // c= M^e mod n
            descifrado[j] = cifrado[j].modPow(d, n);
        }
         
         char[] charArray = new char[descifrado.length];
         for(int j=0; j< charArray.length; j++){
             charArray[j] = (char)(descifrado[j].intValue());
             
         }
     return (new String(charArray));
    }
}
