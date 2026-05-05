package com.tdea.c2.mmb.service;

import java.util.List;
import java.util.Optional;

import com.tdea.c2.mmb.modelo.Tecnico;

public interface ITecnicoService {
	
	List<Tecnico> getAllTecnicos();
	Optional<Tecnico> getTecnicoById(Integer id);
	Tecnico createTecnico(Tecnico tecnico);
	Tecnico updateTecnico(Integer id, Tecnico tecnico);
	void deleteTecnico(Integer id);
	Tecnico updateEstadoTecnico(Integer id, String nuevoEstado);

}
