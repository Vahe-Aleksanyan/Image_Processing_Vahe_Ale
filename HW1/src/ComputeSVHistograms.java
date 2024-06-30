package src;

import ij.IJ;
import ij.ImagePlus;
import ij.process.ImageStatistics;
import java.io.FileWriter;
import java.io.IOException;

public class ComputeSVHistograms {

    public static void main(String[] args) {
        String basePath = "sv/";

        try (FileWriter writer = new FileWriter("sv_histograms.csv")) {
            for (int personId = 121; personId <= 128; personId++) {
                for (int imageId = 1; imageId <= 14; imageId++) {
                    String imagePath = String.format(basePath + "%03d-%02d_sv.png", personId, imageId);
                    ImagePlus img = IJ.openImage(imagePath);
                    if (img != null) {
                        ImageStatistics stats = img.getStatistics();
                        int[] histogram = stats.histogram;

                        writer.append(String.format("%03d-%02d", personId, imageId));
                        for (int value : histogram) {
                            writer.append(',').append(Integer.toString(value));
                        }
                        writer.append('\n');
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
