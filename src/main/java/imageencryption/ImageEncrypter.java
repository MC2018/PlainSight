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
    
    // consider removing MegaPixel[][] and look into straight up using int[]
    // test speeds with both the removal of ints from the list, as well as a loop thru each individual
    protected void generateMegaPixels(String encodedText) {
        ArrayList<Integer> coordinates = random.generateShuffledIntArray(width * height);
        int cornerRemoved = (random.nextBoolean() ? 0 : width - 1) + width * (random.nextBoolean() ? 0 : height - 1);
        boolean insertRandomData = false;
        
        setCornerMegaPixel(cornerRemoved);
        coordinates.remove(cornerRemoved);
        
        for (int coordinate : coordinates) {
            
        }
        
    }
    
    private void setCornerMegaPixel(int coordinate) {
        megaPixels[coordinate % width][coordinate / width] = null;
    }
    
    private void setMegaPixel(int coordinate, String encodedText) {
        MegaPixel megaPixel = new MegaPixel(multiplier);
        megaPixel.generateColors(-1, encodedText, random);
        megaPixels[coordinate % width][coordinate / width] = megaPixel;
    }
    
    private void getColorAtCoordinate(int coordinate) {
        //return image.getR
    }
    
    private BufferedImage buildLargeImage() {
        BufferedImage image = new BufferedImage(width * multiplier, height * multiplier, BufferedImage.TYPE_INT_ARGB);
        int[] colors = new int[width * height * multiplier * multiplier];
        
        for (int w = 0; width < megaPixels.length; w++) {
            for (int h = 0; h < megaPixels[w].length; h++) {
                for (int i = 0; i < multiplier * multiplier; i++) {
                    colors[(w + h * w) * multiplier] = megaPixels[w][h].getColorAt(i);
                }
                
                
            }
        }
        
        return image;
    }
    
}
