import api from './axios';

// 产品一级分类相关的API服务
const categoryLevel1API = {
  // 获取分类列表（分页）
  getCategories: (current = 1, size = 10) => {
    return api.get(`/product-category-level1/page?current=${current}&size=${size}`);
  },

  // 获取所有分类
  getAllCategories: () => {
    return api.get('/product-category-level1/list');
  },

  // 获取启用的分类
  getEnabledCategories: () => {
    return api.get('/product-category-level1/enabled');
  },

  // 根据ID获取分类
  getCategoryById: (id) => {
    return api.get(`/product-category-level1/${id}`);
  },

  // 创建分类
  createCategory: (categoryData) => {
    return api.post('/product-category-level1', categoryData);
  },

  // 更新分类
  updateCategory: (categoryData) => {
    return api.put('/product-category-level1', categoryData);
  },

  // 删除分类
  deleteCategory: (id) => {
    return api.delete(`/product-category-level1/${id}`);
  },
};

export default categoryLevel1API;