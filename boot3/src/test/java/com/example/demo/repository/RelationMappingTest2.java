package com.example.demo.repository;

import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.demo.entity.Order;
import com.example.demo.entity.Product;

@SpringBootTest
public class RelationMappingTest2 {

	@Autowired
	private ProductRepository productRepo;
	@Autowired
	private OrderRepository orderRepo;

	@Test
	public void testManyToManyInsert() {
		// 1번 상품 등록
		Product product1 = new Product();
		product1.setName("Samsung TV");
		productRepo.save(product1);

		// 2번 상품 등록
		Product product2 = new Product();
		product2.setName("LG TV");
		productRepo.save(product2);

		// 1번 주문 등록
		Order order = new Order();
		order.setOrederDate(new Date());
		// 주문 객체가 가진 상품 목록(productList)에 상품 저장
		order.getProductList().add(product1);
		order.getProductList().add(product2);
		orderRepo.save(order);
	}

	@Test
	public void testManyToManySelect() {
		Order order = orderRepo.findById(1L).get();
		System.out.println(order.getId() + "주문에 대한 상품 목록");
		List<Product> productList = order.getProductList();
		productList.forEach(product -> System.out.println(product.getName()));
	}
}
