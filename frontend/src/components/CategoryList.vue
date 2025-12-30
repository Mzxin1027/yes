<template>
  <div class="category-list">
    <h2>商品分类</h2>
    <div class="category-container">
      <div class="category-level1">
        <h3>一级分类</h3>
        <div class="category-grid">
          <div 
            v-for="category in level1Categories" 
            :key="category.id" 
            class="category-card"
            :class="{ active: selectedCategory1 === category.id }"
            @click="selectCategory1(category.id)"
          >
            <h4>{{ category.name }}</h4>
            <p>{{ category.description }}</p>
            <div class="category-status">
              状态: <span :class="{'status-active': category.status === 1, 'status-inactive': category.status === 0}">
                {{ category.status === 1 ? '启用' : '禁用' }}
              </span>
            </div>
          </div>
        </div>
      </div>
      
      <div class="category-level2" v-if="selectedCategory1">
        <h3>二级分类</h3>
        <div class="category-grid">
          <div 
            v-for="category in level2Categories" 
            :key="category.id" 
            class="category-card"
          >
            <h4>{{ category.name }}</h4>
            <p>{{ category.description }}</p>
            <div class="category-status">
              状态: <span :class="{'status-active': category.status === 1, 'status-inactive': category.status === 0}">
                {{ category.status === 1 ? '启用' : '禁用' }}
              </span>
            </div>
          </div>
        </div>
      </div>
    </div>
    
    <div v-if="loading" class="loading">加载中...</div>
    <div v-if="error" class="error">{{ error }}</div>
  </div>
</template>

<script>
import { ref, onMounted, watch } from 'vue';
import categoryLevel1API from '@/api/categoryLevel1API';
import categoryLevel2API from '@/api/categoryLevel2API';

export default {
  name: 'CategoryList',
  setup() {
    const level1Categories = ref([]);
    const level2Categories = ref([]);
    const selectedCategory1 = ref(null);
    const loading = ref(false);
    const error = ref('');

    const fetchLevel1Categories = async () => {
      try {
        loading.value = true;
        error.value = '';
        const response = await categoryLevel1API.getEnabledCategories();
        level1Categories.value = response.data;
      } catch (err) {
        error.value = '获取一级分类失败: ' + err.message;
        console.error('获取一级分类失败:', err);
      } finally {
        loading.value = false;
      }
    };

    const fetchLevel2Categories = async (parentId) => {
      try {
        const response = await categoryLevel2API.getCategoriesByParentId(parentId);
        level2Categories.value = response.data;
      } catch (err) {
        error.value = '获取二级分类失败: ' + err.message;
        console.error('获取二级分类失败:', err);
      }
    };

    const selectCategory1 = async (categoryId) => {
      selectedCategory1.value = categoryId;
      await fetchLevel2Categories(categoryId);
    };

    watch(selectedCategory1, async (newVal) => {
      if (newVal) {
        await fetchLevel2Categories(newVal);
      }
    });

    onMounted(() => {
      fetchLevel1Categories();
    });

    return {
      level1Categories,
      level2Categories,
      selectedCategory1,
      loading,
      error,
      selectCategory1
    };
  }
};
</script>

<style scoped>
.category-list {
  padding: 20px;
}

.category-container {
  display: flex;
  gap: 20px;
  margin-top: 20px;
}

.category-level1, .category-level2 {
  flex: 1;
}

.category-grid {
  display: grid;
  grid-template-columns: repeat(auto-fill, minmax(250px, 1fr));
  gap: 15px;
  margin-top: 15px;
}

.category-card {
  border: 1px solid #ddd;
  border-radius: 8px;
  padding: 15px;
  cursor: pointer;
  transition: all 0.2s;
  background-color: #fff;
}

.category-card:hover {
  box-shadow: 0 4px 12px rgba(0, 0, 0, 0.1);
}

.category-card.active {
  border-color: #3498db;
  background-color: #f8f9fa;
  box-shadow: 0 4px 12px rgba(52, 152, 219, 0.2);
}

.category-card h4 {
  margin: 0 0 8px 0;
  color: #333;
}

.category-card p {
  margin: 0 0 8px 0;
  color: #666;
  font-size: 0.9em;
}

.category-status {
  font-size: 0.85em;
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

@media (max-width: 768px) {
  .category-container {
    flex-direction: column;
  }
}
</style>