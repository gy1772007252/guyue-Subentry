package com.briup.service.impl;

import com.briup.bean.Book;
import com.briup.config.AllConfig;
import com.briup.dao.BookMapper;
import com.briup.service.IBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IBookServiceImpl implements IBookService {

    @Autowired
    private BookMapper bookMapper;

    private void initCustomerMapper() {
        bookMapper = new AnnotationConfigApplicationContext(AllConfig.class).getBean(BookMapper.class);
    }

    @Override
    public List<Book> findAllBooks() {
        return null;
    }

    @Override
    public Book findBookById(Integer id) {
        initCustomerMapper();
        Book book = bookMapper.findBookById(id);
        return book;
    }

    @Override
    public List<Book> findAllBookByCategory(Integer id) {
        initCustomerMapper();
        List<Book> bookByCategory = bookMapper.findAllBookByCategory(id);
        return bookByCategory;
    }

}
