package br.com.homeoffice.apigerirpessoas;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition(info = @Info(title = "API Cadastro de Pessoas e Endereços", version = "1.0", description = "Cadastro de pessoas e endereços associados as pessoas"))
public class ApigerirpessoasApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApigerirpessoasApplication.class, args);
	}
	//link do Swagger http://localhost:8080/swagger-ui/index.html

}
