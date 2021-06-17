package com.briup.web.servlet;

import com.briup.bean.Customer;
import com.briup.bean.OrderForm;
import com.briup.config.AllConfig;
import com.briup.exception.ServiceException;
import com.briup.service.IOrderFormService;
import com.briup.service.impl.IOrderFormServiceImpl;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet("/orderList")
public class orderListServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Customer customer = (Customer)session.getAttribute("login");
        IOrderFormService iOrderFormService = new AnnotationConfigApplicationContext(AllConfig.class).getBean(IOrderFormServiceImpl.class);
        try {
            List<OrderForm> OrderFormList = iOrderFormService.findOrderFormByCustomerId(customer.getId());
            session.setAttribute("OrderFormList",OrderFormList);
            response.sendRedirect(request.getContextPath()+"/user/orderlist.jsp");
        } catch (ServiceException e) {
            e.printStackTrace();
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
