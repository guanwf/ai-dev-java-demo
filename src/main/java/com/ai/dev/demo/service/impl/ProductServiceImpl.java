package com.ai.dev.demo.service.impl;

import java.util.List;

import com.ai.dev.demo.entity.Product;
import com.ai.dev.demo.mapper.ProductMapper;
import com.ai.dev.demo.service.ProductService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceImpl extends ServiceImpl<ProductMapper, Product> implements ProductService {

	@Override
	public List<Product> listByBrandId(Long brandId) {
		return lambdaQuery().eq(Product::getBrandId, brandId).list();
	}
}
