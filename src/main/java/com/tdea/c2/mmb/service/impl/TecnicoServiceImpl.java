package com.tdea.c2.mmb.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tdea.c2.mmb.modelo.Tecnico;
import com.tdea.c2.mmb.repository.ITecnicoRepository;
import com.tdea.c2.mmb.service.ITecnicoService;

@Service
public class TecnicoServiceImpl implements ITecnicoService {

	@Autowired
	private ITecnicoRepository tecnicoRepository;

	@Override
	public List<Tecnico> getAllTecnicos() {
		return tecnicoRepository.findAll();
	}

	@Override
	public Optional<Tecnico> getTecnicoById(Integer id) {
		return tecnicoRepository.findById(id);
	}

	@Override
	public Tecnico createTecnico(Tecnico tecnico) {
		validarTecnico(tecnico);
		return tecnicoRepository.save(tecnico);
	}

	@Override
	public Tecnico updateTecnico(Integer id, Tecnico tecnico) {
		Tecnico existente = tecnicoRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("Tecnico no encontrado con id: " + id));
		
		existente.setTipoDocumento(tecnico.getTipoDocumento());
		existente.setNumDocumento(tecnico.getNumDocumento());
		existente.setNombreCompleto(tecnico.getNombreCompleto());
		existente.setNumCel(tecnico.getNumCel());
		existente.setEspecialidad(tecnico.getEspecialidad());
		existente.setEstadoTecnico(tecnico.getEstadoTecnico());
		
		return tecnicoRepository.save(existente);
	}

	@Override
	public void deleteTecnico(Integer id) {
		tecnicoRepository.deleteById(id);
	}

	@Override
	public Tecnico updateEstadoTecnico(Integer id, String nuevoEstado) {
		Tecnico existente = tecnicoRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("Tecnico no encontrado con id: " + id));
		
		existente.setEstadoTecnico(nuevoEstado);
		
		return tecnicoRepository.save(existente);
	}

	// Método privado para validaciones
	private void validarTecnico(Tecnico tecnico) {
		if (tecnico == null) {
			throw new IllegalArgumentException("Tecnico no puede ser nulo");
		}
		if (tecnico.getNombreCompleto() == null || tecnico.getNombreCompleto().trim().isEmpty()) {
			throw new IllegalArgumentException("Campo 'nombreCompleto' es requerido");
		}
		if (tecnico.getTipoDocumento() == null || tecnico.getTipoDocumento().trim().isEmpty()) {
			throw new IllegalArgumentException("Campo 'tipoDocumento' es requerido");
		}
		if (tecnico.getNumCel() == null || tecnico.getNumCel() <= 0) {
			throw new IllegalArgumentException("Campo 'numCel' debe ser un número válido");
		}
	}
}