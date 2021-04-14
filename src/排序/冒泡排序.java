package 排序;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class 冒泡排序 {

/*
    Descending  降序
    Ascending order 升序*/


//降序
    public static List sort_list_ascending(List target_list) {
        ArrayList<Integer> temp_list = new ArrayList();
        int temp = 0;
        for (int i = 0; i <= target_list.size(); i++) {
            //转object为int然后比较大小，若前面的比后面的大
            for (int j = 0; j < target_list.size() - 1; j++)
                if (Integer.parseInt(target_list.get(j + 1).toString()) < Integer.parseInt(target_list.get(j).toString())) {
                    temp = Integer.parseInt(target_list.get(j).toString());
                    target_list.set(j,target_list.get(j+1));
                    target_list.set(j+1,temp);
                }
        }
        temp_list = (ArrayList<Integer>) target_list;
        return temp_list;
    }

    //升序
    public static List sort_list_decending(List target_list) {
        ArrayList<Integer> temp_list = new ArrayList();


        int temp = 0;

        for (int i = 0; i <= target_list.size(); i++) {

            //转object为int然后比较大小，若后面的比前面的大
            for (int j = 0; j < target_list.size() - 1; j++)
                if (Integer.parseInt(target_list.get(j + 1).toString()) > Integer.parseInt(target_list.get(j).toString())) {
                    temp = Integer.parseInt(target_list.get(j).toString());
                    target_list.set(j,target_list.get(j+1));
                    target_list.set(j+1,temp);
                }
        }
        temp_list = (ArrayList<Integer>) target_list;
        return temp_list;
    }



    public static void main(String[] args) {
        ArrayList<Integer> a = new ArrayList();
        Collections.addAll(a, 4,1,6,8,5,6);
        System.out.println("降序 ： " + sort_list_decending(a));

//        ArrayList<Integer> b = new ArrayList();
//        Collections.addAll(b, 4,1,6,8,5,6);
//        System.out.println("升序 ： " + sort_list_decending(a));

    }

}
