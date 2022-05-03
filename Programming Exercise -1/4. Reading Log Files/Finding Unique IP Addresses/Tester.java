
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
        String filename = "short-test_log"; 
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
        String filename = "weblog1_log"; 
        la.readFile(filename);
        ArrayList<String> uniqueIPs = la.uniqueIPVisitsOnDay("Mar 17");
        System.out.println("The IPs which visited on that day are " + uniqueIPs);
    }
    
    public void testCountUniqueIPsInRange()
    {
        LogAnalyzer la = new LogAnalyzer();  //Create an object
        String filename = "weblog1_log"; 
        la.readFile(filename);
        int ipNum = la.countUniqueIPsInRange(200, 299);
        System.out.println("The number of unique IP addresses in the range is " + ipNum);
    }
}
