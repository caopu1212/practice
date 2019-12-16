package 题目;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

//给定一个包含 n 个整数的数组 nums，判断 nums 中是否存在三个元素 a，b，c ，使得 a + b + c = 0 ？找出所有满足条件且不重复的三元组。
//

//
//例如, 给定数组 nums = [-1, 0, 1, 2, -1, -4]，
//

public class 三数之和_d {
    public static void threeSum(ArrayList<Integer> nums) {
        int a, b, c;
        int a_and_b;
        List<Integer> temp = new ArrayList<>();
        for (a = 0; a < nums.size(); a++) {
            for (b = 0; b < nums.size(); b++) {
                if (a != b) {
                    a_and_b = nums.get(a)+ nums.get(b);
                    for (c = 0; c < nums.size(); c++) {
                        if (c != a && c != b) {
                            if (nums.get(c) + a_and_b == 0) {
                                List<Integer> result = new ArrayList<>();
                                result.add(nums.get(a));
                                result.add(nums.get(b));
                                result.add(nums.get(c));
                                System.out.println(result);
                            }
                        }
                    }
                }
            }
        }
        return;
    }

    public static void main(String[] args) {
        ArrayList<Integer> a = new ArrayList();
        Collections.addAll(a,-1,0,1,2,-1,-4);
        threeSum(a);
    }
}
