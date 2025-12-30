import api from './axios';

// 供应商相关的API服务
const supplierAPI = {
  // 获取供应商列表（分页）
  getSuppliers: (current = 1, size = 10) => {
    return api.get(`/supplier/page?current=${current}&size=${size}`);
  },

  // 获取所有供应商
  getAllSuppliers: () => {
    return api.get('/supplier/list');
  },

  // 获取启用的供应商
  getEnabledSuppliers: () => {
    return api.get('/supplier/enabled');
  },

  // 根据ID获取供应商
  getSupplierById: (id) => {
    return api.get(`/supplier/${id}`);
  },

  // 创建供应商
  createSupplier: (supplierData) => {
    return api.post('/supplier', supplierData);
  },

  // 更新供应商
  updateSupplier: (supplierData) => {
    return api.put('/supplier', supplierData);
  },

  // 删除供应商
  deleteSupplier: (id) => {
    return api.delete(`/supplier/${id}`);
  },
};

export default supplierAPI;