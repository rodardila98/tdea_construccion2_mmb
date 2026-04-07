package com.tdea.c2.mmb.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tdea.c2.mmb.modelo.Servicio;

@Repository
public interface IServicioRepository extends JpaRepository<Servicio, Integer> {

}
