package com.buybook.DTO;

public class UserDTO {
	private String userEmail; // 회원 이메일
	private String userPwd; // 회원 비밀번호
	private String userName; // 회원 이름
	private String userPhone; // 회원 연락처
	private String userAddress; // 회원 주소

	public UserDTO(String userEmail, String userPwd, String userName, String userPhone, String userAddress) {
		super();
		this.userEmail = userEmail;
		this.userPwd = userPwd;
		this.userName = userName;
		this.userPhone = userPhone;
		this.userAddress = userAddress;
	}

	public String getUserEmail() {
		return userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	public String getUserPwd() {
		return userPwd;
	}

	public void setUserPwd(String userPwd) {
		this.userPwd = userPwd;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserPhone() {
		return userPhone;
	}

	public void setUserPhone(String userPhone) {
		this.userPhone = userPhone;
	}

	public String getUserAddress() {
		return userAddress;
	}

	public void setUserAddress(String userAddress) {
		this.userAddress = userAddress;
	}

	@Override
	public String toString() {
		return "UserDTO [userEmail=" + userEmail + ", userPwd=" + userPwd + ", userName=" + userName + ", userPhone="
				+ userPhone + ", userAddress=" + userAddress + "]";
	}

}
