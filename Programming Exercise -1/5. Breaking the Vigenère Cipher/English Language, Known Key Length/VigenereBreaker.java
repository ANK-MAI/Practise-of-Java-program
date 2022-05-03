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

    public void breakVigenere () 
    {
        FileResource fr = new FileResource();
        String encryptedStr = fr.asString();
        System.out.println("\n" + encryptedStr);
        
        int[] keys = tryKeyLength(encryptedStr, 4, 'e');
        VigenereCipher vc = new VigenereCipher(keys);
        
        String decryptedStr = vc.decrypt(encryptedStr);
        System.out.println("\n" + decryptedStr);
        
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
}
