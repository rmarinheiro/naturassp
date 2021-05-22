package br.com.rafael.naturassp.dao;

import java.util.ArrayList;

import org.springframework.data.repository.CrudRepository;

import br.com.rafael.naturassp.model.Categoria;
import br.com.rafael.naturassp.model.Produto;

public interface ProdutoDao extends CrudRepository<Produto, Integer> {
	
	public ArrayList<Produto> findAllByCategoria(Categoria categoria);
	
	public ArrayList<Produto> findAllByDisponivel(int disponivel);
	
	public ArrayList<Produto> findAllByDisponivelAndCategoria(int disponivel, Categoria cat);
	
	public ArrayList<Produto> findAllByDestaque(int destaque);
	
	public ArrayList<Produto> findByNomeContainingOrDetalheContaining(String keyNome, String keyDetalhe);

}
