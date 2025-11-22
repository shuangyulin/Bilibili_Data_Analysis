import VueRouter from 'vue-router'
//引入组件
import Index from '../pages'
import Home from '../pages/home/home'
import Login from '../pages/login/login'
import Register from '../pages/register/register'
import Center from '../pages/center/center'
import Storeup from '../pages/storeup/list'
import payList from '../pages/pay'

import yonghuList from '../pages/yonghu/list'
import yonghuDetail from '../pages/yonghu/detail'
import yonghuAdd from '../pages/yonghu/add'
import hotvideoList from '../pages/hotvideo/list'
import hotvideoDetail from '../pages/hotvideo/detail'
import hotvideoAdd from '../pages/hotvideo/add'
import hotvideoforecastList from '../pages/hotvideoforecast/list'
import hotvideoforecastDetail from '../pages/hotvideoforecast/detail'
import hotvideoforecastAdd from '../pages/hotvideoforecast/add'
import discusshotvideoList from '../pages/discusshotvideo/list'
import discusshotvideoDetail from '../pages/discusshotvideo/detail'
import discusshotvideoAdd from '../pages/discusshotvideo/add'

const originalPush = VueRouter.prototype.push
VueRouter.prototype.push = function push(location) {
	return originalPush.call(this, location).catch(err => err)
}

//配置路由
export default new VueRouter({
	routes:[
		{
      path: '/',
      redirect: '/index/home'
    },
		{
			path: '/index',
			component: Index,
			children:[
				{
					path: 'home',
					component: Home
				},
				{
					path: 'center',
					component: Center,
				},
				{
					path: 'pay',
					component: payList,
				},
				{
					path: 'storeup',
					component: Storeup
				},
				{
					path: 'yonghu',
					component: yonghuList
				},
				{
					path: 'yonghuDetail',
					component: yonghuDetail
				},
				{
					path: 'yonghuAdd',
					component: yonghuAdd
				},
				{
					path: 'hotvideo',
					component: hotvideoList
				},
				{
					path: 'hotvideoDetail',
					component: hotvideoDetail
				},
				{
					path: 'hotvideoAdd',
					component: hotvideoAdd
				},
				{
					path: 'hotvideoforecast',
					component: hotvideoforecastList
				},
				{
					path: 'hotvideoforecastDetail',
					component: hotvideoforecastDetail
				},
				{
					path: 'hotvideoforecastAdd',
					component: hotvideoforecastAdd
				},
				{
					path: 'discusshotvideo',
					component: discusshotvideoList
				},
				{
					path: 'discusshotvideoDetail',
					component: discusshotvideoDetail
				},
				{
					path: 'discusshotvideoAdd',
					component: discusshotvideoAdd
				},
			]
		},
		{
			path: '/login',
			component: Login
		},
		{
			path: '/register',
			component: Register
		},
	]
})
