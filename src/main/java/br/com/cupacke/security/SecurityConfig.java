//package br.com.cupacke.security;
//
//import java.util.Arrays;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.core.env.Environment;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.builders.WebSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.security.config.http.SessionCreationPolicy;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.web.authentication.AuthenticationFailureHandler;
//import org.springframework.web.cors.CorsConfiguration;
//import org.springframework.web.cors.CorsConfigurationSource;
//import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
//
//@Configuration
//@EnableWebSecurity
//@EnableGlobalMethodSecurity(prePostEnabled = true)
//public class SecurityConfig extends WebSecurityConfigurerAdapter {
//
//	private JwtUserDetailsService userDetailsService;
//
//	private Environment env;
//
//	private JwtTokenUtil jwtUtil;
//	
//	private JwtUserDetailsService service;
//
//
//	public SecurityConfig(JwtUserDetailsService userDetailsService, Environment env, JwtTokenUtil jwtUtil, JwtUserDetailsService service) {
//		this.userDetailsService = userDetailsService;
//		this.env = env;
//		this.jwtUtil = jwtUtil;
//		this.service = service;
//	}
//
//	@Override
//	public void configure(AuthenticationManagerBuilder auth) throws Exception {
//		auth.userDetailsService(userDetailsService).passwordEncoder(encoder());
//	}
//
//	@Override
//	protected void configure(HttpSecurity http) throws Exception {
//		if (Arrays.asList(env.getActiveProfiles()).contains("test")) {
//			http.headers().frameOptions().disable();
//		} 
//		http.cors().and().csrf().disable().anonymous().disable().authorizeRequests().antMatchers("/solicitarRecuperacaoSenha/**").permitAll().antMatchers("/checkUserStatus/**").permitAll().anyRequest().permitAll();
//		http.addFilter(new JwtAuthenticationFilter(authenticationManager(), jwtUtil, service));
//		http.addFilter(new JwtAuthorizationFilter(authenticationManager(), jwtUtil, userDetailsService, env));
//		http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
//
//	}
//
//	@Bean
//	public static PasswordEncoder encoder() {
//		return new BCryptPasswordEncoder();
//	}
//
//	public static void main(String[] args) {
//		System.out.println(new BCryptPasswordEncoder().encode("123456"));
//	}
//
//	@Bean
//	public AuthenticationFailureHandler exceptionHandling() {
//		return new JwtAuthenticationFailureHandler();
//	}
//	
//	@Bean
//	CorsConfigurationSource corsConfigurationSource() {
//		CorsConfiguration configuration = new CorsConfiguration().applyPermitDefaultValues();
//		configuration.setAllowedMethods(Arrays.asList("POST", "GET", "PUT", "DELETE", "PATCH"));
//		final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
//		source.registerCorsConfiguration("/**", configuration);
//		return source;
//	}
//
//	/**
//	 * Este método faz a configuração da necessidade de autenticação para se acessar um determinado
//	 * end point. 
//	 * 
//	 * Para possibilitar end points de acesso anônimo, ou seja, end points que não pedem um token
//	 * no cabeçalho da requisição, a configuração <code>.mvcMatchers("/api/backendVersion/")</code>
//	 * pode ser adicionada neste método. Nesse exemplo, o end point /api/backendVersion/ pode ser acessado 
//	 * de forma anônima.
//	 * 
//	 * End points de acesso anônimo também não passam pelo crivo da autorização. Eles são, na prática, 
//	 * ignorados pela engrenagem de segurança.
//	 */
//	@Override
//	public void configure(WebSecurity web) throws Exception {
//	    web.ignoring()
//	    	// A configuração abaixo é importante para o serviço de retorno da versão do RS.
//	    	// .mvcMatchers("/api/backendVersion/**")	
//	    	.mvcMatchers("/aAutenticacao/**")
//	    	.mvcMatchers("/checkUserStatus/**")
//	    	.mvcMatchers("/servidor/confirmarResetCodigoAutorizacao/**")
//	    	
//	    	// A configuração abaixo é importante para o correto funcionamento do swagger
//	    	.antMatchers("/csrf/**", "/lib/*", "/css/*", "/images/**", "/webjars/**", 
//	    		"/swagger-ui.html", "/swagger-ui.js", "/swagger-ui.min.js", "/springfox.js",
//            	"/csrf/springfox.js", "/swagger-resources/**", "/configuration/ui",
//            	"/configuration/security", "/api-docs", "/v2/api-docs", "/fonts/*", "/api-docs/*", 
//            	"/api-docs/default/*", "/o2c.html", "/hystrix/**");
//	}
//	
//	// Para criar o login/autenticacao no swagger
//	@Override
//	@Bean
//	public AuthenticationManager authenticationManagerBean() throws Exception {
//	    return super.authenticationManagerBean();
//	}
//}