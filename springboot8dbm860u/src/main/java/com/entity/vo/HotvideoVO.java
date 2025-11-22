package com.entity.vo;

import com.entity.HotvideoEntity;

import com.baomidou.mybatisplus.annotations.TableName;
import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.io.Serializable;
 

/**
 * 热门视频
 * @author 
 * @email 
 * @date 2025-03-20 15:08:33
 */
public class HotvideoVO  implements Serializable {
	private static final long serialVersionUID = 1L;

	 			
	/**
	 * 简介
	 */
	
	private String info;
		
	/**
	 * 时长（秒）
	 */
	
	private Integer duration;
		
	/**
	 * 封面
	 */
	
	private String cover;
		
	/**
	 * up主
	 */
	
	private String author;
		
	/**
	 * 播放量
	 */
	
	private Integer playcount;
		
	/**
	 * 弹幕量
	 */
	
	private Integer danmaku;
		
	/**
	 * 点赞
	 */
	
	private Integer likes;
		
	/**
	 * 收藏
	 */
	
	private Integer favorite;
		
	/**
	 * 分享
	 */
	
	private Integer share;
		
	/**
	 * 评论数
	 */
	
	private Integer commentcount;
		
	/**
	 * 上传地点
	 */
	
	private String publocation;
		
	/**
	 * 上传时间
	 */
	
	private String pubtime;
		
	/**
	 * 类型
	 */
	
	private String typename;
		
	/**
	 * 来源
	 */
	
	private String laiyuan;
		
	/**
	 * 投币
	 */
	
	private Integer coin;
		
	/**
	 * 最近点击时间
	 */
		
	@JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat 
	private Date clicktime;
		
	/**
	 * 评论数
	 */
	
	private Integer discussnum;
		
	/**
	 * 收藏数
	 */
	
	private Integer storeupnum;
				
	
	/**
	 * 设置：简介
	 */
	 
	public void setInfo(String info) {
		this.info = info;
	}
	
	/**
	 * 获取：简介
	 */
	public String getInfo() {
		return info;
	}
				
	
	/**
	 * 设置：时长（秒）
	 */
	 
	public void setDuration(Integer duration) {
		this.duration = duration;
	}
	
	/**
	 * 获取：时长（秒）
	 */
	public Integer getDuration() {
		return duration;
	}
				
	
	/**
	 * 设置：封面
	 */
	 
	public void setCover(String cover) {
		this.cover = cover;
	}
	
	/**
	 * 获取：封面
	 */
	public String getCover() {
		return cover;
	}
				
	
	/**
	 * 设置：up主
	 */
	 
	public void setAuthor(String author) {
		this.author = author;
	}
	
	/**
	 * 获取：up主
	 */
	public String getAuthor() {
		return author;
	}
				
	
	/**
	 * 设置：播放量
	 */
	 
	public void setPlaycount(Integer playcount) {
		this.playcount = playcount;
	}
	
	/**
	 * 获取：播放量
	 */
	public Integer getPlaycount() {
		return playcount;
	}
				
	
	/**
	 * 设置：弹幕量
	 */
	 
	public void setDanmaku(Integer danmaku) {
		this.danmaku = danmaku;
	}
	
	/**
	 * 获取：弹幕量
	 */
	public Integer getDanmaku() {
		return danmaku;
	}
				
	
	/**
	 * 设置：点赞
	 */
	 
	public void setLikes(Integer likes) {
		this.likes = likes;
	}
	
	/**
	 * 获取：点赞
	 */
	public Integer getLikes() {
		return likes;
	}
				
	
	/**
	 * 设置：收藏
	 */
	 
	public void setFavorite(Integer favorite) {
		this.favorite = favorite;
	}
	
	/**
	 * 获取：收藏
	 */
	public Integer getFavorite() {
		return favorite;
	}
				
	
	/**
	 * 设置：分享
	 */
	 
	public void setShare(Integer share) {
		this.share = share;
	}
	
	/**
	 * 获取：分享
	 */
	public Integer getShare() {
		return share;
	}
				
	
	/**
	 * 设置：评论数
	 */
	 
	public void setCommentcount(Integer commentcount) {
		this.commentcount = commentcount;
	}
	
	/**
	 * 获取：评论数
	 */
	public Integer getCommentcount() {
		return commentcount;
	}
				
	
	/**
	 * 设置：上传地点
	 */
	 
	public void setPublocation(String publocation) {
		this.publocation = publocation;
	}
	
	/**
	 * 获取：上传地点
	 */
	public String getPublocation() {
		return publocation;
	}
				
	
	/**
	 * 设置：上传时间
	 */
	 
	public void setPubtime(String pubtime) {
		this.pubtime = pubtime;
	}
	
	/**
	 * 获取：上传时间
	 */
	public String getPubtime() {
		return pubtime;
	}
				
	
	/**
	 * 设置：类型
	 */
	 
	public void setTypename(String typename) {
		this.typename = typename;
	}
	
	/**
	 * 获取：类型
	 */
	public String getTypename() {
		return typename;
	}
				
	
	/**
	 * 设置：来源
	 */
	 
	public void setLaiyuan(String laiyuan) {
		this.laiyuan = laiyuan;
	}
	
	/**
	 * 获取：来源
	 */
	public String getLaiyuan() {
		return laiyuan;
	}
				
	
	/**
	 * 设置：投币
	 */
	 
	public void setCoin(Integer coin) {
		this.coin = coin;
	}
	
	/**
	 * 获取：投币
	 */
	public Integer getCoin() {
		return coin;
	}
				
	
	/**
	 * 设置：最近点击时间
	 */
	 
	public void setClicktime(Date clicktime) {
		this.clicktime = clicktime;
	}
	
	/**
	 * 获取：最近点击时间
	 */
	public Date getClicktime() {
		return clicktime;
	}
				
	
	/**
	 * 设置：评论数
	 */
	 
	public void setDiscussnum(Integer discussnum) {
		this.discussnum = discussnum;
	}
	
	/**
	 * 获取：评论数
	 */
	public Integer getDiscussnum() {
		return discussnum;
	}
				
	
	/**
	 * 设置：收藏数
	 */
	 
	public void setStoreupnum(Integer storeupnum) {
		this.storeupnum = storeupnum;
	}
	
	/**
	 * 获取：收藏数
	 */
	public Integer getStoreupnum() {
		return storeupnum;
	}
			
}
