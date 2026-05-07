package com.tdea.c2.mmb.service;

import java.util.List;
import java.util.Optional;

import com.tdea.c2.mmb.modelo.Equipo;

public interface IEquipoService {
	
	List<Equipo> getAllEquipos();
	Optional<Equipo> getEquipoById(Long id);
	Equipo createEquipo(Equipo equipo);
	Equipo updateEquipo(Long id, Equipo equipo);
	void deleteEquipo(Long id);


}
