/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EmployeeManager;

/**
 *
 * @author billc
 */
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import static javafx.scene.text.Font.font;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Callback;


/**
 *
 * @author Chanawee
 */
public class EmployeeStage {

    /**
     * @param args the command line arguments
     */
    Stage window;
    private Employee dataType = null;
    
    /**
     *
     * @param window
     * @throws Exception
     */
    public EmployeeStage(Employee data,Stage login)throws Exception{
        dataType = data;
        Stage stage = new Stage();
        ///////////////////////////////////////LEFT MENU///////////////////////////////
        /////////////////////ADMINISTRATOR LOGO///////////////////
        ///Image///
        Image iconImage = new Image(new FileInputStream("Icon.png"));
        ImageView logo = new ImageView(iconImage);
        ImageView logo1 = new ImageView(iconImage);
        logo.setX(120);
        logo.setY(78);
        logo1.setFitHeight(400);logo1.setFitWidth(400);
        logo1.setOpacity(0.5);
       
        BorderPane BgLogo = new BorderPane();
        BgLogo.setMinSize(300, 200);
        BgLogo.setCenter(logo);
        BgLogo.setStyle("-fx-background-color: #273545");
         
        
        ///////////////////BUTTON MENU//////////////////////////////
        
    
       
        Button logOut = new Button("Logout");
        logOut.setMinSize(280, 50);
        logOut.setAlignment(Pos.CENTER);
        logOut.setFont(font("Arial",20));
        logOut.setTextFill(Color.web("#fcfeff"));
        logOut.setStyle("-fx-background-color: #586d85");
        logOut.setLayoutX(10);
        logOut.setLayoutY(-100);
        buttonEffect(logOut);
        
        Button editProfile = new Button("Edit Profile");
        editProfile.setMinSize(280, 50);
        editProfile.setAlignment(Pos.CENTER);
        editProfile.setFont(font("Arial",20));
        editProfile.setTextFill(Color.web("#fcfeff"));
        editProfile.setStyle("-fx-background-color: #586d85");
        editProfile.setLayoutX(10);
        editProfile.setLayoutY(-100);
        buttonEffect(editProfile);
        
        ///Change Password
        Button changePasswordBt = new Button("Change Password");
        changePasswordBt.setMinSize(280, 50);
        changePasswordBt.setAlignment(Pos.CENTER);
        changePasswordBt.setFont(font("Arial",20));
        changePasswordBt.setTextFill(Color.web("#fcfeff"));
        changePasswordBt.setStyle("-fx-background-color: #586d85");
        changePasswordBt.setLayoutX(10);
        changePasswordBt.setLayoutY(-100);
        buttonEffect(changePasswordBt);
        changePasswordBt.setOnAction((ActionEvent ps)->{
            Stage changePasswordStage = new Stage();
            BorderPane PasswordBar = new BorderPane();PasswordBar.setMinSize(400, 50);
            PasswordBar.setStyle("-fx-background-color: #273545");
            GridPane passwordGridPane = new GridPane(); passwordGridPane.setVgap(3);
            passwordGridPane.setHgap(2);passwordGridPane.setAlignment(Pos.CENTER);
            passwordGridPane.setPadding(new Insets(10, 10, 10, 10));
            Label olLabel = new Label("Old password :");passwordGridPane.add(olLabel, 0, 0);
            Label neLabel = new Label("new password :");passwordGridPane.add(neLabel, 0, 1);
            Label confirmLabel = new Label("Confirm password :");passwordGridPane.add(confirmLabel, 0, 2);
            TextField olTextField = new TextField();passwordGridPane.add(olTextField, 1, 0);
            PasswordField neTextField = new PasswordField();passwordGridPane.add(neTextField, 1, 1);
            PasswordField confirmTextField = new PasswordField();passwordGridPane.add(confirmTextField, 1, 2);
            
            Button bOkPassword = new Button("Ok");Button bCancelPassword = new Button("Cancel");
            HBox h1 = new HBox();h1.getChildren().addAll(bOkPassword,bCancelPassword);
            h1.setAlignment(Pos.CENTER);h1.setPadding(new Insets(10, 10, 10, 10));h1.setSpacing(10);
            bOkPassword.setOnAction((ActionEvent e1)->{
                if(!olTextField.textProperty().get().isEmpty()&&!neTextField.textProperty().get().isEmpty()&&!confirmTextField.textProperty().get().isEmpty())
                {if(!olTextField.textProperty().get().equals(dataType.getPassword()))
                    {
                        errorMessage("Invalid old password", olTextField, neTextField, confirmTextField);
                    }
                else if(!neTextField.textProperty().get().equals(confirmTextField.textProperty().get()))
                {
                    errorMessage("Invalid confirm password", olTextField, neTextField, confirmTextField);
                }
                else{
                    try {
                        ArrayList<Employee> sourc = readingData();
                        for (int i = 0; i < sourc.size(); i++) {
                            if(sourc.get(i).getIdEmployee().equals(dataType.getIdEmployee()))
                            {
                                sourc.get(i).setPassword(neTextField.textProperty().get());
                                writeData(sourc);
                                changePasswordStage.close();
                                Report r1 = new Report(LocalDate.now().toString(), 'E', "["+dataType.getIdEmployee()+dataType.getName()+"] has been updated!");
                                addReport(r1);
                                break;
                            }
                        }
                        
                    } catch (IOException | ClassNotFoundException ex) {
                        
                    }
                }
                }else{
                    errorMessage("Invalid password", olTextField, neTextField, confirmTextField);}
            });
            bCancelPassword.setOnAction((ActionEvent cancel1)->{
            changePasswordStage.close();
            });
            
                        
            BorderPane displayBorderPane = new BorderPane();displayBorderPane.setMinSize(400, 250);
            displayBorderPane.setTop(PasswordBar);displayBorderPane.setCenter(passwordGridPane);
            displayBorderPane.setBottom(h1);
            Scene passwordScene = new Scene(displayBorderPane);
            changePasswordStage.setScene(passwordScene);
            changePasswordStage.initModality(Modality.APPLICATION_MODAL);
            changePasswordStage.initStyle(StageStyle.UNDECORATED);
            changePasswordStage.show();
        });
                        
        Button Exit = new Button("X");
        Exit.setFont(font("Arial",20));
        Exit.setMinSize(30, 30);
        Exit.setAlignment(Pos.CENTER);
        Exit.setFont(font("Arial",20));
        Exit.setTextFill(Color.web("#fcfeff"));
        Exit.setStyle("-fx-background-color: #586d85");
        Exit.setLayoutX(938);
        buttonEffect(Exit);
        
        Button Hide = new Button("_");
        Hide.setFont(font("Arial",20));
        Hide.setMinSize(30, 30);
        Hide.setAlignment(Pos.CENTER);
        Hide.setFont(font("Arial",20));
        Hide.setTextFill(Color.web("#fcfeff"));
        Hide.setStyle("-fx-background-color: #586d85");
        Hide.setLayoutX(895);
        buttonEffect(Hide);
        
        Label Hello = new Label("Hello "+ dataType.getName());
        Hello.setTextFill(Color.web("#ffffff"));
        Hello.setFont(font("Arial",30));
        Hello.setStyle("-fx-font-weight: bold");
        Hello.setLayoutX(10);
        

        
        
        VBox ButtonVBox = new VBox();
        ButtonVBox.setPadding(new Insets(10, 10, 10, 10));
        ButtonVBox.getChildren().addAll(editProfile,changePasswordBt,logOut);
        ButtonVBox.setAlignment(Pos.TOP_CENTER);
        AnchorPane buttonMenu = new AnchorPane();
        buttonMenu.getChildren().addAll(ButtonVBox);
        BorderPane leftPane = new BorderPane();
        leftPane.setMinSize(300, 720);
        leftPane.setStyle("-fx-background-color: #586d85");
        leftPane.setTop(BgLogo);
        leftPane.setCenter(Hello);
        leftPane.setBottom(buttonMenu);
        leftPane.setVisible(true);
        
        /////////////////////////////////////////////
        BorderPane mainObjectPane = new BorderPane();
        mainObjectPane.setMinSize(980, 720);
        
        BorderPane menubar = new BorderPane();
        
        AnchorPane topic = new AnchorPane();
        Label employeeTopicLabel = new Label("Employee Information");
        
        employeeTopicLabel.setTextFill(Color.web("#ffffff"));
        employeeTopicLabel.setFont(font("Arial",40));
        employeeTopicLabel.setStyle("-fx-font-weight: bold");
        employeeTopicLabel.setLayoutX(300);
        employeeTopicLabel.setLayoutY(50);
        topic.getChildren().addAll(employeeTopicLabel,Exit,Hide);
       
        BorderPane Info = new BorderPane();
        Info.setStyle("-fx-background-color: #FFB4A2");
        
        Label name = new Label("Name : " + dataType.getName());
        name.setFont(font("Arial",20));
        name.setStyle("-fx-font-weight: bold");
        Label surname = new Label("Surname : "+ dataType.getSurname());
        surname.setFont(font("Arial",20));
        surname.setStyle("-fx-font-weight: bold");
        Label gender = new Label("Gender : "+ dataType.getGender());
        gender.setFont(font("Arial",20));
        gender.setStyle("-fx-font-weight: bold");
        Label age = new Label("Age : "+ dataType.getAge());
        age.setFont(font("Arial",20));
        age.setStyle("-fx-font-weight: bold");
        Label id = new Label("No.ID : "+ dataType.getIdNo());
        id.setFont(font("Arial",20));
        id.setStyle("-fx-font-weight: bold");
        Label address = new Label("Address : "+ dataType.getAddress());
        address.setFont(font("Arial",20)); address.setWrapText(true);
        address.setMaxSize(300, 200);
        address.setStyle("-fx-font-weight: bold");
        Label idem = new Label("ID Employee : "+ dataType.getIdEmployee());
        idem.setFont(font("Arial",20));
        idem.setStyle("-fx-font-weight: bold");
        Label email = new Label("Email : "+ dataType.getEmail());
        email.setFont(font("Arial",20));
        email.setStyle("-fx-font-weight: bold");
        Label phone = new Label("Phone Number : "+ dataType.getPhoneNumber());
        phone.setFont(font("Arial",20));
        phone.setStyle("-fx-font-weight: bold");
        Label vacancy = new Label("Vacancy : "+ dataType.getVacancy());
        vacancy.setFont(font("Arial",20));
        vacancy.setStyle("-fx-font-weight: bold");
        Label salary = new Label("Salary : "+ dataType.getSalary()+" Baht");
        salary.setFont(font("Arial",20));
        salary.setStyle("-fx-font-weight: bold");
        
        //////edit Profile
        editProfile.setOnAction((ActionEvent a)->{
        Stage employeeEditStage = new Stage();
        BorderPane topicEditBorderPane = new BorderPane();
        Label IdText = new Label("Edit Profile");
        IdText.setFont(font(20));IdText.setTextFill(Color.web("#ffffff"));IdText.setStyle("-fx-font-weight: bold");
        topicEditBorderPane.setMinSize(600, 80);
        topicEditBorderPane.setStyle("-fx-background-color: #273545");
        topicEditBorderPane.setCenter(IdText);
        GridPane dataEditPane = new GridPane();
        dataEditPane.setAlignment(Pos.TOP_CENTER);
        dataEditPane.setPadding(new Insets(15, 15, 15, 15));dataEditPane.setVgap(2);dataEditPane.setHgap(2);
                             
                             
        Label emEdit2 = new Label("Name :");emEdit2.setStyle("-fx-font-weight: bold");
        Label emEdit3 = new Label("SURNAME :");emEdit3.setStyle("-fx-font-weight: bold");
        Label emEdit4 = new Label("GENDER :");emEdit4.setStyle("-fx-font-weight: bold");
        Label emEdit5 = new Label("AGE :");emEdit5.setStyle("-fx-font-weight: bold");
        Label emEdit7 = new Label("ADDRESS :");emEdit7.setStyle("-fx-font-weight: bold");
        Label emEdit8 = new Label("EMAIL :");emEdit8.setStyle("-fx-font-weight: bold");
        Label emEdit9 = new Label("PHONE NUMBER :");emEdit9.setStyle("-fx-font-weight: bold");
        VBox vBoxEditText = new VBox(emEdit2,emEdit3,emEdit4,emEdit5,emEdit7,emEdit8,emEdit9);
        vBoxEditText.setAlignment(Pos.BASELINE_RIGHT); vBoxEditText.setSpacing(13); dataEditPane.add(vBoxEditText, 0, 0);
                       
        TextField tEdit2 = new TextField(); tEdit2.setText(data.getName());
        TextField tEdit3 = new TextField(); tEdit3.setText(data.getSurname());
        TextField tEdit4 = new TextField(); tEdit4.setText(data.getGender());
        String ageConvert = Integer.toString(data.getAge());
        TextField tEdit5 = new TextField(); tEdit5.setText(ageConvert);
        TextField tEdit7 = new TextField(); tEdit7.setText(data.getAddress()); tEdit7.setMinWidth(200);
        TextField tEdit8 = new TextField(); tEdit8.setText(data.getEmail());
        TextField tEdit9 = new TextField(); tEdit9.setText(data.getPhoneNumber());
        VBox vBoxEditField = new VBox(tEdit2,tEdit3,tEdit4,tEdit5,tEdit7,tEdit8,tEdit9);
        vBoxEditField.setSpacing(5); dataEditPane.add(vBoxEditField, 1, 0);
                             
        HBox buttonChoice = new HBox();
        buttonChoice.setAlignment(Pos.BASELINE_RIGHT);
        Button applyButton = new Button("APPLY");
        applyButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent save) {
                try{
                    dataType.setName(tEdit2.textProperty().get());
                    dataType.setSurname(tEdit3.textProperty().get());
                    dataType.setGender(tEdit4.textProperty().get());
                    int ageConvert = Integer.parseInt(tEdit5.textProperty().get());
                    dataType.setAge(ageConvert);
                    dataType.setAddress(tEdit7.textProperty().get());
                    dataType.setEmail(tEdit8.textProperty().get());
                    dataType.setPhoneNumber(tEdit9.textProperty().get());
                    
                    name.setText("Name : " + dataType.getName());
                    surname.setText("Surname : "+ dataType.getSurname());
                    gender.setText("Gender : "+ dataType.getGender());
                    age.setText("Age : "+ dataType.getAge());
                    address.setText("Address : "+ dataType.getAddress());
                    idem.setText("ID Employee : "+ dataType.getIdEmployee());
                    email.setText("Email : "+ dataType.getEmail());
                    phone.setText("Phone Number : "+ dataType.getPhoneNumber());
                                                     
                    
                    } catch(NumberFormatException e){
                    Stage errorConverStage = new Stage();
                    VBox ErrorVBox = new VBox();ErrorVBox.setStyle("-fx-background-color: #273545");
                    Label ErrorMessageLabel = new Label("Error:Invalid variables");ErrorMessageLabel.setTextFill(Color.web("#ffffff"));
                    Button okErrorButton = new Button("Ok");
                    okErrorButton.setOnAction((ActionEvent errorActionEvent) ->{errorConverStage.close();employeeEditStage.close();});
                    ErrorVBox.getChildren().addAll(ErrorMessageLabel,okErrorButton);ErrorVBox.setSpacing(10);ErrorVBox.setAlignment(Pos.CENTER);
                    Scene sError = new Scene(ErrorVBox, 200, 100);
                    errorConverStage.setScene(sError);
                    errorConverStage.initStyle(StageStyle.UNDECORATED);
                    errorConverStage.initModality(Modality.APPLICATION_MODAL);
                    errorConverStage.show();
                                    }
                                 
                                    
                                    try {
                                        EditEmployee(dataType); employeeEditStage.close();
                                        Report r1 = new Report(LocalDate.now().toString(), 'E', "["+data.getIdEmployee()+data.getName()+"] has been updated!");
                                        addReport(r1);
                                    } catch (Exception e) {
                                    }

                                }
                            });
                             Button cancelButton = new Button("CANCEL");
                             cancelButton.setOnAction((ActionEvent cancel) -> {employeeEditStage.close();});
                             buttonChoice.getChildren().addAll(applyButton,cancelButton);
                             buttonChoice.setPadding(new Insets(15, 15, 15, 15));buttonChoice.setSpacing(15);
        
        BorderPane displayEdit = new BorderPane();
        displayEdit.setTop(topicEditBorderPane);
        displayEdit.setCenter(dataEditPane);
        displayEdit.setBottom(buttonChoice);
                             
        Scene sceneEdit = new Scene(displayEdit,600,360);
        employeeEditStage.setScene(sceneEdit);
        employeeEditStage.initModality(Modality.APPLICATION_MODAL);
        employeeEditStage.initStyle(StageStyle.UNDECORATED);
        employeeEditStage.show();
        });
        
        VBox InfoEM1 = new VBox();
        InfoEM1.setLayoutX(50);
        InfoEM1.setLayoutY(40);
        InfoEM1.setSpacing(40);
        InfoEM1.getChildren().addAll(name,surname,gender,age,id,address,idem);
        VBox InfoEM2 = new VBox();
        InfoEM2.setLayoutX(550);
        InfoEM2.setLayoutY(40);
        InfoEM2.setSpacing(35);
        
        TextField searchWork = new TextField();
        searchWork.setFont(font("Arial",15));
        searchWork.setMaxWidth(200);
        searchWork.setLayoutX(615);
        searchWork.setLayoutY(290);
        searchWork.setPromptText("Search Subject");
          
        
        ObservableList<scheduleWork> schworkData = FXCollections.observableArrayList(dataType.getScheduleWorks());
        TableView<scheduleWork> table = new TableView<>(); ;
        TableColumn subColumn = new TableColumn("Subject");
        subColumn.setMinWidth(150);
        subColumn.setStyle("-fx-alignment: CENTER");
        subColumn.setCellValueFactory(new PropertyValueFactory<scheduleWork,String>("nameSubject"));
        TableColumn dateColumn = new TableColumn("Date");
        dateColumn.setMinWidth(150);
        dateColumn.setStyle("-fx-alignment: CENTER");
        dateColumn.setCellValueFactory(new PropertyValueFactory<scheduleWork,String>("date"));
        table.setItems(schworkData);
        TableColumn<scheduleWork, Void> deleteWorkCol = new TableColumn();
        deleteWorkCol.setMinWidth(150);
        deleteWorkCol.setStyle("-fx-alignment: CENTER");
        Callback<TableColumn<scheduleWork, Void>, TableCell<scheduleWork, Void>> WorkDelete = new Callback<TableColumn<scheduleWork, Void>, TableCell<scheduleWork, Void>>()
        {
            @Override
            public TableCell<scheduleWork, Void> call(final TableColumn<scheduleWork, Void> param) {
                final TableCell<scheduleWork, Void> cell = new TableCell<scheduleWork, Void>() {

                    private final Button btn = new Button("Delete");
                    {
                        btn.setOnAction((ActionEvent event) -> {
                        scheduleWork DeletingWork = getTableView().getItems().get(getIndex());
                        Report r1 = new Report(LocalDate.now().toString(), 'E', "["+dataType.getIdEmployee()+dataType.getName()+"] : Remove Work["+DeletingWork.getNameSubject()+"].");
                        schworkData.remove(getTableView().getItems().get(getIndex()));
                        try {
                            addReport(r1);
                            removeWork(dataType, DeletingWork);
                            dataType.removeScheduleWork(DeletingWork);
                        } catch (Exception ex) {}      
                        
                    });}
                    @Override
                    public void updateItem(Void item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(null);
                        } else {
                            setGraphic(btn);
                        }}};
                    return cell;
        }};
        deleteWorkCol.setCellFactory(WorkDelete);
        
        table.getColumns().addAll(subColumn,dateColumn,deleteWorkCol);
        table.setMaxWidth(453);
        table.setMaxHeight(200);
        
        HBox workHBox = new HBox();
        Label sub = new Label("Subject");sub.setStyle("-fx-font-weight: bold");
        sub.setTextFill(Color.web("#ffffff"));
        Label sub2 = new Label("Due Date");sub2.setStyle("-fx-font-weight: bold");sub2.setTextFill(Color.web("#ffffff"));
        TextField textsub = new TextField();
        textsub.setPromptText("Name");
        DatePicker dateP = new DatePicker();
        dateP.setPromptText("dd/mm/yyyy");
        Button addwork = new Button("ADD WORK");
        addwork.setOnAction((ActionEvent actionEvent)->{
        try{
            LocalDate iconvert = dateP.getValue(); 
            scheduleWork addingWork = new scheduleWork(textsub.textProperty().get(),iconvert.toString());
            schworkData.add(addingWork);
            addWork(dataType, addingWork);
            Report r1 = new Report(LocalDate.now().toString(), 'E', "["+dataType.getIdEmployee()+dataType.getName()+"] : Add Work["+addingWork.getNameSubject()+"].");
            addReport(r1);
            }
            catch(Exception e){
                Stage errorConverStage = new Stage();
                VBox ErrorVBox = new VBox();ErrorVBox.setStyle("-fx-background-color: #273545");
                Label ErrorMessageLabel = new Label("Error:Invalid variables");ErrorMessageLabel.setTextFill(Color.web("#ffffff"));
                Button okErrorButton = new Button("Ok");
                okErrorButton.setOnAction((ActionEvent errorActionEvent) ->{errorConverStage.close();dateP.getEditor().clear();textsub.clear();});
                ErrorVBox.getChildren().addAll(ErrorMessageLabel,okErrorButton);ErrorVBox.setSpacing(10);ErrorVBox.setAlignment(Pos.CENTER);
                Scene sError = new Scene(ErrorVBox, 200, 100);
                errorConverStage.initStyle(StageStyle.UNDECORATED);
                errorConverStage.initModality(Modality.APPLICATION_MODAL);
                errorConverStage.setScene(sError);
                errorConverStage.show();
            }
            table.refresh();
            dateP.getEditor().clear();
            textsub.clear();
        });
        dateP.getEditor().setDisable(true);
        searchWork.textProperty().addListener(new InvalidationListener() {
        @Override
        public void invalidated(Observable o) {
        if(searchWork.textProperty().get().isEmpty())
        {
            table.setItems(schworkData);
        }
        else
        {
            ObservableList<scheduleWork> tableWorkItems = FXCollections.observableArrayList();
            ObservableList<TableColumn<scheduleWork, ?>> cols = table.getColumns();

            for(int i=0; i<schworkData.size(); i++) {
            TableColumn col = cols.get(0);
            String cellValue = col.getCellData(schworkData.get(i)).toString();
            cellValue = cellValue.toLowerCase();
            if(cellValue.contains(searchWork.textProperty().get().toLowerCase())) {
            tableWorkItems.add(schworkData.get(i));
            }                        
                    
            }
            table.setItems(tableWorkItems);}
            }});
        
        workHBox.getChildren().addAll(sub,textsub,sub2,dateP,addwork);
        workHBox.setAlignment(Pos.CENTER);
        workHBox.setSpacing(10);
        workHBox.setLayoutX(420);
        workHBox.setLayoutY(530);
        VBox sworkVBox = new VBox();
        sworkVBox.getChildren().addAll(searchWork,table,workHBox);
        sworkVBox.setAlignment(Pos.CENTER);sworkVBox.setSpacing(10);
        sworkVBox.setLayoutX(415);sworkVBox.setLayoutY(260);
        sworkVBox.setPadding(new Insets(20, 20, 20, 20));
        sworkVBox.setStyle("-fx-background-color: AF502A;-fx-background-radius: 5.0;");
        sworkVBox.setMaxSize(580, 300);
        
        InfoEM2.getChildren().addAll(email,phone,vacancy,salary);
        AnchorPane aPane1 = new AnchorPane();
        AnchorPane aPane0 = new AnchorPane();
        StackPane s1StackPane = new StackPane();
        aPane0.getChildren().addAll(InfoEM1,InfoEM2);
        s1StackPane.getChildren().addAll(logo1,aPane0);
        aPane1.getChildren().addAll(s1StackPane); //sworkVbox not include
        aPane1.setMaxSize(950, 540);aPane1.setStyle("-fx-background-color: #ffe2db;-fx-background-radius: 5.0;");
        Info.setCenter(aPane1);
        BorderPane aPane2 = new BorderPane();
        BorderPane aPane3 = new BorderPane();
        aPane3.setRight(sworkVBox);
        aPane2.setBottom(aPane3);aPane2.setPadding(new Insets(10, 10, 10, 10));
        StackPane std1 = new StackPane();
        std1.getChildren().addAll(aPane1,aPane2);
        menubar.setLeft(topic);
        menubar.setMinSize(980, 150);
        menubar.setStyle("-fx-background-color: #374454"); 
        mainObjectPane.setTop(menubar);
        mainObjectPane.setCenter(std1);
        mainObjectPane.setStyle("-fx-background-color: #FFB4A2;");
        
        
        
       
       
        
        
        ///////////////DISPLAY/////////////////////////////
        BorderPane display = new BorderPane();
        display.setLeft(leftPane);
        display.setRight(mainObjectPane);
        
        
        logOut.setOnAction((ActionEvent t)->{
        login.show();stage.close();
            try {
                Login.einLogin = readingData();
            } catch (IOException | ClassNotFoundException ex) {
                
            }
        });
        
        Exit.setOnAction((ActionEvent t)->{
        stage.close();
               
        });
        Hide.setOnAction((ActionEvent t)->{
        stage.setIconified(true);
        });
        
        
       
        
        Scene scene = new Scene(display,1280,720);
        stage.setScene(scene);
       
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.initStyle(StageStyle.UNDECORATED);
        stage.show();
    }
     private void buttonEffect(Button button){
        button.addEventHandler(MouseEvent.MOUSE_MOVED, (MouseEvent t) -> {
            button.setStyle("-fx-background-color: #FFB4A2");
            button.setTextFill(Color.web("#333138"));
        });
         button.addEventHandler(MouseEvent.MOUSE_EXITED, (MouseEvent t) -> {
             button.setStyle("-fx-background-color: #586d85");
             button.setTextFill(Color.web("#fcfeff"));
        });
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
    }private ArrayList<Report> readingReport() throws FileNotFoundException, IOException, ClassNotFoundException
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
    private void writeData(ArrayList<Employee> ein) throws FileNotFoundException, IOException
    {
        File f1 = new File("EmployeeData.dat");
        f1.delete();
        System.out.println("----------delete complete--------------");
        ObjectOutputStream Nout = new ObjectOutputStream(new FileOutputStream("EmployeeData.dat"));
        try{
        Nout.writeInt(ein.size());
        for(int i=0;i<ein.size();i++)
            {
                Nout.writeObject(ein.get(i)); 
            }
        Nout.close();
        }
        catch (Exception ex) {
            
        }
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
    
    private void EditEmployee(Employee e) throws IOException, FileNotFoundException, ClassNotFoundException
    {
        ArrayList<Employee> a = readingData();
        for (int i = 0; i < a.size(); i++) {
            if(a.get(i).getIdEmployee().equals(e.getIdEmployee()))
            {
               a.get(i).setAddress(e.getAddress());
               a.get(i).setAge(e.getAge());
               a.get(i).setEmail(e.getEmail());
               a.get(i).setGender(e.getGender());
               a.get(i).setName(e.getName());
               a.get(i).setSurname(e.getSurname());
               a.get(i).setPhoneNumber(e.getPhoneNumber());
                break;
            }
        }
        writeData(a);
    }
    private void addReport(Report t) throws IOException, FileNotFoundException, ClassNotFoundException
    {
        ArrayList<Report> copy = readingReport();
        copy.add(t);writeReport(copy);
    }
    
    private void addWork(Employee data,scheduleWork w) throws IOException, FileNotFoundException, ClassNotFoundException
    {
        ArrayList<Employee> a = readingData();
        for (int i = 0; i < a.size(); i++) {
            if(a.get(i).getIdEmployee().equals(data.getIdEmployee()))
            {
                a.get(i).setScheduleWork(w);
                break;
            }
        }
        writeData(a);
    }
    private void removeWork(Employee data,scheduleWork w) throws IOException, FileNotFoundException, ClassNotFoundException
    {
        ArrayList<Employee> a = readingData();
        for (int i = 0; i < a.size(); i++) {
            if(a.get(i).getIdEmployee().equals(data.getIdEmployee()))
            {
                System.out.println(a.get(i).getScheduleWorks().size());
                System.out.println(a.get(i).getIdEmployee()+data.getIdEmployee());
                System.out.println(w.getNameSubject());
                for (int j = 0; j < a.get(i).getScheduleWorks().size(); j++) {
                    if(w.getNameSubject().equals(a.get(i).getScheduleWorks().get(j).getNameSubject())&&w.getDate().equals(a.get(i).getScheduleWorks().get(j).getDate()))
                    {a.get(i).removeScheduleWork(a.get(i).getScheduleWorks().get(j));
                    break;}
                }
                System.out.println(a.get(i).getScheduleWorks().size());
                break;
            }
        }
        writeData(a);
    }
    private void errorMessage(String s1,TextField oldText,TextField newText,TextField conText){
    Stage errorConverStage = new Stage();
    VBox ErrorVBox = new VBox();ErrorVBox.setStyle("-fx-background-color: #273545");
    Label ErrorMessageLabel = new Label(s1);ErrorMessageLabel.setTextFill(Color.web("#ffffff"));
    Button okErrorButton = new Button("Ok");
    okErrorButton.setOnAction((ActionEvent errorActionEvent) ->{errorConverStage.close();oldText.clear();newText.clear();conText.clear();});
    ErrorVBox.getChildren().addAll(ErrorMessageLabel,okErrorButton);ErrorVBox.setSpacing(10);ErrorVBox.setAlignment(Pos.CENTER);
    Scene sError = new Scene(ErrorVBox, 200, 100);
    errorConverStage.initStyle(StageStyle.UNDECORATED);
    errorConverStage.initModality(Modality.APPLICATION_MODAL);
    errorConverStage.setScene(sError);
    errorConverStage.show();
    }
      
    
}
