package com.neuedu.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.neuedu.entity.Product;
import com.neuedu.mapper.ProductMapper;
import com.neuedu.service.ProductService;
import org.springframework.stereotype.Service;
import java.util.List;

/**
 * 商品 服务实现类
 */
@Service
public class ProductServiceImpl extends ServiceImpl<ProductMapper, Product> implements ProductService {

    @Override
    public List<Product> getProductsByCategoryId(Long categoryId) {
        QueryWrapper<Product> wrapper = new QueryWrapper<>();
        wrapper.eq("category_id", categoryId);
        return this.list(wrapper);
    }

    @Override
    public List<Product> getAllEnabledProducts() {
        QueryWrapper<Product> wrapper = new QueryWrapper<>();
        wrapper.eq("status", 1); // 只查询上架的商品
        return this.list(wrapper);
    }
}