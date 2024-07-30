package br.com.cupacke;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RequestMethod;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.builders.ResponseMessageBuilder;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ResponseMessage;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfiguracao {
	
		
//	// Para utilizar o Token/Autenticação no Swagger
//	private ApiKey apiKey() { 
//	    return new ApiKey("JWT", "Authorization", "header"); 
//	}
//	
//	// Para utilizar o Token/Autenticação no Swagger
//	private SecurityContext securityContext() { 
//	    return SecurityContext.builder().securityReferences(defaultAuth()).build(); 
//	}
//	
//	// Para utilizar o Token/Autenticação no Swagger
//	private List<SecurityReference> defaultAuth() { 
//	    AuthorizationScope authorizationScope = new AuthorizationScope("global", "accessEverything"); 
//	    AuthorizationScope[] authorizationScopes = new AuthorizationScope[1]; 
//	    authorizationScopes[0] = authorizationScope; 
//	    return Arrays.asList(new SecurityReference("JWT", authorizationScopes)); 
//	}
	
	private ApiInfo apiInfo() {
	    return new ApiInfo(
	      "Cupcake API",
	      "API criada utilizando a linguagem de programação Java (Spring Boot)",
	      "1.0",
	      null, // "Termos de serviço",
	      null, // new Contact("Sallo Szrajbman", "www.baeldung.com", "salloszraj@gmail.com"),
	      null, // "Licença de API",
	      null, // "URL da licença da API",
	      Collections.emptyList());
	}
	
	@Bean
    public Docket api() {  //Obs.: Docket e diferente de Docker 
        return new Docket(DocumentationType.SWAGGER_2)
          .apiInfo(apiInfo())
          //.securityContexts(Arrays.asList(securityContext()))
          //.securitySchemes(Arrays.asList(apiKey()))
          .select()
          //.apis(RequestHandlerSelectors.any())  // Para definir que todas as apis e caminhos estarão disponíveis
          .apis(RequestHandlerSelectors.basePackage("br.com.cupacke.controller"))  // Deixa disponível apenas as apis nesse pacote
          .paths(PathSelectors.any())
          .build()
          .globalResponseMessage(RequestMethod.GET, listaMensagemMetodosGet)  // lista padrão de mensagens de retorno para os metodos GET
          .globalResponseMessage(RequestMethod.POST, listaMensagemMetodosPost);  // lista padrão de mensagens de retorno para os metodos POST
         
        // http://localhost:8082/cupacke-cupacke/swagger-ui
        // http://localhost:8082/cupacke-cupacke/swagger-ui.html#/
    }
	
	// Define uma lista padrão de retorno para os metodos GET
	final List<ResponseMessage> listaMensagemMetodosGet = Arrays.asList(
				new ResponseMessageBuilder().code(200)
								            .message("Sucesso")
								            .responseModel(new ModelRef("SucessoResponse"))
								            .build(),
				new ResponseMessageBuilder().code(204)
								            .message("Não encontrado")
								            //.responseModel(new ModelRef("NaoEncontradoResponse"))
								            .build(),				            
//		        new ResponseMessageBuilder().code(400)
//		                                    .message("Requisição inválida ao serviço")
//		                                    //.responseModel(new ModelRef("InvalidDataResponse"))
//		                                    .build(),
//		        new ResponseMessageBuilder().code(401)
//		                                    .message("Não possui credenciais de autenticação")
//		                                    //.responseModel(new ModelRef("UnauthenticatedResponse"))
//		                                    .build(),
//		        new ResponseMessageBuilder().code(403)
//		                                    .message("É necessário autorização para acessar essa api")
//		                                    //.responseModel(new ModelRef("ForbiddenResponse"))
//		                                    .build(),
		        new ResponseMessageBuilder().code(422)
		                                    .message("Parâmetro(s) inválido(s)")
		                                    //.responseModel(new ModelRef("ParametrosInvalidosResponse"))
		                                    .build(),                            
		        new ResponseMessageBuilder().code(500)
		                                    .message("Ocorreu um erro interno ao consumir o serviço")
		                                    //.responseModel(new ModelRef("InternalServerError"))
		                                    .build()
	);

	// Define uma lista padrão de retorno para os metodos POST
	final List<ResponseMessage> listaMensagemMetodosPost = Arrays.asList(
			new ResponseMessageBuilder().code(200)
	            .message("Sucesso")
	            .responseModel(new ModelRef("SucessoResponse"))
	            .build(),			            
//			new ResponseMessageBuilder().code(400)
//			            .message("Requisição inválida ao serviço")
//			            //.responseModel(new ModelRef("InvalidDataResponse"))
//			            .build(),
//			new ResponseMessageBuilder().code(401)
//			            .message("Não possui credenciais de autenticação")
//			            //.responseModel(new ModelRef("UnauthenticatedResponse"))
//			            .build(),
//			new ResponseMessageBuilder().code(403)
//			            .message("É necessário autorização para acessar essa api")
//			            //.responseModel(new ModelRef("ForbiddenResponse"))
//			            .build(),
			new ResponseMessageBuilder().code(422)
			            .message("Parâmetro(s) inválido(s)")
			            //.responseModel(new ModelRef("ParametrosInvalidosResponse"))
			            .build(),                            
			new ResponseMessageBuilder().code(500)
			            .message("Ocorreu um erro interno ao consumir o serviço")
			            //.responseModel(new ModelRef("InternalServerError"))
			            .build()
			);				

}


/*
package br.com.cupacke;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RequestMethod;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.builders.ResponseMessageBuilder;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ApiKey;
import springfox.documentation.service.AuthorizationScope;
import springfox.documentation.service.ResponseMessage;
import springfox.documentation.service.SecurityReference;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfiguracao {
	
		
	// Para utilizar o Token/Autenticação no Swagger
	private ApiKey apiKey() { 
	    return new ApiKey("JWT", "Authorization", "header"); 
	}
	
	// Para utilizar o Token/Autenticação no Swagger
	private SecurityContext securityContext() { 
	    return SecurityContext.builder().securityReferences(defaultAuth()).build(); 
	}
	
	// Para utilizar o Token/Autenticação no Swagger
	private List<SecurityReference> defaultAuth() { 
	    AuthorizationScope authorizationScope = new AuthorizationScope("global", "accessEverything"); 
	    AuthorizationScope[] authorizationScopes = new AuthorizationScope[1]; 
	    authorizationScopes[0] = authorizationScope; 
	    return Arrays.asList(new SecurityReference("JWT", authorizationScopes)); 
	}
	
	private ApiInfo apiInfo() {
	    return new ApiInfo(
	      "Servidor API REST",
	      "ATENÇÃO: Para utilizar essa API, é preciso de um TOKEN. \n" +
	              " Faça a autenticação para obter esse TOKEN. \n "+
	              " Exemplo: Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiI4MjUzMDM...",
	      "1.0",
	      null, // "Termos de serviço",
	      null, // new Contact("Sallo Szrajbman", "www.baeldung.com", "salloszraj@gmail.com"),
	      null, // "Licença de API",
	      null, // "URL da licença da API",
	      Collections.emptyList());
	}
	
	@Bean
    public Docket api() {  //Obs.: Docket e diferente de Docker 
        return new Docket(DocumentationType.SWAGGER_2)
          .apiInfo(apiInfo())
          .securityContexts(Arrays.asList(securityContext()))
          .securitySchemes(Arrays.asList(apiKey()))
          .select()
          // .apis(RequestHandlerSelectors.any())  // Para definir que todas as apis e caminhos estarão disponíveis
          .apis(RequestHandlerSelectors.basePackage("br.com.cupacke.controller"))  // Deixa disponível apenas as apis nesse pacote
          .paths(PathSelectors.any())
          .build()
          .globalResponseMessage(RequestMethod.GET, listaMensagemMetodosGet)  // lista padrão de mensagens de retorno para os metodos GET
          .globalResponseMessage(RequestMethod.POST, listaMensagemMetodosPost);  // lista padrão de mensagens de retorno para os metodos POST
         
        // http://localhost:8082/cupacke-cupacke/swagger-ui
        // http://localhost:8082/cupacke-cupacke/swagger-ui.html#/
    }
	
	// Define uma lista padrão de retorno para os metodos GET
	final List<ResponseMessage> listaMensagemMetodosGet = Arrays.asList(
				new ResponseMessageBuilder().code(200)
								            .message("Sucesso")
								            .responseModel(new ModelRef("SucessoResponse"))
								            .build(),
				new ResponseMessageBuilder().code(204)
								            .message("Não encontrado")
								            .responseModel(new ModelRef("NaoEncontradoResponse"))
								            .build(),				            
		        new ResponseMessageBuilder().code(400)
		                                    .message("Requisição inválida ao serviço")
		                                    .responseModel(new ModelRef("InvalidDataResponse"))
		                                    .build(),
		        new ResponseMessageBuilder().code(401)
		                                    .message("Não possui credenciais de autenticação")
		                                    .responseModel(new ModelRef("UnauthenticatedResponse"))
		                                    .build(),
		        new ResponseMessageBuilder().code(403)
		                                    .message("É necessário autorização para acessar essa api")
		                                    .responseModel(new ModelRef("ForbiddenResponse"))
		                                    .build(),
		        new ResponseMessageBuilder().code(422)
		                                    .message("Parâmetro(s) inválido(s)")
		                                    .responseModel(new ModelRef("ParametrosInvalidosResponse"))
		                                    .build(),                            
		        new ResponseMessageBuilder().code(500)
		                                    .message("Ocorreu um erro interno ao consumir o serviço")
		                                    .responseModel(new ModelRef("InternalServerError"))
		                                    .build()
	);

	// Define uma lista padrão de retorno para os metodos POST
	final List<ResponseMessage> listaMensagemMetodosPost = Arrays.asList(
			new ResponseMessageBuilder().code(200)
	            .message("Sucesso")
	            .responseModel(new ModelRef("SucessoResponse"))
	            .build(),			            
			new ResponseMessageBuilder().code(400)
			            .message("Requisição inválida ao serviço")
			            .responseModel(new ModelRef("InvalidDataResponse"))
			            .build(),
			new ResponseMessageBuilder().code(401)
			            .message("Não possui credenciais de autenticação")
			            .responseModel(new ModelRef("UnauthenticatedResponse"))
			            .build(),
			new ResponseMessageBuilder().code(403)
			            .message("É necessário autorização para acessar essa api")
			            .responseModel(new ModelRef("ForbiddenResponse"))
			            .build(),
			new ResponseMessageBuilder().code(422)
			            .message("Parâmetro(s) inválido(s)")
			            .responseModel(new ModelRef("ParametrosInvalidosResponse"))
			            .build(),                            
			new ResponseMessageBuilder().code(500)
			            .message("Ocorreu um erro interno ao consumir o serviço")
			            .responseModel(new ModelRef("InternalServerError"))
			            .build()
			);				

}

*/