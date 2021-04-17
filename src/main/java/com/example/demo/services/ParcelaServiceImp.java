package com.example.demo.services;

import java.util.List;
import java.util.Optional;

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
	public void eliminarParcela(Long id) {
		pr.deleteById(id);

	}

	@Override
	public List<Parcela> listarTodasParcelas() {
		return pr.findAll();
	}

	@Override
	public Optional<Parcela> buscarParcelaId(Long id) {
		return pr.findById(id);
	}

}
