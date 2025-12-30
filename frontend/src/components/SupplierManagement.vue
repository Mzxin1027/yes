<template>
  <div class="supplier-management">
    <h2>供应商管理</h2>
    
    <div class="supplier-list">
      <div v-for="supplier in suppliers" :key="supplier.id" class="supplier-card">
        <div class="supplier-header">
          <h3>{{ supplier.name }}</h3>
          <span class="supplier-status" :class="{'status-active': supplier.status === 1, 'status-inactive': supplier.status === 0}">
            {{ supplier.status === 1 ? '启用' : '禁用' }}
          </span>
        </div>
        
        <div class="supplier-info">
          <p v-if="supplier.contactPerson"><strong>联系人:</strong> {{ supplier.contactPerson }}</p>
          <p v-if="supplier.phone"><strong>电话:</strong> {{ supplier.phone }}</p>
          <p v-if="supplier.email"><strong>邮箱:</strong> {{ supplier.email }}</p>
          <p v-if="supplier.address"><strong>地址:</strong> {{ supplier.address }}</p>
          <p v-if="supplier.remark"><strong>备注:</strong> {{ supplier.remark }}</p>
        </div>
      </div>
    </div>

    <div v-if="loading" class="loading">加载中...</div>
    <div v-if="error" class="error">{{ error }}</div>
  </div>
</template>

<script>
import { ref, onMounted } from 'vue';
import supplierAPI from '@/api/supplierAPI';

export default {
  name: 'SupplierManagement',
  setup() {
    const suppliers = ref([]);
    const loading = ref(false);
    const error = ref('');

    const fetchSuppliers = async () => {
      try {
        loading.value = true;
        error.value = '';
        const response = await supplierAPI.getEnabledSuppliers();
        suppliers.value = response.data;
      } catch (err) {
        error.value = '获取供应商信息失败: ' + err.message;
        console.error('获取供应商信息失败:', err);
      } finally {
        loading.value = false;
      }
    };

    onMounted(() => {
      fetchSuppliers();
    });

    return {
      suppliers,
      loading,
      error
    };
  }
};
</script>

<style scoped>
.supplier-management {
  padding: 20px;
}

.supplier-list {
  display: grid;
  grid-template-columns: repeat(auto-fill, minmax(350px, 1fr));
  gap: 15px;
}

.supplier-card {
  border: 1px solid #ddd;
  border-radius: 8px;
  overflow: hidden;
  box-shadow: 0 2px 8px rgba(0, 0, 0, 0.1);
  background-color: white;
}

.supplier-header {
  display: flex;
  justify-content: space-between;
  align-items: center;
  padding: 12px 15px;
  background-color: #f8f9fa;
  border-bottom: 1px solid #eee;
}

.supplier-header h3 {
  margin: 0;
  font-size: 1.1em;
}

.supplier-status {
  padding: 4px 8px;
  border-radius: 4px;
  font-size: 0.8em;
  font-weight: bold;
}

.status-active {
  background-color: #27ae60;
  color: white;
}

.status-inactive {
  background-color: #95a5a6;
  color: white;
}

.supplier-info {
  padding: 15px;
}

.supplier-info p {
  margin: 8px 0;
  word-break: break-all;
}

.supplier-info strong {
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
  .supplier-list {
    grid-template-columns: 1fr;
  }
}
</style>