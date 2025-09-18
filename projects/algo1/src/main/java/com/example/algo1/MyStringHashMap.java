package com.example.algo1;

public class MyStringHashMap {
    private class Node {
        byte key;
        String value;
        Node next;

        Node(byte key, String value) {
            this.key = key;
            this.value = value;
        }
    }

    private Node[] table;

    public MyStringHashMap() {
        table = new Node[256]; //   the size is 256
    }

    public void put(byte key, String value) {
        int index = key & 0xFF; // Convert the byte to an index
        Node node = table[index];
        if (node == null) {
            table[index] = new Node(key, value);
        } else {
            while (node.next != null && node.key != key) {
                node = node.next;
            }
            if (node.key == key) {
                node.value = value;
            } else {
                node.next = new Node(key, value);
            }
        }
    }

    public String get(byte key) {
        int index = key & 0xFF;
        Node node = table[index];
        while (node != null && node.key != key) {
            node = node.next;
        }
        return node != null ? node.value : null;
    }

    public String getOrDefault(byte key, String defaultValue) {
        int index = key & 0xFF;
        Node node = table[index];
        while (node != null && node.key != key) {
            node = node.next;
        }
        return node != null ? node.value : defaultValue;
    }

    public int size() {
        int size = 0;
        for (int i = 0; i < table.length; i++) {
            Node node = table[i];
            while (node != null) {
                size++;
                node = node.next;
            }
        }
        return size;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < table.length; i++) {
            Node node = table[i];
            while (node != null) {
                sb.append(node.key).append(": ").append(node.value).append(", ");
                node = node.next;
            }
        }
        return sb.toString();
    }
}
