package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dao.ProductDao;
import com.example.demo.entity.Product;

@Service
public class ProductService {

	@Autowired
	private ProductDao dao;

	public void save(Product p) {
		dao.save(p);
	}

	public List<Product> findAll() {
		return dao.findAll();
	}

	public Product getProduct(int id) {
		return dao.getOne(id);
	}

	public void delete(int id) {
		dao.deleteById(id);
	}
}
