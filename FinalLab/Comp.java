package FinalLab;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Comp {
    Map<Integer, Integer> map = new HashMap<>();
    Map<String, Integer> map2 = new HashMap<>();

    public Comp() throws IOException {
        String path = "/Volumes/D/CSCI-240/FinalLab/large100k.txt";
        Scanner scanner = new Scanner(new File(path));
        while (scanner.hasNext()) {
            int num = scanner.nextInt();
            map.put(num, num);
            map2.put(String.valueOf(num),num);
        }
    }

    public void run1() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Input x: ");
        int x = scanner.nextInt();
        System.out.print("Input t: ");
        int t = scanner.nextInt();
        while (x != -1) {
            if (map.containsKey(x) && map.containsKey(t - x))
                System.out.println("Yes\n");
            else
                System.out.println("No\n");
            System.out.print("Input x: ");
            x = scanner.nextInt();
            System.out.print("Input t: ");
            t = scanner.nextInt();
        }
    }

    public void run2() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Input x: ");
        int x = scanner.nextInt();
        String y = "";
        while(x != -1){
            y = reverse(x);
            if(map.containsKey(x) && map2.containsKey(y))
                System.out.println("Yes, both " + x + " and " + y + " are in the file.\n");
            else if(!map.containsKey(x))
                System.out.println("No, " + x + " is not in the file.\n");
            else if(!map2.containsKey(y))
                System.out.println("No, " + y + " is not in the file.\n");
            System.out.print("Input x: ");
            x = scanner.nextInt();
        }
    }

    public String reverse(int x) {
        String str = String.valueOf(x);
        return new StringBuffer(str).reverse().toString();
    }
}
