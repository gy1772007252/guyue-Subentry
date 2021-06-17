package com.briup.web.servlet;

import com.briup.config.AllConfig;
import com.briup.exception.ServiceException;
import com.briup.service.impl.IOrderFormServiceImpl;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/deleteOrderForm")
public class deleteOrderFormServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");
        IOrderFormServiceImpl iOrderFormService = new AnnotationConfigApplicationContext(AllConfig.class).getBean(IOrderFormServiceImpl.class);
        try {
            iOrderFormService.deleteOrderFormById(Integer.valueOf(id));
            response.sendRedirect(request.getContextPath()+"/orderList");
        } catch (ServiceException e) {
            e.printStackTrace();
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
