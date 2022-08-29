

package PA1;

import java.util.Random;

public class Exercise {
    public Boolean exercise1(int[] nums) {
        for (int i = 0; i < nums.length - 1; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[i] == nums[j]) return false;
            }
        }
        return true;
    }

    public int birthdayParadox(int n) {
        int[] people = new int[n];
        int res = 0;
        for (int count = 0; count < 10; count++) {
            Random birthday  = new Random();
            for (int i = 0; i < n; i++)
                people[i] = birthday .nextInt(365) + 1; //day between 1 - 365

            outerloop:
            for (int i = 0; i < n - 1; i++) {
                for (int j = i + 1; j < n; j++)
                    if (people[i] == people[j]) {
                        res += 1;
                        break outerloop;
                    }
            }
        }
        return res;
    }

    public void exercise2() {
        System.out.println("N        Count out of 10");
        for (int n=5;n<=100;n+=5)
        System.out.println(n+"        "+birthdayParadox(n));
    }

    public long EC1(int i){
        return (1L << i);
    }
}
