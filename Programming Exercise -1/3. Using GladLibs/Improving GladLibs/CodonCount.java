
/**
 * Write a description of CodonCount here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import edu.duke.*;
import java.util.*;
import java.io.*;

public class CodonCount 
{
    private HashMap<String,Integer> DNAmap;
    
    
    public CodonCount()
    {
        DNAmap = new HashMap<String,Integer>();
        
    }
    
    private void buildCodonMap(int start, String dna)
    {
        for(int i = start; i < (dna.length() - 3); i = i + 3)
        {
            String codon = dna.substring(i,i+3);
            String uppercaseCodon = codon.toUpperCase();
            if (DNAmap.containsKey(uppercaseCodon))
            {   
                DNAmap.put(uppercaseCodon, DNAmap.get(uppercaseCodon)+1);
            }
            else 
            {
                DNAmap.put(uppercaseCodon,1);
            }
        }
    }
    
    private String getMostCommonCodon()
    {
        String outputCodon = "";
        int maxCount = 0;
        for (String codon : DNAmap.keySet())
        {
            if (maxCount < DNAmap.get(codon))
            {
                outputCodon = codon;
                maxCount = DNAmap.get(codon);
            }
        }
        return outputCodon;
    }
    
    private void printCodonCounts(int start, int end)
    {
        for (String codon : DNAmap.keySet())
        {
            if (DNAmap.get(codon) >= start && DNAmap.get(codon) <= end)
            {
                System.out.println("Codon / count within " + start + " and " + end + ": " + codon + " " + DNAmap.get(codon));
            }
        }
    }
    
    public void tester()
    {
        FileResource fr = new FileResource();
        String dna = fr.asString();
        
        for (int i = 0; i < 3; i ++)
        {
            DNAmap.clear();
            buildCodonMap(i,dna);
            System.out.println("The total number of unique codons starting with " + i + ": " + DNAmap.size());
            String mostCommonCodon = getMostCommonCodon();
            System.out.println("The most common codon / count: " + mostCommonCodon + " " + DNAmap.get(mostCommonCodon));
            printCodonCounts(6, 8);
        }
    }
}
