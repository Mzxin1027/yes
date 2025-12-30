package com.neuedu.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.neuedu.entity.ProductCategoryLevel1;
import com.neuedu.service.ProductCategoryLevel1Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 商品一级分类 前端控制器
 */
@RestController
@RequestMapping("/api/product-category-level1")
public class ProductCategoryLevel1Controller {

    @Autowired
    private ProductCategoryLevel1Service productCategoryLevel1Service;

    /**
     * 分页查询商品一级分类
     */
    @GetMapping("/page")
    public Object getPage(@RequestParam(defaultValue = "1") Integer current,
                          @RequestParam(defaultValue = "10") Integer size) {
        Page<ProductCategoryLevel1> page = new Page<>(current, size);
        Page<ProductCategoryLevel1> result = productCategoryLevel1Service.page(page);
        return result;
    }

    /**
     * 查询所有商品一级分类
     */
    @GetMapping("/list")
    public List<ProductCategoryLevel1> getList() {
        return productCategoryLevel1Service.list();
    }

    /**
     * 根据ID查询商品一级分类
     */
    @GetMapping("/{id}")
    public ProductCategoryLevel1 getById(@PathVariable Long id) {
        return productCategoryLevel1Service.getById(id);
    }

    /**
     * 新增商品一级分类
     */
    @PostMapping
    public boolean add(@RequestBody ProductCategoryLevel1 productCategoryLevel1) {
        return productCategoryLevel1Service.save(productCategoryLevel1);
    }

    /**
     * 更新商品一级分类
     */
    @PutMapping
    public boolean update(@RequestBody ProductCategoryLevel1 productCategoryLevel1) {
        return productCategoryLevel1Service.updateById(productCategoryLevel1);
    }

    /**
     * 删除商品一级分类
     */
    @DeleteMapping("/{id}")
    public boolean delete(@PathVariable Long id) {
        return productCategoryLevel1Service.removeById(id);
    }

    /**
     * 查询所有启用的商品一级分类
     */
    @GetMapping("/enabled")
    public List<ProductCategoryLevel1> getEnabledList() {
        return productCategoryLevel1Service.getAllEnabledCategories();
    }
}