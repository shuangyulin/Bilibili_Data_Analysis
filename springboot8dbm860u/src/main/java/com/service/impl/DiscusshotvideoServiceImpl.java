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


import com.dao.DiscusshotvideoDao;
import com.entity.DiscusshotvideoEntity;
import com.service.DiscusshotvideoService;
import com.entity.vo.DiscusshotvideoVO;
import com.entity.view.DiscusshotvideoView;

@Service("discusshotvideoService")
public class DiscusshotvideoServiceImpl extends ServiceImpl<DiscusshotvideoDao, DiscusshotvideoEntity> implements DiscusshotvideoService {
	
	
    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<DiscusshotvideoEntity> page = this.selectPage(
                new Query<DiscusshotvideoEntity>(params).getPage(),
                new EntityWrapper<DiscusshotvideoEntity>()
        );
        return new PageUtils(page);
    }
    
    @Override
	public PageUtils queryPage(Map<String, Object> params, Wrapper<DiscusshotvideoEntity> wrapper) {
		  Page<DiscusshotvideoView> page =new Query<DiscusshotvideoView>(params).getPage();
	        page.setRecords(baseMapper.selectListView(page,wrapper));
	    	PageUtils pageUtil = new PageUtils(page);
	    	return pageUtil;
 	}

    
    @Override
	public List<DiscusshotvideoVO> selectListVO(Wrapper<DiscusshotvideoEntity> wrapper) {
 		return baseMapper.selectListVO(wrapper);
	}
	
	@Override
	public DiscusshotvideoVO selectVO(Wrapper<DiscusshotvideoEntity> wrapper) {
 		return baseMapper.selectVO(wrapper);
	}
	
	@Override
	public List<DiscusshotvideoView> selectListView(Wrapper<DiscusshotvideoEntity> wrapper) {
		return baseMapper.selectListView(wrapper);
	}

	@Override
	public DiscusshotvideoView selectView(Wrapper<DiscusshotvideoEntity> wrapper) {
		return baseMapper.selectView(wrapper);
	}


}
