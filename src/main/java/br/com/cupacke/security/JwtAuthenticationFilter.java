//package br.com.cupacke.security;
//
//import java.io.IOException;
//import java.util.ArrayList;
//import java.util.Date;
//
//import javax.servlet.FilterChain;
//import javax.servlet.ServletException;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.AuthenticationException;
//import org.springframework.security.web.authentication.AuthenticationFailureHandler;
//import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
//
//import com.fasterxml.jackson.databind.ObjectMapper;
//import br.com.cupacke.exception.AuthenticationCupcakeException;
//
//public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
//
//	private AuthenticationManager authenticationManager;
//
//	private JwtTokenUtil jwtUtil;
//	
//	private JwtUserDetailsService service;
//	
//	public JwtAuthenticationFilter(AuthenticationManager authenticationManager, JwtTokenUtil jwtUtil, JwtUserDetailsService service) {
//		setAuthenticationFailureHandler(new JWTAuthenticationFailureHandler());
//		this.authenticationManager = authenticationManager;
//		this.jwtUtil = jwtUtil;
//		this.service = service;
//	}
//
//	@Override
//	public Authentication attemptAuthentication(HttpServletRequest req, HttpServletResponse res) throws AuthenticationException {
//
//		try {
//			JwtAuthenticationRequest creds = new ObjectMapper().readValue(req.getInputStream(), JwtAuthenticationRequest.class);
//			if (this.service.isFirsAccess(creds.getUsuario())) {
//				if (this.service.isTokenFirstAccessValid(creds.getUsuario(), creds.getDsTokenFirstAccess())) {
//					this.service.removeTokenFirstAccess(creds.getUsuario(), creds.getSenha(), creds.getDsTokenFirstAccess());
//				} else {
//					throw new AuthenticationCupcakeException("Primeiro acesso! Deve ser feito pelo link enviado ao email.");
//				}
//			}
//			UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(creds.getUsuario(), creds.getSenha(), new ArrayList<>());
//			Authentication auth = authenticationManager.authenticate(authToken);
//			return auth;
//		} catch (IOException e) {
//			throw new RuntimeException(e);
//		}
//	}
//
//	@Override
//	protected void successfulAuthentication(HttpServletRequest req, HttpServletResponse res, FilterChain chain, Authentication auth) throws IOException, ServletException {
//		JwtUserDetails jwtUserDetails =  (JwtUserDetails) auth.getPrincipal();
//		String token = jwtUtil.generateToken(jwtUserDetails);
//		res.setStatus(200);
//		res.setContentType("application/json; charset=UTF-8");
//		res.getWriter().append(token(token));
//	}
//
//	private CharSequence token(String token) {
//		return "{\"authorization\": \"" + token + "\"} " ;
//	}
//
//	private class JWTAuthenticationFailureHandler implements AuthenticationFailureHandler {
//		@Override
//		public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
//				AuthenticationException exception) throws IOException, ServletException {
//			response.setStatus(400);
//			response.setContentType("application/json");
//			response.getWriter().append(json(exception.getMessage()));
//		}
//
//		private String json(String error) {
//			long date = new Date().getTime();
//			return "{\"timestamp\": " + date + ", " + "\"status\": 400, " + "\"error\": \"Não autorizado\", "
//					+ "\"message\": \""+error+"\", " + "\"path\": \"/login\"}";
//		}
//	}
//}
