package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dao.CategoryDao;
import com.example.demo.entity.Category;

@Service
public class CategoryService {
	
	@Autowired
	private CategoryDao dao;
	
	public List<Category> findAll(){
		return dao.findAll();
		}
}
