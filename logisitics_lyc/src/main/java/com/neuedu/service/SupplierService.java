package com.neuedu.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.neuedu.entity.Supplier;
import java.util.List;

/**
 * 供应商 服务类
 */
public interface SupplierService extends IService<Supplier> {

    /**
     * 获取所有启用的供应商
     * @return 供应商列表
     */
    List<Supplier> getAllEnabledSuppliers();
}