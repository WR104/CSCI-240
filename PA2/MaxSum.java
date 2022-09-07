package PA2;

public class MaxSum {

    public int MaxSum(int[] nums){
        int max = Integer.MIN_VALUE, cur=0;
        for(int i=0;i<nums.length-1;i++){
            cur=nums[i];
            max = Math.max(max,cur);
            for(int j=i+1;j<nums.length;j++){
                cur += nums[j];
                max = Math.max(max,cur);
            }
        }
        return max;
    }
}
