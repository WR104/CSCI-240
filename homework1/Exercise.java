package homework1;

import java.util.*;
public class Exercise {
    final String str = "1234";
    public void allPossibleString(){
        Set<String> res = new HashSet();
       for(int i=0;i<str.length();i++){
            String s = Character.toString(str.charAt(i));
            String tempStr = str.replace(s, "");
            for(int j=0;j<tempStr.length();j++){
                res.add(tempStr.substring(0, j)+s+tempStr.substring(j)); 
                System.out.println(tempStr.substring(0, j)+s+tempStr.substring(j));           
            }
            res.add(tempStr + s);
            System.out.println(tempStr + s);
       } 
       //res.stream().forEach(System.out::println);
    }
    
}
