package com.servlet;

import com.main.Task;
import com.main.TaskDataBaseRepository;
import org.json.JSONArray;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class TaskServlet extends HttpServlet {

    public TaskServlet(){}

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter out = resp.getWriter();
        System.out.println("Instantiating TaskDBR");
        TaskDataBaseRepository db = new TaskDataBaseRepository();
        List<Task> output = new ArrayList();
        output = db.listTasks();
        JSONArray ja = new JSONArray(output);
        out.println(ja);
    }
}
