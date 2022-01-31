package com.controlefinanceiro.form;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

public class LoginForm {

	@NotNull
	@Email
	private String email;
	
	@NotNull
	private String senha;
	
	public LoginForm() { }
	
	public LoginForm(String email, String senha) {
		this.email = email;
		this.senha = senha;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}
	public String getEmail() {
		return email;
	}
	public String getSenha() {
		return senha;
	}

	public UsernamePasswordAuthenticationToken converter() {
		return new UsernamePasswordAuthenticationToken(email, senha);
	}	
	
	
}
