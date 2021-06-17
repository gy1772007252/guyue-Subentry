package com.briup.service.impl;

import com.briup.bean.Customer;
import com.briup.config.AllConfig;
import com.briup.dao.CustomerMapper;
import com.briup.service.ICustomerService;
import com.briup.exception.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Service;

@Service
public class ICustomerServiceImpl implements ICustomerService {

    @Autowired
    private CustomerMapper customerMapper;

    private void initCustomerMapper() {
        customerMapper = new AnnotationConfigApplicationContext(AllConfig.class).getBean(CustomerMapper.class);
    }

    @Override
    public void register(Customer customer) throws ServiceException {
        String name = customer.getName();
        if (name.equals("")) {
            throw new ServiceException("用户名不能为空!");
        }
        String password = customer.getPassword();
        if (password.equals("")) {
            throw new ServiceException("用户密码不能为空!");
        }
        if (password.length() < 6 || password.length() > 10) {
            throw new ServiceException("用户密码长度为 6~10!");
        }
        String zipCode = customer.getZipCode();
        if (zipCode.equals("")) {
            throw new ServiceException("邮编不能为空!");
        } else if (zipCode.length() != 6 ) {
            throw new ServiceException("邮编长度为 6!");
        }
        String address = customer.getAddress();
        if (address.equals("")) {
            throw new ServiceException("地址不能为空!");
        }
        String telephone = customer.getTelephone();
        if (telephone.equals("")) {
            throw new ServiceException("电话不能为空!");
        } else if (telephone.length() != 11) {
            throw new ServiceException("电话长度为11!");
        }
        String email = customer.getEmail();
        if (email.equals("")) {
            throw new ServiceException("邮箱不能为空!");
        }
        initCustomerMapper();
        customerMapper.saveCustomer(customer);
    }

    @Override
    public Customer findCustomerByName(String name) throws ServiceException {
        initCustomerMapper();
        Customer customer = customerMapper.findCustomerByName(name);
        if (customer != null) {
            throw new ServiceException("用户名已存在");
        }
        return customer;
    }

    @Override
    public Customer login(String name, String password) throws ServiceException {
        initCustomerMapper();
        if (name == "") {
            throw new ServiceException("用户名不能为空!");
        }
        Customer login = customerMapper.findCustomerByName(name);
        if (login == null) {
            throw new ServiceException("用户名不存在!");
        } else if (!login.getPassword().equals(password)) {
            throw new ServiceException("用户密码错误!");
        }
        return login;
    }
}
