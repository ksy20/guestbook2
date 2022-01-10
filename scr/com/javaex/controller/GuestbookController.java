package com.javaex.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.javaex.dao.GuestBookDao;
import com.javaex.vo.GuestBookVo;

@WebServlet("/Gbc")
public class GuestbookController extends HttpServlet {
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("GuestbookController");
		
		String act = request.getParameter("action");
		System.out.println(act);
		
		if("addList".equals(act)){
			GuestBookDao guestDao = new GuestBookDao();
			List<GuestBookVo> guestList = guestDao.getList();
			
			System.out.println(guestList);
			
			
			request.setAttribute("pList", guestList);
			
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/addList.jsp");
			rd.forward(request, response);
		}else if ("add".equals(act)) {
			//파라미터 3개를 꺼내온다 
			String name = request.getParameter("name");
			String password = request.getParameter("password");
			String content = request.getParameter("content");
				
			//vo로 만든다
			GuestBookVo guestVo = new GuestBookVo(name, password, content);
			//System.out.println(personVo);
				
			//dao 메모리에 올린다
			GuestBookDao guestDao = new GuestBookDao();
				
			//dao.insert(vo) 에 넣어준다
			guestDao.insert(guestVo);
			
			//리다이렉트
			response.sendRedirect("/phoneBook2/Pbc?action=addList");
		}else if ("deleteForm".equals(act)) {
			int no = Integer.parseInt(request.getParameter("no"));
			
			request.setAttribute("no", no);
			
			
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/deleteForm.jsp");
			rd.forward(request, response);
			
		} else if ("delete".equals(act)) {
			//파라미터 3개를 꺼내온다 
			int no = Integer.parseInt(request.getParameter("no"));
			String password = request.getParameter("password");
			
			//dao 메모리에 올린다
			GuestBookDao guestDao = new GuestBookDao();
			
			//delete
			guestDao.delete(no, password);
			
			//리다이렉트
			response.sendRedirect("/guestbook2/gbc?action=addList");
		} else {
			System.out.println("파라미터 값 없음");
		}
	
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		doGet(request, response);
	}

}
