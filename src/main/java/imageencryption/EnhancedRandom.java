package imageencryption;

import java.util.ArrayList;
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
    
    public int nextInt(int size) {
        return randoms.get(index++).nextInt(size);
    }
    
}
