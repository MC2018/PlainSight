package plainsight;

/**
 *
 * @author MC_2018 <mc2018.git@gmail.com>
 */
public class MegaPixel {
    
    private int[] colors;
    private final static int[] ARGB_BITWISE_NUMBERS = {255 * 256 * 256 * 256, 255 * 256 * 256, 255 * 256, 255};
    private final static int[] BASE_64_BITWISE_NUMBERS = {3 * 4 * 4 * 4, 3 * 4 * 4, 3 * 4, 3};
    
    protected MegaPixel(int multiplier) {
        colors = new int[multiplier * multiplier];
    }
    
    protected static MegaPixel generateGenericMegaPixel(int multiplier, int originalColor) {
        MegaPixel megaPixel = new MegaPixel(multiplier);
        
        for (int i = 0; i < megaPixel.colors.length; i++) {
            megaPixel.colors[i] = originalColor;
        }
        
        return megaPixel;
    }
    
    protected static MegaPixel generateMegaPixel(int multiplier, int originalColor, String encodedText, EnhancedRandom random) {
        MegaPixel megaPixel = new MegaPixel(multiplier);
        megaPixel.colors[0] = originalColor;
        int[] originalColorARGB = new int[4];
        
        for (int i = 0; i < originalColorARGB.length; i++) {
            originalColorARGB[i] = (originalColor & ARGB_BITWISE_NUMBERS[i]) >>> (24 - 8 * i);
        }
        
        for (int i = 0; i < encodedText.length(); i++) {
            int base64Value = Utils.indexOfBase64(encodedText.charAt(i));
            int newColor = originalColorARGB[0] << 24;
            
            for (int j = 1; j < 4; j++) {
                int offsetColor = originalColorARGB[j];
                boolean upwardOffset;
                
                if (offsetColor > Utils.MAX_OFFSET) {
                    upwardOffset = false;
                } else if (offsetColor < Utils.MIN_OFFSET) {
                    upwardOffset = true;
                } else {
                    upwardOffset = random.nextBoolean();
                }
                
                if (upwardOffset) {
                    offsetColor += (base64Value & BASE_64_BITWISE_NUMBERS[j]) >>> (6 - 2 * j);
                } else {
                    offsetColor -= (base64Value & BASE_64_BITWISE_NUMBERS[j]) >>> (6 - 2 * j);
                }
                
                newColor += offsetColor << (24 - 8 * j);
            }
            
            megaPixel.colors[i + 1] = newColor;
        }
        
        return megaPixel;
    }
    
    protected static MegaPixel generateCornerMegaPixel(int multiplier, int originalColor, EnhancedRandom random) {
        return new MegaPixel(multiplier);
    }
    
    protected static MegaPixel generateConcludingMegaPixel(int multiplier, int originalColor, String encodedText, EnhancedRandom random) {
        MegaPixel megaPixel = new MegaPixel(multiplier);
        megaPixel.colors[0] = originalColor;
        int[] originalColorARGB = new int[4];
        
        for (int i = 0; i < originalColorARGB.length; i++) {
            originalColorARGB[i] = (originalColor & ARGB_BITWISE_NUMBERS[i]) >>> (24 - 8 * i);
        }
        
        for (int i = 0; i < encodedText.length(); i++) {
            int base64Value = Utils.indexOfBase64(encodedText.charAt(i));
            int newColor = originalColorARGB[0] << 24;
            
            for (int j = 1; j < 4; j++) {
                int offsetColor = originalColorARGB[j];
                boolean upwardOffset;
                
                if (offsetColor > Utils.MAX_OFFSET) {
                    upwardOffset = false;
                } else if (offsetColor < Utils.MIN_OFFSET) {
                    upwardOffset = true;
                } else {
                    upwardOffset = random.nextBoolean();
                }
                
                if (base64Value == 64 && j == 1 && upwardOffset) {
                    offsetColor += 5;
                } else if (base64Value == 64 && j == 1 && !upwardOffset) {
                    offsetColor -= 5;
                } else if (upwardOffset) {
                    offsetColor += (base64Value & BASE_64_BITWISE_NUMBERS[j]) >>> (6 - 2 * j);
                } else {
                    offsetColor -= (base64Value & BASE_64_BITWISE_NUMBERS[j]) >>> (6 - 2 * j);
                }
                
                newColor += offsetColor << (24 - 8 * j);
            }
            
            megaPixel.colors[i + 1] = newColor;
        }
        
        return megaPixel;
    }
    
    protected static MegaPixel generateRandomMegaPixel(int multiplier, int originalColor, EnhancedRandom random) {
        StringBuilder encodedText = new StringBuilder();
        
        while (encodedText.length() < multiplier * multiplier - 1) {
            encodedText.append(random.nextBase64Char());
        }
        
        return MegaPixel.generateMegaPixel(multiplier, originalColor, encodedText.toString(), random);
    }

    protected int getColorAt(int index) {
        return colors[index];
    }
    
    protected int[] getColors() {
        return colors;
    }
    
}
