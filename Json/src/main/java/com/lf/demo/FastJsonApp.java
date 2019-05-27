package com.lf.demo;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import java.util.Map;

public class FastJsonApp {
    public static void main(String[] args){
        foo();
        foo3();
        foo4();
    }

    public static void foo2(){
        Student student = new Student();
        student.setStudentAge(18);
        student.setStudentName("yuwei");
        JSONObject jsonObject = (JSONObject) (JSON.toJSON(student));
        System.out.println(jsonObject.toJSONString());
        System.out.println((Map) jsonObject);
    }

    public  static void foo(){
        String result = "<applications>"
                +"<versions__delta>1</versions__delta>"
                +"<apps__hashcode></apps__hashcode>"
                +"</applications>";
        String json = (StaxonUtils.xml2json(result));
        System.out.println(json);
        String xml = StaxonUtils.json2xml(json);
        System.out.println(xml);
    }

    static public void foo3(){
        String xml = "<body><x1>test</x1> <x2>test2</x2></body>";
        String json = (StaxonUtils.xml2json(xml));
        System.out.println(json);
    }

    static public void  foo4(){
        String xml = "<Body>\n" +
                "   <GetPriceResponse>\n" +
                "      <Price>1.90</Price>\n" +
                "   </GetPriceResponse>\n" +
                "</Body>";
        System.out.println(xml);
        String json = (StaxonUtils.xml2json(xml));
        System.out.println(json);
    }

}
