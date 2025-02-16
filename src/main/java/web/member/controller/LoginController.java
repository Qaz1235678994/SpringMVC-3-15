package web.member.controller;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import web.member.entity.Member;
import web.member.service.MemberService;

//實作18-2 翻寫登入
@Controller
@RequestMapping("member")
public class LoginController {

	@Autowired
	private MemberService service;
	
	@PostMapping("login")
	@ResponseBody
	protected Member login(HttpServletRequest request, @RequestBody Member member) {
//		Member member = json2Pojo(request, Member.class);
		if (member == null) {
			member = new Member();
			member.setMessage("無會員資訊");
			member.setSuccessful(false);
//			writePojo2Json(response, member);
			return member;
		}

		member = service.login(member);
		if (member.isSuccessful()) {
			if (request.getSession(false) != null) {
				request.changeSessionId();
			}
			final HttpSession session = request.getSession();
			session.setAttribute("loggedin", true);
			session.setAttribute("member", member);
		}
//		writePojo2Json(response, member);
		return member;
	}
}
