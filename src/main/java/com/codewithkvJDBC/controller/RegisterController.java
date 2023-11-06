package com.codewithkvJDBC.controller;

import com.codewithkvJDBC.dao.RegisterDao;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;

public class RegisterController extends HttpServlet {
    private final RegisterDao registerDao;

    public RegisterController() {
        registerDao=new RegisterDao();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String username=req.getParameter("username");
        String password=req.getParameter("password");
        String confirmPassword=req.getParameter("confirmPassword");

        if(username!=null && password!=null && password.equals(confirmPassword) && confirmPassword!=null){
            registerDao.registerUser(username,password);
            RequestDispatcher rd=req.getRequestDispatcher("index.jsp");
            rd.forward(req,resp);
        }
        else{
            RequestDispatcher rx=req.getRequestDispatcher("Register.jsp");
            rx.forward(req,resp);
        }

    }
}
