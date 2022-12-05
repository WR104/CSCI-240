package FinalLab;

import java.io.IOException;

public class Test {
    public static void main(String[] args) throws IOException{
        DAGExample dag = new DAGExample();
        //dag.print();
        dag.topological();
    }
}
