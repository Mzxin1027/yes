import api from './axios';

// 库存相关的API服务
const stockAPI = {
  // 获取库存列表（分页）
  getStocks: (current = 1, size = 10) => {
    return api.get(`/stock/page?current=${current}&size=${size}`);
  },

  // 获取所有库存
  getAllStocks: () => {
    return api.get('/stock/list');
  },

  // 根据ID获取库存
  getStockById: (id) => {
    return api.get(`/stock/${id}`);
  },

  // 根据商品ID获取库存
  getStockByProductId: (productId) => {
    return api.get(`/stock/by-product/${productId}`);
  },

  // 获取低库存商品
  getLowStockProducts: () => {
    return api.get('/stock/low-stock');
  },

  // 获取库存不足的商品
  getOutOfStockProducts: () => {
    return api.get('/stock/out-of-stock');
  },

  // 获取库存充足的商品
  getSufficientStockProducts: () => {
    return api.get('/stock/sufficient-stock');
  },

  // 创建库存
  createStock: (stockData) => {
    return api.post('/stock', stockData);
  },

  // 更新库存
  updateStock: (stockData) => {
    return api.put('/stock', stockData);
  },

  // 删除库存
  deleteStock: (id) => {
    return api.delete(`/stock/${id}`);
  },
};

export default stockAPI;