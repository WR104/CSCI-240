package PA9;

public class Entry <K,V>{
    private K key;
    private V value;
    public Entry(K k, V v){
        key = k;
        value = v;
    }
    public K getKey(){
        return key;
    }
    public V getValue(){
        return value;
    }
    public String toString(){
        if(key.getClass() == String.class)
            return "<\"" + key + "\"" + "," + value + ">";
        if(value.getClass() == String.class)
            return "<" + key + "," + "\"" + value + "\">";

        return "<" + key + "," + value + ">";
    }
}
