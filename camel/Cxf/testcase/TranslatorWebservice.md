
#Translator Webservice

webservice Url:
```$xslt
http://www.webxml.com.cn/WebServices/TranslatorWebService.asmx
```
REST request via curl
```$xslt
curl  -H "Content-Type:application/json" -v http://localhost:9000/foo -X POST -d@data.json
```

Json string in data file:
```$xslt
{"getEnCnTwoWayTranslator" : {"@xmlns" : "http://WebXml.com.cn/","Word" : "thing"}}
```


#Weather 