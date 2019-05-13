package com.oaec.hw.servlet;

import com.oaec.hw.dao.UserDao;
import com.oaec.hw.entity.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/register")
public class RegisterServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String username = request.getParameter("username");
        String password = request.getParameter("password");

        UserDao userDao = new UserDao();

        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        user.setSex("保密");

        int id = userDao.create(user);

        if(id>0){
            System.out.println("注册成功！");
        }else{
            System.out.println("注册失败！");
        }

    }
}
