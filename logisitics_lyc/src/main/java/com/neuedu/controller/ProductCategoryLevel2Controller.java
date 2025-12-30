package com.neuedu.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.neuedu.entity.ProductCategoryLevel2;
import com.neuedu.service.ProductCategoryLevel2Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 商品二级分类 前端控制器
 */
@RestController
@RequestMapping("/api/product-category-level2")
public class ProductCategoryLevel2Controller {

    @Autowired
    private ProductCategoryLevel2Service productCategoryLevel2Service;

    /**
     * 分页查询商品二级分类
     */
    @GetMapping("/page")
    public Object getPage(@RequestParam(defaultValue = "1") Integer current,
                          @RequestParam(defaultValue = "10") Integer size) {
        Page<ProductCategoryLevel2> page = new Page<>(current, size);
        Page<ProductCategoryLevel2> result = productCategoryLevel2Service.page(page);
        return result;
    }

    /**
     * 查询所有商品二级分类
     */
    @GetMapping("/list")
    public List<ProductCategoryLevel2> getList() {
        return productCategoryLevel2Service.list();
    }

    /**
     * 根据ID查询商品二级分类
     */
    @GetMapping("/{id}")
    public ProductCategoryLevel2 getById(@PathVariable Long id) {
        return productCategoryLevel2Service.getById(id);
    }

    /**
     * 新增商品二级分类
     */
    @PostMapping
    public boolean add(@RequestBody ProductCategoryLevel2 productCategoryLevel2) {
        return productCategoryLevel2Service.save(productCategoryLevel2);
    }

    /**
     * 更新商品二级分类
     */
    @PutMapping
    public boolean update(@RequestBody ProductCategoryLevel2 productCategoryLevel2) {
        return productCategoryLevel2Service.updateById(productCategoryLevel2);
    }

    /**
     * 删除商品二级分类
     */
    @DeleteMapping("/{id}")
    public boolean delete(@PathVariable Long id) {
        return productCategoryLevel2Service.removeById(id);
    }

    /**
     * 根据父分类ID查询启用的商品二级分类
     */
    @GetMapping("/by-parent/{parentId}")
    public List<ProductCategoryLevel2> getByParentId(@PathVariable Long parentId) {
        return productCategoryLevel2Service.getEnabledCategoriesByParentId(parentId);
    }
}