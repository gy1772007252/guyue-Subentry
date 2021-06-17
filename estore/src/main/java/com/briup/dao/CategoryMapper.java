package com.briup.dao;

import com.briup.bean.Category;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface CategoryMapper {

	@Select("select id, name, description from es_category where parent_id = ${id}")
	Category findCategory(Integer id);

	@Select("select id, name, description from es_category where parent_id = 0")
	@Results({
			@Result(id = true, column = "id", property = "id"),
			@Result(property = "categories", column = "id",
					many = @Many(select = "com.briup.dao.CategoryMapper.findCategory"))
	})
	List<Category> findAllCategorys();

}
