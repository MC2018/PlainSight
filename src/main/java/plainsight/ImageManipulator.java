package imageencryption;

import java.awt.image.BufferedImage;

/**
 *
 * @author MC_2018 <mc2018.git@gmail.com>
 */
public abstract class ImageManipulator {
    
    protected MegaPixel[][] megaPixels;
    protected BufferedImage image;
    protected EnhancedRandom random;
    protected int multiplier;
    protected int width;
    protected int height;
    
}
