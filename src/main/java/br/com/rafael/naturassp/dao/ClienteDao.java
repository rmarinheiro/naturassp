package br.com.rafael.naturassp.dao;

import java.util.ArrayList;

import org.springframework.data.repository.CrudRepository;

import br.com.rafael.naturassp.model.Categoria;
import br.com.rafael.naturassp.model.Cliente;

public interface ClienteDao extends CrudRepository<Cliente, Integer> {
	
	// Metodo responsavel por retornar uma palavra chave
	public Cliente findByEmailOrTelefone(String email,String telefone);
	
	//public Cliente findByTelefone(String telefone);
	
	public Cliente findByCpf(String cpf);

}
