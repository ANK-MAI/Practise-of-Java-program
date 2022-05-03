
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
    public void totalBirths(FileResource fr)
    {
        int totalGirls = 0;
        int totalBoys = 0;
        int totalBirths = 0;
        CSVParser parser = fr.getCSVParser(false);  //when parser the file, if there is not header for the sheet, need to add atribute 'false'.
        for (CSVRecord record : parser)
        {
            int numBorn = Integer.parseInt(record.get(2));
            totalBirths = totalBirths + numBorn;
            if(record.get(1).equals("M"))
            {
                totalBoys = totalBoys + numBorn;
            }
            else
            {
                totalGirls = totalGirls + numBorn;
            }
        }
        System.out.println("The number of girls names is " + totalGirls);
        System.out.println("The number of boys names is " + totalBoys);
        System.out.println("The number of total births is " + totalBirths);
    }
    public void testTotalBirths()
    {
        FileResource fr = new FileResource();
        totalBirths(fr);
    }
    
    
    public int getRank(int year,String name,String gender)
    {
        int outputRank = -1;
        int currentRank = 0;
        FileResource fr = new FileResource("data/yob" + year + ".csv");
        CSVParser parser = fr.getCSVParser(false);  //when parser the file, if there is not header for the sheet, need to add atribute 'false'.
        for (CSVRecord record : parser)
        {
            if(record.get(1).equals(gender))
            {
                currentRank ++;
                if(record.get(0).equals(name))
                {
                    outputRank = currentRank;
                    break;
                }
            }
        }
        return outputRank;
    }
    public void testGetRank()
    {
        int year = 1971;
        String name = "Frank";
        String gender = "M";
        int rank = getRank(year,name,gender);
        System.out.println("The rank of this name is " + rank);
    }
    
    
    public String getName(int year,int rank,String gender)
    {
        String outputName = "NO NAME";
        int currentRank = 0;
        FileResource fr = new FileResource("data/yob" + year + ".csv");
        CSVParser parser = fr.getCSVParser(false);  //when parser the file, if there is not header for the sheet, need to add atribute 'false'.
        for (CSVRecord record : parser)
        {
            if(record.get(1).equals(gender))
            {
                currentRank ++;
                if(currentRank == rank)
                {
                    outputName = record.get(0);
                    break;
                }
            }
        }
        return outputName;
    }
    public void testGetName()
    {
        int year = 1982;
        int rank = 450;
        String gender = "M";
        String name= getName(year,rank,gender);
        System.out.println("The name at this rank is " + name);
    }
    
    
    public void whatIsNameInYear(String name,int year,int newYear,String gender) // This method determines what name would have been named if they were born in a different year, based on the same popularity.
    {
        int oldYearRank = 0;
        String newYearName = "NO NAME";
        oldYearRank = getRank(year,name,gender);
        newYearName = getName(newYear,oldYearRank,gender);
        System.out.println(name + " born in " + year + " would be " + newYearName + " if he/she was born in " + newYear + ".");
    }
    public void testWhatIsNameInYear()
    {
        String name = "Owen";
        String gender = "M";
        int year = 1974;
        int newYear = 2014;
        whatIsNameInYear(name,year,newYear,gender);
    }
    
    
    public int yearOfHighestRank(String name, String gender)
    {
        int outputYear = -1;
        int rankSoFar = 0;
        DirectoryResource dr = new DirectoryResource(); //Open several files
        for(File f : dr.selectedFiles()) //For each file
        {
            int currentRank = 0;
            String fileName = f.getName();
            int currentYear = Integer.parseInt(fileName.substring(3,7));
            FileResource fr = new FileResource(f); 
            CSVParser parser = fr.getCSVParser(false); //Parse the csv file
            for (CSVRecord record : parser)
            {
                if(record.get(1).equals(gender))
                {
                    currentRank ++;
                    if(record.get(0).equals(name))
                    {
                        if(rankSoFar == 0)
                        {
                            rankSoFar = currentRank;
                            outputYear = currentYear;
                            break;
                        }
                        else if(currentRank < rankSoFar)
                        {
                            rankSoFar = currentRank;
                            outputYear = currentYear;
                            break;
                        }
                    }
                }
            }
        }
        return outputYear;
    }
    public void testYearOfHighestRank()
    {
        String name = "Mich";
        String gender = "M";
        int highestRankYear = yearOfHighestRank(name, gender);
        System.out.println(name + " born in " + highestRankYear + " would have the highest rank.");
    }
    
    public double getAverageRank(String name,String gender)
    {
        double outputAvgRank = -1.0;
        double totalRank = 0.0;
        int yearNum = 0;
        DirectoryResource dr = new DirectoryResource(); //Open several files
        for(File f : dr.selectedFiles()) //For each file
        {
            yearNum ++;
            double currentRank = 0.0;
            FileResource fr = new FileResource(f); 
            CSVParser parser = fr.getCSVParser(false); //Parse the csv file
            for (CSVRecord record : parser)
            {
                if(record.get(1).equals(gender))
                {
                    currentRank ++;
                    if(record.get(0).equals(name))
                    {
                        if(totalRank == 0.0)
                        {
                            totalRank = currentRank;
                            break;
                        }
                        else
                        {
                            totalRank = totalRank + currentRank;
                            break;
                        }
                    }
                }
            }
        }
        outputAvgRank = totalRank / yearNum;
        return outputAvgRank;
    }
    public void testGetAverageRank() 
    {
        String name = "Robert";
        String gender = "M";
        double avgRank = getAverageRank(name, gender);
        System.out.println(name + " has average rank: " + avgRank);
    }
    
    
    public int getTotalBirthsRankedHigher(int year, String name, String gender) //The total number of births of those names with the same gender and same year who are ranked higher than name.
    {
        int outputTotalNum = 0;
        FileResource fr = new FileResource("data/yob" + year + ".csv"); 
        CSVParser parser = fr.getCSVParser(false); //Parse the csv file
        for (CSVRecord record : parser)
        {
             if(record.get(1).equals(gender))
             {
                if(record.get(0).equals(name))
                {
                    break;
                }
                else
                {
                    outputTotalNum = outputTotalNum + Integer.parseInt(record.get(2));
                }
             }
        }
        return outputTotalNum;
    }
    public void testGetTotalBirthsRankedHigher()
    {
        String name = "Drew";
        int year = 1990;
        String gender = "M";
        int totalBirths = getTotalBirthsRankedHigher(year,name, gender);
        System.out.println("The total births with names ranking higher than " + name + " is: " + totalBirths);
    }
    
    
    
    
    
}
