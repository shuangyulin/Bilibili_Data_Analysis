<template>
	<div class="home-preview">




		<!-- 商品推荐 -->
		<div id="animate_recommendhotvideo" class="recommend animate__animated">
			<div class="recommend_title_box">
				<span class="recommend_title">热门视频推荐</span>
				<span class="recommend_subhead">{{'hotvideo'.toUpperCase()}} RECOMMEND</span>
			</div>
			<div class="list list18 index-pv1">
				<div class="list-body" v-if="hotvideoRecommend.length">
					<div class="list-body-left">
						<div class="list-item1" @click="toDetail('hotvideoDetail', hotvideoRecommend[0])">
							<div class="infoBox">
								<div class="name">{{hotvideoRecommend[0].title}}</div>
								<div class="time_item">
									<span class="icon iconfont icon-shijian21"></span>
									<span class="label">发布时间：</span>
									<span class="text">{{hotvideoRecommend[0].addtime.split(' ')[0]}}</span>
								</div>
								<div class="collect_item">
									<span class="icon iconfont icon-shoucang10"></span>
									<span class="label">收藏：</span>
									<span class="text">{{hotvideoRecommend[0].storeupnum}}</span>
								</div>
							</div>
							<div class="img">
								<img v-if="preHttp(hotvideoRecommend[0].cover)&&preHttp2(hotvideoRecommend[0].cover)" :src="hotvideoRecommend[0].cover" alt="" />
								<img v-else-if="preHttp(hotvideoRecommend[0].cover)" :src="hotvideoRecommend[0].cover.split(',')[0]" alt="" />
								<img v-else :src="baseUrl + (hotvideoRecommend[0].cover?hotvideoRecommend[0].cover.split(',')[0]:'')" alt="" />
							</div>
						</div>
						<div class="list-item2" @click="toDetail('hotvideoDetail', hotvideoRecommend[1])">
							<div class="img">
								<img v-if="preHttp(hotvideoRecommend[1].cover)&&preHttp2(hotvideoRecommend[1].cover)" :src="hotvideoRecommend[1].cover" alt="" />
								<img v-else-if="preHttp(hotvideoRecommend[1].cover)" :src="hotvideoRecommend[1].cover.split(',')[0]" alt="" />
								<img v-else :src="baseUrl + (hotvideoRecommend[1].cover?hotvideoRecommend[1].cover.split(',')[0]:'')" alt="" />
							</div>
							<div class="infoBox">
								<div class="name">{{hotvideoRecommend[1].title}}</div>
								<div class="time_item">
									<span class="icon iconfont icon-shijian21"></span>
									<span class="label">发布时间：</span>
									<span class="text">{{hotvideoRecommend[1].addtime.split(' ')[0]}}</span>
								</div>
								<div class="collect_item">
									<span class="icon iconfont icon-shoucang10"></span>
									<span class="label">收藏：</span>
									<span class="text">{{hotvideoRecommend[1].storeupnum}}</span>
								</div>
							</div>
						</div>
					</div>
					<div class="list-body-right">
						<div class="swiper-container recommendSwiper18 recommend-list-18hotvideo">
							<div class="swiper-wrapper">
								<div class="swiper-slide" v-for="(item,index) in hotvideoRecommend" :key="index" v-if="index>2">
									<div class="list-item" @click="toDetail('hotvideoDetail', item)">
										<div class="img">
											<img v-if="preHttp(item.cover)&&preHttp2(item.cover)" :src="item.cover" alt="" />
											<img v-else-if="preHttp(item.cover)" :src="item.cover.split(',')[0]" alt="" />
											<img v-else :src="baseUrl + (item.cover?item.cover.split(',')[0]:'')" alt="" />
										</div>
										<div class="infoBox">
											<div class="name">{{item.title}}</div>
											<div class="time_item">
												<span class="icon iconfont icon-shijian21"></span>
												<span class="label">发布时间：</span>
												<span class="text">{{item.addtime.split(' ')[0]}}</span>
											</div>
											<div class="collect_item">
												<span class="icon iconfont icon-shoucang10"></span>
												<span class="label">收藏：</span>
												<span class="text">{{item.storeupnum}}</span>
											</div>
										</div>
									</div>
								</div>
							</div>
							<div class="swiper-button-prev"></div>
							<div class="swiper-button-next"></div>
							<div class="swiper-pagination"></div>
						</div>
					</div>
				</div>
			</div>
			<div class="moreBtn" @click="moreBtn('hotvideo')">
				<span class="text">查看更多</span>
				<i class="icon iconfont icon-gengduo1"></i>
			</div>
		</div>
		<!-- 商品推荐 -->
	</div>
</template>

<script>
import 'animate.css'
import Swiper from "swiper";

	export default {
		//数据集合
		data() {
			return {
				baseUrl: '',
				newsList: [],
				hotvideoRecommend: [],



				recommendListSwiper18hotvideo: null,


			}
		},
		created() {
			this.baseUrl = this.$config.baseUrl;
			this.getList();
		},
		mounted() {
			window.addEventListener('scroll', this.handleScroll)
			setTimeout(()=>{
				this.handleScroll()
			},100)
			
			this.swiperChanges()
		},
		beforeDestroy() {
			window.removeEventListener('scroll', this.handleScroll)
		},
		//方法集合
		methods: {
			swiperChanges() {
				if (this['recommendListSwiper18hotvideo']) this['recommendListSwiper18hotvideo'].destroy()
				setTimeout(()=>{
					this['recommendListSwiper18hotvideo'] = new Swiper(".recommend-list-18hotvideo", {"navigation":{"nextEl":".swiper-button-next","prevEl":".swiper-button-prev"},"pagination":{"el":".swiper-pagination","clickable":true},"loop":true,"coverflowEffect":{"rotate":10,"stretch":10,"depth":60,"modifier":3,"slideShadows":false},"centeredSlides":true,"slidesPerView":1,"spaceBetween":0,"autoplay":{"delay":3000,"disableOnInteraction":false}})
				},750)
			},
			async recommendIndexClick18(index, name) {
				this['recommendIndex18' + name] = index
				await this.getList()
			},


			handleScroll() {
				let arr = [
					{id:'about',css:'animate__'},
					{id:'system',css:'animate__'},
					{id:'animate_recommendhotvideo',css:'animate__'},
				]
			
				for (let i in arr) {
					let doc = document.getElementById(arr[i].id)
					if (doc) {
						let top = doc.offsetTop
						let win_top = window.innerHeight + window.pageYOffset
						// console.log(top,win_top)
						if (win_top > top && doc.classList.value.indexOf(arr[i].css) < 0) {
							// console.log(doc)
							doc.classList.add(arr[i].css)
						}
					}
				}
			},
			preHttp(str) {
				return str && str.substr(0,4)=='http';
			},
			preHttp2(str) {
				return str && str.split(',w').length>1;
			},
			getList() {
				let autoSortUrl = "";
				let data = {}
				autoSortUrl = "hotvideo/autoSort";
				if(localStorage.getItem('frontToken')) {
					autoSortUrl = "hotvideo/autoSort2";
				}
				data = {
					page: 1,
					limit: 8,
				}
				this.$http.get(autoSortUrl, {params: data}).then(res => {
					if (res.data.code == 0) {
						this.hotvideoRecommend = res.data.data.list;
					}
				});
			
			},
			toDetail(path, item) {
				this.$router.push({path: '/index/' + path, query: {id: item.id}});
			},
			moreBtn(path) {
				this.$router.push({path: '/index/' + path});
			}
		}
	}
</script>

<style rel="stylesheet/scss" lang="scss" scoped>
	.home-preview {
		margin: 0px auto;
		flex-direction: column;
		background: #fff;
		display: flex;
		width: 100%;
		.recommend {
			padding: 60px 0 96px;
			margin: 0;
			align-content: flex-start;
			background: url(http://codegen.caihongy.cn/20241021/dc5560406d4146e1ada337e5a58f1599.jpg) no-repeat center bottom;
			display: flex;
			width: 100%;
			justify-content: center;
			align-items: flex-start;
			position: relative;
			order: 3;
			.recommend_title_box {
				padding: 20px 0 63px;
				margin: 0;
				background: url(http://codegen.caihongy.cn/20241021/84fcb60c88f446dd90bb6c22d0baf026.png) no-repeat center 102%,#0674fc;
				display: flex;
				width: 100px;
				min-height: 300px;
				position: relative;
				text-align: center;
				.recommend_title {
					margin: 0 auto;
					writing-mode: vertical-rl;
					color: #fff;
					background: none;
					font-weight: 600;
					display: block;
					letter-spacing: 8px;
					width: auto;
					font-size: 32px;
					line-height: 1;
					text-align: center;
				}
				.recommend_subhead {
					margin: 0;
					color: #999;
					display: none;
					width: 100%;
					font-size: 18px;
					line-height: 40px;
					text-align: center;
				}
			}
			.index-pv1 .animation-box {
				transform: rotate(0deg) scale(1) skew(0deg, 0deg) translate3d(0px, 0px, 0px);
				z-index: initial;
			}
			
			.index-pv1 .animation-box:hover {
				transform: rotate(0deg) scale(1) skew(0deg, 0deg) translate3d(0px, 0px, 0px);
				-webkit-perspective: 1000px;
				perspective: 1000px;
				transition: 0.3s;
				z-index: 1;
			}
			
			.index-pv1 .animation-box img {
				transform: rotate(0deg) scale(1) skew(0deg, 0deg) translate3d(0px, 0px, 0px);
			}
			
			.index-pv1 .animation-box img:hover {
				transform: rotate(0deg) scale(1) skew(0deg, 0deg) translate3d(0px, 0px, 0px);
				-webkit-perspective: 1000px;
				perspective: 1000px;
				transition: 0.3s;
			}
			.list18 {
				padding: 50px 20px;
				margin: 0;
				color: #666;
				background: #fff;
				width: 1300px;
				font-size: 14px;
				.tab_view {
					margin: 10px auto;
					display: none;
					width: 60%;
					justify-content: center;
					.tab {
						cursor: pointer;
						border-radius: 5px;
						margin: 0 5px 10px;
						color: #333;
						background: #eee;
						display: block;
						width: 140px;
						font-size: 14px;
						line-height: 40px;
						text-align: center;
						height: 40px;
					}
					.tab:hover {
						color: #ddd;
						background: #0195df;
					}
					.tab.tabActive {
						color: #fff;
						background: #5555ff;
					}
				}
				.list-body {
					padding: 0 10px;
					display: flex;
					width: 100%;
					.list-body-left {
						margin: 0 auto 0 0;
						width: 49%;
						.list-item1 {
							display: flex;
							width: 100%;
							align-items: center;
							transition: all 0s;
							.infoBox {
								padding: 20px;
								color: #0674fc;
								align-content: center;
								display: flex;
								width: 50%;
								border-color: #929292;
								border-width: 1px 0 0 1px;
								align-items: center;
								border-style: solid;
								flex-wrap: wrap;
								height: 300px;
								.name {
									overflow: hidden;
									color: #000;
									white-space: nowrap;
									width: 100%;
									font-size: 16px;
									line-height: 1.5;
									text-overflow: ellipsis;
								}
								.price {
									padding: 10px 0;
									color: #f00;
									width: 100%;
									font-size: 16px;
									line-height: 1.5;
								}
								.time_item {
									padding: 0;
									display: inline-block;
									.icon {
										margin: 0 2px 0 0;
										color: inherit;
										display: none;
										font-size: inherit;
										line-height: 1.5;
									}
									.label {
										color: inherit;
										font-size: inherit;
										line-height: 1.5;
									}
									.text {
										color: inherit;
										font-size: inherit;
										line-height: 1.5;
									}
								}
								.publisher_item {
									padding: 0;
									display: inline-block;
									.icon {
										margin: 0 2px 0 0;
										color: inherit;
										display: none;
										font-size: inherit;
										line-height: 1.5;
									}
									.label {
										color: inherit;
										font-size: inherit;
										line-height: 1.5;
									}
									.text {
										color: inherit;
										font-size: inherit;
										line-height: 1.5;
									}
								}
								.like_item {
									padding: 0;
									display: inline-block;
									.icon {
										margin: 0 2px 0 0;
										color: inherit;
										display: none;
										font-size: inherit;
										line-height: 1.5;
									}
									.label {
										color: inherit;
										font-size: inherit;
										line-height: 1.5;
									}
									.text {
										color: inherit;
										font-size: inherit;
										line-height: 1.5;
									}
								}
								.collect_item {
									padding: 0;
									display: inline-block;
									.icon {
										margin: 0 2px 0 0;
										color: inherit;
										display: none;
										font-size: inherit;
										line-height: 1.5;
									}
									.label {
										color: inherit;
										font-size: inherit;
										line-height: 1.5;
									}
									.text {
										color: inherit;
										font-size: inherit;
										line-height: 1.5;
									}
								}
								.view_item {
									padding: 0;
									display: inline-block;
									.icon {
										margin: 0 2px 0 0;
										color: inherit;
										display: none;
										font-size: inherit;
										line-height: 1.5;
									}
									.label {
										color: inherit;
										font-size: inherit;
										line-height: 1.5;
									}
									.text {
										color: inherit;
										font-size: inherit;
										line-height: 1.5;
									}
								}
							}
							.img {
								overflow: hidden;
								width: 50%;
								transition: all .3s;
								height: 300px;
								img {
									transform: scale(1);
									object-fit: cover;
									display: block;
									width: 100%;
									transition: all .3s;
									height: 100%;
								}
							}
						}
						.list-item1:hover {
							cursor: pointer;
							transform: translate3d(0px, 0px, 0px);
							.infoBox {
								.name {
								}
								.price {
									color: #f00;
								}
								.time_item {
									.icon {
									}
									.label {
									}
									.text {
									}
								}
								.publisher_item {
									.icon {
									}
									.label {
									}
									.text {
									}
								}
								.like_item {
									.icon {
									}
									.label {
									}
									.text {
									}
								}
								.collect_item {
									.icon {
									}
									.label {
									}
									.text {
									}
								}
								.view_item {
									.icon {
									}
									.label {
									}
									.text {
									}
								}
							}
							.img {
								overflow: hidden;
								img {
									transform: scale(1.05);
								}
							}
						}
						.list-item2 {
							display: flex;
							width: 100%;
							align-items: center;
							transition: all 0s;
							.img {
								overflow: hidden;
								width: 50%;
								transition: all .3s;
								height: 300px;
								img {
									transform: scale(1);
									object-fit: cover;
									display: block;
									width: 100%;
									transition: all .3s;
									height: 100%;
								}
							}
							.infoBox {
								padding: 20px;
								color: #0674fc;
								align-content: center;
								display: flex;
								width: 50%;
								border-color: #929292;
								border-width: 0 1px 1px 0;
								align-items: center;
								border-style: solid;
								flex-wrap: wrap;
								height: 300px;
								.name {
									overflow: hidden;
									color: #000;
									white-space: nowrap;
									width: 100%;
									font-size: 16px;
									line-height: 1.5;
									text-overflow: ellipsis;
								}
								.price {
									padding: 10px 0;
									color: #f00;
									width: 100%;
									font-size: 16px;
									line-height: 1.5;
								}
								.time_item {
									padding: 0;
									display: inline-block;
									.icon {
										margin: 0 2px 0 0;
										color: inherit;
										display: none;
										font-size: inherit;
										line-height: 1.5;
									}
									.label {
										color: inherit;
										font-size: inherit;
										line-height: 1.5;
									}
									.text {
										color: inherit;
										font-size: inherit;
										line-height: 1.5;
									}
								}
								.publisher_item {
									padding: 0;
									display: inline-block;
									.icon {
										margin: 0 2px 0 0;
										color: inherit;
										display: none;
										font-size: inherit;
										line-height: 1.5;
									}
									.label {
										color: inherit;
										font-size: inherit;
										line-height: 1.5;
									}
									.text {
										color: inherit;
										font-size: inherit;
										line-height: 1.5;
									}
								}
								.like_item {
									padding: 0;
									display: inline-block;
									.icon {
										margin: 0 2px 0 0;
										color: inherit;
										display: none;
										font-size: inherit;
										line-height: 1.5;
									}
									.label {
										color: inherit;
										font-size: inherit;
										line-height: 1.5;
									}
									.text {
										color: inherit;
										font-size: inherit;
										line-height: 1.5;
									}
								}
								.collect_item {
									padding: 0;
									display: inline-block;
									.icon {
										margin: 0 2px 0 0;
										color: inherit;
										display: none;
										font-size: inherit;
										line-height: 1.5;
									}
									.label {
										color: inherit;
										font-size: inherit;
										line-height: 1.5;
									}
									.text {
										color: inherit;
										font-size: inherit;
										line-height: 1.5;
									}
								}
								.view_item {
									padding: 0;
									display: inline-block;
									.icon {
										margin: 0 2px 0 0;
										color: inherit;
										display: none;
										font-size: inherit;
										line-height: 1.5;
									}
									.label {
										color: inherit;
										font-size: inherit;
										line-height: 1.5;
									}
									.text {
										color: inherit;
										font-size: inherit;
										line-height: 1.5;
									}
								}
							}
						}
						.list-item2:hover {
							cursor: pointer;
							transform: translate3d(0px, 0px, 0px);
							.img {
								overflow: hidden;
								img {
									transform: scale(1.2);
								}
							}
							.infoBox {
								.name {
								}
								.price {
								}
								.time_item {
									.icon {
									}
									.label {
									}
									.text {
									}
								}
								.publisher_item {
									.icon {
									}
									.label {
									}
									.text {
									}
								}
								.like_item {
									.icon {
									}
									.label {
									}
									.text {
									}
								}
								.collect_item {
									.icon {
									}
									.label {
									}
									.text {
									}
								}
								.view_item {
									.icon {
									}
									.label {
									}
									.text {
									}
								}
							}
						}
					}
					.list-body-right {
						width: 49%;
						.list-item {
							width: 100%;
							position: relative;
							.img {
								width: 100%;
								height: 600px;
								img {
									cursor: pointer;
									transform: scale(1);
									object-fit: cover;
									display: block;
									width: 100%;
									transition: all .3s;
									height: 100%;
								}
							}
							.infoBox {
								padding: 10px 10px 30px;
								color: #0674fc;
								left: 0;
								bottom: 0;
								background: rgba(0, 0, 0, .3);
								width: 100%;
								font-size: 14px;
								position: absolute;
								.name {
									overflow: hidden;
									color: #fff;
									white-space: nowrap;
									width: 100%;
									font-size: 16px;
									line-height: 30px;
									text-overflow: ellipsis;
								}
								.price {
									padding: 0;
									color: #f00;
									font-size: 16px;
									line-height: 30px;
								}
								.time_item {
									padding: 0;
									margin: 0 10px 0 0;
									display: inline-block;
									.icon {
										margin: 0 2px 0 0;
										color: inherit;
										display: none;
										font-size: inherit;
										line-height: 1.5;
									}
									.label {
										color: inherit;
										font-size: inherit;
										line-height: 1.5;
									}
									.text {
										color: inherit;
										font-size: inherit;
										line-height: 1.5;
									}
								}
								.publisher_item {
									padding: 0;
									margin: 0 10px 0 0;
									display: inline-block;
									.icon {
										margin: 0 2px 0 0;
										color: inherit;
										display: none;
										font-size: inherit;
										line-height: 1.5;
									}
									.label {
										color: inherit;
										font-size: inherit;
										line-height: 1.5;
									}
									.text {
										color: inherit;
										font-size: inherit;
										line-height: 1.5;
									}
								}
								.like_item {
									padding: 0;
									margin: 0 10px 0 0;
									display: inline-block;
									.icon {
										margin: 0 2px 0 0;
										color: inherit;
										display: none;
										font-size: inherit;
										line-height: 1.5;
									}
									.label {
										color: inherit;
										font-size: inherit;
										line-height: 1.5;
									}
									.text {
										color: inherit;
										font-size: inherit;
										line-height: 1.5;
									}
								}
								.collect_item {
									padding: 0;
									margin: 0 10px 0 0;
									display: inline-block;
									.icon {
										margin: 0 2px 0 0;
										color: inherit;
										display: none;
										font-size: inherit;
										line-height: 1.5;
									}
									.label {
										color: inherit;
										font-size: inherit;
										line-height: 1.5;
									}
									.text {
										color: inherit;
										font-size: inherit;
										line-height: 1.5;
									}
								}
								.view_item {
									display: inline-block;
									.icon {
										margin: 0 2px 0 0;
										color: inherit;
										display: none;
										font-size: inherit;
										line-height: 1.5;
									}
									.label {
										color: inherit;
										font-size: inherit;
										line-height: 1.5;
									}
									.text {
										color: inherit;
										font-size: inherit;
										line-height: 1.5;
									}
								}
							}
						}
						.swiper-button-prev {
							left: 10px;
							display: none;
							right: auto;
						}
						.swiper-button-prev::after {
							color: #d7b830;
						}
						.swiper-button-next {
							left: auto;
							display: none;
							right: 10px;
						}
						.swiper-button-next::after {
							color: #d7b830;
						}
						/deep/.swiper-pagination {
							transform: translate3d(0,0,0);
							z-index: 10;
							left: 0;
							bottom: 8px;
							width: 100%;
							position: absolute;
							transition: .3s opacity;
							text-align: center;
							.swiper-pagination-bullet {
								cursor: pointer;
								border-radius: 100%;
								margin: 0 4px;
								background: #f6f6f6;
								display: inline-block;
								width: 8px;
								opacity: .8;
								height: 8px;
							}
							.swiper-pagination-bullet:hover {
								background: #0674fc;
							}
							.swiper-pagination-bullet.swiper-pagination-bullet-active {
								background: #0674fc;
							}
						}
					}
				}
			}
			.moreBtn {
				border: 0px solid #999;
				cursor: pointer;
				padding: 0 20px;
				margin: 0px calc((100% - 1200px)/2)  0 0;
				background: #475a8310;
				display: none;
				width: auto;
				line-height: 32px;
				float: right;
				text-align: right;
				.text {
					color: #333;
					font-size: 15px;
				}
				.icon {
					color: #333;
					font-size: 15px;
				}
			}
		}
	}
</style>
