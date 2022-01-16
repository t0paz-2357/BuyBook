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
						<div class="col-lg-5">
							<div class="card shadow-lg border-0 rounded-lg mt-5">
								<div class="card-header">
									<h3 class="text-center font-weight-light my-4">Sign In</h3>
								</div>
								<div class="card-body">
									<!-- 로그인 -->
									<form action="/user/userSignIn" method="POST">
										<!-- 이메일 -->
										<div class="form-floating mb-3">
											<input class="form-control" id="inputUserEmail" type="email" placeholder="name@example.com" name="inputUserEmail" />
											<label for="inputUserEmail">E-Mail Address</label>
										</div>
										<!-- 이메일 -->
										<!-- 비밀번호 -->
										<div class="form-floating mb-3">
											<input class="form-control" id="inputUserPwd" type="password" placeholder="Password" name="inputUserPwd" />
											<label for="inputUserPwd">Password</label>
										</div>
										<!-- 비밀번호 -->
										<div class="d-flex align-items-center justify-content-between mt-4 mb-0">
											<a class="small" href="/user/userForgotPwd">Forgot Password?</a>
											<input type="submit" class="btn btn-primary" value="Sign In" />
										</div>
									</form>
									<!-- 로그인 -->
								</div>
								<div class="card-footer text-center py-3">
									<div class="small">
										<a href="/user/userSignUp">Need an account? Sign up!</a>
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