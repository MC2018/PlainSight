package imageencryption;

import java.io.File;
import java.nio.file.Files;
import java.util.Base64;

/**
 *
 * @author MC_2018 <mc2018.git@gmail.com>
 */
public class Utils {
    
    
    public static String encode(String text) {
        return Base64.getEncoder().encodeToString(text.getBytes());
    }
    
    public static String decode(String text) {
        return new String(Base64.getDecoder().decode(text));
    }
    
    public static String read(File file) {
        try {
            return new String(Files.readAllBytes(file.toPath()));
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    
    public static void write(File file, String rawData) throws Exception {
        try {
            Files.write(file.toPath(), rawData.getBytes());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
}
