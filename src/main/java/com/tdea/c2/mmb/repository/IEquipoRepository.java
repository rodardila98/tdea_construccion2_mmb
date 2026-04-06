package com.tdea.c2.mmb.repository;

import com.tdea.c2.mmb.modelo.Equipo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IEquipoRepository extends JpaRepository<Equipo, Long> {



}