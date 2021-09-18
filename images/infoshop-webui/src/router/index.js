import { createWebHistory, createRouter } from "vue-router";
import Product from "@/views/Product.vue";
import Feedback from "@/views/Feedback.vue";
import Ranking from "@/views/Ranking.vue";
import NotFound from "@/views/NotFound.vue";

const routes = [
  {
    path: "/",
    name: "Product",
    component: Product,
  },
  {
    path: "/product",
    name: "Product2",
    component: Product,
  },
  {
    path: "/feedback/:id",
    name: "Feedback",
    component: Feedback,
  },
  {
    path: "/ranking",
    name: "Ranking",
    component: Ranking,
  },
  { path: "/:pathMatch(.*)*", name: "not-found", component: NotFound },
];

const router = createRouter({
  history: createWebHistory(),
  routes,
});

export default router;
