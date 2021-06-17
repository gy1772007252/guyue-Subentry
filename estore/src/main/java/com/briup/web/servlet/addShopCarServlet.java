package com.briup.web.servlet;

import com.briup.bean.Book;
import com.briup.bean.ShopCar;
import com.briup.config.AllConfig;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/addShopCar")
public class addShopCarServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String num = request.getParameter("num");
        HttpSession session = request.getSession();
        ShopCar shopCar = (ShopCar) session.getAttribute("shopCar");
        Book book = (Book) session.getAttribute("book");
        if (shopCar == null) {
            shopCar = new AnnotationConfigApplicationContext(AllConfig.class).getBean(ShopCar.class);
            session.setAttribute("shopCar", shopCar);
        }
        shopCar.add(book, Integer.valueOf(num));
        response.sendRedirect(request.getContextPath() + "/shopCar.jsp");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
