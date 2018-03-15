package jp.co.jmas.ecdemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
//@ComponentScan("*")
//@ComponentScan({"jp.co.jmas.ecdemo.dao.*","jp.co.jmas.ecdemo.api"})
//@EntityScan("jp.co.jmas.ecdemo.dao.*")
public class EcDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(EcDemoApplication.class, args);
	}
}
