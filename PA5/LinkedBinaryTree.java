package PA5;

import net.datastructures.AbstractBinaryTree;
import net.datastructures.Position;

/**
 * LinkedBinaryTree
 * @author Michael T. Goodrich
 * @author Roberto Tamassia
 * @author Michael H. Goldwasser
 * @modifier Zhenyu Jia
 * Class: CSCI 240
 * Date: 10/4/2022
 * Description: Exercise 3 from PA 5
 * Exception(s): N/A
 *
 */
public class LinkedBinaryTree<E> extends AbstractBinaryTree<E> {

    protected static class Node<E> implements Position<E> {
        private E element;          // an element stored at this node
        private LinkedBinaryTree.Node<E> parent;     // a reference to the parent node (if any)
        private LinkedBinaryTree.Node<E> left;       // a reference to the left child (if any)
        private LinkedBinaryTree.Node<E> right;      // a reference to the right child (if any)


        public Node(E e, LinkedBinaryTree.Node<E> above, LinkedBinaryTree.Node<E> leftChild, LinkedBinaryTree.Node<E> rightChild) {
            element = e;
            parent = above;
            left = leftChild;
            right = rightChild;
        }

        // accessor methods
        public E getElement() { return element; }
        public LinkedBinaryTree.Node<E> getParent() { return parent; }
        public LinkedBinaryTree.Node<E> getLeft() { return left; }
        public LinkedBinaryTree.Node<E> getRight() { return right; }

        // update methods
        public void setElement(E e) { element = e; }
        public void setParent(LinkedBinaryTree.Node<E> parentNode) { parent = parentNode; }
        public void setLeft(LinkedBinaryTree.Node<E> leftChild) { left = leftChild; }
        public void setRight(LinkedBinaryTree.Node<E> rightChild) { right = rightChild; }
    } //----------- end of nested Node class -----------

    /** Factory function to create a new node storing element e. */
    protected LinkedBinaryTree.Node<E> createNode(E e, LinkedBinaryTree.Node<E> parent,
                                                                     LinkedBinaryTree.Node<E> left, LinkedBinaryTree.Node<E> right) {
        return new LinkedBinaryTree.Node<E>(e, parent, left, right);
    }

    // LinkedBinaryTree instance variables
    /** The root of the binary tree */
    protected LinkedBinaryTree.Node<E> root = null;     // root of the tree

    /** The number of nodes in the binary tree */
    private int size = 0;              // number of nodes in the tree

    public LinkedBinaryTree() { }      // constructs an empty binary tree

    protected LinkedBinaryTree.Node<E> validate(Position<E> p) throws IllegalArgumentException {
        if (!(p instanceof LinkedBinaryTree.Node))
            throw new IllegalArgumentException("Not valid position type");
        LinkedBinaryTree.Node<E> node = (LinkedBinaryTree.Node<E>) p;       // safe cast
        if (node.getParent() == node)     // our convention for defunct node
            throw new IllegalArgumentException("p is no longer in the tree");
        return node;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public Position<E> root() {
        return root;
    }

    @Override
    public Position<E> parent(Position<E> p) throws IllegalArgumentException {
        LinkedBinaryTree.Node<E> node = validate(p);
        return node.getParent();
    }

    @Override
    public Position<E> left(Position<E> p) throws IllegalArgumentException {
        LinkedBinaryTree.Node<E> node = validate(p);
        return node.getLeft();
    }

    @Override
    public Position<E> right(Position<E> p) throws IllegalArgumentException {
        LinkedBinaryTree.Node<E> node = validate(p);
        return node.getRight();
    }

    public Position<E> addRoot(E e) throws IllegalStateException {
        if (!isEmpty()) throw new IllegalStateException("Tree is not empty");
        root = createNode(e, null, null, null);
        size = 1;
        return root;
    }

    public Position<E> addLeft(Position<E> p, E e)
            throws IllegalArgumentException {
        LinkedBinaryTree.Node<E> parent = validate(p);
        if (parent.getLeft() != null)
            throw new IllegalArgumentException("p already has a left child");
        LinkedBinaryTree.Node<E> child = createNode(e, parent, null, null);
        parent.setLeft(child);
        size++;
        return child;
    }

    public Position<E> addRight(Position<E> p, E e)
            throws IllegalArgumentException {
        LinkedBinaryTree.Node<E> parent = validate(p);
        if (parent.getRight() != null)
            throw new IllegalArgumentException("p already has a right child");
        LinkedBinaryTree.Node<E> child = createNode(e, parent, null, null);
        parent.setRight(child);
        size++;
        return child;
    }

    public E set(Position<E> p, E e) throws IllegalArgumentException {
        LinkedBinaryTree.Node<E> node = validate(p);
        E temp = node.getElement();
        node.setElement(e);
        return temp;
    }

    public E remove(Position<E> p) throws IllegalArgumentException {
        LinkedBinaryTree.Node<E> node = validate(p);
        if (numChildren(p) == 2)
            throw new IllegalArgumentException("p has two children");
        LinkedBinaryTree.Node<E> child = (node.getLeft() != null ? node.getLeft() : node.getRight() );
        if (child != null)
            child.setParent(node.getParent());  // child's grandparent becomes its parent
        if (node == root)
            root = child;                       // child becomes root
        else {
            LinkedBinaryTree.Node<E> parent = node.getParent();
            if (node == parent.getLeft())
                parent.setLeft(child);
            else
                parent.setRight(child);
        }
        size--;
        E temp = node.getElement();
        node.setElement(null);                // help garbage collection
        node.setLeft(null);
        node.setRight(null);
        node.setParent(node);                 // our convention for defunct node
        return temp;
    }

    @Override
    public Iterable<Position<E>> positions(){   //postorder positions
        return postorder();
    }
}

