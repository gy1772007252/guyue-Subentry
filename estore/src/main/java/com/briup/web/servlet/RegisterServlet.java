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
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/register")
public class RegisterServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ApplicationContext ac = new AnnotationConfigApplicationContext(AllConfig.class);

        String name = request.getParameter("name");
        String password = request.getParameter("password");
        String zipCode = request.getParameter("zipCode");
        String address = request.getParameter("address");
        String telephone = request.getParameter("telephone");
        String email = request.getParameter("email");

        Customer customer = ac.getBean(Customer.class);
        customer.setName(name);
        customer.setPassword(password);
        customer.setZipCode(zipCode);
        customer.setAddress(address);
        customer.setTelephone(telephone);
        customer.setEmail(email);
        try {
            ICustomerServiceImpl customerService = ac.getBean(ICustomerServiceImpl.class);
            customerService.register(customer);
            response.sendRedirect(request.getContextPath() + "/login.jsp");
        } catch (ServiceException e) {
            //e.printStackTrace();
            request.setAttribute("error", e.getMessage());
            request.getRequestDispatcher("register.jsp").forward(request, response);
        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
