package com.carros.api;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.carros.domain.Carro;
import com.carros.domain.CarroService;
import com.carros.domain.dto.CarroDTO;


@RestController
@RequestMapping("/api/v1/carros")
public class CarrosController {
	
	@Autowired
	private CarroService service;
	
	@GetMapping()
	public ResponseEntity<List<CarroDTO>> get() {
		return ResponseEntity.ok(service.getCarros());
		//return new ResponseEntity<>( service.getCarros(), HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity get(@PathVariable("id") Long id) {
		 Optional<CarroDTO> carro = service.getCarroById(id);
		 return carro .map(ResponseEntity::ok) .orElse(ResponseEntity.notFound().build());
				 
	}
	
	@GetMapping("/tipo/{tipo}")
	public ResponseEntity <List<CarroDTO>> getCarroByTipo(@PathVariable("tipo") String tipo) {
		List<CarroDTO> carros =  service.getCarroByTipo(tipo);
		
		return carros.isEmpty()?
				ResponseEntity.noContent().build() :
				ResponseEntity.ok(carros);
	}

	@PostMapping
	public String  post(@RequestBody Carro carro) {
		Carro c = service.insert(carro);
		
		return "Carro salvo com sucesso: " + c.getId();
	}

	@PutMapping
	public String  put(@PathVariable("id") Long id, @RequestBody Carro carro) {
		Carro c = service.update(carro, id);
		
		return "Carro atualizado com sucesso: " + c.getId();
	}
	
	@DeleteMapping
	public String  delete(@PathVariable("id") Long id) {
		service.delete(id);
		
		return "Carro deletado com sucesso: ";
	}
}
