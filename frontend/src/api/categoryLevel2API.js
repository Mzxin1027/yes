import api from './axios';

// 产品二级分类相关的API服务
const categoryLevel2API = {
  // 获取分类列表（分页）
  getCategories: (current = 1, size = 10) => {
    return api.get(`/product-category-level2/page?current=${current}&size=${size}`);
  },

  // 获取所有分类
  getAllCategories: () => {
    return api.get('/product-category-level2/list');
  },

  // 根据父分类ID获取启用的二级分类
  getCategoriesByParentId: (parentId) => {
    return api.get(`/product-category-level2/by-parent/${parentId}`);
  },

  // 根据ID获取分类
  getCategoryById: (id) => {
    return api.get(`/product-category-level2/${id}`);
  },

  // 创建分类
  createCategory: (categoryData) => {
    return api.post('/product-category-level2', categoryData);
  },

  // 更新分类
  updateCategory: (categoryData) => {
    return api.put('/product-category-level2', categoryData);
  },

  // 删除分类
  deleteCategory: (id) => {
    return api.delete(`/product-category-level2/${id}`);
  },
};

export default categoryLevel2API;