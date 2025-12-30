package com.neuedu.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.neuedu.entity.InOutRecord;
import org.apache.ibatis.annotations.Mapper;

/**
 * 出入库记录 Mapper 接口
 */
@Mapper
public interface InOutRecordMapper extends BaseMapper<InOutRecord> {

}