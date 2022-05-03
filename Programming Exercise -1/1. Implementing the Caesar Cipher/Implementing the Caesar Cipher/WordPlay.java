
/**
 * Write a description of WordPlay here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.*;
import edu.duke.*;
import java.io.*;

public class WordPlay 
{
    public boolean isVowel(char ch)
    {
        boolean outJudge = false;
        char lowerVowels[] = {'a','e','i','o','u'};
        char upperVowels[] = {'A','E','I','O','U'};
        for (int k = 0; k < 5; k++)
        {
            if (ch == lowerVowels[k] || ch == upperVowels[k])
            {
                outJudge = true;
            }
        }
        return outJudge;
    }
    
    public void testIsVowel()
    {
        if (isVowel('a'))
        {
            System.out.println ("It is vowel.");
        }
        else 
        {
            System.out.println ("It is not vowel.");
        }
        /*
        FileResource fr = new FileResource();
        for(String line : fr.lines())
        {
            
        }*/
    }
    
    public String replaceVowels(String phrase, char ch)
    {
        String outputPhrase = "";
        for (int i = 0; i < phrase.length(); i++)
        {
            if (isVowel(phrase.charAt(i)))
            {
                outputPhrase = outputPhrase + ch; 
            }
            else 
            {
                outputPhrase = outputPhrase + phrase.charAt(i);
            }
        }
        return outputPhrase;
    }
    public void testReplaceVowels()
    {
        String str = replaceVowels("Hello World", '*');
        System.out.println (str);
    }
    
    public String emphasize(String phrase, char ch) //This method should return a String that is the string phrase but with the Char ch (upper- or lowercase) replaced by  ¡®*¡¯ if it is in an odd number location in the string or ¡®+¡¯ if it is in an even number location in the string
    {
        String outputPhrase = "";
        String lowercasePhrase = phrase.toLowerCase();
        for(int i = 0; i < lowercasePhrase.length(); i++)
        {        
            if((i%2 == 0) && lowercasePhrase.charAt(i) == ch)
            {
                outputPhrase = outputPhrase + "*";
            }
            else if ((i%2 == 1) && lowercasePhrase.charAt(i) == ch)
            {
                outputPhrase = outputPhrase + "+";
            }
            else 
            {
                outputPhrase = outputPhrase + lowercasePhrase.charAt(i);
            }
        }
        return outputPhrase;
    }
    public void testEmphasize()
    {
        String str = emphasize("dna ctgaaactga", 'a');
        System.out.println (str);
    }
    
    
    
    
}
