package com.neuedu.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.neuedu.entity.ProductCategoryLevel1;
import com.neuedu.mapper.ProductCategoryLevel1Mapper;
import com.neuedu.service.ProductCategoryLevel1Service;
import org.springframework.stereotype.Service;
import java.util.List;

/**
 * 商品一级分类 服务实现类
 */
@Service
public class ProductCategoryLevel1ServiceImpl extends ServiceImpl<ProductCategoryLevel1Mapper, ProductCategoryLevel1> implements ProductCategoryLevel1Service {

    @Override
    public List<ProductCategoryLevel1> getAllEnabledCategories() {
        QueryWrapper<ProductCategoryLevel1> wrapper = new QueryWrapper<>();
        wrapper.eq("status", 1); // 只查询启用状态的分类
        return this.list(wrapper);
    }
}