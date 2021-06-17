package com.briup.service.impl;

import com.briup.bean.OrderLine;
import com.briup.config.AllConfig;
import com.briup.dao.OrderFormMapper;
import com.briup.dao.OrderLineMapper;
import com.briup.exception.ServiceException;
import com.briup.service.IOrderLineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IOrderLineServiceImpl implements IOrderLineService {

    @Autowired
    private OrderLineMapper orderLineMapper;

    private void initCustomerMapper() {
        orderLineMapper = new AnnotationConfigApplicationContext(AllConfig.class).getBean(OrderLineMapper.class);
    }

    @Override
    public void saveOrderLine(OrderLine ol) throws ServiceException {

    }

    @Override
    public List<OrderLine> findOrderLineByOrderId(Integer id) throws ServiceException {
        initCustomerMapper();
        List<OrderLine> orderLineByOrderId = orderLineMapper.findOrderLineByOrderId(id);
        return orderLineByOrderId;
    }
}
