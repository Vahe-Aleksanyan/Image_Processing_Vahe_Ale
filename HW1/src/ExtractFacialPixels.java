package src;

import ij.IJ;
import ij.ImagePlus;
import ij.plugin.filter.PlugInFilter;
import ij.process.ImageProcessor;

import java.awt.Color;

public class ExtractFacialPixels implements PlugInFilter {
    private int minHue, maxHue;
    private int minSat, maxSat;
    private int minVal, maxVal;

    public ExtractFacialPixels(int minHue, int maxHue, int minSat, int maxSat, int minVal, int maxVal) {
        this.minHue = minHue;
        this.maxHue = maxHue;
        this.minSat = minSat;
        this.maxSat = maxSat;
        this.minVal = minVal;
        this.maxVal = maxVal;
    }

    @Override
    public int setup(String arg, ImagePlus imp) {
        return DOES_RGB;
    }

    @Override
    public void run(ImageProcessor ip) {
        int width = ip.getWidth();
        int height = ip.getHeight();

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                int rgb = ip.getPixel(x, y);
                Color color = new Color(rgb);
                float[] hsb = Color.RGBtoHSB(color.getRed(), color.getGreen(), color.getBlue(), null);

                int hue = Math.round(hsb[0] * 255);
                int sat = Math.round(hsb[1] * 255);
                int val = Math.round(hsb[2] * 255);

                if (hue < minHue || hue > maxHue || sat < minSat || sat > maxSat || val < minVal || val > maxVal) {
                    ip.putPixel(x, y, Color.WHITE.getRGB());
                }
            }
        }
    }

    public static void main(String[] args) {
        // Example thresholds (replace these with actual values from the Excel file)
        int minHue = 0, maxHue = 180;
        int minSat = 0, maxSat = 255;
        int minVal = 0, maxVal = 255;

        String basePath = "dataset/";
        String outputFacePath = "face/";

        // Ensure output directory exists
        new java.io.File(outputFacePath).mkdirs();

        for (int personId = 121; personId <= 128; personId++) {
            for (int imageId = 1; imageId <= 14; imageId++) {
                String imagePath = String.format(basePath + "%03d-%02d.jpg", personId, imageId);
                ImagePlus img = IJ.openImage(imagePath);
                if (img != null) {
                    ExtractFacialPixels filter = new ExtractFacialPixels(minHue, maxHue, minSat, maxSat, minVal, maxVal);
                    filter.setup("", img);
                    filter.run(img.getProcessor());

                    // Save the processed image
                    IJ.saveAs(img, "PNG", outputFacePath + String.format("%03d-%02d_face.png", personId, imageId));
                }
            }
        }
    }
}
