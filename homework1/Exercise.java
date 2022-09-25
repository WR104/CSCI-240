package homework1;

import java.util.*;

public class Exercise {
    final String target = "abcdef";
    List<String> list = new ArrayList<String>();

    public void allPossibleString() {
        for (int i = 0; i < target.length(); i++) {
            String s = Character.toString(target.charAt(i));
            String str = target;
            helper(s, str, "");
        }
        list.stream().forEach(System.out::println);
    }

    public void helper(String s, String str, String res) {
        res += s;
        if (res.length() == target.length()) {
            list.add(res);
            return;
        }
        String sstr = str.replace(s, "");
        for (int i = 0; i < sstr.length(); i++) {
            String ss = Character.toString(sstr.charAt(i));
            helper(ss, sstr, res);
        }
    }

    public void swap(int[] array, int x, int y) {
        int temp = array[x];
        array[x] = array[y];
        array[y] = temp;
    }

    public void sortByK(int[] array, int k) {
        sortHelper(0, array.length - 1, array, k);
        System.out.println(Arrays.toString(array));
    }

    public void sortHelper(int low, int high, int[] array, int k) {
        while (low < high) {
            while (low < high && array[high] > k) --high;
            while (low < high && array[low] <= k) ++low;
            swap(array, low, high);
        }
    }

    public boolean SandQ(Stack S, Queue Q, int E) {
        boolean flag = false;
        while (!S.isEmpty()) {
            if (S.peek().equals(E))
                flag = true;
            Q.offer(S.pop());
        }
        while (!Q.isEmpty())
            S.push(Q.poll());
        while (!S.isEmpty())
            Q.offer(S.pop());
        while (!Q.isEmpty())
            S.push(Q.poll());
        return flag;
    }

    public void RotateArray(int[] arr, int d) {
        int temp[] = new int[arr.length];
        for (int i = d, k = 0; i < arr.length; i++, k++)
            temp[k] = arr[i];
        for (int i = 0; i < arr.length; i++)
            arr[i] = temp[i];
    }

    public int randomInteger(int n){
        return (int)(Math.random()*n);
    }
    public void shuffle(int[] arr){
        for(int i=arr.length-1;i>=0;i--){
            int randomIndex = randomInteger(i); //randomInteger(n) return random int < n
            int temp = arr[randomIndex];
            arr[randomIndex] = arr[i];
            arr[i] = temp;
        }
    }

}
