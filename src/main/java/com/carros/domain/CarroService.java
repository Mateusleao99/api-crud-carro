package com.carros.domain;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.internal.util.Assert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.carros.domain.dto.CarroDTO;


@Service
public class CarroService {
	
	@Autowired
	private CarroRepository rep;
	
	//Buscar toda lista
	public List<CarroDTO> getCarros(){

		return rep.findAll()
				.stream()
				.map(CarroDTO::create)
				.collect(Collectors.toList());
	
	}
	//Buscar carro por ID
	public Optional<CarroDTO> getCarroById(Long id){
		return rep.findById(id).map(CarroDTO::new);
	}
	//Buscar carro por tipo
	public List<CarroDTO> getCarroByTipo(String tipo){
		return rep.findByTipo(tipo).stream().map(CarroDTO::create).collect(Collectors.toList());
	}
	
	public Carro save(Carro carro) {
		return rep.save(carro);
	}
	//Inserindo carro 
	public Carro insert(Carro carro) {
		Assert.isNull(carro.getId(),"Não foi possivel inserir o registro");
		return rep.save(carro);
	}
	//Atualizando carro
	public Carro update(Carro carro, Long id) {
		Assert.notNull(id,"Não foi possivel atualizar o registro");
		
		//Buscar carro no banco de dados
		Optional<CarroDTO> optional = getCarroById(id);
		if(optional.isPresent()) {
			CarroDTO db = optional.get();
			//Copiar as propriedades
			db.setNome(carro.getNome());
			db.setTipo(carro.getTipo());
			System.out.println("Carro id " + db.getId());
			
			//Atualizar o carro
			
			return rep.save(db);
		}else {
			throw new RuntimeException("Não foi possivel atualizar o registro ");
		}
	
	

	}
	//Deletando Carro
	public void delete(Long id) {
		if(getCarroById(id).isPresent()) {
			rep.deleteById(id);
		}
		
	}
}
