package com.briup.config;

import com.briup.bean.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan("com.briup.bean")
public class BeanConfig {

    @Bean
    public Book book() {
        return new Book();
    }
    @Bean
    public Category category() {
        return new Category();
    }
    @Bean
    public Customer customer() {
        return new Customer();
    }
    @Bean
    public OrderForm orderForm() {
        return new OrderForm();
    }
    @Bean
    public OrderLine orderLine() {
        return new OrderLine();
    }
    @Bean
    public ShopAddress shopAddress() {
        return new ShopAddress();
    }
    @Bean
    public ShopCar shopCar() {
        return new ShopCar();
    }

}
