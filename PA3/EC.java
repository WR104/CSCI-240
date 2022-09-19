package PA3;

import java.util.*;
public class EC {
    Stack<Character> operator = new Stack<Character>();
    String res;
    EC(String s){
        res = "";
        for(char c : s.toCharArray()){
            if(c == '(')
                operator.push(c);
            if(c == ')'){
                while(!operator.isEmpty() && operator.peek() != '(')
                    res += operator.pop();
                operator.pop();
            }
            if(Character.isDigit(c))
                res += c;
            else if(!Character.isDigit(c) && precedence(c) >= 0){
                while(!operator.isEmpty() && precedence(operator.peek())>=precedence(c))
                    res += operator.pop();
                operator.push(c);
            }
        }
        while(!operator.isEmpty())
            res += operator.pop();
    }
    public String toString(){
        return res;
    }

    public int precedence(char x){
        if(x == '^')
            return 2;
        if(x == '*' || x == '/')
            return 1;
        if(x == '+' || x == '-')
            return 0;
        return -1;
    }

}
