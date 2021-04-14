package 数据结构.linked_list;

import com.sun.xml.internal.ws.runtime.config.TubelineFeatureReader;

public class singly_linked_list {

    /**
     * 初始化头结点
     */
    Node head = null;

    /**
     * 节点
     */

    class Node {
        Node next = null;
        String data;

        public Node(String data) {
            this.data = data;
        }
    }

    /**
     * 插入
     * 尾插
     *
     * @param data
     */
    public void addNodeTail(String data) {
        Node newNode = new Node(data);

        if (head == null) {
            head = newNode;
            return;
        }
        Node temp = head;
        while (temp.next != null) {
            temp = temp.next;
        }
        temp.next = newNode;
    }

    /**
     * 插入
     * 头插(倒序)
     *
     * @param data
     */
    public void addNodeHead(String data) {
        Node newNode = new Node(data);
        if (head == null) {
            head = newNode;
            return;
        }
        Node temp = head;
        head = newNode;
        head.next = temp;
    }

    /**
     * 链表长度
     *
     * @param
     */
    public int length() {
        int result = 0;
        Node temp = head;
        while (temp.next != null) {
            result = result + 1;
            temp = temp.next;
        }
        return result;
    }

    /**
     * 删除
     * 通过序号
     *
     * @param index
     */
    public boolean deleteNode(int index) {
        if (index < 1 || index > length()) {
            return false;
        }
        if (index == 1) {
            head= head.next ;
            return true;
        }
        int count = 1;
        Node preNode = head;
        Node curNode = preNode.next;
        while (curNode!= null) {

            if (count == index) {
                preNode.next = curNode.next;
                return true;
            }
            preNode = curNode;
            curNode = preNode.next;

            count = count + 1;


        }
        return true;
    }

    /**
     * 打印链表数据
     *
     * @param
     */
    public void printList() {
        Node temp = head;
        System.out.println(temp.data);
        while (temp.next != null) {

            temp = temp.next;
            System.out.println(temp.data);
        }
    }


    public static void main(String[] args) {

        singly_linked_list testList = new singly_linked_list();
        testList.addNodeHead("头插的1");
        testList.addNodeHead("头插的2");
        testList.addNodeHead("头插的3");
        testList.addNodeTail("尾插的4");
        testList.addNodeTail("尾插的5");
        testList.addNodeTail("尾插的6");
//        testList.printList();
        testList.deleteNode(3);
        testList.printList();
    }


}


