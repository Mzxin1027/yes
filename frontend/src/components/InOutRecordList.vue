<template>
  <div class="in-out-record-list">
    <h2>出入库记录</h2>
    
    <div class="filters">
      <div class="filter-item">
        <label>记录类型:</label>
        <select v-model="selectedType" @change="fetchRecords">
          <option value="">全部</option>
          <option value="1">入库</option>
          <option value="2">出库</option>
        </select>
      </div>
    </div>

    <div class="record-list">
      <div v-for="record in records" :key="record.id" class="record-card">
        <div class="record-header">
          <span class="record-type" :class="{'in': record.type === 1, 'out': record.type === 2}">
            {{ record.type === 1 ? '入库' : '出库' }}
          </span>
          <span class="record-time">{{ formatDate(record.createTime) }}</span>
        </div>
        
        <div class="record-body">
          <div class="record-info">
            <p><strong>商品:</strong> {{ record.productName || record.productId }}</p>
            <p><strong>数量:</strong> {{ record.quantity }}</p>
            <p v-if="record.unitPrice"><strong>单价:</strong> ¥{{ record.unitPrice }}</p>
            <p v-if="record.totalAmount"><strong>总金额:</strong> ¥{{ record.totalAmount }}</p>
            <p v-if="record.supplierName"><strong>供应商:</strong> {{ record.supplierName }}</p>
            <p v-if="record.operator"><strong>操作员:</strong> {{ record.operator }}</p>
            <p v-if="record.remark"><strong>备注:</strong> {{ record.remark }}</p>
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
import inOutRecordAPI from '@/api/inOutRecordAPI';

export default {
  name: 'InOutRecordList',
  setup() {
    const records = ref([]);
    const loading = ref(false);
    const error = ref('');
    const selectedType = ref('');

    const fetchRecords = async () => {
      try {
        loading.value = true;
        error.value = '';
        const response = await inOutRecordAPI.getRecordsWithType(1, 50, selectedType.value ? parseInt(selectedType.value) : null);
        records.value = response.data.records || response.data;
      } catch (err) {
        error.value = '获取出入库记录失败: ' + err.message;
        console.error('获取出入库记录失败:', err);
      } finally {
        loading.value = false;
      }
    };

    const formatDate = (dateString) => {
      if (!dateString) return '';
      const date = new Date(dateString);
      return date.toLocaleString('zh-CN');
    };

    onMounted(() => {
      fetchRecords();
    });

    return {
      records,
      loading,
      error,
      selectedType,
      fetchRecords,
      formatDate
    };
  }
};
</script>

<style scoped>
.in-out-record-list {
  padding: 20px;
}

.filters {
  display: flex;
  gap: 20px;
  margin-bottom: 20px;
  padding: 15px;
  background-color: #f8f9fa;
  border-radius: 8px;
}

.filter-item {
  display: flex;
  align-items: center;
  gap: 8px;
}

.filter-item label {
  font-weight: bold;
}

.record-list {
  display: grid;
  grid-template-columns: repeat(auto-fill, minmax(400px, 1fr));
  gap: 15px;
}

.record-card {
  border: 1px solid #ddd;
  border-radius: 8px;
  overflow: hidden;
  box-shadow: 0 2px 8px rgba(0, 0, 0, 0.1);
  background-color: white;
}

.record-header {
  display: flex;
  justify-content: space-between;
  align-items: center;
  padding: 12px 15px;
  background-color: #f8f9fa;
  border-bottom: 1px solid #eee;
}

.record-type {
  font-weight: bold;
  padding: 4px 8px;
  border-radius: 4px;
  color: white;
}

.record-type.in {
  background-color: #27ae60;
}

.record-type.out {
  background-color: #e74c3c;
}

.record-time {
  font-size: 0.85em;
  color: #666;
}

.record-body {
  padding: 15px;
}

.record-info p {
  margin: 8px 0;
  word-break: break-all;
}

.record-info strong {
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
  .record-list {
    grid-template-columns: 1fr;
  }
  
  .filters {
    flex-direction: column;
  }
}
</style>