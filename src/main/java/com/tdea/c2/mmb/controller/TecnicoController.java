package com.tdea.c2.mmb.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tdea.c2.mmb.modelo.Tecnico;
import com.tdea.c2.mmb.modelo.Usuario;
import com.tdea.c2.mmb.repository.ITecnicoRepository;

@RestController
@RequestMapping("/api")
public class TecnicoController {
	
	@Autowired
	private ITecnicoRepository tecnicoRepository;
	
	@GetMapping("/tecnicos")
	public ResponseEntity<List<Tecnico>> getAllTecnicos(){
		
		List<Tecnico> tecnicos = tecnicoRepository.findAll();
		
		if (tecnicos == null || tecnicos.isEmpty()) {
			
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.ok(tecnicos);
	}
	
	@GetMapping("/tecnicos/{id}")
	public ResponseEntity<Tecnico> getTecnicoById(@PathVariable("id") int id) {
		
		Optional<Tecnico> opt = tecnicoRepository.findById(id);
		
		return ResponseEntity.of(opt);
	}
	
	@PostMapping("/tecnicos")
	public ResponseEntity<?> createTecnico(@RequestBody Tecnico tecnico) {
		if (tecnico == null) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Tecnico no puede ser nulo");
		}
		if (tecnico.getNombreCompleto() == null || tecnico.getNombreCompleto().trim().isEmpty()) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Campo 'nombreCompleto' es requerido");
		}
		if (tecnico.getTipoDocumento() == null || tecnico.getTipoDocumento().trim().isEmpty()) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Campo 'tipoDocumento' es requerido");
		}
		// numCel debe ser mayor que 0
		if (tecnico.getNumCel() == null || tecnico.getNumCel() <= 0) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Campo 'numCel' debe ser un número válido");
		}
		Tecnico saved = tecnicoRepository.save(tecnico);
		return ResponseEntity.status(HttpStatus.CREATED).body(saved);
	}

	@PutMapping("/tecnicos/{id}")
	public ResponseEntity<Tecnico> updateTecnico(@PathVariable("id") Integer id, @RequestBody Tecnico tecnicos) {
		Tecnico existente = tecnicoRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("No encontrado"));
		existente.setTipoDocumento(tecnicos.getTipoDocumento());
		existente.setNumDocumento(tecnicos.getNumDocumento());
		existente.setNombreCompleto(tecnicos.getNombreCompleto());
		existente.setNumCel(tecnicos.getNumCel());
		existente.setEspecialidad(tecnicos.getEspecialidad());
		existente.setEstadoTecnico(tecnicos.getEstadoTecnico());
		return ResponseEntity.ok(tecnicoRepository.save(existente));
	}
}
