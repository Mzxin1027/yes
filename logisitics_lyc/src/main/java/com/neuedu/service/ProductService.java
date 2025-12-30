package com.neuedu.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.neuedu.entity.Product;
import java.util.List;

/**
 * 商品 服务类
 */
public interface ProductService extends IService<Product> {

    /**
     * 根据分类ID获取商品列表
     * @param categoryId 分类ID
     * @return 商品列表
     */
    List<Product> getProductsByCategoryId(Long categoryId);

    /**
     * 获取所有启用的商品
     * @return 商品列表
     */
    List<Product> getAllEnabledProducts();
}