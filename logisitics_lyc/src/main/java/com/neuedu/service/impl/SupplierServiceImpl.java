package com.neuedu.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.neuedu.entity.Supplier;
import com.neuedu.mapper.SupplierMapper;
import com.neuedu.service.SupplierService;
import org.springframework.stereotype.Service;
import java.util.List;

/**
 * 供应商 服务实现类
 */
@Service
public class SupplierServiceImpl extends ServiceImpl<SupplierMapper, Supplier> implements SupplierService {

    @Override
    public List<Supplier> getAllEnabledSuppliers() {
        QueryWrapper<Supplier> wrapper = new QueryWrapper<>();
        wrapper.eq("status", 1); // 只查询启用的供应商
        return this.list(wrapper);
    }
}