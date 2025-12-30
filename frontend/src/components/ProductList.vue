<template>
  <div class="product-list">
    <h2>商品列表</h2>
    <div class="product-grid">
      <div v-for="product in products" :key="product.id" class="product-card">
        <img 
          :src="product.imageUrl || '/api/placeholder/200/200'" 
          :alt="product.name"
          class="product-image"
        />
        <div class="product-info">
          <h3 class="product-name">{{ product.name }}</h3>
          <p class="product-description">{{ product.description }}</p>
          <div class="product-details">
            <span class="product-price">¥{{ product.price }}</span>
            <span class="product-stock">库存: {{ product.stock }}</span>
          </div>
          <div class="product-status">
            状态: <span :class="{'status-active': product.status === 1, 'status-inactive': product.status === 0}">
              {{ product.status === 1 ? '上架' : '下架' }}
            </span>
          </div>
        </div>
      </div>
    </div>
    <div v-if="loading" class="loading">加载中...</div>
    <div v-if="error" class="error">{{ error }}</div>
  </div>
</template>

<script>
import { ref, onMounted } from 'vue';
import productAPI from '@/api/productAPI';

export default {
  name: 'ProductList',
  setup() {
    const products = ref([]);
    const loading = ref(false);
    const error = ref('');

    const fetchProducts = async () => {
      try {
        loading.value = true;
        error.value = '';
        const response = await productAPI.getEnabledProducts();
        products.value = response.data;
      } catch (err) {
        error.value = '获取商品列表失败: ' + err.message;
        console.error('获取商品列表失败:', err);
      } finally {
        loading.value = false;
      }
    };

    onMounted(() => {
      fetchProducts();
    });

    return {
      products,
      loading,
      error
    };
  }
};
</script>

<style scoped>
.product-list {
  padding: 20px;
}

.product-grid {
  display: grid;
  grid-template-columns: repeat(auto-fill, minmax(300px, 1fr));
  gap: 20px;
  margin-top: 20px;
}

.product-card {
  border: 1px solid #ddd;
  border-radius: 8px;
  overflow: hidden;
  box-shadow: 0 2px 8px rgba(0, 0, 0, 0.1);
  transition: transform 0.2s, box-shadow 0.2s;
}

.product-card:hover {
  transform: translateY(-2px);
  box-shadow: 0 4px 16px rgba(0, 0, 0, 0.15);
}

.product-image {
  width: 100%;
  height: 200px;
  object-fit: cover;
}

.product-info {
  padding: 15px;
}

.product-name {
  margin: 0 0 10px 0;
  color: #333;
  font-size: 1.2em;
}

.product-description {
  color: #666;
  margin: 0 0 10px 0;
  line-height: 1.4;
}

.product-details {
  display: flex;
  justify-content: space-between;
  margin-bottom: 8px;
}

.product-price {
  font-weight: bold;
  color: #e74c3c;
  font-size: 1.1em;
}

.product-stock {
  color: #666;
}

.product-status {
  font-size: 0.9em;
  color: #666;
}

.status-active {
  color: #27ae60;
}

.status-inactive {
  color: #999;
}

.loading, .error {
  text-align: center;
  padding: 20px;
  font-size: 1.1em;
}

.error {
  color: #e74c3c;
}
</style>