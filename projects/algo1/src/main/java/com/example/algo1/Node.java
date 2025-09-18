package com.example.algo1;

public class Node implements Comparable<Node> {
    byte character;
    int frequency;
    Node left;
    Node right;

    Node(byte character, int frequency) {
        this.character = character;
        this.frequency = frequency;
    }

    Node(Node left, Node right) {
        this.left = left;
        this.right = right;
        this.frequency = left.frequency + right.frequency;
    }

    boolean isLeaf() {
        return this.left == null && this.right == null;
    }

    @Override
    public int compareTo(Node other) {
        return this.frequency - other.frequency;
    }

    @Override
    public String toString() {
        return "Node{" +
                "character=" + character +
                ", frequency=" + frequency +
                '}';
    }
}
