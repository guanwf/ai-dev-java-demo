package com.ai.dev.demo.controller;

import java.util.List;

import com.ai.dev.demo.entity.Category;
import com.ai.dev.demo.service.CategoryService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

/**
 * 商品类别 增删改查接口
 */
@RestController
@RequestMapping("/category")
public class CategoryController {

	private final CategoryService categoryService;

	public CategoryController(CategoryService categoryService) {
		this.categoryService = categoryService;
	}

	/** 新增 / 保存（无 id 新增，有 id 更新） */
	@PostMapping
	public Category save(@RequestBody Category category) {
		validateRequired(category);
		categoryService.saveOrUpdate(category);
		return categoryService.getById(category.getId());
	}

	/** 修改 */
	@PutMapping("/{id}")
	public Category update(@PathVariable Long id, @RequestBody Category category) {
		category.setId(id);
		categoryService.updateById(category);
		return categoryService.getById(id);
	}

	/** 删除 */
	@DeleteMapping("/{id}")
	public boolean delete(@PathVariable Long id) {
		return categoryService.removeById(id);
	}

	/** 查询单个 */
	@GetMapping("/{id}")
	public Category get(@PathVariable Long id) {
		return categoryService.getById(id);
	}

	/** 查询列表 */
	@GetMapping("/list")
	public List<Category> list() {
		return categoryService.list();
	}

	/** 新增 / 保存时 code、name 必填 */
	private void validateRequired(Category category) {
		if (category == null || category.getCode() == null || category.getCode().isBlank()
				|| category.getName() == null || category.getName().isBlank()) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "code 和 name 不能为空");
		}
	}

}
