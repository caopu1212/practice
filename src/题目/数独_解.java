package 题目;

import com.sun.xml.internal.ws.developer.MemberSubmissionAddressing;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;




public class 数独_解 {
    //从list A中删去list B中的数据
    public static List<Integer> listrem(List<Integer> listA, List<Integer> listB) {
        for (Iterator<Integer> itA = listA.iterator(); itA.hasNext(); ) {
            Integer temp = itA.next();
            // itA.next() 只能在外层循环里面调用1次
            for (int i = 0; i < listB.size(); i++) {
                if (temp.equals(listB.get(i)))
                // 你不该在这里多次调用itA.next()的
                {
                    itA.remove();
                }
            }
        }
        return listA;
    }

    //从list 中删去指定数据
    public static void remove(List<Integer> list, int target) {
        for (int i = list.size() - 1; i >= 0; i--) {
            int item = list.get(i);
            if (item == target) {
                list.remove(item);
            }
        }
    }


    //验证数独是否完整
    private static boolean valid_iscomplete(int[][] sudoku_target) {
        for (int i = 0; i < sudoku_target.length; i++) {
            for (int j = 0; j < sudoku_target[i].length; j++) {
                if (sudoku_target[i][j] == 0) {
                    return false;
                }
            }
        }
        return true;
    }

    //检测每一行和列是否满足规则
    private static boolean valid_line_and_row(int[][] sudoku_target) {
        for (int i = 0; i < 9; i++) {
            int count = 0;
            int a = 0;
            while (count < 9) {
                a = a + sudoku_target[i][count];
                count++;
            }
            if(a != 45){
                return false;
            }
        }
        for (int j = 0;j < 9 ; j++){
            int count = 0;
            int a = 0;
            while (count < 9) {
                a = a + sudoku_target[count][j];
                count++;
            }
            if(a != 45){
                return false;
            }
        }
        return true;
    }
    //检测每个3x3宫是否符合
    public static boolean valid_gong_all(int[][] sudoku_calculat) {

        //左到右 上到下
        for(int templine = 0;templine < 3;templine++ ){
            for(int tempcolumn = 0;tempcolumn < 3;tempcolumn++ ){
                List<Integer> calculat = new ArrayList<>();

                if (templine == 0 && tempcolumn == 0) {
                    for (int i = 0; i < 3; i++) {
                        for (int j = 0; j < 3; j++) {
                            calculat.add(sudoku_calculat[i][j]);
                        }
                    }
                    int result = 0;
                    for (int i = 0; i < calculat.size(); i++) {
                        result = result + calculat.get(i);
                    }
                    if (result == 45) {
                    } else {
                        return false;
                    }
                }
                if (templine == 0 && tempcolumn == 1) {
                    for (int i = 0; i < 3; i++) {
                        for (int j = 3; j < 6; j++) {
                            calculat.add(sudoku_calculat[i][j]);
                        }
                    }
                    int result = 0;
                    for (int i = 0; i < calculat.size(); i++) {
                        result = result + calculat.get(i);
                    }
                    if (result == 45) {
                    } else {
                        return false;
                    }
                }
                if (templine == 0 && tempcolumn == 2) {
                    for (int i = 0; i < 3; i++) {
                        for (int j = 6; j < 9; j++) {
                            calculat.add(sudoku_calculat[i][j]);
                        }
                    }
                    int result = 0;
                    for (int i = 0; i < calculat.size(); i++) {
                        result = result + calculat.get(i);
                    }
                    if (result == 45) {
                    } else {
                        return false;
                    }
                }
                if (templine == 1 && tempcolumn == 0) {
                    for (int i = 3; i < 6; i++) {
                        for (int j = 0; j < 3; j++) {
                            calculat.add(sudoku_calculat[i][j]);
                        }
                    }
                    int result = 0;
                    for (int i = 0; i < calculat.size(); i++) {
                        result = result + calculat.get(i);
                    }
                    if (result == 45) {
                    } else {
                        return false;
                    }
                }
                if (templine == 1 && tempcolumn == 1) {
                    for (int i = 3; i < 6; i++) {
                        for (int j = 3; j < 6; j++) {
                            calculat.add(sudoku_calculat[i][j]);
                        }
                    }
                    int result = 0;
                    for (int i = 0; i < calculat.size(); i++) {
                        result = result + calculat.get(i);
                    }
                    if (result == 45) {
                    } else {
                        return false;
                    }
                }
                if (templine == 1 && tempcolumn == 2) {
                    for (int i = 3; i < 6; i++) {
                        for (int j = 6; j < 9; j++) {
                            calculat.add(sudoku_calculat[i][j]);
                        }
                    }
                    int result = 0;
                    for (int i = 0; i < calculat.size(); i++) {
                        result = result + calculat.get(i);
                    }
                    if (result == 45) {
                    } else {
                        return false;
                    }
                }
                if (templine == 2 && tempcolumn == 0) {
                    for (int i = 6; i < 9; i++) {
                        for (int j = 0; j < 3; j++) {
                            calculat.add(sudoku_calculat[i][j]);
                        }
                    }
                    int result = 0;
                    for (int i = 0; i < calculat.size(); i++) {
                        result = result + calculat.get(i);
                    }
                    if (result == 45) {
                    } else {
                        return false;
                    }
                }
                if (templine == 2 && tempcolumn == 1) {
                    for (int i = 6; i < 9; i++) {
                        for (int j = 3; j < 6; j++) {
                            calculat.add(sudoku_calculat[i][j]);
                        }
                    }
                    int result = 0;
                    for (int i = 0; i < calculat.size(); i++) {
                        result = result + calculat.get(i);
                    }
                    if (result == 45) {
                    } else {
                        return false;
                    }
                }
                if (templine == 2 && tempcolumn == 2) {
                    for (int i = 6; i < 9; i++) {
                        for (int j = 6; j < 9; j++) {
                            calculat.add(sudoku_calculat[i][j]);
                        }
                    }
                    int result = 0;
                    for (int i = 0; i < calculat.size(); i++) {
                        result = result + calculat.get(i);
                    }
                    if (result == 45) {
                    } else {
                        return false;
                    }
                }

            }
        }
        return true;
    }


    //检测每个指定3x3宫是否符合。 输入参数：目标数组，目标行和列
    public static boolean valid_gong(int[][] sudoku_calculat, int line, int column) {
        //验证 宫
        int tempcolumn = column / 3;
        int templine = line / 3;
        List<Integer> calculat = new ArrayList<>();
        //左到右 上到下
        if (templine == 0 && tempcolumn == 0) {
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    calculat.add(sudoku_calculat[i][j]);
                }
            }
            int result = 0;
            for (int i = 0; i < calculat.size(); i++) {
                result = result + calculat.get(i);
            }
            if (result == 45) {
                return true;
            } else {
                return false;
            }
        }
        if (templine == 0 && tempcolumn == 1) {
            for (int i = 0; i < 3; i++) {
                for (int j = 3; j < 6; j++) {
                    calculat.add(sudoku_calculat[i][j]);
                }
            }
            int result = 0;
            for (int i = 0; i < calculat.size(); i++) {
                result = result + calculat.get(i);
            }
            if (result == 45) {
                return true;
            } else {
                return false;
            }
        }
        if (templine == 0 && tempcolumn == 2) {
            for (int i = 0; i < 3; i++) {
                for (int j = 6; j < 9; j++) {
                    calculat.add(sudoku_calculat[i][j]);
                }
            }
            int result = 0;
            for (int i = 0; i < calculat.size(); i++) {
                result = result + calculat.get(i);
            }
            if (result == 45) {
                return true;
            } else {
                return false;
            }
        }
        if (templine == 1 && tempcolumn == 0) {
            for (int i = 3; i < 6; i++) {
                for (int j = 0; j < 3; j++) {
                    calculat.add(sudoku_calculat[i][j]);
                }
            }
            int result = 0;
            for (int i = 0; i < calculat.size(); i++) {
                result = result + calculat.get(i);
            }
            if (result == 45) {
                return true;
            } else {
                return false;
            }
        }
        if (templine == 1 && tempcolumn == 1) {
            for (int i = 3; i < 6; i++) {
                for (int j = 3; j < 6; j++) {
                    calculat.add(sudoku_calculat[i][j]);
                }
            }
            int result = 0;
            for (int i = 0; i < calculat.size(); i++) {
                result = result + calculat.get(i);
            }
            if (result == 45) {
                return true;
            } else {
                return false;
            }
        }
        if (templine == 1 && tempcolumn == 2) {
            for (int i = 3; i < 6; i++) {
                for (int j = 6; j < 9; j++) {
                    calculat.add(sudoku_calculat[i][j]);
                }
            }
            int result = 0;
            for (int i = 0; i < calculat.size(); i++) {
                result = result + calculat.get(i);
            }
            if (result == 45) {
                return true;
            } else {
                return false;
            }
        }
        if (templine == 2 && tempcolumn == 0) {
            for (int i = 6; i < 9; i++) {
                for (int j = 0; j < 3; j++) {
                    calculat.add(sudoku_calculat[i][j]);
                }
            }
            int result = 0;
            for (int i = 0; i < calculat.size(); i++) {
                result = result + calculat.get(i);
            }
            if (result == 45) {
                return true;
            } else {
                return false;
            }
        }
        if (templine == 2 && tempcolumn == 1) {
            for (int i = 6; i < 9; i++) {
                for (int j = 3; j < 6; j++) {
                    calculat.add(sudoku_calculat[i][j]);
                }
            }
            int result = 0;
            for (int i = 0; i < calculat.size(); i++) {
                result = result + calculat.get(i);
            }
            if (result == 45) {
                return true;
            } else {
                return false;
            }
        }
        if (templine == 2 && tempcolumn == 2) {
            for (int i = 6; i < 9; i++) {
                for (int j = 6; j < 9; j++) {
                    calculat.add(sudoku_calculat[i][j]);
                }
            }
            int result = 0;
            for (int i = 0; i < calculat.size(); i++) {
                result = result + calculat.get(i);
            }
            if (result == 45) {
                return true;
            } else {
                return false;
            }
        }
        return true;
    }

    //得到指定位置的可填入数字，返回是list
    public static List<Integer> selected_num(int target_line, int target_column) {
        int line, column;
        int[][] sudoku_temp = new int[9][9];
        sudoku_temp = sudoku_load();
        List<Integer> selected_number_line = new ArrayList<Integer>();
        List<Integer> selected_number_all = new ArrayList<Integer>(); //返回值：指定位置的可填数
        List<Integer> list_temp_line = new ArrayList<>();
        List<Integer> list_temp_column = new ArrayList<>();
        //将要解决的的数独写入

        //添加1-9待选数
        for (int i = 1; i < 10; i++) {
            selected_number_line.add(i);
        }
        //将已有数字从待选数字中去除 行
        for (column = 0; column < 9; column++) {
            if (sudoku_temp[target_line][column] != 0) {
                list_temp_line.add(sudoku_temp[target_line][column]);
                listrem(selected_number_line, list_temp_line);
            }
        }
        selected_number_all = selected_number_line;
        //将已有数字从待选数字中去除 列
        for (line = 0; line < 9; line++)
            if (sudoku_temp[line][target_column] != 0) {
                list_temp_column.add(sudoku_temp[line][target_column]);
                listrem(selected_number_all, list_temp_column);
            }
        return selected_number_all;
    }

    //将要解决的的数独写入
    public static int[][] sudoku_load() {
        int[][] sudoku_temp = new int[9][9];

        for (int column = 0; column < 9; column++) {
            for (int line = 0; line < 9; line++) {
                sudoku_temp[line][column] = sudoku(line, column);
            }
        }
        return sudoku_temp;
    }

    //如果可填入数为只有一个，直接填入. 入参：需要填入的数独  ：返回：填入后的数独
    public static int[][] sudoku_put_onlyone(int[][] sudoku_target) {

        for (int i = 0; i < sudoku_target.length; i++) {
            for (int j = 0; j < sudoku_target[i].length; j++) {
                if (selected_num(i, j).size() == 1 && sudoku_target[i][j] == 0) {
                    System.out.print("填入 " + "i = " + i + " j = " + j + "数值为: " + selected_num(i, j).get(0) + "\n");
                    sudoku_target[i][j] = selected_num(i, j).get(0);
                }
            }
        }
        return sudoku_target;
    }



    //要解决的数独
    public static int sudoku(int line, int column) {

        int[][] sudoku = {    //数独   8 5 7 ；2 1； 7
                {3, 0, 0, 0, 2, 4, 9, 1, 6},
                {4, 0, 0, 9, 8, 6, 7, 5, 3},
                {9, 0, 6, 5, 3, 1, 8, 4, 2},
                {2, 1, 4, 8, 9, 7, 6, 3, 5},
                {8, 3, 7, 6, 5, 2, 4, 9, 1},
                {6, 5, 9, 4, 1, 3, 2, 8, 7},
                {1, 4, 2, 3, 7, 9, 5, 6, 8},
                {5, 9, 3, 2, 6, 8, 1, 7, 4},
                {7, 6, 8, 1, 4, 5, 3, 2, 9}

       /*       {9, 0, 0, 0, 2, 0, 0, 0, 0},
                {1, 0, 0, 0, 0, 6, 0, 4, 0},
                {0, 6, 4, 0, 3, 0, 0, 0, 0},
                {6, 9, 7, 0, 8, 0, 0, 0, 0},
                {0, 4, 0, 1, 0, 0, 2, 0, 3},
                {3, 0, 0, 0, 5, 0, 0, 0, 8},
                {0, 0, 0, 0, 4, 0, 0, 3, 2},
                {0, 7, 0, 3, 1, 0, 0, 0, 0},
                {0, 1, 0, 0, 6, 8, 0, 9, 7}   */
        };
        return sudoku[line][column];
    }

    public static void main(String[] args) {

        long startTime=System.currentTimeMillis();   //获取开始时间




        int[][] sudoku_temp;
        //载入要解决的数独
        sudoku_temp = sudoku_load();
        //填充可填数为1的空位
       sudoku_temp = sudoku_put_onlyone(sudoku_temp);

        //
        tag:for (int i = 0; i < sudoku_temp.length; i++) {
            for (int j = 0; j < sudoku_temp[i].length; j++) {
                if (sudoku_temp[i][j] == 0) {
                    System.out.println(selected_num(i, j));
                    sudoku_temp[i][j] = selected_num(i, j).get(0);
                    //如果数独完整了
                    if (valid_iscomplete(sudoku_temp)) {
                        //验证是否满足规则

                        if(valid_line_and_row(sudoku_temp) && valid_gong_all(sudoku_temp)){
                            System.out.println("验证成功哈哈哈哈哈啊哈哈哈哈");
                            break tag;
                        }else{
                            System.out.println("验证失败哈哈哈哈哈啊哈哈哈哈");
                        }

                    }
                }
            }
        }
        //打印数独
        for (int i = 0; i < sudoku_temp.length; i++) {
            for (int j = 0; j < sudoku_temp[i].length; j++) {
                System.out.print(sudoku_temp[i][j] + "  ");
            }
            System.out.println();
        }
        long endTime=System.currentTimeMillis(); //获取结束时间
        System.out.println("程序运行时间： "+(endTime-startTime)+"ms");

    }




}

/*进度 ：
* 回溯遍历
*
*
* 验证宫 有bug，。(解决
*填入候选数为1的空 bug(解决
*
*
*
*
* */

