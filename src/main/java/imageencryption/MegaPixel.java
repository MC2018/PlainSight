package imageencryption;

import java.awt.Color;

/**
 *
 * @author MC_2018 <mc2018.git@gmail.com>
 */
public class MegaPixel {
    
    private int[] colors;
    
    public MegaPixel(int multiplier) {
        colors = new int[multiplier * multiplier];
    }
    
    public void generateColors(int originalColor, String encodedText, EnhancedRandom random) {
        
    }

    public int getColorAt(int index) {
        return colors[index];
    }
    
}
