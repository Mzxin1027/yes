package com.neuedu.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.neuedu.entity.ProductCategoryLevel2;
import java.util.List;

/**
 * 商品二级分类 服务类
 */
public interface ProductCategoryLevel2Service extends IService<ProductCategoryLevel2> {

    /**
     * 根据父分类ID获取所有启用的二级分类
     * @param parentId 父分类ID
     * @return 二级分类列表
     */
    List<ProductCategoryLevel2> getEnabledCategoriesByParentId(Long parentId);
}