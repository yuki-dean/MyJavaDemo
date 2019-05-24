package com.lf.demo;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import java.util.Map;

public class FastJsonApp {
    public static void main(String[] args){
        Student student = new Student();
        student.setStudentAge(18);
        student.setStudentName("yuwei");
        JSONObject jsonObject = (JSONObject) (JSON.toJSON(student));
        System.out.println(jsonObject.toJSONString());
        System.out.println((Map) jsonObject);
    }
}
