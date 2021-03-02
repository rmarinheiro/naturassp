package br.com.rafael.naturassp.dao;

import java.util.ArrayList;

import org.springframework.data.repository.CrudRepository;

import br.com.rafael.naturassp.model.Categoria;

public interface CategoriaDao extends CrudRepository<Categoria, Integer> {
	
	// Metodo responsavel por retornar uma palavra chave
	public ArrayList<Categoria> findByNomeContaining(String palavra);

}
