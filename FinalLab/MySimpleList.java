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
        Node node = new Node(e);
        if(head == null)
            head = node;
        else{
            Node curr = head;
            while(curr.next != null)
                curr = curr.next;
            curr.next = node;
        }
        size += 1;
    }

    public E removeFront(){
        if(head == null)
            return null;
        E e = head.data;
        head = head.next;
        size -= 1;
        return e;
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
        while(curr.next.next != null){
            curr = curr.next;
        }
        E e = curr.next.data;
        curr.next = null;
        size -= 1;
        return e;  
    }

    public boolean empty(){
        return size == 0;
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
