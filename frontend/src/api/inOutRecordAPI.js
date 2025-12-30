import api from './axios';

// 出入库记录相关的API服务
const inOutRecordAPI = {
  // 获取出入库记录列表（分页）
  getRecords: (current = 1, size = 10) => {
    return api.get(`/in-out-record/page?current=${current}&size=${size}`);
  },

  // 获取出入库记录列表（支持类型筛选）
  getRecordsWithType: (current = 1, size = 10, type = null) => {
    let url = `/in-out-record/page-with-type?current=${current}&size=${size}`;
    if (type !== null) {
      url += `&type=${type}`;
    }
    return api.get(url);
  },

  // 获取所有出入库记录
  getAllRecords: () => {
    return api.get('/in-out-record/list');
  },

  // 根据ID获取出入库记录
  getRecordById: (id) => {
    return api.get(`/in-out-record/${id}`);
  },

  // 根据商品ID获取出入库记录
  getRecordsByProductId: (productId) => {
    return api.get(`/in-out-record/by-product/${productId}`);
  },

  // 根据类型获取出入库记录
  getRecordsByType: (type) => {
    return api.get(`/in-out-record/by-type/${type}`);
  },

  // 根据时间段获取出入库记录
  getRecordsByTimeRange: (startTime, endTime) => {
    return api.get(`/in-out-record/by-time-range?startTime=${startTime}&endTime=${endTime}`);
  },

  // 获取入库记录
  getInRecords: () => {
    return api.get('/in-out-record/in-records');
  },

  // 获取出库记录
  getOutRecords: () => {
    return api.get('/in-out-record/out-records');
  },

  // 创建出入库记录
  createRecord: (recordData) => {
    return api.post('/in-out-record', recordData);
  },

  // 购货入库
  purchaseIn: (productId, quantity, supplierId, unitPrice, operator = '', remark = '') => {
    const params = new URLSearchParams();
    params.append('productId', productId);
    params.append('quantity', quantity);
    params.append('supplierId', supplierId);
    params.append('unitPrice', unitPrice);
    if (operator) params.append('operator', operator);
    if (remark) params.append('remark', remark);
    
    return api.post(`/in-out-record/purchase-in?${params.toString()}`);
  },

  // 更新出入库记录
  updateRecord: (recordData) => {
    return api.put('/in-out-record', recordData);
  },

  // 删除出入库记录
  deleteRecord: (id) => {
    return api.delete(`/in-out-record/${id}`);
  },
};

export default inOutRecordAPI;