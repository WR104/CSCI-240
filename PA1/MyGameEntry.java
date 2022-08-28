package PA1;

public class MyGameEntry {
    private String name;
    private int score;
    private String date;

    public MyGameEntry(String n, int s, String d) {
        name = n;
        score = s;
        date = d;
        if (score < 0 || score > 1000)
            score = 0;
    }

    public String getName() {
        return name;
    }

    public int getScore() {
        return score;
    }

    public String getDate() {
        return date;
    }

    public String toString() {
        return name + "    " + score + "    " + date;
    }

}
