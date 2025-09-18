package com.example.algo1;

import java.io.FileInputStream;
import java.io.IOException;

public class BitInputStream implements AutoCloseable {
    private FileInputStream in;
    private int currentByte;
    private int numBitsRemaining;

    public BitInputStream(String filePath) throws IOException {
        in = new FileInputStream(filePath);
        numBitsRemaining = 0;
    }

    public int readBit() throws IOException {
        if (numBitsRemaining == 0) {
            currentByte = in.read();
            if (currentByte == -1) return -1;
            numBitsRemaining = 8;
        }
        numBitsRemaining--;
        return (currentByte >> numBitsRemaining) & 1;
    }

    public void close() throws IOException {
        in.close();
    }
}
