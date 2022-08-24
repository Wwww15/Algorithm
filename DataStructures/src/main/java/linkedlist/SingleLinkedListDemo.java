package linkedlist;

/**
 * 数据结构：单链表
 * 1.
 */
public class SingleLinkedListDemo {

    public static void main(String[] args) {

    }


}
/**
 * 单链表
 */
class SingleLinkedList {
     
}

/**
 * 定义 HeroNode ， 每个 HeroNode 对象就是一个节点
 */
class HeroNode {
    public int no;
    public String name;
    public String nickname;
    //指向下一个节点
    public HeroNode next;
    // 构造器
    public HeroNode(int no, String name, String nickname) {
        this.no = no;
        this.name = name;
        this.nickname = nickname;
    }
    /**
     * 为了显示方法，我们重新 toString
     */
    @Override
    public String toString() {
        return "HeroNode [no=" + no + ", name=" + name + ", nickname=" + nickname + "]";
    }
}
