package com.briup.web.servlet;

import com.briup.bean.Book;
import com.briup.bean.ShopCar;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/deleteShopCarBook")
public class deleteShopCarBookServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");
        HttpSession session = request.getSession();
        ShopCar shopCar = (ShopCar) session.getAttribute("shopCar");
        shopCar.removeOrderLine(Integer.parseInt(id));
        response.sendRedirect(request.getContextPath() + "/shopCar.jsp");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
