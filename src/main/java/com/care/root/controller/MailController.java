package com.care.root.controller;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.care.root.service.MailService;

@Controller
public class MailController {
	@Autowired MailService ms;
	@GetMapping("sendmail")
	public void sendMail(HttpServletResponse response) throws Exception {
		//StringBuffer : 내용(String도 가능하지만 스트링버퍼 처리속도가 더 빠르다)
		StringBuffer sb = new StringBuffer();
		sb.append("<h1>제품소개</h1>");
		sb.append("<a href=\"https://shoppinglive.naver.com/home\">");
		sb.append("<img src=\"https://search.pstatic.net/common/?src=http%3A%2F%2Fblogfiles.naver.net%2FMjAxODEwMDNfMjU3%2FMDAxNTM4NTUwOTQwMTkz.YBaIxg9u9JY1yMzYhGX8iKA7z9VLJkFhspNo0joYMFgg.H_dxfmXfArRFrY2qz8wRsTr8rOL38hh88uSnWfQhRikg.JPEG.yan_pic%2F%25B0%25B3%25C0%25CE%25C8%25AD%25BA%25B802.jpg&type=sc960_832\">");
		sb.append("</a>");
		
		//String 형태로 변환
		String msg = sb.toString();
		
		ms.sendMail("@naver.com","(제목)광고",msg);
		//메일 전송("전송하고자 하는 이메일주소","제목","내용")
		//ms.sendMail("youjin9874@naver.com","(제목)테스트 메일","(내용)안녕하세요");
		
		response.setContentType("text/html;charset=utf-8");//응답방법
		PrintWriter out = response.getWriter();
		out.print("메일이 전송되었습니다");
		
	}
	
	@GetMapping("auth_form")
	public String authForm() {
		return "auth";
	}
	@GetMapping("auth")
	public String auth(HttpServletRequest request) {
		ms.auth(request);
		return "redirect:https://www.naver.com/";//받는 사용자 이메일 기본 주소
	}
	@GetMapping("auth_check")
	public String authCheck(@RequestParam String userid,
							@RequestParam String userkey,
							HttpSession session) {
		String sessionKey = (String)session.getAttribute(userid);
		if(sessionKey != null) {
			if(sessionKey.equals(userkey)) {
				session.setAttribute("userid", userid);
			}
		}
		return "redirect:auth_form";
	}

}
