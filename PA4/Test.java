package PA4;

import net.datastructures.LinkedPositionalList;

import java.util.Iterator;

public class Test {
    public static void main(String[] args){
        LinkedPositionalList<String> list = new LinkedPositionalList<>();
        list.addFirst("Two");
        list.addLast("Three");
        list.addFirst("One");
        list.addLast("Four");

        Iterator<String> iterator = list.iterator();
        while(iterator.hasNext())
            System.out.println(iterator.next());
        System.out.println();
        list.remove(list.first());
        list.remove(list.last());
        iterator = list.iterator();
        iterator.next();
        list.addAfter(list.first(),"Jia");
        iterator = list.iterator();
        while(iterator.hasNext())
            System.out.println(iterator.next());
    }
}
