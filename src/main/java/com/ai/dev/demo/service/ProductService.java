package com.ai.dev.demo.service;

import java.util.List;

import com.ai.dev.demo.entity.Product;
import com.baomidou.mybatisplus.extension.service.IService;

public interface ProductService extends IService<Product> {

	List<Product> listByBrandId(Long brandId);
}
