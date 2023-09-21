package com.app.servicios;

import com.app.dto.UsuarioDTO;


public interface UsuarioServicio {
	
	public UsuarioDTO RegistrarUsuario(UsuarioDTO usuarioDTO);

	public UsuarioDTO validarUsuario(String nomUser, String contrasenia);
	
}
