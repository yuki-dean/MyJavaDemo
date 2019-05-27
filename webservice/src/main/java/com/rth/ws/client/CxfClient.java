package com.rth.ws.client;

import org.apache.cxf.endpoint.Client;
import org.apache.cxf.endpoint.Endpoint;
import org.apache.cxf.jaxws.endpoint.dynamic.JaxWsDynamicClientFactory;
import org.apache.cxf.service.model.*;

import javax.xml.namespace.QName;
import java.beans.PropertyDescriptor;
import java.util.Collection;
import java.util.List;

public class CxfClient {
    private static String wsdlUrl =   "http://www.webxml.com.cn/WebServices/IpAddressSearchWebService.asmx?wsdl";//"http://172.20.29.51:8180/uniplatform/service/UniNewOrderDataService?wsdl";

    public static void main(String[] args) throws Exception {
        // 创建动态客户端
        JaxWsDynamicClientFactory factory = JaxWsDynamicClientFactory.newInstance();
        Client client = factory.createClient(wsdlUrl);
        /**endpoint据说为http://172.20.29.51:8180/uniplatform/service/UniNewOrderDataService
         * 不过toString方法打印的为{},有点奇怪,不过getEndpointInfo打印的为BindingQName，ServiceQName，QName*/
        Endpoint endpoint = client.getEndpoint();
//        System.out.println("endpoint:" + endpoint);
        /**获取Service*/
        ServiceInfo serviceInfo = endpoint.getService().getServiceInfos().get(0);
        System.out.println(serviceInfo);
        /**创建Service*/
        Collection<BindingInfo> bindings = serviceInfo.getBindings();
        System.out.println(bindings);
        BindingInfo binding = null;
        for (BindingInfo b : bindings) {
            binding = b;
        }

        System.out.println(binding);

        /**创建Service下的方法*/
        QName opName = null;

        for (BindingOperationInfo bindingOperationInfo: binding.getOperations()) {
            System.out.println("Info:" + bindingOperationInfo.getName().getLocalPart());
            if ("getCountryCityByIp".equals(bindingOperationInfo.getName().getLocalPart())){
                opName = bindingOperationInfo.getName();
                System.out.println("opName:" +opName.getLocalPart());
                System.out.println("opName:" +opName.getNamespaceURI());
//                System.out.println("opName:" +opName.getPrefix());
            }
        }

        BindingOperationInfo operation2 = binding.getOperation(opName);
        BindingMessageInfo input = null;

        if (operation2.isUnwrapped()){
            input = operation2.getUnwrappedOperation().getInput();
        } else {
            input = operation2.getWrappedOperation().getInput();
        }

        List<MessagePartInfo> messageParts = input.getMessageParts();

        MessagePartInfo messagePartInfo = messageParts.get(0);
        Class<?> partClass = messagePartInfo.getTypeClass();
        Object inputObject = partClass.newInstance();
        System.out.println(partClass);
        System.out.println( partClass.getName() );


//        PropertyDescriptor partPropertyDescriptor = new PropertyDescriptor("theIpAddress", partClass);
//        partPropertyDescriptor.getWriteMethod().invoke(inputObject, "106.127.33.140");

        /*
        PropertyDescriptor partPropertyDescriptor2 = new PropertyDescriptor("passportId", partClass);
        partPropertyDescriptor2.getWriteMethod().invoke(inputObject, Long.valueOf("31498882"));

        PropertyDescriptor partPropertyDescriptor3 = new PropertyDescriptor("beginDate", partClass);
        partPropertyDescriptor3.getWriteMethod().invoke(inputObject, 20181230);

        PropertyDescriptor partPropertyDescriptor4 = new PropertyDescriptor("endDate", partClass);
        partPropertyDescriptor4.getWriteMethod().invoke(inputObject, 20190109);

        PropertyDescriptor partPropertyDescriptor5 = new PropertyDescriptor("orderStatus", partClass);
        partPropertyDescriptor5.getWriteMethod().invoke(inputObject, "10054");
        */

        Object[] result = client.invoke(opName, new Object[]{"theIpAddress", "106.127.33.140"});

        Class.forName(result[0].getClass().getName());
        System.out.println("result:" +result[0].getClass().getName());
    }
}
