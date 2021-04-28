package 题目;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

/**
 * 1016. 子串能表示从 1 到 N 数字的二进制串
 * 给定一个二进制字符串 S（一个仅由若干 '0' 和 '1' 构成的字符串）和一个正整数 N，
 * 如果对于从 1 到 N 的每个整数 X，其二进制表示都是 S 的子串，就返回 true，否则返回 false。
 * <p>
 * 示例 1：
 * 输入：S = "0110", N = 3
 * 输出：true
 * 示例 2：
 * 输入：S = "0110", N = 4
 * 输出：false
 * 提示：
 * 1 <= S.length <= 1000
 * 1 <= N <= 10^9
 **/
public class 子串能表示从_1_到_N_数字的二进制串_d {
    public static String DecimaltoBinary(Integer Decimal) {
        return Integer.toBinaryString(Decimal);
    }

    public static boolean function(String S, Integer N) {
        boolean result = true;
        List<String> result_list = new ArrayList<>();
        List<String> DecimaltoBinary_list = new ArrayList<>();
        for (int i = 1; i <= N; i++) {
            DecimaltoBinary_list.add(DecimaltoBinary(i));
        }
        A:
        for (String DecimaltoBinary : DecimaltoBinary_list) {
            B:
            for (int j = 0; j < S.length(); j++) {
                String temp_binary = "";
                for (int i = j; i < S.length(); i++) {
                    temp_binary = temp_binary + String.valueOf(S.charAt(i));
                    //如果第一位和目标二级制不一样，跳出，首位加一个
                    if (temp_binary.charAt(0) == DecimaltoBinary.charAt(0)) {
                        if (temp_binary.equals(DecimaltoBinary)) {
                            result_list.add(DecimaltoBinary);
                            break B;
                        }
                    } else {
                        break;

                    }
                }
            }
        }
        if (result_list.size() != DecimaltoBinary_list.size()) {
            result = false;
        }
        return result;
    }

    public static void main(String[] args) {
        System.out.println(function("0110", 3));
        System.out.println(function("0110", 4));
    }

}
