package com.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.lang.reflect.InvocationTargetException;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.beanutils.BeanUtils;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.enums.FieldFill;
import com.baomidou.mybatisplus.enums.IdType;


/**
 * 热门视频预测
 * 数据库通用操作实体类（普通增删改查）
 * @author 
 * @email 
 * @date 2025-03-20 15:08:33
 */
@TableName("hotvideoforecast")
public class HotvideoforecastEntity<T> implements Serializable {
	private static final long serialVersionUID = 1L;


	public HotvideoforecastEntity() {
		
	}
	
	public HotvideoforecastEntity(T t) {
		try {
			BeanUtils.copyProperties(this, t);
		} catch (IllegalAccessException | InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * 主键id
	 */
    @TableId(type = IdType.AUTO)
	private Long id;
	/**
	 * 标题
	 */
					
	private String title;
	
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
	
	
	@JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat
	private Date addtime;

	public Date getAddtime() {
		return addtime;
	}
	public void setAddtime(Date addtime) {
		this.addtime = addtime;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	/**
	 * 设置：标题
	 */
	public void setTitle(String title) {
		this.title = title;
	}
	/**
	 * 获取：标题
	 */
	public String getTitle() {
		return title;
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
