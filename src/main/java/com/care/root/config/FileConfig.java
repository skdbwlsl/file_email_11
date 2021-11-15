package com.care.root.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;


//빈만들기 방법1. 요즘은 이 방법을 주로 쓴다
@Configuration  //설정하는 내용(설정하는 값을 빈으로 만들어준다, xml에서 만들었던것을 자바로 만드는 방식)
public class FileConfig {
	@Bean//리턴으로 돌려주는 값을 빈으로 만듦
	public CommonsMultipartResolver multipartResolver() {  //CommonsMultipartResolver:리턴 타입 . 리턴 자료형
		CommonsMultipartResolver mr = new CommonsMultipartResolver();//변수만들기
		mr.setMaxUploadSizePerFile(52428800); //50MB  //업로드크기
		mr.setDefaultEncoding("utf-8");//한글 설정
		return mr;

	}
}
