package 题目;
/*给定一个整数数组 nums ，找出一个序列中乘积最大的连续子序列（该序列至少包含一个数）。
        示例 1:
        输入: [2,3,-2,4]
        输出: 6
        解释: 子数组 [2,3] 有最大乘积 6。
        示例 2:
        输入: [-2,0,-1]
        输出: 0
        解释: 结果不能为 2, 因为 [-2,-1] 不是子数组。
     */
public class 乘积最大子序列ing {
    public int maxProduct(int[] nums) {
        int result = 0;
        int max = 0;
        int min = 0;
        int i = 0;
        for( i = 0;i < nums.length ; i++){
            if (nums[i] > max){
                max = nums[i];
            }
        }
        return result;
    }
}
