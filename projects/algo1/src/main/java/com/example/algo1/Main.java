package com.example.algo1;

import javafx.stage.FileChooser;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;


public class Main {
    public static void main(String[] args) throws IOException {
        HuffmanCoding huffmanCoding = new HuffmanCoding();

        String inputFilePath = "src/main/java/com/algo1/test.txt";
        String encodedFilePath = "src/main/java/com/algo1/encoded.huf";
        String decodedFilePath = "src/main/java/com/algo1/decoded.txt";

        MyHashMap frequencyMap = huffmanCoding.getFrequency(inputFilePath);
        System.out.println("Frequency Map: " + frequencyMap);

        Node huffmanTree = huffmanCoding.createHuffmanTree(frequencyMap);
        System.out.println("Huffman Tree: " + huffmanTree);
        MyStringHashMap encodingTable = huffmanCoding.createEncodingTable(huffmanTree);
        System.out.println("Encoding Table: " + encodingTable);

        huffmanCoding.encodeFile(inputFilePath, encodedFilePath, encodingTable, huffmanTree);
        String encodedString = new String(Files.readAllBytes(Paths.get(encodedFilePath)));
        System.out.println("Encoded String: " + encodedString);

        huffmanCoding.decodeFile(encodedFilePath, decodedFilePath);
        String decodedString = new String(Files.readAllBytes(Paths.get(decodedFilePath)));
        System.out.println("Decoded String: " + decodedString);

        boolean isIdentical = huffmanCoding.verifyFiles(inputFilePath, decodedFilePath);
        System.out.println("Are the original and decoded files identical? " + isIdentical);

        long inputFileSize = Files.size(Paths.get(inputFilePath));
        long encodedFileSize = Files.size(Paths.get(encodedFilePath));
        long decodedFileSize = Files.size(Paths.get(decodedFilePath));
        double compressionRate = (double)(inputFileSize - encodedFileSize) / inputFileSize;

        int huffmanTreeSize = huffmanCoding.calculateHuffmanTreeSize(huffmanTree);
        System.out.println("Size of the Huffman Tree (Header Size): " + huffmanTreeSize + " nodes");

        System.out.println("Size of the input file: " + inputFileSize + " bytes");
        System.out.println("Size of the encoded file: " + encodedFileSize + " bytes");
        System.out.println("Size of the decoded file: " + decodedFileSize + " bytes");
        System.out.println("Compression Rate: " + compressionRate);

    }
}