package com.kv;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("com.kv")
public class KvStoreApplication {

	public static void main(String[] args) {
		SpringApplication.run(KvStoreApplication.class, args);
	}
}
