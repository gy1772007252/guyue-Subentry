package com.briup.dao;

import com.briup.bean.Book;
import com.briup.bean.Customer;
import com.briup.bean.OrderLine;
import org.apache.ibatis.annotations.*;

import java.sql.Date;
import java.util.Collection;
import java.util.List;

public interface OrderLineMapper {

	@Insert("insert into es_orderline(num, cost, book_id, orderForm_id) " +
			"values(#{num}, #{cost}, #{book.id}, #{orderForm.id})")
	@Options(useGeneratedKeys = true, keyProperty = "id")
	void saveOrderLine(OrderLine ol);

	@Select("select * from es_orderline " +
			"where orderForm_id = '${id}'")
	@Results({
			@Result(id = true, column = "id", property = "id"),
			@Result(column = "num", property = "num"),
			@Result(column = "cost", property = "cost"),
			@Result(column = "book_id", property = "book", javaType = Book.class,
					one = @One(select = "com.briup.dao.BookMapper.findBookById")),
			@Result(column = "orderForm_id", property = "orderForm",
					one = @One(select = "com.briup.dao.OrderFormMapper.findOrderFormByOrderid"))
	})
	List<OrderLine> findOrderLineByOrderId(Integer id);

	@Delete("<script>" +
			"delete from es_orderline " +
			"where id in " +
			"<foreach collection='orderLines' item='orderLine' open='(' separator=',' close=')'>" +
			"#{orderLine.id }" +
			"</foreach>" +
			"</script>")
	void deleteOrderLineByCollection(@Param("orderLines") Collection<OrderLine> orderLines);
}
