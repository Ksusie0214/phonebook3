<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.List"%>
<%@ page import="com.javaex.vo.PersonVo"%>

<%
List<PersonVo> personList = (List<PersonVo>) request.getAttribute("personList");
System.out.println(personList);
%>


<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<h1>전화번호부</h1>

	<h2>수정폼</h2>

	<p>
		리스트를 수정하려면 <br> 아래 항목을 수정하고 "수정" 버튼을 클릭하세요.
	</p>

	<form action="http://localhost:8080/phonebook3/pbc" method="get">
		<input type="text" name="no" value=>
		<div>
			<label>이름(name): </label> <input type="text" name="name" value="<%=personList.get(0).getName() %>">
		</div>
		<div>
			<label>핸드폰(hp): </label> <input type="text" name="hp" value="<%=personList.get(0).getHp() %>">
		</div>
		<div>
			<label>회사(company):</label> <input type="text" name="company"
				value="<%=personList.get(0).getCompany() %>">
		</div>

		<input type="text" name=action value="update">
		<button type="submit">수정</button>

	</form>
	<br>
	<a href="">리스트 페이지 이동</a>

</body>
</html>