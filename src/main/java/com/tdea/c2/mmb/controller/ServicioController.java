package com.tdea.c2.mmb.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tdea.c2.mmb.modelo.Servicio;
import com.tdea.c2.mmb.service.IServicioService;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins ="http://127.0.0.1:5500/")

public class ServicioController {
	
	@Autowired
	private IServicioService servicioService;
	
	@GetMapping("/servicios")
	public ResponseEntity<List<Servicio>> getAllServicios(){
		List<Servicio> servicios = servicioService.getAllServicios();
		
		if (servicios == null || servicios.isEmpty()) {
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.ok(servicios);
	}
	
	@GetMapping("/servicios/{id}")
	public ResponseEntity<Servicio> getServicioById(@PathVariable("id") Integer id) {
		return ResponseEntity.of(servicioService.getServicioById(id));
	}
	
	@PostMapping("/servicios")
	public ResponseEntity<?> createServicio(@RequestBody Servicio servicio) {
		try {
			Servicio saved = servicioService.createServicio(servicio);
			return ResponseEntity.status(HttpStatus.CREATED).body(saved);
		} catch (IllegalArgumentException e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		} catch (RuntimeException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		}
	}
	
	@PutMapping("/servicios/{id}")
	public ResponseEntity<?> updateServicio(@PathVariable("id") Integer id, @RequestBody Servicio servicios) {
		try {
			Servicio updated = servicioService.updateServicio(id, servicios);
			return ResponseEntity.ok(updated);
		} catch (IllegalArgumentException e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		} catch (RuntimeException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
		}
	}
	
	@DeleteMapping("/servicios/{id}")
	public ResponseEntity<Servicio> deleteServicio(@PathVariable("id") Integer id){
		servicioService.deleteServicio(id);
		return ResponseEntity.noContent().build();
	} 
	
	@PatchMapping("/servicios/{id}")
	public ResponseEntity<?> updateEstadoServ(@PathVariable("id") Integer id, @RequestBody Map<String, Object> cambios) {
		try {
			if (cambios.containsKey("estadoServicio")) {
				String nuevoEstado = (String) cambios.get("estadoServicio");
				Servicio updated = servicioService.updateEstadoServicio(id, nuevoEstado);
				return ResponseEntity.ok(updated);
			}
			return ResponseEntity.badRequest().body("Campo 'estadoServicio' es requerido");
		} catch (RuntimeException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
		}
	}
}