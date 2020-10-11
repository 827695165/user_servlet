package com.cs.servlet;

import com.cs.entity.User;
import com.cs.service.impl.UserServiceImpl;
import com.cs.util.PageSupport;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class UserServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        String comm=request.getParameter("comm");
        if(comm.equals("list")) {
            String pageNO = request.getParameter("indexno");
            if (pageNO == null || pageNO == "") {
                pageNO = "1";
            }
            int pno = Integer.parseInt(pageNO);
            UserServiceImpl usi = new UserServiceImpl();
            PageSupport
                    ps = new PageSupport();
            ps.setCurrentPageNo(pno);
            ps.setPageSize(3);
            try {
                ps.setTotalCount(usi.findByCountUser());
                List<User> list = usi.findByPageUserInfo(ps.getCurrentPageNo(), ps.getPageSize());
                if (list.size() > 0 && list != null) {
                    request.getSession().setAttribute("lists", list);
                    request.getSession().setAttribute("ps", ps);
                    response.sendRedirect("list.jsp");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        if(comm.equals("query")){
            UserServiceImpl usi = new UserServiceImpl();
            String id = request.getParameter("id");
            int ids = Integer.parseInt(id);
            User us = null;
            try {
                us = usi.findByUserInfo(ids);
                if(us != null){
                    request.setAttribute("us",us);
                    request.getRequestDispatcher("query.jsp").forward(request,response);
                }else {

                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        if(comm.equals("querys")){
            UserServiceImpl usi = new UserServiceImpl();
            String id = request.getParameter("id");
            int ids = Integer.parseInt(id);
            User us = null;
            try {
                us = usi.findByUserInfo(ids);
                if(us != null){
                    request.setAttribute("us",us);
                    request.getRequestDispatcher("upd.jsp").forward(request,response);
                }else {

                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }


        if(comm.equals("del")){
            //得到用户修改的数据
            UserServiceImpl usi=new UserServiceImpl();
            String id=request.getParameter("id");
            int ids=Integer.parseInt(id);
            try {
                boolean flag = usi.delUser(ids);
                if(flag){

                    response.sendRedirect("index.jsp");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

        }


    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      this.doPost(request,response);
    }
}
