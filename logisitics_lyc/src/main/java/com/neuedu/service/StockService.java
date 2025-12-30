package com.neuedu.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.neuedu.entity.Stock;
import java.util.List;

/**
 * 库存 服务类
 */
public interface StockService extends IService<Stock> {

    /**
     * 根据商品ID获取库存信息
     * @param productId 商品ID
     * @return 库存信息
     */
    Stock getByProductId(Long productId);

    /**
     * 批量更新库存
     * @param stockList 库存列表
     * @return 是否更新成功
     */
    boolean batchUpdateStock(List<Stock> stockList);

    /**
     * 检查库存是否充足
     * @param productId 商品ID
     * @param quantity 需要的数量
     * @return 是否充足
     */
    boolean checkStock(Long productId, Integer quantity);

    /**
     * 更新库存数量
     * @param productId 商品ID
     * @param quantity 变更数量（正数为入库，负数为出库）
     * @return 是否更新成功
     */
    boolean updateStock(Long productId, Integer quantity);
}