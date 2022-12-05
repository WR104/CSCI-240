package FinalLab;

public class MySimpleList <E> {
    private Node head = null;
    private int size = 0;

    private class Node {
        E data;
        Node next;
        Node(E d){
            data = d;
            this.next = null;
        }
    }

    public void insertFront(E e){
        Node node = new Node(e);
        if(head == null)
            head = node;
        else{
            node.next = head;
            head = node;      
        }
        size += 1;
    }

    public void insertRear(E e){

    }

    public E removeRear(){
        if(head == null)
            return null;
        if(head.next == null){
            E e = head.data;
            head = null;
            return e;
        }
        Node curr = head;
        for(int i = 0; i < size - 2; i++){
            curr = curr.next;
        }
        E e = curr.next.data;
        curr.next = null;
        size -= 1;
        return e;  
    }

    public boolean empty(){
        return head == null;
    }

    public int size(){
        return size;
    }

    public void print(){
        if(head == null)
            return;
        Node curr = head;
        System.out.print("[" + curr.data);
        while(curr.next != null){
            curr = curr.next;
            System.out.print(", " + curr.data);
        }
        System.out.println("]");
    }
}
