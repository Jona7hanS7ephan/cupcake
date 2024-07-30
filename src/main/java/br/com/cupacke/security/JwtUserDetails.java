//package br.com.cupacke.security;
//
//import java.util.ArrayList;
//import java.util.Collection;
//import java.util.List;
//
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.security.core.userdetails.UserDetails;
//
//
//
//public class JwtUserDetails implements UserDetails {
//
//	private static final long serialVersionUID = 3537736217230242314L;
//	
//	
//
//	private String login;
//
//	private String senha;
//
//	private boolean usuarioCancelado;
//
//	private boolean usuarioBloqueado;
//
//	private boolean senhaExpirada;
//
//	private Collection<? extends GrantedAuthority> authorities;
//
//
//	@Override
//	public Collection<? extends GrantedAuthority> getAuthorities() {
//		return this.authorities;
//	}
//
//	@Override
//	public String getPassword() {
//		return senha;
//	}
//
//	@Override
//	public String getUsername() {
//		return login;
//	}
//
//	@Override
//	public boolean isAccountNonExpired() {
//		return !this.senhaExpirada;
//	}
//
//	@Override
//	public boolean isAccountNonLocked() {
//		return !this.usuarioBloqueado;
//	}
//
//	@Override
//	public boolean isCredentialsNonExpired() {
//		return !this.senhaExpirada;
//	}
//
//	@Override
//	public boolean isEnabled() {
//		return !this.usuarioCancelado;
//	}
//
//
//}
