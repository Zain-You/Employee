import {createRouter, createWebHistory} from 'vue-router'

const router = createRouter({
    history: createWebHistory(import.meta.env.BASE_URL),
    routes: [
        {path: '/',  redirect:"/login"},
        {path: '/manager', name: 'manager', component: () => import('../views/Manager.vue'), children:[
               // 子路由不需要'/'
                {path: 'about', name: 'about', component: () => import('../views/About.vue')},
                {path: 'test', name: 'test',meta:{title:"测试页面"}, component: () => import('../views/Test.vue')},
                {path: 'home', name: 'home', meta:{title:"主页"}, component: () => import('../views/Home.vue')},
                {path: 'data', name: 'data', meta:{title:"数据页面"}, component: () => import('../views/Data.vue')},
                {path: 'employee', name: 'employee', meta:{title:"员工信息"}, component: () => import('../views/Employee.vue')},
                {path: 'admin', name: 'admin', meta:{title:"管理员信息"}, component: () => import('../views/Admin.vue')},
                {path: 'person', name: 'person', meta:{title:"个人信息"}, component: () => import('../views/Person.vue')},
                {path: 'password', name: 'password', meta:{title:"修改密码"}, component: () => import('../views/Password.vue')},
          ]},
        {path:'/login',name:'login',meta:{title:'登录系统'}, component:()=> import('../views/Login.vue')},
        {path:'/register',name:'register',meta:{title:'注册'}, component:()=> import('../views/Register.vue')},
        {path:'/404',name:'NOtFound',meta:{title:'404找不到页面'}, component:()=> import('../views/404.vue')},
        {path:'/:pathMatch(.*)',redirect:'/404'}
    ]
})

router.beforeEach((to, from, next)=>{

    document.title = to.meta.title;
    next(); // 必须调用
})


export default router