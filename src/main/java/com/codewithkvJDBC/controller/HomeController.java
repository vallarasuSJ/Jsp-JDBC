package com.codewithkvJDBC.controller;

import com.codewithkvJDBC.dao.TodoDao;
import com.codewithkvJDBC.model.Todo;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class HomeController extends HttpServlet {

    private final TodoDao todoDao;

    public HomeController() {
        todoDao = new TodoDao();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher requestDispatcher=req.getRequestDispatcher("Home.jsp");
        String id=req.getParameter("id");
        if(id!=null){
            todoDao.deleteTodo(Integer.parseInt(id));
        }
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher requestDispatcher=req.getRequestDispatcher("Home.jsp");
        String userId=req.getSession().getAttribute("id").toString();

        String item=req.getParameter("todo");


        if(item!=null && item.trim().length()>0){
            todoDao.addTodo(item,Integer.parseInt(userId));
        }

        List<Todo> todos=todoDao.selectAllTodos(Integer.parseInt(userId));
        req.setAttribute("todos",todos);

        requestDispatcher.forward(req,resp);
    }

}