package com.main;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Task {

    private String taskName;
    private String description;
    private Date dueDate;
    private TaskStatus status;
    private int id;

    public Task() {
    }


    Task(String taskName, String description, Date dueDate, TaskStatus status, int id) {
        this.taskName = taskName;
        this.description = description;
        this.dueDate = dueDate;
        this.status = status;
        this.id = id;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public void setDescription(String description) {
        this.description = description;
    }


    public void setStatus(TaskStatus status) {
        this.status = status;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getDueDate() {
        return dueDate;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }

    public String getTaskName() {
        return taskName;
    }

    public String getDescription() {
        return description;
    }

    public TaskStatus getStatus() {
        return status;
    }

    public int getId() {
        return id;
    }

    public String toString() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
        String result = "\nTask ID :" + id + "\nTask Name :" + taskName + "\nTask Description :" + description + "\nTask Due Date : " +
                simpleDateFormat.format(dueDate) + "\nTask Status : " + status + "\n";
        return result;
    }


}
