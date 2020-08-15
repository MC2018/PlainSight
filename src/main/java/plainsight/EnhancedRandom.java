package plainsight;

import java.util.ArrayList;
import java.util.Random;
import java.util.stream.IntStream;

/**
 *
 * @author MC_2018 <mc2018.git@gmail.com>
 */
public class EnhancedRandom {
    
    private ArrayList<Random> randoms = new ArrayList();
    private int index = 0;
    
    protected EnhancedRandom(String password) {
        randoms.add(new Random(password.hashCode()));
        randoms.add(new Random(Utils.encode(password).hashCode()));
        index = randoms.get(0).nextInt(randoms.size());
    }
    
    protected boolean nextBoolean() {
        if (index + 1 < randoms.size()) {
            index++;
        } else {
            index = 0;
        }
        
        return randoms.get(index).nextBoolean();
    }
    
    protected int nextInt(int size) {
        if (index + 1 < randoms.size()) {
            index++;
        } else {
            index = 0;
        }
        
        return randoms.get(index).nextInt(size);
    }
    
    protected int[] generateShuffledIntArray(int size, int exceptionIndex) {
        int[] array = new int[size - 1];
        System.out.println(System.currentTimeMillis());
        
        IntStream.range(0, size - 1).parallel().forEach(i -> {
            array[i] = i;
        });
        
        if (exceptionIndex != size - 1) {
            array[exceptionIndex] = size - 1;
        }
        
        shuffleArray(array);
        System.out.println(System.currentTimeMillis());
        
        return array;
    }

    private void shuffleArray(int[] array) {
        int index, temp;
        
        for (int i = array.length - 1; i > 0; i--) {
            index = nextInt(i + 1);
            temp = array[index];
            array[index] = array[i];
            array[i] = temp;
        }
    }
    
    protected char nextBase64Char() {
        return Utils.BASE_64_CHARACTERS.charAt(nextInt(64));
    }
    
}
