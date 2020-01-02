package com.main;

import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class IdServlet extends HttpServlet {

    TaskManager manager = new TaskManager();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter out = resp.getWriter();
        int id = Integer.parseInt(req.getParameter("id"));
        Task result = manager.searchByTaskID(id);

        ObjectMapper mapper = new ObjectMapper();

        String jsonString = mapper.writeValueAsString(result);
        out.println(jsonString);

        resp.setStatus(201);
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter out = resp.getWriter();
        String task = req.getParameter("task");
        String status = req.getParameter("status");
        int res = manager.updateTask(task, status);
        if (res > 0) {
            out.println("updated  " + task);
        } else {
            out.println("NO SUCH DATA FOUND");
        }
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter out = resp.getWriter();
        String name = req.getParameter("name");
        int res = manager.deleteTask(name);
        if (res == 1) {
            out.println("DELETED " + name);
            //resp.setStatus(201);
        } else {
            out.println("NO SUCH DATA FOUND");
            resp.setStatus(404);
        }

    }
}
