import java.util.*;
import edu.duke.*;

public class VigenereBreaker 
{
    public String sliceString(String message, int whichSlice, int totalSlices) 
    {
        StringBuilder outputSlicedStr = new StringBuilder();;
        for(int i = whichSlice; i < message.length(); i = i + totalSlices)
        {
            char letter = message.charAt(i);
            outputSlicedStr.append(letter);
        }
        return outputSlicedStr.toString();
    }

    public int[] tryKeyLength(String encrypted, int klength, char mostCommon) 
    {
        int[] keys = new int[klength];
        CaesarCracker ccr =new CaesarCracker(mostCommon);
        for(int i =0; i < klength ; i++)
        {
            String slicedStr = sliceString(encrypted, i, klength);
            int key = ccr.getKey(slicedStr);
            keys[i] = key;
        }
        return keys;
    }

    public HashSet<String> readDictionary(FileResource fr) // Read the dictionary and put it into hashset
    {
        HashSet<String> words = new HashSet<String>();
        for(String line : fr.lines())
        {
            words.add(line.toLowerCase());
        }
        return words;
    }
    
    public int countWords(String message, HashSet<String> dictionary)
    {
        int outputCount = 0;
        String[] words = message.split("\\W+");
        for (int i = 0; i < words.length; i++)
        {
            String word = words[i].toLowerCase();
            if (dictionary.contains(word))
            {
                outputCount ++;
            }
        }
        return outputCount;
    }
    
    public void breakForLanguage(String encrypted, HashSet<String> dictionary)
    {
        HashMap<Integer,Integer> keyLengthMap = new HashMap<Integer,Integer>();  //Map between keylength and number of real word 
        for (int i = 1; i <101; i++) 
        {
            int[] keys = tryKeyLength(encrypted, i, 'e');
            
            VigenereCipher vc = new VigenereCipher(keys);
            String decrypted = vc.decrypt(encrypted);
        
            int numRealWords = countWords(decrypted, dictionary);
            System.out.println(i + " " + numRealWords);
            
            keyLengthMap.put(i, numRealWords);
        }
        
        int rightKeyLength = 0;
        int maxNumRealWords = 0;
        for (int keyLength : keyLengthMap.keySet())
        {
            if (keyLengthMap.get(keyLength) > maxNumRealWords)
            {
                maxNumRealWords = keyLengthMap.get(keyLength);
                rightKeyLength = keyLength;
            }
        }
        
        int[] keys = tryKeyLength(encrypted, rightKeyLength, 'e');
        System.out.println("\n" + "The correct keys are: ");
        for(int i = 0; i < keys.length; i++)
        {
           System.out.println(keys[i]);
        }
        VigenereCipher vc = new VigenereCipher(keys);
        
        String decrypted = vc.decrypt(encrypted);
        System.out.println("\n" + decrypted.substring(0,200));
    }   
    
    
 
    public void tester()
    {
        String str = sliceString ("abcdefghijklm", 1, 3);
        System.out.println(str);
    }
    
    public void testTryKeyLength()
    {
        FileResource fr = new FileResource("secretmessage1.txt");
        String str = fr.asString();
        System.out.println("\n" + str);
        
        int[] keys = tryKeyLength(str, 4, 'e');
        for(int i = 0; i < keys.length; i++)
        {
           System.out.println(keys[i]);
        }
    }
    
    public void breakVigenere () 
    {
        FileResource fr = new FileResource();
        String encryptedStr = fr.asString();
        System.out.println("\n" + encryptedStr);
        
        FileResource dictionaryFile = new FileResource("dictionaries/English");  //Choose the dictionary
        HashSet<String> dictionary = readDictionary(dictionaryFile);  //Put the dictionary into the hashset
        
        breakForLanguage(encryptedStr, dictionary);

    }
    
}
