package imageencryption;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

/**
 *
 * @author MC_2018 <mc2018.git@gmail.com>
 */
public class ImageEncrypter extends ImageManipulator {
    
    protected ImageEncrypter(BufferedImage image, EnhancedRandom random, int multiplier) {
        this.image = image;
        this.random = random;
        this.multiplier = multiplier;
        width = image.getWidth();
        height = image.getHeight();
        megaPixels = new MegaPixel[width][height];
    }
    
    // test speeds with both the removal of ints from the list, as well as a loop thru each individual
    protected void generateMegaPixels(String encodedText) {
        ArrayList<Integer> coordinates = random.generateShuffledIntArray(width * height);
        int cornerRemoved = (random.nextBoolean() ? 0 : width - 1) + (random.nextBoolean() ? 0 : height - 1);
        boolean insertRandomData = false;
        
        coordinates.remove(cornerRemoved);
        
        for (int coordinate : coordinates) {
            
        }
        
    }
    
}
