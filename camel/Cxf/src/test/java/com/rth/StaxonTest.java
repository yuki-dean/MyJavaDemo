package com.rth;

import com.lf.camel.StaxonUtils;
import org.junit.Test;

public class StaxonTest {


    @Test
    public void testXml(){
        String json4= "{\"getEnCnTwoWayTranslator\" : {\"@xmlns\" : \"http://WebXml.com.cn/\",\"Word\" : \"sea\"}}";
        String xmlrequest1 = StaxonUtils.json2xml(json4);
        System.out.println("Response:"+ xmlrequest1);
    }

    @Test
    public void testJson(){
        String resp =     "<getEnCnTwoWayTranslatorResponse xmlns=\"http://WebXml.com.cn/\"><getEnCnTwoWayTranslatorResult> " +
                "<string>string</string> " +
                "<string>string</string> " +
                "</getEnCnTwoWayTranslatorResult> " +
                "</getEnCnTwoWayTranslatorResponse>";
        System.out.println(StaxonUtils.json2xml(StaxonUtils.xml2json(resp)));
    }

    @Test
    public void test2(){
        String json = "{\"name\":\"bobo\"}";
        System.out.println( StaxonUtils.json2xml(json) );
    }


}
