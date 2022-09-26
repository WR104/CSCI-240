package PA4;

import net.datastructures.LinkedPositionalList;
import net.datastructures.Position;
import java.util.Iterator;
public class TextEditor {
    LinkedPositionalList<Character> list = new LinkedPositionalList<>();
    Position<Character> cursor;

    public TextEditor() {
    }

    public TextEditor(String s) {
        s = s + " ";
        char[] arr = s.toCharArray();
        for (char c : arr)
            list.addLast(c);
        cursor = list.last();
    }

    public void left() {
        if (!cursor.equals(list.first()))
            cursor = list.before(cursor);
    }

    public void right() {
        if (!cursor.equals(list.last()))
            cursor = list.after(cursor);
    }

    public void insert(char c) {
        list.addBefore(cursor, c);

    }

    public void delete() {
        list.remove(list.after(cursor));
    }

    public int current() {
        Position<Character> curr = list.first();
        int count = 0;
        while (!curr.equals(cursor)) {
            curr = list.after(curr);
            count++;
        }
        return count;
    }

    public int size(){
        return list.size()-1;
    }
    public void move(int n) {
        int valid = list.size();
        if (n < 0 || n >= valid)
            return;
        int current = current();
        if (current <= n) {
            for (int i = 0; i < n - current; i++)
                cursor = list.after(cursor);
        } else {
            for (int i = current; i > n; i--)
                cursor = list.before(cursor);
        }
    }

    public void display() {
        Iterator<Character> iter = list.iterator();
        int index = current();
        int count = 0;
        if (count == index)
            System.out.print('>');
        while (count++ < index || iter.hasNext()) {
            if (iter.hasNext())
                System.out.print(iter.next());
            if (count == index)
                System.out.print('>');
        }
        System.out.println();
    }
}
