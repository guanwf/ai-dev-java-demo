package com.ai.dev.demo.entity;

import java.time.LocalDateTime;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import lombok.Data;

/**
 * 商品品牌
 */
@Data
@TableName("brand")
public class Brand {

	@TableId(type = IdType.AUTO)
	private Long id;

	/** 品牌编码 */
	private String code;

	/** 品牌名称 */
	private String name;

	/** 状态：1 启用，0 禁用 */
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
