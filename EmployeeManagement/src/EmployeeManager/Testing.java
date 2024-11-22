/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EmployeeManager;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

/**
 *
 * @author billc
 */
public class Testing extends Application{

    @Override
    public void start(Stage stage) throws Exception {
        
        BorderPane b1 = new BorderPane();
        Button adminButton = new Button("Admin");
        b1.setCenter(adminButton);
        adminButton.setOnAction(((t) -> {
            try {
                Admin hrAdmin = new Admin(stage);
                stage.close();
            } catch (Exception ex) {
               
            }}));
        
        Button employeebt = new Button("employee");
        b1.setBottom(employeebt);
        employeebt.setOnAction(((t) -> {
            try {
                ArrayList<Employee> e1 = readingData();
                EmployeeStage emStage = new EmployeeStage(e1.get(0),stage);
                stage.close();
            } catch (Exception ex) {
               
            }}));
        
        Scene scene = new Scene(b1, 400, 100);
        stage.setScene(scene);
        stage.show();
        
    }
    public static void main(String[] args) {
        launch(args);
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
    
}
