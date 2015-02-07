package com.wecharttest.message;

public class ReturnBaseMessage {

	private String toUser;
	  
	  private String fromUser;
	  
	  private long CreateTime;
	  
	  private String msgType;

	public String getToUser() {
		return toUser;
	}

	public void setToUser(String toUser) {
		this.toUser = toUser;
	}

	public String getFromUser() {
		return fromUser;
	}

	public void setFromUser(String fromUser) {
		this.fromUser = fromUser;
	}

	public long getCreateTime() {
		return CreateTime;
	}

	public void setCreateTime(long createTime) {
		CreateTime = createTime;
	}

	public String getMsgType() {
		return msgType;
	}

	public void setMsgType(String msgType) {
		this.msgType = msgType;
	}
	  
	 public ReturnBaseMessage parseMessage(){
		    return this;
		  }
		  
	  
}
