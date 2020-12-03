package com.example.demo.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Parcela;
import com.example.demo.repositories.ParcelaRepository;

@Service
public class ParcelaServiceImp implements IParcelaService {

	@Autowired
	ParcelaRepository pr;
	
	@Override
	public Parcela crearParcela(Parcela p) {
		return pr.save(p);
	}

	@Override
	public Parcela actualizarParcela(Parcela p) {
		return pr.save(p);
	}

	@Override
	public void eliminarParcela(Parcela p) {
		pr.delete(p);

	}

	@Override
	public List<Parcela> listarTodasParcelas() {
		return pr.findAll();
	}

	@Override
	public Parcela buscarParcelaId(Long id) {
		return pr.getOne(id);
	}

}
