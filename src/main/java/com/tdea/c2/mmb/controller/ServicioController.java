package com.tdea.c2.mmb.controller;

import java.util.List;
import java.util.Optional;

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

import com.tdea.c2.mmb.modelo.Servicio;
import com.tdea.c2.mmb.modelo.Tecnico;
import com.tdea.c2.mmb.repository.IServicioRepository;

@RestController
@RequestMapping("/api")
public class ServicioController {
	
	@Autowired
	private IServicioRepository servicioRepository;
	
	@GetMapping("/servicios")
	public ResponseEntity<List<Servicio>> getAllServicios(){
		
		List<Servicio> servicios = servicioRepository.findAll();
		
		if (servicios == null || servicios.isEmpty()) {
			
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.ok(servicios);
	}
	
	@GetMapping("/servicios/{id}")
	public ResponseEntity<Servicio> getServicioById(@PathVariable("id") Integer id) {
		
		Optional<Servicio> opt = servicioRepository.findById(id);
		return ResponseEntity.of(opt);
	}
	
	@PostMapping("/servicios")
	public ResponseEntity<?> createServicio(@RequestBody Servicio servicio) {
		if (servicio == null) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Servicio no puede ser nulo");
		}
		// Validaciones muy básicas: tipo y estado son requeridos
		if (servicio.getTipoServicio() == null || servicio.getTipoServicio().trim().isEmpty()) {
			
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Campo 'tipoServicio' es requerido");
		}
		if (servicio.getEstadoServicio() == null || servicio.getEstadoServicio().trim().isEmpty()) {
			
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Campo 'estadoServicio' es requerido");
		}
		// fechaServicio podría validarse más, pero por ahora comprobamos no nulo
		if (servicio.getFechaServicio() == null) {
			
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Campo 'fechaServicio' es requerido");
		}
		Servicio saved = servicioRepository.save(servicio);
		return ResponseEntity.status(HttpStatus.CREATED).body(saved);
	}
	
	@PutMapping("/servicios/{id}")
	public ResponseEntity<Servicio> updateTecnico(@PathVariable("id") Integer id, @RequestBody Servicio servicios) {
		Servicio existente = servicioRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("No encontrado"));
		existente.setIdServicio(servicios.getIdServicio());
		existente.setFechaServicio(servicios.getFechaServicio());
		existente.setHoraServicio(servicios.getHoraServicio());
		existente.setTipoServicio(servicios.getTipoServicio());
		existente.setEstadoServicio(servicios.getEstadoServicio());
		return ResponseEntity.ok(servicioRepository.save(existente));
	}
	
	@DeleteMapping("/servicios/{id}")
	public ResponseEntity<Servicio> deleteServicio(@PathVariable("id")Integer id){
		servicioRepository.deleteById(id);
		return ResponseEntity.noContent().build();
} 
}
