package PA2;

public class Test {

    public static void main(String[] args){
        PA2.Exercise1 list = new PA2.Exercise1();
        list.insertFront("Jo");
        list.insertFront("Jane");
        list.insertRear("John");
        list.insertRear("Kim");
        list.remove("Jo");
        list.print();

    }
}
