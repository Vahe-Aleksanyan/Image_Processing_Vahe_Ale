package src;

import ij.IJ;
import ij.ImagePlus;
import ij.plugin.ChannelSplitter;
import ij.process.ImageConverter;

import java.io.File;

public class ConvertToHSB {

    public static void main(String[] args) {
        String basePath = "dataset/";
        String outputHuePath = "hue/";
        String outputSatPath = "sat/";
        String outputValPath = "val/";

        // Ensure output directories exist
        new File(outputHuePath).mkdirs();
        new File(outputSatPath).mkdirs();
        new File(outputValPath).mkdirs();

        // Process images in the range from 121 to 128 for this example
        for (int personId = 121; personId <= 128; personId++) {
            for (int imageId = 1; imageId <= 14; imageId++) {
                String imagePath = String.format(basePath + "%03d-%02d.jpg", personId, imageId);
                ImagePlus img = IJ.openImage(imagePath);
                if (img != null) {
                    new ImageConverter(img).convertToHSB();

                    // Split channels
                    ImagePlus[] channels = ChannelSplitter.split(img);

                    // Save each channel
                    String baseFileName = String.format("%03d-%02d_", personId, imageId);
                    saveImage(channels[0], outputHuePath + baseFileName + "0.png"); // Hue
                    saveImage(channels[1], outputSatPath + baseFileName + "1.png"); // Saturation
                    saveImage(channels[2], outputValPath + baseFileName + "2.png"); // Brightness

                    img.close();
                }
            }
        }
    }

    private static void saveImage(ImagePlus img, String path) {
        IJ.saveAs(img, "PNG", path);
    }
}
