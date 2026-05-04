package com.tdea.c2.mmb.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tdea.c2.mmb.modelo.Servicio;
import com.tdea.c2.mmb.repository.IServicioRepository;
import com.tdea.c2.mmb.service.IServicioService;

@Service
public class ServicioServiceImpl implements IServicioService {

	@Autowired
	private IServicioRepository servicioRepository;

	@Override
	public List<Servicio> getAllServicios() {
		return servicioRepository.findAll();
	}

	@Override
	public Optional<Servicio> getServicioById(Integer id) {
		return servicioRepository.findById(id);
	}

	@Override
	public Servicio createServicio(Servicio servicio) {
		validarServicio(servicio);
		return servicioRepository.save(servicio);
	}

	@Override
	public Servicio updateServicio(Integer id, Servicio servicio) {
		Servicio existente = servicioRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("Servicio no encontrado con id: " + id));
		
		existente.setIdServicio(servicio.getIdServicio());
		existente.setFechaServicio(servicio.getFechaServicio());
		existente.setHoraServicio(servicio.getHoraServicio());
		existente.setTipoServicio(servicio.getTipoServicio());
		existente.setEstadoServicio(servicio.getEstadoServicio());
		
		return servicioRepository.save(existente);
	}

	@Override
	public void deleteServicio(Integer id) {
		servicioRepository.deleteById(id);
	}

	@Override
	public Servicio updateEstadoServicio(Integer id, String nuevoEstado) {
		Servicio existente = servicioRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("Servicio no encontrado con id: " + id));
		
		existente.setEstadoServicio(nuevoEstado);
		
		return servicioRepository.save(existente);
	}

	// Método privado para validaciones
	private void validarServicio(Servicio servicio) {
		if (servicio == null) {
			throw new IllegalArgumentException("Servicio no puede ser nulo");
		}
		if (servicio.getTipoServicio() == null || servicio.getTipoServicio().trim().isEmpty()) {
			throw new IllegalArgumentException("Campo 'tipoServicio' es requerido");
		}
		if (servicio.getEstadoServicio() == null || servicio.getEstadoServicio().trim().isEmpty()) {
			throw new IllegalArgumentException("Campo 'estadoServicio' es requerido");
		}
		if (servicio.getFechaServicio() == null) {
			throw new IllegalArgumentException("Campo 'fechaServicio' es requerido");
		}
	}
}