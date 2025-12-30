package com.neuedu.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.neuedu.entity.Product;
import com.neuedu.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 商品 前端控制器
 */
@RestController
@RequestMapping("/api/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    /**
     * 分页查询商品
     */
    @GetMapping("/page")
    public Object getPage(@RequestParam(defaultValue = "1") Integer current,
                          @RequestParam(defaultValue = "10") Integer size) {
        Page<Product> page = new Page<>(current, size);
        Page<Product> result = productService.page(page);
        return result;
    }

    /**
     * 查询所有商品
     */
    @GetMapping("/list")
    public List<Product> getList() {
        return productService.list();
    }

    /**
     * 根据ID查询商品
     */
    @GetMapping("/{id}")
    public Product getById(@PathVariable Long id) {
        return productService.getById(id);
    }

    /**
     * 新增商品
     */
    @PostMapping
    public boolean add(@RequestBody Product product) {
        return productService.save(product);
    }

    /**
     * 更新商品
     */
    @PutMapping
    public boolean update(@RequestBody Product product) {
        return productService.updateById(product);
    }

    /**
     * 删除商品
     */
    @DeleteMapping("/{id}")
    public boolean delete(@PathVariable Long id) {
        return productService.removeById(id);
    }

    /**
     * 根据分类ID查询商品
     */
    @GetMapping("/by-category/{categoryId}")
    public List<Product> getByCategoryId(@PathVariable Long categoryId) {
        return productService.getProductsByCategoryId(categoryId);
    }

    /**
     * 查询所有启用的商品
     */
    @GetMapping("/enabled")
    public List<Product> getEnabledList() {
        return productService.getAllEnabledProducts();
    }
}