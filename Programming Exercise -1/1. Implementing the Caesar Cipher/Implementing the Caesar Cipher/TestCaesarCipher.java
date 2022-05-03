
/**
 * Write a description of TestCaesarCipher here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;
import edu.duke.*;
import java.io.*;

public class TestCaesarCipher 
{
    private int[] countLetters(String message) //To count the number of each letter appreared in the message
    {
        String alphabet = "abcdefghijklmnopqrstuvwxyz";  //Create the lowercase alphabet
        int[] counts = new int[26];  //Create the letter counter
        for(int k = 0; k < message.length(); k++)  //Iterate the message string
        {
            char ch = Character.toLowerCase(message.charAt(k)); //Get each character
            int index = alphabet.indexOf(ch);  //Check the index of this character in the alphabet
            if (index != -1)  //If this character is letter
            {
                counts[index]++; //Count this letter by +1
            }
        }
        return counts; 
    }
    
    private int maxIndex(int[] counts) //Find out the letter which occurs more frequently in the message
    {
        int outputIndex = 0; //Output index of the letter in the alphabet
        for(int k = 0; k < counts.length; k ++)
        if(counts[k] > counts[outputIndex])
        {
            outputIndex = k;
        }
        return outputIndex;
    }
    
    private void breakCaesarCipher(String input) //This method should figure out which key was used to encrypt this message, then create a CaesarCipher object with that key and decrypt the message.
    {
        int[] freqs = countLetters(input);
        int maxDex = maxIndex(freqs);
        System.out.println("maxDex: " + maxDex);
        int dkey = 0;
        if(maxDex < 4)
        {
            dkey = 4 - maxDex;
        }
        else
        {
            dkey = 26 - (maxDex - 4);
        }
        System.out.println("dkey:" + dkey);
        CaesarCipher breakCC = new CaesarCipher(dkey);
        String message = breakCC.encrypt(input);
        System.out.println("The decrypted message by found dkey is: " + "\n" + message);
    }
    
    public void simpleTests()
    {
        FileResource fr = new FileResource();
        String str = fr.asString();
        System.out.println(str);
        
        CaesarCipher cc = new CaesarCipher(18);
        String encryptedStr = cc.encrypt(str);
        System.out.println(encryptedStr);
        
        String decryptedStr = cc.decrypt(encryptedStr);
        System.out.println(decryptedStr);
        
        breakCaesarCipher(encryptedStr);
    }
    
    
}
