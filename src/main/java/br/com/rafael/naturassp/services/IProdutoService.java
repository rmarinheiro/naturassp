package br.com.rafael.naturassp.services;

import java.util.ArrayList;

import br.com.rafael.naturassp.model.Categoria;
import br.com.rafael.naturassp.model.Produto;

public interface IProdutoService {
	
	public Produto inserirNovoProduto(Produto produto);
	
	public Produto alterarProduto(Produto produto);
	
	public ArrayList<Produto> listarTodos();
	
	public ArrayList<Produto> listarDisponiveis();
	
	public ArrayList<Produto> listarIndisponiveis();
	
	public ArrayList<Produto> listaPorCategoria(Categoria cat);
	
	public Produto listaProdutoPorId(int idProduto);
	
	public ArrayList<Produto> listarDestaques();
	
	public ArrayList<Produto> listarPorPalavraChave(String key);

}
