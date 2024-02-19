package com.javaex.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.javaex.dao.PhoneDao;
import com.javaex.vo.PersonVo;

@WebServlet("/pbc")
public class PhonebookController extends HttpServlet {
	private static final long serialVersionUID = 1L;
 
    public PhonebookController() {
        super();

    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		System.out.println("PhonebookController.goGet()");
		
		String action = request.getParameter("action");
		System.out.println(action);
		
		if("wform".equals(action)) {
			System.out.println("wform: 등록폼");
			
			
			RequestDispatcher rd= request.getRequestDispatcher("/writeForm.jsp");
			rd.forward(request, response);
			
			
			}else if("insert".equals(action)) {
			System.out.println("insert : 등록");
			
			String name = request.getParameter("name");
			String hp = request.getParameter("hp");
			String company = request.getParameter("company");
			
			/*vo로 묶기*/
			PersonVo personVo = new PersonVo(name, hp, company);
			System.out.println(personVo.toString());
			
			//db관련 업무
			PhoneDao phonedao = new PhoneDao();
			//db에 저장
			phonedao.personInsert(personVo);
			
			//db에서 전체 데이터 가져오기
			List<PersonVo> personList = phonedao.personSelect();
			/*System.out.println(personList);*/
			
			request.setAttribute("personList", personList);
			
			//포워드
			RequestDispatcher rd= request.getRequestDispatcher("/list.jsp");
			rd.forward(request, response);
			
		}
		
		
		
	
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
