import edu.duke.*;

public class CaesarCracker //This class provides an implementation of the Caesar cipher cracking (or breaking) algorithm
{
    char mostCommon;
    
    public CaesarCracker() 
    {
        mostCommon = 'e';
    }
    
    public CaesarCracker(char c)
    {
        mostCommon = c;
    }
    
    public int[] countLetters(String message)
    {
        String alph = "abcdefghijklmnopqrstuvwxyz";
        int[] counts = new int[26];
        for(int k=0; k < message.length(); k++)
        {
            int dex = alph.indexOf(Character.toLowerCase(message.charAt(k)));
            if (dex != -1)
            {
                counts[dex] += 1;
            }
        }
        return counts;
    }
    
    public int maxIndex(int[] vals)
    {
        int maxDex = 0;
        for(int k=0; k < vals.length; k++)
        {
            if (vals[k] > vals[maxDex])
            {
                maxDex = k;
            }
        }
        return maxDex;
    }

    public int getKey(String encrypted)
    {
        int[] freqs = countLetters(encrypted); //Count the number of each letter
        int maxDex = maxIndex(freqs);  //Get the max number of one letter
        int mostCommonPos = mostCommon - 'a'; //Shift from common letter to 'a'
        int dkey = maxDex - mostCommonPos;  //Find the shift from common letter to encrypted letter
        if (maxDex < mostCommonPos) 
        {
            dkey = 26 - (mostCommonPos-maxDex);
        }
        return dkey;
    }
    
    public String decrypt(String encrypted)
    {
        int key = getKey(encrypted);
        CaesarCipher cc = new CaesarCipher(key);
        return cc.decrypt(encrypted);
    }
   
    
    
    
    
    
}
