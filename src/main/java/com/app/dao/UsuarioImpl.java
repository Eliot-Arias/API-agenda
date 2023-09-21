package com.app.dao;

import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.dto.UsuarioDTO;
import com.app.entidades.Usuario;
import com.app.excepciones.ResourceNotFoundExcepcion;
import com.app.repositorios.UsuarioRepositorio;
import com.app.servicios.UsuarioServicio;

@Service
public class UsuarioImpl implements UsuarioServicio {

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private UsuarioRepositorio usuarioRepositorio;

	@Override
	public UsuarioDTO RegistrarUsuario(UsuarioDTO usuarioDTO) {
		Usuario usuario = mapearEndtidad(usuarioDTO);
		Usuario usuarioRespuesta = usuarioRepositorio.save(usuario);

		return mapearDTO(usuarioRespuesta);
	}
	
	
	@Override
	public UsuarioDTO validarUsuario(String nomUser, String contrasenia) {
		Optional<Usuario> usuarioConsulta = usuarioRepositorio.findByNomUserAndContrasenia(nomUser, contrasenia);
		return usuarioConsulta.map(this::mapearDTO).orElse(null);
	}

	
	
	

	private UsuarioDTO mapearDTO(Usuario usuario) {

		UsuarioDTO usuarioDTO = modelMapper.map(usuario, UsuarioDTO.class);

		return usuarioDTO;
	}

	private Usuario mapearEndtidad(UsuarioDTO usuarioDTO) {

		Usuario usuario = modelMapper.map(usuarioDTO, Usuario.class);

		return usuario;
	}






	
}
