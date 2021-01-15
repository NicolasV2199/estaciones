package com.example.demo.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Muestreo;
import com.example.demo.repositories.MuestreoRepository;

@Service
public class MuestreoServiceImp implements IMuestreoService{

	@Autowired
	MuestreoRepository mr;
	
	@Override
	public Muestreo crearMuestreo(Muestreo m) {
		return mr.save(m);
	}

	@Override
	public Muestreo actualizarMuestreo(Muestreo m) {
		return mr.save(m);
	}

	@Override
	public void eliminarMuestreo(Long id) {
		mr.deleteById(id);
	}

	@Override
	public List<Muestreo> listarTodosMuestreos() {
		return mr.findAll();
	}

	@Override
	public Optional<Muestreo> buscarMuestreoId(Long id) {
		return mr.findById(id);
	}

	

}

