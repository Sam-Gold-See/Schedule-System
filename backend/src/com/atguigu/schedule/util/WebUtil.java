package com.atguigu.schedule.util;

import com.atguigu.schedule.common.Result;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.BufferedReader;
import java.io.IOException;
import java.text.SimpleDateFormat;

public class WebUtil {
    private static ObjectMapper objectMapper;

    // 初始化objectionMapper
    static {
        objectMapper = new ObjectMapper();
        // 设置JSON和Object转换的时间日期格式
        objectMapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
    }

    // 从请求中获取JSON串并转换为Object
    public static <T> T readJson(HttpServletRequest request, Class<T> clazz) {
        T t = null;
        BufferedReader br;
        try {
            br = request.getReader();
            StringBuilder sb = new StringBuilder();
            String line;
            while ((line = br.readLine()) != null) sb.append(line);
            t = objectMapper.readValue(sb.toString(), clazz);
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
        return t;
    }

    // 将Result对象转换成JSON串并放入响应对象
    public static void writeJson(HttpServletResponse resp, Result result) {
        resp.setContentType("application/json;charset=utf-8");
        try {
            String json = objectMapper.writeValueAsString(result);
            resp.getWriter().write(json);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
