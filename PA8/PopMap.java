package PA8;

import net.datastructures.Entry;
import net.datastructures.TreeMap;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.Scanner;

public class PopMap {
    public TreeMap<Integer, String> map;

    public PopMap(String file) throws IOException {
        Scanner scanner = new Scanner(new File("D:\\CSCI-240\\PA7\\" + file));
        map = new TreeMap<>();
        scanner.nextLine();
        while (scanner.hasNext()) {
            String s = scanner.nextLine();
            int key = Integer.parseInt(s.substring(0,4));
            String value = s.substring(5);
            map.put(key,value);
        }
        scanner.close();
    }

    public void find(int code){
        map.setExaminedNodes();
        if(map.get(code) == null) {
            System.out.println("Can not find " + code + "; Examined Nodes are: " + map.ExmaineNodes());
        }
        else {
            map.setExaminedNodes();
            System.out.println(code + ": " + map.get(code) + "; Examined Nodes are: " + map.ExmaineNodes());
        }
    }

    public void insert(int code, int pop, String county){
        String value = String.valueOf(pop)+",\"" + county + "\"";
        map.setExaminedNodes();
        if(map.get(code) == null){
            map.put(code,value);
            System.out.println(code + ": " + value  + " has been successfully inserted" + "; Examined Nodes are: " + map.ExmaineNodes());
        }
        else{
            map.setExaminedNodes();
            String old = map.put(code,value);
            System.out.println(code + ": " + old + " has been replaced to " + value  + "; Examined Nodes are: " + map.ExmaineNodes());
        }
    }

    public void erase(int code){
        map.setExaminedNodes();
        if(map.get(code) == null){
            System.out.println("code is not found" + "; Examined Nodes are: " + map.ExmaineNodes());
        } else {
            map.setExaminedNodes();
            String old = map.remove(code);
            System.out.println(code + ": " + old + " has been removed" + "; Examined Nodes are: " + map.ExmaineNodes());
        }
    }

    public void print(){
        Iterator<Entry<Integer,String>> it = map.entrySet().iterator();
        System.out.println("-------------------------------------------------");
        while(it.hasNext()){
            Entry<Integer, String> e = it.next();
            System.out.println(e.getKey() + ": " + e.getValue());
        }
        System.out.println("-------------------------------------------------");
    }
}
