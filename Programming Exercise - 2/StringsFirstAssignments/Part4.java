
/**
 * Write a description of Part4 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import edu.duke.*;

public class Part4
{
    public StorageResource findURLs(String url) 
    {
        URLResource page = new URLResource(url);
        String source = page.asString();
        System.out.println(source);
        StorageResource outputLink = new StorageResource();
        int currIndex = 0;
        while (true) 
        {
            int index = source.indexOf("href=", currIndex);
            if (index == -1) 
            {
                break;
            }
            int firstQuote = index+6; // after href="
            int endQuote = source.indexOf("\"", firstQuote);
            String sub = source.substring(firstQuote, endQuote);
            if (sub.startsWith("http") && sub.contains("youtube.com"))
            {
                outputLink.add(sub);
            }
            currIndex = endQuote + 1;
        }
        return outputLink;
    }

    public void testURL()
    {
        StorageResource s1 = findURLs("https://www.dukelearntoprogram.com//course2/data/manylinks.html"); //s1 is a list of string.
        for (String link : s1.data()) 
        {
            System.out.println(link);
        }
        System.out.println("size = " + s1.size());
    }
}
