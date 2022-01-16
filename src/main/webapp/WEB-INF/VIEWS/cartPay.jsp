<%@page import="com.buybook.DTO.UserDTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Buy Book</title>
<!-- Favicon-->
<link rel="icon" type="image/x-icon" href="../assets/favicon.ico" />
<!-- jQuery -->
<script type="text/javascript" src="https://code.jquery.com/jquery-1.12.4.min.js"></script>
<!-- iamport.payment.js -->
<script type="text/javascript" src="https://cdn.iamport.kr/js/iamport.payment-1.2.0.js"></script>
<%
UserDTO userDTO = (UserDTO) session.getAttribute("userSessionDTO");

int totalPrice = Integer.parseInt((String) request.getParameter("totalPrice"));
%>
</head>
<body>
	<script>
$(function(){
		var IMP = window.IMP; // 생략가능
		IMP.init("imp75701478"); // 'iamport' 대신 부여받은 "가맹점 식별코드"를 사용
		var msg;

		IMP.request_pay({
			pg : "kakaopay",
			pay_method : "card",
			merchant_uid : 'merchant_' + new Date().getTime(),
			name : "도서 주문",
			amount : <%=totalPrice%>,
			buyer_email : '<%=userDTO.getUserEmail()%>',
			buyer_name : '<%=userDTO.getUserName()%>',
			buyer_tel : '<%=userDTO.getUserPhone()%>',
			buyer_addr : '<%=userDTO.getUserAddress()%>',
			buyer_postcode : ""
		}, function(rsp) { // callback
			if (rsp.success) {
				//[1] 서버단에서 결제정보 조회를 위해 jQuery ajax로 imp_uid 전달하기
				jQuery.ajax({
					url : "/cart/cartPay", //cross-domain error가 발생하지 않도록 주의해주세요
					type : 'POST',
					dataType : 'json',
					data : {
						imp_uid : rsp.imp_uid
					//기타 필요한 데이터가 있으면 추가 전달
					}
				}).done(function(data) {
					//[2] 서버에서 REST API로 결제정보확인 및 서비스루틴이 정상적인 경우
					if (everythings_fine) {
						var msg = '결제가 완료되었습니다.';
						msg += '\n고유ID : ' + rsp.imp_uid;
						msg += '\n상점 거래ID : ' + rsp.merchant_uid;
						msg += '\결제 금액 : ' + rsp.paid_amount;
						msg += '카드 승인번호 : ' + rsp.apply_num;

						alert(msg);
					} else {
						//[3] 아직 제대로 결제가 되지 않았습니다.
						//[4] 결제된 금액이 요청한 금액과 달라 결제를 자동취소처리하였습니다.
					}
				});
				location.href='<%=request.getContextPath()%>/cart/paySuccess?inputUserEmail=<%=userDTO.getUserEmail()%>';
			} else {
				// 결제에 실패시
				var msg = '결제에 실패';
				msg += '에러내용 : ' + rsp.error_msg;
				location.href='<%=request.getContextPath()%>/cart/payFail';
									alert(msg);
								}
							});
		});
	</script>
</body>
</html>
