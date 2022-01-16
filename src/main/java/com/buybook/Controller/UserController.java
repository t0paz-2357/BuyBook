package com.buybook.Controller;

import java.io.*;

import java.util.*;

import javax.servlet.http.*;

import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;
import org.springframework.ui.*;
import org.springframework.web.bind.annotation.*;

import com.buybook.Service.*;
import com.buybook.DTO.*;
import com.buybook.Exception.*;

@Controller
@RequestMapping(value = "/user/*")
public class UserController {

	@Autowired
	UserService userService;
	@Autowired
	PhraseService phraseService;
	@Autowired
	OrderService orderService;

	// 회원가입
	@RequestMapping(value = "/userSignUp", method = RequestMethod.GET)
	public String userSignUp() {
		return "userSignUp";
	}

	// 회원가입
	@PostMapping(value = "/userSignUp")
	public void userSignUp(HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {
			String inputUserEmail = request.getParameter("inputUserEmail");
			String inputUserPwd = request.getParameter("inputUserPwd");
			String inputUserPwdConfirm = request.getParameter("inputUserPwdConfirm");
			String inputUserName = request.getParameter("inputUserName");
			String inputUserPhone = request.getParameter("inputUserPhone");
			String inputUserAddress = request.getParameter("inputUserAddress");

			UserDTO userDTO = null;

			if (inputUserPwd.equals(inputUserPwdConfirm)) { // 비밀번호가 일치
				userDTO = userService.userSignUp(inputUserEmail, inputUserPwd, inputUserName, inputUserPhone,
						inputUserAddress); // 회원가입
			} else // 비밀번호 일치하지 않음
				throw new NotMatchingException("확인 비밀번호와 맞지 않습니다.");

			if (userDTO == null)
				throw new AlreadyExistingException("이미 존재하는 계정입니다.");
			else {
				System.out.println(userDTO.toString());

				response.sendRedirect("/user/userSignIn");
			}
		} catch (NotMatchingException ex) {
			response.setContentType("text/html; charset=UTF-8");

			PrintWriter out = response.getWriter();

			out.println("<script>alert('확인 비밀번호와 맞지 않습니다.'); location.href='/user/userSignUp';</script>");

			out.flush();
		} catch (AlreadyExistingException ex) {
			response.setContentType("text/html; charset=UTF-8");

			PrintWriter out = response.getWriter();

			out.println("<script>alert('이미 존재하는 계정입니다.'); location.href='/user/userSignUp';</script>");

			out.flush();
		}
	}

	// 로그인
	@RequestMapping(value = "/userSignIn", method = RequestMethod.GET)
	public String userSignIn() {
		return "userSignIn";
	}

	// 로그인
	@PostMapping(value = "/userSignIn")
	public void userSignIn(HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {
			HttpSession session = request.getSession(true);

			String inputUserEmail = request.getParameter("inputUserEmail");
			String inputUserPwd = request.getParameter("inputUserPwd");

			UserDTO userDTO = userService.userSignIn(inputUserEmail, inputUserPwd);

			if (userDTO == null) // 로그인 실패
				throw new SignInFailException("로그인에 실패하였습니다.");
			else if (userDTO.getUserEmail().equals("admin@admin")) { // 관리자
				session.setAttribute("userSessionName", userDTO.getUserName());
				session.setAttribute("userSessionDTO", userDTO);

				response.sendRedirect("/admin/");
			} else { // 회원
				session.setAttribute("userSessionName", userDTO.getUserName());
				session.setAttribute("userSessionDTO", userDTO);

				response.sendRedirect("/");
			}
		} catch (SignInFailException ex) {
			response.setContentType("text/html; charset=UTF-8");

			PrintWriter out = response.getWriter();

			out.println("<script>alert('로그인에 실패하였습니다.'); location.href='/user/userSignIn';</script>");

			out.flush();
		}
	}

	// 비밀번호 찾기
	@RequestMapping(value = "/userForgotPwd", method = RequestMethod.GET)
	public String userForgotPwd() {
		return "userForgotPwd";
	}

	// 비밀번호 찾기
	@PostMapping(value = "/userForgotPwd")
	public void userForgotPwd(HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {
			String inputUserEmail = request.getParameter("inputUserEmail");

			UserDTO userDTO = userService.findOneUser(inputUserEmail);

			if (userDTO == null) // 회원이 존재하지 않는다.
				throw new NotExistingException("존재하지 않는 계정입니다.");
			else { // 임시 비밀번호를 생성해 DB를 업데이트하고 메일을 보내준다.
				userDTO = userService.changeUserPwd(userDTO);

				userService.sendPasswordEmail(userDTO, "userForgotPwd");

				response.sendRedirect("/user/userSignIn");
			}
		} catch (NotExistingException ex) {
			response.setContentType("text/html; charset=UTF-8");

			PrintWriter out = response.getWriter();

			out.println("<script>alert('존재하지 않는 계정입니다.'); location.href='/user/userSignUp';</script>");

			out.flush();
		}
	}

	// 회원 내 페이지
	@RequestMapping(value = "/userDetail", method = RequestMethod.GET)
	public String userDetail(Model model, HttpSession session) {
		UserDTO userDTO = (UserDTO) session.getAttribute("userSessionDTO");

		PhraseDTO phraseDTO = phraseService.selectOnePhrase();

		List<OrderDTO> orderList = orderService.showAll(userDTO.getUserEmail());

		model.addAttribute("orderList", orderList);
		model.addAttribute("phraseDTO", phraseDTO);

		return "userDetail";
	}

	// 로그아웃
	@GetMapping("/userSignOut")
	public String userSignOut(final HttpSession session) {
		if (session.getAttribute("userSessionName") != null)
			session.removeAttribute("userSessionName");

		session.invalidate();

		return "redirect:/";
	}

	// 비밀번호 수정
	@RequestMapping(value = "/userChangePwd", method = RequestMethod.GET)
	public String userChangePwd() {
		return "userChangePwd";
	}

	// 비밀번호 수정
	@PostMapping(value = "/userChangePwd")
	public void userChangePwd(HttpServletRequest request, HttpServletResponse response, HttpSession session)
			throws Exception {
		try {
			UserDTO userDTO = (UserDTO) session.getAttribute("userSessionDTO");

			String inputUserOldPwd = request.getParameter("inputUserOldPwd");
			String inputUserNewPwd = request.getParameter("inputUserNewPwd");
			String inputUserNewPwdConfirm = request.getParameter("inputUserNewPwdConfirm");

			if (userDTO.getUserPwd().equals(inputUserOldPwd)) {
				if (inputUserNewPwd.equals(inputUserNewPwdConfirm)) {
					userService.changeUserPwd(userDTO, inputUserNewPwd);

					response.sendRedirect("/user/userDetail");
				} else
					throw new NotMatchingException("비밀번호가 맞지 않습니다.");
			} else
				throw new NotMatchingException("비밀번호가 맞지 않습니다.");
		} catch (NotMatchingException ex) {
			response.setContentType("text/html; charset=UTF-8");

			PrintWriter out = response.getWriter();

			out.println("<script>alert('비밀번호가 맞지 않습니다.'); location.href='/user/userChangePwd';</script>");

			out.flush();
		}

	}

	// 회원 정보 수정
	@RequestMapping(value = "/userChangeInfo", method = RequestMethod.GET)
	public String userChangeInfo() {
		return "userChangeInfo";
	}

	// 회원 정보 수정
	@PostMapping(value = "/userChangeInfo")
	public void userChangeInfo(HttpServletRequest request, HttpServletResponse response, HttpSession session) throws Exception {
			UserDTO userDTO = (UserDTO) session.getAttribute("userSessionDTO");
			
			String inputUserName = request.getParameter("inputUserName");
			String inputUserPhone = request.getParameter("inputUserPhone");
			String inputUserAddress = request.getParameter("inputUserAddress");
			
			userService.changeUserName(userDTO, inputUserName);
			userService.changeUserPhone(userDTO, inputUserPhone);
			userService.changeUserAddress(userDTO, inputUserAddress);
			
			userDTO = userService.findOneUser(userDTO.getUserEmail());
			
			session.setAttribute("userSessionDTO", userDTO);
			
			response.sendRedirect("/user/userDetail");
	}
}
