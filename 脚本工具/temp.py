import re
import codecs


# 读取原文件然后清空
def read_lines(address):
    with codecs.open(address, "r+", encoding="utf-8") as file:
        lines = file.readlines()
        file.truncate(0)
        return lines


# 格式化
def formate(address):
    temp_list = []
    lines = read_lines(address)
    with codecs.open(address, "a+", encoding="utf-8") as file:
        for line in lines:
            if ("○○○" in line):
                text = line[10:-2]
                new_line = "m 048 "+text +"\n"
                temp_list.append(new_line)
        for i in temp_list:
            file.write(i + "\n")


if __name__ == '__main__':
    address = "G:/汉化/R2/cook-R2.txt"
    formate(address)
