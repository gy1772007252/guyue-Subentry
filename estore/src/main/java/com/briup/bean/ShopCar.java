package com.briup.bean;

import com.briup.config.AllConfig;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.core.annotation.Order;

import java.io.Serializable;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * 购物车类
 */
public class ShopCar implements Serializable{

	private static final long serialVersionUID = 241520752302560989L;
	//key   - 书籍编号
	//value - 订单项
	private Map<Integer,OrderLine> map;
	
	{
		map = new HashMap<>();
	}
	
	public void add(Book book,Integer num) {
		Integer bookId = book.getId();
		if(map.containsKey(bookId)) {
			OrderLine orderLine = map.get(bookId);
			orderLine.setNum(orderLine.getNum()+num);
			orderLine.setCost(book.getPrice()*orderLine.getNum());
		}else {
			ApplicationContext ac = new AnnotationConfigApplicationContext(AllConfig.class);
			OrderLine orderLine = ac.getBean(OrderLine.class);
			orderLine.setBook(book);
			orderLine.setNum(num.longValue());
			orderLine.setCost(book.getPrice()*num);
			map.put(book.getId(), orderLine);
		}
	}
	public void add(Book book) {
		add(book,1);
	}
	
	public Map<Integer,OrderLine> getData(){
		return map;
	}
	
	public Collection<OrderLine> getOrderLines(){
		return map.values();
	}
	
	public int getSize() {
		int num = 0;
		for (OrderLine value : map.values()) {
			num += value.getNum();
		}
		return num;
	}
	
	public double getCost() {
		double sum = 0;
		for(OrderLine ol : map.values()) {
			sum += ol.getCost();
		}
		return sum;
	}
	
	public void removeOrderLine(int bookId) {
		map.remove(bookId);
	}
	
	public void clear() {
		map.clear();
	}

	public void updateOrderLine(int id, int num) {
		OrderLine orderLine = map.get(id);
		orderLine.setNum(Long.valueOf(num));
		orderLine.setCost(num*orderLine.getBook().getPrice());
	}
	
}
