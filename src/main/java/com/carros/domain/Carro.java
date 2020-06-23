package com.carros.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;

//Lombok
@Data
//Entity (name="NOME DA TABELA ")
@Entity 
public class Carro {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	//Column (name= "NOME DA COLUNA")
	@Column
	private String nome;
	private String tipo;
	

	
	
}
