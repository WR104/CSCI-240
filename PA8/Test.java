package PA8;

import java.io.IOException;

public class Test {
    public static void main(String[] args) throws IOException {
        PopMap map = new PopMap("popSmall.txt");
        map.print();
//        map.find(6037);
//        map.find(6000);
//        map.insert(6066,1,"New County, CA");
//        map.insert(6065,2000,"Riverside, CA");
//        map.erase(6999);
//        map.erase(6075);
//        map.erase(6055);
        map.print();
    }
}
