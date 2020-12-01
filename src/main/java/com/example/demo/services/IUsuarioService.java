package com.example.demo.services;

import java.util.List;

import com.example.demo.model.Usuario;

public interface IUsuarioService {

	Usuario crearUsuario(Usuario u);
	Usuario actualizarUsuario(Usuario u); 
	void eliminarUsuario(Usuario u);
	
	
	List<Usuario> listarTodosUsuarios();
	Usuario buscarUsuarioId(Long id);
}

