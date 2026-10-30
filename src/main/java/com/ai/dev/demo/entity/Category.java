package com.ai.dev.demo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import lombok.Data;

/**
 * 商品类别
 */
@Data
@TableName("category")
public class Category {

	@TableId(type = IdType.AUTO)
	private Long id;

	/** 类别编码 */
	private String code;

	/** 类别名称 */
	private String name;

}
