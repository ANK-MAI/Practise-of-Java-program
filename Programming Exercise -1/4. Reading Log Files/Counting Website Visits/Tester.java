
/**
 * Write a description of class Tester here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;

public class Tester
{
    public void testLogEntry() 
    {
        LogEntry le = new LogEntry("1.2.3.4", new Date(), "example request", 200, 500);
        System.out.println(le);
        LogEntry le2 = new LogEntry("1.2.100.4", new Date(), "example request 2", 300, 400);
        System.out.println(le2);
    }
    
    public void testLogAnalyzer() 
    {
        LogAnalyzer la = new LogAnalyzer();  //Create an object
        String filename = "weblog-short_log"; 
        la.readFile(filename);
        la.printAll();
    }
    
    public void testUniqueIP()
    {
        LogAnalyzer la = new LogAnalyzer();  //Create an object
        String filename = "weblog2_log"; 
        la.readFile(filename);
        int ipNum = la.countUniqueIPs();
        System.out.println("The number of unique IP addresses is " + ipNum);
    }
    
    public void testPrintAllHigherThanNum()
    {
        LogAnalyzer la = new LogAnalyzer();  //Create an object
        String filename = "weblog1_log"; 
        la.readFile(filename);
        la.printAllHigherThanNum(400);
    }
    
    public void testUniqueIPVisitsOnDay()
    {
        LogAnalyzer la = new LogAnalyzer();  //Create an object
        String filename = "weblog2_log"; 
        la.readFile(filename);
        ArrayList<String> uniqueIPs = la.uniqueIPVisitsOnDay("Sep 27");
        System.out.println("The IPs which visited on that day are " + uniqueIPs);
    }
    
    public void testCountUniqueIPsInRange()
    {
        LogAnalyzer la = new LogAnalyzer();  //Create an object
        String filename = "weblog2_log"; 
        la.readFile(filename);
        int ipNum = la.countUniqueIPsInRange(200, 299);
        System.out.println("The number of unique IP addresses in the range is " + ipNum);
    }
    
    public void testCountVisitsPerIP()
    {
        LogAnalyzer la = new LogAnalyzer();  //Create an object
        String filename = "weblog1_log"; 
        la.readFile(filename);
        HashMap<String, Integer> ipMap = la.countVisitsPerIP();
        System.out.println("IP / Visit time: " + ipMap);
    }
    
    public void testMostNumberVisitsByIP()
    {
        LogAnalyzer la = new LogAnalyzer();  //Create an object
        String filename = "weblog2_log"; 
        la.readFile(filename);
        HashMap<String, Integer> ipMap = la.countVisitsPerIP();
        System.out.println("IP / Visit time: " + ipMap);
        int mostNum = la.mostNumberVisitsByIP(ipMap);
        System.out.println("The maximum number of visits to this website by a single IP address: " + mostNum);
    }
    
    public void testIPsMostVisits()
    {
        LogAnalyzer la = new LogAnalyzer();  //Create an object
        String filename = "weblog2_log"; 
        la.readFile(filename);
        HashMap<String, Integer> ipMap = la.countVisitsPerIP();
        System.out.println("IP / Visit time: " + ipMap);
        ArrayList<String> maxIPs = la.iPsMostVisits(ipMap);
        System.out.println("The IP addresses that have the maximum number of visits to this website: " + maxIPs);
    }
    
    public void testIPsForDays()
    {
        LogAnalyzer la = new LogAnalyzer();  //Create an object
        String filename = "weblog3-short_log"; 
        la.readFile(filename);
        HashMap<String, ArrayList<String>> daysIPsMap = la.iPsForDays();
        System.out.println(daysIPsMap);
    }
    
    public void testDayWithMostIPVisits()
    {
        LogAnalyzer la = new LogAnalyzer();  //Create an object
        String filename = "weblog2_log"; 
        la.readFile(filename);
        HashMap<String, ArrayList<String>> daysIPsMap = la.iPsForDays();
        System.out.println(daysIPsMap);
        String maxDay = la.dayWithMostIPVisits(daysIPsMap);
        System.out.println("The day that has the most IP address visits: " + maxDay);
    }
    
    public void testIPsWithMostVisitsOnDay()
    {
        LogAnalyzer la = new LogAnalyzer();  //Create an object
        String filename = "weblog2_log"; 
        la.readFile(filename);
        HashMap<String, ArrayList<String>> daysIPsMap = la.iPsForDays();
        System.out.println(daysIPsMap);
        ArrayList<String> maxIPs = la.iPsWithMostVisitsOnDay(daysIPsMap,"Sep 30");
        System.out.println("The IP addresses that had the most accesses on the given day: " + maxIPs);
    }
    
}
