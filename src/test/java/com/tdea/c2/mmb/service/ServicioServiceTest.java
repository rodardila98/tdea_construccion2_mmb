package com.tdea.c2.mmb.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import com.tdea.c2.mmb.dto.ServicioCreateRequest;
import com.tdea.c2.mmb.modelo.Servicio;
import com.tdea.c2.mmb.modelo.Tecnico;
import com.tdea.c2.mmb.modelo.Usuario;
import com.tdea.c2.mmb.repository.IServicioRepository;
import com.tdea.c2.mmb.repository.ITecnicoRepository;
import com.tdea.c2.mmb.repository.IUsuarioRepository;

@ExtendWith(MockitoExtension.class)
class ServicioServiceTest {
	
	@Mock
	private IServicioRepository servicioRepository;
	
	@Mock
	private IUsuarioRepository usuarioRepository;
	
	@Mock
	private ITecnicoRepository tecnicoRepository;
	
	private ServicioService servicioService;
	
	@BeforeEach
	void setUp() {
		servicioService = new ServicioService(servicioRepository, usuarioRepository, tecnicoRepository);
	}
	
	@Test
	void crearServicioDebeFallarCuandoClienteIdEsNulo() {
		ServicioCreateRequest request = crearRequestValido();
		request.setClienteId(null);
		
		ResponseStatusException ex = assertThrows(ResponseStatusException.class, () -> servicioService.crearServicio(request));
		
		assertEquals(HttpStatus.BAD_REQUEST, ex.getStatusCode());
		assertEquals("Campo 'clienteId' es requerido", ex.getReason());
	}
	
	@Test
	void crearServicioDebeFallarCuandoClienteNoExiste() {
		ServicioCreateRequest request = crearRequestValido();
		when(usuarioRepository.findById(1001)).thenReturn(Optional.empty());
		
		ResponseStatusException ex = assertThrows(ResponseStatusException.class, () -> servicioService.crearServicio(request));
		
		assertEquals(HttpStatus.NOT_FOUND, ex.getStatusCode());
		assertEquals("Cliente no encontrado", ex.getReason());
	}
	
	@Test
	void crearServicioDebeFallarCuandoTecnicoNoExiste() {
		ServicioCreateRequest request = crearRequestValido();
		Usuario cliente = new Usuario();
		cliente.setNumDocumento(1001);
		
		when(usuarioRepository.findById(1001)).thenReturn(Optional.of(cliente));
		when(tecnicoRepository.findById(2002)).thenReturn(Optional.empty());
		
		ResponseStatusException ex = assertThrows(ResponseStatusException.class, () -> servicioService.crearServicio(request));
		
		assertEquals(HttpStatus.NOT_FOUND, ex.getStatusCode());
		assertEquals("Técnico no encontrado", ex.getReason());
	}
	
	@Test
	void crearServicioDebePersistirRelacionesCuandoIdsSonValidos() {
		ServicioCreateRequest request = crearRequestValido();
		Usuario cliente = new Usuario();
		cliente.setNumDocumento(1001);
		Tecnico tecnico = new Tecnico();
		tecnico.setNumDocumento(2002);
		
		when(usuarioRepository.findById(1001)).thenReturn(Optional.of(cliente));
		when(tecnicoRepository.findById(2002)).thenReturn(Optional.of(tecnico));
		when(servicioRepository.save(any(Servicio.class))).thenAnswer(invocation -> invocation.getArgument(0));
		
		Servicio saved = servicioService.crearServicio(request);
		
		ArgumentCaptor<Servicio> captor = ArgumentCaptor.forClass(Servicio.class);
		verify(servicioRepository).save(captor.capture());
		Servicio persisted = captor.getValue();
		assertNotNull(saved);
		assertEquals("Instalación", persisted.getTipoServicio());
		assertEquals(cliente, persisted.getCliente());
		assertEquals(tecnico, persisted.getTecnico());
	}
	
	private ServicioCreateRequest crearRequestValido() {
		ServicioCreateRequest request = new ServicioCreateRequest();
		request.setFechaServicio(LocalDate.of(2026, 1, 10));
		request.setHoraServicio("10:00");
		request.setTipoServicio("Instalación");
		request.setEstadoServicio("Pendiente");
		request.setClienteId(1001);
		request.setTecnicoId(2002);
		return request;
	}
}
