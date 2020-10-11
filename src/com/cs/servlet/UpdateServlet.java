package com.cs.servlet;

import com.cs.entity.User;
import com.cs.service.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class UpdateServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        System.out.println("--------->##############");
        UserServiceImpl usi = new UserServiceImpl();
        String id = request.getParameter("id");
        int ids = Integer.parseInt(id);
        String name = request.getParameter("username");
        String pass = request.getParameter("password");
        String sex = request.getParameter("sex");
        User us = new User();
        us.setId(ids);
        us.setUsername(name);
        us.setPassword(pass);
        us.setSex(sex);
        try {
            boolean  flag = usi.updateUser(us);
            if(flag){
                response.sendRedirect("index.jsp");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
       this.doPost(request,response);
    }
}
