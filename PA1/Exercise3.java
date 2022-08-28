package PA1;

public class Exercise3 extends JavaSourceCode.dsaj.design.Progression {
    protected long next;

    public Exercise3(){
        current = 2;
        next = 200;
    }

    public Exercise3(long first, long second){
        current = first;
        next = second;
    }

    @Override
    public long nextValue(){
        long answer = current;
        current = next;
        next = Math.abs(answer - next);
        return answer;
    }

    public void print(int n){
        System.out.print(nextValue());
        for(int i=1;i<n;i++)
            System.out.print(" "+nextValue());
        System.out.println();
    }

}
