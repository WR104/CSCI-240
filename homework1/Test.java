package homework1;

import java.util.Arrays;

public class Test {
    static public void main(String[] args){
        int[] array = {1,2,3,4,5};
        new Exercise().shuffle(array);
        System.out.println(Arrays.toString(array));
    }
}
