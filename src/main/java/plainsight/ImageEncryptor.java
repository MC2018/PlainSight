package plainsight;

import java.awt.image.BufferedImage;

/**
 *
 * @author MC_2018 <mc2018.git@gmail.com>
 */
public class ImageEncryptor {
    
    private MegaPixel[] megaPixels;
    private EnhancedRandom random;
    private int[] colors;
    private int multiplier;
    private int width;
    private int height;
    private boolean randomizeAllMegaPixels;
    
    protected ImageEncryptor(BufferedImage image, EnhancedRandom random, int multiplier, boolean randomizeAllMegaPixels) {
        this.random = random;
        this.multiplier = multiplier;
        this.randomizeAllMegaPixels = randomizeAllMegaPixels;
        width = image.getWidth();
        height = image.getHeight();
        megaPixels = new MegaPixel[width * height];
        colors = image.getRGB(0, 0, width, height, null, 0, width);
    }
    
    // consider removing MegaPixel[] and look into straight up using int[]
    // test speeds with both the removal of ints from the list, as well as a loop thru each individual
    protected void generateMegaPixels(String encodedText) {
        int cornerRemoved = (random.nextBoolean() ? 0 : width - 1) + width * (random.nextBoolean() ? 0 : height - 1);
        int[] coordinates = random.generateShuffledIntArray(width * height, cornerRemoved);
        String concludingEncodedText;
        int pixelsAvailable = multiplier * multiplier - 1;
        int encodedTextLength = encodedText.length();
        int encodedTextIndex;
        
        setCornerMegaPixel(cornerRemoved);
        
        for (encodedTextIndex = 0; encodedTextIndex + pixelsAvailable <= encodedTextLength; encodedTextIndex += pixelsAvailable) {
            setMegaPixel(coordinates[encodedTextIndex / pixelsAvailable], encodedText.substring(encodedTextIndex, encodedTextIndex + pixelsAvailable));
        }
        
        concludingEncodedText = encodedTextLength % pixelsAvailable == 0 ? "" : encodedText.substring(encodedTextLength - (encodedTextLength % pixelsAvailable), encodedTextLength);
        concludingEncodedText += Utils.BASE_64_BREAK;
        
        while (concludingEncodedText.length() < pixelsAvailable) {
            concludingEncodedText += "A";
        }
        
        setConcludingMegaPixel(coordinates[encodedTextIndex / pixelsAvailable], concludingEncodedText);
        
        if (randomizeAllMegaPixels) {
            for (int i = encodedText.length() / pixelsAvailable + 1; i < coordinates.length; i++) {
                setRandomMegaPixel(coordinates[i]);
            }
        } else {
            for (int i = encodedText.length() / pixelsAvailable + 1; i < coordinates.length; i++) {
                setGenericMegaPixel(coordinates[i]);
            }
        }
    }
    
    private void setCornerMegaPixel(int coordinate) {
        int cornerIndex;
        
        if (coordinate < width) {
            cornerIndex = coordinate == 0 ? 0 : 1;
        } else {
            cornerIndex = coordinate == width * height - 1 ? 2 : 3;
        }
        
        megaPixels[coordinate] = MegaPixel.generateCornerMegaPixel(multiplier, colors[coordinate], random, cornerIndex);
    }
    
    private void setMegaPixel(int coordinate, String encodedText) {
        megaPixels[coordinate] = MegaPixel.generateMegaPixel(multiplier, colors[coordinate], encodedText, random);
    }
    
    private void setRandomMegaPixel(int coordinate) {
        megaPixels[coordinate] = MegaPixel.generateRandomMegaPixel(multiplier, colors[coordinate], random);
    }
    
    private void setConcludingMegaPixel(int coordinate, String encodedText) {
        megaPixels[coordinate] = MegaPixel.generateConcludingMegaPixel(multiplier, colors[coordinate], encodedText, random);
    }
    
    private void setGenericMegaPixel(int coordinate) {
        megaPixels[coordinate] = MegaPixel.generateGenericMegaPixel(multiplier, colors[coordinate]);
    }
    
    protected BufferedImage buildLargeImage() {
        BufferedImage image = new BufferedImage(width * multiplier, height * multiplier, BufferedImage.TYPE_INT_ARGB);
        int[] colors = new int[width * height * multiplier * multiplier];
        
        for (int w = 0; w < width; w++) {
            for (int h = 0; h < height; h++) {
                for (int i = 0; i < multiplier * multiplier; i++) {
                    int colorIndex = (w * multiplier + (i % multiplier)) + (h * multiplier * width * multiplier + (((int) (i / multiplier)) * multiplier * width));
                    colors[colorIndex] = megaPixels[w + h * width].getColorAt(i);
                }
            }
        }
        
        image.setRGB(0, 0, width * multiplier, height * multiplier, colors, 0, width * multiplier);
        return image;
    }
    
}
