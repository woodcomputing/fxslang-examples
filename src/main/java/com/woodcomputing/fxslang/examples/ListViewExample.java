/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.woodcomputing.fxslang.examples;

import com.woodcomputing.fxslang.collections.ObservableSlangList;
import java.text.MessageFormat;
import java.util.logging.Logger;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.collections.ListChangeListener;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 *
 * @author jwood
 */
public class ListViewExample extends Application {

    private static final Logger LOG = Logger.getLogger(ListViewExample.class.getName());
           
    public static void main(String[] args) {
        launch(args);
    }
    
    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("FXSlang ListView Sample");        
    
        ObservableSlangList<String> names = ObservableSlangList.empty();
    
        final ListView<String> listView = new ListView(names);
        listView.setPrefSize(200, 250);
        
        names.addListener((ListChangeListener.Change<? extends String> c) -> {
            while(c.next()) {
                if(c.wasAdded()) {
                    for(Object name : c.getAddedSubList()) {
                        LOG.info(MessageFormat.format("Adding: {0}", name.toString()));
                    }
                }
            }
        });
        
        names.addAll(
             "Adam", "Alex", "Alfred", "Albert",
             "Brenda", "Connie", "Derek", "Donny", 
             "Lynne", "Myrtle", "Rose", "Rudolph", 
             "Tony", "Trudy", "Williams", "Zach"
        );

        TextField searchField = new TextField();
        searchField.addEventHandler(KeyEvent.KEY_RELEASED, (KeyEvent t) -> {
            KeyCode key = t.getCode();
            if (key == KeyCode.ESCAPE){
                searchField.setText("");
            } else {
                if(!searchField.getText().isEmpty()) {
                    listView.setItems(names.filter(name -> name.toLowerCase().startsWith(searchField.getText().toLowerCase())));
                } else {
                    listView.setItems(names);
                }
            }
        });
        
        BorderPane root = new BorderPane();
        root.setCenter(listView);
        root.setBottom(searchField);
        primaryStage.setScene(new Scene(root, 200, 250));
        primaryStage.show();
    }

}
