
/**
 * Write a description of WordLengths here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;
import edu.duke.*;

public class WordLengths 
{
    public int[] countWordLengths(FileResource resource)
    {
        int[] outputCounts = new int[20];  // Create a string array with lengths and counts of each length
        String[] extractedWords = new String[200000]; //Create a string array with 20 elements
        int index = 0;
        for (String str : resource.words()) //Separate each word and put it in the string array
        {
            int strLength = str.length();
            if(strLength == -1)
            {
                continue;
            }
            else if(strLength == 1 && !Character.isLetter(str.charAt(0)))
            {
                continue;
            }
            
            if (!Character.isLetter(str.charAt(0)) && !Character.isLetter(str.charAt(strLength - 1)))
            {
                extractedWords[index] = str.substring(1, strLength - 1);  //If a word has a non-letter as the first and last character, delete them 
            }
            else if(!Character.isLetter(str.charAt(0)))
            {
                extractedWords[index] = str.substring(1, strLength); //If a word has a non-letter as the first character, delete it
            }
            else if(!Character.isLetter(str.charAt(strLength - 1)))
            {
                extractedWords[index] = str.substring(0, strLength - 1);  //If a word has a non-letter as the last character, delete it
            }
            else
            {
                extractedWords[index] = str;
            }
            
            System.out.println (extractedWords[index]);
            
            index ++;
        }
        
        for (int i = 0; i < extractedWords.length; i++) //Note that 'length' of array is not method
        {
            if (extractedWords[i] != null)
            {
                int charCount = extractedWords[i].length();
                outputCounts[charCount] ++ ;
            }
        }
        
        for (int i = 0; i < outputCounts.length; i++)
        {
            System.out.println("\n" + "The number of words with length " + i + " : " + outputCounts[i]);
            /*for (int k = 0; k < extractedWords.length; k ++)
            {
                if (extractedWords[k] != null && extractedWords[k].length() == i)
                {System.out.println (extractedWords[k]);}
            }*/
        }
        return outputCounts;
    }
   
    public int indexOfMax(int[] values) //This method returns the index position of the largest element in values.
    {
        int max = values[0];
        int outputLength = 0;
        for(int i = 0; i < values.length; i++)
        {
            if (max < values[i])
            {
                max = values[i];
                outputLength = i;
            }
        }
        return outputLength;
    }
    
    public void testCountWordLengths()
    {
        FileResource fr = new FileResource();
        int[] counts = countWordLengths(fr);
        int maxCount = indexOfMax (counts);
        System.out.println("The length of the most common words in the file is " + maxCount);
    }
    
    
}
