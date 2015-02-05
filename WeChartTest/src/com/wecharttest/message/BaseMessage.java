package com.wecharttest.message;

public class BaseMessage {
  
  private String toUser;
  
  private String fromUser;
  
  private long CreateTime;
  
  private String msgType;
  
  private long msgId;

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

  public long getMsgId() {
    return msgId;
  }

  public void setMsgId(long msgId) {
    this.msgId = msgId;
  }
  
  public BaseMessage parseMessage(){
    return this;
  }
  
}
