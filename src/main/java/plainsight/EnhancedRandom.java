package imageencryption;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

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
    
    public boolean nextBoolean() {
        return randoms.get(index++).nextBoolean();
    }
    
    public int nextInt(int size) {
        return randoms.get(index++).nextInt(size);
    }
    
    public ArrayList<Integer> generateShuffledIntArray(int size) {
        ArrayList<Integer> list = new ArrayList();
        
        for (int i = 0; i < size; i++) {
            list.add(i);
        }
        
        Collections.shuffle(list, randoms.get(0));
        return list;
    }
    
    public char nextBase64Char(boolean finishingCharacter) {
        if (finishingCharacter) {
            
        }
        return Utils.BASE_64_CHARACTERS.charAt(nextInt(64));
    }
    
}
