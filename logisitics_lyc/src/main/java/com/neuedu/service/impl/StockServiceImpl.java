package com.neuedu.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.neuedu.entity.Stock;
import com.neuedu.mapper.StockMapper;
import com.neuedu.service.StockService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

/**
 * 库存 服务实现类
 */
@Service
public class StockServiceImpl extends ServiceImpl<StockMapper, Stock> implements StockService {

    @Override
    public Stock getByProductId(Long productId) {
        QueryWrapper<Stock> wrapper = new QueryWrapper<>();
        wrapper.eq("product_id", productId);
        return this.getOne(wrapper);
    }

    @Override
    @Transactional
    public boolean batchUpdateStock(List<Stock> stockList) {
        return this.updateBatchById(stockList);
    }

    @Override
    public boolean checkStock(Long productId, Integer quantity) {
        Stock stock = this.getByProductId(productId);
        if (stock == null) {
            return false;
        }
        return stock.getCurrentStock() >= quantity;
    }

    @Override
    @Transactional
    public boolean updateStock(Long productId, Integer quantity) {
        Stock stock = this.getByProductId(productId);
        if (stock == null) {
            // 如果库存记录不存在，创建新的库存记录
            stock = new Stock();
            stock.setProductId(productId);
            stock.setCurrentStock(quantity);
            stock.setStatus(1);
            return this.save(stock);
        } else {
            // 更新现有库存
            Integer newStock = stock.getCurrentStock() + quantity;
            if (newStock < 0) {
                // 出库数量不能超过现有库存
                return false;
            }
            stock.setCurrentStock(newStock);
            return this.updateById(stock);
        }
    }
}