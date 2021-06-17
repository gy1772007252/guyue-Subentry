package com.briup.service;

import com.briup.bean.OrderLine;
import com.briup.exception.ServiceException;

import java.util.List;

public interface IOrderLineService {
	//保存订单项
	void saveOrderLine(OrderLine ol) throws ServiceException;
	//根据订单项ID查找订单项
	List<OrderLine> findOrderLineByOrderId(Integer id) throws ServiceException;
}
