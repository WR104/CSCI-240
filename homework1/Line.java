package homework1;

public class Line {
    private double a;
    private double b;

    Line(int a1, int b1){
        a = a1;
        b = b1;
    }
    public double getA(){ return a; }
    public double getB() { return b; }

    public double intersect(Line line) throws Exception {
        if(a == line.getA())
            throw new Exception("Parallel");
        else{
            return (line.getB()-b)/(a-line.getA());
        }
    }

}
