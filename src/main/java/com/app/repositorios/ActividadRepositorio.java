package com.app.repositorios;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.app.entidades.Actividad;

@Repository
public interface ActividadRepositorio extends JpaRepository<Actividad, Integer> {
	
	public List<Actividad> findByUsuarioId(Integer UsuarioId);
}
