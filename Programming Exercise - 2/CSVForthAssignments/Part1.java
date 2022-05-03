
/**
 * Write a description of Part1 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import edu.duke.*;
import java.io.*;

public class Part1 
{
    public ImageResource makeGray(ImageResource inImage)
    {
        ImageResource outImage = new ImageResource(inImage.getWidth(), inImage.getHeight()); //Create a blank image of the same size
        for(Pixel pixel : outImage.pixels()) //For each pixel of the outImage
        {
            Pixel inPixel = inImage.getPixel(pixel.getX(), pixel.getY()); //look at the same pixel in inImage
            int average = (inPixel.getRed() + inPixel.getGreen() + inPixel.getBlue()) / 3; //Calculate the average value of red, green and blue
            pixel.setRed(average);  //set pixel's red to average
            pixel.setGreen(average);  //set pixel's green to average
            pixel.setBlue(average);  //set pixel's blue to average
        }
        return outImage;
    }
    public void testMakeGray()
    {
        ImageResource ir = new ImageResource(); //Select an image file
        String sourceName = ir.getFileName();  //Get the name from the image file
        String newName = "gray-" + sourceName;  //Set a new name for processed image file
        ImageResource grayIr = makeGray(ir);  //Make the initial image file gray
        grayIr.setFileName(newName); //Set the processed image file with new name
        grayIr.draw(); //Draw the processed image file
        grayIr.save();  //Save the processed image file
    }
    
}
