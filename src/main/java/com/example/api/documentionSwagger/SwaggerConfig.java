package com.example.api.documentionSwagger;

import org.springdoc.core.customizers.OpenApiCustomiser;
import org.springframework.context.annotation.Bean;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.responses.ApiResponse;
import io.swagger.v3.oas.models.responses.ApiResponses;

public class SwaggerConfig {
	@Bean
	public OpenAPI springOpenAPI() {
		
        return new OpenAPI()
				.info(new Info()
					.title("Teste Técnico Java")
					.description("Teste Técnico Java")
					.version("v0.0.1")
				.license(new License()
					.name("Teste Técnico Java")
					.url("https://github.com/mig1998/Desafio-Tecnico-Java-Spring"))
				.contact(new Contact()
					.name("Miguel Araujo")
					.url("https://github.com/mig1998")
					.email("miguelaras@hotmail.com")))
				.externalDocs(new ExternalDocumentation()
					.description("Github")
					.url("https://github.com/allanalves92/practical-test"));
	}

	@Bean
	public OpenApiCustomiser customerGlobalHeaderOpenApiCustomiser() {

		return openApi -> {
			openApi.getPaths().values().forEach(pathItem -> pathItem.readOperations().forEach(operation -> {

				ApiResponses apiResponses = operation.getResponses();

				apiResponses.addApiResponse("200", createApiResponse("Sucesso!"));
				apiResponses.addApiResponse("201", createApiResponse("Objeto Persistido!"));
				apiResponses.addApiResponse("204", createApiResponse("Objeto Excluído!"));
				apiResponses.addApiResponse("400", createApiResponse("Erro na Requisição!"));
				apiResponses.addApiResponse("401", createApiResponse("Acesso Não Autorizado!"));
				apiResponses.addApiResponse("404", createApiResponse("Objeto Não Encontrado!"));
				apiResponses.addApiResponse("500", createApiResponse("Erro na Aplicação!"));

			}));
		};
	}

   
	private ApiResponse createApiResponse(String message) {

		return new ApiResponse().description(message);

	}
	
}

