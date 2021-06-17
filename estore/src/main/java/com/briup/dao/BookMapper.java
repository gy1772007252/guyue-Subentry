package com.briup.dao;

import com.briup.bean.Book;
import com.briup.bean.Category;
import org.apache.ibatis.annotations.One;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;

public interface BookMapper {

	@Select("select * from es_category where id = ${id}")
	Category findByCategory(Integer id);

	List<Book> findAllBooks();

	@Select("select * from es_book where id = '${id}'")
	@Results({
			@Result(column = "pubDate", property = "pubDate", javaType = Date.class),
			@Result(column = "category_id", property = "category", javaType = Category.class,
					one = @One(select = "com.briup.dao.BookMapper.findByCategory"))
	})
	Book findBookById(Integer id);

	@Select("select * from es_book where category_id = '${id}'")
	@Results({
			@Result(column = "pubDate", property = "pubDate", javaType = Date.class),
			@Result(column = "category_id", property = "category", javaType = Category.class,
					one = @One(select = "com.briup.dao.BookMapper.findByCategory"))
	})
	List<Book> findAllBookByCategory(Integer id);

}
