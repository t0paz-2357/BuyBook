<%@page import="com.buybook.DTO.UserDTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
<meta name="description" content="" />
<meta name="author" content="" />
<title>Buy Book</title>
<!-- Favicon-->
<link rel="icon" type="image/x-icon" href="../assets/favicon.ico" />
<!-- Core theme CSS (includes Bootstrap)-->
<link href="../css/styles2.css" rel="stylesheet" />
<script src="../js/scripts.js"></script>
<script src="../js/dataTables.js"></script>
<!-- Bootstrap core JS-->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/js/bootstrap.bundle.min.js"></script>
<!-- Core theme JS-->
<!-- <script src="js/scripts.js"></script> -->
<!--    회원 정의 추가용-->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.1/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta.3/js/bootstrap.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/js/bootstrap.bundle.min.js" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/simple-datatables@latest" crossorigin="anonymous"></script>
</head>
<body>
	<!-- Responsive navbar-->
	<nav class="navbar navbar-expand-lg bg-light static-top ">
		<div class="container px-5">
			<a class="navbar-brand" href="/">Buy Book</a>
			<button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
				<span class="navbar-toggler-icon"></span>
			</button>
			<div class="collapse navbar-collapse" id="navbarSupportedContent">
				<ul class="navbar-nav ms-auto mb-2 mb-lg-0">
					<%
					// 세션값 가져오기
					UserDTO userDTO = (UserDTO) session.getAttribute("userSessionDTO"); // Object 타입이므로 다운캐스팅
					%>
					<li class="nav-item">
						<a class="nav-link" href="/user/userChangePwd">Change Password</a>
					</li>
					<li class="nav-item">
						<a class="nav-link" href="/user/userChangeInfo">Change Info</a>
					</li>
					<li class="nav-item">
						<a class="nav-link" href="/cart/Cart?cartEmail=<%=userDTO.getUserEmail()%>">My Cart</a>
					</li>
					<li class="nav-item">
						<a class="nav-link" href="/user/userSignOut">Sign Out</a>
					</li>
				</ul>
			</div>
		</div>
	</nav>
	<!-- Navigation-->
	<nav class="navbar navbar-light" style="background-color: #e3f2fd;">
		<div class="container">
			<a class="btn" style="background-color: #e3f2fd; color: dodgerblue;" href="/book/bookSearch">도서 검색</a>
			<div class="dropdown show">
				<a class="btn dropdown-toggle" style="background-color: #e3f2fd; color: dodgerblue;" href="#" role="button" id="dropdownMenuLink" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">신청 / 참여</a>
				<div class="dropdown-menu" aria-labelledby="dropdownMenuLink">
					<a class="dropdown-item" href="/book/userHope">희망 도서 신청</a>
					<a class="dropdown-item" href="/board/boardSearch">자유 게시판</a>
				</div>
			</div>
			<div class="dropdown show">
				<a class="btn dropdown-toggle" style="background-color: #e3f2fd; color: dodgerblue;" href="#" role="button" id="dropdownMenuLink" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">서점 이용</a>
				<div class="dropdown-menu" aria-labelledby="dropdownMenuLink">
					<a class="dropdown-item" href="/book/goodSearch">추천 도서</a>
					<a class="dropdown-item" href="/book/hitBookSearch">인기 도서</a>
					<a class="dropdown-item" href="/book/newBookSearch">신간 도서</a>
				</div>
			</div>
			<div class="dropdown show">
				<a class="btn dropdown-toggle" style="background-color: #e3f2fd; color: dodgerblue;" href="#" role="button" id="dropdownMenuLink" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">서점 정보</a>
				<div class="dropdown-menu" aria-labelledby="dropdownMenuLink">
					<a class="dropdown-item" href="/storeIntroduce">서점 소개</a>
					<a class="dropdown-item" href="/board/noticeSearch">공지 사항</a>
				</div>
			</div>
		</div>
	</nav>
	<!-- Page Content-->
	<div class="container px-4 px-lg-5">
		<div class="row gx-4 gx-lg-5 align-items-center my-5">
			<div class="col-lg-7">
				<img class="img-fluid rounded mb-4 mb-lg-0" src="../assets/img/admin_wallpaper.jpg" alt="..." />
			</div>
			<div class="col-lg-5">
				<h1 class="font-weight-light">
					Buy Book
					<br />
				</h1>
				<h2 class="font-weight-light">My Page</h2>
				<p>
					<%=session.getAttribute("userSessionName")%>님 안녕하세요!
					<br />
					<%=session.getAttribute("userSessionName")%>님의 행복한 하루를 기원합니다.
					<br />
					<br />
					<!-- 저희 서점은 대여한 기록과 연체 반납 횟수를 종합해 계산하여 회원님이 대여하실 수 있는 도서의 권수를 조정해드립니다. -->
				</p>
			</div>
		</div>
		<!-- Call to Action-->
		<div class="card text-white bg-primary my-5 py-4 text-center">
			<div class="card-body">
				<h3>
					<!-- 명언 랜덤 출력 -->
					<c var="phraseDTO" items="${phraseDTO}">
					<p class="text-white m-0">${phraseDTO.phraseString}</p>
					</c>
				</h3>
			</div>
		</div>
		<!-- Heading Row-->
		<div id="layoutSidenav_content">
			<main>
				<div class="container-fluid px-4">
					<h1 class="mt-4">구매 도서</h1>
					<div class="card mb-4">
						<div class="card-body">
							<table id="datatablesSimple">
								<thead>
									<tr>
										<th>ISBN</th>
										<th>제목</th>
										<th>수</th>
										<th>가격</th>
										<th>날짜</th>
									</tr>
								</thead>
								<tbody>
									<c:forEach var="orderDTO" items="${orderList}">
										<tr>
											<td>${orderDTO.orderedISBN}</td>
											<td>${orderDTO.orderedTitle}</td>
											<td>${orderDTO.orderedCount}</td>
											<td>${orderDTO.orderedPrice}</td>
											<td>${orderDTO.orderedDate}</td>
										</tr>
									</c:forEach>
								</tbody>
							</table>
						</div>
					</div>
				</div>
			</main>
			<!-- Footer-->
			<footer class="footer bg-light">
				<div class="container">
					<div class="row">
						<div class="col-lg-6 h-100 text-center text-lg-start my-auto">
							<ul class="list-inline mb-2">
								<li class="list-inline-item">
									<a href="#!">About</a>
								</li>
								<li class="list-inline-item">⋅</li>
								<li class="list-inline-item">
									<a href="#!">Contact</a>
								</li>
							</ul>
							<p class="text-muted small mb-4 mb-lg-0">&copy; Buy Book 2021. All Rights Reserved.</p>
						</div>
						<div class="col-lg-6 h-100 text-center text-lg-end my-auto">
							<ul class="list-inline mb-0">
								<li class="list-inline-item">
									<a href="https://www.github.com/t0paz-2357/BuyBook">
										<i class="bi-github fs-3"></i>
									</a>
								</li>
							</ul>
						</div>
					</div>
				</div>
			</footer>
		</div>
	</div>
</body>
</html>
