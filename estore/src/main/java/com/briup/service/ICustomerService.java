package com.briup.service;

import com.briup.bean.Customer;
import com.briup.exception.ServiceException;

public interface ICustomerService {

    //注册用户信息
    void register(Customer customer) throws ServiceException;
    //通过用户名字查找用户信息
    Customer findCustomerByName(String name) throws ServiceException;
    //根据用户名以及密码查看用户信息
    Customer login(String name,String password) throws ServiceException;

}
