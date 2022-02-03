package com.controlefinanceiro.config.security;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.controlefinanceiro.modelo.Usuario;
import com.controlefinanceiro.repository.UsuarioRepository;

@Service
@Profile(value = { "prod","test" })
public class AutenticacaoService implements UserDetailsService {

	@Autowired
	private UsuarioRepository userRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		Optional<Usuario> u = userRepository.findByEmail(username); 
		
		if(u.isPresent()) {
			UserDetails ud = (UserDetails) u.get();
			return ud;	
		}
		else {
			throw new UsernameNotFoundException("Dados invalidos");
		}
		
	}
	
}
