 
import edu.duke.*;
import java.io.*;

/**
 * Write a description of Part1 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Part3 {

    public boolean twoOccurrences (String stringa, String stringb)
    {
        boolean feedBack = true;
        String upCaseSTRB = stringb.toUpperCase(); //Switch all letters in the string to uppercase letters.
        int lengthstra = stringa.length();
        int lengthSTRB = upCaseSTRB.length();
        int currIndex = 0;
        int nextIndex = 0;
        int numstra = 0;
        while (currIndex <= lengthSTRB)
        {
            nextIndex = upCaseSTRB.indexOf(stringa,currIndex);
            if (nextIndex == -1)
            {
                break;
            }
            else
            {
                numstra = numstra + 1;
                currIndex = nextIndex + lengthstra;
            }
        }
        if (numstra >= 2)
        {
            return feedBack = true;
        }
        else
        {
            return feedBack = false;
        }
    }
    
    public String lastPart (String stringa, String stringb)
    {
        String outputStr = "";
        int lengthStra = stringa.length();
        int lengthStrb = stringb.length();
        int currIndex = stringb.indexOf(stringa);
        if (currIndex == -1)
        {
            return outputStr = stringb;
        }
        else
        {
            return outputStr = stringb.substring ((currIndex + lengthStra),lengthStrb);
        }
    }
    
    
    public void testing()
    {
        String str1 = "ATG";
        String str2 = "TAA";
        String dna1 = "ATTTGATTATAGATTGAATAGGGATAAATAGAATA";
        System.out.println("The dna is:" + dna1);
        System.out.println("Are there at least two times of " + str1 + " in this dna? :" + twoOccurrences(str1,dna1));
        String dna2 = "ATGTGATTATGATGGAATAGGGATGAATAGAATAG";
        System.out.println("The dna is:" + dna2);
        System.out.println("Are there at least two times of " + str1 + " in this dna? :" + twoOccurrences(str1,dna2));
        String dna3 = "ATTTGATTAGGATTGAATAGGGATAGATAGAATAG";
        System.out.println("The dna is:" + dna3);
        System.out.println("Are there at least two times of " + str2 + " in this dna? :" + twoOccurrences(str2,dna3));
        String dna4 = "ATTTGATGAGGATTGAATAAGGATAGATATAATAG";
        System.out.println("The dna is:" + dna4);
        System.out.println("Are there at least two times of " + str2 + " in this dna? :" + twoOccurrences(str2,dna4));
        
        String str3 = "banana";
        System.out.println("The string is:" + str3);
        System.out.println("The string right after an is:" + lastPart("an",str3));
        
        String str4 = "forest";
        System.out.println("The string is:" + str4);
        System.out.println("The string right after zoo is:" + lastPart("zoo",str4));
    }
}

