package PA1;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

public class GameScore {
    private String name;
    private int nums;
    private List<MyGameEntry> board;

    public GameScore(String n){
        name = n;
        nums = 1;
        board= new ArrayList<>();
    }

    public void add(MyGameEntry e){
        board.add(e);
        board = board.stream().sorted(Comparator.comparing(MyGameEntry::getScore).reversed())
                .collect(Collectors.toList());// sort the list through scores
    }

    public void print(){
        Iterator<MyGameEntry> iter = board.iterator();
        while(iter.hasNext()){
            System.out.println(iter.next().toString());
        }
    }
}
