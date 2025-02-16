package web.member.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import web.member.entity.Member;
import web.member.service.MemberService;

//實作18-2 翻寫註冊
@Controller
@RequestMapping("member")
public class RegisterController {
	
	// 注入
	@Autowired
	private MemberService service;
	
	@PostMapping("register")
	@ResponseBody
	public Member register(@RequestBody Member member) {
//		Member member = json2Pojo(request, Member.class); //對映@RequestBody Member member
		
		if (member == null) {
			member = new Member();
			member.setMessage("無會員資訊");
			member.setSuccessful(false);
//			writePojo2Json(response, member);
			return member;
		}

		member = service.register(member);
//		writePojo2Json(response, member);
		return member;
	}
	
}
