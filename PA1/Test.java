package PA1;

public class Test {
    public static void main(String[] args){
        GameScore g1 = new GameScore("Classic Pac-Man");
        g1.add(new MyGameEntry("Jill",980,"08/05/2021"));
        g1.add(new MyGameEntry("Jack",600,"08/18/2021"));
        g1.add(new MyGameEntry("Rob",875,"07/30/2021"));
        g1.add(new MyGameEntry("Rob" ,900,"08/02/2021"));
        g1.print();

    }
}
