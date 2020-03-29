package sample;

import com.company.*;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;


import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.URL;
import java.text.DateFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.*;

public class Controller implements Initializable {
    CountingSort cs = new CountingSort();
    QuickSort qs = new QuickSort();
    InsertSort is = new InsertSort();

    Stage primaryStage;

    List<IntElement> intList = new ArrayList();
    List<IElement> startList = new ArrayList();
    List<IntElement> intResult = new ArrayList();
    List<IElement> result = new ArrayList();

    Locale pl = new Locale("pl","PL");
    Locale uk = new Locale("en","UK");
    Locale us = new Locale("en","US");
    NumberFormat nf;

    @FXML ListView notSortedList;
    @FXML ListView sortedList;

    @FXML TextField nameField;
    @FXML TextField valueField;

    @FXML ChoiceBox algorithmChoice;

    @FXML Label dateLabel;
    @FXML Label sortedLabel;
    @FXML Label toSortLabel;
    @FXML Label addLabel;
    @FXML Label aLabel;

    @FXML Button addButton;
    @FXML Button sortButton;

    @FXML ImageView imageView;

    @FXML MenuItem close;
    @FXML MenuItem plMItem;
    @FXML MenuItem ukMItem;
    @FXML MenuItem usMItem;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Setup();
        algorithmChoice.getItems().addAll("CountingSort","QuickSort","InsertSort");
        polishLanguage();

    }
    public void Setup(){

        IElement a = new IntElement("a",42234);
        IElement b = new IntElement("b",-2123);
        IElement c = new IntElement("c",2523423);
        IElement d = new IntElement("d",1232);
        startList.add(a);
        startList.add(b);
        startList.add(c);
        startList.add(d);

    }
    public void SortButtonClicked(){
        intList.clear();
        sortedList.getItems().clear();
        if(algorithmChoice.getValue()=="CountingSort") {
            for (IElement temp: startList) {
                intList.add((IntElement)temp);
            }
            intResult = intList;
            intResult = cs.solve(intResult);

            for (IntElement temp: intResult) {
                sortedList.getItems().add(toStringLocale(temp));
            }
        } else if (algorithmChoice.getValue()=="QuickSort"){
            result = startList;
            result = qs.solveAll(result,0,result.size()-1);
            updateList(sortedList,result);
        } else if(algorithmChoice.getValue()=="InsertSort"){
            result = startList;
            result = is.solveAll(result);
            updateList(sortedList,result);
        }
    }
    public void addButtonClicked() {
        if (!valueField.getText().isEmpty() && !nameField.getText().isEmpty()) {
            Float f = Float.parseFloat(valueField.getText());
            if (f % 1 != 0) {
                IElement n = new FloatElement(nameField.getText(), f);
                startList.add(n);
                algorithmChoice.getItems().clear();
                algorithmChoice.getItems().addAll("QuickSort","InsertSort");
            } else if (f % 1 == 0) {
                IElement n = new IntElement(nameField.getText(), Integer.parseInt(valueField.getText()));
                startList.add(n);
            }
            updateList(notSortedList, startList);
            nameField.clear();
            valueField.clear();
        }

    }
    public void polishLanguage() {
        nf = NumberFormat.getInstance(pl);

        String pattern = "dd.MM.yyyy";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        String date = simpleDateFormat.format(new Date());
        dateLabel.setText(date);

        sortedLabel.setText("Posortowane:");
        toSortLabel.setText("Dane do sortowania:");
        addLabel.setText("Dodaj Element");
        aLabel.setText("Algorytmy: ");
        sortButton.setText("Sortuj");
        addButton.setText("Dodaj");
        updateList(notSortedList, startList);
        updateList(sortedList,result);
    }
    public void englishLanguage() {
        nf = NumberFormat.getInstance(uk);


        String pattern = "dd/MM/yyyy";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        String date = simpleDateFormat.format(new Date());
        dateLabel.setText(date);

        sortedLabel.setText("Sorted:");
        toSortLabel.setText("Data to sort:");
        addLabel.setText("Add element");
        aLabel.setText("Algorithms: ");
        sortButton.setText("Sort");
        addButton.setText("Add");
        updateList(notSortedList, startList);
        updateList(sortedList,result);
    }
    public void americaLanguage() {
        nf = NumberFormat.getInstance(us);

        String pattern = "  M/d/yyyy";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        String date = simpleDateFormat.format(new Date());
        dateLabel.setText(date);

        sortedLabel.setText("Sorted:");
        toSortLabel.setText("Data to sort:");
        addLabel.setText("Add element");
        aLabel.setText("Algorithms: ");
        sortButton.setText("Sort");
        addButton.setText("Add");
       updateList(notSortedList, startList);
       updateList(sortedList,result);
    }
    public String toStringLocale(IElement i){
        return "\nName: " + i.getName() + ", Value: " + nf.format(i.getValue());
    }

    public void updateList(ListView lv, List<IElement> l){
        lv.getItems().clear();
        for (IElement temp: l) {
            lv.getItems().add(toStringLocale(temp));
        }
    }
    public void closeApp(){
        Platform.exit();
    }
}
