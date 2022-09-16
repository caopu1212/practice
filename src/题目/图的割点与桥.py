# import numpy as np
from collections import defaultdict

input_ = input().split(" ")
V, E = int(input_[0]), int(input_[1])
# prenum = []
# lowest = []
# visited = []
# parent = []
# for i in range(V):
#     prenum.append(0)
#     lowest.append(0)
#     visited.append(0)
#     parent.append(0)
prenum, lowest, visited, parent = [0] * V, [0] * V, [0] * V, [0] * V
t = 1

import sys

sys.setrecursionlimit(10000)


# p: 上一个顶点
def dfs(u, p):
    global t
    prenum[u] = lowest[u] = t
    t += 1
    visited[u] = 1
    for index in range(len(inputGraph[u])):
        if (inputGraph[u][index] == 1):
            # 如果没有访问过
            if visited[index] == 0:
                parent[index] = u
                dfs(index, u)
                lowest[u] = min(lowest[u], lowest[index])
            elif index != p:
                lowest[u] = min(lowest[u], prenum[index])


def cut_point():
    dfs(0, -1)

    cut_points = []
    son_of_root = 0
    for u in range(1, V):
        p = parent[u]
        if p == 0:
            son_of_root += 1
        elif prenum[p] <= lowest[u]:
            if p not in cut_points:
                cut_points.append(p)
    if son_of_root > 1:
        cut_points.append(0)

    for i in sorted(cut_points):
        print(i)


def bridges():
    dfs(0, -1)
    bridges = []
    for u in range(1, V):
        if lowest[u] == prenum[u]:
            list_ = [parent[u], u]
            bridges.append(sorted(list_))
            # .sort()
    bridges.sort()
    sorted(bridges, key=(lambda x: [x[0]]))
    # , x[1]
    for i in bridges:
        print(i[0], i[1])


if __name__ == '__main__':
    # 邻接矩阵存
    intList = defaultdict(int)
    inputGraph = [[0 for i in range(V)] for j in range(V)]

    for i in range(E):
        input_ = input().split(" ")
        vector, edge = int(input_[0]), int(input_[1])
        inputGraph[vector][edge] = 1
        inputGraph[edge][vector] = 1
    # print(inputGraph)
    # cut_point()
    bridges()
