package com.briup.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * 书籍类
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Book {

	private Integer id;
	private String name;
	private Double price;
	private String author;
	private String publisher;
	private Date pubDate;
	private String description;
	private String image;
	private Category category;

}
