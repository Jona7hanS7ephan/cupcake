//package br.com.cupacke.security;
//
//import java.io.IOException;
//import java.util.Date;
//
//import javax.servlet.ServletException;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
//import org.springframework.security.core.AuthenticationException;
//import org.springframework.security.web.authentication.AuthenticationFailureHandler;
//
//public class JwtAuthenticationFailureHandler implements AuthenticationFailureHandler {
//
//	@Override
//	public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
//			AuthenticationException exception) throws IOException, ServletException {
//		response.setStatus(401);
//		response.setContentType("application/json");
//		response.getWriter().append(json());
//	}
//
//	private String json() {
//		long date = new Date().getTime();
//		return "{\"timestamp\": " + date + ", " + "\"status\": 401, " + "\"error\": \"Não autorizado\", "
//				+ "\"message\": \"Usuário ou senha inválido\", " + "\"path\": \"/login\"}";
//	}
//}