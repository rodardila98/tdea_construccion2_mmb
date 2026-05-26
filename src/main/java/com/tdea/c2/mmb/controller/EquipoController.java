package com.tdea.c2.mmb.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tdea.c2.mmb.modelo.Equipo;
import com.tdea.c2.mmb.service.IEquipoService;

@RestController //Cualquier método retorna en http json
@RequestMapping("/api") //Asigna las solicitudes http a métodos dentro del controlador
@CrossOrigin(origins ="http://127.0.0.1:5500/")

public class EquipoController {
	
	@Autowired //Crea un objeto @Repository en este caso y lo inyecta aquí
	private IEquipoService equipoService;
	
	@GetMapping("/equipos")
	public ResponseEntity<List<Equipo>> getAllEquipos(){
		List<Equipo> equipos = equipoService.getAllEquipos();
		
		if (equipos == null || equipos.isEmpty()) {
			
			// Muy básica: si no hay equipos, devolvemos 204 No Content
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.ok(equipos);
	}
	
	@GetMapping("/equipos/{id}")
	public ResponseEntity<Equipo> getEquipoById(@PathVariable("id") Long id){
		return ResponseEntity.of(equipoService.getEquipoById(id));
	}
	
	@PostMapping("/equipos")
	public ResponseEntity<?> createEquipo(@RequestBody Equipo equipo) {
		try {
			Equipo saved = equipoService.createEquipo(equipo);
			return ResponseEntity.status(HttpStatus.CREATED).body(saved);
		} catch (IllegalArgumentException e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
	}
	
	@PutMapping("/equipos/{id}")
	public ResponseEntity<Equipo> updateEquipo(@PathVariable("id") Long id, @RequestBody Equipo equipo) {
		try {
			Equipo updated = equipoService.updateEquipo(id, equipo);
			return ResponseEntity.ok(updated);
		} catch (RuntimeException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}
	}
	
	@DeleteMapping("/equipos/{id}")
	public ResponseEntity<Equipo> deleteEquipo(@PathVariable("id") Long id){
		equipoService.deleteEquipo(id);
		return ResponseEntity.noContent().build();
		
		
	}
}



