//package br.com.cupacke.security;
//
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
//
//@Service
//public class JwtUserDetailsService implements UserDetailsService {
//
//	@Override
//	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	JwtAuthenticationRepository repository;
//	
//	public JwtUserDetailsService(JwtAuthenticationRepository repository) {
//		this.repository = repository;
//	}
//
//	@Override
//	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException, AuthenticationConsignusException {
//		
//		UsuarioAutenticado dadosUsuario = repository.loadUserByUsername(username);
//		return new JwtUserDetails(dadosUsuario);
//	}
//	
//	@Transactional(readOnly = true)
//	public boolean isFirsAccess(String username) {
//		return repository.isFirsAccess(username);
//	}
//	
//	@Transactional(readOnly = true)
//	public boolean isTokenFirstAccessValid(String username, String token) {
//		return repository.isTokenFirstAccessValid(username, token);
//	}
//
//	@Transactional
//	public void removeTokenFirstAccess(String username, String senha, String token) {
//		repository.removeTokenFirstAccess(username, senha, token);
//	}
//}
