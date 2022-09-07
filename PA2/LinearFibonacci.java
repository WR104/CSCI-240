package PA2;

public class LinearFibonacci {
    public int a = 0;
    public int b = 1;
    public int LinearFibonacci(int k){

        if(k <= 1)
            return b;
        else{
            int temp = b;
            b = a+b;
            a = temp;
            LinearFibonacci(k-1);
        }
        return b;
    }
}
