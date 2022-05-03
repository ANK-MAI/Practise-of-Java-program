
/**
 * Write a description of class LogAnalyzer here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;
import edu.duke.*;

public class LogAnalyzer
{
     private ArrayList<LogEntry> records;
     
     public LogAnalyzer() 
     {
         records = new ArrayList<LogEntry>();
     }
        
     public void readFile(String filename) 
     {
         FileResource fr = new FileResource(filename);
         for (String line : fr.lines())
         {
             LogEntry le = WebLogParser.parseEntry(line);
             records.add(le);
         }
     }
        
     public void printAll()
     {
         for (LogEntry le : records)
         {
             System.out.println(le);
         }
     }
     
     public int countUniqueIPs()
     {
         int ipNum = 0;
         ArrayList<String> ipAddresses = new ArrayList<String>();
         for (LogEntry le : records)
         {
             String ipAddress = le.getIpAddress();
             if(!ipAddresses.contains(ipAddress))
             {
                 ipAddresses.add(ipAddress);
                 ipNum ++;
             }
         }
         return ipNum;
     }
     
     public void printAllHigherThanNum(int num)
     {
         for (LogEntry le : records)
         {
             int statusCode = le.getStatusCode();
             if(statusCode > num)
             {
                 System.out.println(le);
             }
         }
     }
     
     public ArrayList<String> uniqueIPVisitsOnDay(String someday)
     {
         ArrayList<String> outputIPs = new ArrayList<String>();
         for (LogEntry le : records)
         {
             String ipAddress = le.getIpAddress();
             String accessTime = le.getAccessTime().toString();
             if(accessTime.indexOf(someday) != -1 && !outputIPs.contains(ipAddress))
             {
                 outputIPs.add(ipAddress);
             }
         }
         return outputIPs;
     }
     
     public int countUniqueIPsInRange(int low, int high)
     {
         int ipNum = 0;
         ArrayList<String> ipAddresses = new ArrayList<String>();
         for (LogEntry le : records)
         {
             String ipAddress = le.getIpAddress();
             int statusCode = le.getStatusCode();
             if(statusCode >= low && statusCode <=high && !ipAddresses.contains(ipAddress))
             {
                 ipAddresses.add(ipAddress);
                 ipNum ++;
             }
         }
         return ipNum;
     }
     
     
     
     
     
}
