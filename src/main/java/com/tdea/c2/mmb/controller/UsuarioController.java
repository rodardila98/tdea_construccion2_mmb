package com.tdea.c2.mmb.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tdea.c2.mmb.modelo.Usuario;
import com.tdea.c2.mmb.repository.IUsuarioRepository;

@RestController
@RequestMapping("/api")
public class UsuarioController {
	
	@Autowired
	private IUsuarioRepository usuarioRepository;
	
	@GetMapping("/usuarios")
	public List<Usuario> getAllUsuarios(){
		
		return usuarioRepository.findAll();
	}
	

}
