package com.wyy.servlet.user;

import com.wyy.pojo.User;
import com.wyy.service.user.UserServiceImpl;
import com.wyy.util.Constants;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class LoginServlet extends HttpServlet {

    //Servlet:控制层，调用业务层代码
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("LoginServlet--start...");

        //获取前端用户输入的用户名和密码
        String userCode = req.getParameter("userCode");
        String userPassword = req.getParameter("userPassword");

        //和数据库中的用户名和密码进行对比，调用业务层
        UserServiceImpl userService = new UserServiceImpl();
        User user = userService.login(userCode, userPassword);//这里已经把登录账户和对应的密码给查出来了

        if (user != null&&userPassword.equals(user.getUserPassword())) {//用户名和密码正确，可以登陆
            //将用户的信息放到session中
            req.getSession().setAttribute(Constants.USER_SESSION, user);
            //登录成功跳转到内部主页
            resp.sendRedirect("jsp/frame.jsp");
        } else {
            //查无此人，无法登录，转发回登录页面，顺带提示它，用户名或者密码错误

            req.setAttribute("error", "用户名或者密码不正确！");
            req.getRequestDispatcher("login.jsp").forward(req, resp);
        }

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
