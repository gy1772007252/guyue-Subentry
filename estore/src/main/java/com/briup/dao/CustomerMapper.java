package com.briup.dao;

import com.briup.bean.Customer;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerMapper {

    @Insert("insert into es_customer(name, password, zipCode, address, telephone, email) " +
            "values(#{name},#{password},#{zipCode},#{address},#{telephone},#{email})")
    void saveCustomer(Customer custome);

    @Select("select * from es_customer where name = '${name}'")
    @ResultType(Customer.class)
    Customer findCustomerByName(String name);

    @Select("select * from es_customer where id = '${id}'")
    @ResultType(Customer.class)
    Customer findCustomerById(Integer id);

}
