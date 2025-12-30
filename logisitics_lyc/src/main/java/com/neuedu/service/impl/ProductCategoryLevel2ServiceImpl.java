package com.neuedu.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.neuedu.entity.ProductCategoryLevel2;
import com.neuedu.mapper.ProductCategoryLevel2Mapper;
import com.neuedu.service.ProductCategoryLevel2Service;
import org.springframework.stereotype.Service;
import java.util.List;

/**
 * 商品二级分类 服务实现类
 */
@Service
public class ProductCategoryLevel2ServiceImpl extends ServiceImpl<ProductCategoryLevel2Mapper, ProductCategoryLevel2> implements ProductCategoryLevel2Service {

    @Override
    public List<ProductCategoryLevel2> getEnabledCategoriesByParentId(Long parentId) {
        QueryWrapper<ProductCategoryLevel2> wrapper = new QueryWrapper<>();
        wrapper.eq("parent_id", parentId)
               .eq("status", 1); // 只查询启用状态的分类
        return this.list(wrapper);
    }
}