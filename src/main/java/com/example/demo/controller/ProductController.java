package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.dao.CategoryDao;
import com.example.demo.dao.ProductDao;
import com.example.demo.entity.Product;

@Controller
public class ProductController {

	@Autowired
	private ProductDao dao;
	
	@Autowired
	private CategoryDao cdao;

	public void setCdao(CategoryDao cdao) {
		this.cdao = cdao;
	}

	public void setDao(ProductDao dao) {
		this.dao = dao;
	}
	 
	

	@GetMapping("insert")
	public String insertForm(Model model) {
		model.addAttribute("clist", cdao.findAll());
		return "/manager/insert";
	}
	

	@PostMapping("/insert")
	public String insertProduct(Product p) {
		dao.save(p);
		return "redirect:/list";
	}

	@RequestMapping("/list")
	public void list(Model model,@RequestParam(value = "cid", defaultValue = "0") int cid) {
		System.out.println("카테코리번호:"+cid);
		if(cid != 0) {
			model.addAttribute("list",   cdao.getOne(cid).getProducts());
		}else {
			model.addAttribute("list", dao.findAll());
		}
		
		model.addAttribute("title", "비트 쇼핑몰 상품목록");
		model.addAttribute("clist", cdao.findAll());
		
	}

	@GetMapping("/delete")
	public String delete(int id) {
		dao.deleteById(id);
		return "redirect:/list";
	}

	@GetMapping("/edit")
	public void update(int id, Model model) {
		System.out.println("상품정보 출력:" + dao.getOne(id));
		System.out.println("상품정보 출력:" + dao.findById(id));
		model.addAttribute("p", dao.getOne(id));
	}

}
