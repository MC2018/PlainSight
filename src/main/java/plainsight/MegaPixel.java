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

    protected static void generateColorArray(int[] colors, int originalColor, String encodedText, EnhancedRandom random) {
        int[] originalColorARGB = new int[4];
        colors[0] = originalColor;

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

            colors[i + 1] = newColor;
        }
    }

    protected static MegaPixel generateMegaPixel(int multiplier, int originalColor, String encodedText, EnhancedRandom random) {
        MegaPixel megaPixel = new MegaPixel(multiplier);

        generateColorArray(megaPixel.colors, originalColor, encodedText, random);
        
        return megaPixel;
    }
    
    protected static MegaPixel generateCornerMegaPixel(int multiplier, int originalColor, EnhancedRandom random, int cornerIndex) {
        MegaPixel megaPixel = new MegaPixel(multiplier);
        int[][] positionSets = {
            {0, 1, multiplier, multiplier + 1},
            {multiplier - 1, multiplier - 2, multiplier * 2 - 1, multiplier * 2 - 2},
            {multiplier * multiplier - 1, multiplier * multiplier - 2, multiplier * (multiplier - 1) - 1, multiplier * (multiplier - 1) - 2},
            {multiplier * (multiplier - 1), multiplier * (multiplier - 1) + 1, multiplier * (multiplier - 2), multiplier * (multiplier - 2) + 1},
        };
        int[] positions = positionSets[cornerIndex];
        int[] originalColorARGB = new int[4];
        random.shuffleArray(positions);

        for (int i = 0; i < megaPixel.colors.length; i++) {
            megaPixel.colors[i] = originalColor;
        }

        for (int j = 0; j < originalColorARGB.length; j++) {
            originalColorARGB[j] = (originalColor & ARGB_BITWISE_NUMBERS[j]) >>> (24 - 8 * j);
        }

        for (int i = 0; i < positions.length; i++) {
            switch (i) {
                case 0:
                    megaPixel.colors[positions[i]] = originalColor;
                    break;
                case 1:
                    int newColor = originalColorARGB[0] << 24;

                    for (int j = 1; j < 4; j++) {
                        int offset = (multiplier >> (2 * (j - 1))) & 0b11;
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
                            offsetColor += offset;
                        } else {
                            offsetColor -= offset;
                        }

                        newColor += offsetColor << (24 - 8 * j);
                    }

                    megaPixel.colors[positions[i]] = newColor;
                    break;
                default:
                    break;
            }
        }

        return megaPixel;
    }
    
    /*private static int buildColor(int color, int[] argbOffsets) {
        int color = 0;
        
        for (int i = 0; i < argbOffsets.length; i++) {
            color += argbOffsets[i] << (24 - 8 * i);
        }
        
        return color;
    }*/
    
    protected static MegaPixel generateConcludingMegaPixel(int multiplier, int originalColor, String encodedText, EnhancedRandom random) {
        MegaPixel megaPixel = new MegaPixel(multiplier);
        int[] originalColorARGB = new int[4];
        megaPixel.colors[0] = originalColor;
        
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
                    if (upwardOffset) {
                        offsetColor += 5;
                    } else {
                        offsetColor -= 5;
                    }
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

    // maybe make this faster
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
