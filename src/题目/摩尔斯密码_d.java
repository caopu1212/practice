package 题目;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/*国际摩尔斯密码定义一种标准编码方式，将每个字母对应于一个由一系列点和短线组成的字符串， 比如: "a" 对应 ".-", "b" 对应 "-...", "c" 对应 "-.-.", 等等。

为了方便，所有26个英文字母对应摩尔斯密码表如下：

[".-","-...","-.-.","-..",".","..-.","--.","....","..",".---","-.-",".-..","--","-.","---",".--.","--.-",".-.","...","-","..-","...-",".--","-..-","-.--","--.."]
给定一个单词列表，每个单词可以写成每个字母对应摩尔斯密码的组合。例如，"cab" 可以写成 "-.-..--..."，(即 "-.-." + "-..." + ".-"字符串的结合)。我们将这样一个连接过程称作单词翻译。

返回我们可以获得所有词不同单词翻译的数量。

例如:
输入: words = ["gin", "zen", "gig", "msg"]
输出: 2
解释:
各单词翻译如下:
"gin" -> "--...-."
"zen" -> "--...-."
"gig" -> "--...--."
"msg" -> "--...--."

共有 2 种不同翻译, "--...-." 和 "--...--.".*/
public class 摩尔斯密码_d {

    public static int translate_of_morse_code (String StringList[]){
        String Morse_code_reflective_list[] = {".-","-...","-.-.","-..",".","..-.","--.","....","..",".---","-.-",".-..","--","-.","---",".--.","--.-",".-.","...","-","..-","...-",".--","-..-","-.--","--.."};
        String alphabet_list[] = {"a","b","c","d","e","f","g","h","i","j","k","l","m","n","o","p","q","r","s","t","u","v","w","x","y","z"};
        int result =0;
        List<String> Morse_code_list = new ArrayList<>();

        for(String alphabet_words : StringList){
            String morse_code = "";
            for(int i = 0;i < alphabet_words.length();i++){
                for(int j = 0; j < alphabet_list.length; j++){
                    String temp = String.valueOf(alphabet_words.charAt(i));
                    if (alphabet_list[j].equals(temp)){
                        morse_code = morse_code + Morse_code_reflective_list[j];
                    }
                }
            }
            Morse_code_list.add(morse_code);

        }



        while (Morse_code_list.size()!= 0){
            String temp =Morse_code_list.remove(0);
            Morse_code_list.remove(temp);
            result++;
        }

        return result;
    }
    public static void main(String[] args) {
        String words[] = {"gin", "zen", "gig", "msg"};
        System.out.println(translate_of_morse_code(words));
    }

}
