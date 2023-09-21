package com.app.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioDTO {
	
	private Integer id;
	
	private String nombres;
	
	private String apellidos;

	private String nomUser;

	private String contrasenia;
	
	private String correo;
	
}
