
/**
 * Write a description of Part1 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import edu.duke.*;
import java.util.Arrays;

public class Part2 {
    public StorageResource findGenes(String dna, String startCodon, String stopCodon1, String stopCodon2, String stopCodon3)
    {
        StorageResource outputGenes = new StorageResource();  // Create an output gene list which stores all genes found in every strand of DNA.
        
        String upCaseDNA = dna.toUpperCase(); //Switch all letters in the dna to uppercase letters.
        int lengthDNA = upCaseDNA.length();
        int temIndex = 0;  //Temperary index
        int currIndex = 0;  // Current index
        
        while (currIndex < lengthDNA)  //Loop in the strand of DNA
        {
            int startIndex = upCaseDNA.indexOf(startCodon, currIndex);  //Find the start index of each gene.
            if (startIndex == -1)
            {
                break; //No start index of gene is found, stop looking for genes.
            }
            int stopIndex1 = upCaseDNA.indexOf(stopCodon1, startIndex + 3);
            //System.out.println(stopIndex1);
            int stopIndex2 = upCaseDNA.indexOf(stopCodon2, startIndex + 3);
            //System.out.println(stopIndex2);
            int stopIndex3 = upCaseDNA.indexOf(stopCodon3, startIndex + 3);
            //System.out.println(stopIndex3);
            if (stopIndex1 == -1 && stopIndex2 == -1 && stopIndex3 == -1)
            {
                break;  //No stop indexes of gene are found, stop looking for genes.
            }
            
                int arrIndex[] = {stopIndex1, stopIndex2, stopIndex3};
                Arrays.sort(arrIndex);
                stopIndex1 = arrIndex[0];
                //System.out.println(stopIndex1);
                stopIndex2 = arrIndex[1];
                //System.out.println(stopIndex2);
                stopIndex3 = arrIndex[2];
                //System.out.println(stopIndex3);
            
                if (stopIndex1 != -1 && (stopIndex1-startIndex)%3 == 0) 
                {
                    currIndex = stopIndex1 + 3;
                    outputGenes.add(dna.substring(startIndex, currIndex));
                }
                else if (stopIndex2 != -1 && (stopIndex2-startIndex)%3 == 0)
                {
                    currIndex = stopIndex2 + 3;
                    outputGenes.add(dna.substring(startIndex, currIndex));
                }
                else if (stopIndex3 != -1 && (stopIndex3-startIndex)%3 == 0)
                {
                    currIndex = stopIndex3 + 3;
                    outputGenes.add(dna.substring(startIndex, currIndex));
                }
                else
                {
                    currIndex = startIndex + 3;
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
            genes = findGenes(dna, startCodon, stopCodon1, stopCodon2, stopCodon3); // Call the findGenes method to find all genes in every strand of DNA.
            System.out.println("The number of genes in this strand of dna is: " + genes.size());
            System.out.println("The genes in this strand of dna are:");
            for (String gene : genes.data()) // Loop the genes one by one.
            {
                System.out.println(gene); // Show all genes found in every strand of DNA.
            }
        }
    }
}

