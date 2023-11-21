package com.example.present_new;

import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.List;
import java.io.File;

public class HelloController {

    @FXML
    private ComboBox<String> comboBox;
    @FXML
    private ComboBox<String> comboBox1;
    @FXML
    private Label label;
    private List<String> list = new ArrayList<>();


    @FXML
    private void initialize() {
        updateData();
        for (int i = 0; i < list.size(); i++) {
            comboBox.getItems().addAll(list.get(i));
        }
        comboBox.setValue("Choose the manufacturer");
    }

    private void updateData() {
        Scanner in = null;
        try {
            in = new Scanner(new File("input.txt"));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        while (in.hasNextLine()) {
            list.add(in.nextLine());
        }
    }

    @FXML
    private void handleComboBoxSelection() {
        String selectedManufacturer = comboBox.getValue();
        label.setText(selectedManufacturer);
    }
}
