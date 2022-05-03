/**
 * Write a description of Part1 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import edu.duke.*; //Import the class in edu.duke library
import java.util.Arrays;

public class Part2 {
    
    public int findStopCodon (String dna, int startIndex, String stopCodon)  //Find the index of different stop codon in the dna
    {
        //Find stopCodon starting from (startIndex + 3), currIndex
        int currIndex = dna.indexOf(stopCodon, startIndex + 3);
        //As long as currIndex is not equal to -1
        while (currIndex != -1)
        {
            //Check if currIndex - startIndex is a multiple of 3
            if((currIndex - startIndex) % 3 == 0)
            {
                //If so, currIndex is answer, return it
                return currIndex;
            }
            else
            {
                //If not, update currIndex, looking for stopCodon again starting from currIndex + 1
                currIndex = dna.indexOf(stopCodon, currIndex + 1);
            }
        }
        //If we exit loop, we didn't find stopCodon
        //So return dna.length()
        return dna.length();
    }
    
    
    public StorageResource getAllGenes(String dna, String startCodon, String stopCodon1, String stopCodon2, String stopCodon3)
    {
        StorageResource outputGenes = new StorageResource();  // Create an output gene list which stores all genes found in every strand of DNA.
        
        String upCaseDNA = dna.toUpperCase(); //Switch all letters in the dna to uppercase letters.
        int lengthDNA = upCaseDNA.length();
        int currIndex = 0;  // Current index
        
        while (currIndex < lengthDNA)  //Loop in the strand of DNA
        {
            int startIndex = upCaseDNA.indexOf(startCodon, currIndex);  //Find the start index of each gene.
            if (startIndex == -1)
            {
                break; //No start index of gene is found, stop looking for genes.
            }
            int stopIndex1 = findStopCodon(upCaseDNA, startIndex, stopCodon1);
            //System.out.println(stopIndex1);
            int stopIndex2 = findStopCodon(upCaseDNA, startIndex, stopCodon2);
            //System.out.println(stopIndex2);
            int stopIndex3 = findStopCodon(upCaseDNA, startIndex, stopCodon3);
            //System.out.println(stopIndex3);
            
            int temIndex = Math.min(stopIndex1, stopIndex2);
            int minIndex = Math.min(temIndex, stopIndex3);
            
            if (minIndex == dna.length())
            {
                break;
            }
            else
            {
                currIndex = minIndex + 3;
                outputGenes.add(dna.substring(startIndex, currIndex));
            }
        }
        return outputGenes;
    } 

    public float cgRatio(String dna) //This method is to calculate the ratio of appearance of character 'c' and 'g' in the dna
    {
       float outRatio = 0.0f;
       String upCaseDNA = dna.toUpperCase(); //Switch all letters in the dna to uppercase letters
       int lengthDNA = upCaseDNA.length(); //Get the total number of character in the dna
       int CGNum = 0;  //Number of character 'C' and 'G'
        
       for (int i = 0; i < lengthDNA; i++)  //Iterate the character in the dna
       {
            if (upCaseDNA.charAt(i) == 'C' || upCaseDNA.charAt(i) == 'G')  //When the character is 'C' or 'G', number increases by 1
            {
               CGNum ++ ; 
            }
       }
       outRatio = (float)CGNum / lengthDNA;
       return outRatio;
    }
    
    public int countCTG(String dna)
    {
        int outCount = 0;
        String upCaseDNA = dna.toUpperCase(); //Switch all letters in the dna to uppercase letters.
        int lengthDNA = upCaseDNA.length();
        int currIndex = 0;  // Current index
        
        while (currIndex < lengthDNA)  //Loop in the strand of DNA
        {
            int startIndex = upCaseDNA.indexOf("CTG", currIndex);  //Find the start index of each gene.
            if (startIndex == -1)
            {
                break; //No start index of gene is found, stop looking for genes.
            }
            else
            {
                outCount ++;
                currIndex = startIndex + 3;
            }
        }
        return outCount;
    }
    
    public void processGenes(StorageResource sr) 
    {
        int numStringOver60 = 0;
        int numStringOverCGRatio = 0;
        int maxLength = 0;
        
        System.out.println("The strings which are longer than 60 characters are: "); 
        for (String gene : sr.data())
        {
            if(gene.length() > 60)
            {
                //System.out.println(gene); //Print all the strings in sr that are longer than 60 characters
                numStringOver60 ++; //Count the number of strings in sr that are longer than 60 characters
            }
        }
        //Print the number of Strings in sr that are longer than 9 characters
        System.out.println("The number of strings which are longer than 60 characters is: " + numStringOver60);
        
        System.out.println("The Strings whose C-G-ratio is higher than 0.35 are: ");
        for (String gene : sr.data())
        {
            if(cgRatio(gene) > 0.35)
            {
                //System.out.println(gene); //Print all the strings in sr whose C-G-ratio is higher than 0.35
                numStringOverCGRatio ++; //Count the number of strings in sr whose C-G-ratio is higher than 0.35
            }
        }
        //Print the number of Strings in sr whose C-G-ratio is higher than 0.35
        System.out.println("The number of strings whose C-G-ratio is higher than 0.35 is: " + numStringOverCGRatio);
        
        //Print the length of the longest gene in sr
        for (String gene : sr.data())
        {
            if(gene.length() > maxLength)
            {
                maxLength = gene.length();
            }
        }
        System.out.println("The length of the longest gene in sr is: " + maxLength);
    }
    
    
    
    public void testProcessGenes() //Find out all genes from the stand of DNA
    {
        String startCodon = "ATG";  //Set the start codon.
        String stopCodon1 = "TAA";  //Set 1st kind of stop codon.
        String stopCodon2 = "TAG";  //Set 2nd kind of stop codon.
        String stopCodon3 = "TGA";  //Set 3rd kind of stop codon.
        float CGRatio = 0.0f;
        int numCodon = 0;
        StorageResource genes = new StorageResource();  //Create a string list to store the genes found in the stands of DNA.
        FileResource fr = new FileResource();  // Open the file which includes many stands of DNA.
        String dna = fr.asString();
        //String dna = "ATGATCTAATTTATGCTGCAACGGTGAAGA";
        System.out.println("The strand of dna is:" + dna);
        genes = getAllGenes(dna, startCodon, stopCodon1, stopCodon2, stopCodon3); // Call the findGenes method to find all genes in every strand of DNA.
        CGRatio = cgRatio(dna); //Call the getCGRatio method to calcuate the ratio of character of C and G in th
        numCodon = countCTG(dna);
        System.out.println("The number of genes in this strand of dna is: " + genes.size());
        System.out.println("The genes in this strand of dna are:");
        for (String gene : genes.data()) // Loop the genes one by one.
        {
            System.out.println(gene); // Show all genes found in every strand of DNA.
        }
        System.out.println("The CGRatio in this strand of dna is:" + CGRatio);
        System.out.println("The quantity of codon CTG in this strand of dna is:" + numCodon);
        
        processGenes(genes);
    }
}
