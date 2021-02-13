package com.example.demo.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Usuario;
import com.example.demo.repositories.UsuarioRepository;

@Service
public class UsuarioServiceImp implements IUsuarioService{

	@Autowired
	UsuarioRepository ur;
	

	@Override
	public Usuario crearUsuario(Usuario u) {
		return ur.save(u);
	}

	@Override
	public Usuario actualizarUsuario(Usuario u) {
		return ur.save(u);
	}

	@Override
	public void eliminarUsuario(Long id) {
		ur.deleteById(id);
		
	}

	@Override
	public List<Usuario> listarTodosUsuarios() {
		return ur.findAll();
	}

	@Override
	public Optional<Usuario> buscarUsuarioId(Long id) {
		return ur.findById(id);
	}

	@Override
	public Optional<Usuario> buscarUsuarioEmail(String email) {
		return ur.findByEmail(email);
	}

}
