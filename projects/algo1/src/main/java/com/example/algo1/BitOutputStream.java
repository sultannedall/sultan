package com.example.algo1;

import java.io.FileOutputStream;
import java.io.IOException;

public class BitOutputStream implements AutoCloseable  {
    private FileOutputStream out;
    private int currentByte;
    private int numBitsFilled;

    public BitOutputStream(String filePath) throws IOException {
        out = new FileOutputStream(filePath);
        currentByte = 0;
        numBitsFilled = 0;
    }

    public void writeBit(int bit) throws IOException {
        currentByte = (currentByte << 1) | bit;
        numBitsFilled++;
        if (numBitsFilled == 8) {
            out.write(currentByte);
            currentByte = 0;
            numBitsFilled = 0;
        }
    }

    public void writeByte(byte b) throws IOException {
        for (int i = 7; i >= 0; i--) {
            writeBit((b >> i) & 1);
        }
    }

    public void close() throws IOException {
        if (numBitsFilled > 0) {
            currentByte <<= (8 - numBitsFilled);
            out.write(currentByte);
        }
//        out.close();
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
        if (bit == 1) {
            int value = 0;
            for (int i = 0; i < 8; i++) {
                int nextBit = in.readBit();
                if (nextBit == -1) throw new IOException("Unexpected EOF while reading character");
                value = (value << 1) | nextBit;
            }
            return new Node((byte) value, 0);
        } else if (bit == 0) {
            Node left = readTree(in);
            Node right = readTree(in);
            return new Node(left, right);
        } else {
            throw new IOException("Unexpected EOF while reading tree");
        }
    }

}
