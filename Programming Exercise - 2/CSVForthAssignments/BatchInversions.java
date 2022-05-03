
/**
 * Write a description of BatchInversions here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import edu.duke.*;
import java.io.*;

public class BatchInversions 
{
    public ImageResource makeInversion(ImageResource inImage)
    {
        ImageResource outImage = new ImageResource(inImage.getWidth(), inImage.getHeight());  //Create a blank image of the same size
        for(Pixel pixel : outImage.pixels()) //For each pixel of the outImage
        {
            Pixel inPixel = inImage.getPixel(pixel.getX(), pixel.getY()); //look at the same pixel in inImage
            int invertedRed = 255 - inPixel.getRed(); //Invert the red value
            int invertedGreen = 255 - inPixel.getGreen();  //Invert the green value
            int invertedBlue = 255 - inPixel.getBlue();  //Invert the blue value
            pixel.setRed(invertedRed);  //set pixel's red to average
            pixel.setGreen(invertedGreen);  //set pixel's green to average
            pixel.setBlue(invertedBlue);  //set pixel's blue to average
        }
        return outImage;
    }
    public void selectAndConvert()
    {
        DirectoryResource dr = new DirectoryResource(); //Open several image files
        for (File f : dr.selectedFiles())
        {
            ImageResource ir = new ImageResource(f); //Select an image file
            String sourceName = ir.getFileName();  //Get the name from the image file
            String newName = "inverted-" + sourceName;  //Set a new name for processed image file
            ImageResource invertedIr = makeInversion(ir);  //Make the initial image file gray
            invertedIr.setFileName(newName); //Set the processed image file with new name
            invertedIr.draw(); //Draw the processed image file
            invertedIr.save();  //Save the processed image file
        }
    }
    
}
