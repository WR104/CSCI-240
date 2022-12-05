package FinalLab;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Comp {
    Map<Integer, Integer> map = new HashMap<>();

    public Comp() throws IOException {
        String path = "/Volumes/D/CSCI-240/FinalLab/large100k.txt";
        Scanner scanner = new Scanner(new File(path));
        while (scanner.hasNext()) {
            map.put(scanner.nextInt(), 1);
        }
    }

    public void run() {
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
}
