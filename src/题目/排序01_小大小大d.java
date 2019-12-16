package 题目;
/*给定一个无序的数组 nums，将它重新排列成 nums[0] < nums[1] > nums[2] < nums[3]... 的顺序。

示例 1:

输入: nums = [1, 5, 1, 1, 6, 4]
输出: 一个可能的答案是 [1, 4, 1, 5, 1, 6]

示例 2:

输入: nums = [1, 3, 2, 2, 3, 1]
输出: 一个可能的答案是 [2, 3, 1, 3, 1, 2]
说明:
你可以假设所有输入都会得到有效的结果。

进阶:
你能用 O(n) 时间复杂度和 / 或原地 O(1) 额外空间来实现吗？*/

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class 排序01_小大小大d {

    public static List sort_list(List target_list) {
        List<Integer> temp_list = new ArrayList();
        List<Integer> aim_list = new ArrayList();
        temp_list = target_list;
        std:while (temp_list.size() != 0) {
            int max = 0;
            int min = temp_list.get(0);
            int max_count = 0;
            int min_count = 0;
            if (temp_list.size() == 1){
                aim_list.add(temp_list.get(0));
                break std;
            }
            for (int i = 0; i < temp_list.size(); i++) {
                if (temp_list.get(i) > max) {
                    max = temp_list.get(i);
                    max_count = i;
                }
                if (temp_list.get(i) < min) {
                    min = temp_list.get(i);
                    min_count = i;
                }
            }
            aim_list.add(temp_list.get(min_count));
            aim_list.add(temp_list.get(max_count));
            temp_list.remove(min_count);
            if(min_count < max_count){
                temp_list.remove(max_count-1);
            }else{
                temp_list.remove(max_count);
            }
        }
        return aim_list;
    }
    public static void main(String[] args) {
        List a= new ArrayList();
        int[] w = {1,4,2,5,6,5,8,9,4,5,4,4};
        for(int i= 0 ; i < w.length;i ++){
            a.add(w[i]);
        }
        System.out.println(sort_list(a));
    }
}

