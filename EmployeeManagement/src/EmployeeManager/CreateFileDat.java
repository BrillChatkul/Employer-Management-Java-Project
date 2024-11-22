/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EmployeeManager;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

/**
 *
 * @author billc
 */
public class CreateFileDat {
    public static void main(String[] args) throws FileNotFoundException, IOException {
//        ArrayList<Report> eout = new ArrayList<>();
//        eout.add(new Report("2020-04-17", 'E', "Data has been recieved!"));
//        eout.add(new Report("2020-04-17", 'E', "Starting Rec"));
//        ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("ReportData.dat"));
//        out.writeInt(eout.size());
//        for(int i=0;i<eout.size();i++)
//        {
//            out.writeObject(eout.get(i)); 
//        }
//        out.close();  
//    }
    ArrayList<Employee> eout = new ArrayList<>();  //
    eout.add(new Employee("62010193","Chatkul", "Rattanarithikul","HR"));
    eout.add(new Employee("62010175","Chawakorn", "Laokaew","Manager"));
    eout.add(new Employee("62010142","Chatdanai", "SingTo","Data"));
    eout.add(new Employee("62010711","Pubodin", "Tieanthong","GUImanager"));
    eout.add(new Employee("62010152","Chanawee", "Chanechatchawan","GUImanager"));
    ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("EmployeeData.dat"));
    out.writeInt(eout.size());
    for(int i=0;i<eout.size();i++)
        {
            out.writeObject(eout.get(i)); 
        }
    out.close();  
    }
        
}
