package plainsight;

import java.io.File;
import java.nio.file.Files;
import java.util.Base64;
import org.xerial.snappy.Snappy;

/**
 *
 * @author MC_2018 <mc2018.git@gmail.com>
 */
public class Utils {
    
    final public static char BASE_64_BREAK = '#';
    final public static String BASE_64_CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/" + BASE_64_BREAK;
    final public static int MIN_OFFSET = 5;
    final public static int MAX_OFFSET = 250;
    
    public static String encode(String text) {
        try {
            return Base64.getEncoder().encodeToString(Snappy.compress(text.getBytes()));
        } catch (Exception e) {
            return null;
        }
    }
    
    public static String decode(String text) {
        try {
            return new String(Snappy.uncompress(Base64.getDecoder().decode(text)));
        } catch (Exception e) {
            return null;
        }
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
    
    protected static int indexOfBase64(char character) {
        return BASE_64_CHARACTERS.indexOf(character);
    }
    
}
