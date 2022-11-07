package PA8;

import net.datastructures.AVLTreeMap;
import net.datastructures.Entry;
import net.datastructures.TreeMap;

public class Exercise {

    public void Exercise1(){
        TreeMap<Integer,String> map = new TreeMap<>();
        map.put(10,"10");
        map.put(20,"20");
        map.put(4,"4");
        map.put(8,"8");
        map.put(15,"15");
        map.remove(8);
        map.remove(10);
        System.out.println("get 15: " + map.get(15));
        System.out.println("get 30: " + map.get(30));
        System.out.println("get 8: " + map.get(8));
        for(Entry<Integer,String> entry : map.entrySet())
            System.out.println("Key: " + entry.getKey() + " Value: " + entry.getValue());
    }
    public void Exercise3(){
        AVLTreeMap<Integer,String> map = new AVLTreeMap<>();
        map.put(10,"10");
        map.put(20,"20");
        map.put(4,"4");
        map.put(8,"8");
        map.put(15,"15");
        map.remove(8);
        map.remove(10);
        System.out.println("get 15: " + map.get(15));
        System.out.println("get 30: " + map.get(30));
        System.out.println("get 8: " + map.get(8));
        for(Entry<Integer,String> entry : map.entrySet())
            System.out.println("Key: " + entry.getKey() + " Value: " + entry.getValue());
    }


}
