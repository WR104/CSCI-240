package PA2;

public class Exercise2 {
    public class Node {
        String data;
        Node next;

        Node(String s, Node n) {
            data = s;
            next = n;
        }
    }

    static Node head;
    static int count;

    Exercise2() {
        head = null;
        count = 0;
    }

    public void insertFront(String s) {
        if (head == null)
            head = new Node(s, null);
        else {
            Node node = new Node(s, head);
            head = node;
        }
        count += 1;
    }

    public void insertRear(String s) {
        Node node = new Node(s, null);
        Node cur = head;
        while (cur.next != null)
            cur = cur.next;
        cur.next = node;
        count += 1;
    }

    public void remove(String s) {
        Node curr = head;
        Node preCurr = new Node("", curr);
        while (curr.next != null) {
            if (curr.data.equals(s)) {
                preCurr.next = curr.next;
                count -= 1;
                break;
            }
            curr = curr.next;
            preCurr = preCurr.next;
        }
    }

    public void print() {
        Node curr = head;
        while (curr != null) {
            System.out.println(curr.data);
            curr = curr.next;
        }
    }
}
