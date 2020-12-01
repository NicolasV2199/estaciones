package com.example.demo.services;

import java.util.List;

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
	public void eliminarMuestreo(Muestreo m) {
		mr.delete(m);
		
	}

	@Override
	public List<Muestreo> listarTodosMuestreos() {
		return mr.findAll();
	}

	@Override
	public Muestreo buscarMuestreoId(Long id) {
		return mr.getOne(id);
	}

	

}

