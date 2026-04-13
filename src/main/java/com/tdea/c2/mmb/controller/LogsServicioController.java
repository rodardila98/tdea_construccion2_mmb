package com.tdea.c2.mmb.controller;

import java.util.List;
import java.util.Optional;

import java.time.LocalDateTime;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tdea.c2.mmb.modelo.LogsServicios;
import com.tdea.c2.mmb.repository.ILogsServicioRepository;

@RestController
@RequestMapping("/api")
public class LogsServicioController {
	
	@Autowired
	private ILogsServicioRepository logsServicioRepository;
	
	@GetMapping("/logsservicios")
	public ResponseEntity<List<LogsServicios>> getAllLogsServicios(){
		List<LogsServicios> logs = logsServicioRepository.findAll();
		if (logs == null || logs.isEmpty()) {
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.ok(logs);
	}

	@GetMapping("/logsservicios/{id}")
	public ResponseEntity<LogsServicios> getLogsById(@PathVariable("id") Integer id) {
		Optional<LogsServicios> opt = logsServicioRepository.findById(id);
		return ResponseEntity.of(opt);
	}

	@PostMapping("/logsservicios")
	public ResponseEntity<?> createLogs(@RequestBody LogsServicios logs) {
		if (logs == null) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("LogsServicios no puede ser nulo");
		}
		if (logs.getServicio() == null) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Campo 'servicio' es requerido");
		}
		// Establecer fecha de modificación en el momento de creación
		logs.setFechaModificacion(LocalDateTime.now());
		LogsServicios saved = logsServicioRepository.save(logs);
		return ResponseEntity.status(HttpStatus.CREATED).body(saved);
	}

	@PutMapping("/logsservicios/{id}")
	public ResponseEntity<LogsServicios> updateLogs(@PathVariable("id") Integer id, @RequestBody LogsServicios cambios) {
		LogsServicios existente = logsServicioRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("No encontrado"));
		existente.setServicio(cambios.getServicio());
		existente.setFechaServicio(cambios.getFechaServicio());
		existente.setHoraServicio(cambios.getHoraServicio());
		existente.setTipoServicio(cambios.getTipoServicio());
		existente.setEstadoServicio(cambios.getEstadoServicio());
		existente.setFechaModificacion(LocalDateTime.now());
		return ResponseEntity.ok(logsServicioRepository.save(existente));
	}

	@DeleteMapping("/logsservicios/{id}")
	public ResponseEntity<LogsServicios> deleteLogs(@PathVariable("id") Integer id){
		logsServicioRepository.deleteById(id);
		return ResponseEntity.noContent().build();
	}

}
