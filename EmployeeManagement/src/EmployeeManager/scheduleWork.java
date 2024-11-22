/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EmployeeManager;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * 
 */
public class scheduleWork implements Serializable{
    private String nameSubject,date;
    
    public scheduleWork() {
    }

    public scheduleWork(String nameSubject, String date) {
        this.nameSubject = nameSubject;
        this.date = date;
    }

    public String getNameSubject() {
        return nameSubject;
    }

    public void setNameSubject(String nameSubject) {
        this.nameSubject = nameSubject;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    
    
    
}
