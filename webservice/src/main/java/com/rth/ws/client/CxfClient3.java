package com.rth.ws.client;

import org.apache.cxf.endpoint.Client;
import org.apache.cxf.jaxws.endpoint.dynamic.JaxWsDynamicClientFactory;

import javax.xml.namespace.QName;

public class CxfClient3 {

    private static String wsdlUrl =   "http://www.webxml.com.cn/WebServices/IpAddressSearchWebService.asmx?wsdl";//"http://172.20.29.51:8180/uniplatform/service/UniNewOrderDataService?wsdl";


    public static void main(String[] args) throws Exception {
        //采用动态工厂方式 不需要指定服务接口
        JaxWsDynamicClientFactory dcf = JaxWsDynamicClientFactory.newInstance();
        Client client = dcf
                .createClient(wsdlUrl);
        QName qName = new QName("http://com.soft.ws/my", "authorization");
        Object[] result = client.invoke(qName,
                new Object[] { "admin", "123456" });
        System.out.println(result[0]);
    }

}
