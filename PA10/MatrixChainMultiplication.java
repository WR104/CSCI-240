package PA10;

public class MatrixChainMultiplication {

    int [][] m;
    int n;
    public MatrixChainMultiplication(int[] d) {
        n = d.length - 1;
        m = new int[n][n];
        for (int a = 1; a < n; a++) {
            for (int i = 0; i < n - a; i++) {
                int j = i + a;
                m[i][j] = Integer.MAX_VALUE;
                for (int k = i; k < j; k++)
                    m[i][j] = Math.min(m[i][j], m[i][k] + m[k + 1][j] + d[i] * d[k + 1] * d[j + 1]);
            }
        }
    }

    public void print(){
        System.out.println("The resulting table: ");
        for(int i=0;i<n;i++){
            for(int j=0; j<n; j++)
                System.out.print(m[i][j] + " ");
            System.out.println();
        }
        System.out.println("The minimum number of operations: " + m[0][n-1]);
    }

}
