package FinalLab;

import java.util.Stack;

public class DAGExample {
    private int[][] matrix;
    private int n;

    public DAGExample() {
        n = 6;
        initial();
    }

    public void initial() {
        matrix = new int[n][n];
        matrix[0][2] = 1;
        matrix[0][3] = 1;
        matrix[1][3] = 1;
        matrix[2][4] = 1;
        matrix[3][4] = 1;
        matrix[4][5] = 1;
    }

    public void print() {
        for(int i=0;i<n;i++){
            for(int j=0;j<n;j++)
                System.out.print(matrix[i][j] + " ");
            System.out.println();
        }
        System.out.println();
        System.out.print("Vertex    Edges");
        for (int i = 0; i < n; i++) {
            System.out.print("\n" + i + "         ");
            for (int j = 0; j < n; j++)
                if (matrix[i][j] == 1)
                    System.out.print(j + " ");
        }
        System.out.println();
    }

    public void topological() {
        Stack<Integer> stack = new Stack<>();
        boolean[] visited = new boolean[n];
        for (int i = 0; i < n; i++)
            visited[i] = false;
        
        for(int i=0;i<n;i++){
            if(visited[i] == false){
                topologicalHelper(i, visited, stack);
            }
        }

        while(!stack.isEmpty())
            System.out.print(stack.pop() + " ");
        System.out.println();
    }

    public void topologicalHelper(int k, boolean[] visited, Stack<Integer> stack){
        visited[k] = true;
        for(int i=0;i<n;i++){
            if( !visited[i] && matrix[k][i] != 0)
                topologicalHelper(i, visited, stack);
        }
        stack.push(k);
    }
}
