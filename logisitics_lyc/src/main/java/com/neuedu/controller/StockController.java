package com.neuedu.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.neuedu.entity.Stock;
import com.neuedu.service.StockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 库存 前端控制器
 */
@RestController
@RequestMapping("/api/stock")
public class StockController {

    @Autowired
    private StockService stockService;

    /**
     * 分页查询库存
     */
    @GetMapping("/page")
    public Object getPage(@RequestParam(defaultValue = "1") Integer current,
                          @RequestParam(defaultValue = "10") Integer size) {
        Page<Stock> page = new Page<>(current, size);
        Page<Stock> result = stockService.page(page);
        return result;
    }

    /**
     * 查询所有库存
     */
    @GetMapping("/list")
    public List<Stock> getList() {
        return stockService.list();
    }

    /**
     * 根据ID查询库存
     */
    @GetMapping("/{id}")
    public Stock getById(@PathVariable Long id) {
        return stockService.getById(id);
    }

    /**
     * 根据商品ID查询库存
     */
    @GetMapping("/by-product/{productId}")
    public Stock getByProductId(@PathVariable Long productId) {
        return stockService.getByProductId(productId);
    }

    /**
     * 查询低库存商品
     */
    @GetMapping("/low-stock")
    public List<Stock> getLowStockProducts() {
        QueryWrapper<Stock> wrapper = new QueryWrapper<>();
        wrapper.apply("current_stock <= safe_stock");
        return stockService.list(wrapper);
    }

    /**
     * 查询库存不足的商品
     */
    @GetMapping("/out-of-stock")
    public List<Stock> getOutOfStockProducts() {
        QueryWrapper<Stock> wrapper = new QueryWrapper<>();
        wrapper.eq("current_stock", 0);
        return stockService.list(wrapper);
    }

    /**
     * 查询库存充足的商品
     */
    @GetMapping("/sufficient-stock")
    public List<Stock> getSufficientStockProducts() {
        QueryWrapper<Stock> wrapper = new QueryWrapper<>();
        wrapper.gt("current_stock", 0);
        return stockService.list(wrapper);
    }

    /**
     * 新增库存
     */
    @PostMapping
    public boolean add(@RequestBody Stock stock) {
        return stockService.save(stock);
    }

    /**
     * 更新库存
     */
    @PutMapping
    public boolean update(@RequestBody Stock stock) {
        return stockService.updateById(stock);
    }

    /**
     * 删除库存
     */
    @DeleteMapping("/{id}")
    public boolean delete(@PathVariable Long id) {
        return stockService.removeById(id);
    }

    /**
     * 检查库存是否充足
     */
    @GetMapping("/check/{productId}/{quantity}")
    public boolean checkStock(@PathVariable Long productId, @PathVariable Integer quantity) {
        return stockService.checkStock(productId, quantity);
    }

    /**
     * 更新库存数量
     */
    @PutMapping("/update-stock/{productId}/{quantity}")
    public boolean updateStock(@PathVariable Long productId, @PathVariable Integer quantity) {
        return stockService.updateStock(productId, quantity);
    }
}