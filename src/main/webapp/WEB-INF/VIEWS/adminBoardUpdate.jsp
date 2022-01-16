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
	<!-- Responsive navbar-->
	<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
		<div class="container px-5">
			<a class="navbar-brand" href="/admin/">Buy Book Admin Page</a>
			<button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
				<span class="navbar-toggler-icon"></span>
			</button>
			<div class="collapse navbar-collapse" id="navbarSupportedContent">
				<ul class="navbar-nav ms-auto mb-2 mb-lg-0">
					<li class="nav-item">
						<a class="nav-link active" aria-current="page" href="/">Home</a>
					</li>
					<li class="nav-item">
						<a class="nav-link" href="/user/userSignOut">Sign Out</a>
					</li>
				</ul>
			</div>
		</div>
	</nav>
	<!-- Page Content-->
	<div class="container px-4 px-lg-5">
		<!-- Heading Row-->
		<div id="layoutSidenav_content">
			<main>
				<div class="container-fluid px-4">
					<h1 class="mt-4">게시글</h1>
					<!--                    회원 목록-->
					<div class="card mb-4">
						<div class="card-body">현재 자유 게시판에 기록된 게시글들의 목록을 확인합니다.</div>
					</div>
					<div class="card mb-4">
						<div class="card-header">
							<i class="fas fa-table me-1"></i>
							게시글 목록
						</div>
						<div class="card-body">
							<table id="datatablesSimple">
								<thead>
									<tr>
										<th>번호</th>
										<th>제목</th>
										<th>작성자</th>
										<th>작성 날짜</th>
										<th>공개 여부</th>
										<th>상세 페이지</th>
									</tr>
								</thead>
								<tbody>
									<c:forEach var="boardDTO" items="${boardList}">
										<tr>
											<td>${boardDTO.boardNo}</td>
											<td>${boardDTO.boardTitle}</td>
											<td>${boardDTO.boardName}</td>
											<td>${boardDTO.boardDate}</td>
											<td>${boardDTO.boardPublic}</td>
											<td><input type="button" value="자세히" onclick="location.href='/board/boardDetail?boardNo=${boardDTO.boardNo}'" /></td>
										</tr>
									</c:forEach>
								</tbody>
							</table>
						</div>
					</div>
				</div>
			</main>
			<div class="container px-4 px-lg-5">
				<div class="row justify-content-center">
					<div class="card shadow-lg border-5 rounded-lg mt-5">
						<div class="card-header">
							<h3 class="text-center font-weight-light my-4">게시글 공개 여부 수정</h3>
						</div>
						<div class="card-body">
							<form action="/admin/boardUpdate" method="POST">
								<div class="form-floating mb-3">
									<input class="form-control" id="inputBoardNo" type="text" placeholder="게시글의 번호를 입력해주세요." name="inputBoardNo" />
									<label for="inputBoardNo">수정을 원하는 게시글의 번호</label>
								</div>
								<label for="inputBoardPublic">회원 게시글 공개 여부</label>
								<div class="">
									<select class="form-control form-control-lg" id="inputBoardPublic" name="inputBoardPublic">
										<option>Y</option>
										<option>N</option>
									</select>
								</div>
								<div class="mt-4 mb-0">
									<div class="d-grid">
										<input type="submit" class="btn btn-primary btn-block" value="게시글 공개 여부를 수정합니다." />
									</div>
								</div>
							</form>
						</div>
					</div>
				</div>
			</div>
			<!-- 안 예뻐 ...-->
			<footer class="m-5 py-5 bg-dark">
				<div class="container px-4 px-lg-5">
					<p class="m-0 text-center text-white">Copyright &copy; Buy Book 2021</p>
				</div>
			</footer>
		</div>
	</div>
</body>
</html>
