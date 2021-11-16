package com.care.root.service;

import java.util.Random;

import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
public class MailService {
	@Autowired JavaMailSender mailSender;//JavaMailSender로 mailSender(config)를 빈으로 얻어옴
	public void sendMail(String to, String subject, String body) {//(보내는 곳, 제목, 내용)
		MimeMessage message = mailSender.createMimeMessage();//받는 사용자에 대한 세팅(MimeMessageHelper를 위해 message가 필요한것)
		try {
			//MimeMessageHelper : 실질적으로 사용자에게 보낼 내용을 저장
			MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");//true : 멀티팟 허용
			helper.setTo(to);
			helper.setSubject(subject);
			helper.setText(body, true);//true를 적어줘야 html형식으로 넘어간다. 안그럼 text형식으로 감
			
			mailSender.send(message);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
		//이메일 로그인 인증
	   public void auth(HttpServletRequest request) {
		      HttpSession session = request.getSession();
		      String userid = "jin";
		      String userkey = rand();
		      session.setAttribute(userid, userkey);
		      String body="<h2>안녕하세요 아뱅입니다</h2><hr>"
		            +"<h3>"+userid+" 님</h3>"
		            +"<p>인증하기 버튼을 누르면 로그인 됩니다</p>"
		            +"<a href='http://localhost:8089"
		            +request.getContextPath()+"/auth_check?userid="
		            +userid+"&userkey="+userkey+"'>인증하기</a>";
		      sendMail("@naver.com","이메일 인증입니다",body);
		   }
		   private String rand() {
		      Random ran = new Random();
		      String str="";
		      int num;
		      while(str.length() != 20) {
		         num = ran.nextInt(75)+48;
		         if((num>=48 && num<=57)||(num>=65 && num<=90)||(num>=97 && num<=122)) {
		            str+=(char)num;
		         }else {
		            continue;
		         }
		      }
		      return str;
		   }
}
