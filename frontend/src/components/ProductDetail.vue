<template>
  <div class="product-detail" v-if="product">
    <div class="product-header">
      <img 
        :src="product.imageUrl || '/api/placeholder/400/400'" 
        :alt="product.name"
        class="product-image-large"
      />
      <div class="product-info">
        <h1>{{ product.name }}</h1>
        <p class="product-description">{{ product.description }}</p>
        <div class="product-specs">
          <div class="spec-item">
            <label>价格:</label>
            <span class="price">¥{{ product.price }}</span>
          </div>
          <div class="spec-item">
            <label>库存:</label>
            <span :class="{'stock-available': product.stock > 10, 'stock-low': product.stock <= 10 && product.stock > 0, 'stock-out': product.stock === 0}">
              {{ product.stock }} 件
            </span>
          </div>
          <div class="spec-item">
            <label>状态:</label>
            <span :class="{'status-active': product.status === 1, 'status-inactive': product.status === 0}">
              {{ product.status === 1 ? '上架' : '下架' }}
            </span>
          </div>
        </div>
      </div>
    </div>
  </div>
  <div v-else-if="loading" class="loading">加载中...</div>
  <div v-else-if="error" class="error">{{ error }}</div>
  <div v-else class="no-product">未找到商品信息</div>
</template>

<script>
import { ref, onMounted } from 'vue';
import { useRoute } from 'vue-router';
import productAPI from '@/api/productAPI';

export default {
  name: 'ProductDetail',
  setup() {
    const product = ref(null);
    const loading = ref(false);
    const error = ref('');
    const route = useRoute();

    const fetchProduct = async (id) => {
      try {
        loading.value = true;
        error.value = '';
        const response = await productAPI.getProductById(id);
        product.value = response.data;
      } catch (err) {
        error.value = '获取商品详情失败: ' + err.message;
        console.error('获取商品详情失败:', err);
      } finally {
        loading.value = false;
      }
    };

    onMounted(() => {
      const productId = route.params.id;
      if (productId) {
        fetchProduct(productId);
      }
    });

    return {
      product,
      loading,
      error
    };
  }
};
</script>

<style scoped>
.product-detail {
  padding: 20px;
}

.product-header {
  display: flex;
  gap: 30px;
  margin-bottom: 20px;
}

.product-image-large {
  width: 400px;
  height: 400px;
  object-fit: cover;
  border-radius: 8px;
  box-shadow: 0 4px 16px rgba(0, 0, 0, 0.1);
}

.product-info {
  flex: 1;
}

.product-info h1 {
  margin: 0 0 15px 0;
  color: #333;
  font-size: 2em;
}

.product-description {
  color: #666;
  line-height: 1.6;
  margin-bottom: 20px;
  font-size: 1.1em;
}

.product-specs {
  border-top: 1px solid #eee;
  padding-top: 20px;
}

.spec-item {
  display: flex;
  margin-bottom: 12px;
  align-items: center;
}

.spec-item label {
  font-weight: bold;
  width: 80px;
  color: #555;
}

.price {
  font-size: 1.5em;
  font-weight: bold;
  color: #e74c3c;
}

.stock-available {
  color: #27ae60;
}

.stock-low {
  color: #f39c12;
}

.stock-out {
  color: #e74c3c;
}

.status-active {
  color: #27ae60;
}

.status-inactive {
  color: #999;
}

.loading, .error, .no-product {
  text-align: center;
  padding: 40px;
  font-size: 1.2em;
}

.error {
  color: #e74c3c;
}

@media (max-width: 768px) {
  .product-header {
    flex-direction: column;
  }
  
  .product-image-large {
    width: 100%;
    height: 300px;
  }
}
</style>