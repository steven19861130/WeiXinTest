package com.wecharttest.util;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.output.XMLOutputter;

import com.wecharttest.message.ReceiveBaseMessage;
import com.wecharttest.message.ReceiveTextMessage;
import com.wecharttest.message.ReturnBaseMessage;
import com.wecharttest.message.ReturnTextMessage;

public class MessageToXMLUtil {
  
  public static String strToTextXml(ReturnBaseMessage bm){
    String type = bm.getMsgType();
    String returnStr = "";  
        
    SimpleDateFormat format=new SimpleDateFormat("yyyyMMddHHmmss");  
    String times = format.format(new Date());  
      
    Element rootXML = new Element("xml");  
    if(type.equalsIgnoreCase("text")){
      textXML(rootXML,bm,times);
    }

    Document doc = new Document(rootXML);   
      
    XMLOutputter XMLOut = new XMLOutputter();    
    returnStr = XMLOut.outputString(doc);  

    return returnStr;  
    
  }
  
  private static Element textXML(Element rootXML,ReturnBaseMessage bm,String times){
    ReturnTextMessage tm = (ReturnTextMessage)bm;
    rootXML.addContent(new Element("ToUserName").setText(tm.getFromUser()));  
    rootXML.addContent(new Element("FromUserName").setText(tm.getToUser()));  
    rootXML.addContent(new Element("CreateTime").setText(times));  
    rootXML.addContent(new Element("MsgType").setText("text"));  
    rootXML.addContent(new Element("Content").setText(tm.getMsgContent()));    
    return rootXML;
  }
  
  
  
}
