package com.briup.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Collection;
import java.util.Date;

/**
 * 订单类
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderForm {

	private Integer id;
	private Double cost;
	private Date orderdate;
	private Customer customer;
	private ShopAddress shopAddress;
	private Collection<OrderLine> orderLines;

}
