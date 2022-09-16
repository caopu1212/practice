# 初始化树
def initial_tree(arr, tree, father_node, start, end):
    if (start == end):
        tree[father_node] = arr[start]
    else:
        mid = int((start + end) / 2)
        left_node = 2 * father_node + 1
        right_node = 2 * father_node + 2

        initial_tree(arr, tree, left_node, start, mid)
        initial_tree(arr, tree, right_node, mid + 1, end)

        tree[father_node] = tree[left_node] + tree[right_node]

        return tree

# 更新
def updata(arr, tree, node, start, end, replece, replece_index):
    mid = (start + end) / 2
    left_node = 2 * node + 1
    right_node = 2 * node + 2

    # if ()

# 查询
# def find():
if __name__ == '__main__':
    arr = [1, 3, 5, 7, 9, 11]
    tree_size = 1
    while (True):
        tree_size = tree_size * 2
        if tree_size >= len(arr):
            break
    tree = [None] * (tree_size * 2 - 1)


    print(initial_tree(arr, tree, 0, 0, len(arr) - 1))

    print()
