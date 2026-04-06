package com.tdea.c2.mmb.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tdea.c2.mmb.modelo.Tecnico;

@Repository
public interface ITecnicoRepository extends JpaRepository<Tecnico, Integer> {

	List<Tecnico> findAll();

}
