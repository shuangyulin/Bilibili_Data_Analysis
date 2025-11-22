package com.service;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.service.IService;
import com.utils.PageUtils;
import com.entity.HotvideoEntity;
import java.util.List;
import java.util.Map;
import com.entity.vo.HotvideoVO;
import org.apache.ibatis.annotations.Param;
import com.entity.view.HotvideoView;


/**
 * 热门视频
 *
 * @author 
 * @email 
 * @date 2025-03-20 15:08:33
 */
public interface HotvideoService extends IService<HotvideoEntity> {

    PageUtils queryPage(Map<String, Object> params);
    
   	List<HotvideoVO> selectListVO(Wrapper<HotvideoEntity> wrapper);
   	
   	HotvideoVO selectVO(@Param("ew") Wrapper<HotvideoEntity> wrapper);
   	
   	List<HotvideoView> selectListView(Wrapper<HotvideoEntity> wrapper);
   	
   	HotvideoView selectView(@Param("ew") Wrapper<HotvideoEntity> wrapper);
   	
   	PageUtils queryPage(Map<String, Object> params,Wrapper<HotvideoEntity> wrapper);

   	

    List<Map<String, Object>> selectValue(Map<String, Object> params,Wrapper<HotvideoEntity> wrapper);

    List<Map<String, Object>> selectTimeStatValue(Map<String, Object> params,Wrapper<HotvideoEntity> wrapper);

    List<Map<String, Object>> selectGroup(Map<String, Object> params,Wrapper<HotvideoEntity> wrapper);



}

