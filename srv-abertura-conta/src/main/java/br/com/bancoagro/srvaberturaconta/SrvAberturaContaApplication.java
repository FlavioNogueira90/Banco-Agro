package br.com.bancoagro.srvaberturaconta;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EnableFeignClients
@ComponentScan
public class SrvAberturaContaApplication {

	public static void main(String[] args) {
		SpringApplication.run(SrvAberturaContaApplication.class, args);
	}

}
