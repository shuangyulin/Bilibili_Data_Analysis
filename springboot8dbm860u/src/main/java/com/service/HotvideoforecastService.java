package com.service;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.service.IService;
import com.utils.PageUtils;
import com.entity.HotvideoforecastEntity;
import java.util.List;
import java.util.Map;
import com.entity.vo.HotvideoforecastVO;
import org.apache.ibatis.annotations.Param;
import com.entity.view.HotvideoforecastView;


/**
 * 热门视频预测
 *
 * @author 
 * @email 
 * @date 2025-03-20 15:08:33
 */
public interface HotvideoforecastService extends IService<HotvideoforecastEntity> {

    PageUtils queryPage(Map<String, Object> params);
    
   	List<HotvideoforecastVO> selectListVO(Wrapper<HotvideoforecastEntity> wrapper);
   	
   	HotvideoforecastVO selectVO(@Param("ew") Wrapper<HotvideoforecastEntity> wrapper);
   	
   	List<HotvideoforecastView> selectListView(Wrapper<HotvideoforecastEntity> wrapper);
   	
   	HotvideoforecastView selectView(@Param("ew") Wrapper<HotvideoforecastEntity> wrapper);
   	
   	PageUtils queryPage(Map<String, Object> params,Wrapper<HotvideoforecastEntity> wrapper);

   	

}

