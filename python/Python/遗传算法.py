# -*- coding:utf-8 -*-
import numpy as np
import random

# 题目： 走五个地方的最短路径
# 答案应该为 [0,1,2,3,8]或[8,3,2,1,0]

places = ([1, 1], [2, 2], [3, 3], [4, 4], [5, 100],
          [124, 768], [56, 78], [77, 88], [4, 7], [65, 47], [45, 23],
          [15, 678], [35, 24], [25, 67], [231, 952], [48, 20])  # 16个地点

species = []

fitness_highest = 0
fitness_lowest = 0
rounds = 0

total_fitness_list = []

new_generation_species = []


# 计算两点距离
def calculateDistance(place_A, place_B):
    return ((places[place_A][0] - places[place_B][0]) ** 2 +
            (places[place_A][1] - places[place_B][1]) ** 2) ** 0.5


# 计算一条染色体走的路径
# 入参like: [3,4,6,9,7]
def pastFivePlace(aim_list):
    total_distance = 0
    for index in range(0, len(aim_list)):
        if index == 4:
            break
        # print(index)
        total_distance = calculateDistance(
            aim_list[index], aim_list[index + 1]) + total_distance
        # print("距离为：", total_distance)

    return total_distance


# 随机生成一条新的编码后的染色体
def get_new_individual():
    DNA_befor_encode = get_five_place()
    DNA_after_encode = ""
    # print("随机五个数为： ", DNA_befor_encode)
    index = 0
    for index in range(0, 5):
        # print("index: ", index)
        # print("编码之前：", DNA_befor_encode[index])

        DNA_after_encode = DNA_after_encode + decimal_to_binary(
            DNA_befor_encode[index])
        # print(DNA_after_encode)
    return DNA_after_encode


# 初始化物种
def initialize_species(cycle):
    count = 0
    while count < cycle:
        species.append(get_new_individual())
        count = count + 1


# 获得5个随机随机数 0—15之间
def get_five_place():
    DNA_befor_encode = random.sample(range(0, 15), 5)
    return DNA_befor_encode


# 二转十 入str 出int
def binary_to_decimal(binary_num):
    return int(str(binary_num), 2)


# 十转二 入参int， return String
def decimal_to_binary(decimal_num):
    return bin(decimal_num).replace("0b", "").zfill(4)  # 高位补零到4位


# dna解码为十进制list
def decode(DNA_after_encode):
    temp_list = []
    count = 0
    for index1 in range(0, 5):
        temp_str = ""
        for index2 in range(0, 4):
            temp_str = temp_str + DNA_after_encode[count]
            count = count + 1
        # print("binary is: ", temp_str)
        temp_list.append(binary_to_decimal(temp_str))
    # print("解码结果为：", temp_list)
    return temp_list


# 计算适合度 反比例函数 y= kx**-1， x越小y越大
def calculate_fitness(places_list):
    fitness = (pastFivePlace(places_list)) ** -1 * (10 ** 3)
    # print("适应度: ", fitness)
    return fitness


# 每一代初始化后的一些处理:获得最高最低适合度，将最高最低放到相应位置
def generation_process():
    for individual_DNA in species:
        index_of_individual = species.index(individual_DNA)
        # 如果有路径重复，那么生成随机染色体代替
        if validator_of_repeated_element(individual_DNA):
            species.pop(index_of_individual)
            species.insert(index_of_individual,
                           (get_new_individual()))

        DNA_after_decode = decode(individual_DNA)  # 解码
        fitness_target = calculate_fitness(DNA_after_decode)
        # print(species[0])
        fitness_highest = calculate_fitness(
            decode(species[0]))  # list第一个为适合度最高的个体

        fitness_lowest = calculate_fitness(
            decode(species[len(species) - 1]))  # 最后一个为最低

        # 计算每一个的适合度，如果大于最高的话，将这个放到species[0]
        if fitness_target > fitness_highest:
            pop_individual = species.pop(index_of_individual)
            species.insert(0, pop_individual)

        # 如果比最低小的话放到最后
        if fitness_target < fitness_lowest:
            pop_individual = species.pop(index_of_individual)
            species.insert(len(species), pop_individual)

        total_fitness_list.append(fitness_target)


# 轮盘赌选择 每次选两个，然后对比，适应度高的进入下一代
# 轮盘赌：每个个体进入下一代的概率等于它的适应度值与整个种群中个体适应度值和的比例。
def selection():
    _total_fitness_list = np.array(total_fitness_list)
    total_fitness = np.sum(total_fitness_list)
    i = 0
    individual_probability = []  # 个体各自的概率（个体适合度/总适合度）
    cumulative_probability = []  # 累计概率
    cumulative_count = 0
    for i in range(len(_total_fitness_list)):
        individual_probability.append(_total_fitness_list[i] / total_fitness)
        cumulative_count = individual_probability[i] + cumulative_count
        cumulative_probability.append(cumulative_count)

    while 1:
        # 随机选两个对比，fitness大的加到new_generation_species里
        competitor = []
        random_rate = np.random.random(2)
        # print(random_rate)
        count = 0
        while count < len(cumulative_probability):
            if not competitor:
                if random_rate[0] < cumulative_probability[count]:
                    competitor.append(species[count])
                    count = 0
                count = count + 1
            else:
                if random_rate[1] < cumulative_probability[count]:
                    competitor.append(species[count])
                    break
                count = count + 1

        # 随机选出两个，然后对比，fitness大的进入下一代
        if competitor[0] >= competitor[1]:
            new_generation_species.append(competitor[0])
        else:
            new_generation_species.append(competitor[1])
        if len(new_generation_species) >= len(species) * 0.8:  # 直到新世代达到原个体数的0.8倍
            break

    # print(individual_probability)
    # print(cumulative_probability)


# 验证是否有重复元素


def validator_of_repeated_element(input):
    Offspring_A_valid = decode(input)
    set_lst = set(Offspring_A_valid)
    if len(set_lst) != len(Offspring_A_valid):  # 有重复的元素
        Offspring_A_valid = get_new_individual()
        return True
    else:
        return False


# 替换字符串字符
def replace_string(string, index, replace):
    string2 = ''
    for i in range(len(string)):
        if i == index:
            string2 += replace
    else:
        string2 += string[i]
    return string2


# 变异(在交叉的时候，有rate ％的概率突变，随机位置的基因变为相反的 )
def mutation(target_DNA, rate):
    mutation_rate = random.randint(0,100)
    mutation_position = random.randint(0,15)
    if mutation_rate <= rate:
        count = 0
        while count < len(target_DNA):
            if count == mutation_position:
                if target_DNA[count] == "1":
                    replace_string(target_DNA, count, "0")
                else:
                    replace_string(target_DNA, count, "1")
            count = count + 1

    return target_DNA


# 杂交
def crossover():
    global new_generation_species

    # 杂交
    best_individual_of_last_generation = species[0]
    while 1:
        # 如果新世代个体数大于等于上一世代则停止
        if len(new_generation_species) >= len(species):
            break
        # 将上一世代最强的染色体复制填满新世代
        new_generation_species.append(best_individual_of_last_generation)

    Offspring_list = []
    odd_DNA_list = new_generation_species[::2]  # 奇数
    even_DNA_list = new_generation_species[1::2]  # 偶数
    count = 0
    while 1:
        if count >= len(odd_DNA_list) - 1:
            break
        # 奇数排序的个体
        temp_left_DNA_odd = odd_DNA_list[count][12:20]
        temp_right_DNA_odd = odd_DNA_list[count][:12]
        # 偶数排序的个体
        temp_left_DNA_even = even_DNA_list[count][12:20]
        temp_right_DNA_even = even_DNA_list[count][:12]

        # 两个后代
        Offspring_A = temp_left_DNA_odd + temp_right_DNA_even
        Offspring_B = temp_left_DNA_even + temp_right_DNA_odd

        # 突变(控制概率突变，随机改变其中一位二级制数字）
        Offspring_A = mutation(Offspring_A, 10)
        Offspring_B = mutation(Offspring_B, 10)

        # 验证是否有重复的元素（经过地点），如果有的话随机生成一条新的染色体
        if validator_of_repeated_element(Offspring_A):
            Offspring_list.append(get_new_individual())
        else:
            Offspring_list.append(Offspring_A)
        if validator_of_repeated_element(Offspring_A):
            Offspring_list.append(get_new_individual())
        else:
            Offspring_list.append(Offspring_B)

        count = count + 1

    while 1:
        # 如果新世代个体数大于等于上一世代则停止
        if len(Offspring_list) >= len(species):
            break
        # 将上一世代最强的染色体复制填满新世代
        if validator_of_repeated_element(best_individual_of_last_generation):
            Offspring_list.append(get_new_individual())
        else:
            Offspring_list.append(best_individual_of_last_generation)

    new_generation_species = Offspring_list


# 初始化
def initialization():
    global species
    global new_generation_species
    species = new_generation_species
    new_generation_species = []  # 每一轮都要在最后清空下一代（将处理写回species）
    global total_fitness_list
    total_fitness_list = []
    generation_process()  # 每一代初始化后的一些处理

    # if (temp_best_fitness == 176.77669529663686):
    #     return True


if __name__ == '__main__':
    # 生成种群（多个个体）(每条染色体（个体）为一个解法，dna解码后为 从0-15日中的5个不重复的的数字)
    # 编码（直接二进制对应，（0000-1111)）
    # 解码（直接二进制对应）
    # 选择
    # 杂交（）
    # 突变（）
    # 适应度（路径长度，越短 适应度越高）

    initialize_species(100)  # 初始设定个体个数
    rounds = 2000  # 进行轮数
    count = 0
    generation_process()  # 每一代初始化后的一些处理
    while count < rounds:
        selection()  # 选择，优秀的进入下一代
        crossover()  # 杂交，复制
        # print(new_generation_species)
        initialization()  # 初始化(在最后)
        # if(initialization()):
        #     break          
        count = count + 1
        temp_best_fitness = calculate_fitness(decode(species[0]))
        print("best case: ",decode(species[0]))
        print("fitness of best case is :", temp_best_fitness)
        print("current generation is : ", count)
    #
    print("the final result: ", calculate_fitness(decode(species[0])))
    print("the final result: ", decode(species[0]))


    '''
    # 替换最菜的两个
    species[len(species)-1] = Offspring_A
    species[len(species)-2] = Offspring_B

    temp_fitness = calculate_fitness(
        decode(species[0]))

    if temp_fitness not in temp_fitness_list:
        temp_fitness_list.append(temp_fitness)
    '''
