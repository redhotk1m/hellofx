package org.openjfx;

import javafx.collections.ObservableList;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import static javafx.collections.FXCollections.observableArrayList;

public class mCSVWriter {

    public void saveFile(ObservableList<mdPerson> data) {
        try {
            String absolutePath = new File("").getAbsolutePath();
            FileWriter fileWriter =  new FileWriter(absolutePath + "/src/main/resources/org/openjfx/test4.csv");
            for (mdPerson datum : data) {
                fileWriter.write(datum.getFirstName() + ";");
                fileWriter.write(datum.getLastName() + ";");
                fileWriter.write(datum.getForsikringsNR() + "\n");

            }
            fileWriter.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
