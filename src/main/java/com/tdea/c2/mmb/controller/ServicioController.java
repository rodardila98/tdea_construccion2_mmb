package com.tdea.c2.mmb.controller;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.tdea.c2.mmb.dto.ServicioCreateRequest;
import com.tdea.c2.mmb.modelo.Servicio;
import com.tdea.c2.mmb.repository.IServicioRepository;
import com.tdea.c2.mmb.service.ServicioService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api")
public class ServicioController {
	
	@Autowired
	private IServicioRepository servicioRepository;
	
	@Autowired
	private ServicioService servicioService;
	
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
	public ResponseEntity<?> createServicio(@Valid @RequestBody ServicioCreateRequest request) {
		if (request == null) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Servicio no puede ser nulo");
		}
		// Validaciones muy básicas: tipo y estado son requeridos
		if (request.getTipoServicio() == null || request.getTipoServicio().trim().isEmpty()) {
			
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Campo 'tipoServicio' es requerido");
		}
		if (request.getEstadoServicio() == null || request.getEstadoServicio().trim().isEmpty()) {
			
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Campo 'estadoServicio' es requerido");
		}
		// fechaServicio podría validarse más, pero por ahora comprobamos no nulo
		if (request.getFechaServicio() == null) {
			
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Campo 'fechaServicio' es requerido");
		}
		try {
			Servicio saved = servicioService.crearServicio(request);
			return ResponseEntity.status(HttpStatus.CREATED).body(saved);
		} catch (ResponseStatusException ex) {
			return ResponseEntity.status(ex.getStatusCode()).body(ex.getReason());
		}
	}
	
	@PutMapping("/servicios/{id}")
	public ResponseEntity<Servicio> updateTecnico(@PathVariable("id") Integer id, @RequestBody Servicio servicios) {
		Servicio existente = servicioRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("No encontrado"));
		existente.setFechaServicio(servicios.getFechaServicio());
		existente.setHoraServicio(servicios.getHoraServicio());
		existente.setTipoServicio(servicios.getTipoServicio());
		existente.setEstadoServicio(servicios.getEstadoServicio());
		if (servicios.getCliente() != null) {
			existente.setCliente(servicios.getCliente());
		}
		if (servicios.getTecnico() != null) {
			existente.setTecnico(servicios.getTecnico());
		}
		return ResponseEntity.ok(servicioRepository.save(existente));
	}
	
	@DeleteMapping("/servicios/{id}")
	public ResponseEntity<Servicio> deleteServicio(@PathVariable("id")Integer id){
			servicioRepository.deleteById(id);
		return ResponseEntity.noContent().build();
} 
	
	@PatchMapping("/servicios/{id}")
	public ResponseEntity<Servicio> updateEstadoServ(@PathVariable("id") Integer id, @RequestBody Map<String, Object> cambios) {
		Servicio existente = servicioRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("No encontrado"));

		if (cambios.containsKey("estadoServicio")) {
        existente.setEstadoServicio((String) cambios.get("estadoServicio"));
    }

    return ResponseEntity.ok(servicioRepository.save(existente));

	}
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<String> handleValidationException(MethodArgumentNotValidException ex) {
		String errorMessage = ex.getBindingResult().getFieldError() != null
				? ex.getBindingResult().getFieldError().getDefaultMessage()
				: "Solicitud inválida";
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorMessage);
	}
}
