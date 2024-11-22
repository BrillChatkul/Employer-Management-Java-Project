/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EmployeeManager;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 *
 * @author Chanawee
 */
public class AlertBox {
    public static void display(String title,String message){
        Stage window = new Stage();
        String css = Login.class.getResource("style.css").toExternalForm();
        AnchorPane main = new AnchorPane();
        main.setPrefSize(350, 200);
        Scene scene = new Scene(main);
        scene.getStylesheets().add(css);
        window.initStyle(StageStyle.UNDECORATED);
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle(title);
        window.setMinWidth(250);
        Label exit = new Label("X");
        exit.getStyleClass().add("alert_exit");
        exit.setLayoutX(319);
        exit.setLayoutY(14);
        exit.setOnMouseClicked((t) -> {
             window.close();
         });
        
        
        Label label = new Label();
        label.setText(message);
        label.setStyle("-fx-text-color: #F0EBD8");
        Button cont = new Button("CONTINUE");
        
        cont.setPrefSize(176, 31);
        //87 140
        cont.setLayoutX(87);
        cont.setLayoutY(140);
        cont.getStyleClass().add("alert_button");
        cont.setOnAction((t) -> {
            window.close();
        });
        Label warningLabel = new Label();
        warningLabel.setText("Invalid username or password");
        warningLabel.getStyleClass().add("alert_label");
        //17 65
        warningLabel.setLayoutX(17);
        warningLabel.setLayoutY(65);
        
        GridPane grid = new GridPane();
        grid.setPadding(new Insets(2, 2, 2, 2));
        grid.setVgap(15);
        grid.setHgap(15);
        GridPane.setConstraints(label, 0, 1);
        //GridPane.setConstraints(closeButton, 2, 1);
        //label.setAlignment(Pos.CENTER);
        
        VBox layout = new VBox(10);
        //layout.getChildren().addAll(label,closeButton);
        layout.setAlignment(Pos.CENTER);
        main.getStyleClass().add("alert");
        main.getChildren().addAll(cont,exit,warningLabel);
        
        
        
        window.setScene(scene);
        window.showAndWait();
    }
    
}
