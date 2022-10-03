package PA5;

import java.util.Iterator;

import net.datastructures.LinkedBinaryTree;

public class LBTDriver {
    public static void main (String[] args){
        net.datastructures.LinkedBinaryTree<Character> tree = new LinkedBinaryTree<>();
        net.datastructures.Position<Character> position;

        position = tree.addRoot('A');
        tree.addRight(position, 'C');
        position = tree.addLeft(position, 'B');
        tree.addRight(position, 'E');
        tree.addLeft(position, 'D');

        Iterator<Character> itr = tree.iterator();
        itr.forEachRemaining((i) -> System.out.print(i+" "));
        System.out.println();
    }
}
