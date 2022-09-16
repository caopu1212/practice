package 题目;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class Main {
    public static Node node = null;

    //右旋
    //右旋指将左儿子提到根，将根向下移动到右儿子。左旋是右旋的相反操作。
    public static Node rightRotate(Node root) {
        Node newNode = root.left;

        root.left = newNode.right;
        newNode.right = root;
        return newNode;
    }

//    rightRotate(Node t)
//    Node s = t.left
//    t.left = s.right
//    s.right = t
//    return s // the new root of subtree

    //    leftRotate(Node t)
//    Node s = t.right
//    t.right = s.left
//    s.left = t
//    return s // the new root of subtree
    //左旋
    public static Node leftRotate(Node root) {
        Node newNode = root.right;
        root.right = newNode.left;
        newNode.left = root;
        return newNode;
    }

    //插入
    public static Node insert(Node root, int newValue, int newHeapValue) {
        // 若树为空(或达到了叶子结点的时候)
        if (root == null) {
            return new Node(newValue, newHeapValue);
        }
        //若有重复数据则无视
        if (newValue == root.value) {
            return root;
        }
        if (newValue < root.value) {
            root.left = insert(root.left, newValue, newHeapValue);
            if (root.heapValue < root.left.heapValue) {
                root = rightRotate(root);
//                return Copy(root,rightRotate(root));
            }

        } else {
            root.right = insert(root.right, newValue, newHeapValue);
            if (root.heapValue < root.right.heapValue) {
                root = leftRotate(root);
//                return Copy(root,leftRotate(root));
//                return root_;
            }
        }
        return root;
    }

    public static void inOrderTraverse(Node root) {
        if (root != null) {
            inOrderTraverse(root.left);
            System.out.print(root.value + " ");
            inOrderTraverse(root.right);
        }
    }

    public static void preOrderTraverse(Node root) {
        if (root != null) {
            System.out.print(root.value + " ");
            inOrderTraverse(root.left);
            inOrderTraverse(root.right);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String amountOfOrder = br.readLine();

        for (int i = 0; i < Integer.valueOf(amountOfOrder); i++) {
            String[] orderList = br.readLine().split(" ");
            if (orderList[0].equals("insert")) {
                node = insert(node, Integer.valueOf(orderList[1]), Integer.valueOf(orderList[2]));
            }
        }
        inOrderTraverse(node);
        System.out.println("");
        preOrderTraverse(node);
    }

}

class Node {
    public int value;
    public int heapValue;
    public Node left;
    public Node right;

    Node(int value, int heapValue) {
        this.value = value;
        this.heapValue = heapValue;
        this.left = null;
        this.right = null;
    }


}
//insert 35 99
//insert 3 80
//insert 1 53
//insert 14 25
//insert 80 76
//insert 42 3
//insert 86 47
//insert 21 12
//insert 7 10
//insert 6 90

//1 3 6 7 14 21 35 42 80 86
//        35 1 3 6 7 14 21 42 80 86