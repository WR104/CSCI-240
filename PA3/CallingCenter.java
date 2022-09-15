package PA3;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;
public class CallingCenter {
    int t;
    int n;
    int m;
    Queue<Integer> queue = new LinkedList<>();
    Queue<Integer> done = new LinkedList<>();
    public CallingCenter(int a, int b, int c){ t = a; n = b; m = c; }

    public void Simulation(){
        Random r = new Random();
        int c = r.nextInt(n) + 1;   //customer calling time
        int s = 1;        //serving finish time
        boolean serving = false;
        int ID = 1;
        System.out.println("Time        Activites");
        for(int i=1; i <= t; i++){
            System.out.printf("%03d         ",i);
            if(c == i){     //a customer calls
                System.out.print("Customer "+ ID + " calls. ");
                queue.offer(ID++);
                c = r.nextInt(n) + i + 1;    //schedule next customer;
            }
            if(serving && s == i){
                System.out.print("Done with Customer " + done.poll() + ". ");
                serving = false;
            }
            else if(!serving && !queue.isEmpty()){
                System.out.print("Serving Customer " + queue.element() +". ");
                done.offer(queue.poll());
                s = r.nextInt(m) + i + 1;   //schedule serving time
                serving = true;
            }
            
            System.out.println();
        }
    }
}
