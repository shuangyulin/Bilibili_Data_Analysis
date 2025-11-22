package com.controller;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.text.ParseException;
import java.util.*;
import java.lang.*;
import java.math.*;
import java.util.stream.Collectors;
import javax.servlet.http.HttpServletRequest;
import com.utils.ValidatorUtils;
import com.utils.DeSensUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.annotation.IgnoreAuth;

import com.entity.HotvideoforecastEntity;
import com.entity.view.HotvideoforecastView;

import com.service.HotvideoforecastService;
import com.service.TokenService;
import com.utils.PageUtils;
import com.utils.R;
import com.utils.MPUtil;
import com.utils.MapUtils;
import com.utils.CommonUtil;
import java.io.IOException;
import com.service.HotvideoService;
import com.entity.HotvideoEntity;
import java.text.DecimalFormat;
import weka.core.*;
import java.util.*;
import java.util.stream.Stream;
import weka.classifiers.trees.RandomForest;

/**
 * 热门视频预测
 * 后端接口
 * @author 
 * @email 
 * @date 2025-03-20 15:08:33
 */
@RestController
@RequestMapping("/hotvideoforecast")
public class HotvideoforecastController {
    @Autowired
    private HotvideoforecastService hotvideoforecastService;
    @Autowired
    private HotvideoService hotvideoService;






    



    /**
     * 后台列表
     */
    @RequestMapping("/page")
    public R page(@RequestParam Map<String, Object> params,HotvideoforecastEntity hotvideoforecast,
		HttpServletRequest request){
        //设置查询条件
        EntityWrapper<HotvideoforecastEntity> ew = new EntityWrapper<HotvideoforecastEntity>();


        //查询结果
		PageUtils page = hotvideoforecastService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, hotvideoforecast), params), params));
        Map<String, String> deSens = new HashMap<>();
        //给需要脱敏的字段脱敏
        DeSensUtil.desensitize(page,deSens);
        return R.ok().put("data", page);
    }
    
    /**
     * 前台列表
     */
	@IgnoreAuth
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params,HotvideoforecastEntity hotvideoforecast, 
		HttpServletRequest request){
        //设置查询条件
        EntityWrapper<HotvideoforecastEntity> ew = new EntityWrapper<HotvideoforecastEntity>();

        //查询结果
		PageUtils page = hotvideoforecastService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, hotvideoforecast), params), params));
        Map<String, String> deSens = new HashMap<>();
        //给需要脱敏的字段脱敏
        DeSensUtil.desensitize(page,deSens);
        return R.ok().put("data", page);
    }



	/**
     * 列表
     */
    @RequestMapping("/lists")
    public R list( HotvideoforecastEntity hotvideoforecast){
       	EntityWrapper<HotvideoforecastEntity> ew = new EntityWrapper<HotvideoforecastEntity>();
      	ew.allEq(MPUtil.allEQMapPre( hotvideoforecast, "hotvideoforecast")); 
        return R.ok().put("data", hotvideoforecastService.selectListView(ew));
    }

	 /**
     * 查询
     */
    @RequestMapping("/query")
    public R query(HotvideoforecastEntity hotvideoforecast){
        EntityWrapper< HotvideoforecastEntity> ew = new EntityWrapper< HotvideoforecastEntity>();
 		ew.allEq(MPUtil.allEQMapPre( hotvideoforecast, "hotvideoforecast")); 
		HotvideoforecastView hotvideoforecastView =  hotvideoforecastService.selectView(ew);
		return R.ok("查询热门视频预测成功").put("data", hotvideoforecastView);
    }
	
    /**
     * 后台详情
     */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Long id){
        HotvideoforecastEntity hotvideoforecast = hotvideoforecastService.selectById(id);
        Map<String, String> deSens = new HashMap<>();
        //给需要脱敏的字段脱敏
        DeSensUtil.desensitize(hotvideoforecast,deSens);
        return R.ok().put("data", hotvideoforecast);
    }

    /**
     * 前台详情
     */
	@IgnoreAuth
    @RequestMapping("/detail/{id}")
    public R detail(@PathVariable("id") Long id){
        HotvideoforecastEntity hotvideoforecast = hotvideoforecastService.selectById(id);
        Map<String, String> deSens = new HashMap<>();
        //给需要脱敏的字段脱敏
        DeSensUtil.desensitize(hotvideoforecast,deSens);
        return R.ok().put("data", hotvideoforecast);
    }
    



    /**
     * 后台保存
     */
    @RequestMapping("/save")
    public R save(@RequestBody HotvideoforecastEntity hotvideoforecast, HttpServletRequest request){
        //ValidatorUtils.validateEntity(hotvideoforecast);
        hotvideoforecastService.insert(hotvideoforecast);
        return R.ok().put("data",hotvideoforecast.getId());
    }
    
    /**
     * 前台保存
     */
    @RequestMapping("/add")
    public R add(@RequestBody HotvideoforecastEntity hotvideoforecast, HttpServletRequest request){
        //ValidatorUtils.validateEntity(hotvideoforecast);
        hotvideoforecastService.insert(hotvideoforecast);
        return R.ok().put("data",hotvideoforecast.getId());
    }





    /**
     * 修改
     */
    @RequestMapping("/update")
    @Transactional
    public R update(@RequestBody HotvideoforecastEntity hotvideoforecast, HttpServletRequest request){
        //ValidatorUtils.validateEntity(hotvideoforecast);
        //全部更新
        hotvideoforecastService.updateById(hotvideoforecast);
        return R.ok();
    }



    

    /**
     * 删除
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody Long[] ids){
        hotvideoforecastService.deleteBatchIds(Arrays.asList(ids));
        return R.ok();
    }
    







    /**
     * 总数量
     */
    @RequestMapping("/count")
    public R count(@RequestParam Map<String, Object> params,HotvideoforecastEntity hotvideoforecast, HttpServletRequest request){
        EntityWrapper<HotvideoforecastEntity> ew = new EntityWrapper<HotvideoforecastEntity>();
        int count = hotvideoforecastService.selectCount(MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, hotvideoforecast), params), params));
        return R.ok().put("data", count);
    }

    /**
    * 预测算法
    */
    @RequestMapping("/forecast")
    public R forecast(@RequestBody Map<String, Object> params) throws Exception {
        // 特征值
        String[] eigenValueArr = "title,duration,author,typename,coin".split(",");
        // 目标值
        String[] targetValueArr = "playcount".split(",");
        String[] sqlSelectArr = Stream.of(eigenValueArr, targetValueArr).flatMap(Arrays::stream).toArray(String[]::new);
        // 模型训练
        Wrapper<HotvideoEntity> wrapper = new EntityWrapper<>();
        wrapper.setSqlSelect(sqlSelectArr);
        for (String arr : sqlSelectArr) {
            wrapper.isNotNull(arr).ne(arr, "");
        }
        // 从数据库获取需要元数据
        List<Map<String,Object>> list =hotvideoService.selectMaps(wrapper);
        // 模型训练
        Instances instances = createInstances(list, sqlSelectArr);

        // 创建一个map来存储预测结果
        Map<String, Object> forecastRes = forecastRes(instances, params, eigenValueArr, targetValueArr);

        // 更新数据库或其他操作
        EntityWrapper<HotvideoforecastEntity> ew = new EntityWrapper<>();
        ew.eq("id", params.get("id"));
        for (Map.Entry<String, Object> entry : forecastRes.entrySet()) {
            String updateSet = entry.getKey() + "='" + entry.getValue().toString() + "'";
            hotvideoforecastService.updateForSet(updateSet, ew);
        }
        params.putAll(forecastRes);
        return R.ok(params);
    }

    /**
     * 预测结果
     * @param instances
     * @param params
     * @param eigenValueArr
     * @param targetValueArr
     * @return
     * @throws Exception
     */
    private Map<String, Object> forecastRes(Instances instances, Map<String, Object> params, String[] eigenValueArr, String[] targetValueArr) throws Exception {
        Map<String, Object> forecastRes = new HashMap<>();
        // 预测结果
        // 创建一个与训练数据集结构相同的 Instance
        double[] instanceValue = new double[instances.numAttributes()];
        int x = 0;
        for (String attr : eigenValueArr) {
            if (instances.attribute(attr).isNominal()) {
                // 对于名义属性，使用indexOfValue
                double res = instances.attribute(attr).indexOfValue(params.get(attr).toString());
                instanceValue[x++] = res == -1 ? instances.attribute(attr).indexOfValue("unknown") : res; // 如果值不存在，使用Double.NaN
            } else {
                // 对于数值属性，直接使用数值
                instanceValue[x++] = Double.parseDouble(params.get(attr).toString());
            }
        }
        for (String attr : targetValueArr) {
            instanceValue[x++] = Double.NaN;
        }

        Instance instance = new DenseInstance(1.0, instanceValue);
        instance.setDataset(instances);

        for (int i = 0; i < targetValueArr.length; i++) {
            // 设置类属性索引为当前目标属性
            instances.setClassIndex(instances.numAttributes() - targetValueArr.length + i);

            // 创建并训练随机森林模型
            RandomForest randomForest = new RandomForest();
            randomForest.buildClassifier(instances);

            // 为预测创建一个新的实例
            double[] instanceValueForPrediction = Arrays.copyOf(instanceValue, instanceValue.length);
            instanceValueForPrediction[instances.numAttributes() - targetValueArr.length + i] = Double.NaN; // 设置目标属性为缺失值

            Instance instanceForPrediction = new DenseInstance(1.0, instanceValueForPrediction);
            instanceForPrediction.setDataset(instances);

            // 进行预测
            double predictedValue = randomForest.classifyInstance(instanceForPrediction);
            if (instances.classAttribute().isNominal()) {
                // 如果是名义属性，获取预测的类别名称
                String predictedClassName = instances.classAttribute().value((int) predictedValue);
                forecastRes.put(targetValueArr[i], predictedClassName);
            } else {
                // 如果是数值属性，直接使用预测值
                DecimalFormat df = new DecimalFormat("#.00");
                double formattedPredictedValue = Double.parseDouble(df.format(predictedValue));
                forecastRes.put(targetValueArr[i], formattedPredictedValue);
            }
        }
        return forecastRes;
    }

    /**
    * 训练模型
    */
    private Instances createInstances(List<Map<String, Object>> dataList, String[] attrs) throws Exception {
        // 遍历数据集以确定每个属性的类型
        Map<String, Set<Object>> uniqueValuesPerAttribute = new HashMap<>();
        for (Map<String, Object> data : dataList) {
            for (String attr : attrs) {
                uniqueValuesPerAttribute.computeIfAbsent(attr, k -> new HashSet<>()).add(data.get(attr));
            }
        }
        FastVector attributes = new FastVector();
        for (String attr : attrs) {
            Set<Object> uniqueValues = uniqueValuesPerAttribute.get(attr);
            if (isNominal(uniqueValues)) {
                // 如果是分类属性，创建分类属性
                FastVector nominalValues = new FastVector();
                uniqueValues.forEach(value -> nominalValues.addElement(value));
                nominalValues.add("unknown");
                attributes.addElement(new Attribute(attr, nominalValues));
            } else {
                attributes.addElement(new Attribute(attr));
            }
        }

        // 创建 Instances 对象
        Instances dataset = new Instances("dataset-name", attributes, dataList.size());
        dataset.setClassIndex(dataset.numAttributes() - 1); // 设置类属性索引
        // 填充数据
        for (Map<String, Object> data : dataList) {
            double[] instanceValue = new double[dataset.numAttributes()];
            int i = 0;
            for (String attr : attrs) {
                Attribute attribute = dataset.attribute(attr);
                if (attribute.isNominal()) {
                    instanceValue[i++] = attribute.indexOfValue(data.get(attr).toString());
                } else {
                    instanceValue[i++] = Double.parseDouble(data.get(attr).toString());
                }
            }
            Instance instance = new DenseInstance(1.0, instanceValue);
            dataset.add(instance);
        }
        return dataset;
    }

    /**
    * 检测数据类型是否为字符串
    */
    private boolean isNominal(Set<Object> values) {
        // 检查是否所有的值都是字符串，并且数量是否超过某个阈值（例如，如果超过50%的值是唯一的，则可能是分类属性）
        return values.stream().allMatch(v -> v instanceof String);
    }
}
