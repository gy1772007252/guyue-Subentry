package com.briup.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 *  顾客类
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Customer implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private Integer id;
	private String name;
	private String password;
	private String zipCode;
	private String address;
	private String telephone;
	private String email;

	public Customer(String name, String password, String zipCode, String address, String telephone, String email) {
		this.name = name;
		this.password = password;
		this.zipCode = zipCode;
		this.address = address;
		this.telephone = telephone;
		this.email = email;
	}

}
