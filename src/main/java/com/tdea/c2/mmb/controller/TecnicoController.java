package com.tdea.c2.mmb.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tdea.c2.mmb.modelo.Tecnico;
import com.tdea.c2.mmb.service.ITecnicoService;

@RestController
@RequestMapping("/api")
public class TecnicoController {
	
	@Autowired
	private ITecnicoService tecnicoService;
	
	@GetMapping("/tecnicos")
	public ResponseEntity<List<Tecnico>> getAllTecnicos(){
		
		List<Tecnico> tecnicos = tecnicoService.getAllTecnicos();
		
		if (tecnicos == null || tecnicos.isEmpty()) {
			
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.ok(tecnicos);
	}
	
	@GetMapping("/tecnicos/{id}")
	public ResponseEntity<Tecnico> getTecnicoById(@PathVariable("id") int id) {
		
		return ResponseEntity.of(tecnicoService.getTecnicoById(id));
	}
	
	@PostMapping("/tecnicos")
	public ResponseEntity<?> createTecnico(@RequestBody Tecnico tecnico) {
		try {
			Tecnico saved = tecnicoService.createTecnico(tecnico);
			return ResponseEntity.status(HttpStatus.CREATED).body(saved);
		} catch (IllegalArgumentException e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
	}

	@PutMapping("/tecnicos/{id}")
	public ResponseEntity<Tecnico> updateTecnico(@PathVariable("id") Integer id, @RequestBody Tecnico tecnicos) {
		try {
			Tecnico updated = tecnicoService.updateTecnico(id, tecnicos);
			return ResponseEntity.ok(updated);
		} catch (RuntimeException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}
	}
	
	@DeleteMapping("/tecnicos/{id}")
	public ResponseEntity<Tecnico> deleteTecnico(@PathVariable("id")Integer id){
		tecnicoService.deleteTecnico(id);
		return ResponseEntity.noContent().build();
	} 
	
	@PatchMapping("/tecnicos/{id}")
	public ResponseEntity<Tecnico> updateEstadoTec(@PathVariable("id") Integer id, @RequestBody Map<String, Object> cambios) {
		try {
			if (cambios.containsKey("estadoTecnico")) {
				String nuevoEstado = (String) cambios.get("estadoTecnico");
				Tecnico updated = tecnicoService.updateEstadoTecnico(id, nuevoEstado);
				return ResponseEntity.ok(updated);
			}
			return ResponseEntity.badRequest().build();
		} catch (RuntimeException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}
	}
}