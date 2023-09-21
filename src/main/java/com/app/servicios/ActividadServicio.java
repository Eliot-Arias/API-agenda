package com.app.servicios;

import java.util.List;

import com.app.dto.ActividadDTO;

public interface ActividadServicio {

	public ActividadDTO registrarActividad(Integer usuarioId, ActividadDTO actividadDTO);

	public List<ActividadDTO> listarActividadPorIdUsuario(Integer usuarioId);

	public ActividadDTO buscarActividadPorId(Integer actividadId);

	public ActividadDTO actualizaActividad(Integer actividadId, ActividadDTO actividadDTO);

	public void eliminarActividad(Integer actividadId);

}
