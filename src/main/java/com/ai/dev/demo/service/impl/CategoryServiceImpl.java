package com.ai.dev.demo.service.impl;

import com.ai.dev.demo.entity.Category;
import com.ai.dev.demo.mapper.CategoryMapper;
import com.ai.dev.demo.service.CategoryService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

@Service
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, Category> implements CategoryService {
}
