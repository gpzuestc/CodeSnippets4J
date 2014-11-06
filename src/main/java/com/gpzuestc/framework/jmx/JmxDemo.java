package com.gpzuestc.framework.jmx;

import java.lang.management.MemoryUsage;
import java.util.Set;

import javax.management.MBeanAttributeInfo;
import javax.management.MBeanInfo;
import javax.management.MBeanServerConnection;
import javax.management.ObjectName;
import javax.management.openmbean.CompositeDataSupport;
import javax.management.remote.JMXConnector;
import javax.management.remote.JMXConnectorFactory;
import javax.management.remote.JMXServiceURL;

public class JmxDemo {

	public static void main(String[] args) throws Exception {
		JMXServiceURL jmxServiceURL = new JMXServiceURL(
				"service:jmx:rmi:///jndi/rmi://179.30.77.159:8996/jmxrmi");
		JMXConnector connector = JMXConnectorFactory.connect(jmxServiceURL);
		MBeanServerConnection mbsc = connector.getMBeanServerConnection();
		
		 // list domains  
        String[] domains = mbsc.getDomains();  
        for (String domain : domains) {  
            System.out.println("domain : " + domain);  
        }  
  
        // list ObjectNames  
        Set<ObjectName> names = mbsc.queryNames(null, null);  
        for (ObjectName name : names) {  
            System.err.println("ObjectName : " + name);  
//            ObjectName objectName = new ObjectName("java.lang:type=Runtime");
            MBeanInfo mBeanInfo = mbsc.getMBeanInfo(name);
            
            MBeanAttributeInfo[] mBeanAttributes = mBeanInfo.getAttributes();
            for(MBeanAttributeInfo mBeanAttribute : mBeanAttributes) {
            	try {
					System.out.println("==>[" + mBeanAttribute.getName() + "] @" + mBeanAttribute.getType() + " : " +  mbsc.getAttribute(name, mBeanAttribute.getName()));
				} catch (Exception e) {
					System.err.println(e.getMessage());
				}
            }
        }  

		 ObjectName heapObjName = new ObjectName("java.lang:type=Memory");     
		    
		//堆内存     
		MemoryUsage heapMemoryUsage = MemoryUsage.from((CompositeDataSupport) mbsc.getAttribute(heapObjName, "HeapMemoryUsage"));     
		long commitMemory = heapMemoryUsage.getCommitted();// 堆当前分配     
		long usedMemory = heapMemoryUsage.getUsed();  
		System.out.println("committed: " + heapMemoryUsage.getCommitted() + "k");
		System.out.println("init: " + heapMemoryUsage.getInit() + "k");
		System.out.println("max: " + heapMemoryUsage.getMax() + "k");
		System.out.println("used: " + heapMemoryUsage.getUsed() + "k");
		System.out.print("堆内存总量:"+ heapMemoryUsage.getMax()/1024 + "KB,当前分配量:" + commitMemory / 1024 + "KB,当前使用:" + usedMemory/1024 + "KB,");     
		System.out.println("堆内存使用率:" + (float) usedMemory * 100 / commitMemory + "%");// 堆使用率     
		    
		//栈内存     
		MemoryUsage nonheapMemoryUsage = MemoryUsage.from((CompositeDataSupport) mbsc.getAttribute(heapObjName,"NonHeapMemoryUsage"));     
		long noncommitMemory = nonheapMemoryUsage.getCommitted();     
		long nonusedMemory = heapMemoryUsage.getUsed();     
		System.out.println("栈内存使用率:" + (float) nonusedMemory * 100 / noncommitMemory + "%");     
		             
		//PermGen内存     
		ObjectName permObjName = new ObjectName("java.lang:type=MemoryPool,name=CMS Perm Gen");     
		MemoryUsage permGenUsage = MemoryUsage.from((CompositeDataSupport) mbsc.getAttribute(permObjName,"Usage"));     
		long committed = permGenUsage.getCommitted();// 持久堆大小     
		long used = permGenUsage.getUsed(); 
		System.out.println("perm committed: " + permGenUsage.getCommitted());
		System.out.println("perm init: " + permGenUsage.getInit());
		System.out.println("perm max: " + permGenUsage.getMax());
		System.out.println("perm used: " + permGenUsage.getUsed());
		System.out.println("perm 使用量:" + (float) used * 100 / committed + "%");// 持久堆使用率  
        
        // close connection  
        connector.close();  
	}
	
	/**
	domain : resin
	domain : java.lang
	domain : com.mchange.v2.c3p0
	domain : com.sun.management
	domain : JMImplementation
	domain : java.util.logging
	ObjectName : resin:type=Environment,Host=default,WebApp=/resin-admin
	==>[ClassPath] @[Ljava.lang.String; : [Ljava.lang.String;@303020ad
	==>[Name] @java.lang.String : null
	==>[ObjectName] @javax.management.ObjectName : resin:type=Environment,Host=default,WebApp=/resin-admin
	==>[Type] @java.lang.String : Environment
	ObjectName : resin:type=BlockManager
	==>[BlockCapacity] @long : 16384
	==>[BlockReadCountTotal] @long : 5
	==>[BlockWriteCountTotal] @long : 5
	==>[HitCountTotal] @long : 3
	==>[MemorySize] @long : 134217728
	==>[MissCountTotal] @long : 3
	==>[MissRate] @double : 0.5
	==>[Name] @java.lang.String : null
	==>[ObjectName] @javax.management.ObjectName : resin:type=BlockManager
	==>[Type] @java.lang.String : BlockManager
	ObjectName : resin:type=Config,name="jar:file:/opt/resin/resin-video-api4-server/project-jars/resin-cxf-4.0.36.jar!/META-INF/maven/caucho.com/pom.xml"
	==>[Crc64] @long : -1447245931064234040
	==>[LastModified] @long : 1366961518000
	==>[Length] @long : 728
	==>[Name] @java.lang.String : jar:file:/opt/resin/resin-video-api4-server/project-jars/resin-cxf-4.0.36.jar!/META-INF/maven/caucho.com/pom.xml
	==>[ObjectName] @javax.management.ObjectName : resin:type=Config,name="jar:file:/opt/resin/resin-video-api4-server/project-jars/resin-cxf-4.0.36.jar!/META-INF/maven/caucho.com/pom.xml"
	==>[Path] @java.lang.String : /META-INF/maven/caucho.com/pom.xml
	==>[Type] @java.lang.String : Config
	ObjectName : java.lang:type=GarbageCollector,name=ConcurrentMarkSweep
	==>[LastGcInfo] @javax.management.openmbean.CompositeData : null
	==>[CollectionCount] @long : 2
	==>[CollectionTime] @long : 0
	==>[Name] @java.lang.String : ConcurrentMarkSweep
	==>[Valid] @boolean : true
	==>[MemoryPoolNames] @[Ljava.lang.String; : [Ljava.lang.String;@219ce060
	ObjectName : resin:type=Management
	==>[Name] @java.lang.String : null
	==>[ObjectName] @javax.management.ObjectName : resin:type=Management
	==>[Type] @java.lang.String : Management
	ObjectName : resin:type=BamService
	==>[ExternalMessageReadCount] @long : 0
	==>[ExternalMessageWriteCount] @long : 0
	==>[Name] @java.lang.String : null
	==>[ObjectName] @javax.management.ObjectName : resin:type=BamService
	==>[Type] @java.lang.String : BamService
	ObjectName : resin:type=Config,name="classpath:/META-INF/caucho/app-default.xml"
	==>[Crc64] @long : -5170462343304544697
	==>[LastModified] @long : 0
	==>[Length] @long : 12620
	==>[Name] @java.lang.String : classpath:/META-INF/caucho/app-default.xml
	==>[ObjectName] @javax.management.ObjectName : resin:type=Config,name="classpath:/META-INF/caucho/app-default.xml"
	==>[Path] @java.lang.String : /META-INF/caucho/app-default.xml
	==>[Type] @java.lang.String : Config
	ObjectName : resin:type=LocalRepository
	==>[Name] @java.lang.String : null
	==>[ObjectName] @javax.management.ObjectName : resin:type=LocalRepository
	==>[RootHash] @java.lang.String : null
	==>[TagMap] @java.util.Map : {}
	==>[Type] @java.lang.String : LocalRepository
	ObjectName : java.lang:type=Compilation
	==>[Name] @java.lang.String : HotSpot 64-Bit Tiered Compilers
	==>[CompilationTimeMonitoringSupported] @boolean : true
	==>[TotalCompilationTime] @long : 26130
	ObjectName : resin:type=Config,name="jar:file:/opt/resin/resin-video-api4-server/project-jars/resin-xwork2-4.0.36.jar!/META-INF/maven/caucho.com/pom.xml"
	==>[Crc64] @long : 1256106925113860296
	==>[LastModified] @long : 1366961518000
	==>[Length] @long : 734
	==>[Name] @java.lang.String : jar:file:/opt/resin/resin-video-api4-server/project-jars/resin-xwork2-4.0.36.jar!/META-INF/maven/caucho.com/pom.xml
	==>[ObjectName] @javax.management.ObjectName : resin:type=Config,name="jar:file:/opt/resin/resin-video-api4-server/project-jars/resin-xwork2-4.0.36.jar!/META-INF/maven/caucho.com/pom.xml"
	==>[Path] @java.lang.String : /META-INF/maven/caucho.com/pom.xml
	==>[Type] @java.lang.String : Config
	ObjectName : java.lang:type=MemoryPool,name=Par Eden Space
	==>[Name] @java.lang.String : Par Eden Space
	==>[Type] @java.lang.String : HEAP
	==>[Valid] @boolean : true
	==>[CollectionUsage] @javax.management.openmbean.CompositeData : javax.management.openmbean.CompositeDataSupport(compositeType=javax.management.openmbean.CompositeType(name=java.lang.management.MemoryUsage,items=((itemName=committed,itemType=javax.management.openmbean.SimpleType(name=java.lang.Long)),(itemName=init,itemType=javax.management.openmbean.SimpleType(name=java.lang.Long)),(itemName=max,itemType=javax.management.openmbean.SimpleType(name=java.lang.Long)),(itemName=used,itemType=javax.management.openmbean.SimpleType(name=java.lang.Long)))),contents={committed=503316480, init=503316480, max=503316480, used=0})
	==>[CollectionUsageThreshold] @long : 0
	==>[CollectionUsageThresholdCount] @long : 0
	==>[MemoryManagerNames] @[Ljava.lang.String; : [Ljava.lang.String;@48067064
	==>[PeakUsage] @javax.management.openmbean.CompositeData : javax.management.openmbean.CompositeDataSupport(compositeType=javax.management.openmbean.CompositeType(name=java.lang.management.MemoryUsage,items=((itemName=committed,itemType=javax.management.openmbean.SimpleType(name=java.lang.Long)),(itemName=init,itemType=javax.management.openmbean.SimpleType(name=java.lang.Long)),(itemName=max,itemType=javax.management.openmbean.SimpleType(name=java.lang.Long)),(itemName=used,itemType=javax.management.openmbean.SimpleType(name=java.lang.Long)))),contents={committed=503316480, init=503316480, max=503316480, used=503316480})
	==>[Usage] @javax.management.openmbean.CompositeData : javax.management.openmbean.CompositeDataSupport(compositeType=javax.management.openmbean.CompositeType(name=java.lang.management.MemoryUsage,items=((itemName=committed,itemType=javax.management.openmbean.SimpleType(name=java.lang.Long)),(itemName=init,itemType=javax.management.openmbean.SimpleType(name=java.lang.Long)),(itemName=max,itemType=javax.management.openmbean.SimpleType(name=java.lang.Long)),(itemName=used,itemType=javax.management.openmbean.SimpleType(name=java.lang.Long)))),contents={committed=503316480, init=503316480, max=503316480, used=88457336})
	Usage threshold is not supported
	Usage threshold is not supported
	==>[CollectionUsageThresholdExceeded] @boolean : true
	==>[CollectionUsageThresholdSupported] @boolean : true
	Usage threshold is not supported
	==>[UsageThresholdSupported] @boolean : false
	ObjectName : java.lang:type=MemoryPool,name=CMS Perm Gen
	==>[Name] @java.lang.String : CMS Perm Gen
	==>[Type] @java.lang.String : NON_HEAP
	==>[Valid] @boolean : true
	==>[CollectionUsage] @javax.management.openmbean.CompositeData : javax.management.openmbean.CompositeDataSupport(compositeType=javax.management.openmbean.CompositeType(name=java.lang.management.MemoryUsage,items=((itemName=committed,itemType=javax.management.openmbean.SimpleType(name=java.lang.Long)),(itemName=init,itemType=javax.management.openmbean.SimpleType(name=java.lang.Long)),(itemName=max,itemType=javax.management.openmbean.SimpleType(name=java.lang.Long)),(itemName=used,itemType=javax.management.openmbean.SimpleType(name=java.lang.Long)))),contents={committed=268435456, init=268435456, max=268435456, used=18620000})
	==>[CollectionUsageThreshold] @long : 0
	==>[CollectionUsageThresholdCount] @long : 0
	==>[MemoryManagerNames] @[Ljava.lang.String; : [Ljava.lang.String;@42b307f0
	==>[PeakUsage] @javax.management.openmbean.CompositeData : javax.management.openmbean.CompositeDataSupport(compositeType=javax.management.openmbean.CompositeType(name=java.lang.management.MemoryUsage,items=((itemName=committed,itemType=javax.management.openmbean.SimpleType(name=java.lang.Long)),(itemName=init,itemType=javax.management.openmbean.SimpleType(name=java.lang.Long)),(itemName=max,itemType=javax.management.openmbean.SimpleType(name=java.lang.Long)),(itemName=used,itemType=javax.management.openmbean.SimpleType(name=java.lang.Long)))),contents={committed=268435456, init=268435456, max=268435456, used=77774672})
	==>[Usage] @javax.management.openmbean.CompositeData : javax.management.openmbean.CompositeDataSupport(compositeType=javax.management.openmbean.CompositeType(name=java.lang.management.MemoryUsage,items=((itemName=committed,itemType=javax.management.openmbean.SimpleType(name=java.lang.Long)),(itemName=init,itemType=javax.management.openmbean.SimpleType(name=java.lang.Long)),(itemName=max,itemType=javax.management.openmbean.SimpleType(name=java.lang.Long)),(itemName=used,itemType=javax.management.openmbean.SimpleType(name=java.lang.Long)))),contents={committed=268435456, init=268435456, max=268435456, used=77774672})
	==>[UsageThreshold] @long : 0
	==>[UsageThresholdCount] @long : 0
	==>[CollectionUsageThresholdExceeded] @boolean : true
	==>[CollectionUsageThresholdSupported] @boolean : true
	==>[UsageThresholdExceeded] @boolean : true
	==>[UsageThresholdSupported] @boolean : true
	ObjectName : java.lang:type=MemoryPool,name=Par Survivor Space
	==>[Name] @java.lang.String : Par Survivor Space
	==>[Type] @java.lang.String : HEAP
	==>[Valid] @boolean : true
	==>[CollectionUsage] @javax.management.openmbean.CompositeData : javax.management.openmbean.CompositeDataSupport(compositeType=javax.management.openmbean.CompositeType(name=java.lang.management.MemoryUsage,items=((itemName=committed,itemType=javax.management.openmbean.SimpleType(name=java.lang.Long)),(itemName=init,itemType=javax.management.openmbean.SimpleType(name=java.lang.Long)),(itemName=max,itemType=javax.management.openmbean.SimpleType(name=java.lang.Long)),(itemName=used,itemType=javax.management.openmbean.SimpleType(name=java.lang.Long)))),contents={committed=62914560, init=62914560, max=62914560, used=2126880})
	==>[CollectionUsageThreshold] @long : 0
	==>[CollectionUsageThresholdCount] @long : 0
	==>[MemoryManagerNames] @[Ljava.lang.String; : [Ljava.lang.String;@77d2b01b
	==>[PeakUsage] @javax.management.openmbean.CompositeData : javax.management.openmbean.CompositeDataSupport(compositeType=javax.management.openmbean.CompositeType(name=java.lang.management.MemoryUsage,items=((itemName=committed,itemType=javax.management.openmbean.SimpleType(name=java.lang.Long)),(itemName=init,itemType=javax.management.openmbean.SimpleType(name=java.lang.Long)),(itemName=max,itemType=javax.management.openmbean.SimpleType(name=java.lang.Long)),(itemName=used,itemType=javax.management.openmbean.SimpleType(name=java.lang.Long)))),contents={committed=62914560, init=62914560, max=62914560, used=53820976})
	==>[Usage] @javax.management.openmbean.CompositeData : javax.management.openmbean.CompositeDataSupport(compositeType=javax.management.openmbean.CompositeType(name=java.lang.management.MemoryUsage,items=((itemName=committed,itemType=javax.management.openmbean.SimpleType(name=java.lang.Long)),(itemName=init,itemType=javax.management.openmbean.SimpleType(name=java.lang.Long)),(itemName=max,itemType=javax.management.openmbean.SimpleType(name=java.lang.Long)),(itemName=used,itemType=javax.management.openmbean.SimpleType(name=java.lang.Long)))),contents={committed=62914560, init=62914560, max=62914560, used=2126880})
	Usage threshold is not supported
	Usage threshold is not supported
	==>[CollectionUsageThresholdExceeded] @boolean : true
	==>[CollectionUsageThresholdSupported] @boolean : true
	Usage threshold is not supported
	==>[UsageThresholdSupported] @boolean : false
	ObjectName : resin:type=WebApp,Host=default,name=/resin-admin
	==>[ClassPath] @[Ljava.lang.String; : [Ljava.lang.String;@3bc0f2e5
	==>[ClientDisconnectCountTotal] @long : 0
	==>[Configs] @[Lcom.caucho.management.server.ConfigMXBean; : [Ljavax.management.ObjectName;@48082d37
	==>[ContextPath] @java.lang.String : /resin-admin
	==>[Enabled] @boolean : true
	==>[ErrorMessage] @java.lang.String : null
	==>[Host] @com.caucho.management.server.HostMXBean : resin:type=Host,name=default
	==>[Id] @java.lang.String : production/webapp/default/resin-admin
	==>[ManifestAttributes] @java.util.Map : null
	==>[Name] @java.lang.String : /resin-admin
	==>[ObjectName] @javax.management.ObjectName : resin:type=WebApp,Host=default,name=/resin-admin
	==>[RedeployCheckInterval] @long : 60000
	==>[RedeployMode] @java.lang.String : DEFAULT
	==>[RepositoryMetaData] @java.util.Map : {}
	==>[RequestCount] @int : 0
	==>[RequestCountTotal] @long : 0
	==>[RequestReadBytesTotal] @long : 0
	==>[RequestTimeTotal] @long : 0
	==>[RequestWriteBytesTotal] @long : 0
	==>[RootDirectory] @java.lang.String : /opt/resin/resin-video-api4-server/doc/admin
	==>[SessionManager] @com.caucho.management.server.SessionManagerMXBean : resin:type=SessionManager,Host=default,WebApp=/resin-admin
	==>[StartTime] @java.util.Date : Fri Aug 22 17:14:15 CST 2014
	==>[StartupMode] @java.lang.String : DEFAULT
	==>[State] @java.lang.String : ACTIVE
	==>[Status500CountTotal] @long : 0
	==>[Status500LastTime] @java.util.Date : null
	==>[Type] @java.lang.String : WebApp
	==>[Version] @java.lang.String : 
	ObjectName : java.lang:type=MemoryManager,name=CodeCacheManager
	==>[Name] @java.lang.String : CodeCacheManager
	==>[Valid] @boolean : true
	==>[MemoryPoolNames] @[Ljava.lang.String; : [Ljava.lang.String;@a81b1fb
	ObjectName : java.lang:type=MemoryPool,name=Code Cache
	==>[Name] @java.lang.String : Code Cache
	==>[Type] @java.lang.String : NON_HEAP
	==>[Valid] @boolean : true
	==>[CollectionUsage] @javax.management.openmbean.CompositeData : null
	CollectionUsage threshold is not supported
	CollectionUsage threshold is not supported
	==>[MemoryManagerNames] @[Ljava.lang.String; : [Ljava.lang.String;@4c3c2378
	==>[PeakUsage] @javax.management.openmbean.CompositeData : javax.management.openmbean.CompositeDataSupport(compositeType=javax.management.openmbean.CompositeType(name=java.lang.management.MemoryUsage,items=((itemName=committed,itemType=javax.management.openmbean.SimpleType(name=java.lang.Long)),(itemName=init,itemType=javax.management.openmbean.SimpleType(name=java.lang.Long)),(itemName=max,itemType=javax.management.openmbean.SimpleType(name=java.lang.Long)),(itemName=used,itemType=javax.management.openmbean.SimpleType(name=java.lang.Long)))),contents={committed=7012352, init=2555904, max=50331648, used=6872896})
	==>[Usage] @javax.management.openmbean.CompositeData : javax.management.openmbean.CompositeDataSupport(compositeType=javax.management.openmbean.CompositeType(name=java.lang.management.MemoryUsage,items=((itemName=committed,itemType=javax.management.openmbean.SimpleType(name=java.lang.Long)),(itemName=init,itemType=javax.management.openmbean.SimpleType(name=java.lang.Long)),(itemName=max,itemType=javax.management.openmbean.SimpleType(name=java.lang.Long)),(itemName=used,itemType=javax.management.openmbean.SimpleType(name=java.lang.Long)))),contents={committed=7012352, init=2555904, max=50331648, used=6862400})
	==>[UsageThreshold] @long : 0
	==>[UsageThresholdCount] @long : 0
	CollectionUsage threshold is not supported
	==>[CollectionUsageThresholdSupported] @boolean : false
	==>[UsageThresholdExceeded] @boolean : true
	==>[UsageThresholdSupported] @boolean : true
	ObjectName : resin:type=Port,name=127.0.0.1-6806
	==>[AcceptListenBacklog] @int : 4000
	==>[AcceptThreadMax] @int : 64
	==>[AcceptThreadMin] @int : 4
	==>[Address] @java.lang.String : 127.0.0.1
	==>[ClientDisconnectCountTotal] @long : 0
	==>[CometIdleCount] @int : 0
	==>[ConnectionMax] @int : 1048576
	==>[JniEnabled] @boolean : false
	==>[KeepaliveConnectionTimeMax] @long : 4611686018427387903
	==>[KeepaliveCount] @int : 0
	==>[KeepaliveCountTotal] @long : 0
	==>[KeepaliveMax] @int : 65536
	==>[KeepaliveSelectCount] @int : -1
	==>[KeepaliveSelectCountTotal] @long : 0
	==>[KeepaliveSelectMax] @int : -1
	==>[KeepaliveThreadCount] @int : 0
	==>[KeepaliveThreadTimeout] @long : 60000
	==>[KeepaliveTimeout] @long : 480000
	==>[Name] @java.lang.String : 127.0.0.1-6806
	==>[ObjectName] @javax.management.ObjectName : resin:type=Port,name=127.0.0.1-6806
	==>[Port] @int : 6806
	==>[PortThreadMax] @int : 8192
	==>[ProtocolName] @java.lang.String : server
	==>[ReadBytesTotal] @long : 0
	==>[RequestCountTotal] @long : 0
	==>[RequestTimeTotal] @long : 0
	==>[SSL] @boolean : false
	==>[SocketTimeout] @long : 600000
	==>[State] @java.lang.String : ACTIVE
	==>[SuspendTimeMax] @long : 600000
	==>[TcpKeepalive] @boolean : false
	==>[TcpNoDelay] @boolean : true
	==>[ThreadActiveCount] @int : 0
	==>[ThreadCount] @int : 4
	==>[ThreadIdleCount] @int : 4
	==>[ThreadStartCount] @int : 0
	==>[ThrottleDisconnectCountTotal] @long : 0
	==>[Type] @java.lang.String : Port
	==>[WriteBytesTotal] @long : 0
	ObjectName : resin:type=TransactionManager
	==>[CommitCountTotal] @long : 0
	==>[CommitResourceFailCountTotal] @long : 0
	==>[Name] @java.lang.String : null
	==>[ObjectName] @javax.management.ObjectName : resin:type=TransactionManager
	==>[RollbackCountTotal] @long : 0
	==>[TransactionCount] @int : 0
	==>[Type] @java.lang.String : TransactionManager
	ObjectName : java.lang:type=Runtime
	==>[Name] @java.lang.String : 23317@10_10_77_159
	==>[ClassPath] @java.lang.String : /opt/resin/resin-video-api4-server/bin/../lib/resin.jar:./:/usr/local/jdk/lib:/usr/local/jdk/lib/tools.jar:/usr/local/jdk/jre/lib/ext/*.jar:/usr/local/jdk1.6.0_29/lib/tools.jar:/opt/resin/resin-video-api4-server/lib/resin.jar:/opt/resin/resin-video-api4-server/lib/javamail-141.jar:/opt/resin/resin-video-api4-server/lib/activation.jar:/opt/resin/resin-video-api4-server/lib/webutil.jar:/opt/resin/resin-video-api4-server/lib/resin-eclipselink.jar:/opt/resin/resin-video-api4-server/lib/javaee-16.jar:/opt/resin/resin-video-api4-server/lib/eclipselink-2.4.0.jar:/opt/resin/resin-video-api4-server/lib/jboss-logging-3.1.0.CR2.jar:/opt/resin/resin-video-api4-server/lib/validation-api-1.0.0.GA.jar:/opt/resin/resin-video-api4-server/lib/webservices-extra-api.jar:/opt/resin/resin-video-api4-server/lib/hibernate-validator-4.3.0.Final.jar:/opt/resin/resin-video-api4-server/lib/javax.faces-2.1.15.jar
	==>[BootClassPath] @java.lang.String : /usr/local/jdk1.6.0_29/jre/lib/resources.jar:/usr/local/jdk1.6.0_29/jre/lib/rt.jar:/usr/local/jdk1.6.0_29/jre/lib/sunrsasign.jar:/usr/local/jdk1.6.0_29/jre/lib/jsse.jar:/usr/local/jdk1.6.0_29/jre/lib/jce.jar:/usr/local/jdk1.6.0_29/jre/lib/charsets.jar:/usr/local/jdk1.6.0_29/jre/lib/modules/jdk.boot.jar:/usr/local/jdk1.6.0_29/jre/classes
	==>[LibraryPath] @java.lang.String : /usr/local/jdk1.6.0_29/jre/lib/amd64/server:/usr/local/jdk1.6.0_29/jre/lib/amd64:/usr/local/jdk1.6.0_29/jre/../lib/amd64:/opt/resin/resin-video-api4-server/libexec64:/usr/local/jdk1.6.0_29/jre/lib/amd64/server:/usr/local/jdk1.6.0_29/jre/lib/amd64:/usr/local/jdk1.6.0_29/jre/../lib/amd64:/opt/resin/resin-video-api4-server/libexec64:/usr/local/jdk1.6.0_29/jre/lib/amd64/server:/usr/local/jdk1.6.0_29/jre/lib/amd64:/usr/local/jdk1.6.0_29/jre/../lib/amd64:/usr/java/packages/lib/amd64:/usr/lib64:/lib64:/lib:/usr/lib
	==>[VmName] @java.lang.String : Java HotSpot(TM) 64-Bit Server VM
	==>[VmVendor] @java.lang.String : Sun Microsystems Inc.
	==>[VmVersion] @java.lang.String : 20.4-b02
	==>[BootClassPathSupported] @boolean : true
	==>[InputArguments] @[Ljava.lang.String; : [Ljava.lang.String;@3d358f03
	==>[ManagementSpecVersion] @java.lang.String : 1.2
	==>[SpecName] @java.lang.String : Java Virtual Machine Specification
	==>[SpecVendor] @java.lang.String : Sun Microsystems Inc.
	==>[SpecVersion] @java.lang.String : 1.0
	==>[StartTime] @long : 1408698841032
	==>[SystemProperties] @javax.management.openmbean.TabularData : javax.management.openmbean.TabularDataSupport(tabularType=javax.management.openmbean.TabularType(name=java.util.Map<java.lang.String, java.lang.String>,rowType=javax.management.openmbean.CompositeType(name=java.util.Map<java.lang.String, java.lang.String>,items=((itemName=key,itemType=javax.management.openmbean.SimpleType(name=java.lang.String)),(itemName=value,itemType=javax.management.openmbean.SimpleType(name=java.lang.String)))),indexNames=(key)),contents={[java.ext.dirs]=javax.management.openmbean.CompositeDataSupport(compositeType=javax.management.openmbean.CompositeType(name=java.util.Map<java.lang.String, java.lang.String>,items=((itemName=key,itemType=javax.management.openmbean.SimpleType(name=java.lang.String)),(itemName=value,itemType=javax.management.openmbean.SimpleType(name=java.lang.String)))),contents={key=java.ext.dirs, value=/usr/local/jdk1.6.0_29/jre/lib/ext:/usr/java/packages/lib/ext}), [java.vm.specification.vendor]=javax.management.openmbean.CompositeDataSupport(compositeType=javax.management.openmbean.CompositeType(name=java.util.Map<java.lang.String, java.lang.String>,items=((itemName=key,itemType=javax.management.openmbean.SimpleType(name=java.lang.String)),(itemName=value,itemType=javax.management.openmbean.SimpleType(name=java.lang.String)))),contents={key=java.vm.specification.vendor, value=Sun Microsystems Inc.}), [user.timezone]=javax.management.openmbean.CompositeDataSupport(compositeType=javax.management.openmbean.CompositeType(name=java.util.Map<java.lang.String, java.lang.String>,items=((itemName=key,itemType=javax.management.openmbean.SimpleType(name=java.lang.String)),(itemName=value,itemType=javax.management.openmbean.SimpleType(name=java.lang.String)))),contents={key=user.timezone, value=}), [com.sun.management.jmxremote.port]=javax.management.openmbean.CompositeDataSupport(compositeType=javax.management.openmbean.CompositeType(name=java.util.Map<java.lang.String, java.lang.String>,items=((itemName=key,itemType=javax.management.openmbean.SimpleType(name=java.lang.String)),(itemName=value,itemType=javax.management.openmbean.SimpleType(name=java.lang.String)))),contents={key=com.sun.management.jmxremote.port, value=8996}), [java.vm.vendor]=javax.management.openmbean.CompositeDataSupport(compositeType=javax.management.openmbean.CompositeType(name=java.util.Map<java.lang.String, java.lang.String>,items=((itemName=key,itemType=javax.management.openmbean.SimpleType(name=java.lang.String)),(itemName=value,itemType=javax.management.openmbean.SimpleType(name=java.lang.String)))),contents={key=java.vm.vendor, value=Sun Microsystems Inc.}), [user.name]=javax.management.openmbean.CompositeDataSupport(compositeType=javax.management.openmbean.CompositeType(name=java.util.Map<java.lang.String, java.lang.String>,items=((itemName=key,itemType=javax.management.openmbean.SimpleType(name=java.lang.String)),(itemName=value,itemType=javax.management.openmbean.SimpleType(name=java.lang.String)))),contents={key=user.name, value=root}), [user.dir]=javax.management.openmbean.CompositeDataSupport(compositeType=javax.management.openmbean.CompositeType(name=java.util.Map<java.lang.String, java.lang.String>,items=((itemName=key,itemType=javax.management.openmbean.SimpleType(name=java.lang.String)),(itemName=value,itemType=javax.management.openmbean.SimpleType(name=java.lang.String)))),contents={key=user.dir, value=/opt/resin/resin-video-api4-server}), [java.vm.specification.name]=javax.management.openmbean.CompositeDataSupport(compositeType=javax.management.openmbean.CompositeType(name=java.util.Map<java.lang.String, java.lang.String>,items=((itemName=key,itemType=javax.management.openmbean.SimpleType(name=java.lang.String)),(itemName=value,itemType=javax.management.openmbean.SimpleType(name=java.lang.String)))),contents={key=java.vm.specification.name, value=Java Virtual Machine Specification}), [user.language]=javax.management.openmbean.CompositeDataSupport(compositeType=javax.management.openmbean.CompositeType(name=java.util.Map<java.lang.String, java.lang.String>,items=((itemName=key,itemType=javax.management.openmbean.SimpleType(name=java.lang.String)),(itemName=value,itemType=javax.management.openmbean.SimpleType(name=java.lang.String)))),contents={key=user.language, value=zh}), [user.country]=javax.management.openmbean.CompositeDataSupport(compositeType=javax.management.openmbean.CompositeType(name=java.util.Map<java.lang.String, java.lang.String>,items=((itemName=key,itemType=javax.management.openmbean.SimpleType(name=java.lang.String)),(itemName=value,itemType=javax.management.openmbean.SimpleType(name=java.lang.String)))),contents={key=user.country, value=CN}), [resin.server]=javax.management.openmbean.CompositeDataSupport(compositeType=javax.management.openmbean.CompositeType(name=java.util.Map<java.lang.String, java.lang.String>,items=((itemName=key,itemType=javax.management.openmbean.SimpleType(name=java.lang.String)),(itemName=value,itemType=javax.management.openmbean.SimpleType(name=java.lang.String)))),contents={key=resin.server, value=video-api4-server}), [java.specification.version]=javax.management.openmbean.CompositeDataSupport(compositeType=javax.management.openmbean.CompositeType(name=java.util.Map<java.lang.String, java.lang.String>,items=((itemName=key,itemType=javax.management.openmbean.SimpleType(name=java.lang.String)),(itemName=value,itemType=javax.management.openmbean.SimpleType(name=java.lang.String)))),contents={key=java.specification.version, value=1.6}), [sun.cpu.endian]=javax.management.openmbean.CompositeDataSupport(compositeType=javax.management.openmbean.CompositeType(name=java.util.Map<java.lang.String, java.lang.String>,items=((itemName=key,itemType=javax.management.openmbean.SimpleType(name=java.lang.String)),(itemName=value,itemType=javax.management.openmbean.SimpleType(name=java.lang.String)))),contents={key=sun.cpu.endian, value=little}), [java.home]=javax.management.openmbean.CompositeDataSupport(compositeType=javax.management.openmbean.CompositeType(name=java.util.Map<java.lang.String, java.lang.String>,items=((itemName=key,itemType=javax.management.openmbean.SimpleType(name=java.lang.String)),(itemName=value,itemType=javax.management.openmbean.SimpleType(name=java.lang.String)))),contents={key=java.home, value=/usr/local/jdk1.6.0_29/jre}), [resin.home]=javax.management.openmbean.CompositeDataSupport(compositeType=javax.management.openmbean.CompositeType(name=java.util.Map<java.lang.String, java.lang.String>,items=((itemName=key,itemType=javax.management.openmbean.SimpleType(name=java.lang.String)),(itemName=value,itemType=javax.management.openmbean.SimpleType(name=java.lang.String)))),contents={key=resin.home, value=/opt/resin/resin-video-api4-server/}), [sun.jnu.encoding]=javax.management.openmbean.CompositeDataSupport(compositeType=javax.management.openmbean.CompositeType(name=java.util.Map<java.lang.String, java.lang.String>,items=((itemName=key,itemType=javax.management.openmbean.SimpleType(name=java.lang.String)),(itemName=value,itemType=javax.management.openmbean.SimpleType(name=java.lang.String)))),contents={key=sun.jnu.encoding, value=UTF-8}), [file.separator]=javax.management.openmbean.CompositeDataSupport(compositeType=javax.management.openmbean.CompositeType(name=java.util.Map<java.lang.String, java.lang.String>,items=((itemName=key,itemType=javax.management.openmbean.SimpleType(name=java.lang.String)),(itemName=value,itemType=javax.management.openmbean.SimpleType(name=java.lang.String)))),contents={key=file.separator, value=/}), [java.vendor.url]=javax.management.openmbean.CompositeDataSupport(compositeType=javax.management.openmbean.CompositeType(name=java.util.Map<java.lang.String, java.lang.String>,items=((itemName=key,itemType=javax.management.openmbean.SimpleType(name=java.lang.String)),(itemName=value,itemType=javax.management.openmbean.SimpleType(name=java.lang.String)))),contents={key=java.vendor.url, value=http://java.sun.com/}), [java.awt.graphicsenv]=javax.management.openmbean.CompositeDataSupport(compositeType=javax.management.openmbean.CompositeType(name=java.util.Map<java.lang.String, java.lang.String>,items=((itemName=key,itemType=javax.management.openmbean.SimpleType(name=java.lang.String)),(itemName=value,itemType=javax.management.openmbean.SimpleType(name=java.lang.String)))),contents={key=java.awt.graphicsenv, value=sun.awt.X11GraphicsEnvironment}), [os.arch]=javax.management.openmbean.CompositeDataSupport(compositeType=javax.management.openmbean.CompositeType(name=java.util.Map<java.lang.String, java.lang.String>,items=((itemName=key,itemType=javax.management.openmbean.SimpleType(name=java.lang.String)),(itemName=value,itemType=javax.management.openmbean.SimpleType(name=java.lang.String)))),contents={key=os.arch, value=amd64}), [com.sun.management.jmxremote.authenticate]=javax.management.openmbean.CompositeDataSupport(compositeType=javax.management.openmbean.CompositeType(name=java.util.Map<java.lang.String, java.lang.String>,items=((itemName=key,itemType=javax.management.openmbean.SimpleType(name=java.lang.String)),(itemName=value,itemType=javax.management.openmbean.SimpleType(name=java.lang.String)))),contents={key=com.sun.management.jmxremote.authenticate, value=false}), [sun.rmi.transport.tcp.responseTimeout]=javax.management.openmbean.CompositeDataSupport(compositeType=javax.management.openmbean.CompositeType(name=java.util.Map<java.lang.String, java.lang.String>,items=((itemName=key,itemType=javax.management.openmbean.SimpleType(name=java.lang.String)),(itemName=value,itemType=javax.management.openmbean.SimpleType(name=java.lang.String)))),contents={key=sun.rmi.transport.tcp.responseTimeout, value=2000}), [java.io.tmpdir]=javax.management.openmbean.CompositeDataSupport(compositeType=javax.management.openmbean.CompositeType(name=java.util.Map<java.lang.String, java.lang.String>,items=((itemName=key,itemType=javax.management.openmbean.SimpleType(name=java.lang.String)),(itemName=value,itemType=javax.management.openmbean.SimpleType(name=java.lang.String)))),contents={key=java.io.tmpdir, value=/tmp}), [java.runtime.name]=javax.management.openmbean.CompositeDataSupport(compositeType=javax.management.openmbean.CompositeType(name=java.util.Map<java.lang.String, java.lang.String>,items=((itemName=key,itemType=javax.management.openmbean.SimpleType(name=java.lang.String)),(itemName=value,itemType=javax.management.openmbean.SimpleType(name=java.lang.String)))),contents={key=java.runtime.name, value=Java(TM) SE Runtime Environment}), [java.awt.printerjob]=javax.management.openmbean.CompositeDataSupport(compositeType=javax.management.openmbean.CompositeType(name=java.util.Map<java.lang.String, java.lang.String>,items=((itemName=key,itemType=javax.management.openmbean.SimpleType(name=java.lang.String)),(itemName=value,itemType=javax.management.openmbean.SimpleType(name=java.lang.String)))),contents={key=java.awt.printerjob, value=sun.print.PSPrinterJob}), [file.encoding]=javax.management.openmbean.CompositeDataSupport(compositeType=javax.management.openmbean.CompositeType(name=java.util.Map<java.lang.String, java.lang.String>,items=((itemName=key,itemType=javax.management.openmbean.SimpleType(name=java.lang.String)),(itemName=value,itemType=javax.management.openmbean.SimpleType(name=java.lang.String)))),contents={key=file.encoding, value=UTF-8}), [java.version]=javax.management.openmbean.CompositeDataSupport(compositeType=javax.management.openmbean.CompositeType(name=java.util.Map<java.lang.String, java.lang.String>,items=((itemName=key,itemType=javax.management.openmbean.SimpleType(name=java.lang.String)),(itemName=value,itemType=javax.management.openmbean.SimpleType(name=java.lang.String)))),contents={key=java.version, value=1.6.0_29}), [java.vendor.url.bug]=javax.management.openmbean.CompositeDataSupport(compositeType=javax.management.openmbean.CompositeType(name=java.util.Map<java.lang.String, java.lang.String>,items=((itemName=key,itemType=javax.management.openmbean.SimpleType(name=java.lang.String)),(itemName=value,itemType=javax.management.openmbean.SimpleType(name=java.lang.String)))),contents={key=java.vendor.url.bug, value=http://java.sun.com/cgi-bin/bugreport.cgi}), [java.vm.specification.version]=javax.management.openmbean.CompositeDataSupport(compositeType=javax.management.openmbean.CompositeType(name=java.util.Map<java.lang.String, java.lang.String>,items=((itemName=key,itemType=javax.management.openmbean.SimpleType(name=java.lang.String)),(itemName=value,itemType=javax.management.openmbean.SimpleType(name=java.lang.String)))),contents={key=java.vm.specification.version, value=1.0}), [file.encoding.pkg]=javax.management.openmbean.CompositeDataSupport(compositeType=javax.management.openmbean.CompositeType(name=java.util.Map<java.lang.String, java.lang.String>,items=((itemName=key,itemType=javax.management.openmbean.SimpleType(name=java.lang.String)),(itemName=value,itemType=javax.management.openmbean.SimpleType(name=java.lang.String)))),contents={key=file.encoding.pkg, value=sun.io}), [sun.java.command]=javax.management.openmbean.CompositeDataSupport(compositeType=javax.management.openmbean.CompositeType(name=java.util.Map<java.lang.String, java.lang.String>,items=((itemName=key,itemType=javax.management.openmbean.SimpleType(name=java.lang.String)),(itemName=value,itemType=javax.management.openmbean.SimpleType(name=java.lang.String)))),contents={key=sun.java.command, value=com.caucho.server.resin.Resin --root-directory /opt/resin/resin-video-api4-server/ -conf /opt/resin/resin-video-api4-server/conf/video-api4-server.xml -server video-api4-server -socketwait 32723 -server video-api4-server start --log-directory /opt/resin/resin-video-api4-server/log}), [java.util.logging.manager]=javax.management.openmbean.CompositeDataSupport(compositeType=javax.management.openmbean.CompositeType(name=java.util.Map<java.lang.String, java.lang.String>,items=((itemName=key,itemType=javax.management.openmbean.SimpleType(name=java.lang.String)),(itemName=value,itemType=javax.management.openmbean.SimpleType(name=java.lang.String)))),contents={key=java.util.logging.manager, value=com.caucho.log.LogManagerImpl}), [sun.java.launcher]=javax.management.openmbean.CompositeDataSupport(compositeType=javax.management.openmbean.CompositeType(name=java.util.Map<java.lang.String, java.lang.String>,items=((itemName=key,itemType=javax.management.openmbean.SimpleType(name=java.lang.String)),(itemName=value,itemType=javax.management.openmbean.SimpleType(name=java.lang.String)))),contents={key=sun.java.launcher, value=SUN_STANDARD}), [java.class.path]=javax.management.openmbean.CompositeDataSupport(compositeType=javax.management.openmbean.CompositeType(name=java.util.Map<java.lang.String, java.lang.String>,items=((itemName=key,itemType=javax.management.openmbean.SimpleType(name=java.lang.String)),(itemName=value,itemType=javax.management.openmbean.SimpleType(name=java.lang.String)))),contents={key=java.class.path, value=/opt/resin/resin-video-api4-server/bin/../lib/resin.jar:./:/usr/local/jdk/lib:/usr/local/jdk/lib/tools.jar:/usr/local/jdk/jre/lib/ext/*.jar:/usr/local/jdk1.6.0_29/lib/tools.jar:/opt/resin/resin-video-api4-server/lib/resin.jar:/opt/resin/resin-video-api4-server/lib/javamail-141.jar:/opt/resin/resin-video-api4-server/lib/activation.jar:/opt/resin/resin-video-api4-server/lib/webutil.jar:/opt/resin/resin-video-api4-server/lib/resin-eclipselink.jar:/opt/resin/resin-video-api4-server/lib/javaee-16.jar:/opt/resin/resin-video-api4-server/lib/eclipselink-2.4.0.jar:/opt/resin/resin-video-api4-server/lib/jboss-logging-3.1.0.CR2.jar:/opt/resin/resin-video-api4-server/lib/validation-api-1.0.0.GA.jar:/opt/resin/resin-video-api4-server/lib/webservices-extra-api.jar:/opt/resin/resin-video-api4-server/lib/hibernate-validator-4.3.0.Final.jar:/opt/resin/resin-video-api4-server/lib/javax.faces-2.1.15.jar}), [java.runtime.version]=javax.management.openmbean.CompositeDataSupport(compositeType=javax.management.openmbean.CompositeType(name=java.util.Map<java.lang.String, java.lang.String>,items=((itemName=key,itemType=javax.management.openmbean.SimpleType(name=java.lang.String)),(itemName=value,itemType=javax.management.openmbean.SimpleType(name=java.lang.String)))),contents={key=java.runtime.version, value=1.6.0_29-b11}), [path.separator]=javax.management.openmbean.CompositeDataSupport(compositeType=javax.management.openmbean.CompositeType(name=java.util.Map<java.lang.String, java.lang.String>,items=((itemName=key,itemType=javax.management.openmbean.SimpleType(name=java.lang.String)),(itemName=value,itemType=javax.management.openmbean.SimpleType(name=java.lang.String)))),contents={key=path.separator, value=:}), [com.sun.management.jmxremote.ssl]=javax.management.openmbean.CompositeDataSupport(compositeType=javax.management.openmbean.CompositeType(name=java.util.Map<java.lang.String, java.lang.String>,items=((itemName=key,itemType=javax.management.openmbean.SimpleType(name=java.lang.String)),(itemName=value,itemType=javax.management.openmbean.SimpleType(name=java.lang.String)))),contents={key=com.sun.management.jmxremote.ssl, value=false}), [os.name]=javax.management.openmbean.CompositeDataSupport(compositeType=javax.management.openmbean.CompositeType(name=java.util.Map<java.lang.String, java.lang.String>,items=((itemName=key,itemType=javax.management.openmbean.SimpleType(name=java.lang.String)),(itemName=value,itemType=javax.management.openmbean.SimpleType(name=java.lang.String)))),contents={key=os.name, value=Linux}), [java.system.class.loader]=javax.management.openmbean.CompositeDataSupport(compositeType=javax.management.openmbean.CompositeType(name=java.util.Map<java.lang.String, java.lang.String>,items=((itemName=key,itemType=javax.management.openmbean.SimpleType(name=java.lang.String)),(itemName=value,itemType=javax.management.openmbean.SimpleType(name=java.lang.String)))),contents={key=java.system.class.loader, value=com.caucho.loader.SystemClassLoader}), [line.separator]=javax.management.openmbean.CompositeDataSupport(compositeType=javax.management.openmbean.CompositeType(name=java.util.Map<java.lang.String, java.lang.String>,items=((itemName=key,itemType=javax.management.openmbean.SimpleType(name=java.lang.String)),(itemName=value,itemType=javax.management.openmbean.SimpleType(name=java.lang.String)))),contents={key=line.separator, value=
	}), [os.version]=javax.management.openmbean.CompositeDataSupport(compositeType=javax.management.openmbean.CompositeType(name=java.util.Map<java.lang.String, java.lang.String>,items=((itemName=key,itemType=javax.management.openmbean.SimpleType(name=java.lang.String)),(itemName=value,itemType=javax.management.openmbean.SimpleType(name=java.lang.String)))),contents={key=os.version, value=2.6.18-274.el5}), [com.sun.management.jmxremote]=javax.management.openmbean.CompositeDataSupport(compositeType=javax.management.openmbean.CompositeType(name=java.util.Map<java.lang.String, java.lang.String>,items=((itemName=key,itemType=javax.management.openmbean.SimpleType(name=java.lang.String)),(itemName=value,itemType=javax.management.openmbean.SimpleType(name=java.lang.String)))),contents={key=com.sun.management.jmxremote, value=}), [sun.arch.data.model]=javax.management.openmbean.CompositeDataSupport(compositeType=javax.management.openmbean.CompositeType(name=java.util.Map<java.lang.String, java.lang.String>,items=((itemName=key,itemType=javax.management.openmbean.SimpleType(name=java.lang.String)),(itemName=value,itemType=javax.management.openmbean.SimpleType(name=java.lang.String)))),contents={key=sun.arch.data.model, value=64}), [sun.rmi.transport.tcp.handshakeTimeout]=javax.management.openmbean.CompositeDataSupport(compositeType=javax.management.openmbean.CompositeType(name=java.util.Map<java.lang.String, java.lang.String>,items=((itemName=key,itemType=javax.management.openmbean.SimpleType(name=java.lang.String)),(itemName=value,itemType=javax.management.openmbean.SimpleType(name=java.lang.String)))),contents={key=sun.rmi.transport.tcp.handshakeTimeout, value=2000}), [java.class.version]=javax.management.openmbean.CompositeDataSupport(compositeType=javax.management.openmbean.CompositeType(name=java.util.Map<java.lang.String, java.lang.String>,items=((itemName=key,itemType=javax.management.openmbean.SimpleType(name=java.lang.String)),(itemName=value,itemType=javax.management.openmbean.SimpleType(name=java.lang.String)))),contents={key=java.class.version, value=50.0}), [java.rmi.server.hostname]=javax.management.openmbean.CompositeDataSupport(compositeType=javax.management.openmbean.CompositeType(name=java.util.Map<java.lang.String, java.lang.String>,items=((itemName=key,itemType=javax.management.openmbean.SimpleType(name=java.lang.String)),(itemName=value,itemType=javax.management.openmbean.SimpleType(name=java.lang.String)))),contents={key=java.rmi.server.hostname, value=179.30.77.159}), [java.naming.factory.url.pkgs]=javax.management.openmbean.CompositeDataSupport(compositeType=javax.management.openmbean.CompositeType(name=java.util.Map<java.lang.String, java.lang.String>,items=((itemName=key,itemType=javax.management.openmbean.SimpleType(name=java.lang.String)),(itemName=value,itemType=javax.management.openmbean.SimpleType(name=java.lang.String)))),contents={key=java.naming.factory.url.pkgs, value=com.caucho.naming}), [sun.io.unicode.encoding]=javax.management.openmbean.CompositeDataSupport(compositeType=javax.management.openmbean.CompositeType(name=java.util.Map<java.lang.String, java.lang.String>,items=((itemName=key,itemType=javax.management.openmbean.SimpleType(name=java.lang.String)),(itemName=value,itemType=javax.management.openmbean.SimpleType(name=java.lang.String)))),contents={key=sun.io.unicode.encoding, value=UnicodeLittle}), [sun.boot.class.path]=javax.management.openmbean.CompositeDataSupport(compositeType=javax.management.openmbean.CompositeType(name=java.util.Map<java.lang.String, java.lang.String>,items=((itemName=key,itemType=javax.management.openmbean.SimpleType(name=java.lang.String)),(itemName=value,itemType=javax.management.openmbean.SimpleType(name=java.lang.String)))),contents={key=sun.boot.class.path, value=/usr/local/jdk1.6.0_29/jre/lib/resources.jar:/usr/local/jdk1.6.0_29/jre/lib/rt.jar:/usr/local/jdk1.6.0_29/jre/lib/sunrsasign.jar:/usr/local/jdk1.6.0_29/jre/lib/jsse.jar:/usr/local/jdk1.6.0_29/jre/lib/jce.jar:/usr/local/jdk1.6.0_29/jre/lib/charsets.jar:/usr/local/jdk1.6.0_29/jre/lib/modules/jdk.boot.jar:/usr/local/jdk1.6.0_29/jre/classes}), [java.vendor]=javax.management.openmbean.CompositeDataSupport(compositeType=javax.management.openmbean.CompositeType(name=java.util.Map<java.lang.String, java.lang.String>,items=((itemName=key,itemType=javax.management.openmbean.SimpleType(name=java.lang.String)),(itemName=value,itemType=javax.management.openmbean.SimpleType(name=java.lang.String)))),contents={key=java.vendor, value=Sun Microsystems Inc.}), [java.awt.headless]=javax.management.openmbean.CompositeDataSupport(compositeType=javax.management.openmbean.CompositeType(name=java.util.Map<java.lang.String, java.lang.String>,items=((itemName=key,itemType=javax.management.openmbean.SimpleType(name=java.lang.String)),(itemName=value,itemType=javax.management.openmbean.SimpleType(name=java.lang.String)))),contents={key=java.awt.headless, value=true}), [java.vm.info]=javax.management.openmbean.CompositeDataSupport(compositeType=javax.management.openmbean.CompositeType(name=java.util.Map<java.lang.String, java.lang.String>,items=((itemName=key,itemType=javax.management.openmbean.SimpleType(name=java.lang.String)),(itemName=value,itemType=javax.management.openmbean.SimpleType(name=java.lang.String)))),contents={key=java.vm.info, value=mixed mode}), [java.specification.name]=javax.management.openmbean.CompositeDataSupport(compositeType=javax.management.openmbean.CompositeType(name=java.util.Map<java.lang.String, java.lang.String>,items=((itemName=key,itemType=javax.management.openmbean.SimpleType(name=java.lang.String)),(itemName=value,itemType=javax.management.openmbean.SimpleType(name=java.lang.String)))),contents={key=java.specification.name, value=Java Platform API Specification}), [javax.management.builder.initial]=javax.management.openmbean.CompositeDataSupport(compositeType=javax.management.openmbean.CompositeType(name=java.util.Map<java.lang.String, java.lang.String>,items=((itemName=key,itemType=javax.management.openmbean.SimpleType(name=java.lang.String)),(itemName=value,itemType=javax.management.openmbean.SimpleType(name=java.lang.String)))),contents={key=javax.management.builder.initial, value=com.caucho.jmx.MBeanServerBuilderImpl}), [java.vm.version]=javax.management.openmbean.CompositeDataSupport(compositeType=javax.management.openmbean.CompositeType(name=java.util.Map<java.lang.String, java.lang.String>,items=((itemName=key,itemType=javax.management.openmbean.SimpleType(name=java.lang.String)),(itemName=value,itemType=javax.management.openmbean.SimpleType(name=java.lang.String)))),contents={key=java.vm.version, value=20.4-b02}), [java.vm.name]=javax.management.openmbean.CompositeDataSupport(compositeType=javax.management.openmbean.CompositeType(name=java.util.Map<java.lang.String, java.lang.String>,items=((itemName=key,itemType=javax.management.openmbean.SimpleType(name=java.lang.String)),(itemName=value,itemType=javax.management.openmbean.SimpleType(name=java.lang.String)))),contents={key=java.vm.name, value=Java HotSpot(TM) 64-Bit Server VM}), [sun.boot.library.path]=javax.management.openmbean.CompositeDataSupport(compositeType=javax.management.openmbean.CompositeType(name=java.util.Map<java.lang.String, java.lang.String>,items=((itemName=key,itemType=javax.management.openmbean.SimpleType(name=java.lang.String)),(itemName=value,itemType=javax.management.openmbean.SimpleType(name=java.lang.String)))),contents={key=sun.boot.library.path, value=/usr/local/jdk1.6.0_29/jre/lib/amd64}), [sun.cpu.isalist]=javax.management.openmbean.CompositeDataSupport(compositeType=javax.management.openmbean.CompositeType(name=java.util.Map<java.lang.String, java.lang.String>,items=((itemName=key,itemType=javax.management.openmbean.SimpleType(name=java.lang.String)),(itemName=value,itemType=javax.management.openmbean.SimpleType(name=java.lang.String)))),contents={key=sun.cpu.isalist, value=}), [sun.os.patch.level]=javax.management.openmbean.CompositeDataSupport(compositeType=javax.management.openmbean.CompositeType(name=java.util.Map<java.lang.String, java.lang.String>,items=((itemName=key,itemType=javax.management.openmbean.SimpleType(name=java.lang.String)),(itemName=value,itemType=javax.management.openmbean.SimpleType(name=java.lang.String)))),contents={key=sun.os.patch.level, value=unknown}), [java.endorsed.dirs]=javax.management.openmbean.CompositeDataSupport(compositeType=javax.management.openmbean.CompositeType(name=java.util.Map<java.lang.String, java.lang.String>,items=((itemName=key,itemType=javax.management.openmbean.SimpleType(name=java.lang.String)),(itemName=value,itemType=javax.management.openmbean.SimpleType(name=java.lang.String)))),contents={key=java.endorsed.dirs, value=/usr/local/jdk1.6.0_29/jre/lib/endorsed:/opt/resin/resin-video-api4-server//endorsed:/opt/resin/resin-video-api4-server//endorsed}), [sun.rmi.transport.proxy.connectTimeout]=javax.management.openmbean.CompositeDataSupport(compositeType=javax.management.openmbean.CompositeType(name=java.util.Map<java.lang.String, java.lang.String>,items=((itemName=key,itemType=javax.management.openmbean.SimpleType(name=java.lang.String)),(itemName=value,itemType=javax.management.openmbean.SimpleType(name=java.lang.String)))),contents={key=sun.rmi.transport.proxy.connectTimeout, value=2000}), [java.naming.factory.initial]=javax.management.openmbean.CompositeDataSupport(compositeType=javax.management.openmbean.CompositeType(name=java.util.Map<java.lang.String, java.lang.String>,items=((itemName=key,itemType=javax.management.openmbean.SimpleType(name=java.lang.String)),(itemName=value,itemType=javax.management.openmbean.SimpleType(name=java.lang.String)))),contents={key=java.naming.factory.initial, value=com.caucho.naming.InitialContextFactoryImpl}), [user.home]=javax.management.openmbean.CompositeDataSupport(compositeType=javax.management.openmbean.CompositeType(name=java.util.Map<java.lang.String, java.lang.String>,items=((itemName=key,itemType=javax.management.openmbean.SimpleType(name=java.lang.String)),(itemName=value,itemType=javax.management.openmbean.SimpleType(name=java.lang.String)))),contents={key=user.home, value=/root}), [java.library.path]=javax.management.openmbean.CompositeDataSupport(compositeType=javax.management.openmbean.CompositeType(name=java.util.Map<java.lang.String, java.lang.String>,items=((itemName=key,itemType=javax.management.openmbean.SimpleType(name=java.lang.String)),(itemName=value,itemType=javax.management.openmbean.SimpleType(name=java.lang.String)))),contents={key=java.library.path, value=/usr/local/jdk1.6.0_29/jre/lib/amd64/server:/usr/local/jdk1.6.0_29/jre/lib/amd64:/usr/local/jdk1.6.0_29/jre/../lib/amd64:/opt/resin/resin-video-api4-server/libexec64:/usr/local/jdk1.6.0_29/jre/lib/amd64/server:/usr/local/jdk1.6.0_29/jre/lib/amd64:/usr/local/jdk1.6.0_29/jre/../lib/amd64:/opt/resin/resin-video-api4-server/libexec64:/usr/local/jdk1.6.0_29/jre/lib/amd64/server:/usr/local/jdk1.6.0_29/jre/lib/amd64:/usr/local/jdk1.6.0_29/jre/../lib/amd64:/usr/java/packages/lib/amd64:/usr/lib64:/lib64:/lib:/usr/lib}), [sun.management.compiler]=javax.management.openmbean.CompositeDataSupport(compositeType=javax.management.openmbean.CompositeType(name=java.util.Map<java.lang.String, java.lang.String>,items=((itemName=key,itemType=javax.management.openmbean.SimpleType(name=java.lang.String)),(itemName=value,itemType=javax.management.openmbean.SimpleType(name=java.lang.String)))),contents={key=sun.management.compiler, value=HotSpot 64-Bit Tiered Compilers}), [java.specification.vendor]=javax.management.openmbean.CompositeDataSupport(compositeType=javax.management.openmbean.CompositeType(name=java.util.Map<java.lang.String, java.lang.String>,items=((itemName=key,itemType=javax.management.openmbean.SimpleType(name=java.lang.String)),(itemName=value,itemType=javax.management.openmbean.SimpleType(name=java.lang.String)))),contents={key=java.specification.vendor, value=Sun Microsystems Inc.}), [java.rmi.server.randomIDs]=javax.management.openmbean.CompositeDataSupport(compositeType=javax.management.openmbean.CompositeType(name=java.util.Map<java.lang.String, java.lang.String>,items=((itemName=key,itemType=javax.management.openmbean.SimpleType(name=java.lang.String)),(itemName=value,itemType=javax.management.openmbean.SimpleType(name=java.lang.String)))),contents={key=java.rmi.server.randomIDs, value=true})})
	==>[Uptime] @long : 433876622
	ObjectName : resin:type=CacheStore
	==>[DataCount] @long : 0
	==>[LoadCountTotal] @long : 0
	==>[LoadFailCountTotal] @long : 0
	==>[MnodeCount] @long : 0
	==>[Name] @java.lang.String : null
	==>[ObjectCount] @long : 0
	==>[ObjectName] @javax.management.ObjectName : resin:type=CacheStore
	==>[SaveCountTotal] @long : 0
	==>[SaveFailCountTotal] @long : 0
	ObjectName : com.mchange.v2.c3p0:type=C3P0Registry
	==>[Type] @java.lang.String : CacheStore
	==>[AllIdentityTokenCount] @int : 9
	log4j:WARN No appenders could be found for logger (com.mchange.v2.log.MLog).
	log4j:WARN Please initialize the log4j system properly.
	log4j:WARN See http://logging.apache.org/log4j/1.2/faq.html#noconfig for more info.
	==>[AllIdentityTokenized] @java.util.Set : [com.mchange.v2.c3p0.WrapperConnectionPoolDataSource@d5a3f4da [ acquireIncrement -> 1, acquireRetryAttempts -> 30, acquireRetryDelay -> 1000, autoCommitOnClose -> false, automaticTestTable -> null, breakAfterAcquireFailure -> false, checkoutTimeout -> 0, connectionCustomizerClassName -> null, connectionTesterClassName -> com.mchange.v2.c3p0.impl.DefaultConnectionTester, debugUnreturnedConnectionStackTraces -> false, factoryClassLocation -> null, forceIgnoreUnresolvedTransactions -> false, identityToken -> 2saeu7931y73aicgvk8cr|5822e90c, idleConnectionTestPeriod -> 900, initialPoolSize -> 5, maxAdministrativeTaskTime -> 0, maxConnectionAge -> 0, maxIdleTime -> 600000, maxIdleTimeExcessConnections -> 0, maxPoolSize -> 10, maxStatements -> 100, maxStatementsPerConnection -> 0, minPoolSize -> 5, nestedDataSource -> com.mchange.v2.c3p0.DriverManagerDataSource@8c1f5951 [ description -> null, driverClass -> com.mysql.jdbc.Driver, factoryClassLocation -> null, identityToken -> 2saeu7931y73aicgvk8cr|3d32e42f, jdbcUrl -> jdbc:mysql://vrs.db.tv.sohu.com:3306/vrs, properties -> {password=******, user=******} ], preferredTestQuery -> null, propertyCycle -> 0, testConnectionOnCheckin -> false, testConnectionOnCheckout -> false, unreturnedConnectionTimeout -> 0, usesTraditionalReflectiveProxies -> false; userOverrides: {} ], com.mchange.v2.c3p0.DriverManagerDataSource@8c1f5951 [ description -> null, driverClass -> com.mysql.jdbc.Driver, factoryClassLocation -> null, identityToken -> 2saeu7931y73aicgvk8cr|3d32e42f, jdbcUrl -> jdbc:mysql://vrs.db.tv.sohu.com:3306/vrs, properties -> {password=******, user=******} ], com.mchange.v2.c3p0.DriverManagerDataSource@85a8a48a [ description -> null, driverClass -> com.mysql.jdbc.Driver, factoryClassLocation -> null, identityToken -> 2saeu7931y73aicgvk8cr|1c8aeedc, jdbcUrl -> jdbc:mysql://mvrs.db.tv.sohu.com:3307/mvrs?useUnicode=true&amp;characterEncoding=UTF-8&amp;autoReconnect=true&amp;zeroDateTimeBehavior=convertToNull&amp;connectTimeout=5000&amp;socketTimeout=5000, properties -> {password=******, user=******} ], com.mchange.v2.c3p0.ComboPooledDataSource [ acquireIncrement -> 1, acquireRetryAttempts -> 30, acquireRetryDelay -> 1000, autoCommitOnClose -> false, automaticTestTable -> null, breakAfterAcquireFailure -> false, checkoutTimeout -> 0, connectionCustomizerClassName -> null, connectionTesterClassName -> com.mchange.v2.c3p0.impl.DefaultConnectionTester, dataSourceName -> 2saeu7931y73aicgvk8cr|2b73c3aa, debugUnreturnedConnectionStackTraces -> false, description -> null, driverClass -> com.mysql.jdbc.Driver, factoryClassLocation -> null, forceIgnoreUnresolvedTransactions -> false, identityToken -> 2saeu7931y73aicgvk8cr|2b73c3aa, idleConnectionTestPeriod -> 900, initialPoolSize -> 5, jdbcUrl -> jdbc:mysql://vrs.db.tv.sohu.com:3306/vrs, lastAcquisitionFailureDefaultUser -> null, maxAdministrativeTaskTime -> 0, maxConnectionAge -> 0, maxIdleTime -> 600000, maxIdleTimeExcessConnections -> 0, maxPoolSize -> 10, maxStatements -> 100, maxStatementsPerConnection -> 0, minPoolSize -> 5, numHelperThreads -> 10, numThreadsAwaitingCheckoutDefaultUser -> 0, preferredTestQuery -> null, properties -> {user=******, password=******}, propertyCycle -> 0, testConnectionOnCheckin -> false, testConnectionOnCheckout -> false, unreturnedConnectionTimeout -> 0, usesTraditionalReflectiveProxies -> false ], com.mchange.v2.c3p0.DriverManagerDataSource@25a1be3d [ description -> null, driverClass -> com.mysql.jdbc.Driver, factoryClassLocation -> null, identityToken -> 2saeu7931y73aicgvk8cr|3f14b553, jdbcUrl -> jdbc:mysql://mvrsp.db.tv.sohu.com:3306/mvrs_playcount, properties -> {password=******, user=******} ], com.mchange.v2.c3p0.WrapperConnectionPoolDataSource@60e77f1c [ acquireIncrement -> 1, acquireRetryAttempts -> 30, acquireRetryDelay -> 1000, autoCommitOnClose -> false, automaticTestTable -> null, breakAfterAcquireFailure -> false, checkoutTimeout -> 0, connectionCustomizerClassName -> null, connectionTesterClassName -> com.mchange.v2.c3p0.impl.DefaultConnectionTester, debugUnreturnedConnectionStackTraces -> false, factoryClassLocation -> null, forceIgnoreUnresolvedTransactions -> false, identityToken -> 2saeu7931y73aicgvk8cr|688c7c7f, idleConnectionTestPeriod -> 900, initialPoolSize -> 1, maxAdministrativeTaskTime -> 0, maxConnectionAge -> 0, maxIdleTime -> 600000, maxIdleTimeExcessConnections -> 0, maxPoolSize -> 2, maxStatements -> 100, maxStatementsPerConnection -> 0, minPoolSize -> 1, nestedDataSource -> com.mchange.v2.c3p0.DriverManagerDataSource@25a1be3d [ description -> null, driverClass -> com.mysql.jdbc.Driver, factoryClassLocation -> null, identityToken -> 2saeu7931y73aicgvk8cr|3f14b553, jdbcUrl -> jdbc:mysql://mvrsp.db.tv.sohu.com:3306/mvrs_playcount, properties -> {password=******, user=******} ], preferredTestQuery -> null, propertyCycle -> 0, testConnectionOnCheckin -> false, testConnectionOnCheckout -> false, unreturnedConnectionTimeout -> 0, usesTraditionalReflectiveProxies -> false; userOverrides: {} ], com.mchange.v2.c3p0.ComboPooledDataSource [ acquireIncrement -> 1, acquireRetryAttempts -> 30, acquireRetryDelay -> 1000, autoCommitOnClose -> false, automaticTestTable -> null, breakAfterAcquireFailure -> false, checkoutTimeout -> 0, connectionCustomizerClassName -> null, connectionTesterClassName -> com.mchange.v2.c3p0.impl.DefaultConnectionTester, dataSourceName -> 2saeu7931y73aicgvk8cr|b56efe, debugUnreturnedConnectionStackTraces -> false, description -> null, driverClass -> com.mysql.jdbc.Driver, factoryClassLocation -> null, forceIgnoreUnresolvedTransactions -> false, identityToken -> 2saeu7931y73aicgvk8cr|b56efe, idleConnectionTestPeriod -> 900, initialPoolSize -> 5, jdbcUrl -> jdbc:mysql://mvrs.db.tv.sohu.com:3307/mvrs?useUnicode=true&amp;characterEncoding=UTF-8&amp;autoReconnect=true&amp;zeroDateTimeBehavior=convertToNull&amp;connectTimeout=5000&amp;socketTimeout=5000, lastAcquisitionFailureDefaultUser -> null, maxAdministrativeTaskTime -> 0, maxConnectionAge -> 0, maxIdleTime -> 600000, maxIdleTimeExcessConnections -> 0, maxPoolSize -> 10, maxStatements -> 100, maxStatementsPerConnection -> 0, minPoolSize -> 5, numHelperThreads -> 10, numThreadsAwaitingCheckoutDefaultUser -> 0, preferredTestQuery -> null, properties -> {user=******, password=******}, propertyCycle -> 0, testConnectionOnCheckin -> false, testConnectionOnCheckout -> false, unreturnedConnectionTimeout -> 0, usesTraditionalReflectiveProxies -> false ], com.mchange.v2.c3p0.WrapperConnectionPoolDataSource@261b1e6e [ acquireIncrement -> 1, acquireRetryAttempts -> 30, acquireRetryDelay -> 1000, autoCommitOnClose -> false, automaticTestTable -> null, breakAfterAcquireFailure -> false, checkoutTimeout -> 0, connectionCustomizerClassName -> null, connectionTesterClassName -> com.mchange.v2.c3p0.impl.DefaultConnectionTester, debugUnreturnedConnectionStackTraces -> false, factoryClassLocation -> null, forceIgnoreUnresolvedTransactions -> false, identityToken -> 2saeu7931y73aicgvk8cr|1e2aabf8, idleConnectionTestPeriod -> 900, initialPoolSize -> 5, maxAdministrativeTaskTime -> 0, maxConnectionAge -> 0, maxIdleTime -> 600000, maxIdleTimeExcessConnections -> 0, maxPoolSize -> 10, maxStatements -> 100, maxStatementsPerConnection -> 0, minPoolSize -> 5, nestedDataSource -> com.mchange.v2.c3p0.DriverManagerDataSource@85a8a48a [ description -> null, driverClass -> com.mysql.jdbc.Driver, factoryClassLocation -> null, identityToken -> 2saeu7931y73aicgvk8cr|1c8aeedc, jdbcUrl -> jdbc:mysql://mvrs.db.tv.sohu.com:3307/mvrs?useUnicode=true&amp;characterEncoding=UTF-8&amp;autoReconnect=true&amp;zeroDateTimeBehavior=convertToNull&amp;connectTimeout=5000&amp;socketTimeout=5000, properties -> {password=******, user=******} ], preferredTestQuery -> null, propertyCycle -> 0, testConnectionOnCheckin -> false, testConnectionOnCheckout -> false, unreturnedConnectionTimeout -> 0, usesTraditionalReflectiveProxies -> false; userOverrides: {} ], com.mchange.v2.c3p0.ComboPooledDataSource [ acquireIncrement -> 1, acquireRetryAttempts -> 30, acquireRetryDelay -> 1000, autoCommitOnClose -> false, automaticTestTable -> null, breakAfterAcquireFailure -> false, checkoutTimeout -> 0, connectionCustomizerClassName -> null, connectionTesterClassName -> com.mchange.v2.c3p0.impl.DefaultConnectionTester, dataSourceName -> 2saeu7931y73aicgvk8cr|26d0729d, debugUnreturnedConnectionStackTraces -> false, description -> null, driverClass -> com.mysql.jdbc.Driver, factoryClassLocation -> null, forceIgnoreUnresolvedTransactions -> false, identityToken -> 2saeu7931y73aicgvk8cr|26d0729d, idleConnectionTestPeriod -> 900, initialPoolSize -> 1, jdbcUrl -> jdbc:mysql://mvrsp.db.tv.sohu.com:3306/mvrs_playcount, lastAcquisitionFailureDefaultUser -> null, maxAdministrativeTaskTime -> 0, maxConnectionAge -> 0, maxIdleTime -> 600000, maxIdleTimeExcessConnections -> 0, maxPoolSize -> 2, maxStatements -> 100, maxStatementsPerConnection -> 0, minPoolSize -> 1, numHelperThreads -> 10, numThreadsAwaitingCheckoutDefaultUser -> 0, preferredTestQuery -> null, properties -> {user=******, password=******}, propertyCycle -> 0, testConnectionOnCheckin -> false, testConnectionOnCheckout -> false, unreturnedConnectionTimeout -> 0, usesTraditionalReflectiveProxies -> false ]]
	==>[AllIdentityTokenizedCount] @int : 9
	==>[AllIdentityTokenizedStringified] @[Ljava.lang.String; : [Ljava.lang.String;@fa7f9dc
	==>[AllIdentityTokens] @[Ljava.lang.String; : [Ljava.lang.String;@64df83e5
	==>[AllPooledDataSources] @java.util.Set : [com.mchange.v2.c3p0.ComboPooledDataSource [ acquireIncrement -> 1, acquireRetryAttempts -> 30, acquireRetryDelay -> 1000, autoCommitOnClose -> false, automaticTestTable -> null, breakAfterAcquireFailure -> false, checkoutTimeout -> 0, connectionCustomizerClassName -> null, connectionTesterClassName -> com.mchange.v2.c3p0.impl.DefaultConnectionTester, dataSourceName -> 2saeu7931y73aicgvk8cr|2b73c3aa, debugUnreturnedConnectionStackTraces -> false, description -> null, driverClass -> com.mysql.jdbc.Driver, factoryClassLocation -> null, forceIgnoreUnresolvedTransactions -> false, identityToken -> 2saeu7931y73aicgvk8cr|2b73c3aa, idleConnectionTestPeriod -> 900, initialPoolSize -> 5, jdbcUrl -> jdbc:mysql://vrs.db.tv.sohu.com:3306/vrs, lastAcquisitionFailureDefaultUser -> null, maxAdministrativeTaskTime -> 0, maxConnectionAge -> 0, maxIdleTime -> 600000, maxIdleTimeExcessConnections -> 0, maxPoolSize -> 10, maxStatements -> 100, maxStatementsPerConnection -> 0, minPoolSize -> 5, numHelperThreads -> 10, numThreadsAwaitingCheckoutDefaultUser -> 0, preferredTestQuery -> null, properties -> {user=******, password=******}, propertyCycle -> 0, testConnectionOnCheckin -> false, testConnectionOnCheckout -> false, unreturnedConnectionTimeout -> 0, usesTraditionalReflectiveProxies -> false ], com.mchange.v2.c3p0.ComboPooledDataSource [ acquireIncrement -> 1, acquireRetryAttempts -> 30, acquireRetryDelay -> 1000, autoCommitOnClose -> false, automaticTestTable -> null, breakAfterAcquireFailure -> false, checkoutTimeout -> 0, connectionCustomizerClassName -> null, connectionTesterClassName -> com.mchange.v2.c3p0.impl.DefaultConnectionTester, dataSourceName -> 2saeu7931y73aicgvk8cr|b56efe, debugUnreturnedConnectionStackTraces -> false, description -> null, driverClass -> com.mysql.jdbc.Driver, factoryClassLocation -> null, forceIgnoreUnresolvedTransactions -> false, identityToken -> 2saeu7931y73aicgvk8cr|b56efe, idleConnectionTestPeriod -> 900, initialPoolSize -> 5, jdbcUrl -> jdbc:mysql://mvrs.db.tv.sohu.com:3307/mvrs?useUnicode=true&amp;characterEncoding=UTF-8&amp;autoReconnect=true&amp;zeroDateTimeBehavior=convertToNull&amp;connectTimeout=5000&amp;socketTimeout=5000, lastAcquisitionFailureDefaultUser -> null, maxAdministrativeTaskTime -> 0, maxConnectionAge -> 0, maxIdleTime -> 600000, maxIdleTimeExcessConnections -> 0, maxPoolSize -> 10, maxStatements -> 100, maxStatementsPerConnection -> 0, minPoolSize -> 5, numHelperThreads -> 10, numThreadsAwaitingCheckoutDefaultUser -> 0, preferredTestQuery -> null, properties -> {user=******, password=******}, propertyCycle -> 0, testConnectionOnCheckin -> false, testConnectionOnCheckout -> false, unreturnedConnectionTimeout -> 0, usesTraditionalReflectiveProxies -> false ], com.mchange.v2.c3p0.ComboPooledDataSource [ acquireIncrement -> 1, acquireRetryAttempts -> 30, acquireRetryDelay -> 1000, autoCommitOnClose -> false, automaticTestTable -> null, breakAfterAcquireFailure -> false, checkoutTimeout -> 0, connectionCustomizerClassName -> null, connectionTesterClassName -> com.mchange.v2.c3p0.impl.DefaultConnectionTester, dataSourceName -> 2saeu7931y73aicgvk8cr|26d0729d, debugUnreturnedConnectionStackTraces -> false, description -> null, driverClass -> com.mysql.jdbc.Driver, factoryClassLocation -> null, forceIgnoreUnresolvedTransactions -> false, identityToken -> 2saeu7931y73aicgvk8cr|26d0729d, idleConnectionTestPeriod -> 900, initialPoolSize -> 1, jdbcUrl -> jdbc:mysql://mvrsp.db.tv.sohu.com:3306/mvrs_playcount, lastAcquisitionFailureDefaultUser -> null, maxAdministrativeTaskTime -> 0, maxConnectionAge -> 0, maxIdleTime -> 600000, maxIdleTimeExcessConnections -> 0, maxPoolSize -> 2, maxStatements -> 100, maxStatementsPerConnection -> 0, minPoolSize -> 1, numHelperThreads -> 10, numThreadsAwaitingCheckoutDefaultUser -> 0, preferredTestQuery -> null, properties -> {user=******, password=******}, propertyCycle -> 0, testConnectionOnCheckin -> false, testConnectionOnCheckout -> false, unreturnedConnectionTimeout -> 0, usesTraditionalReflectiveProxies -> false ]]
	==>[AllPooledDataSourcesCount] @int : 3
	==>[AllPooledDataSourcesStringified] @[Ljava.lang.String; : [Ljava.lang.String;@7966340c
	==>[C3p0Version] @java.lang.String : 0.9.1
	==>[NumPooledDataSources] @int : 3
	==>[NumPoolsAllDataSources] @int : 3
	ObjectName : java.lang:type=GarbageCollector,name=ParNew
	==>[LastGcInfo] @javax.management.openmbean.CompositeData : javax.management.openmbean.CompositeDataSupport(compositeType=javax.management.openmbean.CompositeType(name=sun.management.ParNew.GcInfoCompositeType,items=((itemName=GcThreadCount,itemType=javax.management.openmbean.SimpleType(name=java.lang.Integer)),(itemName=duration,itemType=javax.management.openmbean.SimpleType(name=java.lang.Long)),(itemName=endTime,itemType=javax.management.openmbean.SimpleType(name=java.lang.Long)),(itemName=id,itemType=javax.management.openmbean.SimpleType(name=java.lang.Long)),(itemName=memoryUsageAfterGc,itemType=javax.management.openmbean.TabularType(name=Map<java.lang.String,java.lang.management.MemoryUsage>,rowType=javax.management.openmbean.CompositeType(name=Map<java.lang.String,java.lang.management.MemoryUsage>,items=((itemName=key,itemType=javax.management.openmbean.SimpleType(name=java.lang.String)),(itemName=value,itemType=javax.management.openmbean.CompositeType(name=java.lang.management.MemoryUsage,items=((itemName=committed,itemType=javax.management.openmbean.SimpleType(name=java.lang.Long)),(itemName=init,itemType=javax.management.openmbean.SimpleType(name=java.lang.Long)),(itemName=max,itemType=javax.management.openmbean.SimpleType(name=java.lang.Long)),(itemName=used,itemType=javax.management.openmbean.SimpleType(name=java.lang.Long))))))),indexNames=(key))),(itemName=memoryUsageBeforeGc,itemType=javax.management.openmbean.TabularType(name=Map<java.lang.String,java.lang.management.MemoryUsage>,rowType=javax.management.openmbean.CompositeType(name=Map<java.lang.String,java.lang.management.MemoryUsage>,items=((itemName=key,itemType=javax.management.openmbean.SimpleType(name=java.lang.String)),(itemName=value,itemType=javax.management.openmbean.CompositeType(name=java.lang.management.MemoryUsage,items=((itemName=committed,itemType=javax.management.openmbean.SimpleType(name=java.lang.Long)),(itemName=init,itemType=javax.management.openmbean.SimpleType(name=java.lang.Long)),(itemName=max,itemType=javax.management.openmbean.SimpleType(name=java.lang.Long)),(itemName=used,itemType=javax.management.openmbean.SimpleType(name=java.lang.Long))))))),indexNames=(key))),(itemName=startTime,itemType=javax.management.openmbean.SimpleType(name=java.lang.Long)))),contents={GcThreadCount=24, duration=6, endTime=433794065, id=35, memoryUsageAfterGc=javax.management.openmbean.TabularDataSupport(tabularType=javax.management.openmbean.TabularType(name=Map<java.lang.String,java.lang.management.MemoryUsage>,rowType=javax.management.openmbean.CompositeType(name=Map<java.lang.String,java.lang.management.MemoryUsage>,items=((itemName=key,itemType=javax.management.openmbean.SimpleType(name=java.lang.String)),(itemName=value,itemType=javax.management.openmbean.CompositeType(name=java.lang.management.MemoryUsage,items=((itemName=committed,itemType=javax.management.openmbean.SimpleType(name=java.lang.Long)),(itemName=init,itemType=javax.management.openmbean.SimpleType(name=java.lang.Long)),(itemName=max,itemType=javax.management.openmbean.SimpleType(name=java.lang.Long)),(itemName=used,itemType=javax.management.openmbean.SimpleType(name=java.lang.Long))))))),indexNames=(key)),contents={[CMS Perm Gen]=javax.management.openmbean.CompositeDataSupport(compositeType=javax.management.openmbean.CompositeType(name=Map<java.lang.String,java.lang.management.MemoryUsage>,items=((itemName=key,itemType=javax.management.openmbean.SimpleType(name=java.lang.String)),(itemName=value,itemType=javax.management.openmbean.CompositeType(name=java.lang.management.MemoryUsage,items=((itemName=committed,itemType=javax.management.openmbean.SimpleType(name=java.lang.Long)),(itemName=init,itemType=javax.management.openmbean.SimpleType(name=java.lang.Long)),(itemName=max,itemType=javax.management.openmbean.SimpleType(name=java.lang.Long)),(itemName=used,itemType=javax.management.openmbean.SimpleType(name=java.lang.Long))))))),contents={key=CMS Perm Gen, value=javax.management.openmbean.CompositeDataSupport(compositeType=javax.management.openmbean.CompositeType(name=java.lang.management.MemoryUsage,items=((itemName=committed,itemType=javax.management.openmbean.SimpleType(name=java.lang.Long)),(itemName=init,itemType=javax.management.openmbean.SimpleType(name=java.lang.Long)),(itemName=max,itemType=javax.management.openmbean.SimpleType(name=java.lang.Long)),(itemName=used,itemType=javax.management.openmbean.SimpleType(name=java.lang.Long)))),contents={committed=268435456, init=268435456, max=268435456, used=77772056})}), [Code Cache]=javax.management.openmbean.CompositeDataSupport(compositeType=javax.management.openmbean.CompositeType(name=Map<java.lang.String,java.lang.management.MemoryUsage>,items=((itemName=key,itemType=javax.management.openmbean.SimpleType(name=java.lang.String)),(itemName=value,itemType=javax.management.openmbean.CompositeType(name=java.lang.management.MemoryUsage,items=((itemName=committed,itemType=javax.management.openmbean.SimpleType(name=java.lang.Long)),(itemName=init,itemType=javax.management.openmbean.SimpleType(name=java.lang.Long)),(itemName=max,itemType=javax.management.openmbean.SimpleType(name=java.lang.Long)),(itemName=used,itemType=javax.management.openmbean.SimpleType(name=java.lang.Long))))))),contents={key=Code Cache, value=javax.management.openmbean.CompositeDataSupport(compositeType=javax.management.openmbean.CompositeType(name=java.lang.management.MemoryUsage,items=((itemName=committed,itemType=javax.management.openmbean.SimpleType(name=java.lang.Long)),(itemName=init,itemType=javax.management.openmbean.SimpleType(name=java.lang.Long)),(itemName=max,itemType=javax.management.openmbean.SimpleType(name=java.lang.Long)),(itemName=used,itemType=javax.management.openmbean.SimpleType(name=java.lang.Long)))),contents={committed=7012352, init=2555904, max=50331648, used=6862400})}), [CMS Old Gen]=javax.management.openmbean.CompositeDataSupport(compositeType=javax.management.openmbean.CompositeType(name=Map<java.lang.String,java.lang.management.MemoryUsage>,items=((itemName=key,itemType=javax.management.openmbean.SimpleType(name=java.lang.String)),(itemName=value,itemType=javax.management.openmbean.CompositeType(name=java.lang.management.MemoryUsage,items=((itemName=committed,itemType=javax.management.openmbean.SimpleType(name=java.lang.Long)),(itemName=init,itemType=javax.management.openmbean.SimpleType(name=java.lang.Long)),(itemName=max,itemType=javax.management.openmbean.SimpleType(name=java.lang.Long)),(itemName=used,itemType=javax.management.openmbean.SimpleType(name=java.lang.Long))))))),contents={key=CMS Old Gen, value=javax.management.openmbean.CompositeDataSupport(compositeType=javax.management.openmbean.CompositeType(name=java.lang.management.MemoryUsage,items=((itemName=committed,itemType=javax.management.openmbean.SimpleType(name=java.lang.Long)),(itemName=init,itemType=javax.management.openmbean.SimpleType(name=java.lang.Long)),(itemName=max,itemType=javax.management.openmbean.SimpleType(name=java.lang.Long)),(itemName=used,itemType=javax.management.openmbean.SimpleType(name=java.lang.Long)))),contents={committed=3665821696, init=3665821696, max=3665821696, used=59593472})}), [Par Eden Space]=javax.management.openmbean.CompositeDataSupport(compositeType=javax.management.openmbean.CompositeType(name=Map<java.lang.String,java.lang.management.MemoryUsage>,items=((itemName=key,itemType=javax.management.openmbean.SimpleType(name=java.lang.String)),(itemName=value,itemType=javax.management.openmbean.CompositeType(name=java.lang.management.MemoryUsage,items=((itemName=committed,itemType=javax.management.openmbean.SimpleType(name=java.lang.Long)),(itemName=init,itemType=javax.management.openmbean.SimpleType(name=java.lang.Long)),(itemName=max,itemType=javax.management.openmbean.SimpleType(name=java.lang.Long)),(itemName=used,itemType=javax.management.openmbean.SimpleType(name=java.lang.Long))))))),contents={key=Par Eden Space, value=javax.management.openmbean.CompositeDataSupport(compositeType=javax.management.openmbean.CompositeType(name=java.lang.management.MemoryUsage,items=((itemName=committed,itemType=javax.management.openmbean.SimpleType(name=java.lang.Long)),(itemName=init,itemType=javax.management.openmbean.SimpleType(name=java.lang.Long)),(itemName=max,itemType=javax.management.openmbean.SimpleType(name=java.lang.Long)),(itemName=used,itemType=javax.management.openmbean.SimpleType(name=java.lang.Long)))),contents={committed=503316480, init=503316480, max=503316480, used=0})}), [Par Survivor Space]=javax.management.openmbean.CompositeDataSupport(compositeType=javax.management.openmbean.CompositeType(name=Map<java.lang.String,java.lang.management.MemoryUsage>,items=((itemName=key,itemType=javax.management.openmbean.SimpleType(name=java.lang.String)),(itemName=value,itemType=javax.management.openmbean.CompositeType(name=java.lang.management.MemoryUsage,items=((itemName=committed,itemType=javax.management.openmbean.SimpleType(name=java.lang.Long)),(itemName=init,itemType=javax.management.openmbean.SimpleType(name=java.lang.Long)),(itemName=max,itemType=javax.management.openmbean.SimpleType(name=java.lang.Long)),(itemName=used,itemType=javax.management.openmbean.SimpleType(name=java.lang.Long))))))),contents={key=Par Survivor Space, value=javax.management.openmbean.CompositeDataSupport(compositeType=javax.management.openmbean.CompositeType(name=java.lang.management.MemoryUsage,items=((itemName=committed,itemType=javax.management.openmbean.SimpleType(name=java.lang.Long)),(itemName=init,itemType=javax.management.openmbean.SimpleType(name=java.lang.Long)),(itemName=max,itemType=javax.management.openmbean.SimpleType(name=java.lang.Long)),(itemName=used,itemType=javax.management.openmbean.SimpleType(name=java.lang.Long)))),contents={committed=62914560, init=62914560, max=62914560, used=2126880})})}), memoryUsageBeforeGc=javax.management.openmbean.TabularDataSupport(tabularType=javax.management.openmbean.TabularType(name=Map<java.lang.String,java.lang.management.MemoryUsage>,rowType=javax.management.openmbean.CompositeType(name=Map<java.lang.String,java.lang.management.MemoryUsage>,items=((itemName=key,itemType=javax.management.openmbean.SimpleType(name=java.lang.String)),(itemName=value,itemType=javax.management.openmbean.CompositeType(name=java.lang.management.MemoryUsage,items=((itemName=committed,itemType=javax.management.openmbean.SimpleType(name=java.lang.Long)),(itemName=init,itemType=javax.management.openmbean.SimpleType(name=java.lang.Long)),(itemName=max,itemType=javax.management.openmbean.SimpleType(name=java.lang.Long)),(itemName=used,itemType=javax.management.openmbean.SimpleType(name=java.lang.Long))))))),indexNames=(key)),contents={[CMS Perm Gen]=javax.management.openmbean.CompositeDataSupport(compositeType=javax.management.openmbean.CompositeType(name=Map<java.lang.String,java.lang.management.MemoryUsage>,items=((itemName=key,itemType=javax.management.openmbean.SimpleType(name=java.lang.String)),(itemName=value,itemType=javax.management.openmbean.CompositeType(name=java.lang.management.MemoryUsage,items=((itemName=committed,itemType=javax.management.openmbean.SimpleType(name=java.lang.Long)),(itemName=init,itemType=javax.management.openmbean.SimpleType(name=java.lang.Long)),(itemName=max,itemType=javax.management.openmbean.SimpleType(name=java.lang.Long)),(itemName=used,itemType=javax.management.openmbean.SimpleType(name=java.lang.Long))))))),contents={key=CMS Perm Gen, value=javax.management.openmbean.CompositeDataSupport(compositeType=javax.management.openmbean.CompositeType(name=java.lang.management.MemoryUsage,items=((itemName=committed,itemType=javax.management.openmbean.SimpleType(name=java.lang.Long)),(itemName=init,itemType=javax.management.openmbean.SimpleType(name=java.lang.Long)),(itemName=max,itemType=javax.management.openmbean.SimpleType(name=java.lang.Long)),(itemName=used,itemType=javax.management.openmbean.SimpleType(name=java.lang.Long)))),contents={committed=268435456, init=268435456, max=268435456, used=77772056})}), [Code Cache]=javax.management.openmbean.CompositeDataSupport(compositeType=javax.management.openmbean.CompositeType(name=Map<java.lang.String,java.lang.management.MemoryUsage>,items=((itemName=key,itemType=javax.management.openmbean.SimpleType(name=java.lang.String)),(itemName=value,itemType=javax.management.openmbean.CompositeType(name=java.lang.management.MemoryUsage,items=((itemName=committed,itemType=javax.management.openmbean.SimpleType(name=java.lang.Long)),(itemName=init,itemType=javax.management.openmbean.SimpleType(name=java.lang.Long)),(itemName=max,itemType=javax.management.openmbean.SimpleType(name=java.lang.Long)),(itemName=used,itemType=javax.management.openmbean.SimpleType(name=java.lang.Long))))))),contents={key=Code Cache, value=javax.management.openmbean.CompositeDataSupport(compositeType=javax.management.openmbean.CompositeType(name=java.lang.management.MemoryUsage,items=((itemName=committed,itemType=javax.management.openmbean.SimpleType(name=java.lang.Long)),(itemName=init,itemType=javax.management.openmbean.SimpleType(name=java.lang.Long)),(itemName=max,itemType=javax.management.openmbean.SimpleType(name=java.lang.Long)),(itemName=used,itemType=javax.management.openmbean.SimpleType(name=java.lang.Long)))),contents={committed=7012352, init=2555904, max=50331648, used=6862400})}), [CMS Old Gen]=javax.management.openmbean.CompositeDataSupport(compositeType=javax.management.openmbean.CompositeType(name=Map<java.lang.String,java.lang.management.MemoryUsage>,items=((itemName=key,itemType=javax.management.openmbean.SimpleType(name=java.lang.String)),(itemName=value,itemType=javax.management.openmbean.CompositeType(name=java.lang.management.MemoryUsage,items=((itemName=committed,itemType=javax.management.openmbean.SimpleType(name=java.lang.Long)),(itemName=init,itemType=javax.management.openmbean.SimpleType(name=java.lang.Long)),(itemName=max,itemType=javax.management.openmbean.SimpleType(name=java.lang.Long)),(itemName=used,itemType=javax.management.openmbean.SimpleType(name=java.lang.Long))))))),contents={key=CMS Old Gen, value=javax.management.openmbean.CompositeDataSupport(compositeType=javax.management.openmbean.CompositeType(name=java.lang.management.MemoryUsage,items=((itemName=committed,itemType=javax.management.openmbean.SimpleType(name=java.lang.Long)),(itemName=init,itemType=javax.management.openmbean.SimpleType(name=java.lang.Long)),(itemName=max,itemType=javax.management.openmbean.SimpleType(name=java.lang.Long)),(itemName=used,itemType=javax.management.openmbean.SimpleType(name=java.lang.Long)))),contents={committed=3665821696, init=3665821696, max=3665821696, used=59592768})}), [Par Eden Space]=javax.management.openmbean.CompositeDataSupport(compositeType=javax.management.openmbean.CompositeType(name=Map<java.lang.String,java.lang.management.MemoryUsage>,items=((itemName=key,itemType=javax.management.openmbean.SimpleType(name=java.lang.String)),(itemName=value,itemType=javax.management.openmbean.CompositeType(name=java.lang.management.MemoryUsage,items=((itemName=committed,itemType=javax.management.openmbean.SimpleType(name=java.lang.Long)),(itemName=init,itemType=javax.management.openmbean.SimpleType(name=java.lang.Long)),(itemName=max,itemType=javax.management.openmbean.SimpleType(name=java.lang.Long)),(itemName=used,itemType=javax.management.openmbean.SimpleType(name=java.lang.Long))))))),contents={key=Par Eden Space, value=javax.management.openmbean.CompositeDataSupport(compositeType=javax.management.openmbean.CompositeType(name=java.lang.management.MemoryUsage,items=((itemName=committed,itemType=javax.management.openmbean.SimpleType(name=java.lang.Long)),(itemName=init,itemType=javax.management.openmbean.SimpleType(name=java.lang.Long)),(itemName=max,itemType=javax.management.openmbean.SimpleType(name=java.lang.Long)),(itemName=used,itemType=javax.management.openmbean.SimpleType(name=java.lang.Long)))),contents={committed=503316480, init=503316480, max=503316480, used=503316480})}), [Par Survivor Space]=javax.management.openmbean.CompositeDataSupport(compositeType=javax.management.openmbean.CompositeType(name=Map<java.lang.String,java.lang.management.MemoryUsage>,items=((itemName=key,itemType=javax.management.openmbean.SimpleType(name=java.lang.String)),(itemName=value,itemType=javax.management.openmbean.CompositeType(name=java.lang.management.MemoryUsage,items=((itemName=committed,itemType=javax.management.openmbean.SimpleType(name=java.lang.Long)),(itemName=init,itemType=javax.management.openmbean.SimpleType(name=java.lang.Long)),(itemName=max,itemType=javax.management.openmbean.SimpleType(name=java.lang.Long)),(itemName=used,itemType=javax.management.openmbean.SimpleType(name=java.lang.Long))))))),contents={key=Par Survivor Space, value=javax.management.openmbean.CompositeDataSupport(compositeType=javax.management.openmbean.CompositeType(name=java.lang.management.MemoryUsage,items=((itemName=committed,itemType=javax.management.openmbean.SimpleType(name=java.lang.Long)),(itemName=init,itemType=javax.management.openmbean.SimpleType(name=java.lang.Long)),(itemName=max,itemType=javax.management.openmbean.SimpleType(name=java.lang.Long)),(itemName=used,itemType=javax.management.openmbean.SimpleType(name=java.lang.Long)))),contents={committed=62914560, init=62914560, max=62914560, used=1650304})})}), startTime=433794059})
	==>[CollectionCount] @long : 35
	==>[CollectionTime] @long : 391
	==>[Name] @java.lang.String : ParNew
	==>[Valid] @boolean : true
	==>[MemoryPoolNames] @[Ljava.lang.String; : [Ljava.lang.String;@1b192059
	ObjectName : resin:type=Config,Host=default,WebApp=/,name="jar:file:/opt/deploy-web/video-api4-server/WEB-INF/lib/spring-web-3.2.3.RELEASE.jar!/META-INF/web-fragment.xml"
	==>[Crc64] @long : 8927401422753239866
	==>[LastModified] @long : 1369015450000
	==>[Length] @long : 368
	==>[Name] @java.lang.String : jar:file:/opt/deploy-web/video-api4-server/WEB-INF/lib/spring-web-3.2.3.RELEASE.jar!/META-INF/web-fragment.xml
	==>[ObjectName] @javax.management.ObjectName : resin:type=Config,Host=default,WebApp=/,name="jar:file:/opt/deploy-web/video-api4-server/WEB-INF/lib/spring-web-3.2.3.RELEASE.jar!/META-INF/web-fragment.xml"
	==>[Path] @java.lang.String : /META-INF/web-fragment.xml
	==>[Type] @java.lang.String : Config
	ObjectName : resin:type=Environment,Host=default
	==>[ClassPath] @[Ljava.lang.String; : [Ljava.lang.String;@5867df9
	==>[Name] @java.lang.String : null
	==>[ObjectName] @javax.management.ObjectName : resin:type=Environment,Host=default
	==>[Type] @java.lang.String : Environment
	ObjectName : com.mchange.v2.c3p0:type=PooledDataSource[2saeu7931y73aicgvk8cr|26d0729d]
	==>[overrideDefaultPassword] @java.lang.String : null
	==>[statementCacheNumConnectionsWithCachedStatementsAllUsers] @int : 0
	==>[dataSourceName] @java.lang.String : 2saeu7931y73aicgvk8cr|26d0729d
	==>[threadPoolNumIdleThreads] @int : 10
	==>[acquireRetryAttempts] @int : 30
	==>[autoCommitOnClose] @boolean : false
	==>[identityToken] @java.lang.String : 2saeu7931y73aicgvk8cr|26d0729d
	==>[forceIgnoreUnresolvedTransactions] @boolean : false
	==>[startTimeMillisDefaultUser] @long : 1408698852204
	==>[breakAfterAcquireFailure] @boolean : false
	==>[idleConnectionTestPeriod] @int : 900
	==>[maxPoolSize] @int : 2
	==>[user] @java.lang.String : db_playcount
	==>[numIdleConnectionsDefaultUser] @int : 2
	==>[numFailedCheckoutsDefaultUser] @long : 0
	==>[numBusyConnections] @int : 0
	==>[numConnections] @int : 2
	==>[connectionCustomizerClassName] @java.lang.String : null
	==>[automaticTestTable] @java.lang.String : null
	==>[numFailedIdleTestsDefaultUser] @long : 0
	==>[statementCacheNumStatementsDefaultUser] @int : 0
	==>[effectivePropertyCycleDefaultUser] @float : 900.0
	==>[maxIdleTime] @int : 600000
	==>[numBusyConnectionsDefaultUser] @int : 0
	==>[numConnectionsAllUsers] @int : 2
	==>[maxIdleTimeExcessConnections] @int : 0
	==>[checkoutTimeout] @int : 0
	==>[allUsers] @java.util.Collection : [db_playcount]
	==>[statementCacheNumConnectionsWithCachedStatementsDefaultUser] @int : 0
	==>[usesTraditionalReflectiveProxies] @boolean : false
	==>[maxAdministrativeTaskTime] @int : 0
	==>[userOverridesAsString] @java.lang.String : null
	==>[driverClass] @java.lang.String : com.mysql.jdbc.Driver
	==>[connectionTesterClassName] @java.lang.String : com.mchange.v2.c3p0.impl.DefaultConnectionTester
	==>[maxStatements] @int : 100
	==>[statementCacheNumCheckedOutDefaultUser] @int : 0
	==>[statementCacheNumStatementsAllUsers] @int : 0
	==>[numUnclosedOrphanedConnectionsDefaultUser] @int : 0
	==>[debugUnreturnedConnectionStackTraces] @boolean : false
	==>[testConnectionOnCheckout] @boolean : false
	==>[numConnectionsDefaultUser] @int : 2
	==>[acquireRetryDelay] @int : 1000
	==>[overrideDefaultUser] @java.lang.String : null
	==>[threadPoolSize] @int : 10
	==>[threadPoolNumTasksPending] @int : 0
	==>[unreturnedConnectionTimeout] @int : 0
	==>[acquireIncrement] @int : 1
	==>[numThreadsAwaitingCheckoutDefaultUser] @int : 0
	==>[threadPoolNumActiveThreads] @int : 0
	==>[minPoolSize] @int : 1
	==>[maxStatementsPerConnection] @int : 0
	==>[statementCacheNumCheckedOutStatementsAllUsers] @int : 0
	==>[testConnectionOnCheckin] @boolean : false
	==>[numUserPools] @int : 1
	==>[numFailedCheckinsDefaultUser] @long : 0
	==>[numUnclosedOrphanedConnectionsAllUsers] @int : 0
	==>[initialPoolSize] @int : 1
	==>[upTimeMillisDefaultUser] @long : 433866169
	==>[numUnclosedOrphanedConnections] @int : 0
	==>[properties] @java.util.Properties : {password=******, user=******}
	==>[maxConnectionAge] @int : 0
	==>[loginTimeout] @int : 0
	==>[numIdleConnectionsAllUsers] @int : 2
	==>[factoryClassLocation] @java.lang.String : null
	==>[jdbcUrl] @java.lang.String : jdbc:mysql://mvrsp.db.tv.sohu.com:3306/mvrs_playcount
	==>[propertyCycle] @int : 0
	==>[numBusyConnectionsAllUsers] @int : 0
	==>[numIdleConnections] @int : 2
	==>[numHelperThreads] @int : 10
	==>[preferredTestQuery] @java.lang.String : null
	==>[description] @java.lang.String : null
	ObjectName : resin:type=WebAppDeploy,Host=default,name=webapps
	==>[ArchiveDirectory] @java.lang.String : /opt/resin/resin-video-api4-server/webapps
	==>[ConfigException] @java.lang.Throwable : null
	==>[DependencyCheckInterval] @long : 5000
	==>[ExpandDirectory] @java.lang.String : /opt/resin/resin-video-api4-server/webapps
	==>[ExpandPrefix] @java.lang.String : 
	==>[ExpandSuffix] @java.lang.String : 
	==>[Extension] @java.lang.String : .war
	==>[Modified] @boolean : false
	==>[Name] @java.lang.String : webapps
	==>[Names] @[Ljava.lang.String; : [Ljava.lang.String;@731de9b
	==>[ObjectName] @javax.management.ObjectName : resin:type=WebAppDeploy,Host=default,name=webapps
	==>[RedeployMode] @java.lang.String : AUTOMATIC
	==>[StartupMode] @java.lang.String : AUTOMATIC
	==>[State] @java.lang.String : ACTIVE
	==>[Type] @java.lang.String : WebAppDeploy
	==>[URLPrefix] @java.lang.String : 
	ObjectName : resin:type=ThreadPool
	==>[Name] @java.lang.String : null
	==>[ObjectName] @javax.management.ObjectName : resin:type=ThreadPool
	==>[ThreadActiveCount] @int : 19
	==>[ThreadCount] @int : 42
	==>[ThreadCreateCountTotal] @long : 3251
	==>[ThreadExecutorMax] @int : -1
	==>[ThreadIdleCount] @int : 23
	==>[ThreadIdleMax] @int : 1024
	==>[ThreadIdleMin] @int : 16
	==>[ThreadMax] @int : 4096
	==>[ThreadOverflowCountTotal] @long : 0
	==>[ThreadPriorityMin] @int : 2
	==>[ThreadPriorityQueueSize] @int : 0
	==>[ThreadStartingCount] @int : 0
	==>[ThreadTaskQueueSize] @int : 0
	==>[ThreadWaitCount] @int : 0
	==>[Type] @java.lang.String : ThreadPool
	ObjectName : java.lang:type=Memory
	==>[Verbose] @boolean : true
	==>[HeapMemoryUsage] @javax.management.openmbean.CompositeData : javax.management.openmbean.CompositeDataSupport(compositeType=javax.management.openmbean.CompositeType(name=java.lang.management.MemoryUsage,items=((itemName=committed,itemType=javax.management.openmbean.SimpleType(name=java.lang.Long)),(itemName=init,itemType=javax.management.openmbean.SimpleType(name=java.lang.Long)),(itemName=max,itemType=javax.management.openmbean.SimpleType(name=java.lang.Long)),(itemName=used,itemType=javax.management.openmbean.SimpleType(name=java.lang.Long)))),contents={committed=4232052736, init=4294967296, max=4232052736, used=159901456})
	==>[NonHeapMemoryUsage] @javax.management.openmbean.CompositeData : javax.management.openmbean.CompositeDataSupport(compositeType=javax.management.openmbean.CompositeType(name=java.lang.management.MemoryUsage,items=((itemName=committed,itemType=javax.management.openmbean.SimpleType(name=java.lang.Long)),(itemName=init,itemType=javax.management.openmbean.SimpleType(name=java.lang.Long)),(itemName=max,itemType=javax.management.openmbean.SimpleType(name=java.lang.Long)),(itemName=used,itemType=javax.management.openmbean.SimpleType(name=java.lang.Long)))),contents={committed=275447808, init=270991360, max=318767104, used=84643552})
	ObjectName : com.mchange.v2.c3p0:type=PooledDataSource[2saeu7931y73aicgvk8cr|2b73c3aa]
	==>[ObjectPendingFinalizationCount] @int : 0
	==>[overrideDefaultPassword] @java.lang.String : null
	==>[statementCacheNumConnectionsWithCachedStatementsAllUsers] @int : 0
	==>[dataSourceName] @java.lang.String : 2saeu7931y73aicgvk8cr|2b73c3aa
	==>[threadPoolNumIdleThreads] @int : 10
	==>[acquireRetryAttempts] @int : 30
	==>[autoCommitOnClose] @boolean : false
	==>[identityToken] @java.lang.String : 2saeu7931y73aicgvk8cr|2b73c3aa
	==>[forceIgnoreUnresolvedTransactions] @boolean : false
	==>[startTimeMillisDefaultUser] @long : 1408698851733
	==>[breakAfterAcquireFailure] @boolean : false
	==>[idleConnectionTestPeriod] @int : 900
	==>[maxPoolSize] @int : 10
	==>[user] @java.lang.String : vrsMobile
	==>[numIdleConnectionsDefaultUser] @int : 5
	==>[numFailedCheckoutsDefaultUser] @long : 0
	==>[numBusyConnections] @int : 0
	==>[numConnections] @int : 5
	==>[connectionCustomizerClassName] @java.lang.String : null
	==>[automaticTestTable] @java.lang.String : null
	==>[numFailedIdleTestsDefaultUser] @long : 0
	==>[statementCacheNumStatementsDefaultUser] @int : 0
	==>[effectivePropertyCycleDefaultUser] @float : 900.0
	==>[maxIdleTime] @int : 600000
	==>[numBusyConnectionsDefaultUser] @int : 0
	==>[numConnectionsAllUsers] @int : 5
	==>[maxIdleTimeExcessConnections] @int : 0
	==>[checkoutTimeout] @int : 0
	==>[allUsers] @java.util.Collection : [vrsMobile]
	==>[statementCacheNumConnectionsWithCachedStatementsDefaultUser] @int : 0
	==>[usesTraditionalReflectiveProxies] @boolean : false
	==>[maxAdministrativeTaskTime] @int : 0
	==>[userOverridesAsString] @java.lang.String : null
	==>[driverClass] @java.lang.String : com.mysql.jdbc.Driver
	==>[connectionTesterClassName] @java.lang.String : com.mchange.v2.c3p0.impl.DefaultConnectionTester
	==>[maxStatements] @int : 100
	==>[statementCacheNumCheckedOutDefaultUser] @int : 0
	==>[statementCacheNumStatementsAllUsers] @int : 0
	==>[numUnclosedOrphanedConnectionsDefaultUser] @int : 0
	==>[debugUnreturnedConnectionStackTraces] @boolean : false
	==>[testConnectionOnCheckout] @boolean : false
	==>[numConnectionsDefaultUser] @int : 5
	==>[acquireRetryDelay] @int : 1000
	==>[overrideDefaultUser] @java.lang.String : null
	==>[threadPoolSize] @int : 10
	==>[threadPoolNumTasksPending] @int : 0
	==>[unreturnedConnectionTimeout] @int : 0
	==>[acquireIncrement] @int : 1
	==>[numThreadsAwaitingCheckoutDefaultUser] @int : 0
	==>[threadPoolNumActiveThreads] @int : 0
	==>[minPoolSize] @int : 5
	==>[maxStatementsPerConnection] @int : 0
	==>[statementCacheNumCheckedOutStatementsAllUsers] @int : 0
	==>[testConnectionOnCheckin] @boolean : false
	==>[numUserPools] @int : 1
	==>[numFailedCheckinsDefaultUser] @long : 0
	==>[numUnclosedOrphanedConnectionsAllUsers] @int : 0
	==>[initialPoolSize] @int : 5
	==>[upTimeMillisDefaultUser] @long : 433866953
	==>[numUnclosedOrphanedConnections] @int : 0
	==>[properties] @java.util.Properties : {password=******, user=******}
	==>[maxConnectionAge] @int : 0
	==>[loginTimeout] @int : 0
	==>[numIdleConnectionsAllUsers] @int : 5
	==>[factoryClassLocation] @java.lang.String : null
	==>[jdbcUrl] @java.lang.String : jdbc:mysql://vrs.db.tv.sohu.com:3306/vrs
	==>[propertyCycle] @int : 0
	==>[numBusyConnectionsAllUsers] @int : 0
	==>[numIdleConnections] @int : 5
	==>[numHelperThreads] @int : 10
	==>[preferredTestQuery] @java.lang.String : null
	==>[description] @java.lang.String : null
	ObjectName : resin:type=SessionManager,Host=default,WebApp=/
	==>[AlwaysSaveSession] @boolean : false
	==>[CookieAppendServerIndex] @boolean : false
	==>[CookieDomain] @java.lang.String : null
	==>[CookieHttpOnly] @boolean : false
	==>[CookieLength] @long : 21
	==>[CookieMaxAge] @long : 0
	==>[CookieName] @java.lang.String : JSESSIONID
	==>[CookiePort] @java.lang.String : null
	==>[CookieSecure] @boolean : false
	==>[CookieVersion] @int : 0
	==>[EnableCookies] @boolean : true
	==>[EnableURLRewriting] @boolean : false
	==>[EstimatedMemorySize] @long : 0
	==>[IgnoreSerializationErrors] @boolean : true
	==>[InvalidateAfterListener] @boolean : false
	==>[Name] @java.lang.String : null
	==>[ObjectName] @javax.management.ObjectName : resin:type=SessionManager,Host=default,WebApp=/
	==>[PersistentStore] @com.caucho.management.server.PersistentStoreMXBean : null
	==>[ReuseSessionId] @boolean : true
	==>[SaveMode] @java.lang.String : after-request
	==>[SessionActiveCount] @long : 0
	==>[SessionCreateCountTotal] @long : 0
	==>[SessionInvalidateCountTotal] @long : 0
	==>[SessionMax] @int : 8192
	==>[SessionTimeout] @long : 1800000
	==>[SessionTimeoutCountTotal] @long : 0
	==>[Type] @java.lang.String : SessionManager
	==>[WebApp] @com.caucho.management.server.WebAppMXBean : resin:type=WebApp,Host=default,name=/
	ObjectName : resin:type=Memory
	==>[CodeCacheCommitted] @long : 7012352
	==>[CodeCacheFree] @long : 43469248
	==>[CodeCacheMax] @long : 50331648
	==>[CodeCacheUsed] @long : 6862400
	==>[EdenCommitted] @long : 503316480
	==>[EdenFree] @long : 404037224
	==>[EdenMax] @long : 503316480
	==>[EdenUsed] @long : 99279256
	==>[GarbageCollectionCount] @long : 37
	==>[GarbageCollectionTime] @long : 391
	==>[Name] @java.lang.String : null
	==>[ObjectName] @javax.management.ObjectName : resin:type=Memory
	==>[PermGenCommitted] @long : 268435456
	==>[PermGenFree] @long : 190654304
	==>[PermGenMax] @long : 268435456
	==>[PermGenUsed] @long : 77781152
	==>[SurvivorCommitted] @long : 62914560
	==>[SurvivorFree] @long : 60787680
	==>[SurvivorMax] @long : 62914560
	==>[SurvivorUsed] @long : 2126880
	==>[TenuredCommitted] @long : 3665821696
	==>[TenuredFree] @long : 3606228224
	==>[TenuredMax] @long : 3665821696
	==>[TenuredUsed] @long : 59593472
	==>[Type] @java.lang.String : Memory
	ObjectName : resin:type=Config,name="file:/opt/resin/resin-video-api4-server/conf/video-api4-server.xml"
	==>[Crc64] @long : 3435916865823248964
	==>[LastModified] @long : 1388715370000
	==>[Length] @long : 12129
	==>[Name] @java.lang.String : file:/opt/resin/resin-video-api4-server/conf/video-api4-server.xml
	==>[ObjectName] @javax.management.ObjectName : resin:type=Config,name="file:/opt/resin/resin-video-api4-server/conf/video-api4-server.xml"
	==>[Path] @java.lang.String : /opt/resin/resin-video-api4-server/conf/video-api4-server.xml
	==>[Type] @java.lang.String : Config
	ObjectName : resin:type=Config,Host=default,WebApp=/,name="file:/opt/deploy-web/video-api4-server/WEB-INF/web.xml"
	==>[Crc64] @long : -7921980277871044456
	==>[LastModified] @long : 1408698812000
	==>[Length] @long : 3450
	==>[Name] @java.lang.String : file:/opt/deploy-web/video-api4-server/WEB-INF/web.xml
	==>[ObjectName] @javax.management.ObjectName : resin:type=Config,Host=default,WebApp=/,name="file:/opt/deploy-web/video-api4-server/WEB-INF/web.xml"
	==>[Path] @java.lang.String : /opt/deploy-web/video-api4-server/WEB-INF/web.xml
	==>[Type] @java.lang.String : Config
	ObjectName : resin:type=ClusterServer,name=video-api4-server
	==>[Address] @java.lang.String : 127.0.0.1
	==>[Cluster] @com.caucho.management.server.ClusterMXBean : resin:type=Cluster,name=app-tier
	==>[ClusterIdleTime] @long : 180000
	==>[ClusterIndex] @int : 0
	==>[ClusterState] @java.lang.String : self
	==>[ConnectTimeout] @long : 5000
	==>[ConnectionActiveCount] @int : 0
	==>[ConnectionBusyCountTotal] @long : 0
	==>[ConnectionFailCountTotal] @long : 0
	==>[ConnectionIdleCount] @int : 0
	==>[ConnectionKeepaliveCountTotal] @long : 0
	==>[ConnectionMin] @int : 0
	==>[ConnectionNewCountTotal] @long : 0
	==>[DynamicServer] @boolean : false
	==>[HeartbeatActive] @boolean : true
	==>[HeartbeatState] @java.lang.String : ACTIVE
	==>[IdleTime] @long : 60000
	==>[LastBusyTime] @java.util.Date : null
	==>[LastFailTime] @java.util.Date : null
	==>[LastHeartbeatTime] @java.util.Date : Fri Aug 22 17:14:04 CST 2014
	==>[LatencyFactor] @double : 0.0
	==>[LoadBalanceState] @java.lang.String : self
	==>[MessageQueueLargeSize] @int : -1
	==>[MessageQueueSize] @int : -1
	==>[Name] @java.lang.String : video-api4-server
	==>[ObjectName] @javax.management.ObjectName : resin:type=ClusterServer,name=video-api4-server
	==>[Port] @int : 6806
	==>[RecoverTime] @long : 15000
	==>[SelfServer] @boolean : true
	==>[ServerCpuLoadAvg] @double : 0.0
	==>[SocketTimeout] @long : 600000
	==>[TriadServer] @boolean : true
	==>[Type] @java.lang.String : ClusterServer
	==>[WarmupTime] @long : 60000
	ObjectName : resin:type=Cache,name="resin:system|resin"
	==>[Weight] @int : 100
	==>[Name] @java.lang.String : resin:system|resin
	error unmarshalling return; nested exception is: 
		java.lang.ClassNotFoundException: javax.cache.Status (no security manager: RMI class loader disabled)
	ObjectName : resin:type=Host,name=default
	==>[ClassPath] @[Ljava.lang.String; : [Ljava.lang.String;@4145b02f
	==>[ErrorMessage] @java.lang.String : null
	==>[HostName] @java.lang.String : 
	==>[Id] @java.lang.String : production/host/default
	==>[Name] @java.lang.String : default
	==>[ObjectName] @javax.management.ObjectName : resin:type=Host,name=default
	==>[RedeployCheckInterval] @long : 60000
	==>[RedeployMode] @java.lang.String : DEFAULT
	==>[RepositoryMetaData] @java.util.Map : {}
	==>[RootDirectory] @java.lang.String : /opt/resin/resin-video-api4-server/
	==>[StartTime] @java.util.Date : Fri Aug 22 17:14:15 CST 2014
	==>[StartupMode] @java.lang.String : DEFAULT
	==>[State] @java.lang.String : ACTIVE
	==>[Type] @java.lang.String : Host
	==>[URL] @java.lang.String : http://localhost:8106
	==>[WarDirectory] @java.lang.String : /opt/resin/resin-video-api4-server/webapps
	==>[WarExpandDirectory] @java.lang.String : /opt/resin/resin-video-api4-server/webapps
	==>[WebApps] @[Lcom.caucho.management.server.WebAppMXBean; : [Ljavax.management.ObjectName;@129dd6e2
	ObjectName : resin:type=Environment
	==>[ClassPath] @[Ljava.lang.String; : [Ljava.lang.String;@7befc208
	==>[Name] @java.lang.String : null
	==>[ObjectName] @javax.management.ObjectName : resin:type=Environment
	ObjectName : java.lang:type=OperatingSystem
	==>[Type] @java.lang.String : Environment
	==>[MaxFileDescriptorCount] @long : 65535
	==>[OpenFileDescriptorCount] @long : 318
	==>[CommittedVirtualMemorySize] @long : 5258715136
	==>[FreePhysicalMemorySize] @long : 2090614784
	==>[FreeSwapSpaceSize] @long : 8585371648
	==>[ProcessCpuTime] @long : 180300000000
	==>[TotalPhysicalMemorySize] @long : 25267376128
	==>[TotalSwapSpaceSize] @long : 8587182080
	==>[Name] @java.lang.String : Linux
	==>[AvailableProcessors] @int : 24
	==>[Version] @java.lang.String : 2.6.18-274.el5
	==>[Arch] @java.lang.String : amd64
	==>[SystemLoadAverage] @double : 0.44
	ObjectName : resin:type=Port,name=INADDR_ANY-8106
	==>[AcceptListenBacklog] @int : 4000
	==>[AcceptThreadMax] @int : 64
	==>[AcceptThreadMin] @int : 4
	==>[Address] @java.lang.String : null
	==>[ClientDisconnectCountTotal] @long : 0
	==>[CometIdleCount] @int : 0
	==>[ConnectionMax] @int : 1048576
	==>[JniEnabled] @boolean : false
	==>[KeepaliveConnectionTimeMax] @long : 600000
	==>[KeepaliveCount] @int : 0
	==>[KeepaliveCountTotal] @long : 2
	==>[KeepaliveMax] @int : 65536
	==>[KeepaliveSelectCount] @int : -1
	==>[KeepaliveSelectCountTotal] @long : 0
	==>[KeepaliveSelectMax] @int : -1
	==>[KeepaliveThreadCount] @int : 0
	==>[KeepaliveThreadTimeout] @long : 60000
	==>[KeepaliveTimeout] @long : 120000
	==>[Name] @java.lang.String : INADDR_ANY-8106
	==>[ObjectName] @javax.management.ObjectName : resin:type=Port,name=INADDR_ANY-8106
	==>[Port] @int : 8106
	==>[PortThreadMax] @int : 8192
	==>[ProtocolName] @java.lang.String : http
	==>[ReadBytesTotal] @long : 516
	==>[RequestCountTotal] @long : 3
	==>[RequestTimeTotal] @long : 0
	==>[SSL] @boolean : false
	==>[SocketTimeout] @long : 120000
	==>[State] @java.lang.String : ACTIVE
	==>[SuspendTimeMax] @long : 600000
	==>[TcpKeepalive] @boolean : false
	==>[TcpNoDelay] @boolean : true
	==>[ThreadActiveCount] @int : 0
	==>[ThreadCount] @int : 6
	==>[ThreadIdleCount] @int : 6
	==>[ThreadStartCount] @int : 0
	==>[ThrottleDisconnectCountTotal] @long : 0
	==>[Type] @java.lang.String : Port
	==>[WriteBytesTotal] @long : 3631
	ObjectName : resin:type=Cluster,name=app-tier
	==>[DynamicServerEnable] @boolean : false
	==>[Hosts] @[Lcom.caucho.management.server.HostMXBean; : [Ljavax.management.ObjectName;@1ac7057c
	==>[Name] @java.lang.String : app-tier
	==>[ObjectName] @javax.management.ObjectName : resin:type=Cluster,name=app-tier
	==>[PersistentStore] @com.caucho.management.server.PersistentStoreMXBean : null
	==>[Resin] @com.caucho.management.server.ResinMXBean : resin:type=Resin
	==>[Servers] @[Lcom.caucho.management.server.ClusterServerMXBean; : [Ljavax.management.ObjectName;@5059cbda
	==>[Type] @java.lang.String : Cluster
	ObjectName : java.lang:type=MemoryPool,name=CMS Old Gen
	==>[Name] @java.lang.String : CMS Old Gen
	==>[Type] @java.lang.String : HEAP
	==>[Valid] @boolean : true
	==>[CollectionUsage] @javax.management.openmbean.CompositeData : javax.management.openmbean.CompositeDataSupport(compositeType=javax.management.openmbean.CompositeType(name=java.lang.management.MemoryUsage,items=((itemName=committed,itemType=javax.management.openmbean.SimpleType(name=java.lang.Long)),(itemName=init,itemType=javax.management.openmbean.SimpleType(name=java.lang.Long)),(itemName=max,itemType=javax.management.openmbean.SimpleType(name=java.lang.Long)),(itemName=used,itemType=javax.management.openmbean.SimpleType(name=java.lang.Long)))),contents={committed=3665821696, init=3665821696, max=3665821696, used=17414616})
	==>[CollectionUsageThreshold] @long : 0
	==>[CollectionUsageThresholdCount] @long : 0
	==>[MemoryManagerNames] @[Ljava.lang.String; : [Ljava.lang.String;@1594df96
	==>[PeakUsage] @javax.management.openmbean.CompositeData : javax.management.openmbean.CompositeDataSupport(compositeType=javax.management.openmbean.CompositeType(name=java.lang.management.MemoryUsage,items=((itemName=committed,itemType=javax.management.openmbean.SimpleType(name=java.lang.Long)),(itemName=init,itemType=javax.management.openmbean.SimpleType(name=java.lang.Long)),(itemName=max,itemType=javax.management.openmbean.SimpleType(name=java.lang.Long)),(itemName=used,itemType=javax.management.openmbean.SimpleType(name=java.lang.Long)))),contents={committed=3665821696, init=3665821696, max=3665821696, used=59593472})
	==>[Usage] @javax.management.openmbean.CompositeData : javax.management.openmbean.CompositeDataSupport(compositeType=javax.management.openmbean.CompositeType(name=java.lang.management.MemoryUsage,items=((itemName=committed,itemType=javax.management.openmbean.SimpleType(name=java.lang.Long)),(itemName=init,itemType=javax.management.openmbean.SimpleType(name=java.lang.Long)),(itemName=max,itemType=javax.management.openmbean.SimpleType(name=java.lang.Long)),(itemName=used,itemType=javax.management.openmbean.SimpleType(name=java.lang.Long)))),contents={committed=3665821696, init=3665821696, max=3665821696, used=59593472})
	==>[UsageThreshold] @long : 0
	==>[UsageThresholdCount] @long : 0
	==>[CollectionUsageThresholdExceeded] @boolean : true
	==>[CollectionUsageThresholdSupported] @boolean : true
	==>[UsageThresholdExceeded] @boolean : true
	ObjectName : resin:type=HostDeploy,name=hosts
	==>[UsageThresholdSupported] @boolean : true
	==>[ArchiveDirectory] @java.lang.String : /opt/resin/resin-video-api4-server/hosts
	==>[ConfigException] @java.lang.Throwable : null
	==>[DependencyCheckInterval] @long : 5000
	==>[ExpandDirectory] @java.lang.String : /opt/resin/resin-video-api4-server/hosts
	==>[ExpandPrefix] @java.lang.String : 
	==>[ExpandSuffix] @java.lang.String : 
	==>[Extension] @java.lang.String : .jar
	==>[Modified] @boolean : false
	==>[Name] @java.lang.String : hosts
	==>[Names] @[Ljava.lang.String; : [Ljava.lang.String;@4858cca9
	==>[ObjectName] @javax.management.ObjectName : resin:type=HostDeploy,name=hosts
	==>[RedeployMode] @java.lang.String : AUTOMATIC
	==>[StartupMode] @java.lang.String : AUTOMATIC
	==>[State] @java.lang.String : ACTIVE
	==>[Type] @java.lang.String : HostDeploy
	ObjectName : resin:type=Environment,Host=default,WebApp=/
	==>[ClassPath] @[Ljava.lang.String; : [Ljava.lang.String;@7b603522
	==>[Name] @java.lang.String : null
	==>[ObjectName] @javax.management.ObjectName : resin:type=Environment,Host=default,WebApp=/
	==>[Type] @java.lang.String : Environment
	ObjectName : resin:type=SessionManager,Host=default,WebApp=/resin-admin
	==>[AlwaysSaveSession] @boolean : false
	==>[CookieAppendServerIndex] @boolean : false
	==>[CookieDomain] @java.lang.String : null
	==>[CookieHttpOnly] @boolean : false
	==>[CookieLength] @long : 21
	==>[CookieMaxAge] @long : 0
	==>[CookieName] @java.lang.String : JSESSIONID
	==>[CookiePort] @java.lang.String : null
	==>[CookieSecure] @boolean : false
	==>[CookieVersion] @int : 0
	==>[EnableCookies] @boolean : true
	==>[EnableURLRewriting] @boolean : false
	==>[EstimatedMemorySize] @long : 0
	==>[IgnoreSerializationErrors] @boolean : true
	==>[InvalidateAfterListener] @boolean : false
	==>[Name] @java.lang.String : null
	==>[ObjectName] @javax.management.ObjectName : resin:type=SessionManager,Host=default,WebApp=/resin-admin
	==>[PersistentStore] @com.caucho.management.server.PersistentStoreMXBean : null
	==>[ReuseSessionId] @boolean : true
	==>[SaveMode] @java.lang.String : after-request
	==>[SessionActiveCount] @long : 0
	==>[SessionCreateCountTotal] @long : 0
	==>[SessionInvalidateCountTotal] @long : 0
	==>[SessionMax] @int : 8192
	==>[SessionTimeout] @long : 1800000
	==>[SessionTimeoutCountTotal] @long : 0
	==>[Type] @java.lang.String : SessionManager
	ObjectName : resin:type=Config,Host=default,WebApp=/resin-admin,name="file:/opt/resin/resin-video-api4-server/doc/admin/WEB-INF/resin-web.xml"
	==>[WebApp] @com.caucho.management.server.WebAppMXBean : resin:type=WebApp,Host=default,name=/resin-admin
	==>[Crc64] @long : 1409730931869584434
	==>[LastModified] @long : 1367015617000
	==>[Length] @long : 1409
	==>[Name] @java.lang.String : file:/opt/resin/resin-video-api4-server/doc/admin/WEB-INF/resin-web.xml
	==>[ObjectName] @javax.management.ObjectName : resin:type=Config,Host=default,WebApp=/resin-admin,name="file:/opt/resin/resin-video-api4-server/doc/admin/WEB-INF/resin-web.xml"
	==>[Path] @java.lang.String : /opt/resin/resin-video-api4-server/doc/admin/WEB-INF/resin-web.xml
	==>[Type] @java.lang.String : Config
	ObjectName : resin:type=AccessLog,Host=default
	==>[Name] @java.lang.String : null
	==>[ObjectName] @javax.management.ObjectName : resin:type=AccessLog,Host=default
	==>[Type] @java.lang.String : AccessLog
	ObjectName : java.lang:type=ClassLoading
	==>[LoadedClassCount] @int : 10367
	==>[UnloadedClassCount] @long : 0
	==>[TotalLoadedClassCount] @long : 10367
	==>[Verbose] @boolean : false
	ObjectName : java.lang:type=Threading
	==>[ThreadAllocatedMemoryEnabled] @boolean : true
	==>[ThreadAllocatedMemorySupported] @boolean : true
	==>[ThreadContentionMonitoringEnabled] @boolean : false
	==>[DaemonThreadCount] @int : 144
	==>[PeakThreadCount] @int : 194
	==>[CurrentThreadCpuTimeSupported] @boolean : true
	==>[ObjectMonitorUsageSupported] @boolean : true
	==>[SynchronizerUsageSupported] @boolean : true
	==>[ThreadContentionMonitoringSupported] @boolean : true
	==>[ThreadCpuTimeEnabled] @boolean : true
	==>[AllThreadIds] @[J : [J@75cefde4
	==>[CurrentThreadCpuTime] @long : 230000000
	==>[CurrentThreadUserTime] @long : 220000000
	==>[ThreadCount] @int : 193
	==>[TotalStartedThreadCount] @long : 3479
	==>[ThreadCpuTimeSupported] @boolean : true
	ObjectName : java.util.logging:type=Logging
	==>[LoggerNames] @[Ljava.lang.String; : [Ljava.lang.String;@2aa937cd
	ObjectName : resin:type=WebApp,Host=default,name=/
	==>[ClassPath] @[Ljava.lang.String; : [Ljava.lang.String;@2e19fc25
	==>[ClientDisconnectCountTotal] @long : 0
	==>[Configs] @[Lcom.caucho.management.server.ConfigMXBean; : [Ljavax.management.ObjectName;@2c1533c8
	==>[ContextPath] @java.lang.String : 
	==>[Enabled] @boolean : true
	==>[ErrorMessage] @java.lang.String : null
	==>[Host] @com.caucho.management.server.HostMXBean : resin:type=Host,name=default
	==>[Id] @java.lang.String : production/webapp/default/ROOT
	==>[ManifestAttributes] @java.util.Map : {Archiver-Version=Plexus Archiver, Build-Jdk=1.6.0_29, Built-By=root, Created-By=Apache Maven, Manifest-Version=1.0}
	==>[Name] @java.lang.String : /
	==>[ObjectName] @javax.management.ObjectName : resin:type=WebApp,Host=default,name=/
	==>[RedeployCheckInterval] @long : 60000
	==>[RedeployMode] @java.lang.String : DEFAULT
	==>[RepositoryMetaData] @java.util.Map : {}
	==>[RequestCount] @int : 0
	==>[RequestCountTotal] @long : 0
	==>[RequestReadBytesTotal] @long : 0
	==>[RequestTimeTotal] @long : 0
	==>[RequestWriteBytesTotal] @long : 0
	==>[RootDirectory] @java.lang.String : /opt/deploy-web/video-api4-server
	==>[SessionManager] @com.caucho.management.server.SessionManagerMXBean : resin:type=SessionManager,Host=default,WebApp=/
	==>[StartTime] @java.util.Date : Fri Aug 22 17:14:15 CST 2014
	==>[StartupMode] @java.lang.String : DEFAULT
	==>[State] @java.lang.String : ACTIVE
	==>[Status500CountTotal] @long : 0
	==>[Status500LastTime] @java.util.Date : null
	==>[Type] @java.lang.String : WebApp
	==>[Version] @java.lang.String : 
	ObjectName : resin:type=Cache,Host=default,WebApp=/resin-admin,name="resin:session|Resin"
	==>[Name] @java.lang.String : resin:session|Resin
	error unmarshalling return; nested exception is: 
		java.lang.ClassNotFoundException: javax.cache.Status (no security manager: RMI class loader disabled)
	ObjectName : com.sun.management:type=HotSpotDiagnostic
	==>[DiagnosticOptions] @[Ljavax.management.openmbean.CompositeData; : [Ljavax.management.openmbean.CompositeData;@32486cdd
	ObjectName : com.mchange.v2.c3p0:type=PooledDataSource[2saeu7931y73aicgvk8cr|b56efe]
	==>[overrideDefaultPassword] @java.lang.String : null
	==>[statementCacheNumConnectionsWithCachedStatementsAllUsers] @int : 2
	==>[dataSourceName] @java.lang.String : 2saeu7931y73aicgvk8cr|b56efe
	==>[threadPoolNumIdleThreads] @int : 10
	==>[acquireRetryAttempts] @int : 30
	==>[autoCommitOnClose] @boolean : false
	==>[identityToken] @java.lang.String : 2saeu7931y73aicgvk8cr|b56efe
	==>[forceIgnoreUnresolvedTransactions] @boolean : false
	==>[startTimeMillisDefaultUser] @long : 1408698850109
	==>[breakAfterAcquireFailure] @boolean : false
	==>[idleConnectionTestPeriod] @int : 900
	==>[maxPoolSize] @int : 10
	==>[user] @java.lang.String : mvrs_api
	==>[numIdleConnectionsDefaultUser] @int : 5
	==>[numFailedCheckoutsDefaultUser] @long : 0
	==>[numBusyConnections] @int : 0
	==>[numConnections] @int : 5
	==>[connectionCustomizerClassName] @java.lang.String : null
	==>[automaticTestTable] @java.lang.String : null
	==>[numFailedIdleTestsDefaultUser] @long : 0
	==>[statementCacheNumStatementsDefaultUser] @int : 3
	==>[effectivePropertyCycleDefaultUser] @float : 900.0
	==>[maxIdleTime] @int : 600000
	==>[numBusyConnectionsDefaultUser] @int : 0
	==>[numConnectionsAllUsers] @int : 5
	==>[maxIdleTimeExcessConnections] @int : 0
	==>[checkoutTimeout] @int : 0
	==>[allUsers] @java.util.Collection : [mvrs_api]
	==>[statementCacheNumConnectionsWithCachedStatementsDefaultUser] @int : 2
	==>[usesTraditionalReflectiveProxies] @boolean : false
	==>[maxAdministrativeTaskTime] @int : 0
	==>[userOverridesAsString] @java.lang.String : null
	==>[driverClass] @java.lang.String : com.mysql.jdbc.Driver
	==>[connectionTesterClassName] @java.lang.String : com.mchange.v2.c3p0.impl.DefaultConnectionTester
	==>[maxStatements] @int : 100
	==>[statementCacheNumCheckedOutDefaultUser] @int : 0
	==>[statementCacheNumStatementsAllUsers] @int : 3
	==>[numUnclosedOrphanedConnectionsDefaultUser] @int : 0
	==>[debugUnreturnedConnectionStackTraces] @boolean : false
	==>[testConnectionOnCheckout] @boolean : false
	==>[numConnectionsDefaultUser] @int : 5
	==>[acquireRetryDelay] @int : 1000
	==>[overrideDefaultUser] @java.lang.String : null
	==>[threadPoolSize] @int : 10
	==>[threadPoolNumTasksPending] @int : 0
	==>[unreturnedConnectionTimeout] @int : 0
	==>[acquireIncrement] @int : 1
	==>[numThreadsAwaitingCheckoutDefaultUser] @int : 0
	==>[threadPoolNumActiveThreads] @int : 0
	==>[minPoolSize] @int : 5
	==>[maxStatementsPerConnection] @int : 0
	==>[statementCacheNumCheckedOutStatementsAllUsers] @int : 0
	==>[testConnectionOnCheckin] @boolean : false
	==>[numUserPools] @int : 1
	==>[numFailedCheckinsDefaultUser] @long : 0
	==>[numUnclosedOrphanedConnectionsAllUsers] @int : 0
	==>[initialPoolSize] @int : 5
	==>[upTimeMillisDefaultUser] @long : 433869765
	==>[numUnclosedOrphanedConnections] @int : 0
	==>[properties] @java.util.Properties : {password=******, user=******}
	==>[maxConnectionAge] @int : 0
	==>[loginTimeout] @int : 0
	==>[numIdleConnectionsAllUsers] @int : 5
	==>[factoryClassLocation] @java.lang.String : null
	==>[jdbcUrl] @java.lang.String : jdbc:mysql://mvrs.db.tv.sohu.com:3307/mvrs?useUnicode=true&amp;characterEncoding=UTF-8&amp;autoReconnect=true&amp;zeroDateTimeBehavior=convertToNull&amp;connectTimeout=5000&amp;socketTimeout=5000
	==>[propertyCycle] @int : 0
	==>[numBusyConnectionsAllUsers] @int : 0
	==>[numIdleConnections] @int : 5
	==>[numHelperThreads] @int : 10
	==>[preferredTestQuery] @java.lang.String : null
	==>[description] @java.lang.String : null
	ObjectName : resin:type=Resin
	==>[Clusters] @[Lcom.caucho.management.server.ClusterMXBean; : [Ljavax.management.ObjectName;@1e8809ce
	==>[ConfigDirectory] @java.lang.String : /opt/resin/resin-video-api4-server/conf
	==>[ConfigFile] @java.lang.String : /opt/resin/resin-video-api4-server/conf/video-api4-server.xml
	==>[Configs] @[Lcom.caucho.management.server.ConfigMXBean; : [Ljavax.management.ObjectName;@3eae3da8
	==>[DataDirectory] @java.lang.String : /opt/resin/resin-video-api4-server/resin-data
	==>[LocalHost] @java.lang.String : 10_10_77_159
	==>[LogDirectory] @java.lang.String : /opt/resin/resin-video-api4-server/log
	==>[Name] @java.lang.String : null
	==>[ObjectName] @javax.management.ObjectName : resin:type=Resin
	==>[Professional] @boolean : false
	==>[ResinHome] @java.lang.String : /opt/resin/resin-video-api4-server/
	==>[Restart] @boolean : false
	==>[RootDirectory] @java.lang.String : /opt/resin/resin-video-api4-server/
	==>[Server] @com.caucho.management.server.ServerMXBean : resin:type=Server
	==>[Type] @java.lang.String : Resin
	==>[UserName] @java.lang.String : root
	==>[Version] @java.lang.String : Resin-4.0.36 (built Fri, 26 Apr 2013 03:31:59 PDT)
	==>[WatchdogStartMessage] @java.lang.String : user start from watchdog
	ObjectName : resin:type=Port,name=INADDR_ANY-9906
	==>[AcceptListenBacklog] @int : 4000
	==>[AcceptThreadMax] @int : 64
	==>[AcceptThreadMin] @int : 4
	==>[Address] @java.lang.String : null
	==>[ClientDisconnectCountTotal] @long : 0
	==>[CometIdleCount] @int : 0
	==>[ConnectionMax] @int : 1048576
	==>[JniEnabled] @boolean : false
	==>[KeepaliveConnectionTimeMax] @long : 600000
	==>[KeepaliveCount] @int : 0
	==>[KeepaliveCountTotal] @long : 0
	==>[KeepaliveMax] @int : 65536
	==>[KeepaliveSelectCount] @int : -1
	==>[KeepaliveSelectCountTotal] @long : 0
	==>[KeepaliveSelectMax] @int : -1
	==>[KeepaliveThreadCount] @int : 0
	==>[KeepaliveThreadTimeout] @long : 60000
	==>[KeepaliveTimeout] @long : 120000
	==>[Name] @java.lang.String : INADDR_ANY-9906
	==>[ObjectName] @javax.management.ObjectName : resin:type=Port,name=INADDR_ANY-9906
	==>[Port] @int : 9906
	==>[PortThreadMax] @int : 8192
	==>[ProtocolName] @java.lang.String : http
	==>[ReadBytesTotal] @long : 0
	==>[RequestCountTotal] @long : 0
	==>[RequestTimeTotal] @long : 0
	==>[SSL] @boolean : true
	==>[SocketTimeout] @long : 120000
	==>[State] @java.lang.String : ACTIVE
	==>[SuspendTimeMax] @long : 600000
	==>[TcpKeepalive] @boolean : false
	==>[TcpNoDelay] @boolean : true
	==>[ThreadActiveCount] @int : 0
	==>[ThreadCount] @int : 4
	==>[ThreadIdleCount] @int : 4
	==>[ThreadStartCount] @int : 0
	==>[ThrottleDisconnectCountTotal] @long : 0
	==>[Type] @java.lang.String : Port
	ObjectName : resin:type=Server
	==>[WriteBytesTotal] @long : 0
	==>[BindPortsAfterStart] @boolean : true
	==>[ClientDisconnectCountTotal] @long : 0
	==>[Cluster] @com.caucho.management.server.ClusterMXBean : resin:type=Cluster,name=app-tier
	==>[ClusterPort] @com.caucho.management.server.PortMXBean : null
	==>[CpuLoadAvg] @double : 0.0
	==>[CurrentTime] @java.util.Date : Wed Aug 27 17:45:20 CST 2014
	==>[DetailedStatistics] @boolean : false
	==>[DevelopmentModeErrorPage] @boolean : true
	==>[Environment] @com.caucho.management.server.EnvironmentMXBean : resin:type=Environment
	==>[HeaderCountMax] @int : 256
	==>[HeaderSizeMax] @int : 16384
	==>[Id] @java.lang.String : video-api4-server
	==>[InvocationCacheHitCountTotal] @long : 0
	==>[InvocationCacheMissCountTotal] @long : 4
	==>[KeepaliveCountTotal] @long : -1
	==>[MemoryFreeMin] @long : 0
	==>[Name] @java.lang.String : null
	==>[ObjectName] @javax.management.ObjectName : resin:type=Server
	==>[PermGenFreeMin] @long : 0
	==>[Ports] @[Lcom.caucho.management.server.PortMXBean; : [Ljavax.management.ObjectName;@256f8834
	==>[RequestCountTotal] @long : 3
	==>[RequestReadBytesTotal] @long : -1
	==>[RequestTimeTotal] @long : -1
	==>[RequestWriteBytesTotal] @long : 0
	==>[RuntimeMemory] @long : 4232052736
	==>[RuntimeMemoryFree] @long : 4063805096
	==>[SelectKeepaliveCount] @int : 0
	==>[SelectManagerEnabled] @boolean : false
	==>[SelfServer] @com.caucho.management.server.ClusterServerMXBean : resin:type=ClusterServer,name=video-api4-server
	==>[SendfileCountTotal] @long : 0
	==>[ServerHeader] @java.lang.String : Resin/4.0.36
	==>[ServerIndex] @int : 0
	==>[ShutdownWaitMax] @long : 60000
	==>[Stage] @java.lang.String : production
	==>[StartTime] @java.util.Date : Fri Aug 22 17:14:04 CST 2014
	==>[State] @java.lang.String : ACTIVE
	==>[ThreadActiveCount] @int : 0
	==>[ThreadKeepaliveCount] @int : 0
	==>[ThreadPool] @com.caucho.management.server.ThreadPoolMXBean : resin:type=ThreadPool
	==>[Type] @java.lang.String : Server
	==>[Uptime] @long : 433875961
	==>[UrlLengthMax] @int : 8192
	ObjectName : JMImplementation:type=MBeanServerDelegate
	==>[ImplementationName] @java.lang.String : Resin-JMX
	==>[ImplementationVendor] @java.lang.String : Caucho Technology
	==>[ImplementationVersion] @java.lang.String : Resin-4.0.36
	==>[MBeanServerId] @java.lang.String : Resin-JMX
	==>[SpecificationName] @java.lang.String : Java Management Extensions
	==>[SpecificationVendor] @java.lang.String : Sun Microsystems
	==>[SpecificationVersion] @java.lang.String : 1.4
	committed: 4232052736k
	init: 4294967296k
	max: 4232052736k
	used: 168479760k
	堆内存总量:4132864KB,当前分配量:4132864KB,当前使用:164531KB,堆内存使用率:3.9810412%
	栈内存使用率:61.165768%
	perm committed: 268435456
	perm init: 268435456
	perm max: 268435456
	perm used: 77781152
	perm 使用量:28.975737%
**/
}
