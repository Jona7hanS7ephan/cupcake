//package br.com.cupacke.security;
//
//import java.io.Serializable;
//
//public class JwtAuthenticationRequest implements Serializable {
//
//	private static final long serialVersionUID = -2576609066924901959L;
//	
//	private String usuario;
//	private String senha;
//	private String dsTokenFirstAccess;
//	
//	public JwtAuthenticationRequest() {
//		super();
//	}
//
//	public JwtAuthenticationRequest(String usuario, String senha) {
//		super();
//		this.usuario = usuario;
//		this.senha = senha;
//	}
//	
//	public JwtAuthenticationRequest(String usuario, String senha, String dsTokenFirstAccess) {
//		super();
//		this.usuario = usuario;
//		this.senha = senha;
//		this.dsTokenFirstAccess = dsTokenFirstAccess;
//	}
//
//	public String getDsTokenFirstAccess() {
//		return dsTokenFirstAccess;
//	}
//
//	public void setDsTokenFirstAccess(String dsTokenFirstAccess) {
//		this.dsTokenFirstAccess = dsTokenFirstAccess;
//	}
//
//	public String getUsuario() {
//		return usuario;
//	}
//
//	public void setUsuario(String usuario) {
//		this.usuario = usuario;
//	}
//
//	public String getSenha() {
//		return senha;
//	}
//
//	public void setSenha(String senha) {
//		this.senha = senha;
//	}
//}