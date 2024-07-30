package br.com.cupacke;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/* */
@Configuration
@EnableWebMvc
public class CorsConfiguration implements WebMvcConfigurer {

	// Classe criada para resolver o problema de CORS - Control-Allow-Origin
	
	@Override
    public void addCorsMappings(CorsRegistry registry) {
		
		
        registry.addMapping("/**")
        	.allowedOrigins("*")
        	//.allowedOrigins("http://localhost:4200")
            .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS", "HEAD", "TRACE", "CONNECT");
            
           
    }
	
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {

	    registry
	            .addResourceHandler("swagger-ui.html")
	            .addResourceLocations("classpath:/META-INF/resources/");

	    registry
	            .addResourceHandler("/webjars/**")
	            .addResourceLocations("classpath:/META-INF/resources/webjars/");
	}
	
	
}

