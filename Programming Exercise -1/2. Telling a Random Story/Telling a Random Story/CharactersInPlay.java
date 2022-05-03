
/**
 * Write a description of CharactersInPlay here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;
import edu.duke.*;
import java.io.*;

public class CharactersInPlay 
{
    private ArrayList<String> characters; //Array list to store the names of the characters
    private ArrayList<Integer> counts;  //Array list to store the corresponding counts for each character
    
    public CharactersInPlay()
    {
        characters = new ArrayList<String>();  
        counts = new ArrayList<Integer>();
    }
    
    public void update(String person)
    {
        int index = characters.indexOf(person);
        if (index != -1)
        {
            int oldCount = counts.get(index);
            counts.set(index, oldCount + 1);
        }
        else
        {
            characters.add(person);
            counts.add(1);
        }
    }
   
    
    public void findAllCharacters() //Count the characters' appearance
    {
        characters.clear(); //Clear the array list
        counts.clear();  //Clear the array list
        
        FileResource fr = new FileResource();
        for(String line : fr.lines())
        {
            int periodIndex = line.indexOf('.');
            if (periodIndex != -1)
            {
                int ch = line.charAt(periodIndex - 1);
                if( ch >= 65 && ch <= 90)
                {
                    String personName = line.substring (0, periodIndex);
                    update(personName);
                }
            }
        }
    }
    
    public void tester()
    {
        findAllCharacters();
        for(int i = 0; i < characters.size(); i++)
        {
            if(counts.get(i) > 1)
            {
                System.out.println("Main character / count: " + characters.get(i) + " " + counts.get(i));
            }
        }
        charactersWithNumParts(10, 15);
    }
    
    public void charactersWithNumParts(int num1, int num2)
    {
        for (int i = 0; i < characters.size(); i++)
        {
            if(counts.get(i) >= num1 && counts.get(i) <= num2)
            {
                System.out.println("Main character / counts within " + num1 + " and "+ num2 + ": " + characters.get(i) + " " + counts.get(i));
            }
        }
    }
}
