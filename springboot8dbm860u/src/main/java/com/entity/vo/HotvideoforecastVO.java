package com.entity.vo;

import com.entity.HotvideoforecastEntity;

import com.baomidou.mybatisplus.annotations.TableName;
import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.io.Serializable;
 

/**
 * 热门视频预测
 * @author 
 * @email 
 * @date 2025-03-20 15:08:33
 */
public class HotvideoforecastVO  implements Serializable {
	private static final long serialVersionUID = 1L;

	 			
	/**
	 * 时长（秒）
	 */
	
	private Integer duration;
		
	/**
	 * up主
	 */
	
	private String author;
		
	/**
	 * 类型
	 */
	
	private String typename;
		
	/**
	 * 投币
	 */
	
	private Integer coin;
		
	/**
	 * 播放量
	 */
	
	private Integer playcount;
				
	
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
			
}
