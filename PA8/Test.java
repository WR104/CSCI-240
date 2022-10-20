package PA8;

import java.io.IOException;

public class Test {
    public static void main(String[] args) throws IOException {
        PopMap map = new PopMap("popSmall.txt");
        map.print();
    }
}
