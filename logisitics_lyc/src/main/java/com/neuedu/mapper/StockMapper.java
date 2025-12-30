package com.neuedu.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.neuedu.entity.Stock;
import org.apache.ibatis.annotations.Mapper;

/**
 * 库存 Mapper 接口
 */
@Mapper
public interface StockMapper extends BaseMapper<Stock> {

}