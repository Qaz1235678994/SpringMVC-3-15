package web.member.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.support.SessionStatus;

//實作18-2 翻寫登出
@Controller
@RequestMapping("member")
public class LogoutController {

	@GetMapping("logout")
	@ResponseBody
	public void logout(SessionStatus sessionStatus) {
		sessionStatus.setComplete();
	}
}
