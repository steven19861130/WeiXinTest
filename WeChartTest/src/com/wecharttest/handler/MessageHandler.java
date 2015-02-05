package com.wecharttest.handler;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;

import com.wecharttest.message.TextMessage;
import com.wecharttest.util.*;

public class MessageHandler {
  
  private InputStream is;
  
  private String returnStr;
  
  public MessageHandler(InputStream is){
    this.is = is;
  }
  
  public String parseXml(){
    String type = null;
    try {  
          
        SAXBuilder sax = new SAXBuilder();   
        Document doc = sax.build(is);  
        Element root = doc.getRootElement();  
                  
        List list = root.getChildren();  
        for(int j=0;j<list.size();j++){  
            Element first = (Element) list.get(j);  
            if(first.getName().equals("MsgType")){  
                type = first.getValue().trim();  
                break;
            }
        }
     if(type!=null){
       if(type.equalsIgnoreCase(Constants.TEXT)){
         TextMessage tm = (TextMessage) new TextMessage(doc).parseMessage();
         returnStr = new TextMessageHandler(tm).returnMessage();
       }
     }
        
    } catch (IOException e) {  
        e.printStackTrace();  
    } catch (JDOMException e) {
      e.printStackTrace();
    }  
      
   
   
    return returnStr;      
  }
  
}
