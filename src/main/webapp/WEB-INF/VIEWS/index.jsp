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
<link rel="icon" type="image/x-icon" href="/assets/favicon.ico" />
<!-- Bootstrap icons-->
<link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.5.0/font/bootstrap-icons.css" rel="stylesheet" type="text/css" />
<!-- Google fonts-->
<link href="https://fonts.googleapis.com/css?family=Lato:300,400,700,300italic,400italic,700italic" rel="stylesheet" type="text/css" />
<!-- Core theme CSS (includes Bootstrap)-->
<link href="/css/styles.css" rel="stylesheet" />
<!-- Bootstrap core JS-->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/js/bootstrap.bundle.min.js"></script>
<!-- Core theme JS-->
<script type="text/javascript" src="/js/scripts.js"></script>
<script type="text/javascript" src="/js/dataTables.js"></script>
<!--    사용자 정의 추가용-->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.1/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-alpha/js/bootstrap.min.js"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta.3/js/bootstrap.min.js"></script>
</head>
<body>
	<!-- Responsive navbar-->
	<nav class="navbar navbar-expand-lg bg-light static-top ">
		<div class="container px-5">
			<a class="navbar-brand" href="/">Buy Book</a>
			<%
			// 세션값 가져오기
			UserDTO userDTO = (UserDTO) session.getAttribute("userSessionDTO"); // Object 타입이므로 다운캐스팅
			%>
			<button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
				<span class="navbar-toggler-icon"></span>
			</button>
			<div class="collapse navbar-collapse" id="navbarSupportedContent">
				<ul class="navbar-nav ms-auto mb-2 mb-lg-0">
					<%
					// 세션값 가져오기
					if (userDTO == null) {
					%>
					<li class="nav-item">
						<a class="nav-link" href="/user/userSignUp">Sign Up</a>
					</li>
					<li class="nav-item">
						<a class="nav-link" href="/user/userSignIn">Sign In</a>
					</li>
					<%
					} else if (userDTO.getUserEmail().equals("admin@admin")) {
					%>
					<li class="nav-item">
						<a class="nav-link" href="/admin/">Admin Page</a>
					</li>
					<li class="nav-item">
						<a class="nav-link" href="/user/userSignOut">Sign Out</a>
					</li>
					<%
					} else {
					%>
					<li class="nav-item">
						<a class="nav-link" href="/cart/Cart?cartEmail=<%=userDTO.getUserEmail()%>">My Cart</a>
					</li>
					<li class="nav-item">
						<a class="nav-link" href="/user/userDetail">My Page</a>
					</li>
					<li class="nav-item">
						<a class="nav-link" href="/user/userSignOut">Sign Out</a>
					</li>
					<%
					}
					%>
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
					<%
					// 세션값 가져오기
					if (userDTO == null) {
					%>
					<a class="dropdown-item disabled" href="/book/userHope">희망 도서 신청</a>
					<a class="dropdown-item" href="/board/boardSearch">자유 게시판</a>
					<%
					} else {
					%>
					<a class="dropdown-item" href="/book/userHope">희망 도서 신청</a>
					<a class="dropdown-item" href="/board/boardSearch">자유 게시판</a>
					<%
					}
					%>
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
	<!--    검색 파트-->
	<!-- Masthead-->
	<header class="masthead">
		<div class="container position-relative">
			<div class="row justify-content-center">
				<div class="col-xl-6">
					<div class="text-center text-white">
						<!-- Page heading-->
						<h1 class="mb-5">Welcome to Buy Book</h1>
					</div>
				</div>
			</div>
		</div>
	</header>
	<!-- !!!!!!!!!!!!!!!!!!!!   자유게시판  !!!!!!!!!!!!!!!!!!!!-->
	<!-- Page Content-->
	<div class="container px-4 px-lg-5">
		<!-- Call to Action-->
		<div class="card text-white bg-primary my-5 py-10 text-center">
			<div class="card-body">
				<!--                    <p class="text-white m-0">자유 게시판</p>-->
				<a href="/board/boardSearch" class="btn btn-primary btn-lg">자유 게시판</a>
			</div>
		</div>
		<!-- Content Row-->
		<div class="row gx-4 gx-lg-5">
			<c:forEach var="boardDTO" items="${boardList}">
				<div class="col-md-4 mb-5">
					<div class="card h-100">
						<div class="card-body">
							<h2 class="card-title">${boardDTO.boardTitle}</h2>
							<p class="card-text">${boardDTO.boardContent}</p>
						</div>
						<div class="card-footer">
							<a class="btn btn-primary btn-sm" href="/board/boardDetail?boardNo=${boardDTO.boardNo}">More Info</a>
						</div>
					</div>
				</div>
			</c:forEach>
		</div>
	</div>
	<!--    추천 도서 / 인기 도서 섹션 -->
	<section class="bg-light py-5 border-bottom">
		<div class="container px-4 px-lg-5">
			<div class="card text-center">
				<div class="card-header">
					<ul class="nav nav-tabs card-header-tabs" id="tabs">
						<li class="nav-item">
							<a class="nav-link active" href="#librarianNominate" data-toggle="tab">추천 도서</a>
						</li>
						<li class="nav-item">
							<a class="nav-link" href="#hitBook" data-toggle="tab">인기 도서</a>
						</li>
					</ul>
				</div>
				<div class="card-body">
					<div class="tab-content">
						<div class="tab-pane active" id="librarianNominate">
							<!--                        추천 도서-->
							<div class="card-group col-sm-auto">
								<c:forEach var="goodDTO" items="${goodList}">
									<div class="card" style="width: 18rem;">
										<img class="card-img-top" src="/bookImageStorage/${goodDTO.goodImage}" alt="Card image cap">
										<div class="card-body">
											<h5 class="card-title">${goodDTO.goodTitle}</h5>
											<a href="/book/goodDetail?goodNo=${goodDTO.goodNo}" class="btn btn-primary">More Info</a>
										</div>
										<div class="card-footer">
											<small class="text-muted">${goodDTO.goodDate}</small>
										</div>
									</div>
								</c:forEach>
							</div>
							<div class="card text-white bg-primary my-5 py-10 text-center">
								<div class="card-body">
									<a href="/book/goodSearch" class="btn btn-primary btn-lg">추천 도서</a>
								</div>
							</div>
						</div>
						<div class="tab-pane" id="hitBook">
							<!--                        인기 도서-->
							<div class="card-group col-sm-auto">
								<c:forEach var="bookDTO" items="${hitBookList}">
									<div class="card" style="width: 18rem;">
										<div class="badge bg-dark text-white position-absolute" style="top: 0.5rem; right: 0.5rem">HIT!</div>
										<img class="card-img-top" src="/bookImageStorage/${bookDTO.bookImage}" alt="Card image cap">
										<div class="card-body">
											<h5 class="card-title">${bookDTO.bookTitle}</h5>
											<a href="/book/bookDetail?bookISBN=${bookDTO.bookISBN}&bookGenre=${bookDTO.bookGenre}" class="btn btn-primary">More Info</a>
										</div>
										<div class="card-footer">
											<small class="text-muted">${bookDTO.bookDate}</small>
										</div>
									</div>
								</c:forEach>
							</div>
							<div class="card text-white bg-primary my-5 py-10 text-center">
								<div class="card-body">
									<a href="/book/hitBookSearch" class="btn btn-primary btn-lg">인기 도서</a>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</section>
	<section class="py-5 border-bottom">
		<div class="container px-5 my-5">
			<div class="row gx-5 justify-content-center">
				<!-- Pricing card free-->
				<div class="col-lg-6 col-xl-6">
					<div class="card mb-5 mb-xl-0">
						<div class="card-body p-10">
							<div class="mb-3">
								<span class="display-4 fw-bold">
									<i class="bi bi-star-fill text-warning"></i>
									공지 사항
								</span>
							</div>
							<ul class="list-unstyled mb-4">
								<c:forEach var="noticeDTO" items="${noticeList}">
									<li class="mb-2">
										<i class="bi bi-check text-primary"></i>
										<strong><a class="bi text-primary" href="/board/noticeDetail?noticeNo=${noticeDTO.noticeNo}">${noticeDTO.noticeTitle}</a></strong>
									</li>
								</c:forEach>
							</ul>
							<div class="d-grid">
								<a class="btn btn-outline-primary" href="/board/noticeSearch">More Info</a>
							</div>
						</div>
					</div>
				</div>
				<div class="col-lg-6 col-xl-6">
					<div class="card mb-5 mb-xl-0">
						<div class="card-body p-10">
							<div class="mb-3">
								<span class="display-4 fw-bold">
									<i class="bi bi-star-fill text-warning"></i>
									신간 도서
								</span>
							</div>
							<ul class="list-unstyled mb-4">
								<c:forEach var="bookDTO" items="${bookList}">
									<li class="mb-2">
										<i class="bi bi-check text-primary"></i>
										<strong> <a class="bi text-primary" href="/book/bookDetail?bookISBN=${bookDTO.bookISBN}&bookGenre=${bookDTO.bookGenre}">${bookDTO.bookTitle}</a>
										</strong>
									</li>
								</c:forEach>
							</ul>
							<div class="d-grid">
								<a class="btn btn-outline-primary" href="/book/newBookSearch">More Info</a>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</section>
	<!-- Testimonials-->
	<section class="testimonials text-center bg-light">
		<div class="container">
			<h2 class="mb-5">Made By.</h2>
			<div class="row justify-content-center">
				<div class="col-lg-6">
					<div class="testimonial-item mx-auto mb-5 mb-lg-0">
						<img class="img-fluid rounded-circle mb-3" src="/assets/img/MJC.jpg" alt="..." />
						<h5>MJ.C</h5>
						<p class="font-weight-light mb-0">"취업하고 싶어요!"</p>
					</div>
				</div>
			</div>
		</div>
	</section>
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
</body>
</html>
