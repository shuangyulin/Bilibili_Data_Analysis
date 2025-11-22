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


import com.dao.HotvideoDao;
import com.entity.HotvideoEntity;
import com.service.HotvideoService;
import com.entity.vo.HotvideoVO;
import com.entity.view.HotvideoView;

@Service("hotvideoService")
public class HotvideoServiceImpl extends ServiceImpl<HotvideoDao, HotvideoEntity> implements HotvideoService {
	
	
    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<HotvideoEntity> page = this.selectPage(
                new Query<HotvideoEntity>(params).getPage(),
                new EntityWrapper<HotvideoEntity>()
        );
        return new PageUtils(page);
    }
    
    @Override
	public PageUtils queryPage(Map<String, Object> params, Wrapper<HotvideoEntity> wrapper) {
		  Page<HotvideoView> page =new Query<HotvideoView>(params).getPage();
	        page.setRecords(baseMapper.selectListView(page,wrapper));
	    	PageUtils pageUtil = new PageUtils(page);
	    	return pageUtil;
 	}

    
    @Override
	public List<HotvideoVO> selectListVO(Wrapper<HotvideoEntity> wrapper) {
 		return baseMapper.selectListVO(wrapper);
	}
	
	@Override
	public HotvideoVO selectVO(Wrapper<HotvideoEntity> wrapper) {
 		return baseMapper.selectVO(wrapper);
	}
	
	@Override
	public List<HotvideoView> selectListView(Wrapper<HotvideoEntity> wrapper) {
		return baseMapper.selectListView(wrapper);
	}

	@Override
	public HotvideoView selectView(Wrapper<HotvideoEntity> wrapper) {
		return baseMapper.selectView(wrapper);
	}

    @Override
    public List<Map<String, Object>> selectValue(Map<String, Object> params, Wrapper<HotvideoEntity> wrapper) {
        return baseMapper.selectValue(params, wrapper);
    }

    @Override
    public List<Map<String, Object>> selectTimeStatValue(Map<String, Object> params, Wrapper<HotvideoEntity> wrapper) {
        return baseMapper.selectTimeStatValue(params, wrapper);
    }

    @Override
    public List<Map<String, Object>> selectGroup(Map<String, Object> params, Wrapper<HotvideoEntity> wrapper) {
        return baseMapper.selectGroup(params, wrapper);
    }




}
