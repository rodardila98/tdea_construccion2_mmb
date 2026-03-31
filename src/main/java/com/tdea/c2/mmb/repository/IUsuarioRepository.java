package com.tdea.c2.mmb.repository;

import java.util.List;

import com.tdea.c2.mmb.modelo.Usuario;

public interface IUsuarioRepository {

	List<Usuario> findAll();

}
