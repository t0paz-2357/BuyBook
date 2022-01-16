<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
<meta name="description" content="" />
<meta name="author" content="" />
<title>Buy Book</title>
<!-- Favicon-->
<link rel="icon" type="image/x-icon" href="../assets/favicon.ico" />
<link href="../css/styles2.css" rel="stylesheet" />
<script src="../js/scripts.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/js/all.min.js" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/js/bootstrap.bundle.min.js" crossorigin="anonymous"></script>
</head>
<body class="bg-primary">
	<div id="layoutAuthentication">
		<div id="layoutAuthentication_content">
			<main>
				<div class="container">
					<div class="row justify-content-center">
						<div class="col-lg-7">
							<div class="card shadow-lg border-0 rounded-lg mt-5">
								<div class="card-header">
									<h3 class="text-center font-weight-light my-4">Sign Up</h3>
								</div>
								<div class="card-body">
									<!-- 회원가입 -->
									<form action="/user/userSignUp" method="POST">
										<!-- 이메일 -->
										<div class="form-floating mb-3">
											<input class="form-control" id="inputUserEmail" type="email" placeholder="name@example.com" name="inputUserEmail" />
											<label for="inputUserEmail">E-Mail Address</label>
										</div>
										<!-- 이메일 -->
										<!-- 비밀번호 -->
										<div class="row mb-3">
											<div class="col-md-6">
												<div class="form-floating mb-3 mb-md-0">
													<input class="form-control" id="inputUserPwd" type="password" placeholder="Create a password" name="inputUserPwd" />
													<label for="inputUserPwd">Password</label>
												</div>
											</div>
											<div class="col-md-6">
												<div class="form-floating mb-3 mb-md-0">
													<input class="form-control" id="inputUserPwdConfirm" type="password" placeholder="Confirm password" name="inputUserPwdConfirm" />
													<label for="inputUserPwdConfirm">Confirm Password</label>
												</div>
											</div>
										</div>
										<!-- 비밀번호 -->
										<!-- 이름 -->
										<div class="form-floating mb-3">
											<input class="form-control" id="inputUserName" type="text" placeholder="Full Name" name="inputUserName" />
											<label for="inputUserName">Name</label>
										</div>
										<!-- 이름 -->
										<!-- 연락처 -->
										<div class="form-floating mb-3">
											<input class="form-control" id="inputUserPhone" type="text" placeholder="Phone" name="inputUserPhone" />
											<label for="inputUserPhone">Phone</label>
										</div>
										<!-- 연락처 -->
										<!-- 주소 -->
										<div class="form-floating mb-3">
											<input class="form-control" id="inputUserAddress" type="text" placeholder="Full Name" name="inputUserAddress" />
											<label for="inputUserAddress">Address</label>
										</div>
										<!-- 주소 -->
										<div class="mt-4 mb-0">
											<div class="d-grid">
												<input type="submit" class="btn btn-primary btn-block" value="Sign Up" />
											</div>
										</div>
									</form>
									<!-- 회원가입 -->
								</div>
								<div class="card-footer text-center py-3">
									<div class="small">
										<a href="/user/userSignIn">Have an account? Go to sign in</a>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</main>
		</div>
		<div id="layoutAuthentication_footer">
			<footer class="py-4 bg-light mt-auto">
				<div class="container-fluid px-4">
					<div class="d-flex align-items-center justify-content-between small">
						<div class="text-muted">Copyright &copy; Buy Book</div>
						<div>
							<a href="#">Privacy Policy</a>
							&middot;
							<a href="#">Terms &amp; Conditions</a>
						</div>
					</div>
				</div>
			</footer>
		</div>
	</div>
</body>
</html>