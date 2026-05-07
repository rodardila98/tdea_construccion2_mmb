package com.tdea.c2.mmb.service;

import java.util.List;
import java.util.Optional;

import com.tdea.c2.mmb.modelo.Usuario;

public interface IUsuarioService {

	List<Usuario> getAllUsuarios();
	Optional<Usuario> getUsuarioById(Integer id);
	Usuario createUsuario(Usuario usuario);
	Usuario updateUsuario(Integer id, Usuario usuario);
	void deleteUsuario(Integer id);
}
