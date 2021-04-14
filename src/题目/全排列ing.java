package 题目;

import java.util.List;

public class 全排列ing {
    public static void permute(int[] nums) {
        List<List<Integer>> result;
        List<Integer> temp = null;
        int count = 0;

        for(int i=0;i<nums.length;i++){
            System.out.println(i);
            temp.set(i, nums[i]);

        }
        System.out.println(temp);

    }

    public static void main(String[] args) {
        int[] nums={1,3,5,6,8};
        permute(nums);
    }
}
