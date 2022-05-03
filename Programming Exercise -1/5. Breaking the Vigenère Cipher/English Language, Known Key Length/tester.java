
/**
 * Write a description of tester here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import edu.duke.*;
import java.util.*;
import java.io.*;


public class tester 
{
    public void testCaesarCipher()
    {
        CaesarCipher cc = new CaesarCipher(4);  //Create a CaesarCipher object;
        FileResource fr = new FileResource("titus-small.txt");
        String str = fr.asString();
        System.out.println(str);
        
        String encryptedStr = cc.encrypt(str);
        System.out.println("\n" + encryptedStr);
        
        String decryptedStr = cc.decrypt(encryptedStr);
        System.out.println("\n" + decryptedStr);
    }
    
    public void testCaesarCracker()
    {
        CaesarCracker ccr1 = new CaesarCracker();
        FileResource fr1 = new FileResource("titus-small_key5.txt");
        String str1 = fr1.asString();
        System.out.println("\n" + str1);
        
        String decryptedStr1 = ccr1.decrypt(str1);
        System.out.println("\n" + decryptedStr1);
        
        CaesarCracker ccr2 = new CaesarCracker('a');
        FileResource fr2 = new FileResource("oslusiadas_key17.txt");
        String str2 = fr2.asString();
        System.out.println("\n" + str2);
        
        String decryptedStr2 = ccr2.decrypt(str2);
        System.out.println("\n" + decryptedStr2);
    }
    
    public void testVigenereCipher()
    {
        int[] rome = {17,14,12,4};
        VigenereCipher vc = new VigenereCipher(rome);
        
        FileResource fr = new FileResource("titus-small.txt");
        String str = fr.asString();
        System.out.println("\n" + str);
        
        String encryptedStr = vc.encrypt(str);
        System.out.println("\n" + encryptedStr);
        
        String decryptedStr = vc.decrypt(encryptedStr);
        System.out.println("\n" + decryptedStr);
    }
    

}
