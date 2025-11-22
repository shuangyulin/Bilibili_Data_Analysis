package com.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import java.util.List;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.utils.PageUtils;
import com.utils.Query;


import com.dao.HotvideoforecastDao;
import com.entity.HotvideoforecastEntity;
import com.service.HotvideoforecastService;
import com.entity.vo.HotvideoforecastVO;
import com.entity.view.HotvideoforecastView;

@Service("hotvideoforecastService")
public class HotvideoforecastServiceImpl extends ServiceImpl<HotvideoforecastDao, HotvideoforecastEntity> implements HotvideoforecastService {
	
	
    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<HotvideoforecastEntity> page = this.selectPage(
                new Query<HotvideoforecastEntity>(params).getPage(),
                new EntityWrapper<HotvideoforecastEntity>()
        );
        return new PageUtils(page);
    }
    
    @Override
	public PageUtils queryPage(Map<String, Object> params, Wrapper<HotvideoforecastEntity> wrapper) {
		  Page<HotvideoforecastView> page =new Query<HotvideoforecastView>(params).getPage();
	        page.setRecords(baseMapper.selectListView(page,wrapper));
	    	PageUtils pageUtil = new PageUtils(page);
	    	return pageUtil;
 	}

    
    @Override
	public List<HotvideoforecastVO> selectListVO(Wrapper<HotvideoforecastEntity> wrapper) {
 		return baseMapper.selectListVO(wrapper);
	}
	
	@Override
	public HotvideoforecastVO selectVO(Wrapper<HotvideoforecastEntity> wrapper) {
 		return baseMapper.selectVO(wrapper);
	}
	
	@Override
	public List<HotvideoforecastView> selectListView(Wrapper<HotvideoforecastEntity> wrapper) {
		return baseMapper.selectListView(wrapper);
	}

	@Override
	public HotvideoforecastView selectView(Wrapper<HotvideoforecastEntity> wrapper) {
		return baseMapper.selectView(wrapper);
	}


}
