package com.briup.web.servlet;

import com.briup.bean.OrderLine;
import com.briup.config.AllConfig;
import com.briup.exception.ServiceException;
import com.briup.service.impl.IOrderFormServiceImpl;
import com.briup.service.impl.IOrderLineServiceImpl;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collection;
import java.util.List;

@WebServlet("/SaveOrderLine")
public class SaveOrderLineServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");
        String cost = request.getParameter("cost");
        IOrderLineServiceImpl iOrderLineService = new AnnotationConfigApplicationContext(AllConfig.class).getBean(IOrderLineServiceImpl.class);
        try {
            List<OrderLine> orderLineByOrderId = iOrderLineService.findOrderLineByOrderId(Integer.valueOf(id));
            request.getSession().setAttribute("orderLineList", orderLineByOrderId);
            request.getSession().setAttribute("cost", cost);
        } catch (ServiceException e) {
            e.printStackTrace();
        }
        response.sendRedirect(request.getContextPath() + "/user/orderdetail.jsp");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
