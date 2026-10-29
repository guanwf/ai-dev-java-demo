package com.ai.dev.demo.controller;

import java.util.List;

import com.ai.dev.demo.entity.Brand;
import com.ai.dev.demo.service.BrandService;
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
 * 商品品牌 增删改查接口
 */
@RestController
@RequestMapping("/brand")
public class BrandController {

	private final BrandService brandService;

	public BrandController(BrandService brandService) {
		this.brandService = brandService;
	}

	/** 新增 / 保存（无 id 新增，有 id 更新） */
	@PostMapping
	public Brand save(@RequestBody Brand brand) {
		validateRequired(brand);
		brandService.saveOrUpdate(brand);
		return brandService.getById(brand.getId());
	}

	/** 修改 */
	@PutMapping("/{id}")
	public Brand update(@PathVariable Long id, @RequestBody Brand brand) {
		brand.setId(id);
		brandService.updateById(brand);
		return brandService.getById(id);
	}

	/** 删除 */
	@DeleteMapping("/{id}")
	public boolean delete(@PathVariable Long id) {
		return brandService.removeById(id);
	}

	/** 查询单个 */
	@GetMapping("/{id}")
	public Brand get(@PathVariable Long id) {
		return brandService.getById(id);
	}

	/** 查询列表 */
	@GetMapping("/list")
	public List<Brand> list() {
		return brandService.list();
	}

	/** 新增 / 保存时 code、name 必填 */
	private void validateRequired(Brand brand) {
		if (brand == null || brand.getCode() == null || brand.getCode().isBlank()
				|| brand.getName() == null || brand.getName().isBlank()) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "code 和 name 不能为空");
		}
	}

}
