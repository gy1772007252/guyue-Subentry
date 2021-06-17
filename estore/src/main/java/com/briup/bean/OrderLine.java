package com.briup.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 订单项类
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderLine implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private Integer id;
	private Long num;
	private Double cost;
	private Book book;
	private OrderForm orderForm;

}
