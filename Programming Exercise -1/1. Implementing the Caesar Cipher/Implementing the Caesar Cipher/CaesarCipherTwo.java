
/**
 * Write a description of CaesarCipherTwo here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.*;
import edu.duke.*;
import java.io.*;

public class CaesarCipherTwo 
{
    private String uppercaseAlphabet;  //Filed/Instance variable
    private String lowercaseAlphabet;  //Filed/Instance variable
    private String shiftedUpperAlphabet1;  //Filed/Instance variable
    private String shiftedLowerAlphabet1;  //Filed/Instance variable
    private String shiftedUpperAlphabet2;  //Filed/Instance variable
    private String shiftedLowerAlphabet2;  //Filed/Instance variable
    private int mainKey1;  //Filed/Instance variable
    private int mainKey2;  //Filed/Instance variable
    
    public CaesarCipherTwo(int key1, int key2) //This method should initialize all the private fields
    {
        uppercaseAlphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        lowercaseAlphabet = "abcdefghijklmnopqrstuvwxyz";
        shiftedUpperAlphabet1 = uppercaseAlphabet.substring(key1) + uppercaseAlphabet.substring(0, key1); // Shift the characters to rightwards with key1 number
        shiftedLowerAlphabet1 = lowercaseAlphabet.substring(key1) + lowercaseAlphabet.substring(0, key1); // Shift the characters to rightwards with key1 number
        shiftedUpperAlphabet2 = uppercaseAlphabet.substring(key2) + uppercaseAlphabet.substring(0, key2); // Shift the characters to rightwards with key2 number
        shiftedLowerAlphabet2 = lowercaseAlphabet.substring(key2) + lowercaseAlphabet.substring(0, key2); // Shift the characters to rightwards with key2 number
        mainKey1 = key1;
        mainKey2 = key2;
    }
    
    public String encrypt(String input) //This method returns a String that is the input encrypted using the two shifted alphabets
    {
        StringBuilder outputString = new StringBuilder("");
        for(int i = 0; i < input.length(); i++)
        {
            char currentChar = input.charAt(i);
            int charPosition = currentChar;
            if (charPosition >= 97 && charPosition <= 122) //When the character is lowercase letter
            { 
                int index = lowercaseAlphabet.indexOf(currentChar);
                if (i%2 == 0)
                {
                    char newChar = shiftedLowerAlphabet1.charAt(index); //Find the new character from the shifted alphabet
                    outputString.append(newChar); //Add the new character in the output string
                }
                else
                {
                    char newChar = shiftedLowerAlphabet2.charAt(index); //Find the new character from the shifted alphabet
                    outputString.append(newChar); //Add the new character in the output string
                }
            }
            else if (charPosition >= 65 && charPosition <= 90) //When the character is uppercase letter
            {
                int index = uppercaseAlphabet.indexOf(currentChar);
                if (i%2 == 0)
                {
                    char newChar = shiftedUpperAlphabet1.charAt(index); //Find the new character from the shifted alphabet
                    outputString.append(newChar); //Add the new character in the output string
                }
                else
                {
                    char newChar = shiftedUpperAlphabet2.charAt(index); //Find the new character from the shifted alphabet
                    outputString.append(newChar); //Add the new character in the output string
                }
            }
            else
            {
                outputString.append (currentChar); 
            }
        }
        return outputString.toString(); //Transformed into string
    }
    
    public String decrypt(String input)  //Decrypt the encrypted message
    {
        CaesarCipherTwo cc = new CaesarCipherTwo(26 - mainKey1,26 - mainKey2); 
        String outputDecrypted = cc.encrypt(input);
        return outputDecrypted;
    }
    
    
    
}
