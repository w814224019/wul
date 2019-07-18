package com.select.wuliu;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@MapperScan("com.select.wuliu.mapper")
@EnableCaching
@EnableScheduling
public class WuliuApplication {
	

	public static void main(String[] args) {
		SpringApplication.run(WuliuApplication.class, args);
	}
	
	

}
