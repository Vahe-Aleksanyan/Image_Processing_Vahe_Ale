### Automated Facial Feature Extraction from Grayscale Images

#### Overview

This project involves processing images from the FEI Face Database to extract facial features using various image processing techniques. The tasks included converting images to different grayscale representations, computing histograms, estimating optimal thresholds, and applying these thresholds to extract facial regions.

#### Input and Output

**Given**:
- A set of images from the FEI Face Database.
- Instructions to convert these images to grayscale based on hue, saturation, and brightness.
- Requirements to compute histograms, estimate thresholds, and extract facial features.

**Result**:
- Grayscale images based on hue, saturation, and brightness stored in respective folders (`hue`, `sat`, `val`).
- Histograms computed and thresholds estimated for these images.
- Facial features extracted and the resulting images saved in the `face` folder.

#### Purpose of Java and Python Files

**Java Files**:
- **ConvertToHSB.java**: Converts color images to grayscale based on hue, saturation, and brightness, and saves them.
- **ComputeHistograms.java**: Computes histograms for the grayscale images and saves the data to CSV files.
- **ExtractFacialPixels.java**: Implements an ImageJ PlugInFilter to extract facial features using predefined thresholds and saves the processed images.

**Python Files**:
- **Histograms and Thresholds**: Python scripts are used to load the histogram data from CSV files, estimate the optimal thresholds, and update the Excel file (`Hist_HSV.xlsx`) with these thresholds.

This setup ensures a streamlined workflow, leveraging Java for image processing and Python for data analysis and management. The combination of these tools allowed for efficient extraction of facial features from a set of images, demonstrating the application of digital image processing techniques.