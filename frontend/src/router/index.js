import { createRouter, createWebHistory } from 'vue-router';
import ProductList from '../components/ProductList.vue';
import CategoryList from '../components/CategoryList.vue';
import ProductDetail from '../components/ProductDetail.vue';
import InOutRecordList from '../components/InOutRecordList.vue';
import StockManagement from '../components/StockManagement.vue';
import SupplierManagement from '../components/SupplierManagement.vue';

const routes = [
  {
    path: '/',
    name: 'Home',
    redirect: '/products'
  },
  {
    path: '/products',
    name: 'ProductList',
    component: ProductList
  },
  {
    path: '/categories',
    name: 'CategoryList',
    component: CategoryList
  },
  {
    path: '/product/:id',
    name: 'ProductDetail',
    component: ProductDetail,
    props: true
  },
  {
    path: '/in-out-records',
    name: 'InOutRecordList',
    component: InOutRecordList
  },
  {
    path: '/stock',
    name: 'StockManagement',
    component: StockManagement
  },
  {
    path: '/suppliers',
    name: 'SupplierManagement',
    component: SupplierManagement
  }
];

const router = createRouter({
  history: createWebHistory(),
  routes
});

export default router;