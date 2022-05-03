
/**
 * Write a description of WordsInFiles here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import edu.duke.*;
import java.util.*;
import java.io.*;

public class WordsInFiles 
{
    private HashMap<String, ArrayList<String>> map;
    
    public WordsInFiles()
    {
        map = new HashMap<String, ArrayList<String>>();
        
    }
    
    private void addWordsFromFile(File f)
    {
        String filename = f.getName();
        FileResource fr = new FileResource(f);
        for (String word : fr.words())
        {
            //word = word.toLowerCase();
            if (map.containsKey(word))
            {   
                if(map.get(word).indexOf(filename) == -1) 
                {
                    map.get(word).add(filename);
                    map.put(word, map.get(word));
                }
            }
            else 
            {
                ArrayList<String> list = new ArrayList<String>();
                list.add(filename);
                map.put(word,list);
            }
        }
    }
    
    private void buildWordFileMap()
    {
        map.clear();
        DirectoryResource dr = new DirectoryResource();
        for (File f: dr.selectedFiles())
        {
            addWordsFromFile(f);
        }
    }
    
    private int maxNumber()
    {
        int outputMaxNum = 0;
        for(ArrayList v : map.values())
        {
            if(outputMaxNum < v.size())
            {
                outputMaxNum = v.size();
            }
        }
        return outputMaxNum;
    }
    
    private ArrayList<String> wordsInNumFiles(int number)
    {
        ArrayList<String> outputWords = new ArrayList<String>();
        for(String word : map.keySet())
        {
            if(map.get(word).size() == number)
            {
                outputWords.add(word);
            }
        }
        return outputWords;
    }
    
    private void printFilesIn(String word)
    {
        System.out.println("THe files that " + word + " appears in are: ");
        for(String fileName : map.get(word))
        {
            System.out.println(fileName);
        }
    }
    
    public void tester()
    {
        buildWordFileMap();
        /*
        for (String word : map.keySet())
        {
            System.out.println(word);
            for(String fileName : map.get(word))
            {
                System.out.println(map.get(word));
            }
        }*/
        
        System.out.println(map.get("tree"));
        
        int maxNum = maxNumber();
        System.out.println("The maximum number of files any word is in is: " + maxNum);
        ArrayList<String> maxWords = wordsInNumFiles(maxNum);
        
        System.out.println("The number of words that occur in max number of files is: " + maxWords.size());
        
      
        /*for (String word : maxWords)
        {
            printFilesIn(word);
        }*/
    }
    
    
    
    
    
}
