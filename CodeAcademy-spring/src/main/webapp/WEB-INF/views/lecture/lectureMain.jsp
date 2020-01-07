<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

<head>
	<%-- head area --%>
	<jsp:include page="../include/common_head.jsp"/>
</head>

<body>
	<%-- header area --%>
	<jsp:include page="../include/header.jsp"/>
	
	<%-- #topMenu area --%>
	<jsp:include page="../include/lecture_topMenu.jsp"/>
	
<fieldset class="gra1">
<h2 class="h2">JAVA</h2>
<div class="firsthomecontent">
	<a href="https://www.javatpoint.com/java-tutorial">
	    <div class="homecontent"><img src="/resources/imgs/java.png" alt="JAVA"/>
	        <p>JAVA</p>
	    </div>
	</a> 
</div>
</fieldset>

<fieldset class="gra1">
<h2 class="h2">WEB</h2>
<div class="firsthomecontent">
	<a href="https://www.javatpoint.com/html-tutorial">
	    <div class="homecontent"><img src="/resources/imgs/html.png" alt="HTML"/>
	        <p>HTML</p>
	    </div>
	</a> 
	<a href="https://www.javatpoint.com/css-tutorial">
	    <div class="homecontent"><img src="/resources/imgs/css3.jpg" alt="CSS"/>
	        <p>CSS</p>
	    </div>
	</a>
	<a href="https://www.javatpoint.com/javascript-tutorial">
	    <div class="homecontent"><img src="/resources/imgs/javascript.png" alt="JavaScript"/>
	        <p>JavaScript</p>
	    </div>
	</a>
	<a href="https://www.javatpoint.com/jquery-tutorial">
	    <div class="homecontent"><img src="/resources/imgs/jquery.png" alt="jQuery"/>
	        <p>jQuery</p>
	    </div>
	</a>
	<a href="https://www.javatpoint.com/ajax-tutorial">
	    <div class="homecontent"><img src="/resources/imgs/ajax.png" alt="AJAX"/>
	        <p>AJAX</p>
	    </div>
	</a>
</div>
</fieldset>

<fieldset class="gra1">
<h2 class="h2">DATABASE</h2>
<div class="firsthomecontent">
	<a href="https://www.javatpoint.com/oracle-tutorial">
	    <div class="homecontent"><img src="/resources/imgs/oracle.png" alt="Oracle" />
	        <p>Oracle</p>
	    </div>
	</a> 
	<a href="https://www.javatpoint.com/mysql-tutorial">
	    <div class="homecontent"><img src="/resources/imgs/mysql.png" alt="MySQL" />
	        <p>MySQL</p>
	    </div>
	</a> 
</div>
</fieldset>
	
<%-- footer area --%>
<jsp:include page="../include/footer.jsp"/>
	
</body>
</html>