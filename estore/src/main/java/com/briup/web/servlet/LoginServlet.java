package com.briup.web.servlet;

import com.briup.bean.Customer;
import com.briup.config.AllConfig;
import com.briup.exception.ServiceException;
import com.briup.service.impl.ICustomerServiceImpl;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("name");
        String password = request.getParameter("password");
        ICustomerServiceImpl iCustomerService = new AnnotationConfigApplicationContext(AllConfig.class).getBean(ICustomerServiceImpl.class);
        HttpSession session = request.getSession();
        try {
            Customer login = iCustomerService.login(name, password);
            //登陆成功
            session.setAttribute("login", login);
            response.sendRedirect(request.getContextPath() + "/index.jsp");
        } catch (ServiceException e) {
            //e.printStackTrace();
            request.setAttribute("error", e.getMessage());
            //登陆失败
            session.setAttribute("login", null);
            request.getRequestDispatcher("login.jsp").forward(request, response);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
