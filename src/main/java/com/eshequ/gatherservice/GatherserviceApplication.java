package com.eshequ.gatherservice;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@MapperScan("com.eshequ.gatherservice.mapper")
@EnableTransactionManagement //开启事务管理
public class GatherserviceApplication {

	public static void main(String[] args) {
		SpringApplication.run(GatherserviceApplication.class, args);
	}
}
