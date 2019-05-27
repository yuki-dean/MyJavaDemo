package com.lf.camel;

public class CxfClient {

    private static  String content=
            "    <getEnCnTwoWayTranslator xmlns=\"http://WebXml.com.cn/\"><Word>sea</Word></getEnCnTwoWayTranslator>";
    private static  String content0=
            "    <getEnCnTwoWayTranslator xmlns=\"http://WebXml.com.cn/\">\n" +
                    "      <Word>sea</Word>\n" +
                    "    </getEnCnTwoWayTranslator>\n";

    static String content2 = "<soap:Envelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\">\n" +
            "  <soap:Body>\n" +
            "    <getEnCnTwoWayTranslator xmlns=\"http://WebXml.com.cn/\">\n" +
            "      <Word>sea</Word>\n" +
            "    </getEnCnTwoWayTranslator>\n" +
            "  </soap:Body>\n" +
            "</soap:Envelope>";

    static String url = "http://www.webxml.com.cn/WebServices/TranslatorWebService.asmx";
    static String portName = "{http://WebXml.com.cn/}TranslatorWebServiceSoap";

    public static void main(String[] args) throws Exception {
        String resturl = "http://localhost:9000/fee";
        String wsUrl = "http://www.webxml.com.cn/WebServices/TranslatorWebService.asmx";
        Ws2RestGateway gateway =
                Ws2RestGateway
                        .builder()
//                        .format(Ws2RestGateway.DataFormat.PAYLOAD)
                        .restUrl(resturl)
                        .wsUrl(wsUrl)
                        .portName(portName)
                        .dismissAdditionalAttr(true)
                        .build();
        gateway.run();
        Thread.sleep(1000);
    }
}
