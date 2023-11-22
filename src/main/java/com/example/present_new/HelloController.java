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
    Label label2;
    private final List<Present> list = new ArrayList<>();
    Integer numberOfManufacturer = 0;

    Present man = new Present();

    Double price = (double) 0;

    String card = "No";

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
                    present.price.add(Double.parseDouble(in.nextLine()));
                }
                list.add(present);
                numberOfManufacturer++;
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
        for (int i = 0; i < numberOfManufacturer; i++) {
            if (selectedManufacturer.equals(list.get(i).manufacturer)) {
                man = list.get(i);
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

    @FXML
    private void card(){
        card = "Yes";
    }

    @FXML
    private void calcPrice(){
        String selectedPresent = comboBox1.getValue();
        for(int i = 0; i < man.n; i++)
        {
            if(man.present.get(i).equals(selectedPresent))
            {
                if(card.equals("Yes"))
                    price = man.price.get(i) * 0.9;
                else
                    price = man.price.get(i);
            }
        }
        label2.setText("The final price: " + price);
    }



}
