package 题目;


/**
 * 问题地址：
 * https://onlinejudge.u-aizu.ac.jp/challenges/sources/UOA/UAPC/1021
 */


import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Scanner;


public class Emacslike_Editor {


    public ArrayList emacsLikeEditor(ArrayList<String> inputList) {

        ArrayList<String> textList = new ArrayList();
        ArrayList<String> operatorList = new ArrayList();

        int lineIndex = 0;
        String buffer = "";


        //将输入拆为text和操作
        for (int i = 0; i < inputList.size(); i++) {
            if (inputList.get(i).equals("END_OF_TEXT")) {
                lineIndex = i;
                break;
            }
            textList.add(inputList.get(i));
        }
        //添加cursor在目标文本最开始 作为光标 定位
        textList.set(0, "*cursor*" + textList.get(0));

        for (int j = lineIndex + 1; j < inputList.size(); j++) {
            operatorList.add(inputList.get(j));
            if (inputList.get(j).equals("-")) {
                break;
            }
        }


        //遍历操作符
        for (String operator : operatorList
        ) {
            int index = 0;
            for (String tempText : textList
            ) {
                if (tempText.contains("*cursor*")) {
                    index = textList.indexOf(tempText);
                }
            }

            switch (operator) {
                //光标移到当前行开头
                case "a":
                    for (String lineText : textList
                    ) {
                        if (lineText.contains("*cursor*")) {
                            lineText = lineText.replace("*cursor*", "");
                            lineText = "*cursor*" + lineText;
                            textList.set(index, lineText);
                            break;
                        }
                    }
                    break;
                //光标移动到当前行的行尾
                case "e":
                    for (String lineText : textList
                    ) {
                        if (lineText.contains("*cursor*")) {
                            lineText = lineText.replace("*cursor*", "");
                            lineText = lineText + "*cursor*";
                            textList.set(index, lineText);
                            break;
                        }
                    }

                    break;
                //移到上一行的首，若没有移到当前行的首
                case "p":
                    for (String lineText : textList
                    ) {
                        if (lineText.contains("*cursor*")) {
                            lineText = lineText.replace("*cursor*", "");
                            if (index == 0) {
                                lineText = "*cursor*" + lineText;
                                textList.set(index, lineText);
                                break;
                            } else {
                                textList.set(index, lineText);
                                textList.set(index - 1, "*cursor*" + textList.get(index - 1));
                                break;
                            }
                        }
                    }
                    break;


                //移到下一行的首，若没有移到当前行的首
                case "n":
                    for (String lineText : textList
                    ) {
                        if (lineText.contains("*cursor*")) {
                            lineText = lineText.replace("*cursor*", "");
                            if (index == textList.size() - 1) {
                                lineText = "*cursor*" + lineText;
                                textList.set(index, lineText);
                                break;
                            } else {
                                textList.set(index, lineText);
                                textList.set(index + 1, "*cursor*" + textList.get(index + 1));
                                break;
                            }
                        }
                    }
                    break;

                //光标向右一个，若是尾，则到下一行第一个（不然啥都不做）
                case "f":
                    for (String lineText : textList
                    ) {
                        if (lineText.contains("*cursor*")) {
                            int textIndex = lineText.indexOf("*cursor*");
//                            if (textIndex != 0) {
//                                textIndex = textInde - 1;
//                            }
                            lineText = lineText.replace("*cursor*", "");

                            //若不在尾 右移一位
                            if (textIndex != lineText.length()) {
                                StringBuilder sb = new StringBuilder(lineText);
                                sb.insert(textIndex + 1, "*cursor*");
                                textList.set(index, String.valueOf(sb));
                            }
                            //若不在最后一行 到下一行首
                            else if (index != textList.size() - 1) {
                                textList.set(index, lineText);
                                textList.set(index + 1, "*cursor*" + textList.get(index + 1));
                            }
                            break;
                        }
                    }
                    break;

                //光标向左一个，若是首，则到上一行尾（不然啥都不做）
                case "b":
                    for (String lineText : textList
                    ) {
                        if (lineText.contains("*cursor*")) {
                            int textIndex = lineText.indexOf("*cursor*");
//                            if (textIndex != 0) {
//                                textInde x= textInde - 1;
//                            }
                            lineText = lineText.replace("*cursor*", "");

                            //若不在首 左移一位
                            if (textIndex != 0) {
                                StringBuilder sb = new StringBuilder(lineText);
                                sb.insert(textIndex, "*cursor*");
                                textList.set(index, String.valueOf(sb));
                            }
                            //若不在第一行 加到上一行末
                            else if (index != 0) {
                                textList.set(index, lineText);
                                textList.set(index - 1, textList.get(index - 1) + "*cursor*");
                            }
                            break;
                        }
                    }
                    break;

                //删除当前光标位置的字符，若是行尾 且 不是最后一行则下一行加到这一行尾
                case "d":
                    for (String lineText : textList
                    ) {
                        if (lineText.contains("*cursor*")) {
                            int textIndex = lineText.indexOf("*cursor*");
                            lineText = lineText.replace("*cursor*", "");

                            //若是行尾 且 不是最后一行 则下一行加到这一行尾
                            if (textIndex == lineText.length() && index != textList.size() - 1) {

                                textList.set(index, lineText + textList.get(index + 1) + "*cursor*");
                                textList.remove(index + 1);

                            } else if (textIndex != lineText.length() - 1) {
                                StringBuilder sb = new StringBuilder(lineText);
                                sb.deleteCharAt(textIndex);
                                sb.insert(textIndex, "*cursor*");
                                textList.set(index, String.valueOf(sb));
                            }
                            break;
                        }
                    }
                    break;


                //如果光标指向行尾且当前行下方有一行，则执行上述命令d，并在缓冲区记录换行。
                //如果光标没有指出行尾，则在光标（含）和行尾之间截取字符，并记录在缓冲区中。此操作后，光标指示当前行的行尾。
                case "k":
                    for (String lineText : textList
                    ) {
                        if (lineText.contains("*cursor*")) {
                            int textIndex = lineText.indexOf("*cursor*");
                            lineText = lineText.replace("*cursor*", "");

                            //若是行尾 且 不是最后一行 则下一行加到这一行尾 并缓存换行符
                            if (textIndex == lineText.length() & index != textList.size() - 1) {
                                textList.set(index, lineText + textList.get(index + 1) + "*cursor*");
                                textList.remove(index + 1);
                                buffer = "\n";
                            } else if (textIndex != lineText.length()) {
                                buffer = lineText.substring(textIndex);
                                lineText = lineText + "*cursor*";
                                textList.set(index, lineText);
                            }
                            break;
                        }
                    }
                    break;
                //如果缓冲区为空，则什么都不做。
                //如果缓冲区正在保存换行符，则在光标的前面插入换行符。光标移动到新行的第一个字符。
                //如果缓冲区正在保存字符，则在光标处插入字符。光标移动到光标最初指向的字符或行尾。
                case "y":
                    for (String lineText : textList
                    ) {
                        if (lineText.contains("*cursor*")) {
                            int textIndex = lineText.indexOf("*cursor*");
                            lineText = lineText.replace("*cursor*", "");
                            //若缓存不是空
                            if (!buffer.equals("")) {
                                //若缓存是换行符
                                if (buffer.equals("\n")) {
                                    String tempText = lineText.substring(textIndex);
                                    lineText.replace("tempText", "");
                                    textList.set(index, lineText);
                                    textList.add(index + 1, "*cursor*" + tempText);
                                }
                                //若缓存是文本
                                else {
                                    StringBuilder sb = new StringBuilder(lineText);
                                    sb.insert(textIndex, buffer);
                                    lineText = sb + "*cursor*";
                                    textList.set(index, lineText);
                                }
                            }
                            break;
                        }
                    }
                    break;
            }
            System.out.println(textList);
            System.out.println(operator);

        }
        for (int i = 0; i < textList.size(); i++) {
            if (textList.get(i).contains("*cursor*")) {
                textList.set(i, textList.get(i).replace("*cursor*", ""));
            }
        }
        return textList;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Emacslike_Editor ee = new Emacslike_Editor();
        System.out.println("plz enter command");

        //输入
        ArrayList<String> inputList = new ArrayList();
        while (true) {
            String input = scanner.nextLine();

            if (input.equals("-")) {
                System.out.println("输入完成");
                break;
            }
            inputList.add(input);
        }
        ArrayList<String> result = ee.emacsLikeEditor(inputList);
        for (String lineText : result
        ) {
            System.out.println(lineText);
        }
    }
}
