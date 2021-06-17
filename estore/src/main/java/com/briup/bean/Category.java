package com.briup.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * 分类
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Category {

	private Integer id;
	private String name;
	private String description;
	private List<Category> categories;

}
