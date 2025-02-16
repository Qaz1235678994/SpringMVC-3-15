package web.member.controller;


import java.util.Objects;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttribute;

import core.pojo.Core;
import web.member.entity.Member;
import web.member.service.MemberService;

//實作18-2 翻寫修改
@Controller
@RequestMapping("member")
public class EditController {
	
	@Autowired
	private MemberService service;

	@PostMapping("getInfo")
	@ResponseBody
	public Member getInfo(@SessionAttribute Member member) {
//		Member member = (Member) request.getSession().getAttribute("member"); //從Http 的session拿到syytibute的物件再強制轉型成Member
		if (member == null) {
			member = new Member();
			member.setMessage("無會員資訊");
			member.setSuccessful(false);
		} else {
			member.setSuccessful(true);
		}
//		writePojo2Json(response, member);
		return member;
	}
	
	@PostMapping("checkPassword")
	@ResponseBody
	public Core checkPassowrd(@RequestBody Member reqMember, @SessionAttribute Member seMember) {
//		final String password = json2Pojo(request, Member.class).getPassword();
		final String password = reqMember.getPassword();
//		final Member member = (Member) request.getSession().getAttribute("member");
		final Core core = new Core();
		if (seMember == null) {
			core.setMessage("無會員資訊");
			core.setSuccessful(false);
		} else {
			final String currentPassword = seMember.getPassword();
			if (Objects.equals(password, currentPassword)) {
				core.setSuccessful(true);
			} else {
				core.setMessage("舊密碼錯誤");
				core.setSuccessful(false);
			}
		}
//		writePojo2Json(response, core);
		return core;
	}
	
		@PostMapping("edit")
		@ResponseBody
		public Member edit(@RequestBody Member reqMember, @SessionAttribute Member seMember) {
//			final HttpSession session = request.getSession();
			final String username = seMember.getUsername();
//			Member member = json2Pojo(request, Member.class);
			reqMember.setUsername(username);
//			writePojo2Json(response, service.edit(member));
			return service.edit(reqMember);
		}
}
