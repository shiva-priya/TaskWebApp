package com.main;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TaskDataBaseRepository implements TaskRepository {

    Connection con;
    Statement stmt;


    public TaskDataBaseRepository() {
        // System.out.println("ENtered COnstructor");
        establishConnection();
    }

    public void establishConnection() {
        // System.out.println("Establishing-Connection");
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/taskdb", "taskuser", "taskuser");
            //   System.out.println("ConnectionObj"+con);
            stmt = con.createStatement();
            //   System.out.println("StatementObj"+stmt);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void addTask(String taskName, String description, Date dueDate, TaskStatus status, int id) {
        try {
            Statement stmt = con.createStatement();
            String query = "insert into task values('" + taskName + "','" + description
                    + "','" + dateToString(dueDate, "yyyy/MM/dd") + "','" + status
                    + "'," + id + ")";
            int rs = stmt.executeUpdate(query);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    @Override
    public List<Task> listTasks() {
        List<Task> tasks = new ArrayList<Task>();
        try {
            System.out.println("Gone!");
            if (stmt == null) {
                //     System.out.println("Statement obj is null");
            }
            ResultSet rs = stmt.executeQuery("select * from task");
            while (rs.next())
                tasks.add(createTask(rs));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return tasks;
    }

    @Override
    public Task searchTask(String key) {
        Task task = new Task();
        task = null;
        try {
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("select * from task where taskName='" + key + "'");

            if (rs.next()) {
                task = createTask(rs);
            }

        } catch (SQLException e) {
            return task;
        }
        return task;
    }

    @Override
    public Task searchByTaskID(int key) {
        Task task = new Task();
        task = null;
        try {
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("select * from task where id=" + key);

            if (rs.next()) {
                task = createTask(rs);
            }

        } catch (SQLException e) {
            return task;
        }
        return task;
    }

    @Override
    public List<Task> listByStatus(String status) {
        List<Task> tasks = new ArrayList<Task>();
        try {
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("select * from task where status='" + status + "'");
            while (rs.next()) {
                Task task = new Task();
                task = createTask(rs);
                tasks.add(task);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        if (tasks.size() <= 0)
            return null;
        else
            return tasks;
    }

    @Override
    public int deleteTask(String name) {
        int totalTasksBeforeDelete = getTotalTasks();

        int totalTasksAfterDelete;
        try {
            Statement stmt = con.createStatement();
            stmt.executeUpdate("delete from task where taskName='" + name + "'");

            totalTasksAfterDelete = getTotalTasks();
            if (totalTasksBeforeDelete == totalTasksAfterDelete + 1)
                return 1;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public List<Task> getPendingTasks() {
        List<Task> tasks = new ArrayList<Task>();
        try {
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("select * from task where status='IN_PROGRESS' or status='INITIAL' order by dueDate asc");
            while (rs.next()) {
                Task task = new Task();
                task = createTask(rs);
                tasks.add(task);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return tasks;
    }

    @Override
    public List<Task> getTodayTasks() {
        List<Task> tasks = new ArrayList<Task>();
        String currentDate = dateToString(new Date(), "yyyy/MM/dd");
        try {
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("select * from task where dueDate='" + currentDate + "'");
            while (rs.next()) {
                Task task = new Task();
                task = createTask(rs);
                tasks.add(task);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return tasks;
    }

    @Override
    public int updateTask(String task, TaskStatus status) {
        try {
            Statement stmt = con.createStatement();
            int res = stmt.executeUpdate("update task set status='" + status + "' where taskName='" + task + "'");
            return res;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public Task updateFullTask(String taskName, String description, Date dueDate, TaskStatus status, int id) throws SQLException {
        return null;
    }

    public int getTotalTasks() {

        int totalTasks = 0;
        try {
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("select count(*) from task");
            if (rs.next()) {
                totalTasks = rs.getInt(1);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return totalTasks;
    }

    public Task createTask(ResultSet resultSet) {
        Task task = new Task();
        try {
            task.setTaskName(resultSet.getString(1));
            task.setDescription(resultSet.getString(2));
            task.setDueDate(resultSet.getDate(3));
            task.setStatus(TaskStatus.valueOf(resultSet.getString(4)));
            task.setId(resultSet.getInt(5));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return task;
    }

    public String dateToString(Date date, String format) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);
        return simpleDateFormat.format(date);
    }
}