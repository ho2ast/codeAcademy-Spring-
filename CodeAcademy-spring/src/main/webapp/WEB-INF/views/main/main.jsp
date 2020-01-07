<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

<head>
	<%-- head area --%>
	<jsp:include page="../include/common_head.jsp"/>
</head>

<body>
	<div class="wrap">
	<%-- header area --%>
	<jsp:include page="../include/header.jsp"/>
	

    <div class="full-width back-image1 full-page page" id="first">
        <div class="wrap">
            <div class="first-part">
                <h1>Programming makes your life better</h1>
            </div>
        </div>
    </div>
    
	<%-- footer area --%>
	<jsp:include page="../include/footer.jsp"/>
	</div>
    <script src="/resources/js/jquery.js"></script>
    <script src="/resources/js/smooth-scroll.polyfills.min.js"></script>
    <script src="/resources/js/script.js"></script>
    <script>
        var scroll = new SmoothScroll('a[href*="#"]');
    </script>
</body>

</html>