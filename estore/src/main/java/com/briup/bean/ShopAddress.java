package com.briup.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 收获地址类
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ShopAddress {

	private Integer id;
	private String receiveName;
	private String address;
	private String phone;
	private Customer customer;

}
