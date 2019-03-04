package com.example.cutomer.timer;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class TimerTask {
	private int count = 0;
	private static final SimpleDateFormat dateFormat = new SimpleDateFormat(
			"HH:mm:ss");

	//使用@Scheduled注解，开启一个定时任务
	//@Scheduled(cron = "*/6 * * * * ?")
	private void process() {
		System.out.println("this is scheduler task runing  " + (count++));
	}

	//@Scheduled(fixedRate = 6000)
	public void reportCurrentTime() {
		System.out.println("现在时间：" + dateFormat.format(new Date()));
	}
}
