package 题目;

import com.sun.org.apache.xerces.internal.dom.PSVIAttrNSImpl;

import java.util.ArrayList;
import java.util.List;

public class 水仙花数 {
    //入参决定位数
    public static List test(int n) {
        long target = 100;
        ArrayList resultList = new ArrayList();
        while (true){
            //指定终止位数
            if (String.valueOf(target).length() == n+1 ){
                return resultList;
            }
            long temp = 0;
            for (int i = 0; i < String.valueOf(target).length() ; i++) {
                long originNum = Long.parseLong(String.valueOf(String.valueOf(target).charAt(i)));
                temp = (long) (Math.pow(originNum,String.valueOf(target).length()) + temp);
            }
            if (temp == target){
                System.out.println(target);
                resultList.add(target);
            }
            target++;
        }
    }

    public static void main(String[] args) {
        System.out.println(test(7));
    }
}
