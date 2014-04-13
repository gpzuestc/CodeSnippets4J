package com.gpzuestc.framework.jedis;

import java.io.Serializable;
import java.util.Date;

/**
 * Description  
 * @author guopengzhang@sohu-inc.com @2012-10-8
 *
 */
public class Msg implements Serializable{
	private long id;
	private long suid;
	private long ruid;
	private String content;
	private Date date;
	private String title;
	private long replyNum;
	
	public Msg(long id) {
		this.id = id;
		this.suid = 1111111;
		this.ruid = 2222222;
		this.content = id + ":大部分小型摄像头（包括这几代iPhone）在拍摄离光光源时，在图片边缘会出现光圈。出现光圈是因为拍摄时的光源角度问题，因此会在摄像机模块和相机传感器上形成倒影。用户稍微移动摄像头的位置或者用手来稍微挡住镜头，这样就可以最小化或者消除上述问题。";
		this.date = new Date();
		this.title = id + ":苹果公开回应iPhone 5拍照现紫色光圈问题";
		this.replyNum = 0;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getSuid() {
		return suid;
	}

	public void setSuid(long suid) {
		this.suid = suid;
	}

	public long getRuid() {
		return ruid;
	}

	public void setRuid(long ruid) {
		this.ruid = ruid;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public long getReplyNum() {
		return replyNum;
	}

	public void setReplyNum(long replyNum) {
		this.replyNum = replyNum;
	}
	
}
