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
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 *
 * @author jwood
 */
public class ListViewExample extends Application {

    private static final Logger LOG = Logger.getLogger(ListViewExample.class.getName());
    
    private final ObservableSlangList names = ObservableSlangList.empty();
       
    public static void main(String[] args) {
        launch(args);
    }
    
    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("FXSlang ListView Sample");        
        
        final ListView listView = new ListView(names);
        listView.setPrefSize(200, 250);
        
        names.addListener((ListChangeListener.Change c) -> {
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
        
        StackPane root = new StackPane();
        root.getChildren().add(listView);
        primaryStage.setScene(new Scene(root, 200, 250));
        primaryStage.show();
    }

}
