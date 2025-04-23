package com.mc.resourcesshareback;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EntityScan(basePackages = "com.mc.resourcesshareback.pojo") 
@EnableJpaRepositories(basePackages = "com.mc.resourcesshareback.repository")  // 指定 JPA Repository 的位置
@EnableScheduling
public class ResourcessharebackApplication {

	public static void main(String[] args) {
		SpringApplication.run(ResourcessharebackApplication.class, args);
	}

}
