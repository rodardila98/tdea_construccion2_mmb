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

import com.tdea.c2.mmb.modelo.Equipo;
import com.tdea.c2.mmb.repository.IEquipoRepository;

@RestController //Cualquier método retorna en http json
@RequestMapping("/api") //Asigna las solicitudes http a métodos dentro del controlador 
public class EquipoController {
	
	@Autowired //Crea un objeto @Repository en este caso y lo inyecta aquí
	private IEquipoRepository equipoRepository;
	
	@GetMapping("/equipos")
	public ResponseEntity<List<Equipo>> getAllEquipos(){
		List<Equipo> equipos = equipoRepository.findAll();
		
		if (equipos == null || equipos.isEmpty()) {
			
			// Muy básica: si no hay equipos, devolvemos 204 No Content
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.ok(equipos);
	}
	
	@GetMapping("/equipos/{id}")
	public ResponseEntity<Equipo> getEquipoById(@PathVariable("id") Long id){
		Optional<Equipo> opt = equipoRepository.findById(id);
		// Muy básica: si no existe, devolvemos 404 Not Found
		return ResponseEntity.of(opt);
	}
	
	@PostMapping("/equipos")
	public ResponseEntity<?> createEquipo(@RequestBody Equipo equipo) {
		// Validaciones muy básicas
		if (equipo == null) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Equipo no puede ser nulo");
		}
		if (equipo.getMarca() == null || equipo.getMarca().trim().isEmpty()) {
			
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Campo 'marca' es requerido");
		}
		if (equipo.getModelo() == null || equipo.getModelo().trim().isEmpty()) {
			
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Campo 'modelo' es requerido");
		}
		// Si pasa las validaciones, guardamos y devolvemos 201 Created
		Equipo saved = equipoRepository.save(equipo);
		return ResponseEntity.status(HttpStatus.CREATED).body(saved);
	}
	
	@PutMapping("/equipos/{id}")
	public ResponseEntity<Equipo> updateEquipo(@PathVariable("id")Long id, @RequestBody Equipo equipo) {
		Equipo existente = equipoRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("No encontrado"));
		existente.setSerial(equipo.getSerial());
		existente.setMarca(equipo.getMarca());
		existente.setModelo(equipo.getModelo());
		existente.setTipo(equipo.getTipo());
		return ResponseEntity.ok(equipoRepository.save(existente));
	}
	
	@DeleteMapping("/equipos/{id}")
	public ResponseEntity<Equipo> deleteEquipo(@PathVariable("id")Long id){
		equipoRepository.deleteById(id);
		return ResponseEntity.noContent().build();
		
		
	}
}




