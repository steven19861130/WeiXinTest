package com.wecharttest.servlet;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.wecharttest.controller.MessageController;
import com.wecharttest.handler.event.UserUnSubscribeHandler;
import com.wecharttest.util.SignUtil;

/**
 * Servlet implementation class MainServlet
 */
@WebServlet(name = "MainServlet", urlPatterns = { "/MainServlet" })
public class MainServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private Logger log = Logger.getLogger(MainServlet.class);


	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public MainServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		response.setCharacterEncoding("UTF-8");
		request.setCharacterEncoding("UTF-8");

		String signature = request.getParameter("signature");
		String timestamp = request.getParameter("timestamp");
		String nonce = request.getParameter("nonce");
		String echostr = request.getParameter("echostr");
        String action = request.getParameter("action");
		
		PrintWriter out = response.getWriter();
		if (echostr != null && !echostr.isEmpty()) {
			if (SignUtil.checkSignature(signature, timestamp, nonce)) {
				out.print(echostr);
			}
		} else if(action == null || action.isEmpty()){
			try{
			InputStream is = request.getInputStream();
			MessageController push = new MessageController(is);
			String getXml = push.parseXml();
			log.info("getXml:" + getXml);
			out.print(getXml);
			}catch(Exception e){
				e.printStackTrace();
			}
			
			
		}
		
		else{
			
		}

		out.close();
		out = null;
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
