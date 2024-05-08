package dh.pensionmanagement.webservices.auth;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import lombok.Generated;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
@Generated
@OpenAPIDefinition(info = @Info(title = "Pension-Management-Authorization",description = "Authorization & Authentication"))
public class PensionManagementAuthorizationApplication {

	public static void main(String[] args) {
		SpringApplication.run(PensionManagementAuthorizationApplication.class, args);
	}

}
