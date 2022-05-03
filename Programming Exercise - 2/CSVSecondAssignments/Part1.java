
/**
 * Write a description of Part1 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import edu.duke.*;
import org.apache.commons.csv.*;
import java.io.*;

public class Part1 
{
    public CSVRecord coldestHourInFile(CSVParser parser)
    {
        CSVRecord coldestSoFar = null; //Start with coldestSoFar as nothing
        double currentTemp = 0.0;
        double coldestTemp = 0.0;
        for (CSVRecord currentRow : parser) //For each row in the CSV File
        {
            currentTemp = Double.parseDouble(currentRow.get("TemperatureF"));
            
            if (currentTemp != -9999 && coldestSoFar == null)
            {
                coldestSoFar = currentRow;  //Get the initial record
                coldestTemp = currentTemp; //Get the initial temperature
            }
            else if (currentTemp != -9999 && currentTemp < coldestTemp) //Check if currentRow’s temperature < coldestSoFar’s
            {
                coldestSoFar = currentRow; //If so update coldestSoFar to currentRow
                coldestTemp = currentTemp;
            }
        }
        return coldestSoFar; //The coldestSoFar is the answer
    }
    public void testColdestHourInFile()
    {
        CSVRecord coldestInfo = null; //Start with coldestSoFar as nothing
        FileResource fr = new FileResource(); //Choose and open the csv file
        CSVParser parser = fr.getCSVParser(); //Parse the csv file
        coldestInfo = coldestHourInFile(parser); //Find out the information about the coldest temperature
        System.out.println("The coldest temperature was " + coldestInfo.get("TemperatureF") +
                   " at " + coldestInfo.get("DateUTC"));
    }
    
    public String fileWithColdestTemperature()
    {
        String outputFileName = "";
        CSVRecord coldestSoFar = null;
        double coldestTemp = 0.0;
        DirectoryResource dr = new DirectoryResource(); //Open several files
        for (File f : dr.selectedFiles())
        {
            FileResource fr = new FileResource(f);
            CSVParser parser = fr.getCSVParser(); //Parse the csv file
            CSVRecord currentRow = coldestHourInFile(parser); //Find out the information about the coldest temperature
        
            double currentTemp = Double.parseDouble(currentRow.get("TemperatureF"));
            
            if (coldestSoFar == null)
            {
                coldestSoFar = currentRow;  //Get the initial record
                coldestTemp = currentTemp; //Get the initial temperature
                outputFileName = f.getName();
            }
            else if (currentTemp < coldestTemp) //Check if currentRow’s temperature < coldestSoFar’s
            {
                coldestSoFar = currentRow; //If so update coldestSoFar to currentRow
                coldestTemp = currentTemp;
                outputFileName = f.getName();
            }
        }
        return outputFileName;
    }
    public void testFileWithColdestTemperature()
    {
        String coldestDay = fileWithColdestTemperature();
        FileResource fr = new FileResource("data/2013/" + coldestDay);
        CSVParser parser = fr.getCSVParser();
        CSVRecord coldestInfo = coldestHourInFile(parser);
        
        System.out.println("The coldest day was in file " + coldestDay);
        System.out.println("The coldest temperature on that day was " + coldestInfo.get("TemperatureF") +
                   " at " + coldestInfo.get("DateUTC"));
        System.out.println("All temperatures on the coldest day were:");
        
        parser = fr.getCSVParser();
        for (CSVRecord row : parser)
        {
            System.out.println(row.get("DateUTC") + ": " + row.get("TemperatureF"));
        }
    }
    
    public String fileWithLowestHumidity()
    {
        String outputFileName = "";
        CSVRecord lowestSoFar = null;
        int lowestHum = 0;
        DirectoryResource dr = new DirectoryResource(); //Open several files
        for (File f : dr.selectedFiles())
        {
            FileResource fr = new FileResource(f);
            CSVParser parser = fr.getCSVParser(); //Parse the csv file
            CSVRecord currentRow = lowestHumidityInFile(parser); //Find out the information about the lowest humidity
        
            int currentHum = Integer.parseInt(currentRow.get("Humidity"));
            
            if (lowestSoFar== null)
            {
                lowestSoFar = currentRow;  //Get the initial record
                lowestHum = currentHum; //Get the initial humidity
                outputFileName = f.getName();
            }
            else if (currentHum < lowestHum) //Check if currentRow’s humidity < lowestSoFar’s
            {
                lowestSoFar = currentRow; //If so update lowestSoFar to currentRow
                lowestHum = currentHum;
                outputFileName = f.getName();
            }
        }
        return outputFileName;
    }
    public void testFileWithLowestHumidity()
    {
        String lowestDay = fileWithLowestHumidity();
        
        FileResource fr = new FileResource("data/2013/" + lowestDay);
        CSVParser parser = fr.getCSVParser();
        CSVRecord lowestInfo = lowestHumidityInFile(parser);
        
        System.out.println("The lowest day was in file " + lowestDay);
        System.out.println("The lowest humidity on that day was " + lowestInfo.get("Humidity") +
                   " at " + lowestInfo.get("DateUTC"));
        System.out.println("All humidities on the lowest day were:");
        
        parser = fr.getCSVParser();
        for (CSVRecord row : parser)
        {
            System.out.println(row.get("DateUTC") + ": " + row.get("Humidity"));
        }
    }
    
    public CSVRecord lowestHumidityInFile(CSVParser parser)
    {
        CSVRecord lowestSoFar = null; //Start with lowestSoFar as nothing
        int currentHum = 0;
        int lowestHum = 0;
        for(CSVRecord currentRow : parser)
        {        
            if((currentRow.get("Humidity")).contains("N/A")) //If the humidity is N/A, skip. !!!!Can not compare the string!!!!!
            {
                continue;
            }
            currentHum = Integer.parseInt(currentRow.get("Humidity"));
            if(lowestSoFar == null)
            {
                lowestSoFar = currentRow; //Get initial row
                lowestHum = currentHum; //Get initial humidity
            }
            else if(currentHum < lowestHum)
            {
                lowestSoFar = currentRow;
                lowestHum = currentHum;
            }
        }
        return lowestSoFar;
    }
    public void testLowestHumidityInFile()
    {
        CSVRecord lowestInfo = null; //Start with lowestInfo as nothing
        FileResource fr = new FileResource(); //Choose and open the csv file
        CSVParser parser = fr.getCSVParser(); //Parse the csv file
        lowestInfo = lowestHumidityInFile(parser); //Find out the information about the lowest humidity
        System.out.println("The lowest humidity was " + lowestInfo.get("Humidity") +
                   " at " + lowestInfo.get("DateUTC"));
    }
    
    public double averageTemperatureInFile(CSVParser parser)
    {
        double outputAvgTemp = 0.0;
        double currentTemp = 0.0;
        double totalTemp = 0.0;
        int count = 0;
        for (CSVRecord currentRow : parser) //For each row in the CSV File
        {
            currentTemp = Double.parseDouble(currentRow.get("TemperatureF"));
            
            if (currentTemp != -9999)
            {
                totalTemp = totalTemp + currentTemp; 
                count ++;
            }
            else
            {
                count ++;
            }
        }
        outputAvgTemp = totalTemp / count;
        return outputAvgTemp;
    }
    public void testAverageTemperatureInFile()
    {
        double avgTemp = 0.0;
        FileResource fr = new FileResource(); //Choose and open the csv file
        CSVParser parser = fr.getCSVParser(); //Parse the csv file
        avgTemp = averageTemperatureInFile(parser); //Calculate the average temperature in a csv file
        System.out.println("The average temperature in file is " + avgTemp);
    }
    
    public double averageTemperatureWithHighHumidityInFile(CSVParser parser, int value)
    {
        double outputAvgTemp = 0.0;
        double currentTemp = 0.0;
        int currentHum = 0;
        double totalTemp = 0.0;
        int count = 0;
        for (CSVRecord currentRow : parser) //For each row in the CSV File
        {
            currentTemp = Double.parseDouble(currentRow.get("TemperatureF"));
            currentHum = Integer.parseInt(currentRow.get("Humidity"));
            if (currentTemp != -9999 && currentHum >= value)
            {
                totalTemp = totalTemp + currentTemp; 
                count ++;
            }
        }
        if(count != 0)
        {
            outputAvgTemp = totalTemp / count;
        }
        return outputAvgTemp;
    }
    public void testAverageTemperatureWithHighHumidityInFile()
    {
        double avgTempAtHighTemp = 0.0;
        int humLimit = 80;
        FileResource fr = new FileResource(); //Choose and open the csv file
        CSVParser parser = fr.getCSVParser(); //Parse the csv file
        avgTempAtHighTemp = averageTemperatureWithHighHumidityInFile(parser, humLimit); //Calculate the average temperature at high humidity
        if(avgTempAtHighTemp == 0.0)
        {
            System.out.println("No temperatures with that humidity.");
        }
        else
        {
            System.out.println("The average temperature when high Humidity is " + avgTempAtHighTemp);
        }
    }
}
