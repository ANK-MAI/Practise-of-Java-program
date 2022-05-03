
/**
 * Write a description of Part1 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import edu.duke.*;
import org.apache.commons.csv.*;

public class Part1 
{
    public String countryInfo(CSVParser parser, String country) //This method returns a string of information about the country or returns “NOT FOUND” if there is no information about the country. 
    {
        String outputInfo = "";
        for(CSVRecord record : parser) //for each row in the CSV File
        {
            String getCountry = record.get("Country"); //Look at the "Exports" column
            if(getCountry.contains(country))
            {
                outputInfo = getCountry + ": " + record.get("Exports") + ": " + record.get("Value (dollars)");
                break;
            }
            else
            {
                outputInfo = "NOT FOUND";
            }
        }
        return outputInfo; //The format of the string returned is the country, followed by “: “, followed by a list of the countries’ exports, followed by “: “, followed by the countries export value.
    }
    
    public void listExportersTwoProducts(CSVParser parser, String exportItem1, String exportItem2) //This method prints the names of all the countries that have both exportItem1 and exportItem2 as export items.
    {
        for(CSVRecord record : parser) //for each row in the CSV File
        {
            String ExportProduct = record.get("Exports");
            if(ExportProduct.contains(exportItem1) && ExportProduct.contains(exportItem2))
            {
                System.out.println(record.get("Country"));
            }
        }
    }
    
    public int numberOfExporters(CSVParser parser, String exportItem) //This method returns the number of countries that export exportItem. 
    {
        int numCountry = 0;
        for(CSVRecord record : parser) //for each row in the CSV File
        {
            String ExportProduct = record.get("Exports");
            if(ExportProduct.contains(exportItem))
            {
                numCountry ++;
            }
        }
        return numCountry;
    }
    
    public void bigExporters(CSVParser parser,String amount) // This method prints the names of countries and their Value amount for all countries whose Value (dollars) string is longer than the amount string.
    {
        for(CSVRecord record : parser) //for each row in the CSV File
        {
            String ExportValue = record.get("Value (dollars)");
            if(ExportValue.length() > amount.length())
            {
                System.out.println(record.get("Country") + " " + ExportValue);
            }
        }
    }
    
    public void tester()
    {
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        
        String countrySelect = countryInfo(parser, "Nauru");
        System.out.println(countrySelect);
        
        parser = fr.getCSVParser(); //Reset the parser
        listExportersTwoProducts(parser, "cotton", "flowers");
        
        parser = fr.getCSVParser(); //Reset the parser
        int NumCountry = numberOfExporters(parser, "cocoa");
        System.out.println("The number of countries that export the specific item is: " + NumCountry);
        
        parser = fr.getCSVParser(); //Reset the parser
        bigExporters(parser,"$999,999,999,999");
        
    }
    
}
