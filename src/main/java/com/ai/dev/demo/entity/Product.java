package com.ai.dev.demo.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import lombok.Data;

/**
 * 商品资料
 */
@Data
@TableName("product")
public class Product {

	@TableId(type = IdType.AUTO)
	private Long id;

	/** 商品名称 */
	private String name;

	/** 商品编码 */
	private String code;

	/** 分类 */
	private String category;

	/** 品牌ID */
	private Long brandId;

	/** 供应商 */
	private String vender;

	/** 价格 */
	private BigDecimal price;

	/** 进价 */
	private BigDecimal cost;

	/** 库存 */
	private Integer stock;

	/** 单位 */
	private String unit;

	/** 状态：1 上架，0 下架 */
	private Integer status;

	/** 描述 */
	private String description;

	/** 创建时间 */
	@TableField(fill = FieldFill.INSERT)
	private LocalDateTime createTime;

	/** 更新时间 */
	@TableField(fill = FieldFill.INSERT_UPDATE)
	private LocalDateTime updateTime;

}
