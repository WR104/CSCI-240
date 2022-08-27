

package PA1;

public class Exercise {
    public Boolean exercise1 (int[] nums){
        for(int i=0;i<nums.length-1;i++){
            for(int j=i+1;j<nums.length;j++){
                if(nums[i] == nums[j])
                    return false;
            }
        }
        return true;
    }
}
