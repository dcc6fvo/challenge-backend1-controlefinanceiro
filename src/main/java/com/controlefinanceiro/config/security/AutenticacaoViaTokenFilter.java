package com.controlefinanceiro.config.security;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.annotation.Profile;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import com.controlefinanceiro.modelo.Usuario;
import com.controlefinanceiro.repository.UsuarioRepository;

@Profile(value = { "prod","test" })
public class AutenticacaoViaTokenFilter extends OncePerRequestFilter {

	private TokenService tokenService;
	private UsuarioRepository usuarioRepository;

	public AutenticacaoViaTokenFilter(TokenService tokenService, UsuarioRepository usuarioRepository) {
		this.tokenService=tokenService;
		this.usuarioRepository=usuarioRepository;
	}


	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {


		String token = recuperarToken(request); 
		boolean valido  = tokenService.isTokenValido(token);

		if (valido) {
			autenticarCliente(token);
		}
		
		filterChain.doFilter(request, response);
	}


	private void autenticarCliente(String token) {
		Long idUsuario = tokenService.getIdUsuario(token);
		Usuario usuario = usuarioRepository.findById(idUsuario).get();
		UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(usuario, null , usuario.getPerfis());
		SecurityContextHolder.getContext().setAuthentication(authentication);
	}


	public String recuperarToken(HttpServletRequest request) {

		String token = request.getHeader("Authorization");
		if( token == null || token.isEmpty() || !token.startsWith("Bearer ")) {
			return null;
		}

		// para retornar somente o token, sem "Bearer "
		return token.substring(7, token.length());
	}



}
