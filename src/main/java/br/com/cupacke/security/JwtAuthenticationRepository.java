//package br.com.cupacke.security;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.dao.EmptyResultDataAccessException;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.stereotype.Repository;
//import org.springframework.transaction.annotation.Transactional;
//
//@Repository
//public class JwtAuthenticationRepository {
//	
//	@Autowired
//	private ServidorRepository servidorRepository;
//	
//	@Autowired    
//	private PasswordEncoder passwordEncoder;
//
//	@Transactional(readOnly = true)
//	public UsuarioAutenticado loadUserByUsername(String username) {
//		try {
//			UsuarioAutenticado usuario = servidorRepository.findServidor(username);
//			return usuario;
//		} catch (EmptyResultDataAccessException e) {
//			throw new UsernameNotFoundException(username);
//		}
//	}
//	 
//	@Transactional(readOnly = true)
//	public boolean isFirsAccess(String username) {
//		return servidorRepository.isFirstAccess(username);
//	}
//	
//	@Transactional(readOnly = true)
//	public boolean isTokenFirstAccessValid(String username, String token) {
//		return servidorRepository.istTokenDesbloqueioValido(username, token);
//	}
//	
//	@Transactional
//	public void removeTokenFirstAccess(String username, String senha, String token) {
//		servidorRepository.debloquearUsuario(username, passwordEncoder.encode(senha), token);
//	}
//}
