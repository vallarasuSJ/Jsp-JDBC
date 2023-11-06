package com.codewithkvJDBC.controller;

import com.codewithkvJDBC.Do.DbConnection;
import com.codewithkvJDBC.dao.UserDao;
import com.codewithkvJDBC.model.User;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class AuthController extends HttpServlet {

    private final UserDao userDao;

    public AuthController() {
      userDao=new UserDao();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String username=req.getParameter("username");
        String password=req.getParameter("password");
        User loggedInuser=userDao.loginUser(username,password);
        if(loggedInuser!=null){
            HttpSession httpSession=req.getSession();
            httpSession.setAttribute("id",loggedInuser.getId());
            RequestDispatcher requestDispatcher=req.getRequestDispatcher("Home.jsp");
            requestDispatcher.forward(req,resp);
        }
        else{
            req.setAttribute("error",true);
            RequestDispatcher rd = req.getRequestDispatcher("Register.jsp");
            rd.forward(req, resp);
        }
    }
}

