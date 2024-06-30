package src;

import ij.IJ;
import ij.ImagePlus;
import ij.plugin.ChannelSplitter;
import ij.process.FloatProcessor;
import ij.process.ImageConverter;
import ij.process.ImageProcessor;

import java.io.File;

public class ConvertToSV {

    public static void main(String[] args) {
        String basePath = "dataset/";
        String outputSVPath = "sv/";

        // Ensure output directory exists
        new File(outputSVPath).mkdirs();

        // Process images
        for (int personId = 121; personId <= 128; personId++) {
            for (int imageId = 1; imageId <= 14; imageId++) {
                String imagePath = String.format(basePath + "%03d-%02d.jpg", personId, imageId);
                ImagePlus img = IJ.openImage(imagePath);
                if (img != null) {
                    ImageConverter converter = new ImageConverter(img);
                    converter.convertToHSB();

                    ImagePlus[] hsbChannels = ChannelSplitter.split(img);
                    ImageProcessor saturation = hsbChannels[1].getProcessor().convertToFloat();
                    ImageProcessor brightness = hsbChannels[2].getProcessor().convertToFloat();

                    // Create an image to store the product of saturation and brightness
                    FloatProcessor svProcessor = new FloatProcessor(saturation.getWidth(), saturation.getHeight());

                    for (int y = 0; y < saturation.getHeight(); y++) {
                        for (int x = 0; x < saturation.getWidth(); x++) {
                            float satValue = saturation.getf(x, y);
                            float brightValue = brightness.getf(x, y);
                            svProcessor.setf(x, y, satValue * brightValue);
                        }
                    }

                    // Create a new ImagePlus from the FloatProcessor
                    ImagePlus svImage = new ImagePlus("SV", svProcessor);

                    // Save the product of saturation and brightness
                    saveImage(svImage, outputSVPath + String.format("%03d-%02d_sv.png", personId, imageId));
                }
            }
        }
    }

    private static void saveImage(ImagePlus img, String path) {
        IJ.saveAs(img, "PNG", path);
    }
}
