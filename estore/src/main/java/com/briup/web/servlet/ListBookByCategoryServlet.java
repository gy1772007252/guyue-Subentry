package com.briup.web.servlet;

import com.briup.bean.Book;
import com.briup.config.AllConfig;
import com.briup.service.impl.IBookServiceImpl;
import com.briup.service.impl.ICustomerServiceImpl;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/ListBookByCategory")
public class ListBookByCategoryServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        IBookServiceImpl iBookService = new AnnotationConfigApplicationContext(AllConfig.class).getBean(IBookServiceImpl.class);
        String idCate = request.getParameter("idCate");
        List<Book> bookByCategory = iBookService.findAllBookByCategory(Integer.valueOf(idCate));
        request.getSession().setAttribute("bookByCategory", bookByCategory);
        response.sendRedirect(request.getContextPath() + "/list.jsp");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
