package 题目;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


public class 数独_解 {
    static  int count = 0;
    static class StopMsgException extends RuntimeException {
    }
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
    //检测每一行和列是否满足规则
    private static boolean valid_line_and_row(int[][] sudoku_target) {
        for (int i = 0; i < 9; i++) {
            int count = 0;
            int a = 0;
            while (count < 9) {
                a = a + sudoku_target[i][count];
                count++;
            }
            if (a != 45) {
//                System.out.println("line不满足  " + i);
                return false;
            }
        }
        for (int j = 0; j < 9; j++) {
            int count = 0;
            int a = 0;
            while (count < 9) {
                a = a + sudoku_target[count][j];
                count++;
            }
            if (a != 45) {
//                System.out.println("column 不满足   " + j);
                return false;
            }
        }
        return true;
    }
    //检测每个3x3宫是否符合
    public static boolean valid_gong_all(int[][] sudoku_calculat) {

        //左到右 上到下
        for (int templine = 0; templine < 3; templine++) {
            for (int tempcolumn = 0; tempcolumn < 3; tempcolumn++) {
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
                    if (result != 45) {
                        return false;
                    }
                } else if (templine == 0 && tempcolumn == 1) {
                    for (int i = 0; i < 3; i++) {
                        for (int j = 3; j < 6; j++) {
                            calculat.add(sudoku_calculat[i][j]);
                        }
                    }
                    int result = 0;
                    for (int i = 0; i < calculat.size(); i++) {
                        result = result + calculat.get(i);
                    }
                    if (result != 45) {
                        return false;
                    }
                } else if (templine == 0 && tempcolumn == 2) {
                    for (int i = 0; i < 3; i++) {
                        for (int j = 6; j < 9; j++) {
                            calculat.add(sudoku_calculat[i][j]);
                        }
                    }
                    int result = 0;
                    for (int i = 0; i < calculat.size(); i++) {
                        result = result + calculat.get(i);
                    }
                    if (result != 45) {
                        return false;
                    }
                } else if (templine == 1 && tempcolumn == 0) {
                    for (int i = 3; i < 6; i++) {
                        for (int j = 0; j < 3; j++) {
                            calculat.add(sudoku_calculat[i][j]);
                        }
                    }
                    int result = 0;
                    for (int i = 0; i < calculat.size(); i++) {
                        result = result + calculat.get(i);
                    }
                    if (result != 45) {
                        return false;
                    }
                } else if (templine == 1 && tempcolumn == 1) {
                    for (int i = 3; i < 6; i++) {
                        for (int j = 3; j < 6; j++) {
                            calculat.add(sudoku_calculat[i][j]);
                        }
                    }
                    int result = 0;
                    for (int i = 0; i < calculat.size(); i++) {
                        result = result + calculat.get(i);
                    }
                    if (result != 45) {
                        return false;
                    }
                } else if (templine == 1 && tempcolumn == 2) {
                    for (int i = 3; i < 6; i++) {
                        for (int j = 6; j < 9; j++) {
                            calculat.add(sudoku_calculat[i][j]);
                        }
                    }
                    int result = 0;
                    for (int i = 0; i < calculat.size(); i++) {
                        result = result + calculat.get(i);
                    }
                    if (result != 45) {
                        return false;
                    }
                } else if (templine == 2 && tempcolumn == 0) {
                    for (int i = 6; i < 9; i++) {
                        for (int j = 0; j < 3; j++) {
                            calculat.add(sudoku_calculat[i][j]);
                        }
                    }
                    int result = 0;
                    for (int i = 0; i < calculat.size(); i++) {
                        result = result + calculat.get(i);
                    }
                    if (result != 45) {
                        return false;
                    }
                } else if (templine == 2 && tempcolumn == 1) {
                    for (int i = 6; i < 9; i++) {
                        for (int j = 3; j < 6; j++) {
                            calculat.add(sudoku_calculat[i][j]);
                        }
                    }
                    int result = 0;
                    for (int i = 0; i < calculat.size(); i++) {
                        result = result + calculat.get(i);
                    }
                    if (result != 45) {
                        return false;
                    }
                } else if (templine == 2 && tempcolumn == 2) {
                    for (int i = 6; i < 9; i++) {
                        for (int j = 6; j < 9; j++) {
                            calculat.add(sudoku_calculat[i][j]);
                        }
                    }
                    int result = 0;
                    for (int i = 0; i < calculat.size(); i++) {
                        result = result + calculat.get(i);
                    }
                    if (result != 45) {
                        return false;
                    }
                }

            }
        }
        return true;
    }
    //可填数验证，宫
    public static List<Integer> valid_gong_select_num(int[][] sudoku_calculat, int line, int column, List<Integer> list_target) {
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
            listrem(list_target, calculat);
            return list_target;
        } else if (templine == 0 && tempcolumn == 1) {
            for (int i = 0; i < 3; i++) {
                for (int j = 3; j < 6; j++) {
                    calculat.add(sudoku_calculat[i][j]);
                }
            }
            listrem(list_target, calculat);
            return list_target;
        } else if (templine == 0 && tempcolumn == 2) {
            for (int i = 0; i < 3; i++) {
                for (int j = 6; j < 9; j++) {
                    calculat.add(sudoku_calculat[i][j]);
                }
            }
            listrem(list_target, calculat);
            return list_target;
        } else if (templine == 1 && tempcolumn == 0) {
            for (int i = 3; i < 6; i++) {
                for (int j = 0; j < 3; j++) {
                    calculat.add(sudoku_calculat[i][j]);
                }
            }
            listrem(list_target, calculat);
            return list_target;
        } else if (templine == 1 && tempcolumn == 1) {
            for (int i = 3; i < 6; i++) {
                for (int j = 3; j < 6; j++) {
                    calculat.add(sudoku_calculat[i][j]);
                }
            }
            listrem(list_target, calculat);
            return list_target;
        } else if (templine == 1 && tempcolumn == 2) {
            for (int i = 3; i < 6; i++) {
                for (int j = 6; j < 9; j++) {
                    calculat.add(sudoku_calculat[i][j]);
                }
            }
            listrem(list_target, calculat);
            return list_target;
        } else if (templine == 2 && tempcolumn == 0) {
            for (int i = 6; i < 9; i++) {
                for (int j = 0; j < 3; j++) {
                    calculat.add(sudoku_calculat[i][j]);
                }
            }
            listrem(list_target, calculat);
            return list_target;
        } else if (templine == 2 && tempcolumn == 1) {
            for (int i = 6; i < 9; i++) {
                for (int j = 3; j < 6; j++) {
                    calculat.add(sudoku_calculat[i][j]);
                }
            }
            listrem(list_target, calculat);
            return list_target;
        } else if (templine == 2 && tempcolumn == 2) {
            for (int i = 6; i < 9; i++) {
                for (int j = 6; j < 9; j++) {
                    calculat.add(sudoku_calculat[i][j]);
                }
            }
            listrem(list_target, calculat);
            return list_target;
        }
        return list_target;
    }
    //得到指定位置的可填入数字，返回是list
    public static List<Integer> selected_num(int[][] target_sudoku, int target_line, int target_column) {
        int line, column;
        int[][] sudoku_temp = new int[9][9];
//        sudoku_temp = sudoku_load();
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
        for (column = 0; column < 8; column++) {
            if (target_sudoku[target_line][column] != 0) {
                list_temp_line.add(target_sudoku[target_line][column]);
                listrem(selected_number_line, list_temp_line);
            }
        }
        selected_number_all = selected_number_line;
        //将已有数字从待选数字中去除 列
        for (line = 0; line < 8; line++)
            if (target_sudoku[line][target_column] != 0) {
                list_temp_column.add(target_sudoku[line][target_column]);
                listrem(selected_number_all, list_temp_column);
            }


        return valid_gong_select_num(target_sudoku, target_line, target_column, selected_number_all);
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
    //如果可填入数为只有一个，直接填入
    public static int[][] sudoku_put_onlyone(int[][] sudoku_target) {
        for (int i = 0; i < sudoku_target.length; i++) {
            for (int j = 0; j < sudoku_target[i].length; j++) {
                if (selected_num(sudoku_target, i, j).size() == 1 && sudoku_target[i][j] == 0) {
                    System.out.print("直接填入 " + "i = " + i + " j = " + j + "数值为: " + selected_num(sudoku_target, i, j).get(0) + "\n");
                    sudoku_target[i][j] = selected_num(sudoku_target, i, j).get(0);
                }
            }
        }
        return sudoku_target;
    }
    // 递归 主循环
    public static void solve(int[][] sudoku_target, int line, int column) {
        if (line == 8 && column == 9) {
            //已经成功了，打印数组即可
            if (valid_gong_all(sudoku_target) && valid_line_and_row(sudoku_target)) {
                count++;
                System.out.println("验证正确");
                System.out.println("这是第"+count+" 个解");
                for (int i = 0; i < sudoku_target.length; i++) {
                    for (int j = 0; j < sudoku_target[i].length; j++) {
                        System.out.print(sudoku_target[i][j] + "  ");
                    }
                    System.out.println();
                }
            }
            return;
        }
        if (column == 9) {
            line++;
            column = 0;
        }
        if (sudoku_target[line][column] == 0) {
            for (int i = 0; i < selected_num(sudoku_target, line, column).size(); i++) {
                sudoku_target[line][column] = selected_num(sudoku_target, line, column).get(i);
                solve(sudoku_target, line, column + 1);
                //用来重置
                sudoku_target[line][column] = 0;
            }
        } else {
            solve(sudoku_target, line, column + 1);
        }
    }
    //要解决的数独
    public static int sudoku(int line, int column) {

        int[][] sudoku = {    //题目.数独.数独_生成   8 5 7 ；2 1； 7；

                /*   {3, 0, 0, 0, 2, 4, 9, 1, 6},
                   {4, 0, 0, 9, 8, 6, 7, 5, 3},
                   {9, 0, 6, 5, 3, 1, 8, 4, 2},
                   {2, 1, 4, 8, 9, 7, 6, 3, 5},
                   {8, 3, 7, 6, 5, 2, 4, 9, 1},
                   {6, 5, 9, 4, 1, 3, 2, 8, 7},
                   {1, 4, 2, 3, 7, 9, 5, 6, 8},
                   {5, 9, 3, 2, 6, 8, 1, 7, 4},
                   {7, 6, 8, 1, 4, 5, 3, 2, 9}*/

                     {9, 0, 0, 0, 2, 0, 0, 0, 0},
                     {1, 0, 0, 0, 0, 6, 0, 4, 0},
                     {0, 6, 4, 0, 3, 0, 0, 0, 0},
                     {6, 9, 7, 0, 8, 0, 0, 0, 0},
                     {0, 4, 0, 1, 0, 0, 2, 0, 3},
                     {3, 0, 0, 0, 5, 0, 0, 0, 8},
                     {0, 0, 0, 0, 4, 0, 0, 3, 2},
                     {0, 7, 0, 3, 1, 0, 0, 0, 0},
                     {0, 0, 0, 0, 6, 8, 0, 9, 7}

                  /*{0, 0, 7, 0, 0, 0, 0, 0, 0},
                  {5, 0, 0, 4, 0, 0, 7, 0, 0},
                  {8, 0, 0, 0, 0, 1, 0, 0, 3},
                  {9, 0, 0, 8, 0, 0, 5, 0, 0},
                  {0, 4, 0, 0, 0, 0, 0, 6, 0},
                  {0, 0, 1, 3, 0, 0, 0, 0, 4},
                  {3, 0, 0, 5, 0, 0, 0, 0, 9},
                  {0, 0, 9, 0, 0, 6, 0, 0, 1},
                  {0, 0, 0, 0, 0, 0, 8, 0, 0}*/

            /*    {5,8,3,0,0,0,0,0,0},
                {4,0,0,0,0,0,0,0,0},
                {9,7,6,0,0,1,0,0,0},
                {0,0,7,0,5,0,0,0,0},
                {0,0,0,2,1,0,0,8,0},
                {0,1,0,8,0,9,5,0,0},
                {7,0,9,0,0,2,4,0,0},
                {2,0,8,0,0,0,0,0,0},
                {0,3,0,0,0,0,2,9,0}*/

             /*   {0,0,0,0,0,8,0,0,0},
                {5,0,0,0,0,0,0,0,0},
                {3,7,0,0,9,0,0,0,5},
                {9,0,0,0,0,0,0,0,3},
                {0,0,0,0,0,0,0,0,0},
                {0,0,6,0,0,1,0,8,0},
                {0,0,8,0,0,0,0,6,0},
                {0,0,4,0,8,0,0,0,0},
                {0,0,0,5,3,0,0,0,0}
*/
               /* {0,0,5,7,0,0,0,0,0},
                {4,0,0,0,0,0,9,3,0},
                {0,7,0,0,0,0,8,5,1},
                {0,9,8,0,5,4,0,0,2},
                {6,0,0,2,1,3,0,0,0},
                {0,0,0,0,0,0,0,0,6},
                {0,0,0,0,0,0,2,0,0},
                {0,0,0,0,0,0,0,8,5},
                {1,0,3,0,0,5,0,0,9}*/

//
//                {8, 0, 0, 0, 0, 0, 0, 0, 0},
//                {0, 0, 3, 6, 0, 0, 0, 0, 0},
//                {0, 7, 0, 0, 9, 0, 2, 0, 0},
//                {0, 5, 0, 0, 0, 7, 0, 0, 0},
//                {0, 0, 0, 0, 4, 5, 7, 0, 0},
//                {0, 0, 0, 1, 0, 0, 0, 3, 0},
//                {0, 0, 1, 0, 0, 0, 0, 6, 8},
//                {0, 0, 8, 5, 0, 0, 0, 1, 0},
//                {0, 9, 0, 0, 0, 0, 4, 0, 0}
        };
        return sudoku[line][column];
    }
    public static void main(String[] args) {
        long startTime = System.currentTimeMillis();   //获取开始时间
        List<String> list_location_storager = new ArrayList<>();
        int[][] sudoku_temp;
        //载入要解决的数独
        sudoku_temp = sudoku_load();
        //填充可填数为1的空位
        sudoku_temp = sudoku_put_onlyone(sudoku_temp);
        try {
            solve(sudoku_temp, 0, 0);
        } catch (StopMsgException e) {
            System.out.println(e);
        }
        long endTime = System.currentTimeMillis(); //获取结束时间
        System.out.println("用时： " + (endTime - startTime) + "ms");

    }
}


