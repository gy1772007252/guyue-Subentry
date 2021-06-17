package com.briup.dao;

import com.briup.bean.Customer;
import com.briup.bean.OrderForm;
import org.apache.ibatis.annotations.*;

import java.sql.Date;
import java.util.List;

public interface OrderFormMapper {

	@Insert("insert into es_orderform(cost, orderDate, customer_id) " +
			"values(#{cost}, #{orderdate}, #{customer.id})")
	@Options(useGeneratedKeys = true, keyProperty = "id")
	void saveOrderForm(OrderForm of);

	@Select("select * from es_orderform " +
			"where id = '${id}'")
	@Results({
			@Result(id = true, column = "id", property = "id"),
			@Result(column = "cost", property = "cost"),
			@Result(column = "orderDate", property = "orderdate", javaType = Date.class),
			@Result(column = "customer_id", property = "customer", javaType = Customer.class,
					one = @One(select = "com.briup.dao.CustomerMapper.findCustomerById")),
			@Result(column = "id", property = "orderLines",
					many = @Many(select = "com.briup.dao.OrderLineMapper.findOrderLineByOrderId"))
	})
	OrderForm findOrderFormByOrderid(Integer orderid);

	@Select("select * from es_orderform " +
			"where customer_id = '${id}'")
	@Results({
			@Result(id = true, column = "id", property = "id"),
			@Result(column = "cost", property = "cost"),
			@Result(column = "orderDate", property = "orderdate", javaType = Date.class),
			@Result(column = "customer_id", property = "customer", javaType = Customer.class,
					one = @One(select = "com.briup.dao.CustomerMapper.findCustomerById")),
			@Result(column = "id", property = "orderLines",
					many = @Many(select = "com.briup.dao.OrderLineMapper.findOrderLineByOrderId"))
	})
	List<OrderForm> findOrderFormByCustomerId(Integer id);

	@Delete("delete from es_orderform " +
			"where id =${id}")
	void deleteOrderFormById(Integer id);
}
