package com.lf.camel;

import lombok.Builder;
import org.apache.camel.builder.RouteBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;

@Builder
public class Ws2RestGateway extends AbstractGateway{
    private String restUrl;
    private String wsUrl;
    private String portName;
    private String binding;
    private DataFormat format;
    private boolean dismissAdditionalAttr;
    private static String destUrlFormat = "cxf:%s?wsdlURL=%s?wsdl&dataFormat=%s&portName=%s";

    public enum DataFormat{
        POJO(1, "POJO"),
        PAYLOAD(2, "PAYLOAD"),
        MESSAGE(3, "MESSAGE"),
        CXF_MESSAGE(4, "CXF_MESSAGE");
        private int index;
        private String format;
        DataFormat(int i, String format){
            this.index = i;
            this.format = format;
        }
    }

    private String AssembleDestUrl(){
        String value = String.format(destUrlFormat, wsUrl, wsUrl, format.format, portName);
        return value;
    }

    /**
     * add route
     */
    @Override
    protected void addRoute(){

        if(format == null) format = DataFormat.PAYLOAD;
        try {
            context.addRoutes(new RouteBuilder() {
                @Override
                public void configure() {
                    String destUri= AssembleDestUrl();
                    String sourceUri =  String.format("netty4-http:%s", restUrl);
                    log.debug("Source URI:" + sourceUri);
                    log.debug("Destination URI:" + destUri);
                    from(sourceUri)
                            .process(ex-> {
                                String json = collectStreamToString( (InputStream) ex.getIn().getBody());
                                String xmlrequest = StaxonUtils.json2xml(json);
                                ex.getOut().setBody(xmlrequest);
                                log.debug("XML Request Send to Cxf Endooint: "+xmlrequest);
                            })
                            .to(destUri)
                            .convertBodyTo(String.class)
                            .process(ex-> {
                                String xml = (String)ex.getIn().getBody();
                                String resp= StaxonUtils.xml2json(xml);
                                log.debug("Reply From Cxf Endpoint:"+ resp);
                                if(dismissAdditionalAttr) {
                                    resp = dismissadditionalAttributes(resp);
                                    log.debug("Response after Dismiss additional attributes:" + resp);
                                }
                                ex.getOut().setBody(resp);
                            });
                            //.to("log:output");
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     *  Dismiss additional attributes
     * @param response
     * @return
     */
    private String dismissadditionalAttributes(String response){
        return response.replaceAll("\"@.*\n", "\r");
    }


    /**
     *
     * @param in
     * @return
     */
    private String collectStreamToString(InputStream in ){
        InputStreamReader reader =  new InputStreamReader(in);
        char[] data =  new char[1024*4];
        String str = null;
        int size = 0;
        try {
            size = reader.read(data);
            str = new String(Arrays.copyOf(data, size));
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return str;
    }

    private String collectStreamToString2(InputStream in ){
        InputStreamReader reader =  new InputStreamReader(in);
//        byte[] data =  new byte[1024];
        char[] data =  new char[1024];
        StringBuilder builder= new StringBuilder();
        int size = 0;
        try {
            do {
//                size = in.read(data);
                System.out.println("begin to reading...");
                size = reader.read(data);
                builder.append(data, 0 , size);
                System.out.println("append:" + new String(data));
            }while(size >-1 );
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        String str =  builder.toString();
        System.out.println("get String:"+str);
        return str;
    }
}
