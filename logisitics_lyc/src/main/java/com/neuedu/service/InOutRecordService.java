package com.neuedu.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.neuedu.entity.InOutRecord;
import java.util.List;

/**
 * 出入库记录 服务类
 */
public interface InOutRecordService extends IService<InOutRecord> {

    /**
     * 根据商品ID查询出入库记录
     * @param productId 商品ID
     * @return 出入库记录列表
     */
    List<InOutRecord> getByProductId(Long productId);

    /**
     * 根据类型查询出入库记录
     * @param type 类型：1-入库，2-出库
     * @return 出入库记录列表
     */
    List<InOutRecord> getByType(Integer type);

    /**
     * 根据时间段查询出入库记录
     * @param startTime 开始时间
     * @param endTime 结束时间
     * @return 出入库记录列表
     */
    List<InOutRecord> getByTimeRange(java.time.LocalDateTime startTime, java.time.LocalDateTime endTime);
}