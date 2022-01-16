<%@page import="com.buybook.DTO.UserDTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
<!-- Bootstrap core JS-->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/js/bootstrap.bundle.min.js"></script>
<!-- Core theme JS-->
<script src="../js/scripts.js"></script>
<script src="../js/dataTables.js"></script>
<!--    회원 정의 추가용-->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.1/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta.3/js/bootstrap.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/js/bootstrap.bundle.min.js" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/simple-datatables@latest" crossorigin="anonymous"></script>
</head>
<body>
	<%
	// 세션값 가져오기
	UserDTO userDTO = (UserDTO) session.getAttribute("userSessionDTO"); // Object 타입이므로 다운캐스팅
	%>
	<!-- Responsive navbar-->
	<nav class="navbar navbar-expand-lg bg-light static-top ">
		<div class="container px-5">
			<a class="navbar-brand" href="/">Buy Book</a>
			<button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
				<span class="navbar-toggler-icon"></span>
			</button>
			<div class="collapse navbar-collapse" id="navbarSupportedContent">
				<ul class="navbar-nav ms-auto mb-2 mb-lg-0">
					<li class="nav-item">
						<a class="nav-link" href="/cart/Cart?cartEmail=<%=userDTO.getUserEmail()%>">My Cart</a>
					</li>
					<li class="nav-item">
						<a class="nav-link" href="/user/userDetail">My Page</a>
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
		<!-- Heading Row-->
		<div id="layoutSidenav_content">
			<main>
				<div class="container-fluid px-4">
					<h1 class="mt-4">장바구니</h1>
					<div class="card mb-4">
						<div class="card-header">
							<i class="fas fa-table me-1"></i>
							도서 목록
						</div>
						<div class="card-body">
							<table id="datatablesSimple">
								<thead>
									<tr>
										<th>이미지</th>
										<th>제목</th>
										<th>수량</th>
										<th>금액</th>
										<th>삭제</th>
									</tr>
								</thead>
								<tbody>
									<c:set var="totalPrice" value="0" />
									<c:forEach var="cartDTO" items="${cartList}">
										<tr>
											<td><img src="/bookImageStorage/${cartDTO.cartImage}" alt="..." style="height: 25rem" /></td>
											<td>${cartDTO.cartTitle}</td>
											<td>${cartDTO.cartCount}</td>
											<td>${cartDTO.cartPrice}</td>
											<td>
												<form action="/cart/Cart?cartEmail=${cartDTO.cartEmail}&cartISBN=${cartDTO.cartISBN}" method="POST">
													<input type="submit" value="삭제" />
												</form>
											</td>
											<c:set var="totalPrice" value="${totalPrice + cartDTO.cartPrice}" />
										</tr>
									</c:forEach>
								</tbody>
							</table>
						</div>
					</div>
				</div>
				<div class="card text-white bg-primary my-5 py-10 text-center">
					<div class="card-body">
						<span>결제할 총 금액 : ${totalPrice} 원</span>
					</div>
				</div>
				<br />
				<div class="card text-white bg-primary my-5 py-10 text-center">
					<div class="card-body">
						<form action="/cart/cartPay" method="POST">
							<input type="hidden" id="totalPrice" name="totalPrice" value="${totalPrice}" />
							<input type="submit" class="btn btn-primary btn-lg" value="결제" />
						</form>
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
