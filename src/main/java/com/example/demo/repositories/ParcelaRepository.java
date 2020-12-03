package com.example.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.Parcela;



public interface ParcelaRepository extends JpaRepository<Parcela, Long> {
	

}

