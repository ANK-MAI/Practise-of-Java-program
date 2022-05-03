 
import edu.duke.*;
import java.io.*;

/**
 * Write a description of Part1 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Part2 {

    public String findSimpleGene (String dna, String startCodon, String stopCodon)
    {
        String gene = new String("");
        String upCaseDNA = dna.toUpperCase(); //Switch all letters in the string to uppercase letters.
        int startIndex = upCaseDNA.indexOf(startCodon);
        int stopIndex = upCaseDNA.indexOf(stopCodon, startIndex + 3);
        if (startIndex == -1 || stopIndex == -1 || ((stopIndex-startIndex)%3 != 0))
        {
            return gene="";
        }
        else
        {
            gene = dna.substring(startIndex, stopIndex +3);
            return gene;
        }
    
    }
    
    public void testSimpleGene()
    {
        String startCodon = "ATG";
        String stopCodon = "TAA";
        String dna1 = "ATTTGATTATAGATTGAATAGGGATAAATAGAATA";
        System.out.println("The dna is:" + dna1);
        System.out.println("The gene in the dna is:" + findSimpleGene(dna1, startCodon, stopCodon));
        String dna2 = "ATTTGATTATGATTGAATAGGGATAGATAGAATAG";
        System.out.println("The dna is:" + dna2);
        System.out.println("The gene in the dna is:" + findSimpleGene(dna2, startCodon, stopCodon));
        String dna3 = "ATTTGATTAGGATTGAATAGGGATAGATAGAATAG";
        System.out.println("The dna is:" + dna3);
        System.out.println("The gene in the dna is:" + findSimpleGene(dna3, startCodon, stopCodon));
        String dna4 = "ATTTGATGAGGATTGAATAAGGATAGATAGAATAG";
        System.out.println("The dna is:" + dna4);
        System.out.println("The gene in the dna is:" + findSimpleGene(dna4, startCodon, stopCodon));
        String dna5 = "ATTTGATGAGGATTGAAATAAGGATAGATAGAATA";
        System.out.println("The dna is:" + dna5);
        System.out.println("The gene in the dna is:" + findSimpleGene(dna5, startCodon, stopCodon));
    }
}
