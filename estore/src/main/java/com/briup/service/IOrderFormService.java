package com.briup.service;

import com.briup.bean.OrderForm;
import com.briup.exception.ServiceException;

import java.util.List;

public interface IOrderFormService {
	//保存订单信息
	void saveOrderForm(OrderForm of) throws ServiceException;
	//根据订单ID查找订单信息
	OrderForm findOrderFormByOrderid(Long orderid) throws ServiceException;
	//根据用户ID查找订单信息
	List<OrderForm> findOrderFormByCustomerId(Integer id) throws ServiceException;
	//根据订单ID删除订单信息
	void deleteOrderFormById(Integer id) throws ServiceException;
}
