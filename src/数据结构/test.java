package 数据结构;

import java.util.HashSet;
import java.util.List;

public class test {

    public static String longest(String s1, String s2) {
        String total = s1 + s2;
        char[] totalChar = total.toCharArray();
        String[] array = new String[25];
        for (int  i = 0;  i < totalChar.length;  i++) {
            array[ total.charAt(i)-0 - 97] = String.valueOf(total.charAt(i));
        }
        String result = "";
        for (String s : array) {
            if (s != null)
                result = result + s;
        }
        return result;
    }

    public static void main(String[] args) {
        longest("asbvbnnpd","asdasd");
    }
}
