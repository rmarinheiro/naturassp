package br.com.rafael.naturassp.services;

import java.util.ArrayList;

import br.com.rafael.naturassp.model.Categoria;

public interface ICategoriaService {
	
	//metodo responsavel em receber uma categoria e inserir no banco
	public Categoria inserirNovaCategoria(Categoria categoria);
	
	//metodo responsavel em alterar uma categoria existente e retornar se a categoria foi atualizada com sucesso.
	public Categoria alterarCategoria(Categoria categoria);
	
	//Metodo responsavel em recuperar todas as categorias sem filtro
	public ArrayList<Categoria> recuperarTodasCategorias();
	
	//Metodo responsavel por recuperar categoria por palavra chave
	public ArrayList<Categoria> recuperarPorPalavraChave(String palavraChave);
	
	//recupera uma unica categoria
	public Categoria recuperarPorId(int id);
	
	//recupera todas as categorias por Id
	public ArrayList<Categoria> recuperarTodasOrdenadasPorId();
	

}
