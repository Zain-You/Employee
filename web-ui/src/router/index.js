import {createRouter, createWebHistory} from 'vue-router'

const router = createRouter({
    history: createWebHistory(import.meta.env.BASE_URL),
    routes: [
        {path: '/',  redirect:"/manager/home"},
        {path: '/manager', name: 'manager', component: () => import('../views/Manager.vue'), children:[
               // 子路由不需要'/'
                {path: 'about', name: 'about', component: () => import('../views/About.vue')},
                {path: 'test', name: 'test',meta:{title:"测试页面"}, component: () => import('../views/Test.vue')},
                {path: 'home', name: 'home', meta:{title:"主页"}, component: () => import('../views/Home.vue')},
                {path: 'data', name: 'data', meta:{title:"数据页面"}, component: () => import('../views/Data.vue')},
                {path: 'employee', name: 'employee', meta:{title:"员工信息"}, component: () => import('../views/Employee.vue')},
          ]},
        {path:'/404',name:'NOtFound',meta:{title:'404找不到页面'}, component:()=> import('../views/404.vue')},
        {path:'/:pathMatch(.*)',redirect:'/404'}
    ]
})

router.beforeEach((to, from, next)=>{

    document.title = to.meta.title;
    next(); // 必须调用
})


export default router