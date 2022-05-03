
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
     
     public HashMap<String,Integer>  countVisitsPerIP()
     {
         HashMap<String,Integer> outputMap = new HashMap<String,Integer>();
         for (LogEntry le : records)
         {
             String ipAddress = le.getIpAddress();
             if(outputMap.get(ipAddress) != null)
             {
                 int visitTime = outputMap.get(ipAddress) + 1;
                 outputMap.put(ipAddress, visitTime);
             }
             else
             {
                 outputMap.put(ipAddress, 1);
             }
         }
         return outputMap;
     }
     
     public int mostNumberVisitsByIP(HashMap<String, Integer> map)
     {
         String maxIP ="";
         int outputMaxTime = 0; 
         for(String s : map.keySet())
         {
             if(map.get(s) > outputMaxTime)
             {
                 outputMaxTime = map.get(s);
                 maxIP = s;
             }
         }
         return outputMaxTime;
     }
     
     public ArrayList<String> iPsMostVisits (HashMap<String, Integer> map)
     {
         ArrayList<String> outputMaxIPs = new ArrayList<String>();
         int maxTime = 0; 
         for(String s : map.keySet())
         {
             if(map.get(s) > maxTime)
             {
                 maxTime = map.get(s);
             }
         }
         for(String s : map.keySet())
         {
             if(map.get(s) == maxTime)
             {
                 outputMaxIPs.add(s);
             }
         }
         return outputMaxIPs;
     }
     
     public HashMap<String, ArrayList<String>> iPsForDays()
     {
         HashMap<String, ArrayList<String>> outputDaysIPsMap = new HashMap<String, ArrayList<String>>();
         for (LogEntry le : records)
         {
             String ipAddress = le.getIpAddress();
             String accessTime = le.getAccessTime().toString();
             String accessDay = accessTime.substring((accessTime.indexOf(" ")+1),(accessTime.indexOf(" ")+7));
             if(outputDaysIPsMap.get(accessDay) != null)
             {
                 outputDaysIPsMap.get(accessDay).add(ipAddress);
             }
             else
             {
                 ArrayList<String> ips = new ArrayList<String>();
                 ips.add(ipAddress);
                 outputDaysIPsMap.put(accessDay,ips);
             }
         }
         
         return outputDaysIPsMap;
     }
     
     public String dayWithMostIPVisits(HashMap<String, ArrayList<String>> map)
     {
         String outputDay = "";
         int ipNum = 0;
         for (String day : map.keySet())
         {
             if(ipNum < map.get(day).size())
             {
                 ipNum = map.get(day).size();
                 outputDay = day;
             }
         }
         return outputDay;
     }
     
     public ArrayList<String> iPsWithMostVisitsOnDay(HashMap<String, ArrayList<String>> map, String day) // This method returns an ArrayList<String> of IP addresses that had the most accesses on the given day.
     {
         ArrayList<String> outputIPs = new ArrayList<String>();
         HashMap<String, Integer> IPsNumMap = new HashMap<String, Integer>();
         for (String ip : map.get(day))
         {
            if (IPsNumMap.get(ip) != null)
            {
                IPsNumMap.put(ip, IPsNumMap.get(ip)+1); 
            }
            else
            {
                IPsNumMap.put(ip, 1);
            }
         }
         
         int ipNum = 0;
         for (String ip : IPsNumMap.keySet())
         {
            if(ipNum < IPsNumMap.get(ip))
            {
                ipNum = IPsNumMap.get(ip);
            }
         }
         for (String ip : IPsNumMap.keySet())
         {
            if(ipNum == IPsNumMap.get(ip))
            {
                outputIPs.add(ip);
            }
         }
         return outputIPs;
     }
     
     
     
     
}
