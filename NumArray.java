public class NumArray {
    private int[] prefixsum;
    public NumArray(int[] nums) {
        prefixsum = new int[nums.length + 1];
        for(var i = 0;i < nums.length;i++){
            prefixsum[i + 1] = prefixsum[i] + nums[i];
        }
    }
    
    public int sumRange(int left, int right) {
        return prefixsum[right + 1] - prefixsum[left];
    }
}
