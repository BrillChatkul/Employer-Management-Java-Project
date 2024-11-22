/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EmployeeManager;

import java.io.Serializable;

/**
 *
 * @author billc
 */
public class Report implements Serializable{
    private String date;
    private char type;       //Type E = login,editData A = add/delete Employee 
    private String report;

    public Report() {
    }

    public Report(String date, char type, String report) {
        this.date = date;
        this.type = type;
        this.report = report;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public char getType() {
        return type;
    }

    public void setType(char type) {
        this.type = type;
    }

    public String getReport() {
        return report;
    }

    public void setReport(String report) {
        this.report = report;
    }
    
}
