package 题目;


import java.util.Random;

public class 数独_生成 {
    public int[][] reversionInit() {
        int num[][] = new int[9][9];
        for (int i = 0; i < num.length; i++) {
            for (int j = 0; j < num[i].length; j++) {
                num[i][j] = 0;
            }
        }
        return num;
    }
    //生成一个随机数
    public int randomIndex(StringBuffer indexArray) {
        int index = (int) (Math.random() * 10);
        if (index == 9 || indexArray.indexOf(index + "") >= 0) {
            index = randomIndex(indexArray);
        }
        return index;
    }
    //数组的第一行数据,随机生成
    public void arrayRowOne(int[][] array) {
        StringBuffer indexArray = new StringBuffer();
        for (int i = 0; i < 9; i++) {
            int index = randomIndex(indexArray);
            if (indexArray.length() > 0) {
                indexArray.append(",");
            }
            indexArray.append(index);
        }
        String indexVal[] = indexArray.toString().split(",");
        for (int i = 0; i < array[0].length; i++) {
            array[0][i] = Integer.parseInt(indexVal[i]) + 1;
        }
    }
    public static int randomnum(int start, int end) {
        Random random = new Random();
        int randomnum = random.nextInt(end - start + 1) + start;
        return randomnum;
    }
    //根据数组的第一行数据,生成一个数独
    public int[][] reversion(int[][] array, int line_index, int column_index) {
        int line = line_index;
        int col = column_index;
//如果是大于数组的最后一行,则已得出正确的解
        if (line > array.length - 1) {
            return array;
        }
//如果是一行的列首,则回退到上一行
        if (col < 0) {
            line = line - 1;
            col = array[0].length - 1;
            return reversion(array, line, col);
        }
//如果是一行的列尾,则继续下一行的解值
        if (col > array[0].length - 1) {
            line = line + 1;
            col = 0;
            return reversion(array, line, col);
        }
        if (line == 0) {
            System.out.println("无解!!!");
            return null;
        }
//当前下标的值,用于初始值判断,若是0,则从9开始递减;若是1,则回退到上一列的值 .
        int temp = array[line][col];
        if (temp == 0) {
            temp = 9;
        } else {
            temp--;
        }
        boolean flag = true;
        do {
            int num_temp = temp;
            for (int i = 0; i < line; i++) {
//如果这一列上存在相同值,则temp减一
                if (temp == array[i][col]) {
                    temp--;
                    break;
                }
            }
            for (int j = 0; j < col; j++) {
//如果这一行上存在相同的值,则temp减一
                if (temp == array[line][j]) {
                    temp--;
                    break;
                }
            }
            if (num_temp != temp) {
                continue;
            }
            int k = (line / 3) * 3;  //起始行下标值
            int n_start = (col / 3) * 3;   //起始列下标值
            int n_len = n_start + 3;
            boolean finish_flag = true;
/**
 * 每9个小的九宫格中,不能存在同样的数据
 * */
            for (; k <= line; k++) {
                for (int n = n_start; n < n_len; n++) {
//小的九宫格,是否存在同样的值
                    if (temp == array[k][n]) {
                        temp--;
                        finish_flag = false;
                        break;
                    }
                }
                if (!finish_flag) {
                    break;
                }
            }
            if (num_temp != temp) {
                continue;
            }
//当前位置已找不到合适的值,回退到上一列中
            if (temp < 1) {
//清空回退下标后的所有数值
                for (int i = line; i < array.length; i++) {
                    for (int j = col; j < array[i].length; j++) {
                        array[i][j] = 0;
                    }
                }
                col--;
                return reversion(array, line, col);
            }
//找到合适的值,跳出本次循环
            flag = false;
        } while (flag);
//找到合适的值,继续找下一列的值
        array[line][col] = temp;
/*System.out.println("==================");
printArray(array);*/
        if (line > array.length - 1) {
//进入到数组的尾部，得出正确的解
            return array;
        }
        return reversion(array, line, ++col);
    }
    public static int account(int[][] array) {
        int[][] sudoku_temp = new int[9][9];
        sudoku_temp = array;
        int count = 0;
        for (int i1 = 0; i1 < sudoku_temp.length; i1++) {
            for (int j1 = 0; j1 < sudoku_temp[i1].length; j1++) {
                if (sudoku_temp[i1][j1] == 0) {
                    count++;
                }
            }
        }
        return count;
    }
    public static int[][] dig(int[][] array, int amount) {
        int[][] sudoku_temp = new int[9][9];
        sudoku_temp = array;
        while (account(sudoku_temp) < amount) {
            sudoku_temp[randomnum(0, 8)][randomnum(0, 8)] = 0;
        }
        return sudoku_temp;
    }
    //输出数组
    public void printArray(int[][] array) {
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[i].length; j++) {
//            if (j % 3 == 0) {
//              System.out.print("\t");
//            }
                if (i == 8 && j == 8) {
                    System.out.print(array[i][j] + "}");
                } else if (j == 0) {
                    System.out.print("{" + array[i][j] + ",");
                } else if (j == 8) {
                    System.out.print(array[i][j] + "},");
                } else {
                    System.out.print(array[i][j] + ",");
                }
            }
//            if ((i + 1) % 3 == 0) {
//                System.out.println();
//            }
            System.out.println();
        }
    }
    public static void main(String[] args) {
        数独_生成 re = new 数独_生成();
        int num[][] = re.reversionInit();
        //初始化第一行
        re.arrayRowOne(num);
        //得到一个正确的数独数据
        re.reversion(num, 1, 0);
        //amount控制需要挖多少个空
        re.printArray(dig(re.reversion(num, 1, 0), 55));
    }
}




