Android -XML 解析
=================
XML stands for Extensible Mark-up Language.XML is a very popular format and commonly used for sharing data on the internet. This chapter explains how to parse the XML file and extract necessary information from it.

Android provides three types of XML parsers which are **DOM**,**SAX** and **XMLPullParser**. Among all of them android recommend **XMLPullParser** because it is efficient and easy to use. So we are going to use XMLPullParser for parsing XML

The first step is to identify the fields in the XML data in which you are interested in. For example. In the XML given below we interested in getting temperature only.

```
<CHANNELS>
	<package name="热门节目">
		<CHANNEL no="1" service_id="1" name="CCTV 1" url="http://www.cfm880.com/wp-content/uploads/2015/12/videoviewdemo.mp4?_=1" apid="1001" vpid="0" ppid="3593" atype="0" vtype="0" hd="1" visible="1" favorite="0"/>
		<CHANNEL no="2" service_id="2" name="CCTV 2" url="http://www.cfm880.com/wp-content/uploads/2015/12/videoviewdemo.mp4?_=1" apid="1001" vpid="0" ppid="3593" atype="0" vtype="0" hd="1" visible="1" favorite="0"/>
	</package>
	
	<package name="卫视台">
		<CHANNEL no="30" service_id="30" name="广东卫视" url="http://www.cfm880.com/wp-content/uploads/2015/12/videoviewdemo.mp4?_=1" apid="1001" vpid="0" ppid="3593" atype="0" vtype="0" hd="1" visible="1" favorite="0"/>
		<CHANNEL no="31" service_id="31" name="广西卫视" url="http://www.cfm880.com/wp-content/uploads/2015/12/videoviewdemo.mp4?_=1" apid="1001" vpid="0" ppid="3593" atype="0" vtype="0" hd="1" visible="1" favorite="0"/>
		<CHANNEL no="32" service_id="32" name="云南卫视" url="udp://@224.0.1.32:5000" apid="1001" vpid="0" ppid="3593" atype="0" vtype="0" hd="1" visible="1" favorite="0"/>
		<CHANNEL no="33" service_id="33" name="重庆卫视" url="udp://@224.0.1.33:5000" apid="1001" vpid="0" ppid="3593" atype="0" vtype="0" hd="1" visible="1" favorite="0"/>
		<CHANNEL no="34" service_id="34" name="甘肃卫视" url="udp://@224.0.1.34:5000" apid="1001" vpid="0" ppid="3593" atype="0" vtype="0" hd="1" visible="1" favorite="0"/>
		<CHANNEL no="35" service_id="35" name="湖北卫视" url="udp://@224.0.1.35:5000" apid="1001" vpid="0" ppid="3593" atype="0" vtype="0" hd="1" visible="1" favorite="0"/>
		<CHANNEL no="36" service_id="36" name="北京卫视" url="udp://@224.0.1.36:5000" apid="1001" vpid="0" ppid="3593" atype="0" vtype="0" hd="1" visible="1" favorite="0"/>
		<CHANNEL no="37" service_id="37" name="卡酷卫视" url="udp://@224.0.1.37:5000" apid="1001" vpid="0" ppid="3593" atype="0" vtype="0" hd="1" visible="1" favorite="0"/>
		<CHANNEL no="38" service_id="38" name="江西卫视" url="udp://@224.0.1.38:5000" apid="1001" vpid="0" ppid="3593" atype="0" vtype="0" hd="1" visible="1" favorite="0"/>
		<CHANNEL no="39" service_id="39" name="四川卫视" url="udp://@224.0.1.39:5000" apid="1001" vpid="0" ppid="3593" atype="0" vtype="0" hd="1" visible="1" favorite="0"/>
		<CHANNEL no="40" service_id="40" name="陕西卫视" url="udp://@224.0.1.40:5000" apid="1001" vpid="0" ppid="3593" atype="0" vtype="0" hd="1" visible="1" favorite="0"/>
		<CHANNEL no="41" service_id="41" name="陕西农林" url="udp://@224.0.1.41:5000" apid="1001" vpid="0" ppid="3593" atype="0" vtype="0" hd="1" visible="1" favorite="0"/>
		<CHANNEL no="42" service_id="42" name="山东卫视" url="udp://@224.0.1.42:5000" apid="1001" vpid="0" ppid="3593" atype="0" vtype="0" hd="1" visible="1" favorite="0"/>
		<CHANNEL no="43" service_id="43" name="山西卫视" url="udp://@224.0.1.43:5000" apid="1001" vpid="0" ppid="3593" atype="0" vtype="0" hd="1" visible="1" favorite="0"/>
		<CHANNEL no="44" service_id="44" name="河南卫视" url="udp://@224.0.1.44:5000" apid="1001" vpid="0" ppid="3593" atype="0" vtype="0" hd="1" visible="1" favorite="0"/>
		<CHANNEL no="45" service_id="45" name="宁夏卫视" url="udp://@224.0.1.45:5000" apid="1001" vpid="0" ppid="3593" atype="0" vtype="0" hd="1" visible="1" favorite="0"/>
		<CHANNEL no="46" service_id="46" name="浙江卫视" url="udp://@224.0.1.46:5000" apid="1001" vpid="0" ppid="3593" atype="0" vtype="0" hd="1" visible="1" favorite="0"/>
		<CHANNEL no="47" service_id="47" name="安徽卫视" url="udp://@224.0.1.47:5000" apid="1001" vpid="0" ppid="3593" atype="0" vtype="0" hd="1" visible="1" favorite="0"/>
		<CHANNEL no="48" service_id="48" name="河北卫视" url="udp://@224.0.1.48:5000" apid="1001" vpid="0" ppid="3593" atype="0" vtype="0" hd="1" visible="1" favorite="0"/>
		<CHANNEL no="49" service_id="49" name="黑龙江卫视" url="udp://@224.0.1.49:5000" apid="1001" vpid="0" ppid="3593" atype="0" vtype="0" hd="1" visible="1" favorite="0"/>
	</package>
</CHANNELS>
```

# XML - Elements

An xml file consist of many components. Here is the table defining the components of an XML file and their description.


**Sr.No** | **Component & description** 
----------|------------------------------
1         | **Prolog** <br An XML file starts with a prolog. The first line that contains the information about a file is prolog/>

