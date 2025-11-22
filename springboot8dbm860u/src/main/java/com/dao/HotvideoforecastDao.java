package com.dao;

import com.entity.HotvideoforecastEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;

import org.apache.ibatis.annotations.Param;
import com.entity.vo.HotvideoforecastVO;
import com.entity.view.HotvideoforecastView;


/**
 * 热门视频预测
 * 
 * @author 
 * @email 
 * @date 2025-03-20 15:08:33
 */
public interface HotvideoforecastDao extends BaseMapper<HotvideoforecastEntity> {
	
	List<HotvideoforecastVO> selectListVO(@Param("ew") Wrapper<HotvideoforecastEntity> wrapper);
	
	HotvideoforecastVO selectVO(@Param("ew") Wrapper<HotvideoforecastEntity> wrapper);
	
	List<HotvideoforecastView> selectListView(@Param("ew") Wrapper<HotvideoforecastEntity> wrapper);

	List<HotvideoforecastView> selectListView(Pagination page,@Param("ew") Wrapper<HotvideoforecastEntity> wrapper);

	
	HotvideoforecastView selectView(@Param("ew") Wrapper<HotvideoforecastEntity> wrapper);
	

}
