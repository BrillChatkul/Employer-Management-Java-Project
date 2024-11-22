/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EmployeeManager;

import java.io.Serializable;
import java.util.ArrayList;

/**
 *
 * 
 */
public class Employee implements Serializable {
    private String idEmployee,name,surname,gender,idNo,address,email,phoneNumber,vacancy,password;
    private int age,WorkCount;
    private double Salary;
    private ArrayList<scheduleWork> workList = new ArrayList<>();
    
    public Employee() {
    }
    public Employee(String idEmployee,String name, String surname,String vacancy) {
        this.idEmployee = idEmployee;
        this.name = name;
        this.surname = surname;
        this.vacancy = vacancy;
        WorkCount = 0;
    }

    public Employee(String idEmployee, String name, String surname, String gender, String idNo, String address, String email, String phoneNumber, String vacancy, String password, int age, int WorkCount, double Salary) {
        this.idEmployee = idEmployee;
        this.name = name;
        this.surname = surname;
        this.gender = gender;
        this.idNo = idNo;
        this.address = address;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.vacancy = vacancy;
        this.password = password;
        this.age = age;
        this.WorkCount = WorkCount;
        this.Salary = Salary;
    }

    public int getWorkCount() {
        return WorkCount;
    }

    public void setWorkCount(int WorkCount) {
        this.WorkCount = WorkCount;
    }
    
    
    

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    
    public String getIdEmployee() {
        return idEmployee;
    }

    public void setIdEmployee(String idEmployee) {
        this.idEmployee = idEmployee;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getIdNo() {
        return idNo;
    }

    public void setIdNo(String idNo) {
        this.idNo = idNo;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getVacancy() {
        return vacancy;
    }

    public void setVacancy(String vacancy) {
        this.vacancy = vacancy;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public double getSalary() {
        return Salary;
    }

    public void setSalary(double Salary) {
        this.Salary = Salary;
    }
    
    public void setScheduleWork(scheduleWork swork){
        workList.add(swork);
        WorkCount = workList.size();
    }
    public void removeScheduleWork(scheduleWork swork)
    {
        workList.remove(swork);
        WorkCount = workList.size();
    }
    public ArrayList<scheduleWork> getScheduleWorks(){
        return workList;
    }

    
    
}
