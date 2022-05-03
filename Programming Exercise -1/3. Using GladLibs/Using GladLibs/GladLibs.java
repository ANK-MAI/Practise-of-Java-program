
/**
 * Write a description of GladLibs here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import edu.duke.*;
import java.util.*;
import java.io.*;

public class GladLibs 
{
    private ArrayList<String> adjectiveList;  //Instance variable: array list
    private ArrayList<String> nounList;  //Instance variable: array list
    private ArrayList<String> colorList;  //Instance variable: array list
    private ArrayList<String> countryList;  //Instance variable: array list
    private ArrayList<String> nameList;  //Instance variable: array list
    private ArrayList<String> animalList;  //Instance variable: array list
    private ArrayList<String> timeList;  //Instance variable: array list
    private ArrayList<String> verbList;  //Instance variable: array list
    private ArrayList<String> fruitList;  //Instance variable: array list
    private ArrayList<String> usedList;  //Instance variable: array list, this list is to stored the words which have been used for one time
    
    private Random myRandom;   //Random class specified by edu.duke package. This class provides methods for generating random numbers. 
    
    private static String dataSourceURL = "http://dukelearntoprogram.com/course3/data";  //URL link in which the contents stored
    private static String dataSourceDirectory = "datalong";  //Folder in which the .txt file stored
    
    public GladLibs()  //Constructor without parameter
    {
        initializeFromSource(dataSourceDirectory);
        myRandom = new Random();  //Initialize myRandom variable, creates a new object for generating random numbers
        usedList = new ArrayList<String>();
    }
    
    
    public GladLibs(String source)  //Constructor with one parameter
    {
        initializeFromSource(source);
        myRandom = new Random();   //Initialize myRandom variable, creates a new object for generating random numbers
        usedList = new ArrayList<String>();
    }
    
    
    
    private ArrayList<String> readIt(String path) //Read the link / file and put the contents into the array list
    {
        ArrayList<String> outputList = new ArrayList<String>(); //Initialize the output array list
        if (path.startsWith("http")) //If the path is URL link
        {
            URLResource resource = new URLResource(path);
            for(String line : resource.lines())
            {
                outputList.add(line);
            }
        }
        else //If the path is file path
        {
            FileResource resource = new FileResource(path);
            for(String line : resource.lines())
            {
                outputList.add(line);
            }
        }
        return outputList;
    }
    private void initializeFromSource(String source) //Read the link / file and put the contents into the array list
    {
        adjectiveList= readIt(source+"/adjective.txt");	
        nounList = readIt(source+"/noun.txt");
        colorList = readIt(source+"/color.txt");
        countryList = readIt(source+"/country.txt");
        nameList = readIt(source+"/name.txt");
        animalList = readIt(source+"/animal.txt");
        timeList = readIt(source+"/timeframe.txt");
        verbList = readIt(source+"/verb.txt");
        fruitList = readIt(source+"/fruit.txt");
    }
    
    
    
    private String randomFrom(ArrayList<String> list) //Get a random content from the specific array list
    {
        int index = myRandom.nextInt(list.size());  //Returns a random integer in the range of 0 to source.size(), inclusive
        return list.get(index);  //Return the content in this random index of array list
    }
	
    
    private String getSubstitute(String label)  //Replace the placeholder words with corresponding random word from right array list
    {
        if (label.equals("country")) {
            return randomFrom(countryList);
        }
        if (label.equals("color")){
            return randomFrom(colorList);
        }
        if (label.equals("noun")){
            return randomFrom(nounList);
        }
        if (label.equals("name")){
            return randomFrom(nameList);
        }
        if (label.equals("adjective")){
            return randomFrom(adjectiveList);
        }
        if (label.equals("animal")){
            return randomFrom(animalList);
        }
        if (label.equals("timeframe")){
            return randomFrom(timeList);
        }
        if (label.equals("verb")){
            return randomFrom(verbList);
        }
        if (label.equals("fruit")){
            return randomFrom(fruitList);
        }
        if (label.equals("number")){
            return ""+myRandom.nextInt(50)+5;
        }
        return "**UNKNOWN**";
    }
    
    private String processWord(String word)  //Look for the placeholder words and get them replaced by random word from right array list
    {
        int first = word.indexOf("<");
        int last = word.indexOf(">",first);
        if (first == -1 || last == -1)  //If the word is not the placeholder word, keep this word
        {
            return word;
        }
        String prefix = word.substring(0,first);
        String suffix = word.substring(last+1);
        String replacedWord = "";
        while (true)
        {
            replacedWord = getSubstitute(word.substring(first+1,last)); //Get the corresponding random word from right array list 
            if (usedList.indexOf(replacedWord) != 1) //If this random word is not in the usedList, add it in this list, and apply this random word, else keep rolling the random word.
            {
               usedList.add(replacedWord);
               break; 
            }
        }
        return prefix+replacedWord+suffix;
    }
    
    /*
    private void printOut(String story, int lineWidth) //
    {
        int charsWritten = 0;
        for(String word : story.split("\\s+"))  //Returns an array of substrings of the original string, the answer array does not include the separators
        {
            if (charsWritten + word.length() > lineWidth)
            {
                System.out.println();
                charsWritten = 0;
            }
            System.out.print(word+" ");
            charsWritten += word.length() + 1;
        }
    }*/
    
    private String fromTemplate(String templatePath)  //This method is used to create the random story
    {
        String outputStory = "";  //Output completed random story
        if (templatePath.startsWith("http")) //If the template path is URL link
        {  
            URLResource resource = new URLResource(templatePath);
            for(String word : resource.words())
            {
                outputStory = outputStory + processWord(word) + " ";
            }
        }
        else //If the template path is file path
        {
            FileResource resource = new FileResource(templatePath);
            for(String word : resource.words())
            {
                outputStory = outputStory + processWord(word) + " ";
            }
        }
        return outputStory;
    }
    
    
    public void makeStory()
    {
        usedList.clear();
        System.out.println("\n");
        String story = fromTemplate("datalong/madtemplate2.txt");
        System.out.println(story);
        //printOut(story, 60);
        System.out.println("The total number of words that were replaced: " + usedList.size());
    }
    
    
    
    
}





