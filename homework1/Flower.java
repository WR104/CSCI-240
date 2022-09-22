package homework1;

public class Flower{  //class name: Flower
    private String name;
    private int number;
    private float price;

    Flower(String s, int n, float p){
        name = s;
        number = n;
        price = p;
    }

    public void setName(String s) { name = s; }
    public void setNumber(int n) { number = n; }
    public void setPrice(float p) { price = p; }

    public String getName() { return name; }
    public int getNumber() { return number; }
    public float getPrice() { return price; }
}
