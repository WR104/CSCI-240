package PA3;

import net.datastructures.Stack;
import net.datastructures.LinkedStack;

public class Exercise1 {
    public boolean isMatched(String s) {
        String opening  = "({[";                
        String closing  = ")}]";                
        Stack<Character> buffer = new LinkedStack<>();
        for (char c : s.toCharArray()) {
          if (opening.indexOf(c) != -1)              
            buffer.push(c);
          else if (closing.indexOf(c) != -1) {        
            if (buffer.isEmpty())                     
              return false;
            if (closing.indexOf(c) != opening.indexOf(buffer.pop()))
              return false;                          
          }
        }
        return buffer.isEmpty();                     
      }
}
