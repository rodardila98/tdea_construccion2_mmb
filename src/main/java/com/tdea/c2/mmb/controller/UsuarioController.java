package com.tdea.c2.mmb.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tdea.c2.mmb.modelo.Usuario;
import com.tdea.c2.mmb.service.IUsuarioService;

@RestController
@RequestMapping("/api")
public class UsuarioController {
	
	@Autowired
	private IUsuarioService usuarioService;
	
	@GetMapping("/usuarios")
	public ResponseEntity<List<Usuario>> getAllUsuarios(){
		
		List<Usuario> usuarios = usuarioService.getAllUsuarios();
		
		if (usuarios == null || usuarios.isEmpty()) {
			
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.ok(usuarios);
	}
	
	@GetMapping("/usuarios/{id}")
	public ResponseEntity<Usuario> getTecnicoById(@PathVariable("id") int id) {
		
		return ResponseEntity.of(usuarioService.getUsuarioById(id));
	}
	
	@PostMapping("/usuarios")
	public ResponseEntity<?> createUsuario(@RequestBody Usuario usuario) {
		try {
			Usuario saved = usuarioService.createUsuario(usuario);
			return ResponseEntity.status(HttpStatus.CREATED).body(saved);
		} catch (IllegalArgumentException e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
	}
	
	@PutMapping("/usuarios/{id}")
	public ResponseEntity<Usuario> updateUsuario(@PathVariable("id") Integer id, @RequestBody Usuario usuarios) {
		try {
			Usuario updated = usuarioService.updateUsuario(id, usuarios);
			return ResponseEntity.ok(updated);
		} catch (RuntimeException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}
	}
		
	@DeleteMapping("/usuarios/{id}")
	public ResponseEntity<Usuario> deleteUsuario(@PathVariable("id")Integer id){
		usuarioService.deleteUsuario(id);
		return ResponseEntity.noContent().build();
	}
}
