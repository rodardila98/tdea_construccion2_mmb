package com.tdea.c2.mmb.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tdea.c2.mmb.modelo.Usuario;
import com.tdea.c2.mmb.repository.IUsuarioRepository;
import com.tdea.c2.mmb.service.IUsuarioService;

@Service
public class UsuarioServiceImpl implements IUsuarioService {

	@Autowired
	private IUsuarioRepository usuarioRepository;

	@Override
	public List<Usuario> getAllUsuarios() {
		return usuarioRepository.findAll();
	}

	@Override
	public Optional<Usuario> getUsuarioById(Integer id) {
		return usuarioRepository.findById(id);
	}

	@Override
	public Usuario createUsuario(Usuario usuario) {
		validarUsuario(usuario);
		return usuarioRepository.save(usuario);
	}

	@Override
	public Usuario updateUsuario(Integer id, Usuario usuario) {
		Usuario existente = usuarioRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("Usuario no encontrado con id: " + id));
		
		existente.setTipoDocumento(usuario.getTipoDocumento());
		existente.setNumDocumento(usuario.getNumDocumento());
		existente.setNombreCompleto(usuario.getNombreCompleto());
		existente.setDireccion(usuario.getDireccion());
		existente.setBarrio(usuario.getBarrio());
		existente.setCiudad(usuario.getCiudad());
		existente.setCorreo(usuario.getCorreo());
		existente.setNumCel(usuario.getNumCel());
		
		return usuarioRepository.save(existente);
	}

	@Override
	public void deleteUsuario(Integer id) {
		usuarioRepository.deleteById(id);
	}

	// Método privado para validaciones
	private void validarUsuario(Usuario usuario) {
		if (usuario == null) {
			throw new IllegalArgumentException("Usuario no puede ser nulo");
		}
		if (usuario.getTipoDocumento() == null || usuario.getTipoDocumento().trim().isEmpty()) {
			throw new IllegalArgumentException("Campo 'tipoDocumento' es requerido");
		}
		if (usuario.getNombreCompleto() == null || usuario.getNombreCompleto().trim().isEmpty()) {
			throw new IllegalArgumentException("Campo 'nombreCompleto' es requerido");
		}
		if (usuario.getCorreo() == null || usuario.getCorreo().trim().isEmpty()) {
			throw new IllegalArgumentException("Campo 'correo' es requerido");
		}
		if (usuario.getNumCel() == null || usuario.getNumCel() <= 0) {
			throw new IllegalArgumentException("Campo 'numCel' debe ser un número válido");
		}
	}
}