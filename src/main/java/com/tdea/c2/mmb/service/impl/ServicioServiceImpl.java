package com.tdea.c2.mmb.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tdea.c2.mmb.modelo.Servicio;
import com.tdea.c2.mmb.modelo.Usuario;
import com.tdea.c2.mmb.modelo.Tecnico;
import com.tdea.c2.mmb.modelo.Equipo;
import com.tdea.c2.mmb.repository.IServicioRepository;
import com.tdea.c2.mmb.repository.IUsuarioRepository;
import com.tdea.c2.mmb.repository.ITecnicoRepository;
import com.tdea.c2.mmb.repository.IEquipoRepository;
import com.tdea.c2.mmb.service.IServicioService;

@Service
public class ServicioServiceImpl implements IServicioService {

	@Autowired
	private IServicioRepository servicioRepository;
	
	@Autowired
	private IUsuarioRepository usuarioRepository;
	
	@Autowired
	private ITecnicoRepository tecnicoRepository;
	
	@Autowired
	private IEquipoRepository equipoRepository;

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
		validarYAsignarUsuario(servicio);
		validarYAsignarTecnico(servicio);
		validarYAsignarEquipo(servicio);
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
		
		// Validar y actualizar Usuario si se envía
		if (servicio.getUsuario() != null && servicio.getUsuario().getNumDocumento() != null) {
			validarYAsignarUsuario(servicio);
			existente.setUsuario(servicio.getUsuario());
		}
		
		// Validar y actualizar Técnico si se envía
		if (servicio.getTecnico() != null && servicio.getTecnico().getNumDocumento() != null) {
			validarYAsignarTecnico(servicio);
			existente.setTecnico(servicio.getTecnico());
		}
		
		// Validar y actualizar Equipo si se envía
		if (servicio.getEquipo() != null && servicio.getEquipo().getSerial() != null) {
			validarYAsignarEquipo(servicio);
			existente.setEquipo(servicio.getEquipo());
		}
		
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

	// Métodos privados para validaciones
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
	
	private void validarYAsignarUsuario(Servicio servicio) {
		if (servicio.getUsuario() == null || servicio.getUsuario().getNumDocumento() == null) {
			throw new IllegalArgumentException("El usuario es requerido y debe incluir el número de documento");
		}
		
		Integer doc = servicio.getUsuario().getNumDocumento();
		Usuario usuario = usuarioRepository.findById(doc)
			.orElseThrow(() -> new IllegalArgumentException(
				"Usuario no encontrado con ID: " + doc));
		
		servicio.setUsuario(usuario);
	}
	
	private void validarYAsignarTecnico(Servicio servicio) {
		if (servicio.getTecnico() == null || servicio.getTecnico().getNumDocumento() == null) {
			throw new IllegalArgumentException("El técnico es requerido y debe incluir el número de documento");
		}
		
		Integer doc = servicio.getTecnico().getNumDocumento();
		Tecnico tecnico = tecnicoRepository.findById(doc)
			.orElseThrow(() -> new IllegalArgumentException(
				"Técnico no encontrado con ID: " + doc));
		
		servicio.setTecnico(tecnico);
	}
	
	private void validarYAsignarEquipo(Servicio servicio) {
		if (servicio.getEquipo() == null || servicio.getEquipo().getSerial() == null) {
			throw new IllegalArgumentException("El equipo es requerido y debe incluir el número de serie");
		}
		
		Long serial = servicio.getEquipo().getSerial();
		Equipo equipo = equipoRepository.findById(serial)
			.orElseThrow(() -> new IllegalArgumentException(
				"Equipo no encontrado con serial: " + serial));
		
		servicio.setEquipo(equipo);
	}
}