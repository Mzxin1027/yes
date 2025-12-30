package com.neuedu.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.neuedu.entity.InOutRecord;
import com.neuedu.service.InOutRecordService;
import com.neuedu.service.StockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

/**
 * 出入库记录 前端控制器
 */
@RestController
@RequestMapping("/api/in-out-record")
public class InOutRecordController {

    @Autowired
    private InOutRecordService inOutRecordService;
    
    @Autowired
    private StockService stockService;

    /**
     * 分页查询出入库记录
     */
    @GetMapping("/page")
    public Object getPage(@RequestParam(defaultValue = "1") Integer current,
                          @RequestParam(defaultValue = "10") Integer size) {
        Page<InOutRecord> page = new Page<>(current, size);
        Page<InOutRecord> result = inOutRecordService.page(page);
        return result;
    }

    /**
     * 分页查询出入库记录（支持类型筛选）
     */
    @GetMapping("/page-with-type")
    public Object getPageWithType(@RequestParam(defaultValue = "1") Integer current,
                                  @RequestParam(defaultValue = "10") Integer size,
                                  @RequestParam(required = false) Integer type) {
        Page<InOutRecord> page = new Page<>(current, size);
        QueryWrapper<InOutRecord> wrapper = new QueryWrapper<>();
        if (type != null) {
            wrapper.eq("type", type);
        }
        wrapper.orderByDesc("create_time");
        return inOutRecordService.page(page, wrapper);
    }

    /**
     * 查询所有出入库记录
     */
    @GetMapping("/list")
    public List<InOutRecord> getList() {
        return inOutRecordService.list();
    }

    /**
     * 根据ID查询出入库记录
     */
    @GetMapping("/{id}")
    public InOutRecord getById(@PathVariable Long id) {
        return inOutRecordService.getById(id);
    }

    /**
     * 根据商品ID查询出入库记录
     */
    @GetMapping("/by-product/{productId}")
    public List<InOutRecord> getByProductId(@PathVariable Long productId) {
        return inOutRecordService.getByProductId(productId);
    }

    /**
     * 根据类型查询出入库记录
     */
    @GetMapping("/by-type/{type}")
    public List<InOutRecord> getByType(@PathVariable Integer type) {
        return inOutRecordService.getByType(type);
    }

    /**
     * 根据时间段查询出入库记录
     */
    @GetMapping("/by-time-range")
    public List<InOutRecord> getByTimeRange(
            @RequestParam LocalDateTime startTime,
            @RequestParam LocalDateTime endTime) {
        return inOutRecordService.getByTimeRange(startTime, endTime);
    }

    /**
     * 查询入库记录
     */
    @GetMapping("/in-records")
    public List<InOutRecord> getInRecords() {
        return inOutRecordService.getByType(1);
    }

    /**
     * 查询出库记录
     */
    @GetMapping("/out-records")
    public List<InOutRecord> getOutRecords() {
        return inOutRecordService.getByType(2);
    }

    /**
     * 新增出入库记录
     */
    @PostMapping
    public boolean add(@RequestBody InOutRecord inOutRecord) {
        // 更新库存
        Integer quantity = inOutRecord.getType() == 1 ? inOutRecord.getQuantity() : -inOutRecord.getQuantity();
        boolean stockUpdated = stockService.updateStock(inOutRecord.getProductId(), quantity);
        if (!stockUpdated) {
            throw new RuntimeException("库存更新失败");
        }
        return inOutRecordService.save(inOutRecord);
    }

    /**
     * 购货入库
     */
    @PostMapping("/purchase-in")
    public boolean purchaseIn(@RequestParam Long productId,
                              @RequestParam Integer quantity,
                              @RequestParam Long supplierId,
                              @RequestParam BigDecimal unitPrice,
                              @RequestParam(required = false) String operator,
                              @RequestParam(required = false) String remark) {
        // 创建出入库记录
        InOutRecord record = new InOutRecord();
        record.setProductId(productId);
        record.setType(1); // 入库
        record.setQuantity(quantity);
        record.setUnitPrice(unitPrice);
        record.setTotalAmount(unitPrice.multiply(new BigDecimal(quantity)));
        record.setSupplierId(supplierId);
        record.setOperator(operator);
        record.setRemark(remark);

        // 更新库存
        boolean stockUpdated = stockService.updateStock(productId, quantity);
        if (!stockUpdated) {
            throw new RuntimeException("库存更新失败");
        }

        return inOutRecordService.save(record);
    }

    /**
     * 更新出入库记录
     */
    @PutMapping
    public boolean update(@RequestBody InOutRecord inOutRecord) {
        return inOutRecordService.updateById(inOutRecord);
    }

    /**
     * 删除出入库记录
     */
    @DeleteMapping("/{id}")
    public boolean delete(@PathVariable Long id) {
        return inOutRecordService.removeById(id);
    }
}