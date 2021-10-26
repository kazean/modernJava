package modernjavainaction.chap19.test;

import java.util.*;
import java.util.stream.Stream;

public class Practice {
    public static void main(String[] args) {

        List<Integer> list =new ArrayList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        list.forEach(i-> System.out.print(i+" "));
        System.out.println();
        boolean flag = list.removeIf(i->i%2==0);
        list.forEach(i-> System.out.print(i+" "));
        System.out.println();

        List<Integer> numbers =Arrays.asList(1,2,3,4,5);

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
