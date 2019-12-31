package com.main;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class TaskManager {

    //  InMemoryTaskRepository inMemory = new InMemoryTaskRepository();
    //   TaskFileRepository repository = new TaskFileRepository();

    TaskDataBaseRepository repository = new TaskDataBaseRepository();
    Random random = new Random();

    public void addTask(String taskName, String description, String dueDate, String status) throws ParseException {
        repository.addTask(taskName, description, toDate(dueDate), toStatus(status), random.nextInt(100000));
    }

    TaskStatus toStatus(String st)
    {
        TaskStatus status = TaskStatus.valueOf(st);
        return  status;
    }

    Date toDate(String date) throws ParseException {
        Date dt = new SimpleDateFormat("dd/MM/yyyy").parse(date);
        return dt;
    }

    public List<Task> listTasks() {
        return repository.listTasks();
    }

    public Task searchTask(String key) {
        return repository.searchTask(key);
    }

    public Task searchByTaskID(int key) {
        return repository.searchByTaskID(key);
    }


    public List<Task> listByStatus(String status) {
        return repository.listByStatus(status);
    }

    public int deleteTask(String name) {
        return repository.deleteTask(name);
    }

    public List<Task> getPendingTasks() {
        return repository.getPendingTasks();
    }

    public List<Task> getTodayTasks() {
        return repository.getTodayTasks();
    }

    public int updateTask(String task, TaskStatus status) {
        return repository.updateTask(task, status);
    }


    public int getTaskCount() {
        return 0;
    }


}
