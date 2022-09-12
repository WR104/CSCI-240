package PA3;

import net.datastructures.Stack;

import net.datastructures.LinkedStack;
public class Exercise2 {
    String error = "inappropriate expression";

    public String postfixExpresson(String expression){
        String[] string = expression.split(" ");
        Stack<Integer> buffer = new LinkedStack<>();
        
        for(String s : string){
            if(s.matches("-?\\d+"))    //check if it is a number
                buffer.push(Integer.parseInt(s));   //push to the stack if it is a number
            else{
                if(buffer.isEmpty()) return error;
                int y = buffer.pop();
                if(buffer.isEmpty()) return error;
                int x = buffer.pop();
                int res = helper(x, y, s);
                if(res == Integer.MIN_VALUE)   return error;
                buffer.push(res);
            }
        }
        
        int res = buffer.pop();
        if(!buffer.isEmpty())   return error;
        return Integer.toString(res);
    }

    public int helper(int x, int y, String o){
        if(o.equals("+"))
            return x + y;
        else if(o.equals("-"))
            return x - y;
        else if(o.equals("*"))
            return x * y;
        else if(o.equals("/")){
            if(y == 0)                      //can not divided by zero
                return Integer.MIN_VALUE;
            return x/y;
        }
        else if(o.equals("^")){
            if(x == 0 && y == 0)
                return Integer.MIN_VALUE;   //can not do 0^0
            return (int)Math.pow((double)x,(double)y);
        }
        
        return Integer.MIN_VALUE;
    }
}
