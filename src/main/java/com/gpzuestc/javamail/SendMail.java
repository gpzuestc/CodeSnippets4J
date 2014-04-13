package com.gpzuestc.javamail;

//JAVAMAIL发送邮件(运行前先修改密码)
//发送邮件:
//    1.创建一个邮件会话(MailSession)实例;
//    2.使用邮件会话创建一个邮件消息（Message）实例；
//    3.设置邮件消息的内容；
//    4.使用Tansport.send()方法发送邮件。
/**
 * <p>Title: JavaMail</p>
 * <p>Description: 发电子邮件</p>
 * <p>Company: </p>
 * @author Derek.G
 * @version 1.0
 */
import java.util.Date;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.Address;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

public class SendMail {
	private static final String MAILBODY = "<meta http-equiv=Content-Type content=text/html; charset=gb2312>"
					+ "<div align=center>JAVAMAIL发送邮件测试...</div>";
	private static final String DOMAIN = "163.com";
	private static final String USERNAME = "zhangguopeng19870";
	private static final String SEND_TO = "guopeng.zhang2010@gmail.com";
	private static final String PASSWORD = "***";
	
	private MimeMessage mimeMsg; // MIME邮件对象
	private Session session; // 邮件会话对象
	private Properties props; // 系统属性
	private String username = ""; // smtp认证用户名和密码
	private String password = "";
	private Multipart mp; // Multipart对象,邮件内容,标题,附件等内容均添加到其中后再生成MimeMessage对象
	private boolean auth = false; // smtp验证与否
	
	
	
	public SendMail(String smtp) {
		setSmtpHost(smtp);
		createMimeMessage();
	}

	/**
	 * 创建系统属性Properties
	 * 
	 * @param hostName
	 *            String
	 */
	public void setSmtpHost(String hostName) {
		System.out.println("设置系统属性：mail.smtp.host = " + hostName);
		if (props == null)
			props = System.getProperties(); // 获得系统属性对象
		props.put("mail.smtp.host", hostName); // 设置SMTP主机
	}

	/**
	 * 创建邮件会话对象Session、MIME邮件对象MimeMessage
	 * 
	 * @return boolean
	 */
	public boolean createMimeMessage() {
		try {
			System.out.println("准备获取邮件会话对象！");
			if (auth) {
				PopupAuthenticator popupAuthenticator = new PopupAuthenticator();// 验证类
				PasswordAuthentication pop = popupAuthenticator.performCheck(
						username, password);
				session = Session.getInstance(props, popupAuthenticator);
			} else {
				session = Session.getInstance(props, null); // 获得邮件会话对象
			}
		} catch (Exception e) {
			System.err.println("获取邮件会话对象时发生错误！" + e);
			return false;
		}
		System.out.println("准备创建MIME邮件对象！");
		try {
			mimeMsg = new MimeMessage(session); // 创建MIME邮件对象
			mp = new MimeMultipart();
			return true;
		} catch (Exception e) {
			System.err.println("创建MIME邮件对象失败！" + e);
			return false;
		}
	}

	/**
	 * 设置smtp身份认证
	 * 
	 * @param need
	 *            boolean
	 */
	public void setNeedAuth(boolean need) {
		System.out.println("设置smtp身份认证：mail.smtp.auth = " + need);
		if (props == null)
			props = System.getProperties();
		if (need) {
			props.put("mail.smtp.auth", "true");
			auth = true;
		} else
			props.put("mail.smtp.auth", "false");
	}

	/**
	 * 设置用户名密码
	 * 
	 * @param name
	 *            String
	 * @param pass
	 *            String
	 */
	public void setNamePass(String name, String pass) {
		username = name;
		password = pass;
	}

	/**
	 * 设置邮件主题
	 * 
	 * @param mailSubject
	 *            String
	 * @return boolean
	 */
	public boolean setSubject(String mailSubject) {
		System.out.println("设置邮件主题！");
		try {
			mimeMsg.setSubject(mailSubject);
			return true;
		} catch (Exception e) {
			System.err.println("设置邮件主题发生错误！");
			return false;
		}
	}

	/**
	 * 设置邮件体文本内容
	 * 
	 * @param mailBody
	 *            String
	 */
	public boolean setBody(String mailBody) {
		try {
			BodyPart bp = new MimeBodyPart();
			bp.setContent(
					"<meta http-equiv=Content-Type content=text/html; charset=gb2312>"
							+ mailBody, "text/html;charset=GB2312");
			mp.addBodyPart(bp);
			return true;
		} catch (Exception e) {
			System.err.println("设置邮件正文时发生错误！" + e);
			return false;
		}
	}

	/**
	 * 设置邮件附件
	 * 
	 * @param filename
	 *            String
	 * @return boolean
	 */
	public boolean addFileAffix(String filename) {

		System.out.println("增加邮件附件：" + filename);

		try {
			BodyPart bp = new MimeBodyPart();
			FileDataSource fileds = new FileDataSource(filename);
			bp.setDataHandler(new DataHandler(fileds));
			bp.setFileName(fileds.getName());
			mp.addBodyPart(bp);
			return true;
		} catch (Exception e) {
			System.err.println("增加邮件附件：" + filename + "发生错误！" + e);
			return false;
		}
	}

	/**
	 * 设置发信人
	 * 
	 * @param from
	 *            String
	 * @return boolean
	 */
	public boolean setFrom(String from) {
		System.out.println("发信人:" + from);
		try {
			mimeMsg.setFrom(new InternetAddress(from)); // 设置发信人
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	/**
	 * 设置收件人
	 * 
	 * @param to
	 *            String
	 * @return boolean
	 */
	public boolean setTo(String to) {
		System.out.println("收件人:" + to);
		if (to == null)
			return false;
		try {
			mimeMsg.setRecipients(Message.RecipientType.TO, InternetAddress
					.parse(to));
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	/**
	 * @param copyto
	 *            String
	 * @return boolean
	 */
	public boolean setCopyTo(String copyto) {
		if (copyto == null)
			return false;
		try {
			mimeMsg.setRecipients(Message.RecipientType.CC,
					(Address[]) InternetAddress.parse(copyto));
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	/**
	 * 发邮件操作
	 * 
	 * @return boolean
	 */
	public boolean sendout() {
		try {
			mimeMsg.setContent(mp);
			mimeMsg.saveChanges();
			System.out.println("正在发送邮件.");
			mimeMsg.setSentDate(new Date());
			Transport transport = session.getTransport("smtp");
			transport.connect((String) props.get("mail.smtp.host"), username,
					password);
			transport.sendMessage(mimeMsg, mimeMsg
					.getRecipients(Message.RecipientType.TO));
			transport.close();
			// Transport.send(mimeMsg);
		} catch (MessagingException ex) {
			System.err.println("邮件发送失败！" + ex.getMessage());
			return false;
		}
		System.out.println("邮件发送完成！");
		return true;
	}

	/**
	 * Just do it as this
	 */
	public static void main(String[] args) {
		String mailbody = MAILBODY;
		SendMail fromMail = new SendMail("smtp." + DOMAIN);
		
		fromMail.setNamePass(USERNAME, PASSWORD); // 登陆帐号密码
		fromMail.setNeedAuth(true);// 设置SMTP是否验证
		if (fromMail.setSubject("JavaMailTest") == false)
			return; // 设置标题
		if (fromMail.setBody(mailbody) == false)
			return; // 设置邮件体
		if (fromMail.setTo(SEND_TO) == false)
			return; // 设置收件人
		if (fromMail.setFrom(USERNAME + "@" + DOMAIN) == false)
			return; // 设置发件人
		if (fromMail.addFileAffix("D:\\Q.jpg") == false)
			return; // 设置附件
//		if (fromMail.addFileAffix("c:\\tmuninst.ini") == false)
//			return;
		if (fromMail.sendout() == false)
			return; // 发出邮件
	}
}
