import Vue from 'vue'
import Router from 'vue-router'
import HomeView from '../views/HomeView.vue'
import About from '../views/AboutView'
import SignInView from '../views/SignInView'
import HelloUser from '../views/HelloUser.vue'
import YourTrip from '../views/YourTrip.vue'

Vue.use(Router)
/* eslint-disable */

export default new Router({
  mode: 'history',
  routes:  [
    {
      path: '/',
      name: 'home',
      component: HomeView
    },
    {
      path: '/sign-in/',
      name: 'sginIn',
      component: SignInView
    },
    {
      path: '/about',
      name: 'about',
      component: About
    },
    {
      path: '/hello-user',
      name: 'helloUser',
      component: HelloUser
    },
    {
      path: '/your-trip',
      name: 'yourTrip',
      component: YourTrip
    }
  ]
})