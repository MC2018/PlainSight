package imageencryption;

import java.awt.Image;
import java.util.Random;

/**
 *
 * @author MC_2018 <mc2018.git@gmail.com>
 */
public class ImageManipulator {
    
    private Image image;
    private Random random;
    private int multiplier;
    
    protected ImageManipulator(Image image, int multiplier) {
        this.image = image;
        random = new Random();
        this.multiplier = multiplier;
    }
    
}
