package com.config;


import com.SpringbootSchemaApplication;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.apache.commons.lang3.StringUtils;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.DoubleWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.*;

@Component
public class HadoopValue {

    @Resource
    private HadoopConfig hadoopConfig;

    public static String timeType = "timeType";
    public static String className;
    public static String xColumnName;
    public static String yColumnName;


    Map<String, List> res = new LinkedHashMap<>();

    public void run(List<Object> list) throws Exception {
        FileSystem fs = hadoopConfig.createFs();
        Path inputPath = new Path("/input/value/" + className + "/" + xColumnName + "/" + yColumnName + "/" + timeType + ".json");
        Path outputPath = new Path("/output/value/" + className + "/" + xColumnName + "/" + yColumnName + "/" + timeType);
        res = new LinkedHashMap<>();
        if (fs.exists(inputPath)) {
            fs.delete(inputPath, true);
        }
        if (fs.exists(outputPath)) {
            fs.delete(outputPath, true);
        }
        if (!CollectionUtils.isEmpty(list)) {
            ObjectMapper mapper = new ObjectMapper();
            try (BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(fs.create(inputPath)))) {
                for (Object obj : list) {
                    writer.write(mapper.writeValueAsString(obj));
                    writer.newLine();
                }
            }

            Job job = Job.getInstance(hadoopConfig.getConfiguration(), "value_" + xColumnName + "_" + yColumnName + "_" + timeType);
            // 设置jar中的启动类，可以根据这个类找到相应的jar包
            job.setJarByClass(SpringbootSchemaApplication.class);
            job.setMapperClass(ValueMapper.class);
            job.setCombinerClass(ValueReduce.class);
            job.setReducerClass(ValueReduce.class);

            // 设置Mapper的输出
            job.setMapOutputKeyClass(Text.class);
            job.setMapOutputValueClass(Text.class);

            // 设置reduce的输出
            job.setOutputKeyClass(Text.class);
            job.setOutputValueClass(Text.class);

            FileInputFormat.addInputPath(job, inputPath);
            FileOutputFormat.setOutputPath(job, outputPath);
            //提交程序  并且监控打印程序执行情况
            job.waitForCompletion(true);

            boolean jobSuccess = job.waitForCompletion(true);
            if (jobSuccess) {
                //读取MapReduce作业的输出结果
                try (InputStream in = fs.open(new Path(outputPath, "part-r-00000")); Reader reader = new InputStreamReader(in, StandardCharsets.UTF_8)) {
                    java.io.BufferedReader bufferedReader = new java.io.BufferedReader(reader);
                    String line;
                    while ((line = bufferedReader.readLine()) != null) {
                        String[] parts = line.split("\t");
                        if (parts.length == 2) {
                            String xColumn = parts[0];
                            if (StringUtils.isNotEmpty(timeType)) {
                                switch (timeType) {
                                    case "年":
                                        SimpleDateFormat year = new SimpleDateFormat("yyyy");
                                        xColumn = year.format(parts[0]);
                                        break;
                                    case "月":
                                        SimpleDateFormat month = new SimpleDateFormat("yyyy-MM");
                                        xColumn = month.format(parts[0]);
                                        break;
                                    case "日":
                                        SimpleDateFormat day = new SimpleDateFormat("yyyy-MM-dd");
                                        xColumn = day.format(parts[0]);
                                        break;
                                }
                            }
                            for (int i = 0; i < parts[1].split(",").length; i++) {
                                Map<String, Object> dataMap = new LinkedHashMap<>();
                                Double total = Double.parseDouble(parts[1].split(",")[i]);
                                dataMap.put(xColumnName, xColumn);
                                dataMap.put("total", total);
                                getList(i, dataMap);
                            }
                        }
                    }
                }
                // 使用ObjectMapper将数据列表转换为JSON格式
                ObjectMapper mapper1 = new ObjectMapper();
                mapper1.enable(SerializationFeature.INDENT_OUTPUT); // 可选，用于格式化输出
                String localJsonFilePath = System.getProperty("user.dir") + "/value_" + className + "_" + xColumnName + "_" + yColumnName + "_" + timeType + ".json";
                try {
                    Files.delete(Paths.get(localJsonFilePath));
                } catch (IOException e) {

                }
                // 将JSON字符串写入本地文件
                try (BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(localJsonFilePath), StandardCharsets.UTF_8))) {
                    String jsonContent = mapper1.writeValueAsString(getRes());
                    writer.write(jsonContent);
                }
            }
        }
    }


    private void getList(int i, Map map) {
        String key = "key" + i;
        List list = new LinkedList<>();
        if (res.containsKey(key)) {
            list = res.get(key);
        }
        list.add(map);
        res.put(key, list);
    }

    private List getRes() {
        List<List> r = new LinkedList<>();
        for (String key : res.keySet()) {
            r.add(res.get(key));
        }
        if (r.size() == 1) {
            return r.get(0);
        }
        return r;
    }

    /**
     * 分词
     */
    public static class ValueMapper extends Mapper<LongWritable, Text, Text, Text> {
        /**
         * @param key     默认情况下，是MapReduce所读取到的一行文本的起始偏移量
         * @param value   默认情况下，是MapReduce所读取到的一行文本的内容，hadoop中的序列化类型为Text
         * @param context 是用户自定义逻辑处理完成后输出的KEY，在此处是单词，String
         * @throws IOException          异常
         * @throws InterruptedException 异常
         */
        @Override
        public void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
            // 防止中文乱码
            String line = new String(value.getBytes(), 0, value.getLength(), "UTF-8").trim();
            if (StringUtils.isNotEmpty(line)) {
                JSONArray array = new JSONArray(line);
                for (int i = 0; i < array.length(); i++) {
                    JSONObject jsonObject = array.getJSONObject(i);
                    StringBuffer res = new StringBuffer();
                    for (String name : yColumnName.split(",")) {
                        if (res.length() > 0) {
                            res.append(",");
                        }
                        res.append(Double.parseDouble(jsonObject.get(name).toString()));
                    }
                    context.write(new Text(jsonObject.get(xColumnName).toString()), new Text(res.toString()));
                }
            }
        }
    }

    /**
     * 处理Map传入的内容
     *
     * @author Ruison
     * on 2019/7/6 - 15:09
     */
    public static class ValueReduce extends Reducer<Text, Text, Text, Text> {

        private DoubleWritable result = new DoubleWritable();

        /**
         * @param key     第一个Text: 是传入的单词名称，是Mapper中传入的
         * @param values  第二个：LongWritable 是该单词出现了多少次，这个是MapReduce计算出来的，比如 hello出现了11次
         * @param context 第三个Text: 是输出单词的名称 ，这里是要输出到文本中的内容
         * @throws IOException          异常
         * @throws InterruptedException 异常
         */
        @Override
        public void reduce(Text key, Iterable<Text> values, Context context) throws IOException, InterruptedException {
            int lng = yColumnName.split(",").length;
            double[] sum = new double[lng];
            for (Text text : values) {
                String[] arr = text.toString().split(",");
                for (int i = 0; i < arr.length; i++) {
                    sum[i] += Double.parseDouble(arr[i]);
                }
            }
            //result.set(sum);
            if (key != null && sum != null) {
                context.write(key, new Text(arrayToStringWithCommas(sum)));
            }
        }
    }

    private static String arrayToStringWithCommas(double[] array) {
        // 将double数组转换为String数组
        String[] stringArray = Arrays.stream(array).mapToObj(Double::toString).toArray(String[]::new);
        // 使用逗号将String数组中的元素连接起来
        return String.join(", ", stringArray);
    }

    public static void setTimeType(String timeType) {
        HadoopValue.timeType = timeType;
    }

    public static void setClassName(String className) {
        HadoopValue.className = className;
    }

    public static void setxColumnName(String xColumnName) {
        HadoopValue.xColumnName = xColumnName;
    }

    public static void setyColumnName(String yColumnName) {
        HadoopValue.yColumnName = yColumnName;
    }
}
