package com.briup.service.impl;

import com.briup.bean.OrderForm;
import com.briup.bean.OrderLine;
import com.briup.config.AllConfig;
import com.briup.dao.OrderFormMapper;
import com.briup.dao.OrderLineMapper;
import com.briup.exception.ServiceException;
import com.briup.service.IOrderFormService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IOrderFormServiceImpl implements IOrderFormService {

    @Autowired
    private OrderFormMapper orderFormMapper;
    @Autowired
    private OrderLineMapper orderLineMapper;

    private void initCustomerMapper() {
        orderFormMapper = new AnnotationConfigApplicationContext(AllConfig.class).getBean(OrderFormMapper.class);
        orderLineMapper = new AnnotationConfigApplicationContext(AllConfig.class).getBean(OrderLineMapper.class);
    }

    @Override
    public void saveOrderForm(OrderForm of) throws ServiceException {
        initCustomerMapper();
        if (of.getOrderLines() == null) {
            throw new ServiceException("请选择商品!");
        }
        orderFormMapper.saveOrderForm(of);
        for (OrderLine orderLine : of.getOrderLines()) {
            orderLine.setOrderForm(of);
            orderLineMapper.saveOrderLine(orderLine);
        }
    }

    @Override
    public OrderForm findOrderFormByOrderid(Long orderid) throws ServiceException {
        return null;
    }

    @Override
    public List<OrderForm> findOrderFormByCustomerId(Integer id) throws ServiceException {
        initCustomerMapper();
        List<OrderForm> orderFormByCustomerId = orderFormMapper.findOrderFormByCustomerId(id);
        return orderFormByCustomerId;
    }

    @Override
    public void deleteOrderFormById(Integer id) throws ServiceException {
        System.out.println(id);
        List<OrderLine> orderLineByOrderId = orderLineMapper.findOrderLineByOrderId(id);
        orderLineMapper.deleteOrderLineByCollection(orderLineByOrderId);
        orderFormMapper.deleteOrderFormById(id);
    }
}
