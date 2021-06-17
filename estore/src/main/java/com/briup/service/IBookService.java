package com.briup.service;

import com.briup.bean.Book;

import java.util.List;

public interface IBookService {
	//查询所有书籍信息
	List<Book> findAllBooks();
	//通过书籍ID查询书籍信息
	Book findBookById(Integer id);
	//通过类型ID查询书籍信息
	List<Book> findAllBookByCategory(Integer id);
}
