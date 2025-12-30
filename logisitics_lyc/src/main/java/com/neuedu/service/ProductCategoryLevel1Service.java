package com.neuedu.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.neuedu.entity.ProductCategoryLevel1;
import java.util.List;

/**
 * 商品一级分类 服务类
 */
public interface ProductCategoryLevel1Service extends IService<ProductCategoryLevel1> {

    /**
     * 获取所有启用的一级分类
     * @return 一级分类列表
     */
    List<ProductCategoryLevel1> getAllEnabledCategories();
}