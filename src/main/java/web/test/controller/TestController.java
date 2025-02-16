package web.test.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import web.member.entity.Member;
import web.member.service.MemberService;

// 實作6-5
@Controller
@RequestMapping("test")
public class TestController {
	@Autowired
	private MemberService	service;
	@Value("#{systemProperties['catalina.home']}")
	 private String tomcatRootPath;
	
	
	@GetMapping("t1")
	public String t1(Model model) {
		List<Member> list  = service.findAll();
		model.addAttribute("memberList", list);
		return "member/manage";
		
	}
	
	// 查詢全部
	@GetMapping("t2")
	@ResponseBody
	public	List<Member> t2() {
		return service.findAll();
	}
	
	// 測試11-2的上傳
	@PostMapping("t3")
	public void t3(@RequestParam("images") MultipartFile[] files) throws IllegalStateException, IOException {
		for (MultipartFile file : files) {
			file.transferTo(Paths.get(tomcatRootPath + "/files", file.getOriginalFilename()));
		}
	}
	
	// 測試11-3的回傳下載
	@GetMapping(path = "t4", produces =  MediaType.IMAGE_PNG_VALUE)
	@ResponseBody
	public byte[] t4() throws IOException {
		Path path = Paths.get(tomcatRootPath + "/files/yellow.png");
		return Files.readAllBytes(path);
	}
	
	// 測試 JSON用編碼解碼但是程式會自動編碼解碼
//	@GetMapping("t5")
//	@ResponseBody
//	public Member t5() throws IOException {
//		Member member = new Member();
//		Path path = Paths.get(tomcatRootPath + "/files/yellow.png");
//		member.setImage(Files.readAllBytes(path));
//		return member;
//	}
}
