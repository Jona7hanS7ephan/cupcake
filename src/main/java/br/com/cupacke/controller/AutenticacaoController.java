//package br.com.cupacke.controller;
//
//import java.util.ArrayList;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.ResponseEntity;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.Authentication;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.RestController;
//
//import br.com.cupacke.security.JwtTokenUtil;
//import br.com.cupacke.security.JwtUserDetails;
//import io.swagger.annotations.Api;
//import io.swagger.annotations.ApiOperation;
//
//// Classe criada apenas para o Swagger
//// Utilizada apenas para criar o serviço que retorna o Token
//@RestController
//@Api(tags = "A Autenticação/Login/Token")
//@RequestMapping("/aAutenticacao")
//public class AutenticacaoController  {
//
//	@Autowired
//	private AuthenticationManager authenticationManager;
//
//	@Autowired
//	private JwtTokenUtil jwtUtil;
//	
//	
//	@ApiOperation(value = "Método utilizado para buscar o TOKEN do usuário: 99999999999")
//	@PostMapping(value = "/tokenDoUsuario99999999999")
//    public ResponseEntity<?> usuarioFixo() {
//		
//		UsernamePasswordAuthenticationToken dadosLogin = new UsernamePasswordAuthenticationToken("99999999999", "123456", new ArrayList<>());
//        try {
//            Authentication authenticate = authenticationManager.authenticate(dadosLogin);
//            
//            JwtUserDetails jwtUserDetails =  (JwtUserDetails) authenticate.getPrincipal();
//            String token = jwtUtil.generateToken(jwtUserDetails);
//            
//            // return ResponseEntity.ok(new Object(token, "Bearer"));
//            return ResponseEntity.ok(new String("Bearer "+token));
//        } catch (Exception e) {
//            return ResponseEntity.badRequest().build();
//        }
//
//    }
//	
//	@ApiOperation(value = "Método utilizado para buscar o TOKEN de um usuário/senha passado como parametro")
//	@PostMapping(value = "/tokenOutroUsuario")
//    public ResponseEntity<?> outroUsuario(@RequestParam("login") String login, @RequestParam("senha") String senha) {
//		
//		UsernamePasswordAuthenticationToken dadosLogin = new UsernamePasswordAuthenticationToken(login, senha, new ArrayList<>());
//        try {
//            Authentication authenticate = authenticationManager.authenticate(dadosLogin);
//            
//            JwtUserDetails jwtUserDetails =  (JwtUserDetails) authenticate.getPrincipal();
//            String token = jwtUtil.generateToken(jwtUserDetails);
//            
//            // return ResponseEntity.ok(new Object(token, "Bearer"));
//            return ResponseEntity.ok(new String("Bearer "+token));
//        } catch (Exception e) {
//            return ResponseEntity.badRequest().build();
//        }
//
//    }
//
//	
//	
//	
//}
