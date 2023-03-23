package ru.itis.algorithms_201_1.sergeev;
import java.util.ArrayList;

public class Tree {
    public Tree left;
    public Tree right;
    public int key;
    public int operation = 0;
    public Tree(int key) {
        this.key = key;
    }
    //метод вставки элементов
    public int insert(Tree tree){
        operation++;
        if (tree.key < this.key) {
            if (left != null) {
                left.insert(tree);
            }
            else{
                left = tree;
            }
        }
        else {
            if (right != null) {
                right.insert(tree);
            }
            else{
                right = tree;
            }
        }
        return operation;
    }
    public void traverse(){
        if (this.left != null) left.traverse();
        visit(this);
        if (this.right != null) right.traverse();
    }
    public void visit(Tree tree) {
        System.out.println(tree.key);
    }
    //методы для тестов
    public void traverse(ArrayList<Integer> integers) {
        if (this.left != null) left.traverse(integers);
        integers.add(this.key);
        if (this.right != null) right.traverse(integers);
    }
}
