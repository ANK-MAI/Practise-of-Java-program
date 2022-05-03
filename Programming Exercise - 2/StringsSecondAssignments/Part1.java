
/**
 * Write a description of Part1 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 * Find the segment of genes firstly, then justify each genes.
 */

import edu.duke.*;
import java.util.Arrays;

public class Part1 {
    public StorageResource findSegGenes (String dna, String startCodon)
    {
        StorageResource outputSegments = new StorageResource(); 
        
        String upCaseDNA = dna.toUpperCase(); //Switch all letters in the dna to uppercase letters.
        int lengthDNA = upCaseDNA.length();
        String segGene = "";
        int startIndex = 0;
        int nextStartIndex = 0;
        int currIndex = 0;
        
        while ((currIndex + 3) < lengthDNA)
        {
            startIndex = upCaseDNA.indexOf(startCodon, currIndex);
            if (startIndex == -1)
            {
                break; //No start index of gene is found, stop looking for genes.
            }
            nextStartIndex = upCaseDNA.indexOf(startCodon, startIndex + 3);
            if (nextStartIndex == -1)
            {
                segGene = dna.substring(startIndex);
                outputSegments.add(segGene);
                break; //No start index of gene is found, stop looking for genes.
            }
            segGene = dna.substring(startIndex, nextStartIndex);
            outputSegments.add(segGene);
            currIndex = nextStartIndex;
        }
        return outputSegments;
    }
    
    public StorageResource findGenes(String dna, String startCodon, String stopCodon1, String stopCodon2, String stopCodon3)
    {
        StorageResource outputGenes = new StorageResource();  // Create an output gene list which stores all genes found in every strand of DNA.
        
        String upCaseDNA = dna.toUpperCase(); //Switch all letters in the dna to uppercase letters.

        StorageResource segGenes = findSegGenes(dna, startCodon);
        if (segGenes.size() == 0)
        {
            return outputGenes;
        }
        
        for (String segGene : segGenes.data())
        {
            int currIndex = 3;  // Current index
            while (currIndex < segGene.length())
            {
                int stopIndex1 = segGene.indexOf(stopCodon1, currIndex);
                //System.out.println(stopIndex1);
                int stopIndex2 = segGene.indexOf(stopCodon2, currIndex);
                //System.out.println(stopIndex2);
                int stopIndex3 = segGene.indexOf(stopCodon3, currIndex);
                //System.out.println(stopIndex3);
                if (stopIndex1 == -1 && stopIndex2 == -1 && stopIndex3 == -1)
                {
                    break;  //No stop indexes of gene are found, skip this segment.
                }
            
                int arrIndex[] = {stopIndex1, stopIndex2, stopIndex3};
                Arrays.sort(arrIndex); //Sort the index from low to high
                stopIndex1 = arrIndex[0];
                //System.out.println(stopIndex1);
                stopIndex2 = arrIndex[1];
                //System.out.println(stopIndex2);
                stopIndex3 = arrIndex[2];
                //System.out.println(stopIndex3);
            
                if (stopIndex1 != -1 && (stopIndex1 % 3) == 0) 
                {
                    outputGenes.add(segGene.substring(0, stopIndex1 + 3));
                    break;
                }
                else if (stopIndex2 != -1 && (stopIndex2 % 3) == 0)
                {
                    outputGenes.add(segGene.substring(0, stopIndex2 + 3));
                    break;
                }
                else if (stopIndex3 != -1 && (stopIndex3 % 3) == 0)
                {
                    outputGenes.add(segGene.substring(0, stopIndex3 + 3));
                    break;
                }
                else
                {
                    currIndex = stopIndex1 + 3;
                }
            }
        }   
        return outputGenes;
    }
    public void testFindGenes()
    {
        String startCodon = "ATG";  //Set the start codon.
        String stopCodon1 = "TAA";  //Set 1st kind of stop codon.
        String stopCodon2 = "TAG";  //Set 2nd kind of stop codon.
        String stopCodon3 = "TGA";  //Set 3rd kind of stop codon.
        
        StorageResource genes = new StorageResource();  //Create a string list to store the genes found in the stands of DNA.
        
        FileResource fr = new FileResource();  // Open the file which includes many stands of DNA.
        
        for (String dna : fr.lines())  // Loop the stand of DNA one by one.
        {
            if(dna == "\n")
            {
                continue;
            }
            System.out.println("The strand of dna is:" + dna);
            genes = findGenes (dna, startCodon, stopCodon1, stopCodon2, stopCodon3); // Call the findGenes method to find all genes in every strand of DNA.
            System.out.println("The number of genes in this strand of dna is: " + genes.size());
            System.out.println("The genes in this strand of dna are:");
            for (String gene : genes.data()) // Loop the genes one by one.
            {
                System.out.println(gene); // Show all genes found in every strand of DNA.
            }
        }
    }
}


