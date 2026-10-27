package com.ai.dev.demo.service.impl;

import com.ai.dev.demo.entity.Product;
import com.ai.dev.demo.mapper.ProductMapper;
import com.ai.dev.demo.service.ProductService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceImpl extends ServiceImpl<ProductMapper, Product> implements ProductService {
}
