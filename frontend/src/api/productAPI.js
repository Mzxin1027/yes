import api from './axios';

// 产品相关的API服务
const productAPI = {
  // 获取产品列表（分页）
  getProducts: (current = 1, size = 10) => {
    return api.get(`/product/page?current=${current}&size=${size}`);
  },

  // 获取所有产品
  getAllProducts: () => {
    return api.get('/product/list');
  },

  // 获取启用的产品
  getEnabledProducts: () => {
    return api.get('/product/enabled');
  },

  // 根据ID获取产品
  getProductById: (id) => {
    return api.get(`/product/${id}`);
  },

  // 根据分类ID获取产品
  getProductsByCategoryId: (categoryId) => {
    return api.get(`/product/by-category/${categoryId}`);
  },

  // 创建产品
  createProduct: (productData) => {
    return api.post('/product', productData);
  },

  // 更新产品
  updateProduct: (productData) => {
    return api.put('/product', productData);
  },

  // 删除产品
  deleteProduct: (id) => {
    return api.delete(`/product/${id}`);
  },
};

export default productAPI;