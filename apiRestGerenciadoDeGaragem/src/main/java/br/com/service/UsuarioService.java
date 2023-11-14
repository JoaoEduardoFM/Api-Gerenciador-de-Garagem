package br.com.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.config.exception.SenhaInvalidaException;
import br.com.config.security.JwtUtil;
import br.com.model.dto.CredenciaisDto;
import br.com.model.dto.SessaoDto;

@Service
public class UsuarioService {
	@Autowired
	private JwtUtil jwtUtil;

	@Autowired
	private PasswordEncoder passwordEncoder;

	
	@Autowired
	private UsuarioRepository usuarioRepository;

	public SessaoDto autenticar(CredenciaisDto credenciais){
		
		if(credenciais.getLogin().equals("adm") && credenciais.getSenha().equals("adm")) {
					
			SessaoDto sessao = new SessaoDto();
			
			sessao.setToken("Bearer "+jwtUtil.generateToken(credenciais.getLogin()));
			sessao.setDataInicio(new Date(System.currentTimeMillis()));
			sessao.setDataFim(new Date(System.currentTimeMillis() + jwtUtil.getExpiration()));
			
			return sessao;
		}
			 throw new SenhaInvalidaException(); 
		}
	}
