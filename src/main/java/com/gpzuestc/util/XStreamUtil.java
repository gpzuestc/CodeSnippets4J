package com.gpzuestc.util;

import java.io.Writer;
import java.util.HashMap;
import java.util.Map;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.core.util.QuickWriter;
import com.thoughtworks.xstream.io.HierarchicalStreamWriter;
import com.thoughtworks.xstream.io.xml.DomDriver;
import com.thoughtworks.xstream.io.xml.PrettyPrintWriter;
import com.thoughtworks.xstream.io.xml.XmlFriendlyNameCoder;
import com.thoughtworks.xstream.io.xml.XppDriver;


public class XStreamUtil {
	
	private static final ThreadLocal<XStream> cDataThread = new ThreadLocal<XStream>(){
		@Override
		protected XStream initialValue() {
			XStream xstream = new XStream(new XppDriver(new XmlFriendlyNameCoder("_-", "_")) {
	        	public HierarchicalStreamWriter createWriter(Writer out) {
	                return new PrettyPrintWriter(out, getNameCoder()) {
	                    // 对所有xml节点的转换都增加CDATA标记
	                    boolean cdata = true;

	                    @SuppressWarnings("rawtypes")
						public void startNode(String name, Class clazz) {
	                        super.startNode(name, clazz);
	                    }

	                    protected void writeText(QuickWriter writer, String text) {
	                        if (cdata) {
	                            writer.write("<![CDATA[");
	                            writer.write(text);
	                            writer.write("]]>");
	                        } else {
	                            writer.write(text);
	                        }
	                    }
	                };
	            }
	        });
			xstream.autodetectAnnotations(true);
			return xstream;
		}
	};
	
//	private static final ThreadLocal<XStream> toBeanThread = new ThreadLocal<XStream>(){
//		@Override
//		protected XStream initialValue() {
//			return new XStream(new DomDriver("utf8"));
//		}
//	};
	
	private static final ThreadLocal<Map<Class, XStream>> toBeanThread = new ThreadLocal<Map<Class, XStream>>(){
		@Override
		protected Map<Class, XStream> initialValue() {
			return new HashMap<Class, XStream>();
			//return new XStream(new DomDriver("utf8"));
		}
	};
	
//	private static final ThreadLocal<XStream> toXMLThread = new ThreadLocal<XStream>(){
//		@Override
//		protected XStream initialValue() {
//			return new XStream(new XppDriver(new XmlFriendlyNameCoder("_-", "_")) {
//				
//			});
//		}
//	};
	
	private static final ThreadLocal<Map<Class, XStream>> toXMLThread = new ThreadLocal<Map<Class, XStream>>(){
		@Override
		protected Map<Class, XStream> initialValue() {
			return new HashMap<Class, XStream>();
		}
	};
	
    public static XStream getCDataXStream() {
        return cDataThread.get();
    }
    
    public static <T> T toBean(String xmlStr, Class<T> clazz) {
    	Map<Class, XStream> map = toBeanThread.get();
    	XStream xstream = map.get(clazz);
    	if(xstream == null){
    		xstream = new XStream(new DomDriver("utf8"));
    		xstream.processAnnotations(clazz);
    		xstream.ignoreUnknownElements();
    		map.put(clazz, xstream);
    	}
        @SuppressWarnings("unchecked")
        T t = (T) xstream.fromXML(xmlStr);
        return t;
    }
    
    public static String toXml(Object obj) {
    	
    	Map<Class, XStream> map = toXMLThread.get();
    	Class clazz = obj.getClass();
        XStream xstream = map.get(clazz);
        if(xstream == null){
        	xstream = new XStream(new XppDriver(new XmlFriendlyNameCoder("_-", "_")) {
	        	
	        });
        	xstream.processAnnotations(obj.getClass()); // 识别obj类中的注解
        	map.put(clazz, xstream);
        }
        /*
         // 以压缩的方式输出XML
         StringWriter sw = new StringWriter();
         xstream.marshal(obj, new CompactWriter(sw));
         return sw.toString();
         */
        // 以格式化的方式输出XML
        return xstream.toXML(obj);
    }
    
    public static void main(String[] args) {
		XStream xStream = XStreamUtil.getCDataXStream();
		Fooo f = new Fooo();
		f.setA_b("hi");
		System.out.println(xStream.toXML(f));
		System.out.println(toXml(f));
	}
    
   
}
@XStreamAlias("fo")
class Fooo{
//	@XStreamAlias("a_b")
//	@XStreamAsAttribute
	private String a_b;

	public String getA_b() {
		return a_b;
	}

	public void setA_b(String a_b) {
		this.a_b = a_b;
	}
	
}
