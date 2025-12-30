package com.neuedu.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.TableField;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.io.Serializable;
import lombok.Data;

/**
 * 库存实体类
 */
@Data
@TableName("stock")
public class Stock implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键ID
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 商品ID
     */
    @TableField("product_id")
    private Long productId;

    /**
     * 当前库存数量
     */
    @TableField("current_stock")
    private Integer currentStock;

    /**
     * 最低库存数量
     */
    @TableField("min_stock")
    private Integer minStock;

    /**
     * 最高库存数量
     */
    @TableField("max_stock")
    private Integer maxStock;

    /**
     * 安全库存数量
     */
    @TableField("safe_stock")
    private Integer safeStock;

    /**
     * 状态：1-正常，0-异常
     */
    @TableField("status")
    private Integer status;

    /**
     * 创建时间
     */
    @TableField("create_time")
    private LocalDateTime createTime;

    /**
     * 更新时间
     */
    @TableField("update_time")
    private LocalDateTime updateTime;
}