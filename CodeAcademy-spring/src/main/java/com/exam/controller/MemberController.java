package com.exam.controller;

import java.sql.Timestamp;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.mail.SimpleEmail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.exam.domain.MemberVO;
import com.exam.service.MemberService;

@Controller
@RequestMapping("/member/*")
public class MemberController {
	
	@Autowired
	private MemberService memberService;
	
	//=====================================================================================================
	// @RequestMapping(value="/join", method = RequestMethod.GET)
	// RequestMapping으로 통해 해당경로로 지정된 jsp를 실행시 Get방식으로 요청되므로 해당 jsp가 실행된다.
	@GetMapping("/join")
	public String join() {
		return "member/join";
	} // join get
	
	// join.jsp에서 submit하면 post방식으로 요청되므로 아래의 코드가 실행된다.
	@PostMapping("/join")
	public String join(MemberVO memberVO) {
		// memberVO.setRegDate(new Timestamp(System.currentTimeMillis()));
		
		System.out.println(memberVO);
		
		memberService.insertMember(memberVO);
		
		return "redirect:/";
	}
	//=====================================================================================================
	@GetMapping("/mailAuth")
	public String mailAuth() {
		return "member/mailAuth";
	}
	
	@GetMapping("/mailAuthJson") 
	public String mailAuthJson(HttpSession session, HttpServletRequest request, String email) {
		
		// email = request.getParameter("email");
		
		int n1 = (int)(Math.random() * 9 + 1);
		int n2 = (int)(Math.random() * 9 + 1);
		int n3 = (int)(Math.random() * 9 + 1);
		int n4 = (int)(Math.random() * 9 + 1);
		int n5 = (int)(Math.random() * 9 + 1);
		int n6 = (int)(Math.random() * 9 + 1);

		String authNum = n1+""+n2+""+n3+""+n4+""+n5+""+n6;
		
		session.setAttribute("authNum", authNum);

		// SimpleEmail 객체 생성
		SimpleEmail emails = new SimpleEmail();
		// SMTP 서버 연결 설정
		emails.setHostName("smtp.gmail.com");
		emails.setSmtpPort(465);
		//email.setSslSmtpPort("465");
		emails.setAuthentication("donghotest7", "maioncjtvnfmteik");

		// SMTP SSL, TLS 설정
		emails.setSSLOnConnect(true);
		emails.setStartTLSEnabled(true);

		String result = "fail";

		try {
			// 보내는 사람 설정
			emails.setFrom("donghotest7@gmail.com", "CodeAcademy", "utf-8");
			
			// 받는 사람 설정
			emails.addTo(email, "utf-8");
			
			// 제목설정
			emails.setSubject("안녕하세요. CodeAcademy입니다.");
			
			// 본문설정
			emails.setMsg("안녕하세요.\nCodeAcademy입니다.\n인증번호는 " + authNum + " 입니다.\n인증번호를 입력해주세요.");
			
			// 메일전송
			result = emails.send();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		System.out.println("result : " + result);
		return null;
	}
	
	
	//=====================================================================================================
	@GetMapping("/joinIdDupCheckJson")
	public @ResponseBody boolean joinIdDupCheckJson(@RequestParam("id") String userid) {
		
		boolean isIdDup = memberService.isIdDuplicated(userid);
		System.out.println("@@@@@@@"+isIdDup);
		return isIdDup;
	} // joinIdDupCheckJson
	
	//=====================================================================================================
	@GetMapping("/emailDupCheckJson")
	public @ResponseBody boolean eamilDupCheckJson(@RequestParam("email") String email) {
		boolean isEmailDup = memberService.isEmailDuplicated(email);
		
		return isEmailDup;
	}
	//=====================================================================================================
	@GetMapping("/login")
	public String login() {
		return "member/login";
	}
	@PostMapping("/login")
	public ResponseEntity<String> login(@RequestParam("id") String id, @RequestParam("passwd") String passwd,
			String rememberMe, HttpSession session, HttpServletResponse response) { //ResponseEntity는 HttpStatus상태를 추가하고 리턴값으로 사용하기 위함
		int check = memberService.userCheck(id, passwd);
		
		if (check != 1) {
			String message = "";
			if (check == -1) {
				message = "존재하지 않는 아이디입니다. 가입 후 이용해주세요.";
			} else if (check == 0) {
				message = "패스워드가 다릅니다.";
			}
			
			HttpHeaders headers = new HttpHeaders();
			headers.add("Content-Type", "text/html; charset=UTF-8");
			
			StringBuilder sb = new StringBuilder();
			sb.append("<script>");
			sb.append("alert('"+message+"');");
			sb.append("history.back();");
			sb.append("</script>");
			
			return new ResponseEntity<String>(sb.toString(), headers, HttpStatus.OK);
		} // failed login
		
		session.setAttribute("id", id);
		
		if (rememberMe != null && rememberMe.equals("true")) {
			Cookie cookie = new Cookie("id", id);
			cookie.setMaxAge(60 * 10); // 초단위. 10분 = 60초 * 10 = 600초
			cookie.setPath("/"); // 쿠키경로 설정
			response.addCookie(cookie); // 응답객체에 추가
		}
		
		HttpHeaders headers = new HttpHeaders();
		headers.add("Location", "/");
		
		return new ResponseEntity<String>(headers, HttpStatus.FOUND);
	}
	
	//=====================================================================================================
	@GetMapping("/logout")
	public ResponseEntity<String> logout(HttpSession session, HttpServletRequest request,
			HttpServletResponse response) {
		session.invalidate();
		
		Cookie[] cookies = request.getCookies();
		if (cookies != null) {
			for (Cookie cookie : cookies) {
				if(cookie.getName().equals("id")) {
					cookie.setMaxAge(0);
					cookie.setPath("/");
					response.addCookie(cookie);
				}
			}
		}
		
		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-Type", "text/html; charset=UTF-8");
		
		StringBuilder sb = new StringBuilder();
		sb.append("<script>");
		sb.append("alert('로그아웃 되었습니다.');");
		sb.append("location.href='/';");
		sb.append("</script>");
		
		return new ResponseEntity<String>(sb.toString(), headers, HttpStatus.OK);
	}
	
	//=====================================================================================================
	@GetMapping("/infoUpdate")
	public String infoUpdate(HttpSession session, HttpServletRequest request, HttpServletResponse response) {
		String id = (String) session.getAttribute("id");
		MemberVO memberVO;
		memberVO = memberService.getMember(id);
		
		request.setAttribute("member", memberVO);
		
		return "member/infoUpdate";
	}
	
	@PostMapping("/infoUpdate")
	public ResponseEntity<String> infoUpdate(HttpSession session, HttpServletRequest request, HttpServletResponse response, MemberVO memberVO,
			@RequestParam("passwd") String passwd) {
		String id = (String) session.getAttribute("id");
		String realPasswd = memberService.getPasswd(id);
		
		
		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-Type", "text/html; charset=UTF-8");
		
		StringBuilder sb = new StringBuilder();
		if (passwd != null) {
			if(realPasswd.equals(passwd)) {
				memberService.updateMember(memberVO, passwd);
				sb.append("<script>");
				sb.append("alert('수정이 완료되었습니다.');");
				sb.append("location.href='/member/infoUpdate';");
				sb.append("</script>");
			} else {
				sb.append("<script>");
				sb.append("alert('비밀번호가 다릅니다.');");
				sb.append("history.back();");
				sb.append("</script>");
			}
		} else {
			sb.append("<script>");
			sb.append("alert('비밀번호를 입력해주세요.');");
			sb.append("history.back();");
			sb.append("</script>");
		}
		
		return new ResponseEntity<String>(sb.toString(), headers, HttpStatus.OK);
	} //infoUpdate
	
	//=====================================================================================================
	
	@GetMapping("/changePasswd")
	public String changePasswd() {
		return "member/changePasswd";
	} //changePasswd
	
	@PostMapping("/changePasswd")
	public ResponseEntity<String> changePasswd(HttpSession session, HttpServletRequest request, HttpServletResponse response, 
			@RequestParam("passwd") String passwd) {
		String id = (String) session.getAttribute("id");
		
		int check = memberService.userCheck(id, passwd);
		
		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-Type", "text/html; charset=UTF-8");
		
		StringBuilder sb = new StringBuilder();

		if (check == 1) {
			sb.append("<script>");
			sb.append("location.href='/member/realChangePasswd';");
			sb.append("</script>");
		} else {
			sb.append("<script>");
			sb.append("alert('비밀번호가 다릅니다.');");
			sb.append("history.back();");
			sb.append("</script>");
		}
		
		return new ResponseEntity<String>(sb.toString(), headers, HttpStatus.OK);
	} //changePasswd
	
	//=====================================================================================================
	@GetMapping("/realChangePasswd")
	public String realChangePasswd() {
		return "member/realChangePasswd";
	}
	
	@PostMapping("/realChangePasswd")
	public ResponseEntity<String> realChangePasswd (HttpSession session, HttpServletRequest request, HttpServletResponse response, 
			@RequestParam("passwd") String passwd) {
		
		String id = (String) session.getAttribute("id");
		
		int result = memberService.changePasswd(id, passwd);
		
		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-Type", "text/html; charset=UTF-8");
		
		StringBuilder sb = new StringBuilder();
		sb.append("<script>");
		sb.append("alert('비밀번호가 변경되었습니다.');");
		sb.append("location.href='/';");
		sb.append("</script>");

		return new ResponseEntity<String>(sb.toString(), headers, HttpStatus.OK);
	}
}