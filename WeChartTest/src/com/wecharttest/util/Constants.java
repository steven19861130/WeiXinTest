package com.wecharttest.util;

public interface Constants {
  
  public static String EVENT = "event";
  
  public static String TEXT = "text";
  public static String IMAGE = "image";
  
  
  public static String WELCOMERESUBCRIBEMSG = "欢迎您再次订阅Steven私房菜微信公共平台。\n1.查看菜单请输入<菜单>。 " +
  "\n2.点菜请输入<点菜>。\n3.点菜结束请输入<点菜结束>";
  
  public static String WELCOMESUBCRIBEMSG = "欢迎您订阅Steven私房菜微信公共平台。\n1.查看菜单请输入<菜单>。 " +
  "\n2.点菜请输入<点菜>。\n3.点菜结束请输入<点菜结束>";
  
  public static String ORDERMSG = "请根据菜单菜品，按照'我要吃：鱼香肉丝1,青椒肉丝2，番茄鸡蛋汤1'格式发送消息,其中数字代表份数";
  
  public static String ORDERFINISHMSG = "您还需要点菜吗？如果是，请继续输入菜品\n 如果您点菜结束，请输入“点菜结束”完成本次点菜";
  
  public static String INPUTERR = "您输入的格式有问题，";
  
  public static String NOTORDERMSG = "您还没有点菜，请参考菜单点菜";
  
  public static String NOEXISTDISH = "您输入点菜信息无法识别，可能因为：" +"\n1.您输入的菜品不存在，请查看菜单。\n2.";
  
  public static String USERERORMSG = "用户信息有误，请联系管理员";
}
