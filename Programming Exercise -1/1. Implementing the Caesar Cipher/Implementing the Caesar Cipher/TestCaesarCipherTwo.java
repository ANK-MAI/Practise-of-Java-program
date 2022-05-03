
/**
 * Write a description of TestCaesarCipherTwo here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.*;
import edu.duke.*;
import java.io.*;

public class TestCaesarCipherTwo 
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
    
    private String halfOfString (String message, int start)
    {
        StringBuilder outputStr = new StringBuilder("");
        for (int i = start; i < message.length(); i+=2)
        {
            outputStr.append(message.charAt(i)); //Add the character in the output string
        }
        return outputStr.toString();
    }
    
    private int getKey(String s)
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
    
    private void breakCaesarCipher(String input)
    {
        String half1OfStr = halfOfString(input,0);
        String half2OfStr = halfOfString(input,1);
        int dkey1 = getKey(half1OfStr);
        int dkey2 = getKey(half2OfStr);
        System.out.println("dkey1: " + dkey1);
        System.out.println("dkey2: " + dkey2);
        CaesarCipherTwo breakCC = new CaesarCipherTwo(dkey1,dkey2); //Instantiate the object breakCC
        String decrypted = breakCC.encrypt(input);
        System.out.println("The decrypted message by found dkeys is: " + "\n" + decrypted);
    }
    
    public void simpleTests()
    {
        FileResource fr = new FileResource();  //Open a file
        String str = fr.asString();  //Read the file as string
        System.out.println(str);
        
        //CaesarCipherTwo cc = new CaesarCipherTwo(14,24);
        //String encryptedStr = cc.encrypt(str);
        //System.out.println(encryptedStr);
        
        //String decryptedStr = cc.decrypt(encryptedStr);
        //System.out.println(decryptedStr);
        
        breakCaesarCipher(str);
    }
    
}
