package PA2;

public class Exercise1{
    static private String[] names = new String[10];
    static private int nums = 0;

    public void insertFront(String s){
        if(nums >= 10)
            return;
        nums += 1;
        for(int i=nums-1; i>0; i--)
            names[i] = names[i-1];
        names[0] = s;
    }

    public void insertRear(String s){
        if(nums >= 10)
            return;
        names[nums] = s;
        nums += 1;
    }

    public void remove(String s){
        int index = -1;
        for(int i=0;i<nums;i++)
            if(names[i].equals(s))
                index = i;
        if(index < 0)
            return;
        for(int i=index;i<nums-1;i++)
            names[i] = names[i+1];
        names[nums-1] = "";
        nums -= 1;
    }

    public void print(){
        for(int i=0;i<nums;i++)
            System.out.print(names[i]+" ");
        System.out.println();
    }

    public void recPring(int count){    //count start from 0
        if(count == nums)
            return;
        System.out.println(names[count]);
        recPring(count + 1);
    }
}