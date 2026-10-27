package com.ai.dev.demo.controller;

import java.util.List;

import com.ai.dev.demo.entity.Product;
import com.ai.dev.demo.service.ProductService;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 商品资料 增删改查接口
 */
@RestController
@RequestMapping("/product")
public class ProductController {

	private final ProductService productService;

	public ProductController(ProductService productService) {
		this.productService = productService;
	}

	/** 新增 / 保存 */
	@PostMapping
	public Product save(@RequestBody Product product) {
		productService.save(product);
		return productService.getById(product.getId());
	}

	/** 修改 */
	@PutMapping("/{id}")
	public Product update(@PathVariable Long id, @RequestBody Product product) {
		product.setId(id);
		productService.updateById(product);
		return productService.getById(id);
	}

	/** 删除 */
	@DeleteMapping("/{id}")
	public boolean delete(@PathVariable Long id) {
		return productService.removeById(id);
	}

	/** 查询单个 */
	@GetMapping("/{id}")
	public Product get(@PathVariable Long id) {
		return productService.getById(id);
	}

	/** 查询列表 */
	@GetMapping("/list")
	public List<Product> list() {
		return productService.list();
	}

}
