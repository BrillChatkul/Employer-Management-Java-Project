package EmployeeManager;


import java.awt.Rectangle;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import static javafx.scene.text.Font.font;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Callback;
import javafx.util.converter.IntegerStringConverter;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author billc
 */
public class Admin{
    private TableView employeeData = new TableView();
    private ArrayList<Employee> EmployeeInput;
    private ObservableList<Employee> datatable = null;
    private TableView reportData = new TableView();
    private ObservableList<Report> reportdtable= null;
    
    public Admin(Stage loginStage) throws Exception {
        Stage stage = new Stage();
        ///////////////////////////////////////LEFT MENU///////////////////////////////
        /////////////////////ADMINISTRATOR LOGO///////////////////
        Label topic = new Label("ADMINISTRATOR");
        topic.setTextFill(Color.web("#ffffff"));
        topic.setFont(font("Arial",30));topic.setStyle("-fx-font-weight: bold");
        BorderPane AdminLogoPane = new BorderPane();
        AdminLogoPane.setMinSize(300, 150);
        AdminLogoPane.setCenter(topic);
        AdminLogoPane.setStyle("-fx-background-color: #273545");
        
        Image iconImage = new Image(new FileInputStream("Icon.png"));
        ImageView logo = new ImageView(iconImage);
        logo.setFitHeight(200);logo.setFitWidth(200);
        logo.setX(120);
        logo.setY(78);
        ///////////////////BUTTON MENU//////////////////////////////
        Button employeeViewButton = new Button("EmployeeList");
        employeeViewButton.setMinSize(280, 50);
        employeeViewButton.setAlignment(Pos.CENTER_LEFT);
        employeeViewButton.setFont(font("Arial",20));
        employeeViewButton.setTextFill(Color.web("#fcfeff"));
        employeeViewButton.setStyle("-fx-background-color: #586d85");
        buttonEffect(employeeViewButton);
        
        
        Button addNewEmployeeButton = new Button("Add new employee");
        addNewEmployeeButton.setMinSize(280, 50);
        addNewEmployeeButton.setAlignment(Pos.CENTER_LEFT);
        addNewEmployeeButton.setFont(font("Arial",20));
        addNewEmployeeButton.setTextFill(Color.web("#fcfeff"));
        addNewEmployeeButton.setStyle("-fx-background-color: #586d85");
        buttonEffect(addNewEmployeeButton);
        
        Button HistoryButton = new Button("History");
        HistoryButton.setMinSize(280, 50);
        HistoryButton.setAlignment(Pos.CENTER_LEFT);
        HistoryButton.setFont(font("Arial",20));
        HistoryButton.setTextFill(Color.web("#fcfeff"));
        HistoryButton.setStyle("-fx-background-color: #586d85");
        buttonEffect(HistoryButton);
        
        Button logOut = new Button("Logout");
        logOut.setMinSize(280, 50);
        logOut.setAlignment(Pos.CENTER_LEFT);
        logOut.setFont(font("Arial",20));
        logOut.setTextFill(Color.web("#fcfeff"));
        logOut.setStyle("-fx-background-color: #586d85");
        buttonEffect(logOut);
        
        GridPane buttonMenu = new GridPane();
        buttonMenu.setPadding(new Insets(10, 10, 10, 10));
        buttonMenu.add(employeeViewButton, 0, 0);
        buttonMenu.add(addNewEmployeeButton, 0, 1);
        buttonMenu.add(HistoryButton, 0, 2);
        buttonMenu.add(logOut, 0, 3);
        
        
        BorderPane leftPane = new BorderPane();
        leftPane.setMinSize(300, 720);
        leftPane.setStyle("-fx-background-color: #586d85");
        leftPane.setTop(AdminLogoPane);
        leftPane.setCenter(logo);
        leftPane.setBottom(buttonMenu);
        leftPane.setVisible(true);
        
        /////////////////////////////////////////////
        BorderPane mainObjectPane = new BorderPane();
        mainObjectPane.setMinSize(980, 720);
        
        BorderPane menubar = new BorderPane();
        StackPane topicStackPane = new StackPane();
        Label employeeTopicLabel = new Label("Employeelist");employeeTopicLabel.setTextFill(Color.web("#ffffff"));
        employeeTopicLabel.setFont(font("Arial",30));employeeTopicLabel.setStyle("-fx-font-weight: bold");
        Label historyTopicLabel = new Label("History");historyTopicLabel.setTextFill(Color.web("#ffffff"));
        historyTopicLabel.setFont(font("Arial",30));historyTopicLabel.setStyle("-fx-font-weight: bold");
        topicStackPane.getChildren().addAll(employeeTopicLabel,historyTopicLabel);
        topicStackPane.setPadding(new Insets(20, 20, 20, 20));topicStackPane.setAlignment(Pos.BASELINE_LEFT);
        menubar.setLeft(topicStackPane);
        menubar.setMinSize(980, 75);
        menubar.setStyle("-fx-background-color: #374454");
        
        mainObjectPane.setTop(menubar);
        
        //////////EmployeeMenu//////////////////////
        BorderPane employeeMenuBorderPane = EmployeeEditPane();

        
        //////////addNewEmployeeMenu////////////////
        
        
        /////////History/////////////////
        BorderPane historymenuBorderPane = CheckHistory();
        historymenuBorderPane.setMinSize(980, 645);
        
        
        
        StackPane MenuStackPane = new StackPane();
        MenuStackPane.getChildren().addAll(employeeMenuBorderPane,historymenuBorderPane);
        mainObjectPane.setCenter(MenuStackPane);
        historymenuBorderPane.setVisible(false);
        historyTopicLabel.setVisible(false);
        ///////////////DISPLAY/////////////////////////////
        BorderPane display = new BorderPane();
        display.setLeft(leftPane);
        display.setRight(mainObjectPane);
        
        
        ///////////active////////
        employeeViewButton.setOnAction((ActionEvent t) -> {
            employeeMenuBorderPane.setVisible(true);employeeTopicLabel.setVisible(true);
            if(historymenuBorderPane.isVisible()){historymenuBorderPane.setVisible(false);historyTopicLabel.setVisible(false);}
            System.out.println("employeeView");
        });
        addNewEmployeeButton.setOnAction((ActionEvent t) -> {
            try {
                Stage newE1 = newEmployee();
                if(historymenuBorderPane.isVisible()){historymenuBorderPane.setVisible(false);historyTopicLabel.setVisible(false);}
                employeeMenuBorderPane.setVisible(true);employeeTopicLabel.setVisible(true);
            } catch (IOException | ClassNotFoundException ex) {
                 
            }
        });
        HistoryButton.setOnAction((ActionEvent t) -> {
            if(employeeMenuBorderPane.isVisible()){employeeMenuBorderPane.setVisible(false);employeeTopicLabel.setVisible(false);}
            historymenuBorderPane.setVisible(true);historyTopicLabel.setVisible(true);
            System.out.println("HISTORY");
        });
        logOut.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent t) {
                try {
                    stage.close();
                    Login.einLogin = readingData();
                } catch (IOException | ClassNotFoundException ex) {
                }
                loginStage.show();
            }
        });
        
        Scene scene = new Scene(display,1280,720);
        stage.setScene(scene);
        
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.initStyle(StageStyle.UNDECORATED);
        stage.show();
    }
        
//    public static void main(String[] args) {
//        launch(args);
//    }
    private void buttonEffect(Button button){
        button.addEventHandler(MouseEvent.MOUSE_MOVED, (MouseEvent t) -> {
            button.setStyle("-fx-background-color: #f7fbff;");
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
    
    private BorderPane EmployeeEditPane() throws IOException, FileNotFoundException, ClassNotFoundException
    {
        BorderPane employeeMenuBorderPane = new BorderPane();
        employeeMenuBorderPane.setMinSize(980, 645);
        //employeeMenuBorderPane.setStyle("-fx-background-color: #42484a");
        //EmployeeData///////////
        //TableView employeeData = new TableView();
        employeeData.setEditable(true);
        EmployeeInput = readingData();
        datatable = FXCollections.observableArrayList(EmployeeInput);
        
        TableColumn nameCol = new TableColumn("Name");
        TableColumn surNameCol = new TableColumn("Surname");
        TableColumn vacancyCol = new TableColumn("Vacancy");
        TableColumn idCol = new TableColumn("ID");
        TableColumn<Employee, Void> editCo = new TableColumn();
        TableColumn<Employee, Void> deleteCo = new TableColumn();
                
        Callback<TableColumn<Employee, Void>, TableCell<Employee, Void>> cellFactoryEdit = new Callback<TableColumn<Employee, Void>, TableCell<Employee, Void>>()
        {
             @Override
            public TableCell<Employee, Void> call(final TableColumn<Employee, Void> param) {
                final TableCell<Employee, Void> cell = new TableCell<Employee, Void>() {

                    private final Button btn = new Button("Edit/View");

                    {
                        btn.setOnAction((ActionEvent event) -> {
                            Employee data = getTableView().getItems().get(getIndex());
                             Stage employeeEditStage = new Stage();
                             /////////////////topicPane//////////////
                             BorderPane topicEditBorderPane = new BorderPane();
                             Label IdText = new Label("EmployeeID : "+data.getIdEmployee());
                             IdText.setFont(font(20));IdText.setTextFill(Color.web("#ffffff"));IdText.setStyle("-fx-font-weight: bold");
                             topicEditBorderPane.setMinSize(600, 80);
                             topicEditBorderPane.setStyle("-fx-background-color: #273545");
                             topicEditBorderPane.setCenter(IdText);
                             ///////////////DataPane///////////////////
                             GridPane dataEditPane = new GridPane();
                             /*dataEditPane.setMinSize(600, 640);*/dataEditPane.setAlignment(Pos.TOP_CENTER);
                             dataEditPane.setPadding(new Insets(15, 15, 15, 15));dataEditPane.setVgap(2);dataEditPane.setHgap(2);
                             
                             
                             Label emEdit2 = new Label("Name :");emEdit2.setStyle("-fx-font-weight: bold");
                             Label emEdit3 = new Label("SURNAME :");emEdit3.setStyle("-fx-font-weight: bold");
                             Label emEdit4 = new Label("GENDER :");emEdit4.setStyle("-fx-font-weight: bold");
                             Label emEdit5 = new Label("AGE :");emEdit5.setStyle("-fx-font-weight: bold");
                             Label emEdit6 = new Label("No.ID :");emEdit6.setStyle("-fx-font-weight: bold");
                             Label emEdit7 = new Label("ADDRESS :");emEdit7.setStyle("-fx-font-weight: bold");
                             Label emEdit8 = new Label("EMAIL :");emEdit8.setStyle("-fx-font-weight: bold");
                             Label emEdit9 = new Label("PHONE NUMBER :");emEdit9.setStyle("-fx-font-weight: bold");
                             Label emEdit10 = new Label("PASSWORD :");emEdit10.setStyle("-fx-font-weight: bold");
                             Label emEdit11 = new Label("SALARY :");emEdit11.setStyle("-fx-font-weight: bold");
                             Label emEdit13 = new Label("VACANCY :");emEdit13.setStyle("-fx-font-weight: bold");
                             VBox vBoxEditText = new VBox(emEdit2,emEdit3,emEdit4,emEdit5,emEdit6,emEdit7,emEdit8,emEdit9,emEdit10,emEdit13,emEdit11);
                             vBoxEditText.setAlignment(Pos.BASELINE_RIGHT); vBoxEditText.setSpacing(13); dataEditPane.add(vBoxEditText, 0, 0);
                             
                             
                             TextField tEdit2 = new TextField(); tEdit2.setText(data.getName());
                             TextField tEdit3 = new TextField(); tEdit3.setText(data.getSurname());
                             TextField tEdit4 = new TextField(); tEdit4.setText(data.getGender());
                             String ageConvert = Integer.toString(data.getAge());
                             TextField tEdit5 = new TextField(); tEdit5.setText(ageConvert);
                             TextField tEdit6 = new TextField(); tEdit6.setText(data.getIdNo());
                             TextField tEdit7 = new TextField(); tEdit7.setText(data.getAddress()); tEdit7.setMinWidth(200);
                             TextField tEdit8 = new TextField(); tEdit8.setText(data.getEmail());
                             TextField tEdit9 = new TextField(); tEdit9.setText(data.getPhoneNumber());
                             TextField tEdit10 = new TextField(); tEdit10.setText(data.getPassword());
                             String SalaryConvert = Double.toString(data.getSalary());
                             TextField tEdit11 = new TextField(); tEdit11.setText(SalaryConvert);
                             TextField tEdit13 = new TextField(); tEdit13.setText(data.getVacancy());
                             VBox vBoxEditField = new VBox(tEdit2,tEdit3,tEdit4,tEdit5,tEdit6,tEdit7,tEdit8,tEdit9,tEdit10,tEdit13,tEdit11);
                             vBoxEditField.setSpacing(5); dataEditPane.add(vBoxEditField, 1, 0);
                             
                             
                             ///////////////schWork////////////////
                             BorderPane buttomPane = new BorderPane();
                             VBox schWorkVBox = new VBox();
                             TableView schWorkTableView = new TableView();
                             schWorkTableView.setEditable(true);
                             ObservableList<scheduleWork> schworkData = FXCollections.observableArrayList(data.getScheduleWorks());
                             TableColumn SubjectCol = new TableColumn("Subject");
                             TableColumn dateCol = new TableColumn("date");
                             SubjectCol.setCellValueFactory(new PropertyValueFactory<scheduleWork,String>("nameSubject"));
                             dateCol.setCellValueFactory(new PropertyValueFactory<scheduleWork,String>("date"));
                             TableColumn<scheduleWork, Void> deleteWorkCol = new TableColumn();
                             Callback<TableColumn<scheduleWork, Void>, TableCell<scheduleWork, Void>> WorkDelete = new Callback<TableColumn<scheduleWork, Void>, TableCell<scheduleWork, Void>>()
                            {
                                @Override
                                public TableCell<scheduleWork, Void> call(final TableColumn<scheduleWork, Void> param) {
                                    final TableCell<scheduleWork, Void> cell = new TableCell<scheduleWork, Void>() {

                                        private final Button btn = new Button("Delete");
                                        {
                                            btn.setOnAction((ActionEvent event) -> {
                                            scheduleWork DeletingWork = getTableView().getItems().get(getIndex());
                                            Report r1 = new Report(LocalDate.now().toString(), 'E', "HR : Remove Work["+DeletingWork.getNameSubject()+"] From ["+data.getIdEmployee()+data.getName()+"].");
                                            schworkData.remove(getTableView().getItems().get(getIndex()));
                                            reportdtable.add(r1);
                                                try {
                                                    addReport(r1);
                                                } catch (IOException ex) {      
                                                } catch (ClassNotFoundException ex) {                                                  
                                                }
                                            data.removeScheduleWork(DeletingWork);
                                           
                                            try {
                                                writeData(EmployeeInput);
                                                } catch (IOException ex) {                     
                                                }   
                                            });
                                        }
 
                                        
                                @Override
                                public void updateItem(Void item, boolean empty) {
                                    super.updateItem(item, empty);
                                    if (empty) {
                                        setGraphic(null);
                                    } else {
                                        setGraphic(btn);
                                    }
                                    }
                                };
                                return cell;
                                }
                            };
                             deleteWorkCol.setCellFactory(WorkDelete);
                             SubjectCol.setMinWidth(200);SubjectCol.setStyle("-fx-alignment: CENTER");
                             dateCol.setMinWidth(100);dateCol.setStyle("-fx-alignment: CENTER");
                             deleteWorkCol.setMinWidth(95);deleteWorkCol.setStyle("-fx-alignment: CENTER");
                             schWorkTableView.setItems(schworkData);
                             schWorkTableView.getColumns().addAll(SubjectCol,dateCol,deleteWorkCol);
                             schWorkTableView.setMaxSize(400,100);
                             
                             TextField searchWork = new TextField();
                             searchWork.setFont(font("Arial",15));
                             searchWork.setMaxWidth(200);
                             searchWork.setPromptText("Search Subject");
                            searchWork.textProperty().addListener(new InvalidationListener() {
                            @Override
                            public void invalidated(Observable o) {
                            if(searchWork.textProperty().get().isEmpty())
                            {
                                schWorkTableView.setItems(schworkData);
                            }
                            else
                            {
                                ObservableList<scheduleWork> tableWorkItems = FXCollections.observableArrayList();
                                ObservableList<TableColumn<scheduleWork, ?>> cols = schWorkTableView.getColumns();

                                for(int i=0; i<schworkData.size(); i++) {
                                TableColumn col = cols.get(0);
                                String cellValue = col.getCellData(schworkData.get(i)).toString();
                                cellValue = cellValue.toLowerCase();
                                if(cellValue.contains(searchWork.textProperty().get().toLowerCase())) {
                                    tableWorkItems.add(schworkData.get(i));
                                }                        
                    
                            }
                            schWorkTableView.setItems(tableWorkItems);}
                
                            }});
                             
                             schWorkVBox.getChildren().addAll(searchWork,schWorkTableView);
                             schWorkVBox.setSpacing(10);
                             schWorkVBox.setAlignment(Pos.CENTER);
                             
                             HBox workHBox = new HBox();
                             Label emEdit12 = new Label("Subject");
                             TextField tEdit12 = new TextField();tEdit12.setPromptText("Name");
                             DatePicker dateP = new DatePicker();dateP.setPromptText("dd/mm/yyyy");
                             Button buWork = new Button("ADD WORK");dateP.getEditor().setDisable(true);
                             buWork.setOnAction((ActionEvent saveWork) -> {
                                 try{
                                 LocalDate iconvert = dateP.getValue(); 
                                 scheduleWork addingWork = new scheduleWork(tEdit12.textProperty().get(),iconvert.toString());
                                 schworkData.add(addingWork);
                                 data.setScheduleWork(addingWork);
                                 writeData(EmployeeInput);
                                 Report r1 = new Report(LocalDate.now().toString(), 'E', "HR : Add Work["+addingWork.getNameSubject()+"] to ["+data.getIdEmployee()+data.getName()+"].");
                                 reportdtable.add(r1);
                                 addReport(r1);
                                 }
                                 catch(Exception e){
                                     Stage errorConverStage = new Stage();
                                        VBox ErrorVBox = new VBox();ErrorVBox.setStyle("-fx-background-color: #273545");
                                        Label ErrorMessageLabel = new Label("Error:Invalid variables");ErrorMessageLabel.setTextFill(Color.web("#ffffff"));
                                        Button okErrorButton = new Button("Ok");
                                        okErrorButton.setOnAction((ActionEvent errorActionEvent) ->{errorConverStage.close();employeeEditStage.close();});
                                        ErrorVBox.getChildren().addAll(ErrorMessageLabel,okErrorButton);ErrorVBox.setSpacing(10);ErrorVBox.setAlignment(Pos.CENTER);
                                        Scene sError = new Scene(ErrorVBox, 200, 100);
                                        errorConverStage.initStyle(StageStyle.UNDECORATED);
                                        errorConverStage.initModality(Modality.APPLICATION_MODAL);
                                        errorConverStage.setScene(sError);
                                        errorConverStage.show();
                                 }
                                 schWorkTableView.refresh();
                                 dateP.getEditor().clear();
                                 tEdit12.clear();
                                 
                             });
                             workHBox.getChildren().addAll(emEdit12,tEdit12,new Label("Due Date"),dateP,buWork);
                             workHBox.setAlignment(Pos.CENTER);
                             workHBox.setSpacing(10);
                             workHBox.setPadding(new Insets(12, 12, 12, 12));
                             
                             VBox v1 = new VBox();
                             v1.getChildren().addAll(dataEditPane,schWorkVBox,workHBox);
                             
                             HBox buttonChoice = new HBox();
                             buttonChoice.setAlignment(Pos.BASELINE_RIGHT);
                             Button applyButton = new Button("APPLY");
                             applyButton.setOnAction(new EventHandler<ActionEvent>() {
                                @Override
                                public void handle(ActionEvent save) {
                                    try{
                                        getTableView().getItems().get(getIndex()).setName(tEdit2.textProperty().get());
                                        getTableView().getItems().get(getIndex()).setSurname(tEdit3.textProperty().get());
                                        getTableView().getItems().get(getIndex()).setGender(tEdit4.textProperty().get());
                                        int ageConvert = Integer.parseInt(tEdit5.textProperty().get());
                                        getTableView().getItems().get(getIndex()).setAge(ageConvert);
                                        getTableView().getItems().get(getIndex()).setIdNo(tEdit6.textProperty().get());
                                        getTableView().getItems().get(getIndex()).setAddress(tEdit7.textProperty().get());
                                        getTableView().getItems().get(getIndex()).setEmail(tEdit8.textProperty().get());
                                        getTableView().getItems().get(getIndex()).setPhoneNumber(tEdit9.textProperty().get());
                                        getTableView().getItems().get(getIndex()).setPassword(tEdit10.textProperty().get());
                                        double SalaryConvery = Double.parseDouble(tEdit11.textProperty().get());
                                        getTableView().getItems().get(getIndex()).setSalary(SalaryConvery);
                                        getTableView().getItems().get(getIndex()).setVacancy(tEdit13.textProperty().get());
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
                                        writeData(EmployeeInput);employeeData.refresh();employeeEditStage.close();
                                        Report r1 = new Report(LocalDate.now().toString(), 'E', "HR : ["+data.getIdEmployee()+data.getName()+"] has been updated!");
                                        reportdtable.add(r1);addReport(r1);
                                    } catch (Exception e) {
                                    }

                                }
                            });
                             Button cancelButton = new Button("CANCEL");
                             cancelButton.setOnAction((ActionEvent cancel) -> {employeeEditStage.close();});
                             buttonChoice.getChildren().addAll(applyButton,cancelButton);
                             buttonChoice.setPadding(new Insets(15, 15, 15, 15));buttonChoice.setSpacing(15);
                             
                             ////////////display///////////////////
                             BorderPane displayEdit = new BorderPane();
                             displayEdit.setTop(topicEditBorderPane);
                             displayEdit.setCenter(v1);
                             displayEdit.setBottom(buttonChoice);
                             
                             Scene sceneEdit = new Scene(displayEdit,600,720);
                             employeeEditStage.setScene(sceneEdit);
                             employeeEditStage.initModality(Modality.APPLICATION_MODAL);
                             employeeEditStage.initStyle(StageStyle.UNDECORATED);
                             employeeEditStage.show();
                        });
                    }

                    @Override
                    public void updateItem(Void item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(null);
                        } else {
                            setGraphic(btn);
                        }
                    }
                };
                return cell;
            }
        };
              
        Callback<TableColumn<Employee, Void>, TableCell<Employee, Void>> cellFactoryDelete = new Callback<TableColumn<Employee, Void>, TableCell<Employee, Void>>()
        {
             @Override
            public TableCell<Employee, Void> call(final TableColumn<Employee, Void> param) {
                final TableCell<Employee, Void> cell = new TableCell<Employee, Void>() {

                    private final Button btn = new Button("Delete Account");

                    {
                        btn.setOnAction((ActionEvent event) -> {
                             Stage errorConverStage = new Stage();
                VBox ErrorVBox = new VBox(); ErrorVBox.setStyle("-fx-background-color: #273545");
                Label ErrorMessageLabel = new Label("Delete Confirm");ErrorMessageLabel.setTextFill(Color.web("#ffffff"));
                Button okErrorButton = new Button("Ok");
                                Button CancelButton = new Button("Cancel");
                okErrorButton.setOnAction((ActionEvent errorActionEvent) ->{
                    errorConverStage.close();
                Employee data = getTableView().getItems().get(getIndex());
                            datatable.remove(getTableView().getItems().get(getIndex()));
                            EmployeeInput.remove(data);
                            try {
                                writeData(EmployeeInput);
                                Report r1 = new Report(LocalDate.now().toString(), 'A', "HR : ["+data.getIdEmployee()+data.getName()+"] has been resigned.");
                                reportdtable.add(r1);addReport(r1);
                            } catch (IOException ex) {                     
                            } catch (ClassNotFoundException ex) {
                            }
                });
                CancelButton.setOnAction((ActionEvent errorActionEvent) ->{
                errorConverStage.close();
                });
                ErrorVBox.getChildren().addAll(ErrorMessageLabel,okErrorButton,CancelButton);ErrorVBox.setSpacing(10);ErrorVBox.setAlignment(Pos.CENTER);
                Scene sError = new Scene(ErrorVBox, 200, 100);
                errorConverStage.setScene(sError);
                errorConverStage.initStyle(StageStyle.UNDECORATED);
                errorConverStage.initModality(Modality.APPLICATION_MODAL);
                errorConverStage.show();});
//                            Employee data = getTableView().getItems().get(getIndex());
//                            datatable.remove(getTableView().getItems().get(getIndex()));
//                            EmployeeInput.remove(data);
//                            try {
//                                writeData(EmployeeInput);
//                                Report r1 = new Report(LocalDate.now().toString(), 'A', "HR : ["+data.getIdEmployee()+data.getName()+"] has been resigned.");
//                                reportdtable.add(r1);addReport(r1);
//                            } catch (IOException ex) {                     
//                            } catch (ClassNotFoundException ex) {
//                            }});
                    }

                    @Override
                    public void updateItem(Void item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(null);
                        } else {
                            setGraphic(btn);
                        }
                    }
                };
                return cell;
            }
        };
        editCo.setCellFactory(cellFactoryEdit);
        deleteCo.setCellFactory(cellFactoryDelete);
                   
        nameCol.setCellValueFactory(new PropertyValueFactory<Employee,String>("name"));
        nameCol.setCellFactory(TextFieldTableCell.forTableColumn());
        nameCol.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Employee, String>>() {
                @Override
                public void handle(TableColumn.CellEditEvent<Employee, String> t){
                    ((Employee) t.getTableView().getItems().get(t.getTablePosition().getRow())).setName(t.getNewValue());
                    try {
                        writeData(EmployeeInput);
                    } catch (IOException ex) {
                        //Logger.getLogger(TestGUI.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        );
                
        surNameCol.setCellValueFactory(new PropertyValueFactory<Employee,String>("surname"));
        surNameCol.setCellFactory(TextFieldTableCell.forTableColumn());
        surNameCol.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Employee, String>>() {
                @Override
                public void handle(TableColumn.CellEditEvent<Employee, String> t){
                    ((Employee) t.getTableView().getItems().get(t.getTablePosition().getRow())).setSurname(t.getNewValue());
                    try {
                        writeData(EmployeeInput);
                    } catch (IOException ex) {
                        //Logger.getLogger(TestGUI.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        );
        idCol.setCellValueFactory(new PropertyValueFactory<Employee,String>("idEmployee"));
        idCol.setCellFactory(TextFieldTableCell.forTableColumn());
        idCol.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Employee, String>>() {
                @Override
                public void handle(TableColumn.CellEditEvent<Employee, String> t){
                    ((Employee) t.getTableView().getItems().get(t.getTablePosition().getRow())).setIdEmployee(t.getNewValue());
                    try {
                        writeData(EmployeeInput);
                    } catch (IOException ex) {
                        //Logger.getLogger(TestGUI.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                
            }
        );      
      
        vacancyCol.setCellValueFactory(new PropertyValueFactory<Employee,String>("vacancy"));
        vacancyCol.setCellFactory(TextFieldTableCell.forTableColumn());
        vacancyCol.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Employee, String>>() {
                @Override
                public void handle(TableColumn.CellEditEvent<Employee, String> t){
                    ((Employee) t.getTableView().getItems().get(t.getTablePosition().getRow())).setVacancy(t.getNewValue());
                    try {
                        writeData(EmployeeInput);
                    } catch (IOException ex) {
                        //Logger.getLogger(TestGUI.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        );
                
        employeeData.setItems(datatable);
        employeeData.getColumns().addAll(idCol,nameCol, surNameCol, vacancyCol,editCo,deleteCo);
        employeeData.setMaxSize(960,600);
        nameCol.setMinWidth(160);
        nameCol.setStyle("-fx-alignment: CENTER;-fx-font-size:12px;");
        surNameCol.setMinWidth(160);
        surNameCol.setStyle("-fx-alignment: CENTER;-fx-font-size:12px;");
        vacancyCol.setMinWidth(160);
        vacancyCol.setStyle("-fx-alignment: CENTER;-fx-font-size:12px;");
        idCol.setMinWidth(160);
        idCol.setStyle("-fx-alignment: CENTER;-fx-font-size:14px;");
        editCo.setMinWidth(150);
        editCo.setStyle("-fx-alignment: CENTER;");
        deleteCo.setMinWidth(150);
        deleteCo.setStyle("-fx-alignment: CENTER;");
        
        
        Label topicEmployee = new Label("EmployeeList");
        topicEmployee.setFont(font("Arial",30));
        Label topicEmployee1 = new Label("Search ID");
        topicEmployee1.setFont(font("Arial",15));
        TextField search = new TextField();
        search.setFont(font("Arial",15));
        search.textProperty().addListener(new InvalidationListener() {
            @Override
            public void invalidated(Observable o) {
                if(search.textProperty().get().isEmpty())
                {
                    employeeData.setItems(datatable);
                }
                else
                {
                ObservableList<Employee> tableItems = FXCollections.observableArrayList();
                ObservableList<TableColumn<Employee, ?>> cols = employeeData.getColumns();

                for(int i=0; i<datatable.size(); i++) {
                    TableColumn col = cols.get(0);
                    String cellValue = col.getCellData(datatable.get(i)).toString();
                    cellValue = cellValue.toLowerCase();
                    if(cellValue.contains(search.textProperty().get().toLowerCase())) {
                        tableItems.add(datatable.get(i));
                    }                        
                    
                }
                employeeData.setItems(tableItems);}
                
            }
        });
        HBox employeeTop = new HBox();
        employeeTop.getChildren().addAll(topicEmployee1,search);
        employeeTop.setPadding(new Insets(0, 0, 10, 0));
        employeeTop.setSpacing(10);
        employeeTop.setAlignment(Pos.CENTER_LEFT);
        employeeMenuBorderPane.setTop(employeeTop);
        employeeMenuBorderPane.setCenter(employeeData);
        employeeMenuBorderPane.setPadding(new Insets(10, 20, 20, 20));
        return employeeMenuBorderPane;
    }
    private Stage newEmployee() throws IOException, FileNotFoundException, ClassNotFoundException, ClassNotFoundException
    {
            Stage newEmployeeStage = new Stage();
            EmployeeInput = readingData();
                
            /////////////////topicPane//////////////
            BorderPane topicNewBorderPane = new BorderPane();
            topicNewBorderPane.setMinSize(600, 80);
            Label topicNewLabel = new Label("ADD NEW EMPLOYEE"); topicNewLabel.setStyle("-fx-font-weight: bold");
            topicNewLabel.setTextFill(Color.web("#ffffff"));topicNewLabel.setFont(font("Arial", 30));
            topicNewBorderPane.setCenter(topicNewLabel);
            topicNewBorderPane.setStyle("-fx-background-color: #273545");
            ///////////////DataPane///////////////////
            GridPane dataNewPane = new GridPane();
            /*dataEditPane.setMinSize(600, 640);*/dataNewPane.setAlignment(Pos.TOP_CENTER);
            dataNewPane.setPadding(new Insets(15, 15, 15, 15));dataNewPane.setVgap(2);dataNewPane.setHgap(2);
                             
            Label emNew1 = new Label("ID EMPLOYEE :"); emNew1.setStyle("-fx-font-weight: bold");
            Label emNew2 = new Label("Name :");emNew2.setStyle("-fx-font-weight: bold");
            Label emNew3 = new Label("SURNAME :");emNew3.setStyle("-fx-font-weight: bold");
            Label emNew4 = new Label("GENDER :");emNew4.setStyle("-fx-font-weight: bold");
            Label emNew5 = new Label("AGE :");emNew5.setStyle("-fx-font-weight: bold");
            Label emNew6 = new Label("No.ID :");emNew6.setStyle("-fx-font-weight: bold");
            Label emNew7 = new Label("ADDRESS :");emNew7.setStyle("-fx-font-weight: bold");
            Label emNew8 = new Label("EMAIL :");emNew8.setStyle("-fx-font-weight: bold");
            Label emNew9 = new Label("PHONE NUMBER :");emNew9.setStyle("-fx-font-weight: bold");
            Label emNew10 = new Label("PASSWORD :");emNew10.setStyle("-fx-font-weight: bold");
            Label emNew11 = new Label("SALARY :");emNew11.setStyle("-fx-font-weight: bold");
            Label emNew13 = new Label("VACANCY :");emNew13.setStyle("-fx-font-weight: bold");
            VBox vBoxEditText = new VBox(emNew1,emNew2,emNew3,emNew4,emNew5,emNew6,emNew7,emNew8,emNew9,emNew10,emNew13,emNew11);
            vBoxEditText.setAlignment(Pos.BASELINE_RIGHT); vBoxEditText.setSpacing(13); dataNewPane.add(vBoxEditText, 0, 0);
                             
            TextField tNew1 = new TextField();
            tNew1.textProperty().addListener(new InvalidationListener() {
                @Override
                public void invalidated(Observable o) {
                    for (int i = 0; i < EmployeeInput.size(); i++) {
            if(tNew1.textProperty().get().equals(EmployeeInput.get(i).getIdEmployee())){dataIsExist(tNew1);break;}}}
            });
            
            TextField tNew2 = new TextField();
            TextField tNew3 = new TextField();
            TextField tNew4 = new TextField();
            TextField tNew5 = new TextField();
            TextField tNew6 = new TextField();
            tNew6.textProperty().addListener(new InvalidationListener() {
                @Override
                public void invalidated(Observable o) {
                    for (int i = 0; i < EmployeeInput.size(); i++) {
            if(tNew6.textProperty().get().equals(EmployeeInput.get(i).getIdEmployee())){dataIsExist(tNew6);break;}}}
            });
            TextField tNew7 = new TextField();tNew7.setMinWidth(200);
            TextField tNew8 = new TextField();
            TextField tNew9 = new TextField();
            TextField tNew10 = new TextField();
            TextField tNew11 = new TextField();
            TextField tNew13 = new TextField();
            VBox vBoxNewField = new VBox(tNew1,tNew2,tNew3,tNew4,tNew5,tNew6,tNew7,tNew8,tNew9,tNew10,tNew13,tNew11);
            vBoxNewField.setSpacing(5); dataNewPane.add(vBoxNewField, 1, 0);
            
            HBox newButtonChoice = new HBox();
            newButtonChoice.setAlignment(Pos.BASELINE_RIGHT);
            Button applyButton = new Button("APPLY");
            applyButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent save) {
                
                try{
                  if(tNew1.textProperty().get().isEmpty()||tNew2.textProperty().get().isEmpty()||tNew3.textProperty().get().isEmpty()||tNew4.textProperty().get().isEmpty()||
                    tNew5.textProperty().get().isEmpty()||tNew6.textProperty().get().isEmpty()||
                    tNew7.textProperty().get().isEmpty()||tNew8.textProperty().get().isEmpty()||
                    tNew9.textProperty().get().isEmpty()||tNew10.textProperty().get().isEmpty()||
                    tNew11.textProperty().get().isEmpty()||tNew13.textProperty().get().isEmpty()){
                 throw new NumberFormatException();
                 }
                int ageConvert = Integer.parseInt(tNew5.textProperty().get());
                double SalaryConvery = Double.parseDouble(tNew11.textProperty().get());
                Employee e1 = new Employee(tNew1.textProperty().get(),
                        tNew2.textProperty().get(), tNew3.textProperty().get(),
                        tNew4.textProperty().get(),tNew6.textProperty().get(),
                        tNew7.textProperty().get(),tNew8.textProperty().get(),
                        tNew9.textProperty().get(),tNew13.textProperty().get(),tNew10.textProperty().get(), ageConvert,0,SalaryConvery);
                EmployeeInput.add(e1);
                datatable.add(e1);
            } catch(NumberFormatException e){
                Stage errorConverStage = new Stage();
                VBox ErrorVBox = new VBox(); ErrorVBox.setStyle("-fx-background-color: #273545");
                Label ErrorMessageLabel = new Label("Invalid variables");ErrorMessageLabel.setTextFill(Color.web("#ffffff"));
                Button okErrorButton = new Button("Ok");
                okErrorButton.setOnAction((ActionEvent errorActionEvent) ->{errorConverStage.close();newEmployeeStage.close();});
                ErrorVBox.getChildren().addAll(ErrorMessageLabel,okErrorButton);ErrorVBox.setSpacing(10);ErrorVBox.setAlignment(Pos.CENTER);
                Scene sError = new Scene(ErrorVBox, 200, 100);
                errorConverStage.setScene(sError);
                errorConverStage.initStyle(StageStyle.UNDECORATED);
                errorConverStage.initModality(Modality.APPLICATION_MODAL);
                errorConverStage.show();
            }
            try {
                    writeData(EmployeeInput);newEmployeeStage.close();
                    Report r1 = new Report(LocalDate.now().toString(), 'A', "HR : Add new Employee["+tNew1.textProperty().get()+tNew2.textProperty().get()+"].");
                    reportdtable.add(r1);addReport(r1);
                    
                } catch (Exception e) {
                }
            }
            });
            Button cancelButton = new Button("CANCEL");
            cancelButton.setOnAction((ActionEvent cancel) -> {newEmployeeStage.close();});
            newButtonChoice.getChildren().addAll(applyButton,cancelButton);
            newButtonChoice.setPadding(new Insets(15, 15, 15, 15));newButtonChoice.setSpacing(15);
            
            BorderPane displayEdit = new BorderPane();
            displayEdit.setTop(topicNewBorderPane);
            displayEdit.setCenter(dataNewPane);
            displayEdit.setBottom(newButtonChoice);
                             
            Scene sceneEdit = new Scene(displayEdit,600,520);
            newEmployeeStage.setScene(sceneEdit);
            newEmployeeStage.initStyle(StageStyle.UNDECORATED);
            newEmployeeStage.initModality(Modality.APPLICATION_MODAL);
            newEmployeeStage.show();
            return newEmployeeStage;
                             
    }
   private void dataIsExist(TextField t)
   {
       Stage errorStage = new Stage();
       VBox ErrorVBox = new VBox();ErrorVBox.setStyle("-fx-background-color: #273545");
       Label ErrorMessageLabel = new Label("Data has already used");ErrorMessageLabel.setTextFill(Color.web("#ffffff"));
       Button okErrorButton = new Button("Ok");
       ErrorVBox.getChildren().addAll(ErrorMessageLabel,okErrorButton);ErrorVBox.setSpacing(10);ErrorVBox.setAlignment(Pos.CENTER);
       Scene sError = new Scene(ErrorVBox, 200, 100);
       errorStage.setScene(sError);
       errorStage.initStyle(StageStyle.UNDECORATED);
       errorStage.initModality(Modality.APPLICATION_MODAL);
       errorStage.show();
       okErrorButton.setOnAction((ActionEvent errorActionEvent) ->{errorStage.close();t.clear();});
   }
   private BorderPane CheckHistory() throws IOException, FileNotFoundException, ClassNotFoundException
   {
       BorderPane display = new BorderPane();
       VBox v1 = new VBox();
       DatePicker f1 = new DatePicker();f1.setMinWidth(200);f1.autosize();f1.setPromptText("Search date dd/mm/yyyy");
       Button clButton = new Button("Clear Date");
       clButton.setOnAction((ActionEvent e)->{
       f1.getEditor().clear();reportData.setItems(reportdtable);});
       HBox h1 = new HBox();h1.getChildren().addAll(f1,clButton);
       h1.setSpacing(10);h1.setAlignment(Pos.CENTER);
       f1.getEditor().setDisable(true);
       f1.valueProperty().addListener(new InvalidationListener() {
          
                            
                            @Override
                            public void invalidated(Observable o) {
                                
                              LocalDate d1 = f1.getValue();
                              System.out.println(d1.toString());
                              ObservableList<Report> tableWorkItems = FXCollections.observableArrayList();
                                ObservableList<TableColumn<Report, ?>> cols = reportData.getColumns();

                                for(int i=0; i<reportdtable.size(); i++) {
                                TableColumn col = cols.get(0);
                                String cellValue = col.getCellData(reportdtable.get(i)).toString();
                                if(cellValue.contains(d1.toString())) {
                                    
                                    tableWorkItems.add(reportdtable.get(i));
                                }                        

                            reportData.setItems(tableWorkItems);}
                                }});
       ArrayList<Report> reading = readingReport();
       reportdtable = FXCollections.observableArrayList(reading);
       reportData.setMinHeight(525); reportData.setMaxWidth(803);
       TableColumn dateColumn = new TableColumn("DATE");dateColumn.setMinWidth(200);dateColumn.setStyle("-fx-alignment: CENTER;-fx-font-size:14px;");
       TableColumn reportColumn = new TableColumn("Information");reportColumn.setMinWidth(600);reportColumn.setStyle("-fx-font-size:14px;");
       dateColumn.setCellValueFactory(new PropertyValueFactory<Report,String>("date"));
       reportColumn.setCellValueFactory(new PropertyValueFactory<Report,String>("report"));
       reportData.setItems(reportdtable);
       reportData.getColumns().addAll(dateColumn,reportColumn);
       v1.getChildren().addAll(h1,reportData);
       v1.setSpacing(25);v1.setPadding(new Insets(20, 20, 20, 20));
       v1.setAlignment(Pos.CENTER);
       display.setCenter(v1);
       
       return display;
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
                a.get(i).removeScheduleWork(w);
                break;
            }
        }
        writeData(a);
    }
}
