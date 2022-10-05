package PA5;

public class LBTDriver {
    public static void main (String[] args){
        PA5.LinkedBinaryTree<Character> tree = new PA5.LinkedBinaryTree<>();
        net.datastructures.Position<Character> position;

        //Test Case 1
        position = tree.addRoot('A');
        tree.addRight(position, 'C');
        position = tree.addLeft(position, 'B');
        tree.addRight(position, 'E');
        tree.addLeft(position, 'D');

        for (Character character : tree)
            System.out.print(character + " ");
        System.out.println();

        //Test Case 2
        position = tree.right(position);
        tree.remove(position);
        for(Character c : tree)
            System.out.print(c + " ");
        System.out.println();
    }
}
