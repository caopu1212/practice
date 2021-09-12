import codecs
import collections
import re

old_map = {}

'''
从map找key中包含入参的项
return list
'''
JP_lines = []
CH_lines = []


# 重写原文件
def rewrite(address, processing_list):
    with codecs.open(address, "r+", encoding="utf-8") as file:
        file.truncate(0)

    with codecs.open(address, "a+", encoding="utf-8") as file:
        for line in processing_list:
            if "█" in line:
                line = line.replace("█", "")

            file.write(line + "\n")


def find_form_map(param):
    temp_list = []
    for key in old_maps.keys():
        if param in str(key):
            temp_list.append(key)

    return temp_list


def get_index_JP_next(str):
    for line in JP_lines:
        if str in line:
            index = JP_lines.index(line)
    print(str)
    next_line = JP_lines[index + 1]

    return next_line


def get_generated_map(old_JP, old_CH):
    result = {}
    result = collections.OrderedDict()

    with codecs.open(old_JP, "r", encoding="utf-8") as file:
        temp_JP_lines = file.readlines()
    with codecs.open(old_CH, "r", encoding="utf-8") as file:
        temp_CH_lines = file.readlines()
    for JP_line in temp_JP_lines:
        JP_line = repr(JP_line).replace("\t", "").replace("\r\n", "") \
            .replace("\\", "").replace("'", "") \
            .replace("t", "").replace("rn", "").replace("u3000", "").replace("「", "") \
            .replace("」", "")
        JP_lines.append(JP_line[6::])

    for CH_line in temp_CH_lines:
        CH_line = repr(CH_line).replace("\t", "").replace("\r\n", "") \
            .replace("\\", "").replace("'", "") \
            .replace("t", "").replace("rn", "").replace("u3000", "").replace("「", "") \
            .replace("」", "").replace(".", "。").replace(" ", "　").replace("~", "～")

        CH_lines.append(CH_line[6::])

    for i in range(0, len(JP_lines)):
        result[JP_lines[i]] = CH_lines[i]
        # print(result[JP_lines[i]])

    return result


def get_new_version_list(address):
    new_JP_list = []
    with codecs.open(address, "r", encoding="utf-8") as file:
        temp_new_JP_list = file.readlines()
    for line in temp_new_JP_list:
        # 去掉首尾的',然后存到list
        line = repr(line).replace("\t", "").replace("\r\n", "").replace("\\", "").replace("'", "").replace("t",
                                                                                                           "").replace(
            "rn", "").replace("u3000", "")
        line = re.sub("'", "", line)
        # line = re.sub('"',"",line)
        new_JP_list.append(line)
        # print(line)
    return new_JP_list


# # 去掉首尾的',然后存到list
# line = repr(line).replace("\t","").replace("\r\n","").replace("\\","").replace("'","").replace("t","").replace("rn","").replace("u3000","")
# line = eval(line).replace("'","")

def processing(address):
    processing_list = []  # 目标文本
    # 新版 全部读成list
    new_version_list = get_new_version_list(address)

    # 获得目标汉化文本
    for line in new_version_list:
        if ('"' in line):
            processing_list.append(line[1:-1])

    # 若有完全一样的行,先直接替换掉
    for line in processing_list:
        if old_maps.get(line):
            new_line = old_maps.get(line)
            # 用█标记,说明是已经处理过的文本
            processing_list[processing_list.index(line)] = new_line + "█"
    print(processing_list)
    # 重置结果行
    new_line = ""
    # 遍历剩下的行
    line_count = 0
    while (line_count != len(processing_list)):
        line = processing_list[line_count]  # 当前行
        # 若有「,」,█ 则跳出到下一次循环
        if "「" in line or "」" in line or "█" in line or "●" in line:
            line_count = line_count + 1
            continue

        # 这里的字符都是有断句的
        # 遍历每一个字符
        str_count = 0
        temp_str = ""  # 当前字符串
        while (str_count != len(line)):
            temp_str = temp_str + line[str_count]
            key_list = find_form_map(temp_str)  # key包含目标字符的map集合

            # 若没有包含目标字符的map的话并且不是最后一句的话,就说明老版已经断句了
            if len(key_list) == 0 and line_count != len(processing_list):
                # if (old_maps.get(temp_str[:-1]) != []):

                print(temp_str)
                # 将对应key的翻译加到new_line里.取到-1 因为要回滚一位
                new_line = new_line + old_maps.get(temp_str[:-1])

                # 重置,去老版找新的句子
                temp_str = ""
                str_count = str_count - 1

            # 如果到了最后一个字
            if str_count == len(line) - 1 and len(key_list) == 1:
                # 但不是老版的结尾
                if key_list[0].endswith(temp_str) == False:
                    # 将剩余部分加到下一行,然后重新跑这一行

                    next_line = get_index_JP_next(temp_str)  # 获得老版的下一行
                    temp_line = line
                    temp_str2 = ""
                    # 遍历当前行,直到出现temp_str为止
                    for str in temp_line:
                        if temp_str in temp_str2:
                            break
                        temp_str2 = temp_str2 + str

                    # 将当前行多余字符去掉
                    old_maps[line[line.index(temp_str):]] = old_maps.pop(key_list[0])

                    # 只留下要移到下一行的字符
                    temp_line.replace(temp_str2, "")

                    # 将多多余字符加到下一行开头
                    left_str = key_list[0].replace(line[line.index(temp_str):], "")  # 多余字符
                    print(temp_str)
                    old_maps[left_str + next_line] = old_maps.pop(next_line)
                    # 重新跑当前行
                    line_count = line_count - 1

                print(temp_str)

                # 将对应key的翻译加到new_line里
                new_line = new_line + old_maps.get(temp_str)
                # 重置
                temp_str = ""

            str_count += 1

        # 将翻译写回原list
        processing_list[processing_list.index(line)] = new_line + "█"
        print(new_line)
        new_line = ""

        line_count += 1

    print(processing_list)

    # 写回去
    temp_count = 0
    for index in range(0, len(new_version_list)):

        # 处理前缀缺等号
        if re.search('VAR\d*  \d', new_version_list[index]):
            new_version_list[new_version_list.index(new_version_list[index])] = new_version_list[index].replace("  ",
                                                                                                                " = ")
        # 加回''
        if '"' in new_version_list[index]:
            new_version_list[index] = "'" + processing_list[temp_count] + "'"
            temp_count = temp_count + 1

    rewrite(new_JP, new_version_list)

    print(processing_list)


if __name__ == '__main__':
    old_JP = "G:/汉化/R2/ランス０２_jp_text_U8.txt"
    old_CH = "G:/汉化/R2/ランス０２_zh_text_U8.txt"
    new_JP = "G:/汉化/R2/待处理/STAGE6A.txt"

    # 老版对应汉化的map
    old_maps = get_generated_map(old_JP, old_CH)
    print(old_maps)
    print(old_maps.get("ｗｗｗｗ」"))
    processing(new_JP)
