package 题目;

import java.time.temporal.Temporal;
import java.util.ArrayList;
import java.util.Collections;

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

public class 乘积最大子序列_d {
    public static int maxProduct(ArrayList<Integer> nums) {

        int result = 0;
        int max = 0;
        while (nums.size() > 1) {
            ArrayList<Integer> temp_list = new ArrayList();
            for (int i : nums) {
                temp_list.add(i);
                int multiplication_result = 1;
                //得到了最大的
                for (int j : temp_list) {
                    multiplication_result = multiplication_result * j;
                    if (multiplication_result > max) {
                        max = multiplication_result;
                    }
                }
            }
            nums.remove(0);
        }

        result = max;
        return result;
    }

    public static void main(String[] args) {
        ArrayList<Integer> a = new ArrayList();
        Collections.addAll(a, -1, 0, 1, 2, -1, -4);

        System.out.println(maxProduct(a));

    }
}
