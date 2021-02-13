package com.example.demo.services;

import java.util.List;
import java.util.Optional;

import com.example.demo.model.Usuario;

public interface IUsuarioService {

	Usuario crearUsuario(Usuario u);
	Usuario actualizarUsuario(Usuario u); 
	void eliminarUsuario(Long id);
	
	
	List<Usuario> listarTodosUsuarios();
	Optional<Usuario> buscarUsuarioId(Long id);
	Optional<Usuario> buscarUsuarioEmail(String email);
}

