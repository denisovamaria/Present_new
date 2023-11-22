package com.example.present_new;

import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class HelloController {

    @FXML
    private ComboBox<String> comboBox;
    @FXML
    private ComboBox<String> comboBox1;
    @FXML
    private Label label;
    @FXML
    private Label label1;
    private List<Present> list = new ArrayList<>();
    Integer m = 0;

    @FXML
    private void initialize() {
        updateData();
        comboData();
    }

    private void updateData() {
        try (Scanner in = new Scanner(new File("input.txt"))) {
            while (in.hasNextLine()) {
                Present present = new Present();
                present.manufacturer = in.nextLine();
                present.n = in.nextInt();
                in.nextLine();
                for (int i = 0; i < present.n; i++) {
                    present.present.add(in.nextLine());
                    present.price.add(Integer.parseInt(in.nextLine()));
                }
                list.add(present);
                m++;
            }
        } catch (FileNotFoundException e) {
            System.err.println("No such files: " + e.getMessage());
        }
    }

    private void comboData(){
        for (Present s : list) {
            comboBox.getItems().add(s.manufacturer);
        }
    }

    private void comboData1(){
        comboBox1.getItems().clear();
        String selectedManufacturer = comboBox.getValue();
        for (int i = 0; i < m; i++) {
            if (selectedManufacturer.equals(list.get(i).manufacturer)) {
                for (int j = 0; j < list.get(i).n; j++) {
                    comboBox1.getItems().add(list.get(i).present.get(j));
                }
            }
        }
    }

    @FXML
    private void selPres() {
        comboData1();
        comboBox1.setValue("Choose the present");
    }

}
