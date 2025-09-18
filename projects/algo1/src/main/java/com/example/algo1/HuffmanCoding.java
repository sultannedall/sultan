package com.example.algo1;

import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.PriorityQueue;

public class HuffmanCoding {

    public MyHashMap getFrequency(String filePath) throws IOException {
        MyHashMap frequencyMap = new MyHashMap();
        try (FileInputStream fileInputStream = new FileInputStream(filePath)) {
            byte[] bytes = fileInputStream.readAllBytes();   // read the file
            for (byte b : bytes) {
                frequencyMap.put(b, frequencyMap.getOrDefault(b, 0) + 1);   // count each byte
            }
        }
        return frequencyMap;
    }

    public Node createHuffmanTree(MyHashMap frequencyMap) {
        PriorityQueue<Node> queue = new PriorityQueue<>();

        for (byte i = Byte.MIN_VALUE; i < Byte.MAX_VALUE; i++) {
            int frequency = frequencyMap.get(i);
            if (frequency > 0) {
                queue.add(new Node(i, frequency));
            }
        }

        while (queue.size() > 1) {     // smallest 2 freq
            Node left = queue.poll();
            Node right = queue.poll();
            queue.add(new Node(left, right));
        }

        return queue.poll();
    }

    public MyStringHashMap createEncodingTable(Node root) {
        MyStringHashMap encodingTable = new MyStringHashMap();
        createEncodingTableHelper(root, "", encodingTable);
        return encodingTable;
    }

    private void createEncodingTableHelper(Node node, String path, MyStringHashMap encodingTable) {
        if (node.isLeaf()) {
            encodingTable.put(node.character, path);
        } else {
            if (node.left != null) {
                createEncodingTableHelper(node.left, path + "0", encodingTable);
            }
            if (node.right != null) {
                createEncodingTableHelper(node.right, path + "1", encodingTable);
            }
        }
    }

    public void encodeFile(String inputFilePath, String outputFilePath, MyStringHashMap encodingTable, Node huffmanTree) throws IOException {
        try (FileInputStream input = new FileInputStream(inputFilePath);
             BitOutputStream out = new BitOutputStream(outputFilePath)) {

             writeTree(huffmanTree, out);      // in the output file

            // Read input bytes and encode using Huffman table
            byte[] bytes = input.readAllBytes();
            for (byte b : bytes) {
                String code = encodingTable.get(b);
                for (char bit : code.toCharArray()) {
                    out.writeBit(bit == '1' ? 1 : 0);
                }
            }

            out.close();
        }
    }

    public void decodeFile(String inputFilePath, String outputFilePath) throws IOException {
        try (BitInputStream in = new BitInputStream(inputFilePath);
             BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(outputFilePath))) {

            // Reconstruct tree from the bit stream
            Node huffmanTree = readTree(in);
            Node current = huffmanTree;

            int bit;
            while ((bit = in.readBit()) != -1) {
                current = (bit == 0) ? current.left : current.right;
                if (current.isLeaf()) {
                    out.write(current.character);
                    current = huffmanTree;
                }
            }
        }
    }

    public void writeTree(Node node, BitOutputStream out) throws IOException {
        if (node.isLeaf()) {
            out.writeBit(1);
            out.writeByte(node.character);
        } else {
            out.writeBit(0);
            writeTree(node.left, out);
            writeTree(node.right, out);
        }
    }

    public Node readTree(BitInputStream in) throws IOException {
        int bit = in.readBit();
        if (bit == -1) {
            throw new IOException("Unexpected end of file while reading Huffman tree.");
        }

        if (bit == 1) {
            int value = 0;
            for (int i = 0; i < 8; i++) {
                int nextBit = in.readBit();
                if (nextBit == -1) throw new IOException("Unexpected EOF while reading byte");
                value = (value << 1) | nextBit;
            }
            return new Node((byte) value, 0);
        } else {
            Node left = readTree(in);
            Node right = readTree(in);
            return new Node(left, right);
        }
    }

    public boolean verifyFiles(String originalFilePath, String decodedFilePath) throws IOException {
        byte[] original = java.nio.file.Files.readAllBytes(java.nio.file.Paths.get(originalFilePath));
        byte[] decoded = java.nio.file.Files.readAllBytes(java.nio.file.Paths.get(decodedFilePath));

        if (original.length != decoded.length) return false;
        for (int i = 0; i < original.length; i++) {
            if (original[i] != decoded[i]) return false;
        }
        return true;
    }

    public int calculateHuffmanTreeSize(Node root) {
        if (root == null) return 0;
        return 1 + calculateHuffmanTreeSize(root.left) + calculateHuffmanTreeSize(root.right);
    }
}
