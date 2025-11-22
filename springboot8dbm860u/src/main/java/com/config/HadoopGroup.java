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
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class HadoopGroup {

    @Resource
    private HadoopConfig hadoopConfig;

    public static String timeType = "timeType";
    public static String className;
    public static String columnName;

    public void run(List<Object> list) throws Exception {
        FileSystem fs = hadoopConfig.createFs();
        Path inputPath = new Path("/input/group/" + className + "/" + columnName + "/" + timeType + ".json");
        Path outputPath = new Path("/output/group/" + className + "/" + columnName + "/" + timeType);
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

            Job job = Job.getInstance(hadoopConfig.getConfiguration(), "group_" + columnName + "_" + timeType);
            // 设置jar中的启动类，可以根据这个类找到相应的jar包
            job.setJarByClass(SpringbootSchemaApplication.class);
            job.setMapperClass(ValueMapper.class);
            job.setCombinerClass(ValueReduce.class);
            job.setReducerClass(ValueReduce.class);

            // 设置Mapper的输出
            job.setMapOutputKeyClass(Text.class);
            job.setMapOutputValueClass(DoubleWritable.class);

            // 设置reduce的输出
            job.setOutputKeyClass(Text.class);
            job.setOutputValueClass(DoubleWritable.class);

            FileInputFormat.addInputPath(job, inputPath);
            FileOutputFormat.setOutputPath(job, outputPath);
            //提交程序  并且监控打印程序执行情况
            job.waitForCompletion(true);

            boolean jobSuccess = job.waitForCompletion(true);
            if (jobSuccess) {
                //读取MapReduce作业的输出结果
                List<Map<String, Object>> dataList = new ArrayList<>();
                try (InputStream in = fs.open(new Path(outputPath, "part-r-00000")); Reader reader = new InputStreamReader(in, StandardCharsets.UTF_8)) {
                    BufferedReader bufferedReader = new BufferedReader(reader);
                    String line;
                    while ((line = bufferedReader.readLine()) != null) {
                        String[] parts = line.split("\t");
                        if (parts.length == 2) {
                            Map<String, Object> dataMap = new HashMap<>();

                                switch (timeType) {
                                    case "年":
                                        SimpleDateFormat year = new SimpleDateFormat("yyyy");
                                        dataMap.put(year.format(columnName), parts[0]);
                                        break;
                                    case "月":
                                        SimpleDateFormat month = new SimpleDateFormat("yyyy-MM");
                                        dataMap.put(month.format(columnName), parts[0]);
                                        break;
                                    case "日":
                                        SimpleDateFormat day = new SimpleDateFormat("yyyy-MM-dd");
                                        dataMap.put(day.format(columnName), parts[0]);
                                        break;
                                    default:
                                        dataMap.put(columnName, parts[0]);
                                }
                            dataMap.put("total" , Double.parseDouble(parts[1]));
                            dataList.add(dataMap);
                        }
                    }
                }
                // 使用ObjectMapper将数据列表转换为JSON格式
                ObjectMapper mapper1 = new ObjectMapper();
                mapper1.enable(SerializationFeature.INDENT_OUTPUT); // 可选，用于格式化输出
                String localJsonFilePath = System.getProperty("user.dir") + "/group_" + className + "_" + columnName + "_" + timeType + ".json";
                // 将JSON字符串写入本地文件
                try (BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(localJsonFilePath), StandardCharsets.UTF_8))) {
                    String jsonContent = mapper1.writeValueAsString(dataList);
                    writer.write(jsonContent);
                }
            }
        }
    }

    /**
     * 分词
     */
    public static class ValueMapper extends Mapper<LongWritable, Text, Text, DoubleWritable> {
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
                    context.write(new Text(jsonObject.get(columnName).toString()), new DoubleWritable(1));
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
    public static class ValueReduce extends Reducer<Text, DoubleWritable, Text, DoubleWritable> {

        private DoubleWritable result = new DoubleWritable();

        /**
         * @param key     第一个Text: 是传入的单词名称，是Mapper中传入的
         * @param values  第二个：LongWritable 是该单词出现了多少次，这个是MapReduce计算出来的，比如 hello出现了11次
         * @param context 第三个Text: 是输出单词的名称 ，这里是要输出到文本中的内容
         * @throws IOException          异常
         * @throws InterruptedException 异常
         */
        @Override
        public void reduce(Text key, Iterable<DoubleWritable> values, Context context) throws IOException, InterruptedException {
            Double sum = new Double(0);
            for (DoubleWritable val : values) {
                sum += val.get();
            }
            result.set(sum);
            if (key != null && result != null) {
                context.write(key, result);
            }
        }
    }

    public static void setTimeType(String timeType) {
        HadoopGroup.timeType = timeType;
    }

    public static void setClassName(String className) {
        HadoopGroup.className = className;
    }

    public static void setColumnName(String columnName) {
        HadoopGroup.columnName = columnName;
    }
}
