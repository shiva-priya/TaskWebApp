package com.main;

import java.util.*;

public class TaskManager {

    //  InMemoryTaskRepository inMemory = new InMemoryTaskRepository();
    //   TaskFileRepository repository = new TaskFileRepository();

    TaskDataBaseRepository repository = new TaskDataBaseRepository();
    Random random = new Random();

    public void addTask(String taskName, String description, Date dueDate, TaskStatus status) {
        repository.addTask(taskName, description, dueDate, status, random.nextInt(100000));
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
