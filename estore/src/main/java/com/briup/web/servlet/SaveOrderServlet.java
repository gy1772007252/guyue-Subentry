package com.briup.web.servlet;

import com.briup.bean.Customer;
import com.briup.bean.OrderForm;
import com.briup.bean.OrderLine;
import com.briup.bean.ShopCar;
import com.briup.config.AllConfig;
import com.briup.exception.ServiceException;
import com.briup.service.impl.IOrderFormServiceImpl;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Date;
import java.util.List;

@WebServlet("/SaveOrder")
public class SaveOrderServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Customer login = (Customer) session.getAttribute("login");
        ShopCar shopCar = (ShopCar) session.getAttribute("shopCar");
        IOrderFormServiceImpl iOrderFormService = new AnnotationConfigApplicationContext(AllConfig.class).getBean(IOrderFormServiceImpl.class);
        OrderForm orderForm = new AnnotationConfigApplicationContext(AllConfig.class).getBean(OrderForm.class);

        orderForm.setCustomer(login);
        orderForm.setOrderdate(new Date());
        orderForm.setCost(shopCar.getCost());
        orderForm.setOrderLines(shopCar.getOrderLines());
        try {
            iOrderFormService.saveOrderForm(orderForm);
            List<OrderForm> orderFormByCustomerId = iOrderFormService.findOrderFormByCustomerId(login.getId());
            session.setAttribute("OrderFormList", orderFormByCustomerId);
            response.sendRedirect(request.getContextPath() + "/user/orderlist.jsp");
        } catch (ServiceException e) {
            e.printStackTrace();
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
