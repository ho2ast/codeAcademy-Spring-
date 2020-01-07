<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<%-- head Area --%>
	<jsp:include page="../include/common_head.jsp"/>
</head>

<body>
	<%-- header area --%>
	<jsp:include page="../include/header.jsp"/>
	<%-- top menu --%>
	<jsp:include page="../include/adminBoard_topMenu.jsp"/>
	
	<button type="button" id="boardStatistics">카테고리별 게시글 수 통계</button>
	<div id="mychartBoard" style="width: 800px; height: 500px;"></div>
	
	<script src="scripts/jquery-3.4.1.min.js"></script>
	<script src="https://www.gstatic.com/charts/loader.js"></script>
	<script>
	// 구글 시각화 API를 로딩하는 메소드
	google.charts.load('current', {packages: ['corechart']});
	
	$('#boardStatistics').click(function () {
		$.ajax({
			url: 'adminBoardStatistics.do',
			success: function (data) {
				drawChart(data); // 밑에 있는 function drawChart(data)에서 수행
			}
		});
	});
	
	function drawChart(array) {
		
		var dataTable = google.visualization.arrayToDataTable(array);
		
		var options = { isStacked: true };
		
		var objDiv = document.getElementById('mychartBoard');
		var chart = new google.visualization.ColumnChart(objDiv);
		chart.draw(dataTable, options);
	}
	</script>

</body>
</html>