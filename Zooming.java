package zooming;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;

public class Zooming {
    public static void main(String[] args) {
        try {
            // Read the input image
            File inputFile = new File("test\\test_zooming.jpg");
            BufferedImage inputImage = ImageIO.read(inputFile);

            // Determine the zoom factor
            double zoomFactor = 4.0;

            // Calculate the new dimensions of the zoomed image
            int newWidth = (int) (inputImage.getWidth() * zoomFactor);
            int newHeight = (int) (inputImage.getHeight() * zoomFactor);

            // Create a new blank image with the new dimensions
            Image temp = inputImage.getScaledInstance(newWidth, newHeight, Image.SCALE_SMOOTH);
            BufferedImage zoomedImage = new BufferedImage(newWidth, newHeight, inputImage.getType());
            Graphics2D g2d = zoomedImage.createGraphics();
            g2d.drawImage(temp, 0, 0, null);
            g2d.dispose();

            // Define the crop area to be the center of the zoomed image with the same size as the original image
            int cropX = (newWidth - inputImage.getWidth()) / 2;
            int cropY = (newHeight - inputImage.getHeight()) / 2;
            int cropWidth = inputImage.getWidth();
            int cropHeight = inputImage.getHeight();

            // Crop the image
            BufferedImage croppedImage = zoomedImage.getSubimage(cropX, cropY, cropWidth, cropHeight);

            // Save the cropped image to a file
            File outputFile = new File("test\\test_output\\zooming_output.png");
            ImageIO.write(croppedImage, "png", outputFile);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
} 