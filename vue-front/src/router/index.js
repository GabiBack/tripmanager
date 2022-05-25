import { createRouter, createWebHistory } from 'vue-router'
import HomeView from '../views/HomeView.vue'
import About from '../views/AboutView'
import SignInView from '../views/SignInView'
import WelcomeSite from '../views/WelcomeSite'

const routes = [
  {
    path: '/welcomeSite',
    name: 'welcomeSite',
    component: WelcomeSite
  },
  {
    path: '/home',
    name: 'home',
    component: HomeView
  },
  {
    path: '/sign-in',
    name: 'sginIn',
    component: SignInView,
  },
  {
    path: '/about',
    name: 'about',
    component: About,
  }
]

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes
})

export default router
