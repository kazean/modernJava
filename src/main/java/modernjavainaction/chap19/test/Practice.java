package modernjavainaction.chap19.test;

import java.util.ArrayList;
import java.util.List;

public class Practice {
    public static void main(String[] args) {
        List<Integer> list1 = new ArrayList<>();
        List<Integer> list2 = new ArrayList<>();
        list1.add(1);
        list1.add(2);
        list1.add(3);

        List<Integer> tmp = list1;
        list2.addAll(tmp);
        list1.remove(1);
        list1.add(4);

        /*list2.stream()
                .forEach(i-> System.out.println(i));*/

        Tree a = new Tree(5, 5);
        Tree aLeft = new Tree(3, 3);
        aLeft.setLeft(new Tree(1, 1));
        aLeft.setRight(new Tree(4, 4));
        a.setLeft(aLeft);
        
        Tree b = a;
//        b.getLeft().getLeft().val = 2;
//        System.out.println(a.getLeft().getLeft().val); //2
        Tree c = new Tree(5, 5);
        c.setLeft(a.getLeft());
        c.setRight(a.getRight());
        System.out.println(c.getLeft().getLeft().val);
        a.getLeft().getLeft().val = 2;
        System.out.println(c.getLeft().getLeft().val);
    }
}

class Tree{
    int key;
    int val;
    Tree left;
    Tree right;

    public Tree(int key, int val) {
        this.key = key;
        this.val = val;
    }

    public Tree getLeft() {
        return left;
    }

    public void setLeft(Tree left) {
        this.left = left;
    }

    public Tree getRight() {
        return right;
    }

    public void setRight(Tree right) {
        this.right = right;
    }
}
