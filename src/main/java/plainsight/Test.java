package plainsight;

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
            try {
                ImageIO.write(Crypt.encrypt(image, new File("test.png"), "password", false), "png", new File("finish.png"));
            } catch (Exception e) {
                e.printStackTrace();
            }
            //BufferedImage image = new BufferedImage(10, 10, BufferedImage.TYPE_INT_ARGB);
            //int[] nums = new int[100];
            
            //for (int i = 0; i < nums.length; i++) {
            //    nums[i] = Integer.MIN_VALUE;
            //    nums[i] += Math.random() * Integer.MAX_VALUE * 2;
            //}
            
            //image.setRGB(0, 0, 10, 10, nums, 0, 10);
            //ImageIO.write(image, "png", new File("work.png"));
            
            /*int num = 64;
            
            for (int i = 0; i < 3; i++) {
                System.out.println((num & 3 * ((int) Math.pow(4, 2 - i))) >>> (4 - 2 * i));
            }*/
            
            
            /*int num = 262919;
            int a, r, g, b;
            System.out.println(a = (num & 255 * 256 * 256 * 256) >>> 24);
            System.out.println(r = (num & 255 * 256 * 256) >>> 16);
            System.out.println(g = (num & 255 * 256) >>> 8);
            System.out.println(b = (num & 255 * 1) >>> 0);
            System.out.println((a << 24) + (r << 16) + (g << 8) + b);
            System.out.println(num);
            int width = image.getWidth();
            int height = image.getHeight();
            int[] nums = image.getRGB(0, 0, width, height, null, 0, width);
            System.out.println("");*/
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
}
