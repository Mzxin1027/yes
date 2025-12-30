<template>
  <div id="app">
    <nav class="navbar">
      <div class="nav-container">
        <h1 class="nav-title">物流管理系统</h1>
        <ul class="nav-menu">
          <li class="nav-item">
            <router-link to="/products" class="nav-link">商品列表</router-link>
          </li>
          <li class="nav-item">
            <router-link to="/categories" class="nav-link">分类管理</router-link>
          </li>
          <li class="nav-item">
            <router-link to="/in-out-records" class="nav-link">出入库记录</router-link>
          </li>
          <li class="nav-item">
            <router-link to="/stock" class="nav-link">库存管理</router-link>
          </li>
          <li class="nav-item">
            <router-link to="/suppliers" class="nav-link">供应商管理</router-link>
          </li>
        </ul>
      </div>
    </nav>
    
    <main class="main-content">
      <router-view />
    </main>
    
    <footer class="footer">
      <div class="footer-content">
        <p>&copy; 2025 物流管理系统. 保留所有权利.</p>
      </div>
    </footer>
  </div>
</template>

<script>
export default {
  name: 'App'
}
</script>

<style>
* {
  margin: 0;
  padding: 0;
  box-sizing: border-box;
}

body {
  font-family: 'Arial', sans-serif;
  background-color: #f5f5f5;
}

#app {
  min-height: 100vh;
  display: flex;
  flex-direction: column;
}

.navbar {
  background-color: #34495e;
  color: white;
  padding: 1rem 0;
  box-shadow: 0 2px 4px rgba(0,0,0,0.1);
}

.nav-container {
  max-width: 1200px;
  margin: 0 auto;
  display: flex;
  justify-content: space-between;
  align-items: center;
  padding: 0 20px;
}

.nav-title {
  font-size: 1.5rem;
  margin: 0;
}

.nav-menu {
  display: flex;
  list-style: none;
  gap: 2rem;
}

.nav-link {
  color: white;
  text-decoration: none;
  padding: 0.5rem 1rem;
  border-radius: 4px;
  transition: background-color 0.3s;
}

.nav-link:hover, .nav-link.router-link-active {
  background-color: #2c3e50;
}

.main-content {
  flex: 1;
  max-width: 1200px;
  margin: 0 auto;
  width: 100%;
  padding: 20px;
}

.footer {
  background-color: #34495e;
  color: white;
  text-align: center;
  padding: 1rem 0;
  margin-top: auto;
}

.footer-content {
  max-width: 1200px;
  margin: 0 auto;
  padding: 0 20px;
}

/* 响应式设计 */
@media (max-width: 768px) {
  .nav-container {
    flex-direction: column;
    gap: 1rem;
  }
  
  .nav-menu {
    gap: 1rem;
  }
  
  .main-content {
    padding: 10px;
  }
}
</style>