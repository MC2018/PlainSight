package imageencryption;

import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;

/**
 *
 * @author MC_2018 <mc2018.git@gmail.com>
 */
public class Test {
    
    public static void main(String[] arg) {
        try {
            BufferedImage image = ImageIO.read(new File("test.png"));
            Crypt crypt = new Crypt();
            Crypt.encrypt(image, "test", "password");
            /*int num = image.getRGB(0, 0);
            int a, r, g, b;
            System.out.println(a = (num & 0xff * 256 * 256 * 256) >>> 24);
            System.out.println(r = (num & 0xff * 256 * 256) >>> 16);
            System.out.println(g = (num & 0xff * 256) >>> 8);
            System.out.println(b = (num & 0xff * 1) >>> 0);
            System.out.println((a << 24) + (r << 16) + (g << 8) + b);
            System.out.println(num);
            int width = image.getWidth();
            int height = image.getHeight();
            int[] nums = image.getRGB(0, 0, width, height, null, 0, width);
            System.out.println("");*/
        } catch (Exception e) {
            
        }
    }
    
}
