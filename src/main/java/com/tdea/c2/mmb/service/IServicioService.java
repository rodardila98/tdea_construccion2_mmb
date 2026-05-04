package com.tdea.c2.mmb.service;

import java.util.List;
import java.util.Optional;

import com.tdea.c2.mmb.modelo.Servicio;

public interface IServicioService {
	
	List<Servicio> getAllServicios();
	Optional<Servicio> getServicioById(Integer id);
	Servicio createServicio(Servicio servicio);
	Servicio updateServicio(Integer id, Servicio servicio);
	void deleteServicio(Integer id);
	Servicio updateEstadoServicio(Integer id, String nuevoEstado);

}
