package com.gpzuestc.framework.serialize;

import java.io.StringReader;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.XmlRootElement;

import com.gpzuestc.util.JsonUtil;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.io.xml.DomDriver;

public class XmlDemo {
	
/**
 * <xml> <AppId>wx923604a93e0c64c8</AppId>
				 * <CreateTime>1413192605</CreateTime>
				 * <InfoType>component_verify_ticket</InfoType>
				 * <ComponentVerifyTicket>TicketContent</ComponentVerifyTicket>
				 * </xml>
 */
	
	public static void main(String[] args) {
		String xmlStr = "<xml><AppId>wx923604a93e0c64c8</AppId><CreateTime>1413192605</CreateTime><InfoType><![CDATA[event]]></InfoType><ComponentVerifyTicket>TicketContent</ComponentVerifyTicket><foo>bbb</foo></xml>";
		try {
			XStream xStream = new XStream(new DomDriver());
			xStream.processAnnotations(ComponentXml.class);
			xStream.ignoreUnknownElements(); //忽略
			ComponentXml x = (ComponentXml)xStream.fromXML(xmlStr);
			System.out.println(JsonUtil.toJSONString(x));
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		
		try {
			JAXBContext context = JAXBContext.newInstance(ComponentXml.class);    
			Unmarshaller unmarshaller = context.createUnmarshaller();  
			ComponentXml componentXml = (ComponentXml)unmarshaller.unmarshal(new StringReader(xmlStr));
			System.out.println(JsonUtil.toJSONString(componentXml));
		} catch (JAXBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
@XStreamAlias("xml")
@XmlRootElement(name="xml")
class ComponentXml {
    public String AppId;
    public String CreateTime;
    public String InfoType;
    public String ComponentVerifyTicket;
    public String aab;
}