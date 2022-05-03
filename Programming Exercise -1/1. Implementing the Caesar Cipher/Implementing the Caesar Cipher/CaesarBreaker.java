
/**
 * Write a description of CaesarBreaker here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.*;
import edu.duke.*;
import java.io.*;

public class CaesarBreaker {

    public int[] countLetters(String message) //To count the number of each letter appreared in the message
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
    
    public int maxIndex(int[] counts) //Find out the letter which occurs more frequently in the message
    {
        int outputIndex = 0; //Output index of the letter in the alphabet
        for(int k = 0; k < counts.length; k ++)
        if(counts[k] > counts[outputIndex])
        {
            outputIndex = k;
        }
        return outputIndex;
    }
    
    public String decrypt(String encrypted)  //Decrypt the encrypted message by finding the shift of letter 'e' in it
    {
        int[] freqs = countLetters(encrypted);
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
        CaesarCipher cc = new CaesarCipher(dkey);  //Instantiate the object cc
        String message = cc.encrypt(encrypted);
        return message;
    }
    
    public void testDecrypt()
    {
        CaesarCipher cc = new CaesarCipher(15);  //Instantiate the object cc
        String encryptedStr = cc.encrypt("At noon be in the conference room with your hat on for a surprise party. YELL LOUD!");
        //FileResource fr = new FileResource();
        //String encryptedStr = fr.asString();
        System.out.println(encryptedStr);
        
        String decryptedStr = decrypt(encryptedStr);
        System.out.println(decryptedStr);
    }
    
    public String halfOfString (String message, int start)
    {
        StringBuilder outputStr = new StringBuilder("");
        for (int i = start; i < message.length(); i+=2)
        {
            outputStr.append(message.charAt(i)); //Add the character in the output string
        }
        return outputStr.toString();
    }
    
    public void testHalfOfString()
    {
        String str = halfOfString("Qbkm Zgis", 1);
        System.out.println(str);
    }
    
    public int getKey(String s)
    {
        int[] freqs = countLetters(s);
        int maxDex = maxIndex(freqs);
        System.out.println("maxDex: " + maxDex);
        int dkey = 0;
        int mostFreqIndex = 4;  //The index of the most frequent letter
        if(maxDex < mostFreqIndex)
        {
            dkey = mostFreqIndex - maxDex;
        }
        else
        {
            dkey = 26 - (maxDex - mostFreqIndex);
        }
        System.out.println("dkey:" + dkey);
        return dkey;
    }
    
    public String decryptTwoKeys(String encrypted)
    {
        String outputDecrypted = "";
        String half1OfStr = halfOfString(encrypted,0);
        String half2OfStr = halfOfString(encrypted,1);
        int dkey1 = getKey(half1OfStr);
        int dkey2 = getKey(half2OfStr);
        System.out.println("dkey1: " + dkey1);
        System.out.println("dkey2: " + dkey2);
        CaesarCipherTwo cc = new CaesarCipherTwo (dkey1,dkey2);  //Instantiate the object cc
        outputDecrypted = cc.encrypt(encrypted);
        return outputDecrypted;
    }
    
    public void testDecryptTwoKeys()
    {
        FileResource fr = new FileResource();
        String message = fr.asString();
        String decrypted = decryptTwoKeys(message);
        System.out.println(decrypted);
    }
}
