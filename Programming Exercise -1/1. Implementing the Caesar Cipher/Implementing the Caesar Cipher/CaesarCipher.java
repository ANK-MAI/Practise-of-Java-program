
/**
 * Write a description of CaesarCipher here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;
import edu.duke.*;
import java.io.*;

public class CaesarCipher 
{
    private String uppercaseAlphabet;  //Filed/Instance variable
    private String lowercaseAlphabet;  //Filed/Instance variable
    private String shiftedUpperAlphabet;  //Filed/Instance variable
    private String shiftedLowerAlphabet;  //Filed/Instance variable;
    private int mainKey;
    
    public CaesarCipher(int key)  //Constructor, this method should initialize all the private fields of the class.
    {
        uppercaseAlphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        lowercaseAlphabet = "abcdefghijklmnopqrstuvwxyz";
        shiftedUpperAlphabet = uppercaseAlphabet.substring(key) + uppercaseAlphabet.substring(0, key); // Shift the characters to rightwards with key number
        shiftedLowerAlphabet = lowercaseAlphabet.substring(key) + lowercaseAlphabet.substring(0, key); // Shift the characters to rightwards with key number
        mainKey = key;
    }
    
    public String encrypt(String input) //Encrypted using the Caesar Cipher algorithm
    {
        StringBuilder outputString = new StringBuilder(""); 
        for(int i = 0; i < input.length(); i++)
        {
            char currentChar = input.charAt(i);
            int charPosition = currentChar;
            if (charPosition >= 97 && currentChar <= 122) //When the character is lowercase letter
            { 
                int index = lowercaseAlphabet.indexOf(currentChar);
                char newChar = shiftedLowerAlphabet.charAt(index); //Find the new character from the shifted alphabet
                outputString.append(newChar); //Add the new character in the output string
            }
            else if (charPosition >= 65 && currentChar <= 90) //When the character is uppercase letter
            {
                int index = uppercaseAlphabet.indexOf(currentChar);
                char newChar = shiftedUpperAlphabet.charAt(index); //Find the new character from the shifted alphabet
                outputString.append(newChar); //Add the new character in the output string
            }
            else
            {
                outputString.append(currentChar); 
            }
        }
        return outputString.toString(); //Transformed into string
    }
    
    public String decrypt(String encrypted)  //Decrypt the encrypted message
    {
        CaesarCipher cc = new CaesarCipher(26 - mainKey); 
        String outputDecrypted = cc.encrypt(encrypted);
        return outputDecrypted;
    }
    

    
    
    
    /*
    
    
    public void testEncrypt()
    {
        String encryptedStr = encrypt("At noon be in the conference room with your hat on for a surprise party. YELL LOUD!", 15);
        System.out.println(encryptedStr);
    }
    
    public void testCaesarFromFile()
    {
        int key = 23;
        FileResource fr = new FileResource();
        String message = fr.asString();
        String encrypted = encrypt(message, key);
        System.out.println("key is " + key + "\n" + encrypted);
    }
    
    public void testEncryptTwoKeys()
    {
        String encryptedStr = encryptTwoKeys("Bee sees the key,need help!", 8,21);
        System.out.println(encryptedStr);
    }
    
    */
    
}
