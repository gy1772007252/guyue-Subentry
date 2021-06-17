package com.briup.web.servlet;

import com.briup.bean.Book;
import com.briup.config.AllConfig;
import com.briup.service.impl.IBookServiceImpl;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/BookById")
public class BookByIdServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        IBookServiceImpl iBookService = new AnnotationConfigApplicationContext(AllConfig.class).getBean(IBookServiceImpl.class);
        String idView = request.getParameter("idView");
        Book book = iBookService.findBookById(Integer.valueOf(idView));
        request.getSession().setAttribute("book", book);
        response.sendRedirect(request.getContextPath() + "/viewBook.jsp");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
