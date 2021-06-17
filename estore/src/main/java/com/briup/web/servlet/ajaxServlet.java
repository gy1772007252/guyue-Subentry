package com.briup.web.servlet;

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
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/ajax")
public class ajaxServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ApplicationContext ac = new AnnotationConfigApplicationContext(AllConfig.class);
        ICustomerServiceImpl customerService = ac.getBean(ICustomerServiceImpl.class);
        String username = request.getParameter("username");

        PrintWriter pw = response.getWriter();
        try {
            customerService.findCustomerByName(username);
            pw.println("用户名可用");
        } catch (ServiceException e) {
            pw.println(e.getMessage());
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
