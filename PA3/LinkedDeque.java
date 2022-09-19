package PA3;

public class LinkedDeque {
    public class QueueNode {
        int element;
        QueueNode prev;
        QueueNode next;

        QueueNode(int x) {
            element = x;
            prev = null;
            next = null;
        }

        QueueNode(int x, QueueNode p, QueueNode n) {
            element = x;
            prev = p;
            next = n;
        }
    }

    QueueNode head;
    int size;

    LinkedDeque(int x) {
        QueueNode node = new QueueNode(x);
        head = node;
        head.prev = head;
        head.next = head;
        size = 1;
    }

    public void insertfront(int x) {
        QueueNode node = new QueueNode(x);
        node.prev = head.prev;
        node.next = head;
        head = node;
        size += 1;
    }

    public void removefront() {
        if (size > 0) {
            head.prev.next = head.next;
            head = head.next;
            size -= 1;
        }
    }

    public int frontitem(){
        return head.element;
    }
    public void insertrear(int x) {
        QueueNode node = new QueueNode(x,head.prev,head);
        head.prev.next = node;
        size += 1;
    }

    public void removerear(){
        if(size > 0){
            head.prev = head.prev.prev;
            size -= 1;
        }
    }

    public int size(){
        return size;
    }
    public void print(){
        QueueNode curr = head;
        for(int i=0; i<size; i++){
            System.out.print(curr.element + " ");
            curr = curr.next;
        }
        System.out.println();
    }
}