package com.example.admin.week2assigment;

import java.util.Date;

/**
 * Created by Admin on 9/7/2017.
 */

public class Tasks {
    int ID;
    String Name;
    String DueDate;
    int Priorty;
    Boolean Done;

    public int getID() {
        return ID;
    }

    public Tasks(int id,String name, String dueDate, int priorty, Boolean done) {
        ID = id;
        Name = name;
        DueDate = dueDate;
        Priorty = priorty;
        Done = done;
    }

    public void setID(int ID) {
        this.ID = ID;

    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getDueDate() {
        return DueDate;
    }

    public void setDueDate(String dueDate) {
        DueDate = dueDate;
    }

    public int getPriorty() {
        return Priorty;
    }

    public void setPriorty(int priorty) {
        Priorty = priorty;
    }

    public Boolean getDone() {
        return Done;
    }

    public void setDone(Boolean done) {
        Done = done;
    }

    @Override
    public String toString() {
        return "ID: "+ ID + "          " + "Name: "+Name+"\n"+
                "Due Date: "+ DueDate+"          "+"Priority: "+Priorty+"\n"+
                "Done: "+ Done;

    }
}
