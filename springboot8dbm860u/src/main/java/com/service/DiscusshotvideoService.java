package com.service;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.service.IService;
import com.utils.PageUtils;
import com.entity.DiscusshotvideoEntity;
import java.util.List;
import java.util.Map;
import com.entity.vo.DiscusshotvideoVO;
import org.apache.ibatis.annotations.Param;
import com.entity.view.DiscusshotvideoView;


/**
 * hotvideo评论表
 *
 * @author 
 * @email 
 * @date 2025-03-20 15:08:33
 */
public interface DiscusshotvideoService extends IService<DiscusshotvideoEntity> {

    PageUtils queryPage(Map<String, Object> params);
    
   	List<DiscusshotvideoVO> selectListVO(Wrapper<DiscusshotvideoEntity> wrapper);
   	
   	DiscusshotvideoVO selectVO(@Param("ew") Wrapper<DiscusshotvideoEntity> wrapper);
   	
   	List<DiscusshotvideoView> selectListView(Wrapper<DiscusshotvideoEntity> wrapper);
   	
   	DiscusshotvideoView selectView(@Param("ew") Wrapper<DiscusshotvideoEntity> wrapper);
   	
   	PageUtils queryPage(Map<String, Object> params,Wrapper<DiscusshotvideoEntity> wrapper);

   	

}

