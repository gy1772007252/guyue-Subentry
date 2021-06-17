package com.briup.service;

import com.briup.bean.Category;
import com.briup.exception.ServiceException;

import java.util.List;

public interface ICategoryService {
	//查找所有分类信息
	List<Category> findAllCategorys() throws ServiceException;
}
