package plainsight;

import java.awt.image.BufferedImage;

/**
 *
 * @author MC_2018 <mc2018.git@gmail.com>
 */
public class ImageDecryptor {
    
    private class Result {
        
        String decodedText;
        BufferedImage originalImage;
        
        private Result(String decodedText, BufferedImage originalImage) {
            this.decodedText = decodedText;
            this.originalImage = originalImage;
        }
        
    }
    
    public ImageDecryptor() {
        
    }
    
}
