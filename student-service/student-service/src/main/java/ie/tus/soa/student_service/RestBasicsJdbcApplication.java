package ie.tus.soa.student_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class RestBasicsJdbcApplication {

	public static void main(String[] args) {
		SpringApplication.run(RestBasicsJdbcApplication.class, args);
	}

}
