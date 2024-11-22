/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EmployeeManager;

import EmployeeManager.Admin;
import EmployeeManager.Admin;
import EmployeeManager.AlertBox;
import EmployeeManager.AlertBox;
import EmployeeManager.DataManagement;
import EmployeeManager.Employee;
import EmployeeManager.Employee;
import EmployeeManager.EmployeeStage;
import EmployeeManager.Report;
import EmployeeManager.Report;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.PasswordField;
import javafx.scene.control.ToggleButton;
import javafx.scene.effect.BlendMode;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.text.Font;
import javafx.stage.StageStyle;

/**
 *
 * @author Chanawee
 */
public class Login extends Application{
    Stage window;
     
     AnchorPane mainPane = new AnchorPane();
     public static ArrayList<Employee> einLogin = new ArrayList<>();
     
     AnchorPane loginPane = new AnchorPane();
     int loginPanePosX = 271;
     int loginPanePosY = 299;
     
     Label signIn = new Label("Sign in");
     
     Button signButton = new Button("Sign in");

    @Override
    public void start(Stage window) throws Exception {
        //--------------------------DATADATADATADATADATADATADATA--------------------------------------
         einLogin = readingData();
         int count = 0;
         for (int i = 0; i < einLogin.size(); i++) {
             System.out.println("ID :   " + einLogin.get(i).getIdEmployee() + "  Pass :  " + einLogin.get(i).getPassword());
             count++;
        }
         //System.out.println(ein.get(0).getIdEmployee());
         
         //--------------------------DATADATADATADATADATADATADATA--------------------------------------
         //String css = Login.class.getResource("style.css").toExternalForm();
         
         Scene scene;
         mainPane.setPrefSize(700, 400);
         //loginPane.getStyleClass().add("login_pane");
         mainPane.setStyle("-fx-background-color: #273545");
         mainPane.setLayoutX(0);
         mainPane.setLayoutY(0);
         //mainPane.getStylesheets().add(css);
         loginPane.setPrefSize(271, 299);
         loginPane.setStyle("-fx-background-color: #586d85");
         loginPane.setLayoutX(395);
         loginPane.setLayoutY(68);
         
         
         signIn.setStyle("-fx-text-color: #FFFFFF");
         
         signIn.getStyleClass().add("label_sign_in");
         signIn.setLayoutX(90);
         signIn.setLayoutY(25);
         signIn.setAlignment(Pos.CENTER);
         signIn.setBlendMode(BlendMode.ADD);
         //signIn.getStyleClass().add("label_sign_in");
         signIn.setStyle("-fx-font-size: 32;-fx-text-fill: #FFFFFF;");
         
         
         
         
         ToggleButton btn = new ToggleButton("Sign in");
         btn.setLayoutX(30);
         btn.setLayoutY(30);
         btn.setOnAction((t) -> {
             System.out.println("Hello World");
         });
         
         //Exit
         Label exit = new Label("X");
         //exit.getStyleClass().add("exit_button");
         exit.setStyle("-fx-font-size: 28;-fx-text-fill: #FFFFFF;");
         exit.setLayoutX(666);
         exit.setLayoutY(10);
         exit.setOnMouseClicked((t) -> {
             window.close();
         });
         
         //Hide
         Label hide = new Label("-");
         //hide.getStyleClass().add("hide_button");
         hide.setStyle("-fx-font-size: 55;-fx-text-fill: #FFFFFF;");
         hide.setLayoutX(625);
         hide.setLayoutY(-10);
         hide.setOnMouseClicked((t) -> {
             window.setIconified(true);
         });
         Line usernameLine = new Line(47,115,224,115);
         usernameLine.setStroke(Color.WHITE);
         Line passwordLine = new Line(47,176,224,176);
         passwordLine.setStroke(Color.WHITE);
          
         
         
         //USERNAME FILL
         TextField usernameField = new TextField();
         usernameField.setPromptText("Username");
         //usernameField.setAlignment(Pos.CENTER);
         //42 89
         usernameField.setLayoutX(42);
         usernameField.setLayoutY(89);
         usernameField.setPrefSize(187, 31);
         if(usernameField.isHover()){
             usernameField.setStyle("-fx-background-color: #425469;-fx-font-size : 12;-fx-text-inner-color: white;");
         }
         else{
             
             usernameField.setStyle("-fx-background-color: #586d85;-fx-font-size : 12;-fx-text-inner-color: white;");
         }
//         usernameField.getStyleClass().add("textfield");
//         usernameField.getStyleClass().addAll("textfield","textfield:hover");
//         usernameField.setOnAction((t) -> {
//             usernameField.getStyleClass().addAll("textfield:focused","textfield:selected");
//             usernameField.setStyle("-fx-background-color: #425469");
//         });
         
         //usernameField.setFont(Font.font("Opun"));
         
         //PASSWORD FILL
         PasswordField passwordField = new PasswordField();
         passwordField.setPromptText("Password");
         //42 150
         passwordField.setLayoutX(42);
         passwordField.setLayoutY(150);
         passwordField.setPrefSize(187, 31);
         //passwordField.getStyleClass().addAll("textfield","textfield:hover");
         if(passwordField.isHover()){
             passwordField.setStyle("-fx-background-color: #425469;-fx-font-size : 12;-fx-text-inner-color: white;");
             System.out.println("password is hover");
         }
         else if(passwordField.isFocused()){
             passwordField.setStyle("-fx-background-color: #425469;-fx-font-size : 12;-fx-text-inner-color: white;");
             System.out.println("password is focused");
         }
         else{
             passwordField.setStyle("-fx-background-color: #586d85;-fx-font-size : 12;-fx-text-inner-color: white;");
             System.out.println("password nothing");
         }
         
         //BUTTON SIGN IN
         signButton.setLayoutX(22 /*+ loginPanePosX*/);
         signButton.setLayoutY(225 /*+ loginPanePosY*/);
         signButton.setPrefSize(227, 31);
         signButton.setOnAction((t) -> {
             String id = new String();
             id = "";
             String pass = new String();
             pass = "";
             id = usernameField.getText();
             pass = passwordField.getText();
             System.out.println(id + "  " + pass);
             
             int userNum = 0;
             int x = 0;
             if(id.length() > 0 && pass.length() > 0){
                 while(userNum < einLogin.size()){
                    if(einLogin.get(userNum).getIdEmployee().equals(id) && einLogin.get(userNum).getPassword().equals(pass)){
                         x = 1;
                        try {
                            if(!einLogin.get(userNum).getVacancy().equals("HR")&&!einLogin.get(userNum).getVacancy().equals("Manager")){
                            EmployeeStage e1 = new EmployeeStage(einLogin.get(userNum), window);
                            Report r1 = new Report(LocalDate.now().toString(), 'E',"["+einLogin.get(userNum).getIdEmployee()+einLogin.get(userNum).getName()+"] has login.");
                            addReport(r1);
                            usernameField.clear();
                            passwordField.clear();
                            window.close();}
                            else{break;}
                        } catch (Exception ex) {}
                         System.out.println("Correct");
                         break;
                     }
                    else if(id.equals("HR1404")&&pass.equals("HROperator"))
                    {
                     x=2;
                     usernameField.clear();
                     passwordField.clear();
                     window.close();
                        try {
                        Report r1 = new Report(LocalDate.now().toString(), 'E', "HR[Backdoor] has login.");
                        addReport(r1);
                        Admin hrOperator = new Admin(window);
                     } catch (Exception ex) {}break;}
                    else if(id.equals("Manager")&&pass.equals("1404")){
                        x=2;
                        usernameField.clear();
                        passwordField.clear();
                        try {
                            DataManagement d1 = new DataManagement(window);
                        } catch (Exception ex) {}
                        window.close();
                        break;
                    }
                            
                     System.out.println(einLogin.get(userNum).getName() + "  " + userNum);
                     userNum++;
                }
             }
             
             if(x == 0){
                 System.out.println("Error");
                 AlertBox.display(id, pass);
                  usernameField.clear();
                  passwordField.clear();
             }
             else if(x == 1){
                 if(einLogin.get(userNum).getVacancy().equals("HR")){/*---------------GO TO HR SCENE-------------*/
                    window.close();
                    try {
                    Admin hrAdmin = new Admin(window);
                    Report r1 = new Report(LocalDate.now().toString(), 'E', "HR["+einLogin.get(userNum).getIdEmployee()+einLogin.get(userNum).getName()+"] has login.");
                    addReport(r1);
                    usernameField.clear();
                    passwordField.clear();
                    window.close();
                    } catch (Exception ex) {
               
                    }
                 }
                 else if(einLogin.get(userNum).getVacancy().equals("Manager")){
                     window.close();
                    try {
                    DataManagement hrAdmin = new DataManagement(window);
                    Report r1 = new Report(LocalDate.now().toString(), 'E', "Manager["+einLogin.get(userNum).getIdEmployee()+einLogin.get(userNum).getName()+"] has login.");
                    addReport(r1);
                    usernameField.clear();
                    passwordField.clear();
                    window.close();
                    } catch (Exception ex) {
               
                    }
                 }
                 else{
                     System.out.println("go to common scene");/*---------------GO TO COMMON SCENE-------------*/
                 }
             }
              
             
//             try {
//                 if(isCorrect(id, pass, userNum).equals("Correct")){
//                     System.out.println(ein.get(userNum).getIdEmployee() + "    " + ein.get(userNum).getName() + "   " + userNum);
//                     System.out.println("Correct");
//                 }
//             } catch (IOException ex) {
//                 Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
//             } catch (ClassNotFoundException ex) {
//                 Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
//             }
             
         });
         //signButton.getStyleClass().add("button");
         if(signButton.isHover()){
             signButton.setStyle("-fx-backGround-color: #6a819e;-fx-text-fill: #FFFFFF;");
         }else{
             signButton.setStyle("-fx-backGround-color: #778DA9;-fx-text-fill: #FFFFFF;");
         }
         buttonEffect(signButton);
         //IMAGE
         Image iconImage = new Image(new FileInputStream("iconmonstr-building-46-120.png"));
         ImageView logo = new ImageView(iconImage);
         logo.setX(120);
         logo.setY(78);
         
         //EMP:OYEE MANAGEMENT SYSTEM
         Label emp = new Label("EMPLOYEE MANAGEMENT SYSTEM");
         //emp.getStyleClass().add("emp");
         emp.setStyle("-fx-font-size: 21;-fx-text-fill: #FFFFFF;");
         //18 278
         emp.setLayoutX(24);
         emp.setLayoutY(230);
         Label man = new Label("- MANAGE YOUR SCHEDULE -");
         //man.getStyleClass().add("man");
         man.setStyle("-fx-font-size: 19;-fx-text-fill: #FFFFFF;");
         man.setLayoutX(60);
         man.setLayoutY(280);
         loginPane.getChildren().addAll(signIn,signButton,usernameField,passwordField,usernameLine,passwordLine);
         mainPane.getChildren().addAll(loginPane,exit,hide,logo,emp,man);
         scene = new Scene(mainPane);
         window.initStyle(StageStyle.UNDECORATED);
         
         window.setScene(scene);
         window.show();
    }
    public static void main(String[] args) {
        // TODO code application logic here
        launch(args);
    }
    private ArrayList<Report> readingReport() throws FileNotFoundException, IOException, ClassNotFoundException
    {
        ObjectInputStream in = new ObjectInputStream(new FileInputStream("ReportData.dat"));
        ArrayList<Report> ac = new ArrayList<>();
        int count = in.readInt();
        for(int i=0;i<count;i++ )
        {
            ac.add((Report)in.readObject());
        }
        in.close();
        return ac;
    }
    private ArrayList<Employee> readingData() throws FileNotFoundException, IOException, ClassNotFoundException
    {
        ObjectInputStream in = new ObjectInputStream(new FileInputStream("EmployeeData.dat"));
        ArrayList<Employee> ein = new ArrayList<>();
        int count = in.readInt();
        for(int i=0;i<count;i++ )
        {
            ein.add((Employee)in.readObject());
        }
        in.close();
        return ein;
    }
    public String isCorrect(String user,String pass,int i) throws IOException, FileNotFoundException, ClassNotFoundException{
        int x = 0;
         
        String error = new String();
        ArrayList<Employee> ein = readingData();
        if(user.length() > 0 && pass.length() > 0){
            while(i < ein.size()){
                System.out.println(ein.get(i).getIdEmployee() + "   " + i + "   " + ein.get(i).getName());
                if(ein.get(i).getIdEmployee().equals(user) && ein.get(i).getPassword().equals(pass)){
                    x = 1;
                    error = "Correct";
                    break;                   
                }
                i++;
            }                      
        }
        else if(user.length() <= 0 && pass.length() > 0){
            System.out.println("Please enter username");
            error = "Please enter username";
        }
        else if(user.length() > 0 && pass.length() <= 0){
            System.out.println("Please enter password");
            error = "Please enter password";
        }
        else
        {
            System.out.println("Please enter username and password");
            error = "Please enter username and password";
        }
        if(x == 0)
        {
            System.out.println("Wrong username or password");
            error = "Wrong username or password";
        }
        x = 0;
        return error;
     }
    
    private void writeReport(ArrayList<Report> ac) throws FileNotFoundException, IOException
    {
        File f1 = new File("ReportData.dat");
        f1.delete();
        System.out.println("----------delete complete--------------");
        ObjectOutputStream Nout = new ObjectOutputStream(new FileOutputStream("ReportData.dat"));
        try{
        Nout.writeInt(ac.size());
        for(int i=0;i<ac.size();i++)
            {
                Nout.writeObject(ac.get(i)); 
            }
        Nout.close();
        }
        catch (IOException ex) {
            
        }
    }
    private void addReport(Report t) throws IOException, FileNotFoundException, ClassNotFoundException
    {
        ArrayList<Report> copy = readingReport();
        copy.add(t);writeReport(copy);
    }
    private void buttonEffect(Button button){
        button.addEventHandler(MouseEvent.MOUSE_MOVED, (MouseEvent t) -> {
            button.setStyle("-fx-backGround-color: #6a819e;-fx-text-fill: #FFFFFF;");
            //button.setTextFill(Color.web("#333138"));
        });
         button.addEventHandler(MouseEvent.MOUSE_EXITED, (MouseEvent t) -> {
             button.setStyle("-fx-backGround-color: #778DA9;-fx-text-fill: #FFFFFF;");
             //button.setTextFill(Color.web("#fcfeff"));
        });
    }
}

