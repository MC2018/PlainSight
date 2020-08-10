package imageencryption;

import java.awt.image.BufferedImage;
import java.io.File;

/**
 *
 * @author MC_2018 <mc2018.git@gmail.com>
 */
public class Crypt {
    
    public static void encrypt(BufferedImage image, String text, String password) {
        
    }
    
    private static void encrypt(BufferedImage image, String decodedText, String password, String fileName) {
        EnhancedRandom random = new EnhancedRandom(password);
        ImageManipulator im;
        String encodedText = Utils.encode(fileName + "/" + decodedText);
        //int multiplier;
        
        //multiplier = calculateMultiplier(image, text);
    }
    
    public static void encrypt(BufferedImage image, File file, String password) {
        encrypt(image, Utils.read(file), password, file.getName());
    }
    
    public static void decrypt(BufferedImage image, String password) {
        ImageManipulator im;
        File output = new File("output/");
    }
    
    public static void decrypt(File image, String password) {
        
    }
    
    public static int calculateMultiplier(BufferedImage image, String encodedText) {
        int megaPixelCount = image.getWidth() * image.getHeight() - 1;
        int sparePixelsNeeded = encodedText.length() + 1;
        int multiplier = 2;
        
        while (multiplier <= 10) {
            if (megaPixelCount * (multiplier * multiplier - 1) >= sparePixelsNeeded) {
                return multiplier;
            }
        }
        
        return -1;
    }
    
    public static int calculateMultiplier(BufferedImage image) {
        return -1;
    }
    
}
