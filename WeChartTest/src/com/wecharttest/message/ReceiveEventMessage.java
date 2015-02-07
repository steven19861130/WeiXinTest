package com.wecharttest.message;

import java.util.List;

import org.jdom2.Document;
import org.jdom2.Element;

import com.wecharttest.util.Constants;

public class ReceiveEventMessage extends ReceiveBaseMessage {
  
  private String msgContent;

  private Document doc;
  
  private String event;
  
  public ReceiveEventMessage(Document doc){
    this.doc = doc;
    this.setMsgType(Constants.EVENT);
  }

  public String getEvent() {
    return event;
  }

  public void setEvent(String event) {
    this.event = event;
  }
  
  @Override
  public ReceiveBaseMessage parseMessage() {
    Element root = doc.getRootElement();  
    
    List<Element> list = root.getChildren();  
    for(int j=0;j<list.size();j++){  
        Element first = (Element) list.get(j);  
        if(first.getName().equalsIgnoreCase("ToUserName")){  
            this.setToUser(first.getValue().trim());  
        }else if(first.getName().equalsIgnoreCase("FromUserName")){  
            this.setFromUser(first.getValue().trim());  
        }else if(first.getName().equalsIgnoreCase("MsgId")){  
            this.setMsgId(Long.parseLong(first.getValue().trim()));  
        }else if(first.getName().equalsIgnoreCase("CreateTime")){  
            this.setCreateTime(Long.parseLong(first.getValue().trim()));  
        }else if(first.getName().equalsIgnoreCase("Event")){
            this.setEvent(first.getValue().trim());
        }
    }  
    return this;    
  }
}
