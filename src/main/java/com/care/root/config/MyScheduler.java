package com.care.root.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

@Configuration
@EnableScheduling //이 어노테이션을 사용하면 xml설정을 굳이 안해도 스케줄러 실행 가능
public class MyScheduler {
	@Scheduled(cron="0-59 * * * * *")//cron 표현식 (* 는 각 초/, 분,시,일,월 을 의미), 설정 방식은 많다
	public void testSc() {
		System.out.println("10초에 한번씩 실행");
	}

}
