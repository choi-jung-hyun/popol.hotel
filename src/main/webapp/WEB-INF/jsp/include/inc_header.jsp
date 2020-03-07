<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<script>
function goHome(){
	location.href = '/main/main.do';
}

function goAbout(){
	location.href = '/main/about.do';
}

function goPlaces(){
	location.href = '/main/places.do';
}

function goHotels(){
	location.href = '/main/hotels.do';
}

function goBlog(){
	location.href = '/main/blog.do';
}

function goContact(){
	location.href = '/main/contact.do';
}

function goLogin(){
	location.href = '/login/loginView.do';
}

function goLogout(){
	location.href = '/login/logout.do';
}
</script>
    <nav
		class="navbar navbar-expand-lg navbar-dark ftco_navbar bg-dark ftco-navbar-light"
		id="ftco-navbar">
		<div class="container">
			<a class="navbar-brand" href="javascript:goHome();">Adventure</a>
			<button class="navbar-toggler" type="button" data-toggle="collapse"
				data-target="#ftco-nav" aria-controls="ftco-nav"
				aria-expanded="false" aria-label="Toggle navigation">
				<span class="oi oi-menu"></span> Menu
			</button>

			<div class="collapse navbar-collapse" id="ftco-nav">
				<ul class="navbar-nav ml-auto">
					<li class="nav-item active"><a href="javascript:goHome();" class="nav-link">Home</a></li>
					<li class="nav-item"><a href="javascript:goAbout();"  class="nav-link">About</a></li>
					<li class="nav-item"><a href="javascript:goPlaces();" class="nav-link">Places</a></li>
					<li class="nav-item"><a href="javascript:goHotels();" class="nav-link">Hotels</a></li>
					<li class="nav-item"><a href="javascript:goBlog();"   class="nav-link">Blog</a></li>
					<li class="nav-item"><a href="javascript:goContact();"class="nav-link">Contact</a></li>
					<li class="nav-item"><a href="javascript:goBoard();" class="nav-link">Board</a></li>
					<c:choose>
						<c:when test="${!empty loginId}">
							<li class="nav-item"><a href="javascript:goLogout();" class="nav-link">Logout</a></li>
						</c:when>
						<c:otherwise>
							<li class="nav-item"><a href="javascript:goLogin();"  class="nav-link">Login</a></li>
						</c:otherwise>
					</c:choose>		
				</ul>
			</div>
		</div>
	</nav>