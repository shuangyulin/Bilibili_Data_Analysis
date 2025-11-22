package com.dao;

import com.entity.DiscusshotvideoEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;

import org.apache.ibatis.annotations.Param;
import com.entity.vo.DiscusshotvideoVO;
import com.entity.view.DiscusshotvideoView;


/**
 * hotvideo评论表
 * 
 * @author 
 * @email 
 * @date 2025-03-20 15:08:33
 */
public interface DiscusshotvideoDao extends BaseMapper<DiscusshotvideoEntity> {
	
	List<DiscusshotvideoVO> selectListVO(@Param("ew") Wrapper<DiscusshotvideoEntity> wrapper);
	
	DiscusshotvideoVO selectVO(@Param("ew") Wrapper<DiscusshotvideoEntity> wrapper);
	
	List<DiscusshotvideoView> selectListView(@Param("ew") Wrapper<DiscusshotvideoEntity> wrapper);

	List<DiscusshotvideoView> selectListView(Pagination page,@Param("ew") Wrapper<DiscusshotvideoEntity> wrapper);

	
	DiscusshotvideoView selectView(@Param("ew") Wrapper<DiscusshotvideoEntity> wrapper);
	

}
