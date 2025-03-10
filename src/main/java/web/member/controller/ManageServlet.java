package web.member.controller;

import static core.util.Constants.PREFIX_WEB_INF;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import core.util.CommonUtil;
import web.member.entity.Member;
import web.member.service.MemberService;


// 實作12-5 Spring MVC寫法翻寫成MemberController，所以此controller已經不用了

//@WebServlet("/member/manage")
public class ManageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private MemberService service;

	@Override
	public void init() throws ServletException {
		service = CommonUtil.getBean(getServletContext(), MemberService.class);
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		List<Member> memberList = service.findAll();
		request.setAttribute("memberList", memberList);
		request.getRequestDispatcher(PREFIX_WEB_INF + "/member/manage.jsp").forward(request, response);
	}
}
