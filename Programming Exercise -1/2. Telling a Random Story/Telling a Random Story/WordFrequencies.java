
/**
 * Write a description of WordFrequencies here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.*;
import edu.duke.*;
import java.io.*;

public class WordFrequencies 
{
    private ArrayList<String> myWords; //ArrayList used to store unique words from a file
    private ArrayList<Integer> myFreqs; //ArrayList used to store frequency of words from a file
    
    public WordFrequencies()
    {
        myWords = new ArrayList<String>(); //Initialize the instance variable
        myFreqs = new ArrayList<Integer>();  //Initialize the instance variable
    }
    
    public void findUnique()
    {
        myWords.clear();
        myFreqs.clear();
        
        FileResource fr = new FileResource();
        for (String word : fr.words())
        {
            String lowercaseWord = word.toLowerCase();
            int index = myWords.indexOf(lowercaseWord);
            if(index != -1)
            {
                int oldFreq = myFreqs.get(index);
                myFreqs.set(index, oldFreq+1);
            }
            else
            {
                myWords.add(lowercaseWord);
                myFreqs.add(1);
            }
        }
    }
    
    public int findIndexOfMax()
    {
        int outputIndex = 0;
        int maxFreq = 0;
        for(int i = 0; i < myFreqs.size(); i++)
        {
            if(maxFreq < myFreqs.get(i))
            {
                outputIndex = i;
                maxFreq = myFreqs.get(i);
            }
        }
        return outputIndex;
    }
    
    public void tester()
    {
        findUnique();
        
        System.out.println("The number of unique words: " + myWords.size());
        
        /*
        for(int i = 0; i < myWords.size(); i++)
        {
            System.out.println("Word/freq: " + myWords.get(i) + " " + myFreqs.get(i));
        }*/
        
        int index = findIndexOfMax();
        System.out.println("The word that occurs most often and its count are: " + myWords.get(index) + " " + myFreqs.get(index));
    }
}
