<template>
  <div class="stock-management">
    <h2>库存管理</h2>
    
    <div class="stock-actions">
      <button @click="showLowStock = !showLowStock" class="btn btn-secondary">
        {{ showLowStock ? '显示全部' : '显示低库存' }}
      </button>
      <button @click="showOutOfStock = !showOutOfStock" class="btn btn-secondary">
        {{ showOutOfStock ? '显示全部' : '显示缺货' }}
      </button>
    </div>

    <div class="stock-list">
      <div v-for="stock in filteredStocks" :key="stock.id" class="stock-card">
        <div class="stock-header">
          <h3>{{ stock.productName || stock.productId }}</h3>
          <span class="stock-status" :class="getStatusClass(stock)">
            {{ getStatusText(stock) }}
          </span>
        </div>
        
        <div class="stock-info">
          <p><strong>当前库存:</strong> {{ stock.currentStock }}</p>
          <p><strong>安全库存:</strong> {{ stock.safeStock }}</p>
          <p v-if="stock.maxStock"><strong>最大库存:</strong> {{ stock.maxStock }}</p>
          <p v-if="stock.minStock"><strong>最小库存:</strong> {{ stock.minStock }}</p>
          <p v-if="stock.unit"><strong>单位:</strong> {{ stock.unit }}</p>
        </div>
      </div>
    </div>

    <div v-if="loading" class="loading">加载中...</div>
    <div v-if="error" class="error">{{ error }}</div>
  </div>
</template>

<script>
import { ref, onMounted, computed } from 'vue';
import stockAPI from '@/api/stockAPI';

export default {
  name: 'StockManagement',
  setup() {
    const stocks = ref([]);
    const loading = ref(false);
    const error = ref('');
    const showLowStock = ref(false);
    const showOutOfStock = ref(false);

    const fetchStocks = async () => {
      try {
        loading.value = true;
        error.value = '';
        const response = await stockAPI.getAllStocks();
        stocks.value = response.data;
      } catch (err) {
        error.value = '获取库存信息失败: ' + err.message;
        console.error('获取库存信息失败:', err);
      } finally {
        loading.value = false;
      }
    };

    const filteredStocks = computed(() => {
      if (showOutOfStock.value) {
        return stocks.value.filter(stock => stock.currentStock === 0);
      } else if (showLowStock.value) {
        return stocks.value.filter(stock => stock.currentStock <= stock.safeStock);
      }
      return stocks.value;
    });

    const getStatusText = (stock) => {
      if (stock.currentStock === 0) {
        return '缺货';
      } else if (stock.currentStock <= stock.safeStock) {
        return '库存不足';
      } else if (stock.currentStock > stock.safeStock * 2) {
        return '库存充足';
      }
      return '正常';
    };

    const getStatusClass = (stock) => {
      if (stock.currentStock === 0) {
        return 'status-out-of-stock';
      } else if (stock.currentStock <= stock.safeStock) {
        return 'status-low-stock';
      }
      return 'status-normal';
    };

    onMounted(() => {
      fetchStocks();
    });

    return {
      stocks,
      loading,
      error,
      showLowStock,
      showOutOfStock,
      filteredStocks,
      getStatusText,
      getStatusClass
    };
  }
};
</script>

<style scoped>
.stock-management {
  padding: 20px;
}

.stock-actions {
  display: flex;
  gap: 10px;
  margin-bottom: 20px;
}

.btn {
  padding: 8px 16px;
  border: none;
  border-radius: 4px;
  cursor: pointer;
  font-size: 0.9em;
}

.btn-secondary {
  background-color: #6c757d;
  color: white;
}

.btn-secondary:hover {
  background-color: #5a6268;
}

.stock-list {
  display: grid;
  grid-template-columns: repeat(auto-fill, minmax(300px, 1fr));
  gap: 15px;
}

.stock-card {
  border: 1px solid #ddd;
  border-radius: 8px;
  overflow: hidden;
  box-shadow: 0 2px 8px rgba(0, 0, 0, 0.1);
  background-color: white;
}

.stock-header {
  display: flex;
  justify-content: space-between;
  align-items: center;
  padding: 12px 15px;
  background-color: #f8f9fa;
  border-bottom: 1px solid #eee;
}

.stock-header h3 {
  margin: 0;
  font-size: 1.1em;
}

.stock-status {
  padding: 4px 8px;
  border-radius: 4px;
  font-size: 0.8em;
  font-weight: bold;
}

.status-out-of-stock {
  background-color: #e74c3c;
  color: white;
}

.status-low-stock {
  background-color: #f39c12;
  color: white;
}

.status-normal {
  background-color: #27ae60;
  color: white;
}

.stock-info {
  padding: 15px;
}

.stock-info p {
  margin: 8px 0;
}

.stock-info strong {
  color: #333;
}

.loading, .error {
  text-align: center;
  padding: 20px;
  font-size: 1.1em;
}

.error {
  color: #e74c3c;
}

@media (max-width: 768px) {
  .stock-list {
    grid-template-columns: 1fr;
  }
  
  .stock-actions {
    flex-direction: column;
  }
}
</style>