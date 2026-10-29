package com.ai.dev.demo.service.impl;

import com.ai.dev.demo.entity.Brand;
import com.ai.dev.demo.mapper.BrandMapper;
import com.ai.dev.demo.service.BrandService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

@Service
public class BrandServiceImpl extends ServiceImpl<BrandMapper, Brand> implements BrandService {
}
