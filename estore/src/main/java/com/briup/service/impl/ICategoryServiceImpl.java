package com.briup.service.impl;

import com.briup.bean.Category;
import com.briup.config.AllConfig;
import com.briup.dao.CategoryMapper;
import com.briup.exception.ServiceException;
import com.briup.service.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ICategoryServiceImpl implements ICategoryService {

    @Autowired
    private CategoryMapper categoryMapper;

    private void initCustomerMapper() {
        categoryMapper = new AnnotationConfigApplicationContext(AllConfig.class).getBean(CategoryMapper.class);
    }

    @Override
    public List<Category> findAllCategorys() throws ServiceException {
        initCustomerMapper();
        List<Category> allCategorys = categoryMapper.findAllCategorys();
        return allCategorys;
    }

}
