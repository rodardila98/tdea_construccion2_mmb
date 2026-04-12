package com.tdea.c2.mmb.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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
	public ResponseEntity<List<Usuario>> getAllUsuarios(){
		
		List<Usuario> usuarios = usuarioRepository.findAll();
		
		if (usuarios == null || usuarios.isEmpty()) {
			
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.ok(usuarios);
	}
	
	@GetMapping("/usuarios/{id}")
	public ResponseEntity<Usuario> getTecnicoById(@PathVariable("id") int id) {
		
		Optional<Usuario> opt = usuarioRepository.findById(id);
		
		return ResponseEntity.of(opt);
	}
	
	@PostMapping("/usuarios")
	public ResponseEntity<?> createUsuario(@RequestBody Usuario usuario) {
		if (usuario == null) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Usuario no puede ser nulo");
		}
		if (usuario.getTipoDocumento() == null || usuario.getTipoDocumento().trim().isEmpty()) {
			
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Campo 'tipoDocumento' es requerido");
		}
		if (usuario.getNombreCompleto() == null || usuario.getNombreCompleto().trim().isEmpty()) {
			
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Campo 'nombreCompleto' es requerido");
		}
		if (usuario.getCorreo() == null || usuario.getCorreo().trim().isEmpty()) {
			
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Campo 'correo' es requerido");
		}
		if (usuario.getNumCel() <= 0) {
			
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Campo 'numCel' debe ser un número válido");
		}
		Usuario saved = usuarioRepository.save(usuario);
		return ResponseEntity.status(HttpStatus.CREATED).body(saved);
	}
	
	

}
