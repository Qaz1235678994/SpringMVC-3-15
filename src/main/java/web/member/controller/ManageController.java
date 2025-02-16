package web.member.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import core.pojo.Core;
import web.member.entity.Member;
import web.member.service.MemberService;

//實作18-2 翻寫資料管理
@Controller
@RequestMapping("member")
public class ManageController {
	
	@Autowired
	private MemberService service;
	
	// 傳統MVC 所以不能多補 ResponseBody
	// 因為SpringMvcConfig把configureViewResolvers拿掉了所以要手動補WEB-INF跟jsp
	@GetMapping("manage")
	 public String manage(Model model) {
	 List<Member> memberList = service.findAll();
	 model.addAttribute("memberList", memberList);
	 return "/WEB-INF/member/manage.jsp";
	 }
	
	@PostMapping("remove")
	@ResponseBody
	public Core remove(@RequestBody Member member) {
		final Integer id = member.getId();
		final Core core = new Core();
		if (id == null) {
			core.setMessage("無id");
			core.setSuccessful(false);
		} else {
			core.setSuccessful(service.remove(id));
		}
//		writePojo2Json(response, core);
		return core;
	}
	
	@PostMapping("save")
	@ResponseBody
	public Core save(@RequestBody Member member) {
//		final Member member = json2Pojo(request, Member.class);
		final Core core = new Core();
		if (member == null) {
			core.setMessage("無會員資訊");
			core.setSuccessful(false);
		} else {
			core.setSuccessful(service.save(member));
		}
//		writePojo2Json(response, core);
		return core;
	}
}
