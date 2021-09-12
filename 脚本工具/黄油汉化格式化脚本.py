import re
import codecs


# 读取原文件然后清空
def read_lines(address):
    with codecs.open(address, "r+",encoding="utf-8") as file:
        lines = file.readlines()
        file.truncate(0)
        return lines

# 格式化
def formate(address):
    sequence = []
    lines = read_lines(address)
    with codecs.open(address, "a+",encoding="utf-8") as file:
        for line in lines:
            if ("●●●" in line):
                template = line[0:12]
                sequence.append(template)
                text = line[12:-2]
                new_line = text + " █" + "\n"
                file.write(new_line)
            else:
                file.write(line)
        for i in sequence:
            file.write(i + "\n")


# 还原
def recover(address):
    sequence = []
    with codecs.open(address, "r+",encoding="utf-8") as file:
        lines = file.readlines()
        file.truncate(0)

    with codecs.open(address, "a+",encoding="utf-8") as file:
        count = 0

        for line_index in reversed(range(0,len(lines))) : #倒序遍历,因为是根据index删除
            if "●●●" in lines[line_index]:
                template = lines[line_index][0:12]
                sequence.append(template)
                del lines[line_index]
        sequence = list(reversed(sequence))
        for line in lines:
            if "█" in line:
                new_line = sequence[count] + line[:-3]+"\n"
                count = count+1
                file.write(new_line)
            else:
                file.write(line)

if __name__ == '__main__':

    address = "G:/汉化/兰斯年代记2/文本/text_15.txt"

    # formate(address)  #格式化 ,翻译的时候句尾的█不要删
    recover(address) #还原
