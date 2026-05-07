package com.tdea.c2.mmb.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tdea.c2.mmb.modelo.Equipo;
import com.tdea.c2.mmb.repository.IEquipoRepository;
import com.tdea.c2.mmb.service.IEquipoService;

@Service
public class EquipoServiceImpl implements IEquipoService {

	@Autowired
	private IEquipoRepository equipoRepository;

	@Override
	public List<Equipo> getAllEquipos() {
		return equipoRepository.findAll();
	}

	@Override
	public Optional<Equipo> getEquipoById(Long id) {
		return equipoRepository.findById(id);
	}

	@Override
	public Equipo createEquipo(Equipo equipo) {
		validarEquipo(equipo);
		return equipoRepository.save(equipo);
	}

	@Override
	public Equipo updateEquipo(Long id, Equipo equipo) {
		Equipo existente = equipoRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("Equipo no encontrado con id: " + id));
		
		existente.setSerial(equipo.getSerial());
		existente.setMarca(equipo.getMarca());
		existente.setModelo(equipo.getModelo());
		existente.setTipo(equipo.getTipo());
		
		return equipoRepository.save(existente);
	}

	@Override
	public void deleteEquipo(Long id) {
		equipoRepository.deleteById(id);
	}

	// Método privado para validaciones
	private void validarEquipo(Equipo equipo) {
		if (equipo == null) {
			throw new IllegalArgumentException("Equipo no puede ser nulo");
		}
		if (equipo.getSerial() == null) {
			throw new IllegalArgumentException("Campo 'serial' es requerido");
		}
		if (equipo.getMarca() == null || equipo.getMarca().trim().isEmpty()) {
			throw new IllegalArgumentException("Campo 'marca' es requerido");
		}
		if (equipo.getModelo() == null || equipo.getModelo().trim().isEmpty()) {
			throw new IllegalArgumentException("Campo 'modelo' es requerido");
		}
		if (equipo.getTipo() == null || equipo.getTipo().trim().isEmpty()) {
			throw new IllegalArgumentException("Campo 'tipo' es requerido");
		}
	}
}