package 题目;

/*给定一个可能含有重复元素的整数数组，要求随机输出给定的数字的索引。 您可以假设给定的数字一定存在于数组中。

        注意：
        数组大小可能非常大。 使用太多额外空间的解决方案将不会通过测试。

        示例:

        int[] nums = new int[] {1,2,3,3,3};
        Solution solution = new Solution(nums);

// pick(3) 应该返回索引 2,3 或者 4。每个索引的返回概率应该相等。
        solution.pick(3);

// pick(1) 应该返回 0。因为只有nums[0]等于1。
        solution.pick(1);*/

import sun.applet.Main;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class 返回索引_d {

    public static int pick(int target, int[] nums) {
        List<Integer> a = new ArrayList<>();
        int i;
        for(i = 0;i < nums.length;i++){
            if(nums[i] == target){
                a.add(i);
            }
        }
        int max=a.size()-1,min=0;
        int ran2 = (int) (Math.random()*(max-min)+min);
        return a.get(ran2);
    }

    public static void main(String[] args) {
        int[] nums = new int[] {1,2,3,3,3};
        System.out.println(pick(3,nums));
    }
}
