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

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		System.out.println("PhonebookController.goGet()");

		String action = request.getParameter("action");
		System.out.println(action);

		if ("wform".equals(action)) {
			System.out.println("wform: 등록폼");

			RequestDispatcher rd = request.getRequestDispatcher("/writeForm.jsp");
			rd.forward(request, response);

		} else if ("insert".equals(action)) {
			System.out.println("insert : 등록");

			String name = request.getParameter("name");
			String hp = request.getParameter("hp");
			String company = request.getParameter("company");

			/* vo로 묶기 */
			PersonVo personVo = new PersonVo(name, hp, company);
			System.out.println(personVo.toString());

			// db관련 업무
			PhoneDao phonedao = new PhoneDao();
			// db에 저장
			phonedao.personInsert(personVo);
			response.sendRedirect("http://localhost:8080/phonebook3/pbc?action=list");

			/*
			 * //db에서 전체 데이터 가져오기 List<PersonVo> personList = phonedao.personSelect();
			 * /*System.out.println(personList);
			 * 
			 * request.setAttribute("personList", personList);
			 * 
			 * //포워드 RequestDispatcher rd= request.getRequestDispatcher("/list.jsp");
			 * rd.forward(request, response);
			 * 
			 */

		} else if ("list".equals(action)) {
			System.out.println("list : 리스트 ");

			// DB사용
			PhoneDao phonedao = new PhoneDao();
			// list 가져오기
			List<PersonVo> personList = phonedao.personSelect();
			System.out.println(personList);
			// 데이터 담기
			request.setAttribute("personList", personList);
			// 포워드
			RequestDispatcher rd = request.getRequestDispatcher("/list.jsp");
			rd.forward(request, response);

		} else if ("delete".equals(action)) {
			System.out.println("delete:삭제");
			int no = Integer.parseInt(request.getParameter("no"));
			System.out.println(no);

			PhoneDao phonedao = new PhoneDao();
			phonedao.personDelete(no);

			response.sendRedirect("/phonebook3/pbc?action=list");

		} else if ("uform".equals(action)) {
			System.out.println("uform: 수정폼");

			// DB사용
			PhoneDao phonedao = new PhoneDao();
			// list 가져오기
			List<PersonVo> personList = phonedao.personSelect();
			System.out.println(personList);
			// 데이터 담기
			request.setAttribute("personList", personList);

			RequestDispatcher rd = request.getRequestDispatcher("/updateForm.jsp");
			rd.forward(request, response);

		} else if ("update".equals(action)) {
			System.out.println("update : 등록");

			int no = Integer.parseInt(request.getParameter("no"));
			String name = request.getParameter("name");
			String hp = request.getParameter("hp");
			String company = request.getParameter("company");

			/* vo로 묶기 */
			PersonVo personVo = new PersonVo(no, name, hp, company);
			System.out.println(personVo.toString());

			// db관련 업무
			PhoneDao phonedao = new PhoneDao();
			// db에 저장
			phonedao.personUpdate(personVo);
			response.sendRedirect("http://localhost:8080/phonebook3/pbc?action=list");

			/*
			 * //db에서 전체 데이터 가져오기 List<PersonVo> personList = phonedao.personSelect();
			 * /*System.out.println(personList);
			 * 
			 * request.setAttribute("personList", personList);
			 * 
			 * //포워드 RequestDispatcher rd= request.getRequestDispatcher("/list.jsp");
			 * rd.forward(request, response);
			 * 
			 */

		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
