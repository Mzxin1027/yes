package com.neuedu.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.neuedu.entity.InOutRecord;
import com.neuedu.mapper.InOutRecordMapper;
import com.neuedu.service.InOutRecordService;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;

/**
 * 出入库记录 服务实现类
 */
@Service
public class InOutRecordServiceImpl extends ServiceImpl<InOutRecordMapper, InOutRecord> implements InOutRecordService {

    @Override
    public List<InOutRecord> getByProductId(Long productId) {
        QueryWrapper<InOutRecord> wrapper = new QueryWrapper<>();
        wrapper.eq("product_id", productId);
        wrapper.orderByDesc("create_time");
        return this.list(wrapper);
    }

    @Override
    public List<InOutRecord> getByType(Integer type) {
        QueryWrapper<InOutRecord> wrapper = new QueryWrapper<>();
        wrapper.eq("type", type);
        wrapper.orderByDesc("create_time");
        return this.list(wrapper);
    }

    @Override
    public List<InOutRecord> getByTimeRange(LocalDateTime startTime, LocalDateTime endTime) {
        QueryWrapper<InOutRecord> wrapper = new QueryWrapper<>();
        wrapper.between("create_time", startTime, endTime);
        wrapper.orderByDesc("create_time");
        return this.list(wrapper);
    }
}