package com.example.algo1;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;
import java.io.File;
import java.io.IOException;

public class HelloApplication extends Application {

    HuffmanCoding huffmanCoding = new HuffmanCoding();

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Huffman Coding");

        Button compressButton = new Button("Compress");
        compressButton.setOnAction(e -> openNewWindow("Select File to Compress", true));

        Button decompressButton = new Button("Decompress");
        decompressButton.setOnAction(e -> openNewWindow("Select File to Decompress", false));

        VBox vbox = new VBox(compressButton, decompressButton);
        Scene scene = new Scene(vbox, 300, 200);

        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void openNewWindow(String title, boolean isCompress) {
        Stage newWindow = new Stage();
        newWindow.initModality(Modality.APPLICATION_MODAL);
        newWindow.setTitle(title);

        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle(title);
        Button openFileButton = new Button("Open File");

        // Create a TableView to display the ASCII, frequency, Huffman code, and length
        TableView<TableData> table = new TableView<>();
        TableColumn<TableData, Byte> asciiColumn = new TableColumn<>("ASCII");
        asciiColumn.setCellValueFactory(new PropertyValueFactory<>("ascii"));
        TableColumn<TableData, Integer> frequencyColumn = new TableColumn<>("Frequency");
        frequencyColumn.setCellValueFactory(new PropertyValueFactory<>("frequency"));
        TableColumn<TableData, String> huffmanColumn = new TableColumn<>("Huffman Code");
        huffmanColumn.setCellValueFactory(new PropertyValueFactory<>("huffmanCode"));
        TableColumn<TableData, Integer> lengthColumn = new TableColumn<>("Length");
        lengthColumn.setCellValueFactory(new PropertyValueFactory<>("length"));
        table.getColumns().addAll(asciiColumn, frequencyColumn, huffmanColumn, lengthColumn);

        openFileButton.setOnAction(e -> {
            File file = fileChooser.showOpenDialog(newWindow);
            if (file != null) {
                if (isCompress) {
                    try {
                        String inputFilePath = file.getAbsolutePath();
                        String outputFilePath = inputFilePath + ".huf";
                        MyHashMap frequencyMap = huffmanCoding.getFrequency(inputFilePath);
                        Node huffmanTree = huffmanCoding.createHuffmanTree(frequencyMap);
                        MyStringHashMap encodingTable = huffmanCoding.createEncodingTable(huffmanCoding.createHuffmanTree(frequencyMap));
                        huffmanCoding.encodeFile(inputFilePath, outputFilePath, encodingTable, huffmanTree);
                        System.out.println("File " + inputFilePath + " has been compressed.");

                        // Populate the TableView with the ASCII, frequency, Huffman code, and length
                        ObservableList<TableData> data = FXCollections.observableArrayList();
                        for (byte i = Byte.MIN_VALUE; i < Byte.MAX_VALUE; i++) {
                            String huffmanCode = encodingTable.get(i);
                            if (huffmanCode != null) {
                                data.add(new TableData(i, frequencyMap.get(i), huffmanCode, huffmanCode.length()));
                            }
                        }
                        table.setItems(data);
                    } catch (IOException ioException) {
                        ioException.printStackTrace();
                    }
                } else {
                    try {
                        String inputFilePath = file.getAbsolutePath();
                        System.out.println(inputFilePath);

                        String outputFilePath = inputFilePath.substring(0, inputFilePath.lastIndexOf('.')) + "_decompressed.txt";
                        System.out.println(outputFilePath);
                        System.out.println("11111111111111111111111111111111111");

                        huffmanCoding.decodeFile(inputFilePath, outputFilePath);

                        System.out.println("File " + inputFilePath + " has been decompressed.");
                    } catch (IOException ioException) {
                        ioException.printStackTrace();
                    }
                }
            }
        });

        Button backButton = new Button("Back");
        backButton.setOnAction(e -> newWindow.close());

        VBox vbox;
        if (isCompress) {
            vbox = new VBox(openFileButton, table, backButton);
        } else {
            vbox = new VBox(openFileButton, backButton);
        }
        Scene scene = new Scene(vbox, 300, 200);

        newWindow.setScene(scene);
        newWindow.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

    public static class TableData {
        private final byte ascii;
        private final int frequency;
        private final String huffmanCode;
        private final int length;

        public TableData(byte ascii, int frequency, String huffmanCode, int length) {
            this.ascii = ascii;
            this.frequency = frequency;
            this.huffmanCode = huffmanCode;
            this.length = length;
        }

        public byte getAscii() {
            return ascii;
        }

        public int getFrequency() {
            return frequency;
        }

        public String getHuffmanCode() {
            return huffmanCode;
        }

        public int getLength() {
            return length;
        }
    }
}