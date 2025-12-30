package com.neuedu.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.neuedu.entity.Supplier;
import com.neuedu.service.SupplierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 供应商 前端控制器
 */
@RestController
@RequestMapping("/api/supplier")
public class SupplierController {

    @Autowired
    private SupplierService supplierService;

    /**
     * 分页查询供应商
     */
    @GetMapping("/page")
    public Object getPage(@RequestParam(defaultValue = "1") Integer current,
                          @RequestParam(defaultValue = "10") Integer size) {
        Page<Supplier> page = new Page<>(current, size);
        Page<Supplier> result = supplierService.page(page);
        return result;
    }

    /**
     * 查询所有供应商
     */
    @GetMapping("/list")
    public List<Supplier> getList() {
        return supplierService.list();
    }

    /**
     * 根据ID查询供应商
     */
    @GetMapping("/{id}")
    public Supplier getById(@PathVariable Long id) {
        return supplierService.getById(id);
    }

    /**
     * 新增供应商
     */
    @PostMapping
    public boolean add(@RequestBody Supplier supplier) {
        return supplierService.save(supplier);
    }

    /**
     * 更新供应商
     */
    @PutMapping
    public boolean update(@RequestBody Supplier supplier) {
        return supplierService.updateById(supplier);
    }

    /**
     * 删除供应商
     */
    @DeleteMapping("/{id}")
    public boolean delete(@PathVariable Long id) {
        return supplierService.removeById(id);
    }

    /**
     * 查询所有启用的供应商
     */
    @GetMapping("/enabled")
    public List<Supplier> getEnabledList() {
        return supplierService.getAllEnabledSuppliers();
    }
}