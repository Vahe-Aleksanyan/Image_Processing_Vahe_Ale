package src;

import ij.IJ;
import ij.ImagePlus;
import ij.process.ImageStatistics;

import java.io.FileWriter;
import java.io.IOException;

public class ComputeHistograms {

    public static void main(String[] args) {
        String[] types = {"hue", "sat", "val"};
        String basePaths[] = {"hue/", "sat/", "val/"};

        for (int t = 0; t < types.length; t++) {
            String type = types[t];
            String basePath = basePaths[t];

            try (FileWriter writer = new FileWriter(type + "_histograms.csv")) {
                for (int personId = 121; personId <= 128; personId++) {
                    for (int imageId = 1; imageId <= 14; imageId++) {
                        String imagePath = String.format(basePath + "%03d-%02d_%d.png", personId, imageId, t);
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
}
