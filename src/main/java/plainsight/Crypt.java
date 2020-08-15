package plainsight;

import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;

/**
 *
 * @author MC_2018 <mc2018.git@gmail.com>
 */
public class Crypt {
    
    public static BufferedImage encrypt(BufferedImage image, String text, String password, boolean randomizeAllMegaPixels) {
        return encrypt(image, text, password, "output.txt", randomizeAllMegaPixels);
    }
    
    public static BufferedImage encrypt(BufferedImage image, File file, String password, boolean randomizeAllMegaPixels) {
        return encrypt(image, Utils.read(file), password, file.getName(), randomizeAllMegaPixels);
    }
    
    private static BufferedImage encrypt(BufferedImage image, String decodedText, String password, String fileName, boolean randomizeAllMegaPixels) {
        EnhancedRandom random = new EnhancedRandom(password);
        ImageEncrypter ie;
        String encodedText = Utils.encode(fileName + "/" + decodedText);
        int multiplier = calculateMultiplier(image, encodedText);
        
        ie = new ImageEncrypter(image, random, multiplier, randomizeAllMegaPixels);
        image = null;
        ie.generateMegaPixels(encodedText);
        
        try {
            return ie.buildLargeImage();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    
    public static void decrypt(BufferedImage image, String password) {
        //ImageDecrypter id;
        File output = new File("output/");
    }
    
    public static void decrypt(File image, String password) {
        try {
            decrypt(ImageIO.read(image), password);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    private static int calculateMultiplier(BufferedImage image, String encodedText) {
        int megaPixelCount = image.getWidth() * image.getHeight() - 2;
        int sparePixelsNeeded = encodedText.length() + 2;
        int multiplier = 2;
        
        while (multiplier <= 10) {
            if (megaPixelCount * (multiplier * multiplier - 1) >= sparePixelsNeeded) {
                return multiplier;
            }
            
            multiplier++;
        }
        
        return -1;
    }
    
    private static int calculateMultiplier(BufferedImage image) {
        return -1;
    }
    
}
