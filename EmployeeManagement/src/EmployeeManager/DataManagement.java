package EmployeeManager;

import EmployeeManager.Employee;
import EmployeeManager.Report;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import static javafx.scene.text.Font.font;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Callback;
 
public class DataManagement {
 
    TableView table = new TableView();
    ObservableList<Employee> dataObservableList = FXCollections.observableArrayList();
    private ObservableList<Employee> datatable = null;
    private TableView reportData = new TableView();
    private ObservableList<Report> reportdtable= null;
    ObservableList<Report> AItemsList = null;
    long ngern = 0;
    
    public DataManagement(Stage loginStage) throws Exception {
        Stage stage = new Stage();
        
        ArrayList<Employee> dataArrayList = readingData();
        int countEmployee = dataArrayList.size();
        dataObservableList = FXCollections.observableArrayList(dataArrayList);
        
        for(int i=0 ; i<countEmployee ; i++){
            ngern += dataArrayList.get(i).getSalary();
        }
        
        Label CountEmployeeLabel = new Label("Employee: " + countEmployee);
        CountEmployeeLabel.setFont(font("Arial",20));
        CountEmployeeLabel.setTextFill(Color.web("#ffffff"));
        CountEmployeeLabel.setStyle("-fx-background-color: #586d85");
        CountEmployeeLabel.setPadding(new Insets(10, 10, 10, 10));
        
        Label CompanyExpenses = new Label("Company Expenses: " + ngern + " ฿");
        CompanyExpenses.setFont(font("Arial",20));
        CompanyExpenses.setTextFill(Color.web("#ffffff"));
        CompanyExpenses.setStyle("-fx-background-color: #586d85");
        CompanyExpenses.setPadding(new Insets(10, 10, 10, 10));
        
        Label OverallLabel = new Label("Overall");
        OverallLabel.setFont(font("Arial",30));
        OverallLabel.setTextFill(Color.web("#ffffff"));
        OverallLabel.setStyle("-fx-background-color: #F2F2F2");
        OverallLabel.setStyle("-fx-font-weight: bold");
        OverallLabel.setPadding(new Insets(10, 10, 10, 10));
        
        Button employeeListButton = new Button("Employee List");
        employeeListButton.setMinSize(280, 50);
        employeeListButton.setAlignment(Pos.CENTER_LEFT);
        employeeListButton.setFont(font("Arial",20));
        employeeListButton.setTextFill(Color.web("#fcfeff"));
        employeeListButton.setStyle("-fx-background-color: #586d85");
        buttonEffect(employeeListButton);
        
        Button reportListButton = new Button("Report List");
        reportListButton.setMinSize(280, 50);
        reportListButton.setAlignment(Pos.CENTER_LEFT);
        reportListButton.setFont(font("Arial",20));
        reportListButton.setTextFill(Color.web("#fcfeff"));
        reportListButton.setStyle("-fx-background-color: #586d85");
        buttonEffect(reportListButton);
        
        Button goToAdmin = new Button("Administrator");
        goToAdmin.setMinSize(280, 50);
        goToAdmin.setAlignment(Pos.CENTER_LEFT);
        goToAdmin.setFont(font("Arial",20));
        goToAdmin.setTextFill(Color.web("#fcfeff"));
        goToAdmin.setStyle("-fx-background-color: #586d85");
        buttonEffect(goToAdmin);
        
        Button logOut = new Button("Logout");
        logOut.setMinSize(280, 50);
        logOut.setAlignment(Pos.CENTER_LEFT);
        logOut.setFont(font("Arial",20));
        logOut.setTextFill(Color.web("#fcfeff"));
        logOut.setStyle("-fx-background-color: #586d85");
        buttonEffect(logOut);
        
        GridPane buttonMenu = new GridPane();
        buttonMenu.setPadding(new Insets(10, 10, 10, 10));
        buttonMenu.add(employeeListButton, 0, 0);
        buttonMenu.add(reportListButton, 0, 1);
        buttonMenu.add(logOut, 0, 2);
        
        BorderPane BottomPane = new BorderPane();
        BottomPane.setMinSize(980, 645);
        
        Label topic = new Label("MANAGER");
        topic.setTextFill(Color.web("#ffffff"));
        topic.setFont(font("Arial", 30));
        topic.setStyle("-fx-font-weight: bold");
        BorderPane ManagerLogoPane = new BorderPane();
        ManagerLogoPane.setMinSize(300, 150);
        ManagerLogoPane.setCenter(topic);
        ManagerLogoPane.setStyle("-fx-background-color: #273545");
        
        BorderPane CountEmployeePane = new BorderPane();
        CountEmployeePane.setMinSize(300, 50);
        CountEmployeePane.setLeft(CountEmployeeLabel);
        
        BorderPane SalaryExpenses = new BorderPane();
        SalaryExpenses.setMinSize(300, 50);
        SalaryExpenses.setLeft(CompanyExpenses);
        
        BorderPane OverallPane = new BorderPane();
        OverallPane.setMinSize(300, 50);
        OverallPane.setLeft(OverallLabel);
        
        BorderPane SpacePane = new BorderPane();
        SpacePane.setMinSize(300, 5);
        
        GridPane CECE = new GridPane();
        CECE.add(ManagerLogoPane, 0, 0);
        CECE.add(SpacePane, 0, 1);
        CECE.add(OverallPane, 0, 2);
        CECE.add(CountEmployeePane, 0, 3);
        CECE.add(SalaryExpenses, 0, 4);
        
        BorderPane leftPane = new BorderPane();
        leftPane.setMinSize(300, 720);
        leftPane.setStyle("-fx-background-color: #586d85");
        leftPane.setTop(CECE);
        leftPane.setBottom(buttonMenu);
        
        BorderPane mainObjectPane = new BorderPane();
        mainObjectPane.setMinSize(980, 720);
        
        BorderPane menuBar = new BorderPane();
        menuBar.setMinSize(980, 75);
        menuBar.setStyle("-fx-background-color: #374454");
        
        mainObjectPane.setTop(menuBar);
        
        StackPane topicStackPane = new StackPane();
        Label employeeTopicLabel = new Label("Employee List");
        employeeTopicLabel.setTextFill(Color.web("#ffffff"));
        employeeTopicLabel.setFont(font("Arial",30));
        employeeTopicLabel.setStyle("-fx-font-weight: bold");
        Label reportTopicLabel = new Label("Report List");
        reportTopicLabel.setTextFill(Color.web("#ffffff"));
        reportTopicLabel.setFont(font("Arial",30));
        reportTopicLabel.setStyle("-fx-font-weight: bold");
        topicStackPane.getChildren().addAll(employeeTopicLabel,reportTopicLabel);
        topicStackPane.setPadding(new Insets(20, 20, 20, 20));
        topicStackPane.setAlignment(Pos.BASELINE_LEFT);
        menuBar.setLeft(topicStackPane);
        menuBar.setMinSize(980, 75);
        menuBar.setStyle("-fx-background-color: #374454");
        
 
        BorderPane employeeMenuBorderPane = EmployeeViewPane();
        
        BorderPane reportMenuBorderPane = CheckHistory();
        reportMenuBorderPane.setMinSize(980, 645);
        
        StackPane MenuStackPane = new StackPane();
        MenuStackPane.getChildren().addAll(employeeMenuBorderPane, reportMenuBorderPane);
        mainObjectPane.setCenter(MenuStackPane);
        reportMenuBorderPane.setVisible(false);
        reportMenuBorderPane.setVisible(false);
        reportTopicLabel.setVisible(false);
        
        BorderPane display = new BorderPane();
        display.setLeft(leftPane);
        display.setCenter(mainObjectPane);
        
        employeeListButton.setOnAction((ActionEvent t) -> {
            if(reportMenuBorderPane.isVisible()){
                reportMenuBorderPane.setVisible(false);
                reportTopicLabel.setVisible(false);
            }
            employeeMenuBorderPane.setVisible(true);
            employeeTopicLabel.setVisible(true);
        });
        
        reportListButton.setOnAction((ActionEvent t) -> {
            if(employeeMenuBorderPane.isVisible()){
                employeeMenuBorderPane.setVisible(false);
                employeeTopicLabel.setVisible(false);
            }
            reportMenuBorderPane.setVisible(true);
            reportTopicLabel.setVisible(true);
        });
        
        logOut.setOnAction((ActionEvent t)->{
        stage.close();
        loginStage.show();
        });
        
        goToAdmin.setOnAction((ActionEvent t)->{
        stage.close();
            try {
                stage.close();
            } catch (Exception ex) {           
            }
        });
        
        Scene scene = new Scene(display, 1280, 720);
        stage.setScene(scene);
        
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.initStyle(StageStyle.UNDECORATED);
        stage.show();
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
    
    private BorderPane EmployeeViewPane() throws IOException, FileNotFoundException, ClassNotFoundException
    {
        BorderPane employeeMenuBorderPane = new BorderPane();
        employeeMenuBorderPane.setMinSize(980, 645);
        table.setEditable(true);
        ArrayList<Employee> ein = readingData();
        datatable = FXCollections.observableArrayList(ein);
        
        TableColumn idNoCol = new TableColumn("ID");
        idNoCol.setMinWidth(135);
        idNoCol.setCellValueFactory(new PropertyValueFactory<Employee, String>("idEmployee"));
        idNoCol.setStyle("-fx-alignment: CENTER;-fx-font-size:12px;");
                
        TableColumn NameCol = new TableColumn("Name");
        NameCol.setMinWidth(135);
        NameCol.setCellValueFactory(new PropertyValueFactory<Employee, String>("name"));
        NameCol.setStyle("-fx-alignment: CENTER;-fx-font-size:12px;");
 
        TableColumn SurNameCol = new TableColumn("Surname");
        SurNameCol.setMinWidth(135);
        SurNameCol.setCellValueFactory(new PropertyValueFactory<Employee, String>("surname"));
        SurNameCol.setStyle("-fx-alignment: CENTER;-fx-font-size:12px;");
 
        TableColumn vacancyCol = new TableColumn("Vacancy");
        vacancyCol.setMinWidth(135);
        vacancyCol.setCellValueFactory(new PropertyValueFactory<Employee, String>("vacancy"));
        vacancyCol.setStyle("-fx-alignment: CENTER;-fx-font-size:12px;");
        
        TableColumn salaryCol = new TableColumn("Salary");
        salaryCol.setMinWidth(135);
        salaryCol.setCellValueFactory(new PropertyValueFactory<Employee, String>("Salary"));
        salaryCol.setStyle("-fx-alignment: CENTER;-fx-font-size:12px;");
        
        TableColumn phoneNumberCol = new TableColumn("Phone Number");
        phoneNumberCol.setMinWidth(135);
        phoneNumberCol.setCellValueFactory(new PropertyValueFactory<Employee, String>("phoneNumber"));
        phoneNumberCol.setStyle("-fx-alignment: CENTER;-fx-font-size:12px;");
        
        TableColumn WorkCountList = new TableColumn("WorkList");
        WorkCountList.setMinWidth(135);
        WorkCountList.setCellValueFactory(new PropertyValueFactory<Employee,String>("WorkCount"));
        WorkCountList.setStyle("-fx-alignment: CENTER;-fx-font-size:12px;");
                
        table.setItems(datatable);
        table.getColumns().addAll(idNoCol,NameCol, SurNameCol, vacancyCol, salaryCol, phoneNumberCol,WorkCountList);
        table.setMaxSize(960,600);
        
        Label topicEmployee = new Label("EmployeeList");
        topicEmployee.setFont(font("Arial",30));
        Label topicEmployee1 = new Label("");
        topicEmployee1.setFont(font("Arial",15));
        TextField search = new TextField();
        search.setFont(font("Arial",15));
        ComboBox c1 = new ComboBox();
        c1.getItems().addAll("Search ID","Search Name","Search Vacancy");
        c1.setPromptText("Search ID");c1.setValue("Search ID");
        search.textProperty().addListener(new InvalidationListener() {
            @Override
            public void invalidated(Observable o) {
                if(search.textProperty().get().isEmpty())
                {
                    table.setItems(datatable);topicEmployee1.setText("");
                }
                else
                {
                ObservableList<Employee> tableItems = FXCollections.observableArrayList();
                ObservableList<TableColumn<Employee, ?>> cols = table.getColumns();
                
                if(c1.getValue().toString().equals("Search ID")){
                for(int i=0; i<datatable.size(); i++) {
                    TableColumn col = cols.get(0);
                    String cellValue = col.getCellData(datatable.get(i)).toString();
                    cellValue = cellValue.toLowerCase();
                    if(cellValue.contains(search.textProperty().get().toLowerCase())) {
                        tableItems.add(datatable.get(i));
                    }                        
                }
                table.setItems(tableItems);topicEmployee1.setText("");}
                
                else if(c1.getValue().toString().equals("Search Name")){
                for(int i=0; i<datatable.size(); i++) {
                    TableColumn col = cols.get(1);
                    String cellValue = col.getCellData(datatable.get(i)).toString();
                    cellValue = cellValue.toLowerCase();
                    if(cellValue.contains(search.textProperty().get().toLowerCase())) {
                        tableItems.add(datatable.get(i));
                    }                        
                }
                table.setItems(tableItems);topicEmployee1.setText("");}
                else if(c1.getValue().toString().equals("Search Vacancy")){
                for(int i=0; i<datatable.size(); i++) {
                    TableColumn col = cols.get(3);
                    String cellValue = col.getCellData(datatable.get(i)).toString();
                    cellValue = cellValue.toLowerCase();
                    if(cellValue.contains(search.textProperty().get().toLowerCase())) {
                        tableItems.add(datatable.get(i));
                    }                        
                }
                table.setItems(tableItems);topicEmployee1.setText("Result :" + tableItems.size());}
                
                }
            }
        });
        
        
        HBox employeeTop = new HBox();
        employeeTop.getChildren().addAll(c1,search,topicEmployee1);
        employeeTop.setPadding(new Insets(0, 0, 10, 0));
        employeeTop.setSpacing(10);
        employeeTop.setAlignment(Pos.CENTER_LEFT);
        employeeMenuBorderPane.setTop(employeeTop);
        employeeMenuBorderPane.setCenter(table);
        employeeMenuBorderPane.setPadding(new Insets(10, 20, 20, 20));
        return employeeMenuBorderPane;
    }
    
    private BorderPane CheckHistory() throws IOException, FileNotFoundException, ClassNotFoundException
    {
       BorderPane display = new BorderPane();
       VBox v1 = new VBox();
       DatePicker f1 = new DatePicker();f1.setMinWidth(200);f1.autosize();f1.setPromptText("Search date dd/mm/yyyy");
       Button clButton = new Button("Clear Date");
       clButton.setOnAction((ActionEvent e)->{
       f1.getEditor().clear();reportData.setItems(AItemsList);});
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
                                if(cellValue.contains(d1.toString())&&reportdtable.get(i).getType()=='A') {
                                    
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
       AItemsList = FXCollections.observableArrayList(); 
       for (int i = 0; i < reportdtable.size(); i++) {
           if(reportdtable.get(i).getType()=='A')
           {
               AItemsList.add(reportdtable.get(i));
           }
        }
       reportData.setItems(AItemsList);
       reportData.getColumns().addAll(dateColumn,reportColumn);
       v1.getChildren().addAll(h1,reportData);
       v1.setSpacing(25);v1.setPadding(new Insets(20, 20, 20, 20));
       v1.setAlignment(Pos.CENTER);
       display.setCenter(v1);
       
       return display;
    }
}