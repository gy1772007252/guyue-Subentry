package com.briup.web.listener;

import com.briup.bean.Category;
import com.briup.config.AllConfig;
import com.briup.exception.ServiceException;
import com.briup.service.ICategoryService;
import com.briup.service.impl.ICategoryServiceImpl;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.util.List;

@WebListener
public class CategoryListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        ICategoryService iCategoryService = new AnnotationConfigApplicationContext(AllConfig.class).getBean(ICategoryServiceImpl.class);
        try {
            List<Category> allCategorys = iCategoryService.findAllCategorys();
            servletContextEvent.getServletContext().setAttribute("Categorys", allCategorys);
        } catch (ServiceException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {

    }

}
