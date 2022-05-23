package hashtab;

import java.util.Objects;
import java.util.Scanner;

/**
 * 数据结构：hash表（散列表）
 * 1.散列表结构
 *  1）数组+链表
 *  2）数组+二叉树
 */
public class HashTabDemo {

    public static void main(String[] args) {

        HashTab hashTab = new HashTab(15);
        Scanner scanner = new Scanner(System.in);
        while(true) {
            System.out.println("add表示加入雇员");
            System.out.println("delete表示删除雇员");
            System.out.println("list表示遍历雇员");
            System.out.println("find表示查找雇员");
            String next = scanner.next();
            switch (next) {
                case "add":
                    System.out.println("请输入id：");
                    int addId = scanner.nextInt();
                    System.out.println("请输入姓名：");
                    String name = scanner.next();
                    hashTab.add(new Emp(addId,name));
                    break;
                case "delete":
                    System.out.println("请输入id：");
                    int delId = scanner.nextInt();
                    hashTab.delete(delId);
                    break;
                case "list":
                    hashTab.list();
                    break;
                case "find":
                    System.out.println("请输入id：");
                    int findId = scanner.nextInt();
                    Emp emp = hashTab.find(findId);
                    System.out.println("当前找到的对象为："+emp);
                    break;
                case "exit":
                    scanner.close();
                    System.exit(0);
                    break;
                default:
                    break;
            }
        }
    }

}

/**
 * 简单的hash表
 * 1.数组+链表
 */
class  HashTab {

    int maxSize;

    EmpLinkedList[]  empLinkedList;

    public HashTab(int size) {
        this.maxSize = size;
        empLinkedList = new EmpLinkedList[this.maxSize];
        //这里要生成对应的EmpLinkedList
        for (int i = 0; i < empLinkedList.length; i++) {
            empLinkedList[i] = new EmpLinkedList();
        }
    }

    /**
     * 计算得到当前的EmpLinkedList
     */
    public EmpLinkedList getEmpList(int id) {
        int index = id % maxSize;
        return  empLinkedList[index];
    }

    /**
     * 添加雇员
     */
    public void add(Emp emp) {
        EmpLinkedList empList = getEmpList(emp.id);
        empList.add(emp);
    }

    /**
     * 遍历雇员
     */
    public void list() {
        for (int i = 0; i < empLinkedList.length ; i++) {
            empLinkedList[i].list(i);
        }
    }

    /**
     * 删除雇员
     */
    public void delete(int id) {
        EmpLinkedList empList = getEmpList(id);
        empList.delete(id);
    }

    /**
     * 查找雇员
     */
    public Emp find(int id) {
        EmpLinkedList empList = getEmpList(id);
        return empList.find(id);
    }
}
/**
 * 链表
 */
class  EmpLinkedList {

    Emp head;

    /**
     * 添加雇员
     */
    public void add(Emp emp) {
        if(Objects.isNull(head)) {
            head = emp;
            return;
        }
        //默认加入到最后
        Emp curreEmp = head;
        while(!Objects.isNull(curreEmp.next)) {
            curreEmp = curreEmp.next;
        }
        //退出表示找到最后了
        curreEmp.next = emp;
    }

    /**
     * 查找
     */
    public Emp find(int id) {
        Emp curreEmp = head;
        while(!Objects.isNull(curreEmp)) {
            if(curreEmp.id == id) {
                break;
            }
            curreEmp = curreEmp.next;
        }
       return curreEmp;
    }

    /**
     * 删除
     */
    public void delete(int id) {
        if(Objects.isNull(head)) {
            return;
        }
        //首先判断是不是头部
        if(head.id==id) {
            head = head.next;
            return;
        }
        Emp curreEmp = head;
        while(!Objects.isNull(curreEmp.next)) {
            if(curreEmp.next.id == id) {
                curreEmp.next = curreEmp.next.next;
                return;
            }
            curreEmp = curreEmp.next;
        }
    }

    /**
     * 遍历
     */
    public void list(int i) {
        Emp curreEmp = head;
        System.out.print("当前"+i+"链表的数据为：");
        while(!Objects.isNull(curreEmp)) {
            System.out.printf("{id:%d,name:%s}",curreEmp.id,curreEmp.name);
            curreEmp = curreEmp.next;
        }
        System.out.println();
    }

}

/**
 * 链表里的对象
 */
class Emp  {

    /**
     * 雇员id
     */
    int id;
    /**
     * 雇员名称
     */
    String name;
    /**
     * 下一个雇员
     */
    Emp next;

    public Emp(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Emp{" +
                "id=" + id +
                ", name=" + name +
                '}';
    }
}
