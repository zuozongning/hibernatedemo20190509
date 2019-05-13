package com.oaec.hw.servlet;

import com.oaec.hw.dao.UserDao;
import com.oaec.hw.entity.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String username = request.getParameter("username");
        String password = request.getParameter("password");

        UserDao userDao = new UserDao();

        User user = userDao.findByUsername(username);
        if(user==null){
            System.out.println("登录失败！没有此用户！");
        }else{
            if(user.getPassword().equals(password)){
                System.out.println("登录成功！");
            }else{
                System.out.println("登录失败！密码不正确！");
            }
        }
    }
}
