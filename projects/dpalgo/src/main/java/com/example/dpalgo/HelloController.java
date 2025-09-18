package com.example.dpalgo;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.*;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.stage.FileChooser;

public class HelloController implements Initializable {
    @FXML private TextArea alternTableTA;
    @FXML private Button browsebtn;
    @FXML private Label costlbl;
    @FXML private TextArea dpTableTA;
    @FXML private TextField filename;
    @FXML private TextArea pathTableTA;
    @FXML private Label pathlbl;
    @FXML private Button travelbtn;
    @FXML private Label pathl;
    @FXML private Label costl;
    @FXML private Label lbl1, lbl2, lbl3, lbl4;

    private List<City> cities = new ArrayList<>();
    private int numCities;
    private String startCity, endCity;
    private int[][] dp;
    private int[][] path;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        alternTableTA.setVisible(false);
        costlbl.setVisible(false);
        dpTableTA.setVisible(false);
        pathTableTA.setVisible(false);
        pathlbl.setVisible(false);

        browsebtn.setOnAction(event -> handleBrowseButtonAction());
        travelbtn.setOnAction(event -> handleTravelButtonAction());
    }

    private void handleBrowseButtonAction() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Text Files", "*.txt"));
        File file = fileChooser.showOpenDialog(filename.getScene().getWindow());
        if (file != null) {
            filename.setText(file.getAbsolutePath());
        }
    }

    private void handleTravelButtonAction() {
        String filePath = filename.getText();
        if (!filePath.isEmpty()) {
            cities.clear();
            loadCitiesFromFile(filePath);
            calculateOptimumCost();
            displayResults();
        }
    }

    private void loadCitiesFromFile(String filePath) {
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            numCities = Integer.parseInt(br.readLine());
            String[] startEnd = br.readLine().split(", ");
            startCity = startEnd[0];
            endCity = startEnd[1];

            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(", ");
                String cityName = parts[0];
                City city = cities.stream().filter(c -> c.getName().equals(cityName)).findFirst().orElse(null);
                if (city == null) {
                    city = new City(cityName);
                    cities.add(city);
                }

                for (int i = 1; i < parts.length; i++) {
                    String[] adjacentParts = parts[i].substring(1, parts[i].length() - 1).split(",");
                    String adjacentName = adjacentParts[0];
                    int petrolCost = Integer.parseInt(adjacentParts[1]);
                    int hotelCost = Integer.parseInt(adjacentParts[2]);
                    city.addAdjacent(new AdjacentCity(adjacentName, petrolCost, hotelCost));
                }
            }

            if (cities.stream().noneMatch(c -> c.getName().equals(endCity))) {
                cities.add(new City(endCity));
            }

        } catch (IOException | NumberFormatException e) {
            e.printStackTrace();
        }
    }

    private void calculateOptimumCost() {
        int size = cities.size();
        dp = new int[size][size];
        path = new int[size][size];

        for (int i = 0; i < size; i++) {
            Arrays.fill(dp[i], Integer.MAX_VALUE);
            dp[i][i] = 0;
        }

        for (int i = 0; i < size; i++) {
            City city = cities.get(i);
            for (AdjacentCity adj : city.getAdjacents()) {
                int j = getCityIndex(adj.getName());
                if (j != -1) {
                    dp[i][j] = adj.getHotelCost() + adj.getPetrolCost();
                }
            }
        }

        for (int k = 0; k < size; k++) {
            for (int i = 0; i < size; i++) {
                for (int j = 0; j < size; j++) {
                    if (dp[i][k] != Integer.MAX_VALUE && dp[k][j] != Integer.MAX_VALUE) {
                        if (dp[i][j] > dp[i][k] + dp[k][j]) {
                            dp[i][j] = dp[i][k] + dp[k][j];
                            path[i][j] = k;
                        }
                    }
                }
            }
        }
    }

    private int getCityIndex(String name) {
        for (int i = 0; i < cities.size(); i++) {
            if (cities.get(i).getName().equals(name)) return i;
        }
        return -1;
    }

    private void displayResults() {
        int startIdx = getCityIndex(startCity);
        int endIdx = getCityIndex(endCity);

        List<Route> allPaths = getAllPathsWithCost(startCity, new ArrayList<>());
        allPaths.sort(Comparator.comparingInt(Route::getCost));

        StringBuilder sb = new StringBuilder();
        for (Route path : allPaths) {
            sb.append(path.getPath()).append(" (Cost: ").append(path.getCost()).append(")\n");
        }

        alternTableTA.setText(sb.toString());
        dpTableTA.setText(generateTableString(dp));
        pathTableTA.setText(generateTableString(path));

        alternTableTA.setVisible(true);
        dpTableTA.setVisible(true);
        pathTableTA.setVisible(true);
        costlbl.setText("Cost: " + (dp[startIdx][endIdx] == Integer.MAX_VALUE ? "INF" : dp[startIdx][endIdx]));
        costlbl.setVisible(true);
        pathlbl.setText("Best Path: " + getPath(startIdx, endIdx));
        pathlbl.setVisible(true);

        lbl1.setVisible(true);
        lbl2.setVisible(true);
        lbl3.setVisible(true);
        lbl4.setVisible(true);
        pathl.setVisible(true);
        costl.setVisible(true);
    }

    private String getPath(int i, int j) {
        if (i == j) return cities.get(i).getName();
        if (path[i][j] == 0) return cities.get(i).getName() + " -> " + cities.get(j).getName();
        int k = path[i][j];
        return getPath(i, k) + " -> " + getPath(k, j);
    }

    private String generateTableString(int[][] table) {
        StringBuilder sb = new StringBuilder();
        sb.append("\t");
        for (City city : cities) {
            sb.append(city.getName()).append("\t");
        }
        sb.append("\n");

        for (int i = 0; i < cities.size(); i++) {
            sb.append(cities.get(i).getName()).append("\t");
            for (int j = 0; j < cities.size(); j++) {
                sb.append(table[i][j] == Integer.MAX_VALUE ? "INF" : table[i][j]).append("\t");
            }
            sb.append("\n");
        }
        return sb.toString();
    }

    private List<Route> getAllPathsWithCost(String current, List<String> pathSoFar) {
        pathSoFar.add(current);
        if (current.equals(endCity)) {
            return List.of(new Route(String.join(" -> ", pathSoFar), calculatePathCost(pathSoFar)));
        }

        List<Route> allRoutes = new ArrayList<>();
        City currentCity = cities.stream().filter(c -> c.getName().equals(current)).findFirst().orElse(null);
        if (currentCity == null) return allRoutes;

        for (AdjacentCity adj : currentCity.getAdjacents()) {
            if (!pathSoFar.contains(adj.getName())) {
                allRoutes.addAll(getAllPathsWithCost(adj.getName(), new ArrayList<>(pathSoFar)));
            }
        }
        return allRoutes;
    }

    private int calculatePathCost(List<String> path) {
        int cost = 0;
        for (int i = 0; i < path.size() - 1; i++) {
            String from = path.get(i);
            String to = path.get(i + 1);
            City city = cities.stream().filter(c -> c.getName().equals(from)).findFirst().orElse(null);
            if (city != null) {
                AdjacentCity adj = city.getAdjacent(to);
                if (adj != null) {
                    cost += adj.getHotelCost() + adj.getPetrolCost();
                }
            }
        }
        return cost;
    }

    // 🔧 FIXED: Add missing method for FXML onAction
    @FXML
    private void onHelloButtonClick() {
        System.out.println("Hello button clicked!");
    }
}
