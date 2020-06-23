package com.carros.domain;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.carros.domain.dto.CarroDTO;

public interface CarroRepository extends JpaRepository<Carro, Long> {

	List<Carro> findByTipo(String tipo);

	Carro save(CarroDTO db);

}
	