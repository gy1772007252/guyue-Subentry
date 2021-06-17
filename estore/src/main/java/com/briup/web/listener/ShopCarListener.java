package com.briup.web.listener;

import com.briup.bean.ShopCar;
import com.briup.config.AllConfig;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

@WebListener
public class ShopCarListener implements HttpSessionListener {
    @Override
    public void sessionCreated(HttpSessionEvent httpSessionEvent) {
        //注意spring单例问题
        ApplicationContext ac = new AnnotationConfigApplicationContext(AllConfig.class);
        ShopCar shopCar = ac.getBean(ShopCar.class);
        HttpSession session = httpSessionEvent.getSession();
        if (session.getAttribute("shopCar") == null) {
            session.setAttribute("shopCar", shopCar);
        }
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent httpSessionEvent) {

    }
}
