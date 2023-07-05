package com.learnwithankit.springbootrestfulwebservicesankit;

import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
//for swagger openapi doc
@OpenAPIDefinition(
		info = @Info(
				title = "spring boot restful webservice learnwithankit",
				description = "spring boot restful webservice learnwithankit",
				version = "v1.0",
				contact = @Contact(
						name = "ankit",
						email = "learnwithankit@gmail.com",
						url = "learnwithankit.com"
				),
				license = @License(
						name = "Apache 2.0",
						url = "learnwithankit.com/license"
				)
		),
		externalDocs = @ExternalDocumentation(
				description = "spring boot user management",
				url = "learnwithankit.com/user-management.html"
		)
)
public class SpringbootRestfulWebservicesAnkitApplication {

	@Bean
	public ModelMapper modelMapper(){
		return new ModelMapper();
	}
	public static void main(String[] args) {
		SpringApplication.run(SpringbootRestfulWebservicesAnkitApplication.class, args);
	}

}
