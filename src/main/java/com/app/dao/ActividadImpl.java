package com.app.dao;

import java.util.List;
import java.util.stream.Collectors;

import javax.swing.text.html.parser.Entity;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.dto.ActividadDTO;
import com.app.entidades.Actividad;
import com.app.entidades.Usuario;
import com.app.repositorios.ActividadRepositorio;
import com.app.repositorios.UsuarioRepositorio;
import com.app.servicios.ActividadServicio;

import jakarta.persistence.EntityNotFoundException;

@Service
public class ActividadImpl implements ActividadServicio {

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private ActividadRepositorio actividadRepositorio;
	
	@Autowired
	private UsuarioRepositorio usuarioRepositorio;

	@Override
	public ActividadDTO registrarActividad(Integer usuarioId, ActividadDTO actividadDTO) {
		Usuario usuario = usuarioRepositorio.findById(usuarioId).orElse(null);
		
		if (!(usuario == null)) {
			Actividad contacto = mapearEndtidad(actividadDTO);
			contacto.setUsuario(usuario);
			Actividad contactoGuardado =  actividadRepositorio.save(contacto);
			return mapearDTO(contactoGuardado);
		}
		else {
			return null;
		}
	}
	
	
	
	private ActividadDTO mapearDTO(Actividad actividad) {

		ActividadDTO contactoDTO = modelMapper.map(actividad, ActividadDTO.class);

		return contactoDTO;
	}

	
	private Actividad mapearEndtidad(ActividadDTO actividadDTO) {

		Actividad contacto= modelMapper.map(actividadDTO, Actividad.class);

		return contacto;
	}



	@Override
	public List<ActividadDTO> listarActividadPorIdUsuario(Integer usuarioId) {
		List<Actividad> actividades = actividadRepositorio.findByUsuarioId(usuarioId);
		return actividades.stream().map(actividad -> mapearDTO(actividad)).collect(Collectors.toList());
	}



	@Override
	public ActividadDTO actualizaActividad(Integer actividadId, ActividadDTO actividadDTO) {		
		Actividad actividadEncontrada = actividadRepositorio.findById(actividadId)
				.orElseThrow(EntityNotFoundException::new);
		// Actualiza las propiedades individuales del contactoEncontrado
		actividadEncontrada.setTitulo(actividadDTO.getTitulo());
		actividadEncontrada.setFechaInicio(actividadDTO.getFechaInicio());
		actividadEncontrada.setFechaFin(actividadDTO.getFechaFin());
		actividadEncontrada.setDescripcion(actividadDTO.getDescripcion());
		actividadEncontrada.setColor(actividadDTO.getColor());
		actividadEncontrada.setNotas(actividadDTO.getNotas());
		
		
	    // Guarda los cambios en la base de datos
		actividadRepositorio.save(actividadEncontrada);
	    
	    return mapearDTO(actividadEncontrada);
	}



	@Override
	public ActividadDTO buscarActividadPorId(Integer contactoId) {
		Actividad contacto = actividadRepositorio.findById(contactoId)
				.orElseThrow(EntityNotFoundException::new);
		
		return mapearDTO(contacto);
	}



	@Override
	public void eliminarActividad(Integer contactoId) {
		// TODO Auto-generated method stub
		Actividad contacto = actividadRepositorio.findById(contactoId)
				.orElseThrow(EntityNotFoundException::new);
		actividadRepositorio.deleteById(contactoId);
	}

}
