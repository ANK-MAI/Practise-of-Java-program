 
import edu.duke.*;
import java.io.*;

/**
 * Write a description of Part1 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Part1 {

    public String findSimpleGene (String dna)
    {
        String gene=new String("");
        int startIndex = dna.indexOf("ATG");
        int stopIndex = dna.indexOf("TAA", startIndex + 3);
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
        String dna1 = "ATTTGATTATAGATTGAATAGGGATAAATAGAATA";
        System.out.println("The dna is:" + dna1);
        System.out.println("The gene in the dna is:" + findSimpleGene(dna1));
        String dna2 = "ATTTGATTATGATTGAATAGGGATAGATAGAATAG";
        System.out.println("The dna is:" + dna2);
        System.out.println("The gene in the dna is:" + findSimpleGene(dna2));
        String dna3 = "ATTTGATTAGGATTGAATAGGGATAGATAGAATAG";
        System.out.println("The dna is:" + dna3);
        System.out.println("The gene in the dna is:" + findSimpleGene(dna3));
        String dna4 = "ATTTGATGAGGATTGAATAAGGATAGATAGAATAG";
        System.out.println("The dna is:" + dna4);
        System.out.println("The gene in the dna is:" + findSimpleGene(dna4));
        String dna5 = "ATTTGATGAGGATTGAAATAAGGATAGATAGAATA";
        System.out.println("The dna is:" + dna5);
        System.out.println("The gene in the dna is:" + findSimpleGene(dna5));
    }
}
