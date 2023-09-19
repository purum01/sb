package com.example.demo.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "S_ORD")
public class Order {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id; // 주문 아이디
	private Long customerId; // 고객 아이디
	private Date orederDate; // 주문 날짜
	private Double total; // 주문 금액

	@ManyToMany(fetch = FetchType.EAGER)
	private List<Product> productList = new ArrayList<Product>();

}


