package com.tdea.c2.mmb.service;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.tdea.c2.mmb.dto.ServicioCreateRequest;
import com.tdea.c2.mmb.modelo.Servicio;
import com.tdea.c2.mmb.modelo.Tecnico;
import com.tdea.c2.mmb.modelo.Usuario;
import com.tdea.c2.mmb.repository.IServicioRepository;
import com.tdea.c2.mmb.repository.ITecnicoRepository;
import com.tdea.c2.mmb.repository.IUsuarioRepository;

@Service
public class ServicioService {
	
	private final IServicioRepository servicioRepository;
	private final IUsuarioRepository usuarioRepository;
	private final ITecnicoRepository tecnicoRepository;
	
	public ServicioService(IServicioRepository servicioRepository, IUsuarioRepository usuarioRepository,
			ITecnicoRepository tecnicoRepository) {
		this.servicioRepository = servicioRepository;
		this.usuarioRepository = usuarioRepository;
		this.tecnicoRepository = tecnicoRepository;
	}
	
	public Servicio crearServicio(ServicioCreateRequest request) {
		if (request.getClienteId() == null) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Campo 'clienteId' es requerido");
		}
		if (request.getTecnicoId() == null) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Campo 'tecnicoId' es requerido");
		}
		
		Usuario cliente = usuarioRepository.findById(request.getClienteId())
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Cliente no encontrado"));
		Tecnico tecnico = tecnicoRepository.findById(request.getTecnicoId())
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Técnico no encontrado"));
		
		Servicio servicio = new Servicio();
		servicio.setFechaServicio(request.getFechaServicio());
		servicio.setHoraServicio(request.getHoraServicio());
		servicio.setTipoServicio(request.getTipoServicio());
		servicio.setEstadoServicio(request.getEstadoServicio());
		servicio.setCliente(cliente);
		servicio.setTecnico(tecnico);
		
		return servicioRepository.save(servicio);
	}
}
